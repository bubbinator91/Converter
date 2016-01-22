package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.interfaces.view.IAccelerationView;

import java.util.List;

public class MockAccelerationView implements IAccelerationView {

    public String mCmpssValue = null, mFpssValue = null, mMpssValue = null, mSgValue = null;
    public boolean mCmpssError = false, mFpssError = false, mMpssError = false, mSgError = false;

    @Override
    public void displayConversionFromCentimetersPerSecondSquaredResults(List<String> results) {
        mFpssValue = results.get(0);
        mMpssValue = results.get(1);
        mSgValue = results.get(2);
        mCmpssError = false;
    }

    @Override
    public void displayConversionFromCentimetersPerSecondSquaredError(Throwable error) {
        mFpssValue = null;
        mMpssValue = null;
        mSgValue = null;
        mCmpssError = true;
    }

    @Override
    public void displayConversionFromFeetPerSecondSquaredResults(List<String> results) {
        mCmpssValue = results.get(0);
        mMpssValue = results.get(1);
        mSgValue = results.get(2);
        mFpssError = false;
    }

    @Override
    public void displayConversionFromFeetPerSecondSquaredError(Throwable error) {
        mCmpssValue = null;
        mMpssValue = null;
        mSgValue = null;
        mFpssError = true;
    }

    @Override
    public void displayConversionFromMetersPerSecondSquaredResults(List<String> results) {
        mCmpssValue = results.get(0);
        mFpssValue = results.get(1);
        mSgValue = results.get(2);
        mMpssError = false;
    }

    @Override
    public void displayConversionFromMetersPerSecondSquaredError(Throwable error) {
        mCmpssValue = null;
        mFpssValue = null;
        mSgValue = null;
        mMpssError = true;
    }

    @Override
    public void displayConversionFromStandardGravityResults(List<String> results) {
        mCmpssValue = results.get(0);
        mFpssValue = results.get(1);
        mMpssValue = results.get(2);
        mSgError = false;
    }

    @Override
    public void displayConversionFromStandardGravityError(Throwable error) {
        mCmpssValue = null;
        mFpssValue = null;
        mMpssValue = null;
        mSgError = true;
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
        mCmpssValue = null;
        mFpssValue = null;
        mMpssValue = null;
        mSgValue = null;

        mCmpssError = false;
        mFpssError = false;
        mMpssError = false;
        mSgError = false;
    }
}