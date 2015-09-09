package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.ui.interfaces.temperature.ITemperatureView;

import java.util.List;

public class MockTemperatureView implements ITemperatureView {

    public String mCelsiusValue = null, mFahrenheitValue = null, mKelvinValue = null;
    public boolean mCelsiusError = false, mFahrenheitError = false, mKelvinError = false;

    @Override
    public void displayConversionFromCelsiusResults(List<String> results) {
        mFahrenheitValue = results.get(0);
        mKelvinValue = results.get(1);
        mCelsiusError = false;
    }

    @Override
    public void displayConversionFromCelsiusError(Throwable error) {
        mFahrenheitValue = null;
        mKelvinValue = null;
        mCelsiusError = true;
    }

    @Override
    public void displayConversionFromFahrenheitResults(List<String> results) {
        mCelsiusValue = results.get(0);
        mKelvinValue = results.get(1);
        mFahrenheitError = false;
    }

    @Override
    public void displayConversionFromFahrenheitError(Throwable error) {
        mCelsiusValue = null;
        mKelvinValue = null;
        mFahrenheitError = true;
    }

    @Override
    public void displayConversionFromKelvinResults(List<String> results) {
        mCelsiusValue = results.get(0);
        mFahrenheitValue = results.get(1);
        mKelvinError = false;
    }

    @Override
    public void displayConversionFromKelvinError(Throwable error) {
        mCelsiusValue = null;
        mFahrenheitValue = null;
        mKelvinError = true;
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
        mCelsiusValue = null;
        mFahrenheitValue = null;
        mKelvinValue = null;

        mCelsiusError = false;
        mFahrenheitError = false;
        mKelvinError = false;
    }
}
