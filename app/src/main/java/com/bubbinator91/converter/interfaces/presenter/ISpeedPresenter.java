package com.bubbinator91.converter.interfaces.presenter;

import com.bubbinator91.converter.interfaces.base.IConverterPresenter;
import com.bubbinator91.converter.interfaces.view.ISpeedView;
import com.bubbinator91.converter.views.fragments.SpeedFragment;

/**
 * Presenter interface for the presenter of {@link SpeedFragment}
 */
public interface ISpeedPresenter extends IConverterPresenter<ISpeedView> {
    void getConversionFromFeetPerSecond(String fps, int decimalPlaces);

    void getConversionFromKilometersPerHour(String kph, int decimalPlaces);

    void getConversionFromKnots(String knots, int decimalPlaces);

    void getConversionFromMetersPerSecond(String mps, int decimalPlaces);

    void getConversionFromMilesPerHour(String mph, int decimalPlaces);
}
