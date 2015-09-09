package com.bubbinator91.conversion.fuelconsumption;

import com.bubbinator91.conversion.util.Unit;
import com.bubbinator91.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles the conversion from US miles per gallon (mpg) to other units of fuel consumption.
 */
public class USMilesPerGallon extends Unit {

    // Prevents class from being instantiated directly
    private USMilesPerGallon() {}

    // region Singleton items

    /**
     * Holds the instance of the {@link USMilesPerGallon} class. Private so that only the
     * USMilesPerGallon class can use it, and static so that it can carry a static instance of the
     * USMilesPerGallon class.
     */
    private static class USMilesPerGallonInstance {
        private static final USMilesPerGallon INSTANCE = new USMilesPerGallon();
    }

    /**
     * Gets the instance of the {@link USMilesPerGallon} class from the USMilesPerGallonInstance
     * class. Protected so that only members of the same package can use this method, such as
     * {@link FuelConsumption}.
     *
     * @return  An instance of the {@link USMilesPerGallon} class.
     */
    protected static USMilesPerGallon getInstance() {
        return USMilesPerGallonInstance.INSTANCE;
    }

    // endregion

    // region Public methods

    /**
     * Takes in the US miles per gallon value as a {@link String} and converts it to UK miles per
     * gallon, kilometers per liter, and liters per 100 kilometers.
     *
     * @param usmpg             The US miles per gallon value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link List} containing the equivalent UK miles per gallon, kilometers per liter,
     *          and liters per 100 kilometers values (in that order; they will be empty
     *          {@link String}s if there is valid, non-numerical input, such as a leading decimal
     *          point), or null if the <code>ukmpg</code> parameter is null;
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public List<String> toAll(String usmpg, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (usmpg == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        List<String> results = new LinkedList<>();

        if (isNumeric(usmpg)) {
            results.add(toUKMilesPerGallon(usmpg, roundingLength));
            results.add(toKilometersPerLiter(usmpg, roundingLength));
            results.add(toLitersPer100Kilometers(usmpg, roundingLength));
        } else if (usmpg.equals(".") || usmpg.equals("")) {
            results.clear();
            addEmptyItems(results, 3);
        } else {
            throw new NumberFormatException("Input was not numeric.");
        }

        return results;
    }

    /**
     * Takes in the US miles per gallon value as a {@link String} and converts it to UK miles per
     * gallon.
     *
     * @param usmpg             The US miles per gallon value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent UK miles per gallon value as a {@link String}, or null if the
     *          <code>usmpg</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toUKMilesPerGallon(String usmpg, int decimalPlaces)
        throws NumberFormatException, ValueBelowZeroException {
        if (usmpg == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(usmpg);
        if (fuel.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            fuel = fuel.multiply(new BigDecimal("1.2009499255398"))
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
     * Takes in the US miles per gallon value as a {@link String} and converts it to kilometers per
     * liter
     *
     * @param usmpg             The US miles per gallon value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kilometers per liter value as a {@link String}, or null if the
     *          <code>usmpg</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toKilometersPerLiter(String usmpg, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (usmpg == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(usmpg);
        if (fuel.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            fuel = fuel.multiply(new BigDecimal("0.42514370749052"))
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
     * Takes in the US miles per gallon value as a {@link String} and converts it to liters per 100
     * kilometers.
     *
     * @param usmpg             The US miles per gallon value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent liters per 100 kilometers value as a {@link String}, or null if the
     *          <code>usmpg</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toLitersPer100Kilometers(String usmpg, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (usmpg == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(usmpg);
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

    // endregion
}
