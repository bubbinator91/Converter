package com.bubbinator91.converter.conversion.length;

import com.bubbinator91.converter.conversion.util.Unit;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import rx.Observable;

/**
 * Handles the conversion from yards to other units of length
 */
public class Yards extends Unit {

    // Prevents class from being instantiated directly
    private Yards() {}

    // region Public methods

    /**
     * Takes in the yards value as a {@link String} and converts it to inches, feet, miles,
     * millimeters, centimeters, meters, and kilometers by emitting an {@link Observable}. When
     * subscribing, make sure to also handle the onError() call.
     *
     * @param yards             The yards value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  An {@link Observable}, created with a call to defer(), that will either emit a
     *           {@link List} containing the equivalent inches, feet, miles, millimeters,
     *           centimeters, meters, and kilometers values (in that order; they will be empty
     *           {@link String}s if there is valid, non-numerical input, such as a leading decimal
     *           point), a null value if the <code>yards</code> parameter is null, or an error if an
     *           {@link Exception} was thrown.
     */
    public static Observable<List<String>> toAll(final String yards, final int decimalPlaces) {
        return Observable.defer(() -> {
            try {
                if (yards == null) {
                    return Observable.just(null);
                }

                int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
                List<String> results = new LinkedList<>();

                if (isNumeric(yards)) {
                    results.add(toInches(yards, roundingLength));
                    results.add(toFeet(yards, roundingLength));
                    results.add(toMiles(yards, roundingLength));
                    results.add(toMillimeters(yards, roundingLength));
                    results.add(toCentimeters(yards, roundingLength));
                    results.add(toMeters(yards, roundingLength));
                    results.add(toKilometers(yards, roundingLength));
                } else if (yards.equals(".") || yards.equals("")) {
                    results.clear();
                    addEmptyItems(results, 7);
                } else {
                    throw new NumberFormatException("Input was not numeric");
                }

                return Observable.just(results);
            } catch (Exception e) {
                return Observable.error(e);
            }
        });
    }

    /**
     * Takes in the yards value as a {@link String} and converts it to inches.
     *
     * @param yards             The yards value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent inches value as a {@link String}, or null if the <code>yards</code>
     *           parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toInches(String yards, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (yards == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(yards);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("36"))
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
     * Takes in the yards value as a {@link String} and converts it to feet.
     *
     * @param yards             The yards value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent feet value as a {@link String}, or null if the <code>yards</code>
     *           parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toFeet(String yards, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (yards == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(yards);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("3"))
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
     * Takes in the yards value as a {@link String} and converts it to miles.
     *
     * @param yards             The yards value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent miles value as a {@link String}, or null if the <code>yards</code>
     *           parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toMiles(String yards, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (yards == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(yards);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.divide(new BigDecimal("1760"), roundingLength, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Length cannot be below zero");
        }
    }

    /**
     * Takes in the yards value as a {@link String} and converts it to millimeters.
     *
     * @param yards             The yards value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent millimeters value as a {@link String}, or null if the
     *           <code>yards</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toMillimeters(String yards, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (yards == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(yards);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("914.4"))
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
     * Takes in the yards value as a {@link String} and converts it to centimeters.
     *
     * @param yards             The yards value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent centimeters value as a {@link String}, or null if the
     *           <code>yards</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toCentimeters(String yards, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (yards == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(yards);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("91.44"))
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
     * Takes in the yards value as a {@link String} and converts it to meters.
     *
     * @param yards             The yards value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent meters value as a {@link String}, or null if the <code>yards</code>
     *           parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toMeters(String yards, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (yards == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(yards);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("0.9144"))
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
     * Takes in the yards value as a {@link String} and converts it to kilometers.
     *
     * @param yards             The yards value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent kilometers value as a {@link String}, or null if the
     *           <code>yards</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toKilometers(String yards, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (yards == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(yards);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("0.0009144"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
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
