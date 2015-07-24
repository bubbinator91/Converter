package com.bubbinator91.conversion.acceleration;

import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.conversion.util.Tuple;
import com.bubbinator91.conversion.util.Unit;

import java.util.List;

/**
 * Handles the conversion from standard gravity (g sub n) to other units of acceleration
 */
public class StandardGravity extends Unit {
    private static final String TAG = StandardGravity.class.getSimpleName();

    public static Tuple<List<String>, ConversionErrorCodes> toAll(String sg, int decimalPlaces) {
        if (sg == null) {
            return null;
        }

        return null;
    }
}
