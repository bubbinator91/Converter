package com.bubbinator91.converter.ui.interfaces.temperature;

import com.bubbinator91.converter.ui.interfaces.base.ConverterView;

import java.util.List;

/**
 * View interface for the {@link com.bubbinator91.converter.ui.fragments.TemperatureFragment}
 */
public interface TemperatureView extends ConverterView {
    void displayConversionFromCelsiusResults(List<String> results, int errorCode);

    void displayConversionFromFahrenheitResults(List<String> results, int errorCode);

    void displayConversionFromKelvinResults(List<String> results, int errorCode);
}
