package com.bubbinator91.converter.ui.interfaces.speed;

import com.bubbinator91.converter.ui.interfaces.base.ConverterView;

import java.util.List;

/**
 * View interface for the {@link com.bubbinator91.converter.ui.fragments.SpeedFragment}
 */
public interface SpeedView extends ConverterView {
    void displayConversionFromFeetPerSecondResults(List<String> results, int errorCode);

    void displayConversionFromKilometersPerHourResults(List<String> results, int errorCode);

    void displayConversionFromKnotsResults(List<String> results, int errorCode);

    void displayConversionFromMetersPerSecondResults(List<String> results, int errorCode);

    void displayConversionFromMilesPerHourResults(List<String> results, int errorCode);
}
