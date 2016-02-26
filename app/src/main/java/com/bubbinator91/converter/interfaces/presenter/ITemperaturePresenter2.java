package com.bubbinator91.converter.interfaces.presenter;

import com.bubbinator91.converter.interfaces.base.IPresenter2;
import com.bubbinator91.converter.interfaces.view.ITemperatureView2;

/**
 * Presenter interface for the presenter of
 * {@link com.bubbinator91.converter.views.fragments.TemperatureFragment2}
 */
public interface ITemperaturePresenter2 extends IPresenter2<ITemperatureView2> {
    void afterCelsiusTextChanged(String celsius, int decimalPlaces);

    void afterFahrenheitTextChanged(String fahrenheit, int decimalPlaces);

    void afterKelvinTextChanged(String kelvin, int decimalPlaces);
}
