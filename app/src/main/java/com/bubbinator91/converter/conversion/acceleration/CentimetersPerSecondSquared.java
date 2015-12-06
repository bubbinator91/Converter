package com.bubbinator91.converter.conversion.acceleration;

import com.bubbinator91.converter.conversion.util.Unit;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles the conversion from centimeters per second^2 (gal or cm/s^2) to other units of
 * acceleration
 */
public class CentimetersPerSecondSquared extends Unit {

    // Prevents class from being instantiated directly
    private CentimetersPerSecondSquared() {}

    // region Singleton items

    /**
     * Holds the instance of the {@link CentimetersPerSecondSquared} class. Private so that only the
     * CentimetersPerSecondSquared class can use it, and static so that it can carry a static
     * instance of the CentimetersPerSecondSquared class.
     */
    private static class CentimetersPerSecondSquaredInstance {
        private static final CentimetersPerSecondSquared INSTANCE = new CentimetersPerSecondSquared();
    }

    /**
     * Gets the instance of the {@link CentimetersPerSecondSquared} class from the
     * CentimetersPerSecondSquaredInstance class. Protected so that only members of the same package
     * can use this method, such as {@link Acceleration}.
     *
     * @return  An instance of the {@link CentimetersPerSecondSquared} class.
     */
    protected static CentimetersPerSecondSquared getInstance() {
        return CentimetersPerSecondSquaredInstance.INSTANCE;
    }

    // endregion

    // region Public methods

    /**
     * Takes in the centimeters per second squared value as a {@link String} and converts it to
     * feet per second squared, meters per second squared, and standard gravity.
     *
     * @param cmpss             The centimeters per second squared value as a {@link String}. Should
     *                          not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  A {@link List} containing the equivalent feet per second squared, meters per
     *          second squared, and standard gravity values (in that order; they will be empty
     *          {@link String}s if there is valid, non-numerical input, such as a leading decimal
     *          point), or null if the <code>cmpss</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public List<String> toAll(String cmpss, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (cmpss == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        List<String> results = new LinkedList<>();

        if (isNumeric(cmpss)) {
            results.add(toFeetPerSecondSquared(cmpss, roundingLength));
            results.add(toMetersPerSecondSquared(cmpss, roundingLength));
            results.add(toStandardGravity(cmpss, roundingLength));
        } else if (cmpss.equals(".") || cmpss.equals("")) {
            results.clear();
            addEmptyItems(results, 3);
        } else {
            throw new NumberFormatException("Input was not numeric.");
        }

        return results;
    }

    /**
     * Takes in the centimeters per second squared value as a {@link String} and converts it to feet
     * per second squared.
     *
     * @param cmpss             The centimeters per second squared value as a {@link String}. Should
     *                          not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent feet per second squared value as a {@link String}, or null if the
     *          <code>cmpss</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toFeetPerSecondSquared(String cmpss, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (cmpss == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal acceleration = new BigDecimal(cmpss);
        if (acceleration.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            acceleration = acceleration.multiply(new BigDecimal("0.03280839895"))
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
     * Takes in the centimeters per second squared value as a {@link String} and converts it to
     * meters per second squared.
     *
     * @param cmpss             The centimeters per second squared value as a {@link String}. Should
     *                          not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent meters per second squared value as a {@link String}, or null if the
     *          <code>cmpss</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toMetersPerSecondSquared(String cmpss, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (cmpss == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal acceleration = new BigDecimal(cmpss);
        if (acceleration.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            acceleration = acceleration.multiply(new BigDecimal("0.01"))
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
     * Takes in the centimeters per second squared value as a {@link String} and converts it to
     * standard gravity
     *
     * @param cmpss             The centimeters per second squared value as a {@link String}. Should
     *                          not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent standard gravity value as a {@link String}, or null if the
     *          <code>cmpss</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                      number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public String toStandardGravity(String cmpss, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (cmpss == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal acceleration = new BigDecimal(cmpss);
        if (acceleration.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            acceleration = acceleration.multiply(new BigDecimal("0.001019716213"))
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
