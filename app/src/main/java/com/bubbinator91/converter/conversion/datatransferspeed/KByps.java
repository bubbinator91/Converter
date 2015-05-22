package com.bubbinator91.converter.conversion.datatransferspeed;

import android.support.annotation.NonNull;

import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import timber.log.Timber;

/**
 * Handles the conversion from kilobytes per second to other units of length
 */
public class KByps {
    private static final String TAG = "KByps";

    /**
     * Static method that takes in the kilobytes per second value as a {@link String} and converts
     * it to bits per second, bytes per second, kilobits per second, megabits per second, megabytes
     * per second, gigabits per second, gigabytes per second, terabits per second, and terabytes
     * per second.
     *
     * @param kbyps             The kilobytes per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent bits per second, bytes per second, kilobits per second, megabits per
     *          second, megabytes per second, gigabits per second, gigabytes per second, terabits
     *          per second, and terabytes per second values stored in an {@link ArrayList}, in that
     *          order.
     */
    public static ArrayList<String> toAll(@NonNull String kbyps, int roundingLength) {
        Timber.tag(TAG + ".toAll").i("Entered");
        Timber.tag(TAG + ".toAll").i("kbyps = " + kbyps);
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        ArrayList<String> results = new ArrayList<>();
        if (Utils.isNumeric(kbyps)) {
            try {
                results.add(toBps(kbyps, decimalPlaces));
                results.add(toByps(kbyps, decimalPlaces));
                results.add(toKbps(kbyps, decimalPlaces));
                results.add(toMbps(kbyps, decimalPlaces));
                results.add(toMByps(kbyps, decimalPlaces));
                results.add(toGbps(kbyps, decimalPlaces));
                results.add(toGByps(kbyps, decimalPlaces));
                results.add(toTbps(kbyps, decimalPlaces));
                results.add(toTByps(kbyps, decimalPlaces));
            } catch (NumberFormatException e) {
                Timber.tag(TAG + ".toAll").e(e.getMessage());
                results.clear();
                Utils.addWhitespaceItems(results, 9);
            }
        } else {
            Timber.tag(TAG + ".toAll").i("Input was not numeric");
            Utils.addWhitespaceItems(results, 9);
        }
        return results;
    }

    /**
     * Static method that takes in the kilobytes per second value as a {@link String} and converts
     * it to bits per second.
     *
     * @param kbyps             The kilobytes per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent bits per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toBps(@NonNull String kbyps, int roundingLength) {
        Timber.tag(TAG + ".toBps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal dts = new BigDecimal(kbyps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("8000"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (dts.compareTo(BigDecimal.ZERO) == 0) {
                dts = BigDecimal.ZERO;
            }
            return dts.stripTrailingZeros().toPlainString();
        } else {
            return "Transfer speed cannot be below zero";
        }
    }

    /**
     * Static method that takes in the kilobytes per second value as a {@link String} and converts
     * it to bytes per second.
     *
     * @param kbyps             The kilobytes per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent bytes per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toByps(@NonNull String kbyps, int roundingLength) {
        Timber.tag(TAG + ".toByps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal dts = new BigDecimal(kbyps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("1000"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (dts.compareTo(BigDecimal.ZERO) == 0) {
                dts = BigDecimal.ZERO;
            }
            return dts.stripTrailingZeros().toPlainString();
        } else {
            return "Transfer speed cannot be below zero";
        }
    }

    /**
     * Static method that takes in the kilobytes per second value as a {@link String} and converts
     * it to kilobits per second.
     *
     * @param kbyps             The kilobytes per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kilobits per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toKbps(@NonNull String kbyps, int roundingLength) {
        Timber.tag(TAG + ".toKbps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal dts = new BigDecimal(kbyps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("8"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (dts.compareTo(BigDecimal.ZERO) == 0) {
                dts = BigDecimal.ZERO;
            }
            return dts.stripTrailingZeros().toPlainString();
        } else {
            return "Transfer speed cannot be below zero";
        }
    }

    /**
     * Static method that takes in the kilobytes per second value as a {@link String} and converts
     * it to megabits per second.
     *
     * @param kbyps             The kilobytes per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent megabits per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMbps(@NonNull String kbyps, int roundingLength) {
        Timber.tag(TAG + ".toMbps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal dts = new BigDecimal(kbyps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("0.008"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (dts.compareTo(BigDecimal.ZERO) == 0) {
                dts = BigDecimal.ZERO;
            }
            return dts.stripTrailingZeros().toPlainString();
        } else {
            return "Transfer speed cannot be below zero";
        }
    }

    /**
     * Static method that takes in the kilobytes per second value as a {@link String} and converts
     * it to megabytes per second.
     *
     * @param kbyps             The kilobytes per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent megabytes per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMByps(@NonNull String kbyps, int roundingLength) {
        Timber.tag(TAG + ".toMByps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal dts = new BigDecimal(kbyps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("0.001"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (dts.compareTo(BigDecimal.ZERO) == 0) {
                dts = BigDecimal.ZERO;
            }
            return dts.stripTrailingZeros().toPlainString();
        } else {
            return "Transfer speed cannot be below zero";
        }
    }

    /**
     * Static method that takes in the kilobytes per second value as a {@link String} and converts
     * it to gigabits per second.
     *
     * @param kbyps             The kilobytes per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent gigabits per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toGbps(@NonNull String kbyps, int roundingLength) {
        Timber.tag(TAG + ".toGbps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal dts = new BigDecimal(kbyps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("0.000008"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (dts.compareTo(BigDecimal.ZERO) == 0) {
                dts = BigDecimal.ZERO;
            }
            return dts.stripTrailingZeros().toPlainString();
        } else {
            return "Transfer speed cannot be below zero";
        }
    }

    /**
     * Static method that takes in the kilobytes per second value as a {@link String} and converts
     * it to gigabytes per second.
     *
     * @param kbyps             The kilobytes per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent gigabytes per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toGByps(@NonNull String kbyps, int roundingLength) {
        Timber.tag(TAG + ".toGByps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal dts = new BigDecimal(kbyps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("0.000001"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (dts.compareTo(BigDecimal.ZERO) == 0) {
                dts = BigDecimal.ZERO;
            }
            return dts.stripTrailingZeros().toPlainString();
        } else {
            return "Transfer speed cannot be below zero";
        }
    }

    /**
     * Static method that takes in the kilobytes per second value as a {@link String} and converts
     * it to terabits per second.
     *
     * @param kbyps             The kilobytes per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent terabits per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toTbps(@NonNull String kbyps, int roundingLength) {
        Timber.tag(TAG + ".toTbps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal dts = new BigDecimal(kbyps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("0.000000008"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (dts.compareTo(BigDecimal.ZERO) == 0) {
                dts = BigDecimal.ZERO;
            }
            return dts.stripTrailingZeros().toPlainString();
        } else {
            return "Transfer speed cannot be below zero";
        }
    }

    /**
     * Static method that takes in the kilobytes per second value as a {@link String} and converts
     * it to terabytes per second.
     *
     * @param kbyps             The kilobytes per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent terabytes per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toTByps(@NonNull String kbyps, int roundingLength) {
        Timber.tag(TAG + ".toTByps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal dts = new BigDecimal(kbyps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("0.000000001"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (dts.compareTo(BigDecimal.ZERO) == 0) {
                dts = BigDecimal.ZERO;
            }
            return dts.stripTrailingZeros().toPlainString();
        } else {
            return "Transfer speed cannot be below zero";
        }
    }
}
