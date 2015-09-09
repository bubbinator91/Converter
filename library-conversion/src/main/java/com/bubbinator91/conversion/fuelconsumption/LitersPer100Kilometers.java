package com.bubbinator91.conversion.fuelconsumption;

import com.bubbinator91.conversion.util.Unit;
import com.bubbinator91.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles the conversion from liters per 100 kilometers (L/100km) to other units of fuel
 * consumption
 */
public class LitersPer100Kilometers extends Unit {

    // Prevents class from being instantiated directly
    private LitersPer100Kilometers() {}

    // region Singleton items

    /**
     * Holds the instance of the {@link LitersPer100Kilometers} class. Private so that only the
     * LitersPer100Kilometers class can use it, and static so that it can carry a static instance of
     * the LitersPer100Kilometers class.
     */
    private static class LitersPer100KilometersInstance {
        private static final LitersPer100Kilometers INSTANCE = new LitersPer100Kilometers();
    }

    /**
     * Gets the instance of the {@link LitersPer100Kilometers} class from the
     * LitersPer100KilometersInstance class. Protected so that only members of the same package can
     * use this method, such as {@link FuelConsumption}.
     *
     * @return  An instance of the {@link LitersPer100Kilometers} class.
     */
    protected static LitersPer100Kilometers getInstance() {
        return LitersPer100KilometersInstance.INSTANCE;
    }

    // endregion

    // region Public methods

    /**
     * Takes in the liters per 100 kilometers value as a {@link String} and converts it to US miles
     * per gallon, UK miles per gallon, and kilometers per liter.
     *
     * @param l100km            The liters per 100 kilometers value as a {@link String}. Should not
     *                          be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link List} containing the equivalent US miles per gallon, UK miles per gallon,
     *          and kilometers per liter values (in that order; they will be empty {@link String}s
     *          if there is valid, non-numerical input, such as a leading decimal point), or null if
     *          the <code>l100km</code> parameter is null;
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public List<String> toAll(String l100km, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (l100km == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        List<String> results = new LinkedList<>();

        if (isNumeric(l100km)) {
            results.add(toUSMilesPerGallon(l100km, roundingLength));
            results.add(toUKMilesPerGallon(l100km, roundingLength));
            results.add(toKilometersPerLiter(l100km, roundingLength));
        } else if (l100km.equals(".") || l100km.equals("")) {
            results.clear();
            addEmptyItems(results, 3);
        } else {
            throw new NumberFormatException("Input was not numeric.");
        }

        return results;
    }

    /**
     * Takes in the liters per 100 kilometers value as a {@link String} and converts it to US miles
     * per gallon.
     *
     * @param l100km            The liters per 100 kilometers value as a {@link String}. Should not
     *                          be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent US miles per gallon value as a {@link String}, or null if the
     *          <code>kpl</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toUSMilesPerGallon(String l100km, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (l100km == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(l100km);
        if (fuel.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            fuel = (new BigDecimal("378.5411784"))
                    .divide((fuel.multiply(new BigDecimal("1.609344"))), 100, BigDecimal.ROUND_HALF_UP)
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (fuel.compareTo(BigDecimal.ZERO) == 0) {
                fuel = BigDecimal.ZERO;
            }
            return fuel.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Fuel consumption cannot be below zero");
        }
    }

    /**
     * Takes in the liters per 100 kilometers value as a {@link String} and converts it to UK miles
     * per gallon
     *
     * @param l100km            The liters per 100 kilometers value as a {@link String}. Should not
     *                          be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent UK miles per gallon value as a {@link String}, or null if the
     *          <code>kpl</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toUKMilesPerGallon(String l100km, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (l100km == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(l100km);
        if (fuel.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            fuel = (new BigDecimal("454.609"))
                    .divide((fuel.multiply(new BigDecimal("1.609344"))), 100, BigDecimal.ROUND_HALF_UP)
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (fuel.compareTo(BigDecimal.ZERO) == 0) {
                fuel = BigDecimal.ZERO;
            }
            return fuel.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Fuel consumption cannot be below zero");
        }
    }

    /**
     * Takes in the liters per 100 kilometers value as a {@link String} and converts it to
     * kilometers per liter.
     *
     * @param l100km            The liters per 100 kilometers value as a {@link String}. Should not
     *                          be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kilometers per liter value as a {@link String}, or null if the
     *          <code>kpl</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toKilometersPerLiter(String l100km, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (l100km == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(l100km);
        if (fuel.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            fuel = (new BigDecimal("100"))
                    .divide(fuel, 100, BigDecimal.ROUND_HALF_UP)
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (fuel.compareTo(BigDecimal.ZERO) == 0) {
                fuel = BigDecimal.ZERO;
            }
            return fuel.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Fuel consumption cannot be below zero");
        }
    }

    // endregion
}
