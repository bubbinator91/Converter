package com.bubbinator91.conversion.acceleration;

import com.bubbinator91.conversion.util.Unit;
import com.bubbinator91.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles the conversion from standard gravity (g sub n) to other units of acceleration
 */
public class StandardGravity extends Unit {

    // Prevents class from being instantiated directly
    private StandardGravity() {}

    // region Singleton items

    /**
     * Holds the instance of the {@link StandardGravity} class. Private so that only the
     * StandardGravity class can use it, and static so that it can carry a static instance of the
     * StandardGravity class.
     */
    private static class StandardGravityInstance {
        private static final StandardGravity INSTANCE = new StandardGravity();
    }

    /**
     * Gets the instance of the {@link StandardGravity} class from the StandardGravityInstance
     * class. Protected so that only members of the same package can use this method, such as
     * {@link Acceleration}.
     *
     * @return  An instance of the {@link StandardGravity} class.
     */
    protected static StandardGravity getInstance() {
        return StandardGravityInstance.INSTANCE;
    }

    // endregion

    // region Public methods

    /**
     * Takes in the standard gravity value as a {@link String} and converts it to centimeters per
     * second squared, feet per second squared, and meters per second squared.
     *
     * @param sg                The standard gravity value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link List} containing the equivalent centimeters per second squared, feet per
     *          second squared, and meters per second squared values (in that order; they will be
     *          empty {@link String}s if there is valid, non-numerical input, such as a leading
     *          decimal point), or null if the <code>sg</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public List<String> toAll(String sg, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (sg == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        List<String> results = new LinkedList<>();

        if (isNumeric(sg)) {
            results.add(toCentimetersPerSecondSquared(sg, roundingLength));
            results.add(toFeetPerSecondSquared(sg, roundingLength));
            results.add(toMetersPerSecondSquared(sg, roundingLength));
        } else if (sg.equals(".") || sg.equals("")) {
            results.clear();
            addEmptyItems(results, 3);
        } else {
            throw new NumberFormatException("Input was not numeric.");
        }

        return results;
    }

    /**
     * Takes in the standard gravity value as a {@link String} and converts it to centimeters per
     * second squared.
     *
     * @param sg                The standard gravity value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent centimeters per second squared value as a {@link String}, or null if
     *          the <code>sg</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public String toCentimetersPerSecondSquared(String sg, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (sg == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal acceleration = new BigDecimal(sg);
        if (acceleration.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            acceleration = acceleration.multiply(new BigDecimal("980.6649999788"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (acceleration.compareTo(BigDecimal.ZERO) == 0) {
                acceleration = BigDecimal.ZERO;
            }
            return acceleration.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Acceleration cannot be below zero");
        }
    }

    /**
     * Takes in the standard gravity value as a {@link String} and converts it to feet per second
     * squared.
     *
     * @param sg                The standard gravity value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent feet per second squared value as a {@link String}, or null if the
     *          <code>sg</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public String toFeetPerSecondSquared(String sg, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (sg == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal acceleration = new BigDecimal(sg);
        if (acceleration.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            acceleration = acceleration.multiply(new BigDecimal("32.17404855561"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (acceleration.compareTo(BigDecimal.ZERO) == 0) {
                acceleration = BigDecimal.ZERO;
            }
            return acceleration.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Acceleration cannot be below zero");
        }
    }

    /**
     * Takes in the standard gravity value as a {@link String} and converts it to meters per second
     * squared
     *
     * @param sg                The standard gravity value as a {@link String}. Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent meters per second squared value as a {@link String}, or null if the
     *          <code>sg</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below absolute
     *                                      zero.
     */
    public String toMetersPerSecondSquared(String sg, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (sg == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal acceleration = new BigDecimal(sg);
        if (acceleration.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            acceleration = acceleration.multiply(new BigDecimal("9.806649999788"))
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (acceleration.compareTo(BigDecimal.ZERO) == 0) {
                acceleration = BigDecimal.ZERO;
            }
            return acceleration.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Acceleration cannot be below zero");
        }
    }

    // endregion
}
