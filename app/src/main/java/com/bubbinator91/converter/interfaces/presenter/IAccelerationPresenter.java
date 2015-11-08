package com.bubbinator91.converter.interfaces.presenter;

import com.bubbinator91.converter.interfaces.view.IAccelerationView;
import com.bubbinator91.converter.interfaces.base.IConverterPresenter;

/**
 * Presenter interface for the presenter of
 * {@link com.bubbinator91.converter.ui.fragments.AccelerationFragment}
 */
public interface IAccelerationPresenter extends IConverterPresenter<IAccelerationView> {
    void getConversionFromCentimetersPerSecondSquaredResults(String cmpss, int decimalPlaces);

    void getConversionFromFeetPerSecondSquaredResults(String fpss, int decimalPlaces);

    void getConversionFromMetersPerSecondSquaredResults(String mpss, int decimalPlaces);

    void getConversionFromStandardGravity(String sg, int decimalPlaces);
}
