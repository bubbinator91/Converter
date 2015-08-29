package com.bubbinator91.converter.ui.interfaces.acceleration;

import com.bubbinator91.converter.ui.interfaces.base.ConverterView;

import java.util.List;

/**
 * View interface for the {@link com.bubbinator91.converter.ui.fragments.AccelerationFragment}
 */
public interface AccelerationView extends ConverterView {
    void displayConversionFromCentimetersPerSecondSquared(List<String> results, int errorCode);

    void displayConversionFromFeetPerSecondSquared(List<String> results, int errorCode);

    void displayConversionFromMetersPerSecondSquared(List<String> results, int errorCode);

    void displayConversionFromStandardGravity(List<String> results, int errorCode);
}
