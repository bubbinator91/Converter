package com.bubbinator91.converter.interfaces.view;

import com.bubbinator91.converter.interfaces.base.IConverterView;
import com.bubbinator91.converter.views.fragments.SpeedFragment;

import java.util.List;

/**
 * View interface for the {@link SpeedFragment}
 */
public interface ISpeedView extends IConverterView {
    void displayConversionFromFeetPerSecondResults(List<String> results);

    void displayConversionFromFeetPerSecondError(Throwable error);

    void displayConversionFromKilometersPerHourResults(List<String> results);

    void displayConversionFromKilometersPerHourError(Throwable error);

    void displayConversionFromKnotsResults(List<String> results);

    void displayConversionFromKnotsError(Throwable error);

    void displayConversionFromMetersPerSecondResults(List<String> results);

    void displayConversionFromMetersPerSecondError(Throwable error);

    void displayConversionFromMilesPerHourResults(List<String> results);

    void displayConversionFromMilesPerHourError(Throwable error);
}
