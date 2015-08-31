package com.bubbinator91.converter.ui.interfaces.speed;

import com.bubbinator91.converter.ui.interfaces.base.IConverterPresenter;

/**
 * Presenter interface for the presenter of
 * {@link com.bubbinator91.converter.ui.fragments.SpeedFragment}
 */
public interface ISpeedPresenter extends IConverterPresenter<ISpeedView> {
    void getConversionFromFeetPerSecondResults(String fps, int decimalPlaces);

    void getConversionFromKilometersPerHourResults(String kph, int decimalPlaces);

    void getConversionFromKnotsResults(String knots, int decimalPlaces);

    void getConversionFromMetersPerSecondResults(String mps, int decimalPlaces);

    void getConversionFromMilesPerHourResults(String mph, int decimalPlaces);
}
