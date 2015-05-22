package com.bubbinator91.converter.conversion.speed;

import android.support.annotation.NonNull;

import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import timber.log.Timber;

/**
 * Handles the conversion from kilometers per hour to other units of speed
 */
public class Kph {
    private static final String TAG = "Kph";

    /**
     * Static method that takes in the kilometers per hour value as a {@link String} and converts it
     * to feet per second, knots, meters per second, and miles per hour.
     *
     * @param kph               The kph value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent feet per second, knots, meters per second, and miles per hour values
     *          stored in an {@link ArrayList}, in that order.
     */
    public static ArrayList<String> toAll(@NonNull String kph, int roundingLength) {
        Timber.tag(TAG + ".toAll").i("Entered");
        Timber.tag(TAG + ".toAll").i("kph = " + kph);
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        ArrayList<String> results = new ArrayList<>();
        if (Utils.isNumeric(kph)) {
            try {
                results.add(toFps(kph, decimalPlaces));
                results.add(toKnot(kph, decimalPlaces));
                results.add(toMps(kph, decimalPlaces));
                results.add(toMph(kph, decimalPlaces));
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
     * Static method that takes in the kilometers per hour value as a {@link String} and converts it
     * to feet per second.
     *
     * @param kph               The kilometers per hour value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent feet per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toFps(@NonNull String kph, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toFps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal speed = new BigDecimal(kph);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("0.9113444152814232"))
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
     * Static method that takes in the kilometers per hour value as a {@link String} and converts it
     * to knots.
     *
     * @param kph               The kilometers per hour value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent knots value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toKnot(@NonNull String kph, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toKnot").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal speed = new BigDecimal(kph);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("0.5399568034557235"))
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
     * Static method that takes in the kilometers per hour value as a {@link String} and converts it
     * to meters per second.
     *
     * @param kph               The kilometers per hour value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent meters per second value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMps(@NonNull String kph, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toMps").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal speed = new BigDecimal(kph);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.divide(new BigDecimal("3.6"), decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (speed.compareTo(BigDecimal.ZERO) == 0) {
                speed = BigDecimal.ZERO;
            }
            return speed.stripTrailingZeros().toPlainString();
        } else {
            return "Speed cannot be below zero";
        }
    }

    /**
     * Static method that takes in the kilometers per hour value as a {@link String} and converts it
     * to miles per hour.
     *
     * @param kph               The kilometers per hour value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent miles per hour value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMph(@NonNull String kph, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toMph").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal speed = new BigDecimal(kph);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("0.621371192237334"))
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
