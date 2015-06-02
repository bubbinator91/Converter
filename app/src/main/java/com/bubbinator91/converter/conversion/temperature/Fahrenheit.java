package com.bubbinator91.converter.conversion.temperature;

import android.support.annotation.NonNull;

import com.bubbinator91.converter.conversion.Conversion;
import com.bubbinator91.converter.conversion.util.Tuple;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;
import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

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
     * @param fahrenheit           The fahrenheit value as a {@link String}. Cannot be null.
     * @param roundingLength    The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link Tuple}, where the first item is a {@link List} containing the equivalent
     *          celsius and kelvin values (in that order), and the second item is one of the
     *          error codes found in {@link Conversion}.
     */
    public static Tuple<List<String>, Conversion> toAll(@NonNull String fahrenheit, int roundingLength) {
        Timber.tag(TAG + ".toAll").i("Entered");
        Timber.tag(TAG + ".toAll").i("fahrenheit = " + fahrenheit);
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        List<String> results = new LinkedList<>();
        Conversion error = Conversion.ERROR_NONE;
        if (Utils.isNumeric(fahrenheit)) {
            try {
                results.add(toCelsius(fahrenheit, decimalPlaces));
                results.add(toKelvin(fahrenheit, decimalPlaces));
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
        } else if (fahrenheit.equals("-") || fahrenheit.equals(".") || fahrenheit.equals("")) {
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
     * Static method that takes in the fahrenheit value as a {@link String} and converts it to
     * fahrenheit.
     *
     * @param fahrenheit        The fahrenheit value as a {@link String}. Cannot be null.
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
    public static String toCelsius(@NonNull String fahrenheit, int roundingLength)
            throws NumberFormatException, ValueBelowZeroException {
        Timber.tag(TAG + ".toCelsius").i("Entered");
        int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
        BigDecimal temperature = new BigDecimal(fahrenheit);
        if (temperature.compareTo(new BigDecimal("-459.67")) > 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            BigDecimal celsius = temperature.subtract(new BigDecimal("32"))
                    .multiply((new BigDecimal("5"))
                            .divide(new BigDecimal("9"), 100, BigDecimal.ROUND_HALF_UP))
                    .setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
            if (celsius.compareTo(BigDecimal.ZERO) == 0) {
                celsius = BigDecimal.ZERO;
            }
            return celsius.stripTrailingZeros().toPlainString();
        } else if (temperature.compareTo(new BigDecimal("-459.67")) == 0) {
            return "-273.15";
        } else {
            throw new ValueBelowZeroException("Number is below absolute zero");
        }
    }

    /**
     * Static method that takes in the fahrenheit value as a {@link String} and converts it to
     * kelvin.
     *
     * @param fahrenheit        The fahrenheit value as a {@link String}. Cannot be null.
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
    public static String toKelvin(@NonNull String fahrenheit, int roundingLength)
            throws NumberFormatException, ValueBelowZeroException {
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
            throw new ValueBelowZeroException("Number is below absolute zero");
        }
    }
}
