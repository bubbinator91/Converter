package com.bubbinator91.converter.ui.interfaces.acceleration;

import com.bubbinator91.converter.ui.interfaces.base.IConverterView;

import java.util.List;

/**
 * View interface for the {@link com.bubbinator91.converter.ui.fragments.AccelerationFragment}
 */
public interface IAccelerationView extends IConverterView {
    void displayConversionFromCentimetersPerSecondSquaredResults(List<String> results);

    void displayConversionFromCentimetersPerSecondSquaredError(Throwable error);

    void displayConversionFromFeetPerSecondSquaredResults(List<String> results);

    void displayConversionFromFeetPerSecondSquaredError(Throwable error);

    void displayConversionFromMetersPerSecondSquaredResults(List<String> results);

    void displayConversionFromMetersPerSecondSquaredError(Throwable error);

    void displayConversionFromStandardGravityResults(List<String> results);

    void displayConversionFromStandardGravityError(Throwable error);
}
