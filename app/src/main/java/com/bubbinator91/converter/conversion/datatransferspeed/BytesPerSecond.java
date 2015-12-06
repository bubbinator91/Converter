package com.bubbinator91.converter.conversion.datatransferspeed;

import com.bubbinator91.converter.conversion.util.ConversionErrorCodes;
import com.bubbinator91.converter.conversion.util.Tuple;
import com.bubbinator91.converter.conversion.util.Unit;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles the conversion from bytes per second to other units of data transfer speed
 */
public class BytesPerSecond extends Unit {

    // Prevents class from being instantiated directly
    private BytesPerSecond() {}

    // region Singleton items

    /**
     * Holds the instance of the {@link BytesPerSecond} class. Private so that only the
     * BytesPerSecond class can use it, and static so that it can carry a static instance of the
     * BytesPerSecond class.
     */
    private static class BytesPerSecondInstance {
        private static final BytesPerSecond INSTANCE = new BytesPerSecond();
    }

    /**
     * Gets the instance of the {@link BytesPerSecond} class from the BytesPerSecondInstance class.
     * Protected so that only members of the same package can use this method, such as
     * {@link DataTransferSpeed}.
     *
     * @return  An instance of the {@link BytesPerSecond} class.
     */
    protected static BytesPerSecond getInstance() {
        return BytesPerSecondInstance.INSTANCE;
    }

    // endregion

    // region Public methods

    /**
     * Takes in the bytes per second value as a {@link String} and converts it to bits per second,
     * kilobits per second, kilobytes per second, megabits per second, megabytes per second,
     * gigabits per second, gigabytes per second, terabits per second, and terabytes per second.
     *
     * @param byps              The bytes per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link Tuple}, where the first item is a {@link List} containing the equivalent
     *          bits per second, kilobits per second, kilobytes per second, megabits per second,
     *          megabytes per second, gigabits per second, gigabytes per second, terabits per
     *          second, and terabytes per second values (in that order; they will be empty
     *          {@link String}s if there is an error), and the second item is one of the error codes
     *          found in {@link ConversionErrorCodes} as an {@link Integer} object, or null if the
     *          <code>byps</code> parameter is null;
     */
    public Tuple<List<String>, Integer> toAll(String byps, int decimalPlaces) {
        if (byps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        List<String> results = new LinkedList<>();
        int error = ConversionErrorCodes.ERROR_NONE;

        if (isNumeric(byps)) {
            try {
                results.add(toBitsPerSecond(byps, roundingLength));
                results.add(toKilobitsPerSecond(byps, roundingLength));
                results.add(toKilobytesPerSecond(byps, roundingLength));
                results.add(toMegabitsPerSecond(byps, roundingLength));
                results.add(toMegabytesPerSecond(byps, roundingLength));
                results.add(toGigabitsPerSecond(byps, roundingLength));
                results.add(toGigabytesPerSecond(byps, roundingLength));
                results.add(toTerabitsPerSecond(byps, roundingLength));
                results.add(toTerabytesPerSecond(byps, roundingLength));
            } catch (NumberFormatException e) {
                results.clear();
                addEmptyItems(results, 9);
                error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
            } catch (ValueBelowZeroException e) {
                results.clear();
                addEmptyItems(results, 9);
                error = ConversionErrorCodes.ERROR_BELOW_ZERO;
            }
        } else if (byps.equals(".") || byps.equals("")) {
            results.clear();
            addEmptyItems(results, 9);
        } else {
            addEmptyItems(results, 9);
            error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
        }

        return new Tuple<>(results, error);
    }

    /**
     * Takes in the bytes per second value as a {@link String} and converts it to bits per second.
     *
     * @param byps              The bytes per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent bits per second value as a {@link String}, or null if the
     *          <code>byps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toBitsPerSecond(String byps, int decimalPlaces) throws
            NumberFormatException, ValueBelowZeroException {
        if (byps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal dts = new BigDecimal(byps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("8"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (dts.compareTo(BigDecimal.ZERO) == 0) {
                dts = BigDecimal.ZERO;
            }
            return dts.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Transfer speed cannot be below zero");
        }
    }

    /**
     * Takes in the bytes per second value as a {@link String} and converts it to kilobits per
     * second.
     *
     * @param byps              The bytes per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kilobits per second value as a {@link String}, or null if the
     *          <code>byps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toKilobitsPerSecond(String byps, int decimalPlaces) throws
            NumberFormatException, ValueBelowZeroException {
        if (byps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal dts = new BigDecimal(byps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("0.008"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (dts.compareTo(BigDecimal.ZERO) == 0) {
                dts = BigDecimal.ZERO;
            }
            return dts.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Transfer speed cannot be below zero");
        }
    }

    /**
     * Takes in the bytes per second value as a {@link String} and converts it to kilobytes per
     * second.
     *
     * @param byps              The bytes per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kilobytes per second value as a {@link String}, or null if the
     *          <code>byps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toKilobytesPerSecond(String byps, int decimalPlaces) throws
            NumberFormatException, ValueBelowZeroException {
        if (byps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal dts = new BigDecimal(byps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("0.001"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (dts.compareTo(BigDecimal.ZERO) == 0) {
                dts = BigDecimal.ZERO;
            }
            return dts.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Transfer speed cannot be below zero");
        }
    }

    /**
     * Takes in the bytes per second value as a {@link String} and converts it to megabits per
     * second.
     *
     * @param byps              The bytes per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent megabits per second value as a {@link String}, or null if the
     *          <code>byps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toMegabitsPerSecond(String byps, int decimalPlaces) throws
            NumberFormatException, ValueBelowZeroException {
        if (byps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal dts = new BigDecimal(byps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("0.000008"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (dts.compareTo(BigDecimal.ZERO) == 0) {
                dts = BigDecimal.ZERO;
            }
            return dts.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Transfer speed cannot be below zero");
        }
    }

    /**
     * Takes in the bytes per second value as a {@link String} and converts it to megabytes per
     * second.
     *
     * @param byps              The bytes per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent megabytes per second value as a {@link String}, or null if the
     *          <code>byps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toMegabytesPerSecond(String byps, int decimalPlaces) throws
            NumberFormatException, ValueBelowZeroException {
        if (byps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal dts = new BigDecimal(byps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("0.000001"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (dts.compareTo(BigDecimal.ZERO) == 0) {
                dts = BigDecimal.ZERO;
            }
            return dts.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Transfer speed cannot be below zero");
        }
    }

    /**
     * Takes in the bytes per second value as a {@link String} and converts it to gigabits per
     * second.
     *
     * @param byps              The bytes per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent gigabits per second value as a {@link String}, or null if the
     *          <code>byps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toGigabitsPerSecond(String byps, int decimalPlaces) throws
            NumberFormatException, ValueBelowZeroException {
        if (byps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal dts = new BigDecimal(byps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("0.000000008"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (dts.compareTo(BigDecimal.ZERO) == 0) {
                dts = BigDecimal.ZERO;
            }
            return dts.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Transfer speed cannot be below zero");
        }
    }

    /**
     * Takes in the bytes per second value as a {@link String} and converts it to gigabytes per
     * second.
     *
     * @param byps              The bytes per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent gigabytes per second value as a {@link String}, or null if the
     *          <code>byps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toGigabytesPerSecond(String byps, int decimalPlaces) throws
            NumberFormatException, ValueBelowZeroException {
        if (byps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal dts = new BigDecimal(byps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("0.000000001"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (dts.compareTo(BigDecimal.ZERO) == 0) {
                dts = BigDecimal.ZERO;
            }
            return dts.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Transfer speed cannot be below zero");
        }
    }

    /**
     * Takes in the bytes per second value as a {@link String} and converts it to terabits per
     * second.
     *
     * @param byps              The bytes per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent terabits per second value as a {@link String}, or null if the
     *          <code>byps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toTerabitsPerSecond(String byps, int decimalPlaces) throws
            NumberFormatException, ValueBelowZeroException {
        if (byps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal dts = new BigDecimal(byps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("0.000000000008"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (dts.compareTo(BigDecimal.ZERO) == 0) {
                dts = BigDecimal.ZERO;
            }
            return dts.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Transfer speed cannot be below zero");
        }
    }

    /**
     * Takes in the bytes per second value as a {@link String} and converts it to terabytes per
     * second.
     *
     * @param byps              The bytes per second value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent terabytes per second value as a {@link String}, or null if the
     *          <code>byps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toTerabytesPerSecond(String byps, int decimalPlaces) throws
            NumberFormatException, ValueBelowZeroException {
        if (byps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal dts = new BigDecimal(byps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("0.000000000001"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (dts.compareTo(BigDecimal.ZERO) == 0) {
                dts = BigDecimal.ZERO;
            }
            return dts.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Transfer speed cannot be below zero");
        }
    }

    // endregion
}
