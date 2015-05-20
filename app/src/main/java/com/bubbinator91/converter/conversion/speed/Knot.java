package com.bubbinator91.converter.conversion.speed;

import android.support.annotation.NonNull;

import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import timber.log.Timber;

/**
 * Handles the conversion from knots to other units of speed
 */
public class Knot {
    private static final String TAG = "Knot";

    /**
     * Static method that takes in the knot value as a {@link String} and converts it to fps, kph,
     * mps, and mph.
     *
     * @param knot              The knot value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent fps, kph, mps, and mph values stored in an {@link ArrayList}, in
     *          that order.
     */
    public static ArrayList<String> toAll(@NonNull String knot, int roundingLength) {
        Timber.tag(TAG + ".toAll").i("Entered");
        Timber.tag(TAG + ".toAll").i("knot = " + knot);
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        ArrayList<String> results = new ArrayList<>();
        if (Utils.isNumeric(knot)) {
            try {
                results.add(toFps(knot, decimalPlaces));
                results.add(toKph(knot, decimalPlaces));
                results.add(toMps(knot, decimalPlaces));
                results.add(toMph(knot, decimalPlaces));
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
     * Static method that takes in the knot value as a {@link String} and converts it to fps.
     *
     * @param knot              The knot value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent fps value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toFps(@NonNull String knot, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toFps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal speed = new BigDecimal(knot);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("1.6878098571011957"))
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
     * Static method that takes in the knot value as a {@link String} and converts it to kph.
     *
     * @param knot              The knot value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kph value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toKph(@NonNull String knot, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toKph").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal speed = new BigDecimal(knot);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("1.852"))
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
     * Static method that takes in the knot value as a {@link String} and converts it to mps.
     *
     * @param knot              The knot value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent mps value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMps(@NonNull String knot, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toMps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal speed = new BigDecimal(knot);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("0.5144444444444445"))
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
     * Static method that takes in the knot value as a {@link String} and converts it to mph.
     *
     * @param knot              The knot value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent mph value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMph(@NonNull String knot, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toMph").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal speed = new BigDecimal(knot);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("1.1507794480235425"))
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
