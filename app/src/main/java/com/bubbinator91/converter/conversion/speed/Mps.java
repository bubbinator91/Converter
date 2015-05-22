package com.bubbinator91.converter.conversion.speed;

import android.support.annotation.NonNull;

import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import timber.log.Timber;

/**
 * Handles the conversion from meters per second to other units of speed
 */
public class Mps {
    private static final String TAG = "Mps";

    /**
     * Static method that takes in the meters per second value as a {@link String} and converts it
     * to feet per second, knots, kilometers per hour, and miles per hour.
     *
     * @param mps               The meters per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent feet per second, knots, kilometers per hour, and miles per hour
     *          values stored in an {@link ArrayList}, in that order.
     */
    public static ArrayList<String> toAll(@NonNull String mps, int roundingLength) {
        Timber.tag(TAG + ".toAll").i("Entered");
        Timber.tag(TAG + ".toAll").i("mps = " + mps);
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        ArrayList<String> results = new ArrayList<>();
        if (Utils.isNumeric(mps)) {
            try {
                results.add(toFps(mps, decimalPlaces));
                results.add(toKnot(mps, decimalPlaces));
                results.add(toKph(mps, decimalPlaces));
                results.add(toMph(mps, decimalPlaces));
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
     * Static method that takes in the meters per second value as a {@link String} and converts it
     * to feet per second.
     *
     * @param mps               The meters per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent feet per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toFps(@NonNull String mps, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toFps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal speed = new BigDecimal(mps);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("3.2808398950131235"))
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
     * Static method that takes in the meters per second value as a {@link String} and converts it
     * to knots.
     *
     * @param mps               The meters per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent knots value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toKnot(@NonNull String mps, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toKnot").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal speed = new BigDecimal(mps);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("1.9438444924406046"))
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
     * Static method that takes in the meters per second value as a {@link String} and converts it
     * to kilometers per hour.
     *
     * @param mps               The meters per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kilometers per hour value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toKph(@NonNull String mps, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toKph").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal speed = new BigDecimal(mps);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("3.6"))
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
     * Static method that takes in the meters per second value as a {@link String} and converts it
     * to miles per hour.
     *
     * @param mps               The meters per second value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent miles per hour value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMph(@NonNull String mps, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toMph").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal speed = new BigDecimal(mps);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("2.2369362920544025"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (speed.compareTo(BigDecimal.ZERO) == 0) {
                speed = BigDecimal.ZERO;
            }
            return speed.stripTrailingZeros().toPlainString();
        } else {
            return "Speed cannot be below zero";
        }
    }
}
