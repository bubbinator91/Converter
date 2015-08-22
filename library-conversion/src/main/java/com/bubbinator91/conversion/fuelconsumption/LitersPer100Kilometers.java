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
 * Handles the conversion from liters per 100 kilometers (L/100km) to other units of fuel
 * consumption
 */
public class LitersPer100Kilometers extends Unit {
    private static final String TAG = LitersPer100Kilometers.class.getSimpleName();

    /**
     * Takes in the liters per 100 kilometers value as a {@link String} and converts it to US miles
     * per gallon, UK miles per gallon, and kilometers per liter.
     *
     * @param l100km            The liters per 100 kilometers value as a {@link String}. Should not
     *                          be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link Pair}, where the first item is a {@link List} containing the equivalent
     *          US miles per gallon, UK miles per gallon, and kilometers per liter values (in that
     *          order; they will be empty {@link String}s if there is an error), and the second item
     *          is one of the error codes found in {@link ConversionErrorCodes}, or null if the
     *          <code>l100km</code> parameter is null.
     */
    public static Pair<List<String>, ConversionErrorCodes> toAll(String l100km, int decimalPlaces) {
        if (l100km == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        List<String> results = new LinkedList<>();
        ConversionErrorCodes error = ConversionErrorCodes.ERROR_NONE;

        if (isNumeric(l100km)) {
            try {
                results.add(toUSMilesPerGallon(l100km, roundingLength));
                results.add(toUKMilesPerGallon(l100km, roundingLength));
                results.add(toKilometersPerLiter(l100km, roundingLength));
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
        } else if (l100km.equals(".") || l100km.equals("")) {
            results.clear();
            addEmptyItems(results, 3);
        } else {
            addEmptyItems(results, 3);
            error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
        }

        return new Pair<>(results, error);
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
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toUSMilesPerGallon(String l100km, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (l100km == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(l100km);
        if (fuel.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            fuel = fuel.multiply(new BigDecimal("235.2145833"))
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
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toUKMilesPerGallon(String l100km, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (l100km == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(l100km);
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
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toKilometersPerLiter(String l100km, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (l100km == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(l100km);
        if (fuel.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            fuel = fuel.multiply(new BigDecimal("100"))
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
