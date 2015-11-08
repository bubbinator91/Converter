package com.bubbinator91.converter.interfaces.view;

import com.bubbinator91.converter.interfaces.base.IConverterView;

import java.util.List;

/**
 * View interface for the {@link com.bubbinator91.converter.ui.fragments.TemperatureFragment}
 */
public interface ITemperatureView extends IConverterView {
    void displayConversionFromCelsiusResults(List<String> results);

    void displayConversionFromCelsiusError(Throwable error);

    void displayConversionFromFahrenheitResults(List<String> results);

    void displayConversionFromFahrenheitError(Throwable error);

    void displayConversionFromKelvinResults(List<String> results);

    void displayConversionFromKelvinError(Throwable error);
}
