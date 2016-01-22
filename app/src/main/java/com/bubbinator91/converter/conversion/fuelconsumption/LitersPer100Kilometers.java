package com.bubbinator91.converter.conversion.fuelconsumption;

import com.bubbinator91.converter.conversion.util.Unit;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import rx.Observable;

/**
 * Handles the conversion from liters per 100 kilometers (L/100km) to other units of fuel
 * consumption
 */
public class LitersPer100Kilometers extends Unit {

    // Prevents class from being instantiated directly
    private LitersPer100Kilometers() {}

    // region Public methods

    /**
     * Takes in the liters per 100 kilometers value as a {@link String} and converts it to US miles
     * per gallon, UK miles per gallon, and kilometers per liter by emitting an {@link Observable}.
     * When subscribing, make sure to also handle the onError() call.
     *
     * @param l100km            The liters per 100 kilometers value as a {@link String}. Should
     *                           not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  An {@link Observable}, created with a call to defer(), that will either emit a
     *           {@link List} containing the equivalent US miles per gallon, UK miles per gallon,
     *           and kilometers per liter values (in that order; they will be empty {@link String}s
     *           if there is valid, non-numerical input, such as a leading decimal point), a null
     *           value if the <code>l100km</code> parameter is null, or an error if an
     *           {@link Exception} was thrown.
     */
    public static Observable<List<String>> toAll(final String l100km, final int decimalPlaces) {
        return Observable.defer(() -> {
            try {
                if (l100km == null) {
                    return Observable.just(null);
                }

                int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
                List<String> results = new LinkedList<>();

                if (isNumeric(l100km)) {
                    results.add(toUSMilesPerGallon(l100km, roundingLength));
                    results.add(toUKMilesPerGallon(l100km, roundingLength));
                    results.add(toKilometersPerLiter(l100km, roundingLength));
                } else if (l100km.equals(".") || l100km.equals("")) {
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
     * Takes in the liters per 100 kilometers value as a {@link String} and converts it to US miles
     * per gallon.
     *
     * @param l100km            The liters per 100 kilometers value as a {@link String}. Should
     *                           not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent US miles per gallon value as a {@link String}, or null if the
     *           <code>l100km</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toUSMilesPerGallon(String l100km, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (l100km == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(l100km);
        if (fuel.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            fuel = (new BigDecimal("378.5411784"))
                    .divide((fuel.multiply(new BigDecimal("1.609344"))), 100, BigDecimal.ROUND_HALF_UP)
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (fuel.compareTo(BigDecimal.ZERO) == 0) {
                fuel = BigDecimal.ZERO;
            }
            return fuel.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Fuel consumption cannot be below zero");
        }
    }

    /**
     * Takes in the liters per 100 kilometers value as a {@link String} and converts it to UK miles
     * per gallon
     *
     * @param l100km            The liters per 100 kilometers value as a {@link String}. Should
     *                           not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                          treated as if it was zero.
     *
     * @return  The equivalent UK miles per gallon value as a {@link String}, or null if the
     *           <code>l100km</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toUKMilesPerGallon(String l100km, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (l100km == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(l100km);
        if (fuel.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            fuel = (new BigDecimal("454.609"))
                    .divide((fuel.multiply(new BigDecimal("1.609344"))), 100, BigDecimal.ROUND_HALF_UP)
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (fuel.compareTo(BigDecimal.ZERO) == 0) {
                fuel = BigDecimal.ZERO;
            }
            return fuel.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Fuel consumption cannot be below zero");
        }
    }

    /**
     * Takes in the liters per 100 kilometers value as a {@link String} and converts it to
     * kilometers per liter.
     *
     * @param l100km            The liters per 100 kilometers value as a {@link String}. Should
     *                           not be null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent kilometers per liter value as a {@link String}, or null if the
     *           <code>l100km</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toKilometersPerLiter(String l100km, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (l100km == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(l100km);
        if (fuel.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            fuel = (new BigDecimal("100"))
                    .divide(fuel, 100, BigDecimal.ROUND_HALF_UP)
                    .setScale(roundingLength, BigDecimal.ROUND_HALF_UP);
            if (fuel.compareTo(BigDecimal.ZERO) == 0) {
                fuel = BigDecimal.ZERO;
            }
            return fuel.stripTrailingZeros().toPlainString();
        } else {
            throw new ValueBelowZeroException("Fuel consumption cannot be below zero");
        }
    }

    // endregion
}
