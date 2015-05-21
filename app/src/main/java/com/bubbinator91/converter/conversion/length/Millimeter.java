package com.bubbinator91.converter.conversion.length;

import android.support.annotation.NonNull;

import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import timber.log.Timber;

/**
 * Handles the conversion from millimeters to other units of length
 */
public class Millimeter {
    private static final String TAG = "Millimeter";

    /**
     * Static method that takes in the millimeter value as a {@link String} and converts it to
     * inches, feet, yards, miles, centimeters, meters, and kilometers.
     *
     * @param millimeter        The millimeter value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent inch, foot, yard, mile, centimeter, meter, and kilometer values
     *          stored in an {@link ArrayList}, in that order.
     */
    public static ArrayList<String> toAll(@NonNull String millimeter, int roundingLength) {
        Timber.tag(TAG + ".toAll").i("Entered");
        Timber.tag(TAG + ".toAll").i("millimeter = " + millimeter);
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        ArrayList<String> results = new ArrayList<>();
        if (Utils.isNumeric(millimeter)) {
            try {
                results.add(toInch(millimeter, decimalPlaces));
                results.add(toFoot(millimeter, decimalPlaces));
                results.add(toYard(millimeter, decimalPlaces));
                results.add(toMile(millimeter, decimalPlaces));
                results.add(toCentimeter(millimeter, decimalPlaces));
                results.add(toMeter(millimeter, decimalPlaces));
                results.add(toKilometer(millimeter, decimalPlaces));
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
     * Static method that takes in the millimeter value as a {@link String} and converts it to
     * inches.
     *
     * @param millimeter        The millimeter value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent inch value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toInch(@NonNull String millimeter, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toInch").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(millimeter);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("0.03937007874015748031496062992126"))
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
     * Static method that takes in the millimeter value as a {@link String} and converts it to feet.
     *
     * @param millimeter        The millimeter value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent foot value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toFoot(@NonNull String millimeter, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toFoot").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(millimeter);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("0.00328083989501312335958005249344"))
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
     * Static method that takes in the millimeter value as a {@link String} and converts it to
     * yards.
     *
     * @param millimeter        The millimeter value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent yard value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toYard(@NonNull String millimeter, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toYard").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(millimeter);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("0.00109361329833770778652668416448"))
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
     * Static method that takes in the millimeter value as a {@link String} and converts it to
     * miles.
     *
     * @param millimeter        The millimeter value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent mile value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMile(@NonNull String millimeter, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toMile").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(millimeter);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("0.00328083989501312335958005249344"))
                    .divide(new BigDecimal("5280"), decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            return "Length cannot be below zero";
        }
    }

    /**
     * Static method that takes in the millimeter value as a {@link String} and converts it to
     * centimeters.
     *
     * @param millimeter        The millimeter value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent centimeter value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toCentimeter(@NonNull String millimeter, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toCentimeter").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(millimeter);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.divide(new BigDecimal("10"), decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            return "Length cannot be below zero";
        }
    }

    /**
     * Static method that takes in the millimeter value as a {@link String} and converts it to
     * meters.
     *
     * @param millimeter        The millimeter value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent meter value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toMeter(@NonNull String millimeter, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toMeter").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(millimeter);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.divide(new BigDecimal("1000"), decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            return "Length cannot be below zero";
        }
    }

    /**
     * Static method that takes in the millimeter value as a {@link String} and converts it to
     * kilometers.
     *
     * @param millimeter        The millimeter value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kilometer value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toKilometer(@NonNull String millimeter, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toKilometer").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal length = new BigDecimal(millimeter);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.divide(new BigDecimal("1000000"), decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            return "Length cannot be below zero";
        }
    }
}
