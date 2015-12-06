package com.bubbinator91.converter.conversion.length;

import com.bubbinator91.converter.conversion.util.ConversionErrorCodes;
import com.bubbinator91.converter.conversion.util.Tuple;
import com.bubbinator91.converter.conversion.util.Unit;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles the conversion from millimeters to other units of length
 */
public class Millimeters extends Unit {

    // Prevents class from being instantiated directly
    private Millimeters() {}

    // region Singleton items

    /**
     * Holds the instance of the {@link Millimeters} class. Private so that only the Millimeters
     * class can use it, and static so that it can carry a static instance of the Millimeters class.
     */
    private static class MillimetersInstance {
        private static final Millimeters INSTANCE = new Millimeters();
    }

    /**
     * Gets the instance of the {@link Millimeters} class from the MillimetersInstance class.
     * Protected so that only members of the same package can use this method, such as
     * {@link Length}.
     *
     * @return  An instance of the {@link Millimeters} class.
     */
    protected static Millimeters getInstance() {
        return MillimetersInstance.INSTANCE;
    }

    // endregion

    // region Public methods

    /**
     * Takes in the millimeters value as a {@link String} and converts it to inches, feet, yards,
     * miles, centimeters, meters, and kilometers.
     *
     * @param millimeters       The millimeters value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link Tuple}, where the first item is a {@link List} containing the equivalent
     *          inches, feet, yards, miles, centimeters, meters, and kilometers values (in that
     *          order; they will be empty {@link String}s if there is an error), and the second item
     *          is one of the error codes found in {@link ConversionErrorCodes} as an
     *          {@link Integer} object, or null if the <code>millimeters</code> parameter is null;
     */
    public Tuple<List<String>, Integer> toAll(String millimeters,
                                                                  int decimalPlaces) {
        if (millimeters == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        List<String> results = new LinkedList<>();
        int error = ConversionErrorCodes.ERROR_NONE;

        if (isNumeric(millimeters)) {
            try {
                results.add(toInches(millimeters, roundingLength));
                results.add(toFeet(millimeters, roundingLength));
                results.add(toYards(millimeters, roundingLength));
                results.add(toMiles(millimeters, roundingLength));
                results.add(toCentimeters(millimeters, roundingLength));
                results.add(toMeters(millimeters, roundingLength));
                results.add(toKilometers(millimeters, roundingLength));
            } catch (NumberFormatException e) {
                results.clear();
                addEmptyItems(results, 7);
                error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
            } catch (ValueBelowZeroException e) {
                results.clear();
                addEmptyItems(results, 7);
                error = ConversionErrorCodes.ERROR_BELOW_ZERO;
            }
        } else if (millimeters.equals(".") || millimeters.equals("")) {
            results.clear();
            addEmptyItems(results, 7);
        } else {
            addEmptyItems(results, 7);
            error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
        }

        return new Tuple<>(results, error);
    }

    /**
     * Takes in the millimeters value as a {@link String} and converts it to inches.
     *
     * @param millimeters       The millimeters value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent inches value as a {@link String}, or null if the
     *          <code>millimeters</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toInches(String millimeters, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (millimeters == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(millimeters);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("0.03937007874015748031496062992126"))
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
     * Takes in the millimeters value as a {@link String} and converts it to feet.
     *
     * @param millimeters       The millimeters value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent feet value as a {@link String}, or null if the
     *          <code>millimeters</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toFeet(String millimeters, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (millimeters == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(millimeters);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("0.00328083989501312335958005249344"))
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
     * Takes in the millimeters value as a {@link String} and converts it to yards.
     *
     * @param millimeters       The millimeters value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent yards value as a {@link String}, or null if the
     *          <code>millimeters</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toYards(String millimeters, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (millimeters == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(millimeters);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("0.00109361329833770778652668416448"))
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
     * Takes in the millimeters value as a {@link String} and converts it to miles.
     *
     * @param millimeters       The millimeters value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent miles value as a {@link String}, or null if the
     *          <code>millimeters</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toMiles(String millimeters, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (millimeters == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(millimeters);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.multiply(new BigDecimal("0.00000062137119223733396961743418436364"))
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
     * Takes in the millimeters value as a {@link String} and converts it to centimeters.
     *
     * @param millimeters       The millimeters value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent centimeters value as a {@link String}, or null if the
     *          <code>millimeters</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toCentimeters(String millimeters, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (millimeters == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(millimeters);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.divide(new BigDecimal("10"), roundingLength, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Length cannot be below zero");
        }
    }

    /**
     * Takes in the millimeters value as a {@link String} and converts it to meters.
     *
     * @param millimeters       The millimeters value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent meters value as a {@link String}, or null if the
     *          <code>millimeters</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toMeters(String millimeters, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (millimeters == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(millimeters);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.divide(new BigDecimal("1000"), roundingLength, BigDecimal.ROUND_HALF_UP);
            if (length.compareTo(BigDecimal.ZERO) == 0) {
                length = BigDecimal.ZERO;
            }
            return length.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Length cannot be below zero");
        }
    }

    /**
     * Takes in the millimeters value as a {@link String} and converts it to kilometers.
     *
     * @param millimeters       The millimeters value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kilometers value as a {@link String}, or null if the
     *          <code>millimeters</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toKilometers(String millimeters, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (millimeters == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal length = new BigDecimal(millimeters);
        if (length.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            length = length.divide(new BigDecimal("1000000"), roundingLength, BigDecimal.ROUND_HALF_UP);
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
