package com.bubbinator91.converter.conversion.temperature;

import android.support.annotation.NonNull;

import com.bubbinator91.converter.conversion.Conversion;
import com.bubbinator91.converter.conversion.util.Tuple;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

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
     * @param kelvin            The kelvin value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link Tuple}, where the first item is a {@link List} containing the equivalent
     *          celsius and fahrenheit values (in that order), and the second item is one of the
     *          error codes found in {@link Conversion}.
     */
    public static Tuple<List<String>, Conversion> toAll(@NonNull String kelvin, int roundingLength) {
        Timber.tag(TAG + ".toAll").i("Entered");
        Timber.tag(TAG + ".toAll").i("kelvin = " + kelvin);
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        List<String> results = new LinkedList<>();
        Conversion error = Conversion.ERROR_NONE;
        if (Conversion.isNumeric(kelvin)) {
            try {
                results.add(toCelsius(kelvin, decimalPlaces));
                results.add(toFahrenheit(kelvin, decimalPlaces));
            } catch (NumberFormatException e) {
                Timber.tag(TAG + ".toAll").e(e.getMessage());
                results.clear();
                Conversion.addWhitespaceItems(results, 2);
                error = Conversion.ERROR_INPUT_NOT_NUMERIC;
            } catch (ValueBelowZeroException e) {
                Timber.tag(TAG + ".toAll").e(e.getMessage());
                results.clear();
                Conversion.addWhitespaceItems(results, 2);
                error = Conversion.ERROR_BELOW_ABSOLUTE_ZERO;
            }
        } else if (kelvin.equals("-") || kelvin.equals(".") || kelvin.equals("")) {
            Timber.tag(TAG + ".toAll").i("Input was a - or . or whitespace");
            Conversion.addWhitespaceItems(results, 2);
        } else {
            Timber.tag(TAG + ".toAll").i("Input was not numeric");
            results.clear();
            Conversion.addWhitespaceItems(results, 2);
            error = Conversion.ERROR_INPUT_NOT_NUMERIC;
        }
        return new Tuple<>(results, error);
    }

    /**
     * Static method that takes in the kelvin value as a {@link String} and converts it to
     * celsius.
     *
     * @param kelvin            The kelvin value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent celsius value as a {@link String}.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toCelsius(@NonNull String kelvin, int roundingLength)
            throws NumberFormatException, ValueBelowZeroException {
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
            throw new ValueBelowZeroException("Number is below absolute zero");
        }
    }

    /**
     * Static method that takes in the kelvin value as a {@link String} and converts it to
     * fahrenheit.
     *
     * @param kelvin            The kelvin value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent fahrenheit value as a {@link String}.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toFahrenheit(@NonNull String kelvin, int roundingLength)
            throws NumberFormatException, ValueBelowZeroException {
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
            throw new ValueBelowZeroException("Number is below absolute zero");
        }
    }
}
