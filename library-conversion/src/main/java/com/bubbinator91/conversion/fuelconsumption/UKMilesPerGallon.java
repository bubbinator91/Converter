package com.bubbinator91.conversion.fuelconsumption;

import android.util.Log;
import android.util.Pair;

import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.conversion.util.Unit;
import com.bubbinator91.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles the conversion from UK miles per gallon (mpg) to other units of fuel consumption
 */
public class UKMilesPerGallon extends Unit {
    private static final String TAG = UKMilesPerGallon.class.getSimpleName();

    /**
     * Takes in the UK miles per gallon value as a {@link String} and converts it to US miles per
     * gallon, kilometers per liter, and liters per 100 kilometers.
     *
     * @param ukmpg             The UK miles per gallon value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link Pair}, where the first item is a {@link List} containing the equivalent
     *          US miles per gallon, kilometers per liter, and liters per 100 kilometers values (in
     *          that order; they will be empty {@link String}s if there is an error), and the second
     *          item is one of the error codes found in {@link ConversionErrorCodes}, or null if the
     *          <code>ukmpg</code> parameter is null.
     */
    public static Pair<List<String>, ConversionErrorCodes> toAll(String ukmpg, int decimalPlaces) {
        if (ukmpg == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        List<String> results = new LinkedList<>();
        ConversionErrorCodes error = ConversionErrorCodes.ERROR_NONE;

        if (isNumeric(ukmpg)) {
            try {
                results.add(toUSMilesPerGallon(ukmpg, roundingLength));
                results.add(toKilometersPerLiter(ukmpg, roundingLength));
                results.add(toLitersPer100Kilometers(ukmpg, roundingLength));
            } catch (NumberFormatException e) {
                Log.e(TAG + ".toAll", e.getLocalizedMessage());
                results.clear();
                addEmptyItems(results, 3);
                error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
            } catch (ValueBelowZeroException e) {
                Log.e(TAG + ".toAll", e.getLocalizedMessage());
                results.clear();
                addEmptyItems(results, 3);
                error = ConversionErrorCodes.ERROR_BELOW_ZERO;
            }
        } else if (ukmpg.equals(".") || ukmpg.equals("")) {
            results.clear();
            addEmptyItems(results, 3);
        } else {
            addEmptyItems(results, 3);
            error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
        }

        return new Pair<>(results, error);
    }

    /**
     * Takes in the UK miles per gallon value as a {@link String} and converts it to US miles per
     * gallon.
     *
     * @param ukmpg             The UK miles per gallon value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent US miles per gallon value as a {@link String}, or null if the
     *          <code>ukmpg</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toUSMilesPerGallon(String ukmpg, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (ukmpg == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(ukmpg);
        if (fuel.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            fuel = fuel.multiply(new BigDecimal("0.8326742"))
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
     * Takes in the UK miles per gallon value as a {@link String} and converts it to kilometers per
     * liter
     *
     * @param ukmpg             The UK miles per gallon value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kilometers per liter value as a {@link String}, or null if the
     *          <code>ukmpg</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toKilometersPerLiter(String ukmpg, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (ukmpg == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(ukmpg);
        if (fuel.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            fuel = fuel.multiply(new BigDecimal("0.3540062"))
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
     * Takes in the UK miles per gallon value as a {@link String} and converts it to liters per 100
     * kilometers.
     *
     * @param ukmpg             The UK miles per gallon value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent liters per 100 kilometers value as a {@link String}, or null if the
     *          <code>ukmpg</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toLitersPer100Kilometers(String ukmpg, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (ukmpg == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(ukmpg);
        if (fuel.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            fuel = fuel.multiply(new BigDecimal("282.4809363"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (fuel.compareTo(BigDecimal.ZERO) == 0) {
                fuel = BigDecimal.ZERO;
            }
            return fuel.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Fuel consumption cannot be below zero");
        }
    }
}
