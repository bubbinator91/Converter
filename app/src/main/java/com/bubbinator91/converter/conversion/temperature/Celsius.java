package com.bubbinator91.converter.conversion.temperature;

import android.support.annotation.NonNull;

import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import timber.log.Timber;

/**
 * Handles the conversion from Celsius to other temperatures
 */
public class Celsius {
    private static final String TAG = "Celsius";

    /**
     * Static method that takes in the celsius value as a {@link String} and converts it to both
     * fahrenheit and kelvin.
     *
     * @param celsius           The celsius value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent fahrenheit and kelvin values stored in an {@link ArrayList}, in
     *          that order.
     */
    public static ArrayList<String> convert(@NonNull String celsius, int roundingLength) {
        Timber.tag(TAG + ".convert").i("Entered");
        Timber.tag(TAG + ".convert").i("celsius = " + celsius);
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        ArrayList<String> results = new ArrayList<>();
        if (Utils.isNumeric(celsius)) {
            try {
                results.add(toFahrenheit(celsius, decimalPlaces));
                results.add(toKelvin(celsius, decimalPlaces));
            } catch (NumberFormatException e) {
                Timber.tag(TAG + ".convert").e(e.getMessage());
                Utils.addWhitespaceItems(results, 2);
            }
        } else {
            Timber.tag(TAG + ".convert").i("Input was not numeric");
            Utils.addWhitespaceItems(results, 2);
        }
        return results;
    }

    /**
     * Static method that takes in the celsius value as a {@link String} and converts it to
     * fahrenheit.
     *
     * @param celsius           The celsius value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent fahrenheit value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toFahrenheit(@NonNull String celsius, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toFahrenheit").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal temperature = new BigDecimal(celsius);
        if (temperature.compareTo(new BigDecimal("-273.15")) > 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            BigDecimal fahrenheit = temperature.multiply(new BigDecimal("1.8"))
                    .add(new BigDecimal("32"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (fahrenheit.compareTo(BigDecimal.ZERO) == 0) {
                fahrenheit = BigDecimal.ZERO;
            }
            return fahrenheit.stripTrailingZeros().toPlainString();
        } else if (temperature.compareTo(new BigDecimal("-273.15")) == 0) {
            return "-459.67";
        } else {
            return "That value is below absolute zero";
        }
    }

    /**
     * Static method that takes in the celsius value as a {@link String} and converts it to
     * kelvin.
     *
     * @param celsius           The celsius value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kelvin value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toKelvin(@NonNull String celsius, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toKelvin").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal temperature = new BigDecimal(celsius);
        if (temperature.compareTo(new BigDecimal("-273.15")) > 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            BigDecimal kelvin = temperature.add(new BigDecimal("273.15"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (kelvin.compareTo(BigDecimal.ZERO) == 0) {
                kelvin = BigDecimal.ZERO;
            }
            return kelvin.stripTrailingZeros().toPlainString();
        } else if (temperature.compareTo(new BigDecimal("-273.15")) == 0) {
            return "0";
        } else {
            return "That value is below absolute zero";
        }
    }
}
