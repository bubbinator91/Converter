package com.bubbinator91.converter.conversion.length;

import android.support.annotation.NonNull;

import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import timber.log.Timber;

/**
 * Handles the conversion from miles to other units of length
 */
public class Mile {
    private static final String TAG = "Mile";

    /**
     * Static method that takes in the mile value as a {@link String} and converts it to inches,
     * feet, yards, millimeters, centimeters, meters, and kilometers.
     *
     * @param mile              The mile value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent inch, foot, yard, millimeter, centimeter, meter, and kilometer values
     *          stored in an {@link ArrayList}, in that order.
     */
    public static ArrayList<String> toAll(@NonNull String mile, int roundingLength) {
        Timber.tag(TAG + ".toAll").i("Entered");
        Timber.tag(TAG + ".toAll").i("mile = " + mile);
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        ArrayList<String> results = new ArrayList<>();
        if (Utils.isNumeric(mile)) {
            try {
                results.add(toInch(mile, decimalPlaces));
                results.add(toFoot(mile, decimalPlaces));
                results.add(toYard(mile, decimalPlaces));
                results.add(toMillimeter(mile, decimalPlaces));
                results.add(toCentimeter(mile, decimalPlaces));
                results.add(toMeter(mile, decimalPlaces));
                results.add(toKilometer(mile, decimalPlaces));
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
     * Static method that takes in the mile value as a {@link String} and converts it to inches.
     *
     * @param mile              The mile value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent inch value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toInch(@NonNull String mile, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toInch").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(mile);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("63360"))
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
     * Static method that takes in the mile value as a {@link String} and converts it to feet.
     *
     * @param mile              The mile value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent foot value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toFoot(@NonNull String mile, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toFoot").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(mile);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("5280"))
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
     * Static method that takes in the mile value as a {@link String} and converts it to yards.
     *
     * @param mile              The mile value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent yard value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toYard(@NonNull String mile, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toYard").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(mile);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("1760"))
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
     * Static method that takes in the mile value as a {@link String} and converts it to
     * millimeters.
     *
     * @param mile              The mile value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent millimeter value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMillimeter(@NonNull String mile, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toMillimeter").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(mile);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("1609344"))
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
     * Static method that takes in the mile value as a {@link String} and converts it to
     * centimeters.
     *
     * @param mile              The mile value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent centimeter value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toCentimeter(@NonNull String mile, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toCentimeter").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(mile);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("1609344"))
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
     * Static method that takes in the mile value as a {@link String} and converts it to meters.
     *
     * @param mile              The mile value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent meter value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMeter(@NonNull String mile, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toMeter").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(mile);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("1609344"))
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
     * Static method that takes in the mile value as a {@link String} and converts it to kilometers.
     *
     * @param mile              The mile value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kilometer value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toKilometer(@NonNull String mile, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toKilometer").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(mile);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("1609344"))
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
