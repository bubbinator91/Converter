package com.bubbinator91.converter.conversion.length;

import com.bubbinator91.converter.conversion.util.Unit;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import rx.Observable;

/**
 * Handles the conversion from centimeters to other units of length
 */
public class Centimeters extends Unit {

    // Prevents class from being instantiated directly
    private Centimeters() {}

    // region Public methods

    /**
     * Takes in the centimeters value as a {@link String} and converts it to inches, feet, yards,
     * miles, millimeters, meters, and kilometers by emitting an {@link Observable}. When
     * subscribing, make sure to also handle the onError() call.
     *
     * @param centimeters       The centimeters value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  An {@link Observable}, created with a call to defer(), that will either emit a
     *           {@link List} containing the equivalent inches, feet, yards, miles, millimeters,
     *           meters, and kilometers values (in that order; they will be empty {@link String}s
     *           if there is valid, non-numerical input, such as a leading decimal point), a null
     *           value if the <code>centimeters</code> parameter is null, or an error if an
     *           {@link Exception} was thrown.
     */
    public static Observable<List<String>> toAll(final String centimeters, final int decimalPlaces) {
        return Observable.defer(() -> {
            try {
                if (centimeters == null) {
                    return Observable.just(null);
                }

                int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
                List<String> results = new LinkedList<>();

                if (isNumeric(centimeters)) {
                    results.add(toInches(centimeters, roundingLength));
                    results.add(toFeet(centimeters, roundingLength));
                    results.add(toYards(centimeters, roundingLength));
                    results.add(toMiles(centimeters, roundingLength));
                    results.add(toMillimeters(centimeters, roundingLength));
                    results.add(toMeters(centimeters, roundingLength));
                    results.add(toKilometers(centimeters, roundingLength));
                } else if (centimeters.equals(".") || centimeters.equals("")) {
                    results.clear();
                    addEmptyItems(results, 7);
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
     * Takes in the centimeters value as a {@link String} and converts it to inches.
     *
     * @param centimeters       The centimeters value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent inches value as a {@link String}, or null if the
     *           <code>centimeters</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toInches(String centimeters, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (centimeters == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(centimeters);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("0.3937007874015748031496062992126"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Length cannot be below zero");
        }
    }

    /**
     * Takes in the centimeters value as a {@link String} and converts it to feet.
     *
     * @param centimeters       The centimeters value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent feet value as a {@link String}, or null if the
     *           <code>centimeters</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toFeet(String centimeters, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (centimeters == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(centimeters);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("0.0328083989501312335958005249344"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Length cannot be below zero");
        }
    }

    /**
     * Takes in the centimeters value as a {@link String} and converts it to yards.
     *
     * @param centimeters       The centimeters value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent yards value as a {@link String}, or null if the
     *           <code>centimeters</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toYards(String centimeters, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (centimeters == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(centimeters);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("0.0109361329833770778652668416448"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Length cannot be below zero");
        }
    }

    /**
     * Takes in the centimeters value as a {@link String} and converts it to miles.
     *
     * @param centimeters       The centimeters value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent miles value as a {@link String}, or null if the
     *           <code>centimeters</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toMiles(String centimeters, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (centimeters == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(centimeters);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("0.0000062137119223733396961743418436364"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Length cannot be below zero");
        }
    }

    /**
     * Takes in the centimeters value as a {@link String} and converts it to millimeters.
     *
     * @param centimeters       The centimeters value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent millimeters value as a {@link String}, or null if the
     *           <code>centimeters</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toMillimeters(String centimeters, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (centimeters == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(centimeters);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("10"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Length cannot be below zero");
        }
    }

    /**
     * Takes in the centimeters value as a {@link String} and converts it to meters.
     *
     * @param centimeters       The centimeters value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent meters value as a {@link String}, or null if the
     *           <code>centimeters</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toMeters(String centimeters, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (centimeters == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(centimeters);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.divide(new BigDecimal("100"), roundingLength, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Length cannot be below zero");
        }
    }

    /**
     * Takes in the centimeters value as a {@link String} and converts it to kilometers.
     *
     * @param centimeters       The centimeters value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent kilometers value as a {@link String}, or null if the
     *           <code>centimeters</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toKilometers(String centimeters, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (centimeters == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(centimeters);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.divide(new BigDecimal("100000"), roundingLength, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Length cannot be below zero");
        }
    }

    // endregion
}
