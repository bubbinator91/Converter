package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.interfaces.view.ISpeedView;

import java.util.List;

public class MockSpeedView implements ISpeedView {

    public String fpsValue = null, knotValue = null, kphValue = null, mpsValue = null,
            mphValue = null;
    public boolean fpsError = false, knotError = false, kphError = false, mpsError = false,
            mphError = false;

    @Override
    public void displayConversionFromFeetPerSecondResults(List<String> results) {
        knotValue = results.get(0);
        kphValue = results.get(1);
        mpsValue = results.get(2);
        mphValue = results.get(3);
        fpsError = false;
    }

    @Override
    public void displayConversionFromFeetPerSecondError(Throwable error) {
        knotValue = null;
        kphValue = null;
        mpsValue = null;
        mphValue = null;
        fpsError = true;
    }

    @Override
    public void displayConversionFromKnotsResults(List<String> results) {
        fpsValue = results.get(0);
        kphValue = results.get(1);
        mpsValue = results.get(2);
        mphValue = results.get(3);
        knotError = false;
    }

    @Override
    public void displayConversionFromKnotsError(Throwable error) {
        fpsValue = null;
        kphValue = null;
        mpsValue = null;
        mphValue = null;
        knotError = true;
    }

    @Override
    public void displayConversionFromKilometersPerHourResults(List<String> results) {
        fpsValue = results.get(0);
        knotValue = results.get(1);
        mpsValue = results.get(2);
        mphValue = results.get(3);
        kphError = false;
    }

    @Override
    public void displayConversionFromKilometersPerHourError(Throwable error) {
        fpsValue = null;
        knotValue = null;
        mpsValue = null;
        mphValue = null;
        kphError = true;
    }

    @Override
    public void displayConversionFromMetersPerSecondResults(List<String> results) {
        fpsValue = results.get(0);
        knotValue = results.get(1);
        kphValue = results.get(2);
        mphValue = results.get(3);
        mpsError = false;
    }

    @Override
    public void displayConversionFromMetersPerSecondError(Throwable error) {
        fpsValue = null;
        knotValue = null;
        kphValue = null;
        mphValue = null;
        mpsError = true;
    }

    @Override
    public void displayConversionFromMilesPerHourResults(List<String> results) {
        fpsValue = results.get(0);
        knotValue = results.get(1);
        kphValue = results.get(2);
        mpsValue = results.get(3);
        mphError = false;
    }

    @Override
    public void displayConversionFromMilesPerHourError(Throwable error) {
        fpsValue = null;
        knotValue = null;
        kphValue = null;
        mpsValue = null;
        mphError = true;
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
        fpsValue = null;
        knotValue = null;
        kphValue = null;
        mpsValue = null;
        mphValue = null;

        fpsError = false;
        knotError = false;
        kphError = false;
        mpsError = false;
        mphError = false;
    }
}
