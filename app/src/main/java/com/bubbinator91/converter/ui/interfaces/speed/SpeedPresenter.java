package com.bubbinator91.converter.ui.interfaces.speed;

import com.bubbinator91.converter.ui.interfaces.base.ConverterPresenter;

/**
 * Presenter interface for the presenter of
 * {@link com.bubbinator91.converter.ui.fragments.SpeedFragment}
 */
public interface SpeedPresenter extends ConverterPresenter<SpeedView> {
    void getConversionFromFeetPerSecondResults(String fps, int decimalPlaces);

    void getConversionFromKilometersPerHourResults(String kph, int decimalPlaces);

    void getConversionFromKnotsResults(String knots, int decimalPlaces);

    void getConversionFromMetersPerSecondResults(String mps, int decimalPlaces);

    void getConversionFromMilesPerHourResults(String mph, int decimalPlaces);
}
