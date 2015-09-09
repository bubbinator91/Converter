package com.bubbinator91.conversion.acceleration;

import com.bubbinator91.conversion.util.Unit;
import com.bubbinator91.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles the conversion from foot per second^2 (ft/s^2) to other units of acceleration
 */
public class FeetPerSecondSquared extends Unit {

    // Prevents class from being instantiated directly
    private FeetPerSecondSquared() {}

    // region Singleton items

    /**
     * Holds the instance of the {@link FeetPerSecondSquared} class. Private so that only the
     * FeetPerSecondSquared class can use it, and static so that it can carry a static instance of
     * the FeetPerSecondSquared class.
     */
    private static class FeetPerSecondSquaredInstance {
        private static final FeetPerSecondSquared INSTANCE = new FeetPerSecondSquared();
    }

    /**
     * Gets the instance of the {@link FeetPerSecondSquared} class from the
     * FeetPerSecondSquaredInstance class. Protected so that only members of the same package
     * can use this method, such as {@link Acceleration}.
     *
     * @return  An instance of the {@link FeetPerSecondSquared} class.
     */
    protected static FeetPerSecondSquared getInstance() {
        return FeetPerSecondSquaredInstance.INSTANCE;
    }

    // endregion

    // region Public methods

    /**
     * Takes in the feet per second squared value as a {@link String} and converts it to centimeters
     * per second squared, meters per second squared, and standard gravity.
     *
     * @param fpss              The feet per second squared value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link List} containing the equivalent centimeters per second squared, meters per
     *          second squared, and standard gravity values (in that order; they will be empty
     *          {@link String}s if there is valid, non-numerical input, such as a leading decimal
     *          point), or null if the <code>fpss</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public List<String> toAll(String fpss, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (fpss == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        List<String> results = new LinkedList<>();

        if (isNumeric(fpss)) {
            results.add(toCentimetersPerSecondSquared(fpss, roundingLength));
            results.add(toMetersPerSecondSquared(fpss, roundingLength));
            results.add(toStandardGravity(fpss, roundingLength));
        } else if (fpss.equals(".") || fpss.equals("")) {
            results.clear();
            addEmptyItems(results, 3);
        } else {
            throw new NumberFormatException("Input was not numeric.");
        }

        return results;
    }

    /**
     * Takes in the feet per second squared value as a {@link String} and converts it to centimeters
     * per second squared.
     *
     * @param fpss              The fpss per second squared value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent centimeters per second squared value as a {@link String}, or null if
     *          the <code>fpss</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toCentimetersPerSecondSquared(String fpss, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (fpss == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal acceleration = new BigDecimal(fpss);
        if (acceleration.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            acceleration = acceleration.multiply(new BigDecimal("30.48000000012"))
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
     * Takes in the feet per second squared value as a {@link String} and converts it to meters per
     * second squared.
     *
     * @param fpss              The feet per second squared value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent meters per second squared value as a {@link String}, or null if the
     *          <code>fpss</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toMetersPerSecondSquared(String fpss, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (fpss == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal acceleration = new BigDecimal(fpss);
        if (acceleration.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            acceleration = acceleration.multiply(new BigDecimal("0.3048000000012"))
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
     * Takes in the feet per second squared value as a {@link String} and converts it to standard
     * gravity
     *
     * @param fpss              The feet per second squared value as a {@link String}. Should not be
     *                          null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent standard gravity value as a {@link String}, or null if the
     *          <code>fpss</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toStandardGravity(String fpss, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (fpss == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal acceleration = new BigDecimal(fpss);
        if (acceleration.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            acceleration = acceleration.multiply(new BigDecimal("0.03108095017236"))
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
