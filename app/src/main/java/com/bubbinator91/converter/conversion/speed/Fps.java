package com.bubbinator91.converter.conversion.speed;

import android.support.annotation.NonNull;

import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import timber.log.Timber;

/**
 * Handles the conversion from feet per second to other units of speed
 */
public class Fps {
    private static final String TAG = "Fps";

    /**
     * Static method that takes in the feet per second value as a {@link String} and converts it to
     * knots, kilometers per hour, meters per second, and miles per hour.
     *
     * @param fps               The feet per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent knot, kilometers per hour, meters per second, and miles per hour
     *          values stored in an {@link ArrayList}, in that order.
     */
    public static ArrayList<String> toAll(@NonNull String fps, int roundingLength) {
        Timber.tag(TAG + ".toAll").i("Entered");
        Timber.tag(TAG + ".toAll").i("fps = " + fps);
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        ArrayList<String> results = new ArrayList<>();
        if (Utils.isNumeric(fps)) {
            try {
                results.add(toKnot(fps, decimalPlaces));
                results.add(toKph(fps, decimalPlaces));
                results.add(toMps(fps, decimalPlaces));
                results.add(toMph(fps, decimalPlaces));
            } catch (NumberFormatException e) {
                Timber.tag(TAG + ".toAll").e(e.getMessage());
                results.clear();
                Utils.addWhitespaceItems(results, 4);
            }
        } else {
            Timber.tag(TAG + ".toAll").i("Input was not numeric");
            Utils.addWhitespaceItems(results, 4);
        }
        return results;
    }

    /**
     * Static method that takes in the feet per second value as a {@link String} and converts it to
     * knots.
     *
     * @param fps               The feet per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent knots value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toKnot(@NonNull String fps, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toKnot").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal speed = new BigDecimal(fps);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("0.5924838012958964"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (speed.compareTo(BigDecimal.ZERO) == 0) {
                speed = BigDecimal.ZERO;
            }
            return speed.stripTrailingZeros().toPlainString();
        } else {
            return "Speed cannot be below zero";
        }
    }

    /**
     * Static method that takes in the feet per second value as a {@link String} and converts it to
     * kilometers per hour.
     *
     * @param fps               The feet per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kilometers per hour value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toKph(@NonNull String fps, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toKph").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal speed = new BigDecimal(fps);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("1.09728"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (speed.compareTo(BigDecimal.ZERO) == 0) {
                speed = BigDecimal.ZERO;
            }
            return speed.stripTrailingZeros().toPlainString();
        } else {
            return "Speed cannot be below zero";
        }
    }

    /**
     * Static method that takes in the feet per second value as a {@link String} and converts it to
     * meters per second.
     *
     * @param fps               The feet per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent meters per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMps(@NonNull String fps, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toMps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal speed = new BigDecimal(fps);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("0.3048"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (speed.compareTo(BigDecimal.ZERO) == 0) {
                speed = BigDecimal.ZERO;
            }
            return speed.stripTrailingZeros().toPlainString();
        } else {
            return "Speed cannot be below zero";
        }
    }

    /**
     * Static method that takes in the feet per second value as a {@link String} and converts it to
     * miles per hour.
     *
     * @param fps               The feet per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent miles per hour value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMph(@NonNull String fps, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toMph").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal speed = new BigDecimal(fps);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("3600"))
                    .divide(new BigDecimal("5280"), decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (speed.compareTo(BigDecimal.ZERO) == 0) {
                speed = BigDecimal.ZERO;
            }
            return speed.stripTrailingZeros().toPlainString();
        } else {
            return "Speed cannot be below zero";
        }
    }
}
