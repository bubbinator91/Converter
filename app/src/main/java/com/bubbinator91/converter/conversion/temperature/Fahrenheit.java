package com.bubbinator91.converter.conversion.temperature;

import android.support.annotation.NonNull;

import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import timber.log.Timber;

/**
 * Handles the conversion from Fahrenheit to other temperatures
 */
public class Fahrenheit {
    private static final String TAG = "Fahrenheit";

    /**
     * Static method that takes in the fahrenheit value as a {@link String} and converts it to both
     * celsius and kelvin.
     *
     * @param fahrenheit           The fahrenheit value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent celsius and kelvin values stored in an {@link ArrayList}, in
     *          that order.
     */
    public static ArrayList<String> convert(@NonNull String fahrenheit, int roundingLength) {
        Timber.tag(TAG + ".convert").i("Entered");
        Timber.tag(TAG + ".convert").i("fahrenheit = " + fahrenheit);
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        ArrayList<String> results = new ArrayList<>();
        if (Utils.isNumeric(fahrenheit)) {
            try {
                results.add(toCelsius(fahrenheit, decimalPlaces));
                results.add(toKelvin(fahrenheit, decimalPlaces));
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
     * Static method that takes in the fahrenheit value as a {@link String} and converts it to
     * fahrenheit.
     *
     * @param fahrenheit        The fahrenheit value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent celsius value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toCelsius(@NonNull String fahrenheit, int roundingLength) {
        Timber.tag(TAG + ".toCelsius").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal temperature = new BigDecimal(fahrenheit);
        if (temperature.compareTo(new BigDecimal("-459.67")) > 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            BigDecimal celsius = temperature.subtract(new BigDecimal("32"))
                    .multiply(new BigDecimal(5.0 / 9.0))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (celsius.compareTo(BigDecimal.ZERO) == 0) {
                celsius = BigDecimal.ZERO;
            }
            return celsius.stripTrailingZeros().toPlainString();
        } else if (temperature.compareTo(new BigDecimal("-459.67")) == 0) {
            return "-273.15";
        } else {
            return "That value is below absolute zero";
        }
    }

    /**
     * Static method that takes in the fahrenheit value as a {@link String} and converts it to
     * kelvin.
     *
     * @param fahrenheit        The fahrenheit value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kelvin value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toKelvin(@NonNull String fahrenheit, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toKelvin").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal temperature = new BigDecimal(fahrenheit);
        if (temperature.compareTo(new BigDecimal("-459.67")) > 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            BigDecimal kelvin = temperature.subtract(new BigDecimal("32"))
                    .multiply(new BigDecimal(5.0 / 9.0))
                    .add(new BigDecimal("273.15"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (kelvin.compareTo(BigDecimal.ZERO) == 0) {
                kelvin = BigDecimal.ZERO;
            }
            return kelvin.stripTrailingZeros().toPlainString();
        } else if (temperature.compareTo(new BigDecimal("-459.67")) == 0) {
            return "0";
        } else {
            return "That value is below absolute zero";
        }
    }
}
