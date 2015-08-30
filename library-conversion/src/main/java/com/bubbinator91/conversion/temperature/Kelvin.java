package com.bubbinator91.conversion.temperature;

import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.conversion.util.Tuple;
import com.bubbinator91.conversion.util.Unit;
import com.bubbinator91.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles the conversion from Kelvin to other temperatures
 */
public class Kelvin extends Unit {

    // Prevents class from being instantiated directly
    private Kelvin() {}

    // region Singleton items

    private static class KelvinInstance {
        private static final Kelvin INSTANCE = new Kelvin();
    }

    protected static Kelvin getInstance() {
        return KelvinInstance.INSTANCE;
    }

    // endregion

    // region Public methods

    /**
     * Takes in the kelvin value as a {@link String} and converts it to both celsius and fahrenheit.
     *
     * @param kelvin            The kelvin value as a {@link String}. Cannot be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link Tuple}, where the first item is a {@link List} containing the equivalent
     *          celsius and fahrenheit values (in that order; they will be empty {@link String}s if
     *          there is an error), and the second item is one of the error codes found in
     *          {@link ConversionErrorCodes} as an {@link Integer} object, or null if the
     *          <code>kelvin</code> parameter is null;
     */
    public Tuple<List<String>, Integer> toAll(String kelvin, int decimalPlaces) {
        if (kelvin == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        List<String> results = new LinkedList<>();
        int error = ConversionErrorCodes.ERROR_NONE;

        if (isNumeric(kelvin)) {
            try {
                results.add(toCelsius(kelvin, roundingLength));
                results.add(toFahrenheit(kelvin, roundingLength));
            } catch (NumberFormatException e) {
                results.clear();
                addEmptyItems(results, 2);
                error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
            } catch (ValueBelowZeroException e) {
                results.clear();
                addEmptyItems(results, 2);
                error = ConversionErrorCodes.ERROR_BELOW_ZERO;
            }
        } else if (kelvin.equals("-") || kelvin.equals(".")
                || kelvin.equals("") || kelvin.equals("-.")) {
            addEmptyItems(results, 2);
        } else {
            results.clear();
            addEmptyItems(results, 2);
            error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
        }

        return new Tuple<>(results, error);
    }

    /**
     * Takes in the kelvin value as a {@link String} and converts it to celsius.
     *
     * @param kelvin            The kelvin value as a {@link String}. Cannot be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent celsius value as a {@link String}, or null if the <code>kelvin</code>
     *          parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public String toCelsius(String kelvin, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (kelvin == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal temperature = new BigDecimal(kelvin);
        if (temperature.compareTo(BigDecimal.ZERO) > 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            BigDecimal celsius = temperature.subtract(new BigDecimal("273.15"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (celsius.compareTo(BigDecimal.ZERO) == 0) {
                celsius = BigDecimal.ZERO;
            }
            return celsius.stripTrailingZeros().toPlainString();
        } else if (temperature.compareTo(BigDecimal.ZERO) == 0) {
            return "-273.15";
        } else {
            throw new ValueBelowZeroException("Temperature cannot be below absolute zero");
        }
    }

    /**
     * Takes in the kelvin value as a {@link String} and converts it to fahrenheit.
     *
     * @param kelvin            The kelvin value as a {@link String}. Cannot be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent fahrenheit value as a {@link String}, or null if the <code>kelvin</code>
     *          parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public String toFahrenheit(String kelvin, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (kelvin == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal temperature = new BigDecimal(kelvin);
        if (temperature.compareTo(BigDecimal.ZERO) > 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            BigDecimal fahrenheit = temperature.subtract(new BigDecimal("273.15"))
                    .multiply(new BigDecimal("1.8"))
                    .add(new BigDecimal("32"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (fahrenheit.compareTo(BigDecimal.ZERO) == 0) {
                fahrenheit = BigDecimal.ZERO;
            }
            return fahrenheit.stripTrailingZeros().toPlainString();
        } else if (temperature.compareTo(BigDecimal.ZERO) == 0) {
            return "-459.67";
        } else {
            throw new ValueBelowZeroException("Temperature cannot be below absolute zero");
        }
    }

    // endregion
}
