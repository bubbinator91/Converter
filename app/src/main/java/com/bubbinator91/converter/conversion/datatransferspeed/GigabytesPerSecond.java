package com.bubbinator91.converter.conversion.datatransferspeed;

import com.bubbinator91.converter.conversion.util.Unit;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import rx.Observable;

/**
 * Handles the conversion from gigabytes per second to other units of data transfer speed
 */
public class GigabytesPerSecond extends Unit {

    // Prevents class from being instantiated directly
    private GigabytesPerSecond() {}

    // region Public methods

    /**
     * Takes in the gigabytes per second value as a {@link String} and converts it to bits per
     * second, bytes per second, kilobits per second, kilobytes per second, megabits per second,
     * megabytes per second, gigabits per second, terabits per second, and terabytes per second.
     *
     * @param gbyps             The gigabytes per second value as a {@link String}. Should not be
     *                           null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  An {@link Observable}, created with a call to defer(), that will either emit a
     *           {@link List} containing the equivalent bits per second, bytes per second,
     *           kilobits per second, kilobytes per second, megabits per second, megabytes per
     *           second, gigabits per second, terabits per second, and terabytes per second values
     *           (in that order; they will be empty {@link String}s if there is valid,
     *           non-numerical input, such as a leading decimal point), a null value if the
     *           <code>gbyps</code> parameter is null, or an error if an {@link Exception} was
     *           thrown.
     */
    public static Observable<List<String>> toAll(final String gbyps, final int decimalPlaces) {
        return Observable.defer(() -> {
            try {
                if (gbyps == null) {
                    return null;
                }

                int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
                List<String> results = new LinkedList<>();

                if (isNumeric(gbyps)) {
                    results.add(toBitsPerSecond(gbyps, roundingLength));
                    results.add(toBytesPerSecond(gbyps, roundingLength));
                    results.add(toKilobitsPerSecond(gbyps, roundingLength));
                    results.add(toKilobytesPerSecond(gbyps, roundingLength));
                    results.add(toMegabitsPerSecond(gbyps, roundingLength));
                    results.add(toMegabytesPerSecond(gbyps, roundingLength));
                    results.add(toGigabitsPerSecond(gbyps, roundingLength));
                    results.add(toTerabitsPerSecond(gbyps, roundingLength));
                    results.add(toTerabytesPerSecond(gbyps, roundingLength));
                } else if (gbyps.equals(".") || gbyps.equals("")) {
                    results.clear();
                    addEmptyItems(results, 9);
                } else {
                    throw new NumberFormatException("Input was not numeric.");
                }

                return Observable.just(results);
            } catch (Exception e) {
                return Observable.error(e);
            }
        });
    }

    /**
     * Takes in the gigabytes per second value as a {@link String} and converts it to bits per
     * second.
     *
     * @param gbyps             The gigabytes per second value as a {@link String}. Should not be
     *                           null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent bits per second value as a {@link String}, or null if the
     *           <code>gbyps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toBitsPerSecond(String gbyps, int decimalPlaces) throws
            NumberFormatException, ValueBelowZeroException {
        if (gbyps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal dts = new BigDecimal(gbyps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("8000000000"))
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
     * Takes in the gigabytes per second value as a {@link String} and converts it to bytes per
     * second.
     *
     * @param gbyps             The gigabytes per second value as a {@link String}. Should not be
     *                           null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent bytes per second value as a {@link String}, or null if the
     *           <code>gbyps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toBytesPerSecond(String gbyps, int decimalPlaces) throws
            NumberFormatException, ValueBelowZeroException {
        if (gbyps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal dts = new BigDecimal(gbyps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("1000000000"))
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
     * Takes in the gigabytes per second value as a {@link String} and converts it to kilobits per
     * second.
     *
     * @param gbyps             The gigabytes per second value as a {@link String}. Should not be
     *                           null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent kilobits per second value as a {@link String}, or null if the
     *           <code>gbyps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toKilobitsPerSecond(String gbyps, int decimalPlaces) throws
            NumberFormatException, ValueBelowZeroException {
        if (gbyps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal dts = new BigDecimal(gbyps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("8000000"))
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
     * Takes in the gigabytes per second value as a {@link String} and converts it to kilobytes per
     * second.
     *
     * @param gbyps             The gigabytes per second value as a {@link String}. Should not be
     *                           null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent kilobytes per second value as a {@link String}, or null if the
     *           <code>gbyps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toKilobytesPerSecond(String gbyps, int decimalPlaces) throws
            NumberFormatException, ValueBelowZeroException {
        if (gbyps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal dts = new BigDecimal(gbyps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("1000000"))
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
     * Takes in the gigabytes per second value as a {@link String} and converts it to megabits per
     * second.
     *
     * @param gbyps             The gigabytes per second value as a {@link String}. Should not be
     *                           null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent megabits per second value as a {@link String}, or null if the
     *           <code>gbyps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toMegabitsPerSecond(String gbyps, int decimalPlaces) throws
            NumberFormatException, ValueBelowZeroException {
        if (gbyps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal dts = new BigDecimal(gbyps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("8000"))
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
     * Takes in the gigabytes per second value as a {@link String} and converts it to megabytes per
     * second.
     *
     * @param gbyps             The gigabytes per second value as a {@link String}. Should not be
     *                           null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent megabytes per second value as a {@link String}, or null if the
     *           <code>gbyps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toMegabytesPerSecond(String gbyps, int decimalPlaces) throws
            NumberFormatException, ValueBelowZeroException {
        if (gbyps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal dts = new BigDecimal(gbyps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("1000"))
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
     * Takes in the gigabytes per second value as a {@link String} and converts it to gigabits per
     * second.
     *
     * @param gbyps             The gigabytes per second value as a {@link String}. Should not be
     *                           null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent gigabits per second value as a {@link String}, or null if the
     *           <code>gbyps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toGigabitsPerSecond(String gbyps, int decimalPlaces) throws
            NumberFormatException, ValueBelowZeroException {
        if (gbyps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal dts = new BigDecimal(gbyps);
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
     * Takes in the gigabytes per second value as a {@link String} and converts it to terabits per
     * second.
     *
     * @param gbyps             The gigabytes per second value as a {@link String}. Should not be
     *                           null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent terabits per second value as a {@link String}, or null if the
     *           <code>gbyps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toTerabitsPerSecond(String gbyps, int decimalPlaces) throws
            NumberFormatException, ValueBelowZeroException {
        if (gbyps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal dts = new BigDecimal(gbyps);
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
     * Takes in the gigabytes per second value as a {@link String} and converts it to terabytes per
     * second.
     *
     * @param gbyps             The gigabytes per second value as a {@link String}. Should not be
     *                           null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent terabytes per second value as a {@link String}, or null if the
     *           <code>gbyps</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toTerabytesPerSecond(String gbyps, int decimalPlaces) throws
            NumberFormatException, ValueBelowZeroException {
        if (gbyps == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal dts = new BigDecimal(gbyps);
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

    // endregion
}
