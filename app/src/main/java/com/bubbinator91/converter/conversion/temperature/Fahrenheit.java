package com.bubbinator91.converter.conversion.temperature;

import com.bubbinator91.converter.conversion.util.Unit;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import rx.Observable;

/**
 * Handles the conversion from Fahrenheit to other temperatures
 */
public class Fahrenheit extends Unit {

    // Prevents class from being instantiated directly
    private Fahrenheit() {}

    // region Public methods

    /**
     * Takes in the fahrenheit value as a {@link String} and converts it to both celsius and kelvin
     * by emitting an {@link Observable}. When subscribing, make sure to also handle the onError()
     * call.
     *
     * @param fahrenheit        The fahrenheit value as a {@link String}. Cannot be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  An {@link Observable}, created with a call to defer(), that will either emit a
     *           {@link List} containing the equivalent celsius and kelvin values (in that order;
     *           they will be empty {@link String}s if there is valid, non-numerical input, such as
     *           a leading decimal point), a null value if the <code>fahrenheit</code> parameter is
     *           null, or an error if an {@link Exception} was thrown.
     */
    public static Observable<List<String>> toAll(final String fahrenheit, final int decimalPlaces) {
        return Observable.defer(() -> {
            try {
                if (fahrenheit == null) {
                    return Observable.just(null);
                }

                int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
                List<String> results = new LinkedList<>();

                if (isNumeric(fahrenheit)) {
                    results.add(toCelsius(fahrenheit, roundingLength));
                    results.add(toKelvin(fahrenheit, roundingLength));
                } else if (fahrenheit.equals("-") || fahrenheit.equals(".")
                        || fahrenheit.equals("") || fahrenheit.equals("-.")) {
                    results.clear();
                    addEmptyItems(results, 2);
                } else {
                    throw new NumberFormatException("Input was not numeric.");
                }

                return Observable.just(results);
            } catch (Exception e) {
                return Observable.error(e);
            }
        });
    }

    /**
     * Takes in the fahrenheit value as a {@link String} and converts it to celsius.
     *
     * @param fahrenheit        The fahrenheit value as a {@link String}. Cannot be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent celsius value as a {@link String}, or null if the
     *           <code>fahrenheit</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                       zero.
     */
    public static String toCelsius(String fahrenheit, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (fahrenheit == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal temperature = new BigDecimal(fahrenheit);
        if (temperature.compareTo(new BigDecimal("-459.67")) > 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            temperature = temperature.subtract(new BigDecimal("32"))
                    .multiply((new BigDecimal("5"))
                    .divide(new BigDecimal("9"), 100, BigDecimal.ROUND_HALF_UP))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (temperature.compareTo(BigDecimal.ZERO) == 0) {
                temperature = BigDecimal.ZERO;
            }
            return temperature.stripTrailingZeros().toPlainString();
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
     *                           treated as if it was zero.
     *
     * @return  The equivalent kelvin value as a {@link String}, or null if the
     *           <code>fahrenheit</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                       zero.
     */
    public static String toKelvin(String fahrenheit, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (fahrenheit == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal temperature = new BigDecimal(fahrenheit);
        if (temperature.compareTo(new BigDecimal("-459.67")) > 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            temperature = temperature.subtract(new BigDecimal("32"))
                    .multiply(new BigDecimal(5.0 / 9.0))
                    .add(new BigDecimal("273.15"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (temperature.compareTo(BigDecimal.ZERO) == 0) {
                temperature = BigDecimal.ZERO;
            }
            return temperature.stripTrailingZeros().toPlainString();
        } else if (temperature.compareTo(new BigDecimal("-459.67")) == 0) {
            return "0";
        } else {
            throw new ValueBelowZeroException("Temperature cannot be below absolute zero");
        }
    }

    // endregion
}
