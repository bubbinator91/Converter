package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.interfaces.view.IAccelerationView;

import java.util.List;

public class MockAccelerationView implements IAccelerationView {

    public String cmpssValue = null, fpssValue = null, mpssValue = null, sgValue = null;
    public boolean cmpssError = false, fpssError = false, mpssError = false, sgError = false;

    @Override
    public void displayConversionFromCentimetersPerSecondSquaredResults(List<String> results) {
        fpssValue = results.get(0);
        mpssValue = results.get(1);
        sgValue = results.get(2);
        cmpssError = false;
    }

    @Override
    public void displayConversionFromCentimetersPerSecondSquaredError(Throwable error) {
        fpssValue = null;
        mpssValue = null;
        sgValue = null;
        cmpssError = true;
    }

    @Override
    public void displayConversionFromFeetPerSecondSquaredResults(List<String> results) {
        cmpssValue = results.get(0);
        mpssValue = results.get(1);
        sgValue = results.get(2);
        fpssError = false;
    }

    @Override
    public void displayConversionFromFeetPerSecondSquaredError(Throwable error) {
        cmpssValue = null;
        mpssValue = null;
        sgValue = null;
        fpssError = true;
    }

    @Override
    public void displayConversionFromMetersPerSecondSquaredResults(List<String> results) {
        cmpssValue = results.get(0);
        fpssValue = results.get(1);
        sgValue = results.get(2);
        mpssError = false;
    }

    @Override
    public void displayConversionFromMetersPerSecondSquaredError(Throwable error) {
        cmpssValue = null;
        fpssValue = null;
        sgValue = null;
        mpssError = true;
    }

    @Override
    public void displayConversionFromStandardGravityResults(List<String> results) {
        cmpssValue = results.get(0);
        fpssValue = results.get(1);
        mpssValue = results.get(2);
        sgError = false;
    }

    @Override
    public void displayConversionFromStandardGravityError(Throwable error) {
        cmpssValue = null;
        fpssValue = null;
        mpssValue = null;
        sgError = true;
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
        cmpssValue = null;
        fpssValue = null;
        mpssValue = null;
        sgValue = null;

        cmpssError = false;
        fpssError = false;
        mpssError = false;
        sgError = false;
    }
}
