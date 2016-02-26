package com.bubbinator91.converter.interfaces.view;

import com.bubbinator91.converter.interfaces.base.IConverterView;
import com.bubbinator91.converter.views.fragments.AccelerationFragment;

import java.util.List;

/**
 * View interface for the {@link AccelerationFragment}
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
