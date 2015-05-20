package com.bubbinator91.converter.conversion.temperature;

import android.support.annotation.NonNull;

import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import timber.log.Timber;

/**
 * Handles the conversion from Kelvin to other temperatures
 */
public class Kelvin {
    private static final String TAG = "Kelvin";

    /**
     * Static method that takes in the kelvin value as a {@link String} and converts it to both
     * celsius and fahrenheit.
     *
     * @param kelvin            The kelvin value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent celsius and fahrenheit values stored in an {@link ArrayList}, in
     *          that order.
     */
    public static ArrayList<String> toAll(@NonNull String kelvin, int roundingLength) {
        Timber.tag(TAG + ".toAll").i("Entered");
        Timber.tag(TAG + ".toAll").i("kelvin = " + kelvin);
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        ArrayList<String> results = new ArrayList<>();
        if (Utils.isNumeric(kelvin)) {
            try {
                results.add(toCelsius(kelvin, decimalPlaces));
                results.add(toFahrenheit(kelvin, decimalPlaces));
            } catch (NumberFormatException e) {
                Timber.tag(TAG + ".toAll").e(e.getMessage());
                Utils.addWhitespaceItems(results, 2);
            }
        } else {
            Timber.tag(TAG + ".toAll").i("Input was not numeric");
            results.clear();
            Utils.addWhitespaceItems(results, 2);
        }
        return results;
    }

    /**
     * Static method that takes in the kelvin value as a {@link String} and converts it to
     * celsius.
     *
     * @param kelvin            The kelvin value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent celsius value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toCelsius(@NonNull String kelvin, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toCelsius").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal temperature = new BigDecimal(kelvin);
        if (temperature.compareTo(BigDecimal.ZERO) > 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            BigDecimal celsius = temperature.subtract(new BigDecimal("273.15"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (celsius.compareTo(BigDecimal.ZERO) == 0) {
                celsius = BigDecimal.ZERO;
            }
            return celsius.stripTrailingZeros().toPlainString();
        } else if (temperature.compareTo(BigDecimal.ZERO) == 0) {
            return "-273.15";
        } else {
            return "That value is below absolute zero";
        }
    }

    /**
     * Static method that takes in the kelvin value as a {@link String} and converts it to
     * fahrenheit.
     *
     * @param kelvin            The kelvin value as a {@link String}. Cannot be null;
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent fahrenheit value as a {@link String}.
     *
     * @throws  NumberFormatException
     */
    public static String toFahrenheit(@NonNull String kelvin, int roundingLength)
            throws NumberFormatException {
        Timber.tag(TAG + ".toFahrenheit").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal temperature = new BigDecimal(kelvin);
        if (temperature.compareTo(BigDecimal.ZERO) > 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            BigDecimal fahrenheit = temperature.subtract(new BigDecimal("273.15"))
                    .multiply(new BigDecimal("1.8"))
                    .add(new BigDecimal("32"))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (fahrenheit.compareTo(BigDecimal.ZERO) == 0) {
                fahrenheit = BigDecimal.ZERO;
            }
            return fahrenheit.stripTrailingZeros().toPlainString();
        } else if (temperature.compareTo(BigDecimal.ZERO) == 0) {
            return "-459.67";
        } else {
            return "That value is below absolute zero";
        }
    }
}
