package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.ui.interfaces.temperature.ITemperatureView;

import java.util.List;

public class MockTemperatureView implements ITemperatureView {

    public String mCelsiusValue = null, mFahrenheitValue = null, mKelvinValue = null;
    public int mCelsiusError = -1, mFahrenheitError = -1, mKelvinError = -1;

    @Override
    public void displayConversionFromCelsiusResults(List<String> results, int errorCode) {
        if (results != null) {
            mFahrenheitValue = results.get(0);
            mKelvinValue = results.get(1);
        } else {
            mFahrenheitValue = null;
            mKelvinValue = null;
        }

        mCelsiusError = errorCode;
    }

    @Override
    public void displayConversionFromFahrenheitResults(List<String> results, int errorCode) {
        if (results != null) {
            mCelsiusValue = results.get(0);
            mKelvinValue = results.get(1);
        } else {
            mCelsiusValue = null;
            mKelvinValue = null;
        }

        mFahrenheitError = errorCode;
    }

    @Override
    public void displayConversionFromKelvinResults(List<String> results, int errorCode) {
        if (results != null) {
            mCelsiusValue = results.get(0);
            mFahrenheitValue = results.get(1);
        } else {
            mCelsiusValue = null;
            mFahrenheitValue = null;
        }

        mKelvinError = errorCode;
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

        mCelsiusError = -1;
        mFahrenheitError = -1;
        mKelvinError = -1;
    }
}
