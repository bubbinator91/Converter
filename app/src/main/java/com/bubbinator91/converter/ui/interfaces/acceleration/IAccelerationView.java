package com.bubbinator91.converter.ui.interfaces.acceleration;

import com.bubbinator91.converter.ui.interfaces.base.IConverterView;

import java.util.List;

/**
 * View interface for the {@link com.bubbinator91.converter.ui.fragments.AccelerationFragment}
 */
public interface IAccelerationView extends IConverterView {
    void displayConversionFromCentimetersPerSecondSquared(List<String> results, int errorCode);

    void displayConversionFromFeetPerSecondSquared(List<String> results, int errorCode);

    void displayConversionFromMetersPerSecondSquared(List<String> results, int errorCode);

    void displayConversionFromStandardGravity(List<String> results, int errorCode);
}
