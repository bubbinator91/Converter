package com.bubbinator91.conversion.acceleration;

import android.util.Log;
import android.util.Pair;

import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.conversion.util.Unit;
import com.bubbinator91.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles the conversion from meter per second^2 (m/s^2) to other units of acceleration
 */
public class MetersPerSecondSquared extends Unit {
    private static final String TAG = MetersPerSecondSquared.class.getSimpleName();

    /**
     * Takes in the meters per second squared value as a {@link String} and converts it to
     * centimeters per second squared, feet per second squared, and standard gravity.
     *
     * @param mpss              The meters per second squared value as a {@link String}. Should not
     *                          be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link Pair}, where the first item is a {@link List} containing the equivalent
     *          centimeters per second squared, feet per second squared, and standard gravity values
     *          (in that order; they will be empty {@link String}s if there is an error), and the
     *          second item is one of the error codes found in {@link ConversionErrorCodes}, or null
     *          if the <code>mpss</code> parameter is null.
     */
    public static Pair<List<String>, ConversionErrorCodes> toAll(String mpss, int decimalPlaces) {
        if (mpss == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        List<String> results = new LinkedList<>();
        ConversionErrorCodes error = ConversionErrorCodes.ERROR_NONE;

        if (isNumeric(mpss)) {
            try {
                results.add(toCentimetersPerSecondSquared(mpss, roundingLength));
                results.add(toFeetPerSecondSquared(mpss, roundingLength));
                results.add(toStandardGravity(mpss, roundingLength));
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
        } else if (mpss.equals(".") || mpss.equals("")) {
            results.clear();
            addEmptyItems(results, 3);
        } else {
            addEmptyItems(results, 3);
            error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
        }

        return new Pair<>(results, error);
    }

    /**
     * Takes in the meters per second squared value as a {@link String} and converts it to
     * centimeters per second squared.
     *
     * @param mpss              The meters per second squared value as a {@link String}. Should not
     *                          be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent centimeters per second squared value as a {@link String}, or null if
     *          the <code>mpss</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toCentimetersPerSecondSquared(String mpss, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (mpss == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal acceleration = new BigDecimal(mpss);
        if (acceleration.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            acceleration = acceleration.multiply(new BigDecimal("100"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (acceleration.compareTo(BigDecimal.ZERO) == 0) {
                acceleration = BigDecimal.ZERO;
            }
            return acceleration.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Acceleration cannot be below zero");
        }
    }

    /**
     * Takes in the meters per second squared value as a {@link String} and converts it to feet per
     * second squared.
     *
     * @param mpss              The meters per second squared value as a {@link String}. Should not
     *                          be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent feet per second squared value as a {@link String}, or null if the
     *          <code>mpss</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toFeetPerSecondSquared(String mpss, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (mpss == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal acceleration = new BigDecimal(mpss);
        if (acceleration.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            acceleration = acceleration.multiply(new BigDecimal("3.280839895"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (acceleration.compareTo(BigDecimal.ZERO) == 0) {
                acceleration = BigDecimal.ZERO;
            }
            return acceleration.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Acceleration cannot be below zero");
        }
    }

    /**
     * Takes in the meters per second squared value as a {@link String} and converts it to standard
     * gravity
     *
     * @param mpss              The meters per second squared value as a {@link String}. Should not
     *                          be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent standard gravity value as a {@link String}, or null if the
     *          <code>mpss</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toStandardGravity(String mpss, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (mpss == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal acceleration = new BigDecimal(mpss);
        if (acceleration.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            acceleration = acceleration.multiply(new BigDecimal("0.1019716213"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (acceleration.compareTo(BigDecimal.ZERO) == 0) {
                acceleration = BigDecimal.ZERO;
            }
            return acceleration.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Acceleration cannot be below zero");
        }
    }
}
