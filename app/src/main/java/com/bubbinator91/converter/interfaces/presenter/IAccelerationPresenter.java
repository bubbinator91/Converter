package com.bubbinator91.converter.interfaces.presenter;

import com.bubbinator91.converter.interfaces.view.IAccelerationView;
import com.bubbinator91.converter.interfaces.base.IConverterPresenter;
import com.bubbinator91.converter.views.fragments.AccelerationFragment;

/**
 * Presenter interface for the presenter of {@link AccelerationFragment}
 */
public interface IAccelerationPresenter extends IConverterPresenter<IAccelerationView> {
    void getConversionFromCentimetersPerSecondSquared(String cmpss, int decimalPlaces);

    void getConversionFromFeetPerSecondSquared(String fpss, int decimalPlaces);

    void getConversionFromMetersPerSecondSquared(String mpss, int decimalPlaces);

    void getConversionFromStandardGravity(String sg, int decimalPlaces);
}
