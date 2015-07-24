package com.bubbinator91.conversion.acceleration;

import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.conversion.util.Tuple;
import com.bubbinator91.conversion.util.Unit;

import java.util.List;

/**
 * Handles the conversion from meter per second^2 (m/s^2) to other units of acceleration
 */
public class MeterPerSquareSecond extends Unit {
    private static final String TAG = MeterPerSquareSecond.class.getSimpleName();

    public static Tuple<List<String>, ConversionErrorCodes> toAll(String mpss, int decimalPlaces) {
        if (mpss == null) {
            return null;
        }

        return null;
    }
}
