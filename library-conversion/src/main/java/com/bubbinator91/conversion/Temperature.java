package com.bubbinator91.conversion;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import timber.log.Timber;

/**
 * Provides classes that handle the conversion of temperatures.
 */
public class Temperature {

    /**
     * Handles the conversion from Celsius to other temperatures
     */
    public static class Celsius {
        private static final String TAG = "Celsius";

        /**
         * Static method that takes in the celsius value as a {@link String} and converts it to both
         * fahrenheit and kelvin.
         *
         * @param celsius           The celsius value as a {@link String}. Should not be null.
         * @param roundingLength    The number of decimal places to round to. If below zero, will be
         *                          treated as if it was zero.
         *
         * @return  A {@link Tuple}, where the first item is a {@link List} containing the equivalent
         *          fahrenheit and kelvin values (in that order), and the second item is one of the
         *          error codes found in {@link Conversion}, or null if the <code>celsius</code>
         *          parameter is null.
         */
        public static Tuple<List<String>, Conversion> toAll(String celsius, int roundingLength) {
            Timber.tag(TAG + ".toAll").i("Entered");

            if (celsius == null) {
                return null;
            }

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
                    Conversion.addEmptyItems(results, 2);
                    error = Conversion.ERROR_INPUT_NOT_NUMERIC;
                } catch (ValueBelowZeroException e) {
                    Timber.tag(TAG + ".toAll").e(e.getMessage());
                    results.clear();
                    Conversion.addEmptyItems(results, 2);
                    error = Conversion.ERROR_BELOW_ABSOLUTE_ZERO;
                }
            } else if (celsius.equals("-") || celsius.equals(".") || celsius.equals("")) {
                Timber.tag(TAG + ".toAll").i("Input was a - or . or whitespace");
                Conversion.addEmptyItems(results, 2);
            } else {
                Timber.tag(TAG + ".toAll").i("Input was not numeric");
                results.clear();
                Conversion.addEmptyItems(results, 2);
                error = Conversion.ERROR_INPUT_NOT_NUMERIC;
            }
            return new Tuple<>(results, error);
        }

        /**
         * Static method that takes in the celsius value as a {@link String} and converts it to
         * fahrenheit.
         *
         * @param celsius           The celsius value as a {@link String}. Should not be null.
         * @param roundingLength    The number of decimal places to round to. If below zero, will be
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
        public static String toFahrenheit(String celsius, int roundingLength)
                throws NumberFormatException, ValueBelowZeroException {
            Timber.tag(TAG + ".toFahrenheit").i("Entered");

            if (celsius == null) {
                return null;
            }

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
         * @return  The equivalent kelvin value as a {@link String}, or null if the <code>celsius</code>
         *          parameter is null.
         *
         * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
         *                                      number.
         * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
         *                                      zero.
         */
        public static String toKelvin(String celsius, int roundingLength)
                throws NumberFormatException, ValueBelowZeroException {
            Timber.tag(TAG + ".toKelvin").i("Entered");

            if (celsius == null) {
                return null;
            }

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

    /**
     * Handles the conversion from Fahrenheit to other temperatures
     */
    public static class Fahrenheit {
        private static final String TAG = "Fahrenheit";

        /**
         * Static method that takes in the fahrenheit value as a {@link String} and converts it to both
         * celsius and kelvin.
         *
         * @param fahrenheit        The fahrenheit value as a {@link String}. Cannot be null.
         * @param roundingLength    The number of decimal places to round to. If below zero, will be
         *                          treated as if it was zero.
         *
         * @return  A {@link Tuple}, where the first item is a {@link List} containing the equivalent
         *          celsius and kelvin values (in that order), and the second item is one of the
         *          error codes found in {@link Conversion}, or null if the <code>fahrenheit</code>
         *          parameter is null.
         */
        public static Tuple<List<String>, Conversion> toAll(String fahrenheit, int roundingLength) {
            Timber.tag(TAG + ".toAll").i("Entered");

            if (fahrenheit == null) {
                return null;
            }

            Timber.tag(TAG + ".toAll").i("fahrenheit = " + fahrenheit);
            int decimalPlaces = (roundingLength < 0) ? 0 : roundingLength;
            List<String> results = new LinkedList<>();
            Conversion error = Conversion.ERROR_NONE;
            if (Conversion.isNumeric(fahrenheit)) {
                try {
                    results.add(toCelsius(fahrenheit, decimalPlaces));
                    results.add(toKelvin(fahrenheit, decimalPlaces));
                } catch (NumberFormatException e) {
                    Timber.tag(TAG + ".toAll").e(e.getMessage());
                    results.clear();
                    Conversion.addEmptyItems(results, 2);
                    error = Conversion.ERROR_INPUT_NOT_NUMERIC;
                } catch (ValueBelowZeroException e) {
                    Timber.tag(TAG + ".toAll").e(e.getMessage());
                    results.clear();
                    Conversion.addEmptyItems(results, 2);
                    error = Conversion.ERROR_BELOW_ABSOLUTE_ZERO;
                }
            } else if (fahrenheit.equals("-") || fahrenheit.equals(".") || fahrenheit.equals("")) {
                Timber.tag(TAG + ".toAll").i("Input was a - or . or whitespace");
                Conversion.addEmptyItems(results, 2);
            } else {
                Timber.tag(TAG + ".toAll").i("Input was not numeric");
                results.clear();
                Conversion.addEmptyItems(results, 2);
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
         * @return  The equivalent celsius value as a {@link String}, or null if the
         *          <code>fahrenheit</code> parameter is null.
         *
         * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
         *                                      number.
         * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
         *                                      zero.
         */
        public static String toCelsius(String fahrenheit, int roundingLength)
                throws NumberFormatException, ValueBelowZeroException {
            Timber.tag(TAG + ".toCelsius").i("Entered");

            if (fahrenheit == null) {
                return null;
            }

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
         * @return  The equivalent kelvin value as a {@link String}, or null if the
         *          <code>fahrenheit</code> parameter is null.
         *
         * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
         *                                      number.
         * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
         *                                      zero.
         */
        public static String toKelvin(String fahrenheit, int roundingLength)
                throws NumberFormatException, ValueBelowZeroException {
            Timber.tag(TAG + ".toKelvin").i("Entered");

            if (fahrenheit == null) {
                return null;
            }

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

    /**
     * Handles the conversion from Kelvin to other temperatures
     */
    public static class Kelvin {
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
         *          error codes found in {@link Conversion}, or null if the <code>kelvin</code>
         *          parameter is null.
         */
        public static Tuple<List<String>, Conversion> toAll(String kelvin, int roundingLength) {
            Timber.tag(TAG + ".toAll").i("Entered");

            if (kelvin == null) {
                return null;
            }

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
                    Conversion.addEmptyItems(results, 2);
                    error = Conversion.ERROR_INPUT_NOT_NUMERIC;
                } catch (ValueBelowZeroException e) {
                    Timber.tag(TAG + ".toAll").e(e.getMessage());
                    results.clear();
                    Conversion.addEmptyItems(results, 2);
                    error = Conversion.ERROR_BELOW_ABSOLUTE_ZERO;
                }
            } else if (kelvin.equals("-") || kelvin.equals(".") || kelvin.equals("")) {
                Timber.tag(TAG + ".toAll").i("Input was a - or . or whitespace");
                Conversion.addEmptyItems(results, 2);
            } else {
                Timber.tag(TAG + ".toAll").i("Input was not numeric");
                results.clear();
                Conversion.addEmptyItems(results, 2);
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
         * @return  The equivalent celsius value as a {@link String}, or null if the <code>kelvin</code>
         *          parameter is null.
         *
         * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
         *                                      number.
         * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
         *                                      zero.
         */
        public static String toCelsius(String kelvin, int roundingLength)
                throws NumberFormatException, ValueBelowZeroException {
            Timber.tag(TAG + ".toCelsius").i("Entered");

            if (kelvin == null) {
                return null;
            }

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
         * @return  The equivalent fahrenheit value as a {@link String}, or null if the <code>kelvin</code>
         *          parameter is null.
         *
         * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
         *                                      number.
         * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
         *                                      zero.
         */
        public static String toFahrenheit(String kelvin, int roundingLength)
                throws NumberFormatException, ValueBelowZeroException {
            Timber.tag(TAG + ".toFahrenheit").i("Entered");

            if (kelvin == null) {
                return null;
            }

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
}
