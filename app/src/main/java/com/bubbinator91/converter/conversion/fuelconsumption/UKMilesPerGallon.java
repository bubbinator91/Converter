package com.bubbinator91.converter.conversion.fuelconsumption;

import com.bubbinator91.converter.conversion.util.Unit;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import rx.Observable;

/**
 * Handles the conversion from UK miles per gallon (mpg) to other units of fuel consumption
 */
public class UKMilesPerGallon extends Unit {

    // Prevents class from being instantiated directly
    private UKMilesPerGallon() {}

    // region Public methods

    /**
     * Takes in the UK miles per gallon value as a {@link String} and converts it to US miles per
     * gallon, kilometers per liter, and liters per 100 kilometers by emitting an
     * {@link Observable}. When subscribing, make sure to also handle the onError() call.
     *
     * @param ukmpg             The UK miles per gallon value as a {@link String}. Should not be
     *                           null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  An {@link Observable}, created with a call to defer(), that will either emit a
     *           {@link List} containing the equivalent US miles per gallon, kilometers per liter,
     *           and liters per 100 kilometers values (in that order; they will be empty
     *           {@link String}s if there is valid, non-numerical input, such as a leading decimal
     *           point), a null value if the <code>ukmpg</code> parameter is null, or an error if an
     *           {@link Exception} was thrown.
     */
    public static Observable<List<String>> toAll(final String ukmpg, final int decimalPlaces) {
        return Observable.defer(() -> {
            try {
                if (ukmpg == null) {
                    return Observable.just(null);
                }

                int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
                List<String> results = new LinkedList<>();

                if (isNumeric(ukmpg)) {
                    results.add(toUSMilesPerGallon(ukmpg, roundingLength));
                    results.add(toKilometersPerLiter(ukmpg, roundingLength));
                    results.add(toLitersPer100Kilometers(ukmpg, roundingLength));
                } else if (ukmpg.equals(".") || ukmpg.equals("")) {
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
     * Takes in the UK miles per gallon value as a {@link String} and converts it to US miles per
     * gallon.
     *
     * @param ukmpg             The UK miles per gallon value as a {@link String}. Should not be
     *                           null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent US miles per gallon value as a {@link String}, or null if the
     *           <code>ukmpg</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toUSMilesPerGallon(String ukmpg, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (ukmpg == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(ukmpg);
        if (fuel.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            fuel = fuel.multiply(new BigDecimal("0.83267418460479"))
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
     * Takes in the UK miles per gallon value as a {@link String} and converts it to kilometers per
     * liter
     *
     * @param ukmpg             The UK miles per gallon value as a {@link String}. Should not be
     *                           null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent kilometers per liter value as a {@link String}, or null if the
     *           <code>ukmpg</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toKilometersPerLiter(String ukmpg, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (ukmpg == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(ukmpg);
        if (fuel.compareTo(BigDecimal.ZERO) >= 0) {
            // Work around for BigDecimal bug not returning exactly 0 when the answer is 0
            // This bug is fixed in Java 8, but Android still uses Java 7 if i'm not mistaken
            fuel = fuel.multiply(new BigDecimal("0.35400618997453"))
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
     * Takes in the UK miles per gallon value as a {@link String} and converts it to liters per 100
     * kilometers.
     *
     * @param ukmpg             The UK miles per gallon value as a {@link String}. Should not be
     *                           null.
     * @param decimalPlaces     The number of decimal places to round to. If below zero, will be
     *                           treated as if it was zero.
     *
     * @return  The equivalent liters per 100 kilometers value as a {@link String}, or null if the
     *           <code>ukmpg</code> parameter is null.
     *
     * @throws  NumberFormatException       Thrown if the input {@link String} is not a valid
     *                                       number.
     * @throws  ValueBelowZeroException     Thrown if the input {@link String} is below zero.
     */
    public static String toLitersPer100Kilometers(String ukmpg, int decimalPlaces)
            throws NumberFormatException, ValueBelowZeroException {
        if (ukmpg == null) {
            return null;
        }

        int roundingLength = (decimalPlaces < 0) ? 0 : decimalPlaces;
        BigDecimal fuel = new BigDecimal(ukmpg);
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

    // endregion
}
