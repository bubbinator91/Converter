package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.interfaces.view.ITemperatureView;

import java.util.List;

public class MockTemperatureView implements ITemperatureView {

    public String celsiusValue = null, fahrenheitValue = null, kelvinValue = null;
    public boolean celsiusError = false, fahrenheitError = false, kelvinError = false;

    @Override
    public void displayConversionFromCelsiusResults(List<String> results) {
        fahrenheitValue = results.get(0);
        kelvinValue = results.get(1);
        celsiusError = false;
    }

    @Override
    public void displayConversionFromCelsiusError(Throwable error) {
        fahrenheitValue = null;
        kelvinValue = null;
        celsiusError = true;
    }

    @Override
    public void displayConversionFromFahrenheitResults(List<String> results) {
        celsiusValue = results.get(0);
        kelvinValue = results.get(1);
        fahrenheitError = false;
    }

    @Override
    public void displayConversionFromFahrenheitError(Throwable error) {
        celsiusValue = null;
        kelvinValue = null;
        fahrenheitError = true;
    }

    @Override
    public void displayConversionFromKelvinResults(List<String> results) {
        celsiusValue = results.get(0);
        fahrenheitValue = results.get(1);
        kelvinError = false;
    }

    @Override
    public void displayConversionFromKelvinError(Throwable error) {
        celsiusValue = null;
        fahrenheitValue = null;
        kelvinError = true;
    }

    @Override
    public void addTextChangedListeners(String callingClassName) {
        // No relevant implementation for testing
    }

    @Override
    public void removeTextChangedListeners(String callingClassName) {
        // No relevant implementation for testing
    }

    public void resetValues() {
        celsiusValue = null;
        fahrenheitValue = null;
        kelvinValue = null;

        celsiusError = false;
        fahrenheitError = false;
        kelvinError = false;
    }
}
