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
 * Handles the conversion from Celsius to other temperatures
 */
public class Celsius {
    private static final String TAG = "Celsius";

    /**
     * Static method that takes in the celsius value as a {@link String} and converts it to both
     * fahrenheit and kelvin.
     *
     * @param celsius           The celsius value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link Tuple}, where the first item is a {@link List} containing the equivalent
     *          fahrenheit and kelvin values (in that order), and the second item is one of the
     *          error codes found in {@link Conversion}.
     */
    public static Tuple<List<String>, Conversion> toAll(@NonNull String celsius, int roundingLength) {
        Timber.tag(TAG + ".toAll").i("Entered");
        Timber.tag(TAG + ".toAll").i("celsius = " + celsius);
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        List<String> results = new LinkedList<>();
        Conversion error = Conversion.ERROR_NONE;
        if (Conversion.isNumeric(celsius)) {
            try {
                results.add(toFahrenheit(celsius, decimalPlaces));
                results.add(toKelvin(celsius, decimalPlaces));
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
        } else if (celsius.equals("-") || celsius.equals(".") || celsius.equals("")) {
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
     * Static method that takes in the celsius value as a {@link String} and converts it to
     * fahrenheit.
     *
     * @param celsius           The celsius value as a {@link String}. Cannot be null.
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
    public static String toFahrenheit(@NonNull String celsius, int roundingLength)
            throws NumberFormatException, ValueBelowZeroException {
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
            throw new ValueBelowZeroException("Number is below absolute zero");
        }
    }

    /**
     * Static method that takes in the celsius value as a {@link String} and converts it to
     * kelvin.
     *
     * @param celsius           The celsius value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kelvin value as a {@link String}.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toKelvin(@NonNull String celsius, int roundingLength)
            throws NumberFormatException, ValueBelowZeroException {
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
            throw new ValueBelowZeroException("Number is below absolute zero");
        }
    }
}
