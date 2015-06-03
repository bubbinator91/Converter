package com.bubbinator91.conversion.speed;

import android.util.Log;

import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.conversion.util.Tuple;
import com.bubbinator91.conversion.util.Unit;
import com.bubbinator91.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles the conversion from feet per second to other units of speed
 */
public class FeetPerSecond extends Unit {
    private static final String TAG = FeetPerSecond.class.getSimpleName();

    // Implemented as a singleton rather than just static methods so that the class could be
    // extended from a base class. Implemented with the singleton "Initialization-on-demand" pattern
    // so as to not take up unnecessary memory.

    // region Singleton things

    private FeetPerSecond() {}

    private static class FeetPerSecondHolder {
        private static final FeetPerSecond INSTANCE = new FeetPerSecond();
    }

    private static FeetPerSecond getInstance() { return FeetPerSecondHolder.INSTANCE; }

    // endregion

    // region Public methods

    /**
     * Takes in the feet per second value as a {@link String} and converts it to knots, kilometers
     * per hour, meters per second, and miles per hour.
     *
     * @param fps               The feet per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link Tuple}, where the first item is a {@link List} containing the equivalent
     *          knot, kilometers per hour, meters per second, and miles per hour values (in that
     *          order), and the second item is one of the error codes found in
     *          {@link ConversionErrorCodes}, or null if the <code>fps</code> parameter is null.
     */
    public static Tuple<List<String>, ConversionErrorCodes> toAll(String fps, int decimalPlaces) {
        return getInstance().internal_toAll(fps, decimalPlaces);
    }

    /**
     * Takes in the feet per second value as a {@link String} and converts it to knots.
     *
     * @param fps               The feet per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent knots value as a {@link String}, or null if the
     *          <code>fps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toKnots(String fps, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        return getInstance().internal_toKnot(fps, decimalPlaces);
    }

    /**
     * Takes in the feet per second value as a {@link String} and converts it to kilometers per hour.
     *
     * @param fps               The feet per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kilometers per hour value as a {@link String}, or null if the
     *          <code>fps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toKilometersPerHour(String fps, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        return getInstance().internal_toKph(fps, decimalPlaces);
    }

    /**
     * Takes in the feet per second value as a {@link String} and converts it to meters per second.
     *
     * @param fps               The feet per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent meters per second value as a {@link String}, or null if the
     *          <code>fps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toMetersPerSecond(String fps, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        return getInstance().internal_toMps(fps, decimalPlaces);
    }

    /**
     * Takes in the feet per second value as a {@link String} and converts it to miles per hour.
     *
     * @param fps               The feet per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent miles per hour value as a {@link String}, or null if the
     *          <code>fps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toMilesPerHour(String fps, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        return getInstance().internal_toMph(fps, decimalPlaces);
    }

    // endregion

    // region Private methods

    /**
     * Takes in the feet per second value as a {@link String} and converts it to knots, kilometers
     * per hour, meters per second, and miles per hour.
     *
     * @param fps               The feet per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link Tuple}, where the first item is a {@link List} containing the equivalent
     *          knot, kilometers per hour, meters per second, and miles per hour values (in that
     *          order), and the second item is one of the error codes found in
     *          {@link ConversionErrorCodes}, or null if the <code>fps</code> parameter is null.
     */
    private Tuple<List<String>, ConversionErrorCodes> internal_toAll(String fps, int decimalPlaces) {
        if (fps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        List<String> results = new LinkedList<>();
        ConversionErrorCodes error = ConversionErrorCodes.ERROR_NONE;

        if (isNumeric(fps)) {
            try {
                results.add(internal_toKnot(fps, roundingLength));
                results.add(internal_toKph(fps, roundingLength));
                results.add(internal_toMps(fps, roundingLength));
                results.add(internal_toMph(fps, roundingLength));
            } catch (NumberFormatException e) {
                Log.e(TAG + ".internal_toAll", e.getLocalizedMessage());
                results.clear();
                addEmptyItems(results, 4);
                error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
            } catch (ValueBelowZeroException e) {
                Log.e(TAG + ".internal_toAll", e.getLocalizedMessage());
                results.clear();
                addEmptyItems(results, 4);
                error = ConversionErrorCodes.ERROR_BELOW_ABSOLUTE_ZERO;
            }
        } else if (fps.equals(".") || fps.equals("")) {
            results.clear();
            addEmptyItems(results, 4);
        } else {
            addEmptyItems(results, 4);
            error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
        }

        return new Tuple<>(results, error);
    }

    /**
     * Takes in the feet per second value as a {@link String} and converts it to knots.
     *
     * @param fps               The feet per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent knots value as a {@link String}, or null if the
     *          <code>fps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    private String internal_toKnot(String fps, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (fps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal speed = new BigDecimal(fps);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("0.5924838012958964"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (speed.compareTo(BigDecimal.ZERO) == 0) {
                speed = BigDecimal.ZERO;
            }
            return speed.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Speed cannot be below zero");
        }
    }

    /**
     * Takes in the feet per second value as a {@link String} and converts it to kilometers per hour.
     *
     * @param fps               The feet per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kilometers per hour value as a {@link String}, or null if the
     *          <code>fps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    private String internal_toKph(String fps, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (fps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal speed = new BigDecimal(fps);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("1.09728"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (speed.compareTo(BigDecimal.ZERO) == 0) {
                speed = BigDecimal.ZERO;
            }
            return speed.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Speed cannot be below zero");
        }
    }

    /**
     * Takes in the feet per second value as a {@link String} and converts it to meters per second.
     *
     * @param fps               The feet per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent meters per second value as a {@link String}, or null if the
     *          <code>fps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    private String internal_toMps(String fps, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (fps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal speed = new BigDecimal(fps);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("0.3048"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (speed.compareTo(BigDecimal.ZERO) == 0) {
                speed = BigDecimal.ZERO;
            }
            return speed.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Speed cannot be below zero");
        }
    }

    /**
     * Takes in the feet per second value as a {@link String} and converts it to miles per hour.
     *
     * @param fps               The feet per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent miles per hour value as a {@link String}, or null if the
     *          <code>fps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    private String internal_toMph(String fps, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (fps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal speed = new BigDecimal(fps);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("3600"))
                    .divide(new BigDecimal("5280"), roundingLength, BigDecimal.ROUND_HALF_UP);
            if (speed.compareTo(BigDecimal.ZERO) == 0) {
                speed = BigDecimal.ZERO;
            }
            return speed.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Speed cannot be below zero");
        }
    }

    // endregion
}
