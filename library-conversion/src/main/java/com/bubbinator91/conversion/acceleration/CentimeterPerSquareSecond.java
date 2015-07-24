package com.bubbinator91.conversion.acceleration;

import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.conversion.util.Tuple;
import com.bubbinator91.conversion.util.Unit;

import java.util.List;

/**
 * Handles the conversion from centimeter per second^2 (gal or cm/s^2) to other units of acceleration
 */
public class CentimeterPerSquareSecond extends Unit {
    private static final String TAG = CentimeterPerSquareSecond.class.getSimpleName();

    public static Tuple<List<String>, ConversionErrorCodes> toAll(String cmpss, int decimalPlaces) {
        if (cmpss == null) {
            return null;
        }

        return null;
    }
}
