package com.bubbinator91.converter.conversion.length;

import android.support.annotation.NonNull;

import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import timber.log.Timber;

/**
 * Handles the conversion from yards to other units of length
 */
public class Yard {
    private static final String TAG = "Yard";

    /**
     * Static method that takes in the yard value as a {@link String} and converts it to inches,
     * feet, miles, millimeters, centimeters, meters, and kilometers.
     *
     * @param yard              The yard value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent inch, foot, mile, millimeter, centimeter, meter, and kilometer values
     *          stored in an {@link ArrayList}, in that order.
     */
    public static ArrayList<String> toAll(@NonNull String yard, int roundingLength) {
        Timber.tag(TAG + ".toAll").i("Entered");
        Timber.tag(TAG + ".toAll").i("yard = " + yard);
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        ArrayList<String> results = new ArrayList<>();
        if (Utils.isNumeric(yard)) {
            try {
                results.add(toInch(yard, decimalPlaces));
                results.add(toFoot(yard, decimalPlaces));
                results.add(toMile(yard, decimalPlaces));
                results.add(toMillimeter(yard, decimalPlaces));
                results.add(toCentimeter(yard, decimalPlaces));
                results.add(toMeter(yard, decimalPlaces));
                results.add(toKilometer(yard, decimalPlaces));
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
     * Static method that takes in the yard value as a {@link String} and converts it to inches.
     *
     * @param yard              The yard value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent inch value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toInch(@NonNull String yard, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toInch").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(yard);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("36"))
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
     * Static method that takes in the yard value as a {@link String} and converts it to feet.
     *
     * @param yard              The yard value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent foot value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toFoot(@NonNull String yard, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toFoot").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(yard);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("3"))
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
     * Static method that takes in the yard value as a {@link String} and converts it to miles.
     *
     * @param yard              The yard value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent mile value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMile(@NonNull String yard, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toMile").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(yard);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.divide(new BigDecimal("1760"), decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            return "Length cannot be below zero";
        }
    }

    /**
     * Static method that takes in the yard value as a {@link String} and converts it to
     * millimeters.
     *
     * @param yard              The yard value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent millimeter value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMillimeter(@NonNull String yard, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toMillimeter").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(yard);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("914.4"))
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
     * Static method that takes in the yard value as a {@link String} and converts it to
     * centimeters.
     *
     * @param yard              The yard value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent centimeter value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toCentimeter(@NonNull String yard, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toCentimeter").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(yard);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("914.4"))
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
     * Static method that takes in the yard value as a {@link String} and converts it to meters.
     *
     * @param yard              The yard value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent meter value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMeter(@NonNull String yard, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toMeter").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(yard);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("914.4"))
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
     * Static method that takes in the yard value as a {@link String} and converts it to kilometers.
     *
     * @param yard              The yard value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kilometer value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toKilometer(@NonNull String yard, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toKilometer").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(yard);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("914.4"))
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
