package com.bubbinator91.converter.interfaces.presenter;

import com.bubbinator91.converter.interfaces.base.IPresenter2;
import com.bubbinator91.converter.interfaces.view.ITemperatureView;
import com.bubbinator91.converter.views.fragments.TemperatureFragment;

/**
 * Presenter interface for the presenter of {@link TemperatureFragment}
 */
public interface ITemperaturePresenter extends IPresenter2<ITemperatureView> {
    void afterCelsiusTextChanged(String celsius, int decimalPlaces);

    void afterFahrenheitTextChanged(String fahrenheit, int decimalPlaces);

    void afterKelvinTextChanged(String kelvin, int decimalPlaces);
}
