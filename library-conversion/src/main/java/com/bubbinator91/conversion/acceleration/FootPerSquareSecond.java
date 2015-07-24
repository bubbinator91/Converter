package com.bubbinator91.conversion.acceleration;

import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.conversion.util.Tuple;
import com.bubbinator91.conversion.util.Unit;

import java.util.List;

/**
 * Handles the conversion from foot per second^2 (ft/s^2) to other units of acceleration
 */
public class FootPerSquareSecond extends Unit {
    private static final String TAG = FootPerSquareSecond.class.getSimpleName();

    public static Tuple<List<String>, ConversionErrorCodes> toAll(String fpss, int decimalPlaces) {
        if (fpss == null) {
            return null;
        }

        return null;
    }
}
