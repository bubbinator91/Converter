package com.bubbinator91.converter.interfaces.presenter;

import com.bubbinator91.converter.interfaces.base.IConverterPresenter;
import com.bubbinator91.converter.interfaces.view.ITemperatureView;

/**
 * Presenter interface for the presenter of
 * {@link com.bubbinator91.converter.ui.fragments.TemperatureFragment}
 */
public interface ITemperaturePresenter extends IConverterPresenter<ITemperatureView> {
    void getConversionFromCelsius(String celsius, int decimalPlaces);

    void getConversionFromFahrenheit(String fahrenheit, int decimalPlaces);

    void getConversionFromKelvin(String kelvin, int decimalPlaces);
}
