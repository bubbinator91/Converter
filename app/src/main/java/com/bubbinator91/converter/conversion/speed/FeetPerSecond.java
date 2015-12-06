package com.bubbinator91.converter.conversion.speed;

import com.bubbinator91.converter.conversion.util.ConversionErrorCodes;
import com.bubbinator91.converter.conversion.util.Tuple;
import com.bubbinator91.converter.conversion.util.Unit;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles the conversion from feet per second to other units of speed
 */
public class FeetPerSecond extends Unit {

    // Prevents class from being instantiated directly
    private FeetPerSecond() {}

    // region Singleton items

    /**
     * Holds the instance of the {@link FeetPerSecond} class. Private so that only the FeetPerSecond
     * class can use it, and static so that it can carry a static instance of the FeetPerSecond
     * class.
     */
    private static class FeetPerSecondInstance {
        private static final FeetPerSecond INSTANCE = new FeetPerSecond();
    }

    /**
     * Gets the instance of the {@link FeetPerSecond} class from the FeetPerSecondInstance class.
     * Protected so that only members of the same package can use this method, such as
     * {@link Speed}.
     *
     * @return  An instance of the {@link FeetPerSecond} class.
     */
    protected static FeetPerSecond getInstance() {
        return FeetPerSecondInstance.INSTANCE;
    }

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
     *          knots, kilometers per hour, meters per second, and miles per hour values (in that
     *          order; they will be empty {@link String}s if there is an error), and the second item
     *          is one of the error codes found in {@link ConversionErrorCodes} as an
     *          {@link Integer} object, or null if the <code>fps</code> parameter is null;
     */
    public Tuple<List<String>, Integer> toAll(String fps, int decimalPlaces) {
        if (fps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        List<String> results = new LinkedList<>();
        int error = ConversionErrorCodes.ERROR_NONE;

        if (isNumeric(fps)) {
            try {
                results.add(toKnots(fps, roundingLength));
                results.add(toKilometersPerHour(fps, roundingLength));
                results.add(toMetersPerSecond(fps, roundingLength));
                results.add(toMilesPerHour(fps, roundingLength));
            } catch (NumberFormatException e) {
                results.clear();
                addEmptyItems(results, 4);
                error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
            } catch (ValueBelowZeroException e) {
                results.clear();
                addEmptyItems(results, 4);
                error = ConversionErrorCodes.ERROR_BELOW_ZERO;
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
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toKnots(String fps, int decimalPlaces)
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
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toKilometersPerHour(String fps, int decimalPlaces)
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
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toMetersPerSecond(String fps, int decimalPlaces)
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
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toMilesPerHour(String fps, int decimalPlaces)
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
