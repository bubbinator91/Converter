package com.bubbinator91.conversion.speed;

import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.conversion.util.Tuple;
import com.bubbinator91.conversion.util.Unit;
import com.bubbinator91.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles the conversion from miles per hour to other units of speed
 */
public class MilesPerHour extends Unit {

    // Prevents class from being instantiated directly
    private MilesPerHour() {}

    // region Singleton items

    /**
     * Holds the instance of the {@link MilesPerHour} class. Private so that only the MilesPerHour
     * class can use it, and static so that it can carry a static instance of the MilesPerHour
     * class.
     */
    private static class MilesPerHourInstance {
        private static final MilesPerHour INSTANCE = new MilesPerHour();
    }

    /**
     * Gets the instance of the {@link MilesPerHour} class from the MilesPerHourInstance class.
     * Protected so that only members of the same package can use this method, such as
     * {@link Speed}.
     *
     * @return  An instance of the {@link MilesPerHour} class.
     */
    protected static MilesPerHour getInstance() {
        return MilesPerHourInstance.INSTANCE;
    }

    // endregion

    // region Public methods

    /**
     * Takes in the miles per hour value as a {@link String} and converts it to feet per second,
     * knots, kilometers per hour, and meters per second.
     *
     * @param mph               The miles per hour value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link Tuple}, where the first item is a {@link List} containing the equivalent
     *          feet per second, knots, kilometers per hour, and meters per second values (in that
     *          order; they will be empty {@link String}s if there is an error), and the second item
     *          is one of the error codes found in {@link ConversionErrorCodes} as an
     *          {@link Integer} object, or null if the <code>centimeters</code> parameter is null;
     */
    public Tuple<List<String>, Integer> toAll(String mph, int decimalPlaces) {
        if (mph == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        List<String> results = new LinkedList<>();
        int error = ConversionErrorCodes.ERROR_NONE;

        if (isNumeric(mph)) {
            try {
                results.add(toFeetPerSecond(mph, roundingLength));
                results.add(toKnots(mph, roundingLength));
                results.add(toKilometersPerHour(mph, roundingLength));
                results.add(toMetersPerSecond(mph, roundingLength));
            } catch (NumberFormatException e) {
                results.clear();
                addEmptyItems(results, 4);
                error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
            } catch (ValueBelowZeroException e) {
                results.clear();
                addEmptyItems(results, 4);
                error = ConversionErrorCodes.ERROR_BELOW_ZERO;
            }
        } else if (mph.equals(".") || mph.equals("")) {
            results.clear();
            addEmptyItems(results, 4);
        } else {
            addEmptyItems(results, 4);
            error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
        }

        return new Tuple<>(results, error);
    }

    /**
     * Takes in the miles per hour value as a {@link String} and converts it to feet per second.
     *
     * @param mph               The miles per hour value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent feet per second value as a {@link String}, or null if the
     *          <code>mph</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toFeetPerSecond(String mph, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (mph == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal speed = new BigDecimal(mph);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("5280"))
                    .divide(new BigDecimal("3600"), roundingLength, BigDecimal.ROUND_HALF_UP);
            if (speed.compareTo(BigDecimal.ZERO) == 0) {
                speed = BigDecimal.ZERO;
            }
            return speed.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Speed cannot be below zero");
        }
    }

    /**
     * Takes in the miles per hour value as a {@link String} and converts it to knots.
     *
     * @param mph               The miles per hour value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent knots value as a {@link String}, or null if the <code>mph</code>
     *          parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toKnots(String mph, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (mph == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal speed = new BigDecimal(mph);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("0.8689762419006479"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (speed.compareTo(BigDecimal.ZERO) == 0) {
                speed = BigDecimal.ZERO;
            }
            return speed.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Speed cannot be below zero");
        }
    }

    /**
     * Takes in the miles per hour value as a {@link String} and converts it to kilometers per
     * hour.
     *
     * @param mph               The miles per hour value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent kilometers per hour value as a {@link String}, or null if the
     *          <code>mph</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toKilometersPerHour(String mph, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (mph == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal speed = new BigDecimal(mph);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("1.609344"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (speed.compareTo(BigDecimal.ZERO) == 0) {
                speed = BigDecimal.ZERO;
            }
            return speed.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Speed cannot be below zero");
        }
    }

    /**
     * Takes in the miles per hour value as a {@link String} and converts it to meters per second.
     *
     * @param mph               The miles per hour value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent meters per second value as a {@link String}, or null if the
     *          <code>mph</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toMetersPerSecond(String mph, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (mph == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal speed = new BigDecimal(mph);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("0.44704"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (speed.compareTo(BigDecimal.ZERO) == 0) {
                speed = BigDecimal.ZERO;
            }
            return speed.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Speed cannot be below zero");
        }
    }

    // endregion
}
