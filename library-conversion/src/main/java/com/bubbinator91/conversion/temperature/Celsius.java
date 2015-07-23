package com.bubbinator91.conversion.temperature;

import android.util.Log;

import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.conversion.util.Tuple;
import com.bubbinator91.conversion.util.Unit;
import com.bubbinator91.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles the conversion from Celsius to other temperatures
 */
public class Celsius extends Unit {
    private static final String TAG = Celsius.class.getSimpleName();

    // region Public methods

    /**
     * Takes in the celsius value as a {@link String} and converts it to both fahrenheit and kelvin.
     *
     * @param celsius           The celsius value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link Tuple}, where the first item is a {@link List} containing the equivalent
     *          fahrenheit and kelvin values (in that order; they will be empty {@link String}s if
     *          there is an error), and the second item is one of the error codes found in
     *          {@link ConversionErrorCodes}, or null if the <code>celsius</code> parameter is null.
     */
    public static Tuple<List<String>, ConversionErrorCodes> toAll(String celsius, int decimalPlaces) {
        if (celsius == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        List<String> results = new LinkedList<>();
        ConversionErrorCodes error = ConversionErrorCodes.ERROR_NONE;

        if (isNumeric(celsius)) {
            try {
                results.add(toFahrenheit(celsius, roundingLength));
                results.add(toKelvin(celsius, roundingLength));
            } catch (NumberFormatException e) {
                Log.e(TAG + ".toAll", e.getLocalizedMessage());
                results.clear();
                addEmptyItems(results, 2);
                error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
            } catch (ValueBelowZeroException e) {
                Log.e(TAG + ".toAll", e.getLocalizedMessage());
                results.clear();
                addEmptyItems(results, 2);
                error = ConversionErrorCodes.ERROR_BELOW_ZERO;
            }
        } else if (celsius.equals("-") || celsius.equals(".")
                || celsius.equals("") || celsius.equals("-.")) {
            results.clear();
            addEmptyItems(results, 2);
        } else {
            results.clear();
            addEmptyItems(results, 2);
            error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
        }

        return new Tuple<>(results, error);
    }

    /**
     * Takes in the celsius value as a {@link String} and converts it to fahrenheit.
     *
     * @param celsius           The celsius value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent fahrenheit value as a {@link String}, or null if the
     *          <code>celsius</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toFahrenheit(String celsius, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (celsius == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal temperature = new BigDecimal(celsius);
        if (temperature.compareTo(new BigDecimal("-273.15")) > 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            BigDecimal fahrenheit = temperature.multiply(new BigDecimal("1.8"))
                    .add(new BigDecimal("32"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (fahrenheit.compareTo(BigDecimal.ZERO) == 0) {
                fahrenheit = BigDecimal.ZERO;
            }
            return fahrenheit.stripTrailingZeros().toPlainString();
        } else if (temperature.compareTo(new BigDecimal("-273.15")) == 0) {
            return "-459.67";
        } else {
            throw new ValueBelowZeroException("Temperature cannot be below absolute zero");
        }
    }

    /**
     * Takes in the celsius value as a {@link String} and converts it to kelvin.
     *
     * @param celsius           The celsius value as a {@link String}. Cannot be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kelvin value as a {@link String}, or null if the <code>celsius</code>
     *          parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toKelvin(String celsius, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (celsius == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal temperature = new BigDecimal(celsius);
        if (temperature.compareTo(new BigDecimal("-273.15")) > 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            BigDecimal kelvin = temperature.add(new BigDecimal("273.15"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (kelvin.compareTo(BigDecimal.ZERO) == 0) {
                kelvin = BigDecimal.ZERO;
            }
            return kelvin.stripTrailingZeros().toPlainString();
        } else if (temperature.compareTo(new BigDecimal("-273.15")) == 0) {
            return "0";
        } else {
            throw new ValueBelowZeroException("Temperature cannot be below absolute zero");
        }
    }

    // endregion
}
