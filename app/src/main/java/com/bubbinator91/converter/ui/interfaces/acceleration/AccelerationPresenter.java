package com.bubbinator91.converter.ui.interfaces.acceleration;

import com.bubbinator91.converter.ui.interfaces.base.ConverterPresenter;

/**
 * Presenter interface for the presenter of
 * {@link com.bubbinator91.converter.ui.fragments.AccelerationFragment}
 */
public interface AccelerationPresenter extends ConverterPresenter<AccelerationView> {
    void getConversionFromCentimetersPerSecondSquaredResults(String cmpss, int decimalPlaces);

    void getConversionFromFeetPerSecondSquaredResults(String fpss, int decimalPlaces);

    void getConversionFromMetersPerSecondSquaredResults(String mpss, int decimalPlaces);

    void getConversionFromStandardGravity(String sg, int decimalPlaces);
}
