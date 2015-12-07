package com.bubbinator91.converter.conversion.acceleration;

import com.bubbinator91.converter.conversion.util.Unit;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import rx.Observable;

/**
 * Handles the conversion from centimeters per second^2 (gal or cm/s^2) to other units of
 * acceleration
 */
public class CentimetersPerSecondSquared extends Unit {

    // Prevents class from being instantiated directly
    private CentimetersPerSecondSquared() {}

    // region Public methods

    /**
     * Takes in the centimeters per second squared value as a {@link String} and converts it to
     * feet per second squared, meters per second squared, and standard gravity by emitting an
     * {@link Observable}. When subscribing, make sure to also handle the onError() call.
     *
     * @param cmpss             The centimeters per second squared value as a {@link String}.
     *                           Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  An {@link Observable}, created with a call to defer(), that will either emit a
     *           {@link List} containing the equivalent feet per second squared, meters per
     *           second squared, and standard gravity values (in that order; they will be empty
     *           {@link String}s if there is valid, non-numerical input, such as a leading decimal
     *           point), a null value if the <code>cmpss</code> parameter is null, or an error if an
     *           {@link Exception} was thrown.
     */
    public static Observable<List<String>> toAll(final String cmpss, final int decimalPlaces) {
        return Observable.defer(() -> {
            try {
                if (cmpss == null) {
                    return Observable.just(null);
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

                return Observable.just(results);
            } catch (Exception e) {
                return Observable.error(e);
            }
        });
    }

    /**
     * Takes in the centimeters per second squared value as a {@link String} and converts it to feet
     * per second squared.
     *
     * @param cmpss             The centimeters per second squared value as a {@link String}.
     *                           Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent feet per second squared value as a {@link String}, or null if the
     *           <code>cmpss</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toFeetPerSecondSquared(String cmpss, int decimalPlaces)
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
     * @param cmpss             The centimeters per second squared value as a {@link String}.
     *                           Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent meters per second squared value as a {@link String}, or null if the
     *           <code>cmpss</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toMetersPerSecondSquared(String cmpss, int decimalPlaces)
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
     * @param cmpss             The centimeters per second squared value as a {@link String}.
     *                           Should not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent standard gravity value as a {@link String}, or null if the
     *           <code>cmpss</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toStandardGravity(String cmpss, int decimalPlaces)
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
