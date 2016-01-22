package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.interfaces.view.ISpeedView;

import java.util.List;

public class MockSpeedView implements ISpeedView {

    public String mFpsValue = null, mKnotValue = null, mKphValue = null, mMpsValue = null,
            mMphValue = null;
    public boolean mFpsError = false, mKnotError = false, mKphError = false, mMpsError = false,
            mMphError = false;

    @Override
    public void displayConversionFromFeetPerSecondResults(List<String> results) {
        mKnotValue = results.get(0);
        mKphValue = results.get(1);
        mMpsValue = results.get(2);
        mMphValue = results.get(3);
        mFpsError = false;
    }

    @Override
    public void displayConversionFromFeetPerSecondError(Throwable error) {
        mKnotValue = null;
        mKphValue = null;
        mMpsValue = null;
        mMphValue = null;
        mFpsError = true;
    }

    @Override
    public void displayConversionFromKnotsResults(List<String> results) {
        mFpsValue = results.get(0);
        mKphValue = results.get(1);
        mMpsValue = results.get(2);
        mMphValue = results.get(3);
        mKnotError = false;
    }

    @Override
    public void displayConversionFromKnotsError(Throwable error) {
        mFpsValue = null;
        mKphValue = null;
        mMpsValue = null;
        mMphValue = null;
        mKnotError = true;
    }

    @Override
    public void displayConversionFromKilometersPerHourResults(List<String> results) {
        mFpsValue = results.get(0);
        mKnotValue = results.get(1);
        mMpsValue = results.get(2);
        mMphValue = results.get(3);
        mKphError = false;
    }

    @Override
    public void displayConversionFromKilometersPerHourError(Throwable error) {
        mFpsValue = null;
        mKnotValue = null;
        mMpsValue = null;
        mMphValue = null;
        mKphError = true;
    }

    @Override
    public void displayConversionFromMetersPerSecondResults(List<String> results) {
        mFpsValue = results.get(0);
        mKnotValue = results.get(1);
        mKphValue = results.get(2);
        mMphValue = results.get(3);
        mMpsError = false;
    }

    @Override
    public void displayConversionFromMetersPerSecondError(Throwable error) {
        mFpsValue = null;
        mKnotValue = null;
        mKphValue = null;
        mMphValue = null;
        mMpsError = true;
    }

    @Override
    public void displayConversionFromMilesPerHourResults(List<String> results) {
        mFpsValue = results.get(0);
        mKnotValue = results.get(1);
        mKphValue = results.get(2);
        mMpsValue = results.get(3);
        mMphError = false;
    }

    @Override
    public void displayConversionFromMilesPerHourError(Throwable error) {
        mFpsValue = null;
        mKnotValue = null;
        mKphValue = null;
        mMpsValue = null;
        mMphError = true;
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
        mFpsValue = null;
        mKnotValue = null;
        mKphValue = null;
        mMpsValue = null;
        mMphValue = null;

        mFpsError = false;
        mKnotError = false;
        mKphError = false;
        mMpsError = false;
        mMphError = false;
    }
}
