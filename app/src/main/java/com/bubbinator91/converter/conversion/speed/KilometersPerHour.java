package com.bubbinator91.converter.conversion.speed;

import com.bubbinator91.converter.conversion.util.ConversionErrorCodes;
import com.bubbinator91.converter.conversion.util.Tuple;
import com.bubbinator91.converter.conversion.util.Unit;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles the conversion from knots to other units of speed
 */
public class KilometersPerHour extends Unit {

    // Prevents class from being instantiated directly
    private KilometersPerHour() {}

    // region Singleton items

    /**
     * Holds the instance of the {@link FeetPerSecond} class. Private so that only the FeetPerSecond
     * class can use it, and static so that it can carry a static instance of the FeetPerSecond
     * class.
     */
    private static class KilometersPerHourInstance {
        private static final KilometersPerHour INSTANCE = new KilometersPerHour();
    }

    /**
     * Gets the instance of the {@link KilometersPerHour} class from the KilometersPerHourInstance
     * class. Protected so that only members of the same package can use this method, such as
     * {@link Speed}.
     *
     * @return  An instance of the {@link KilometersPerHour} class.
     */
    protected static KilometersPerHour getInstance() {
        return KilometersPerHourInstance.INSTANCE;
    }

    // endregion

    // region Public methods

    /**
     * Takes in the kilometers per hour value as a {@link String} and converts it to feet per
     * second, knots, meters per second, and miles per hour.
     *
     * @param kph               The kilometers per hour value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link Tuple}, where the first item is a {@link List} containing the equivalent
     *          feet per second, knots, meters per second, and miles per hour values (in that order;
     *          they will be empty {@link String}s if there is an error), and the second item is one
     *          of the error codes found in {@link ConversionErrorCodes} as an {@link Integer}
     *          object, or null if the <code>kph</code> parameter is null;
     */
    public Tuple<List<String>, Integer> toAll(String kph, int decimalPlaces) {
        if (kph == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        List<String> results = new LinkedList<>();
        int error = ConversionErrorCodes.ERROR_NONE;

        if (isNumeric(kph)) {
            try {
                results.add(toFeetPerSecond(kph, roundingLength));
                results.add(toKnots(kph, roundingLength));
                results.add(toMetersPerSecond(kph, roundingLength));
                results.add(toMilesPerHour(kph, roundingLength));
            } catch (NumberFormatException e) {
                results.clear();
                addEmptyItems(results, 4);
                error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
            } catch (ValueBelowZeroException e) {
                results.clear();
                addEmptyItems(results, 4);
                error = ConversionErrorCodes.ERROR_BELOW_ZERO;
            }
        } else if (kph.equals(".") || kph.equals("")) {
            results.clear();
            addEmptyItems(results, 4);
        } else {
            addEmptyItems(results, 4);
            error = ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC;
        }

        return new Tuple<>(results, error);
    }

    /**
     * Takes in the kilometers per hour value as a {@link String} and converts it to feet per
     * second.
     *
     * @param kph               The kilometers per hour value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent feet per second value as a {@link String}, or null if the
     *          <code>kph</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toFeetPerSecond(String kph, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (kph == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal speed = new BigDecimal(kph);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("0.9113444152814232"))
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
     * Takes in the kilometers per hour value as a {@link String} and converts it to knots.
     *
     * @param kph               The kilometers per hour value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent knots value as a {@link String}, or null if the <code>kph</code>
     *          parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toKnots(String kph, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (kph == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal speed = new BigDecimal(kph);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("0.5399568034557235"))
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
     * Takes in the kilometers per hour value as a {@link String} and converts it to meters per
     * second.
     *
     * @param kph               The kilometers per hour value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent meters per second value as a {@link String}, or null if the
     *          <code>kph</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toMetersPerSecond(String kph, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (kph == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal speed = new BigDecimal(kph);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.divide(new BigDecimal("3.6"), roundingLength, BigDecimal.ROUND_HALF_UP);
            if (speed.compareTo(BigDecimal.ZERO) == 0) {
                speed = BigDecimal.ZERO;
            }
            return speed.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Speed cannot be below zero");
        }
    }

    /**
     * Takes in the kilometers per hour value as a {@link String} and converts it to miles per hour.
     *
     * @param kph               The kilometers per hour value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent miles per hour value as a {@link String}, or null if the
     *          <code>kph</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toMilesPerHour(String kph, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (kph == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal speed = new BigDecimal(kph);
        if (speed.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            speed = speed.multiply(new BigDecimal("0.621371192237334"))
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
