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
 * Handles the conversion from Fahrenheit to other temperatures
 */
public class Fahrenheit extends Unit {
    private static final String TAG = Fahrenheit.class.getSimpleName();

    // Implemented as a singleton rather than just static methods so that the class could be
    // extended from a base class. Implemented with the singleton "Initialization-on-demand" pattern
    // so as to not take up unnecessary memory.

    // region Singleton things

    private Fahrenheit() {}

    private static class FahrenheitHolder {
        private static final Fahrenheit INSTANCE = new Fahrenheit();
    }

    private static Fahrenheit getInstance() { return FahrenheitHolder.INSTANCE; }

    // endregion

    // region Public methods

    /**
     * Takes in the fahrenheit value as a {@link String} and converts it to both celsius and kelvin.
     *
     * @param fahrenheit        The fahrenheit value as a {@link String}. Cannot be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link Tuple}, where the first item is a {@link List} containing the equivalent
     *          celsius and kelvin values (in that order), and the second item is one of the
     *          error codes found in {@link ConversionErrorCodes}, or null if the <code>fahrenheit</code>
     *          parameter is null.
     */
    public static Tuple<List<String>, ConversionErrorCodes> toAll(String fahrenheit, int decimalPlaces) {
        return getInstance().internal_toAll(fahrenheit, decimalPlaces);
    }

    /**
     * Takes in the fahrenheit value as a {@link String} and converts it to celsius.
     *
     * @param fahrenheit        The fahrenheit value as a {@link String}. Cannot be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent celsius value as a {@link String}, or null if the
     *          <code>fahrenheit</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toCelsius(String fahrenheit, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        return getInstance().internal_toCelsius(fahrenheit, decimalPlaces);
    }

    /**
     * Takes in the fahrenheit value as a {@link String} and converts it to kelvin.
     *
     * @param fahrenheit        The fahrenheit value as a {@link String}. Cannot be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kelvin value as a {@link String}, or null if the
     *          <code>fahrenheit</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public static String toKelvin(String fahrenheit, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        return getInstance().internal_toKelvin(fahrenheit, decimalPlaces);
    }

    // endregion

    // region Private methods

    /**
     * Takes in the fahrenheit value as a {@link String} and converts it to both celsius and kelvin.
     *
     * @param fahrenheit        The fahrenheit value as a {@link String}. Cannot be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link Tuple}, where the first item is a {@link List} containing the equivalent
     *          celsius and kelvin values (in that order), and the second item is one of the
     *          error codes found in {@link ConversionErrorCodes}, or null if the <code>fahrenheit</code>
     *          parameter is null.
     */
    private Tuple<List<String>, ConversionErrorCodes> internal_toAll(String fahrenheit, int decimalPlaces) {
        if (fahrenheit == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        List<String> results = new LinkedList<>();
        ConversionErrorCodes error = ConversionErrorCodes.ERROR_NONE;

        if (isNumeric(fahrenheit)) {
            try {
                results.add(internal_toCelsius(fahrenheit, roundingLength));
                results.add(internal_toKelvin(fahrenheit, roundingLength));
            } catch (NumberFormatException e) {
                Log.e(TAG + ".internal_toAll", e.getLocalizedMessage());
                results.clear();
                addEmptyItems(results, 2);
                error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
            } catch (ValueBelowZeroException e) {
                Log.e(TAG + ".internal_toAll", e.getLocalizedMessage());
                results.clear();
                addEmptyItems(results, 2);
                error = ConversionErrorCodes.ERROR_BELOW_ABSOLUTE_ZERO;
            }
        } else if (fahrenheit.equals("-") || fahrenheit.equals(".") || fahrenheit.equals("")) {
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
     * Takes in the fahrenheit value as a {@link String} and converts it to celsius.
     *
     * @param fahrenheit        The fahrenheit value as a {@link String}. Cannot be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent celsius value as a {@link String}, or null if the
     *          <code>fahrenheit</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    private String internal_toCelsius(String fahrenheit, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (fahrenheit == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal temperature = new BigDecimal(fahrenheit);
        if (temperature.compareTo(new BigDecimal("-459.67")) > 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            BigDecimal celsius = temperature.subtract(new BigDecimal("32"))
                    .multiply((new BigDecimal("5"))
                            .divide(new BigDecimal("9"), 100, BigDecimal.ROUND_HALF_UP))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (celsius.compareTo(BigDecimal.ZERO) == 0) {
                celsius = BigDecimal.ZERO;
            }
            return celsius.stripTrailingZeros().toPlainString();
        } else if (temperature.compareTo(new BigDecimal("-459.67")) == 0) {
            return "-273.15";
        } else {
            throw new ValueBelowZeroException("Temperature cannot be below absolute zero");
        }
    }

    /**
     * Takes in the fahrenheit value as a {@link String} and converts it to kelvin.
     *
     * @param fahrenheit        The fahrenheit value as a {@link String}. Cannot be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kelvin value as a {@link String}, or null if the
     *          <code>fahrenheit</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    private String internal_toKelvin(String fahrenheit, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (fahrenheit == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal temperature = new BigDecimal(fahrenheit);
        if (temperature.compareTo(new BigDecimal("-459.67")) > 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            BigDecimal kelvin = temperature.subtract(new BigDecimal("32"))
                    .multiply(new BigDecimal(5.0 / 9.0))
                    .add(new BigDecimal("273.15"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (kelvin.compareTo(BigDecimal.ZERO) == 0) {
                kelvin = BigDecimal.ZERO;
            }
            return kelvin.stripTrailingZeros().toPlainString();
        } else if (temperature.compareTo(new BigDecimal("-459.67")) == 0) {
            return "0";
        } else {
            throw new ValueBelowZeroException("Temperature cannot be below absolute zero");
        }
    }

    // endregion
}
