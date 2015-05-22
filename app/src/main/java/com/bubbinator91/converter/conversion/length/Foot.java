package com.bubbinator91.converter.conversion.length;

import android.support.annotation.NonNull;

import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import timber.log.Timber;

/**
 * Handles the conversion from feet to other units of length
 */
public class Foot {
    private static final String TAG = "Foot";

    /**
     * Static method that takes in the foot value as a {@link String} and converts it to inches,
     * yards, miles, millimeters, centimeters, meters, and kilometers.
     *
     * @param foot              The foot value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent inch, yard, mile, millimeter, centimeter, meter, and kilometer values
     *          stored in an {@link ArrayList}, in that order.
     */
    public static ArrayList<String> toAll(@NonNull String foot, int roundingLength) {
        Timber.tag(TAG + ".toAll").i("Entered");
        Timber.tag(TAG + ".toAll").i("foot = " + foot);
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        ArrayList<String> results = new ArrayList<>();
        if (Utils.isNumeric(foot)) {
            try {
                results.add(toInch(foot, decimalPlaces));
                results.add(toYard(foot, decimalPlaces));
                results.add(toMile(foot, decimalPlaces));
                results.add(toMillimeter(foot, decimalPlaces));
                results.add(toCentimeter(foot, decimalPlaces));
                results.add(toMeter(foot, decimalPlaces));
                results.add(toKilometer(foot, decimalPlaces));
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
     * Static method that takes in the foot value as a {@link String} and converts it to inches.
     *
     * @param foot              The foot value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent inch value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toInch(@NonNull String foot, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toInch").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(foot);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("12"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            return "Length cannot be below zero";
        }
    }

    /**
     * Static method that takes in the foot value as a {@link String} and converts it to yards.
     *
     * @param foot              The foot value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent yard value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toYard(@NonNull String foot, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toYard").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(foot);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.divide(new BigDecimal("3"), decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            return "Length cannot be below zero";
        }
    }

    /**
     * Static method that takes in the foot value as a {@link String} and converts it to miles.
     *
     * @param foot              The foot value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent mile value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMile(@NonNull String foot, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toMile").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(foot);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.divide(new BigDecimal("5280"), decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            return "Length cannot be below zero";
        }
    }

    /**
     * Static method that takes in the foot value as a {@link String} and converts it to
     * millimeters.
     *
     * @param foot              The foot value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent millimeter value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMillimeter(@NonNull String foot, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toMillimeter").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(foot);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("304.8"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            return "Length cannot be below zero";
        }
    }

    /**
     * Static method that takes in the foot value as a {@link String} and converts it to
     * centimeters.
     *
     * @param foot              The foot value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent centimeter value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toCentimeter(@NonNull String foot, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toCentimeter").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(foot);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("304.8"))
                    .divide(new BigDecimal("10"), decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            return "Length cannot be below zero";
        }
    }

    /**
     * Static method that takes in the foot value as a {@link String} and converts it to meters.
     *
     * @param foot              The foot value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent meter value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMeter(@NonNull String foot, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toMeter").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(foot);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("304.8"))
                    .divide(new BigDecimal("1000"), decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            return "Length cannot be below zero";
        }
    }

    /**
     * Static method that takes in the foot value as a {@link String} and converts it to kilometers.
     *
     * @param foot              The foot value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kilometer value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toKilometer(@NonNull String foot, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toKilometer").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(foot);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("304.8"))
                    .divide(new BigDecimal("1000000"), decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            return "Length cannot be below zero";
        }
    }
}
