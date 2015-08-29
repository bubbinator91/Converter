package com.bubbinator91.converter.ui.interfaces.temperature;

import com.bubbinator91.converter.ui.interfaces.base.ConverterPresenter;

/**
 * Presenter interface for the presenter of
 * {@link com.bubbinator91.converter.ui.fragments.TemperatureFragment}
 */
public interface TemperaturePresenter extends ConverterPresenter<TemperatureView> {
    void getConversionFromCelsiusResults(String celsius, int decimalPlaces);

    void getConversionFromFahrenheitResults(String fahrenheit, int decimalPlaces);

    void getConversionFromKelvinResults(String kelvin, int decimalPlaces);
}
