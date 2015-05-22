package com.bubbinator91.converter.conversion.datatransferspeed;

import android.support.annotation.NonNull;

import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import timber.log.Timber;

/**
 * Handles the conversion from terabits per second to other units of length
 */
public class Tbps {
    private static final String TAG = "Tbps";

    /**
     * Static method that takes in the terabits per second value as a {@link String} and converts it
     * to bits per second, bytes per second, kilobits per second, kilobytes per second, megabits
     * per second, megabytes per second, gigabits per second, gigabytes per second, and terabytes
     * per second.
     *
     * @param tbps              The terabits per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent bits per second, bytes per second, kilobits per second, kilobytes per
     *          second, megabits per second, megabytes per second, gigabits per second, gigabytes
     *          per second, and terabytes per second values stored in an {@link ArrayList}, in that
     *          order.
     */
    public static ArrayList<String> toAll(@NonNull String tbps, int roundingLength) {
        Timber.tag(TAG + ".toAll").i("Entered");
        Timber.tag(TAG + ".toAll").i("tbps = " + tbps);
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        ArrayList<String> results = new ArrayList<>();
        if (Utils.isNumeric(tbps)) {
            try {
                results.add(toBps(tbps, decimalPlaces));
                results.add(toByps(tbps, decimalPlaces));
                results.add(toKbps(tbps, decimalPlaces));
                results.add(toKByps(tbps, decimalPlaces));
                results.add(toMbps(tbps, decimalPlaces));
                results.add(toMByps(tbps, decimalPlaces));
                results.add(toGbps(tbps, decimalPlaces));
                results.add(toGByps(tbps, decimalPlaces));
                results.add(toTByps(tbps, decimalPlaces));
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
     * Static method that takes in the terabits per second value as a {@link String} and converts it
     * to bits per second.
     *
     * @param tbps              The terabits per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent bits per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toBps(@NonNull String tbps, int roundingLength) {
        Timber.tag(TAG + ".toBps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal dts = new BigDecimal(tbps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("1000000000000"))
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
     * Static method that takes in the terabits per second value as a {@link String} and converts it
     * to bytes per second.
     *
     * @param tbps              The terabits per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent bytes per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toByps(@NonNull String tbps, int roundingLength) {
        Timber.tag(TAG + ".toByps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal dts = new BigDecimal(tbps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("125000000000"))
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
     * Static method that takes in the terabits per second value as a {@link String} and converts it
     * to kilobits per second.
     *
     * @param tbps              The terabits per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kilobits per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toKbps(@NonNull String tbps, int roundingLength) {
        Timber.tag(TAG + ".toKbps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal dts = new BigDecimal(tbps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("1000000000"))
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
     * Static method that takes in the terabits per second value as a {@link String} and converts it
     * to kilobytes per second.
     *
     * @param tbps              The terabits per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kilobytes per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toKByps(@NonNull String tbps, int roundingLength) {
        Timber.tag(TAG + ".toKByps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal dts = new BigDecimal(tbps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("125000000"))
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
     * Static method that takes in the terabits per second value as a {@link String} and converts it
     * to megabits per second.
     *
     * @param tbps              The terabits per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent megabits per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMbps(@NonNull String tbps, int roundingLength) {
        Timber.tag(TAG + ".toMbps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal dts = new BigDecimal(tbps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("1000000"))
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
     * Static method that takes in the terabits per second value as a {@link String} and converts it
     * to megabytes per second.
     *
     * @param tbps              The terabits per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent megabytes per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMByps(@NonNull String tbps, int roundingLength) {
        Timber.tag(TAG + ".toMByps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal dts = new BigDecimal(tbps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("125000"))
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
     * Static method that takes in the terabits per second value as a {@link String} and converts it
     * to gigabits per second.
     *
     * @param tbps              The terabits per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent gigabits per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toGbps(@NonNull String tbps, int roundingLength) {
        Timber.tag(TAG + ".toGbps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal dts = new BigDecimal(tbps);
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
     * Static method that takes in the terabits per second value as a {@link String} and converts it
     * to gigabytes per second.
     *
     * @param tbps              The terabits per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent gigabytes per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toGByps(@NonNull String tbps, int roundingLength) {
        Timber.tag(TAG + ".toGByps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal dts = new BigDecimal(tbps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("125"))
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
     * Static method that takes in the terabits per second value as a {@link String} and converts it
     * to terabytes per second.
     *
     * @param tbps              The terabits per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent terabytes per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toTByps(@NonNull String tbps, int roundingLength) {
        Timber.tag(TAG + ".toTByps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal dts = new BigDecimal(tbps);
        if (dts.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            dts = dts.multiply(new BigDecimal("0.125"))
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
