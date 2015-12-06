package com.bubbinator91.converter.conversion.fuelconsumption;

import com.bubbinator91.converter.conversion.util.Unit;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles the conversion from kilometers per liter (km/L) to other units of fuel consumption
 */
public class KilometersPerLiter extends Unit {

    // Prevents class from being instantiated directly
    private KilometersPerLiter() {}

    // region Singleton items

    /**
     * Holds the instance of the {@link KilometersPerLiter} class. Private so that only the
     * KilometersPerLiter class can use it, and static so that it can carry a static instance of the
     * KilometersPerLiter class.
     */
    private static class KilometersPerLiterInstance {
        private static final KilometersPerLiter INSTANCE = new KilometersPerLiter();
    }

    /**
     * Gets the instance of the {@link KilometersPerLiter} class from the KilometersPerLiterInstance
     * class. Protected so that only members of the same package can use this method, such as
     * {@link FuelConsumption}.
     *
     * @return  An instance of the {@link KilometersPerLiter} class.
     */
    protected static KilometersPerLiter getInstance() {
        return KilometersPerLiterInstance.INSTANCE;
    }

    // endregion

    // region Public methods

    /**
     * Takes in the kilometers per liter value as a {@link String} and converts it to US miles per
     * gallon, UK miles per gallon, and liters per 100 kilometers.
     *
     * @param kpl               The kilometers per liter value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link List} containing the equivalent US miles per gallon, UK miles per gallon,
     *          and liters per 100 kilometers values (in that order; they will be empty
     *          {@link String}s if there is valid, non-numerical input, such as a leading decimal
     *          point), or null if the <code>kpl</code> parameter is null;
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public List<String> toAll(String kpl, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (kpl == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        List<String> results = new LinkedList<>();

        if (isNumeric(kpl)) {
            results.add(toUSMilesPerGallon(kpl, roundingLength));
            results.add(toUKMilesPerGallon(kpl, roundingLength));
            results.add(toLitersPer100Kilometers(kpl, roundingLength));
        } else if (kpl.equals(".") || kpl.equals("")) {
            results.clear();
            addEmptyItems(results, 3);
        } else {
            throw new NumberFormatException("Input was not numeric.");
        }

        return results;
    }

    /**
     * Takes in the kilometers per liter value as a {@link String} and converts it to US miles per
     * gallon.
     *
     * @param kpl               The kilometers per liter value as a {@link String}. Should not be
     *                          null.
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
    public String toUSMilesPerGallon(String kpl, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (kpl == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(kpl);
        if (fuel.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            fuel = fuel.multiply(new BigDecimal("2.352145833"))
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
     * Takes in the kilometers per liter value as a {@link String} and converts it to UK miles per
     * gallon
     *
     * @param kpl               The kilometers per liter value as a {@link String}. Should not be
     *                          null.
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
    public String toUKMilesPerGallon(String kpl, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (kpl == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(kpl);
        if (fuel.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            fuel = fuel.multiply(new BigDecimal("2.824809363"))
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
     * Takes in the kilometers per liter value as a {@link String} and converts it to liters per 100
     * kilometers.
     *
     * @param kpl               The kilometers per liter value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent liters per 100 kilometers value as a {@link String}, or null if the
     *          <code>kpl</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toLitersPer100Kilometers(String kpl, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (kpl == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(kpl);
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
