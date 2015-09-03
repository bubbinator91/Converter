package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.ui.interfaces.acceleration.IAccelerationView;

import java.util.List;

public class MockAccelerationView implements IAccelerationView {
    public String mCmpssValue = null, mFpssValue = null, mMpssValue = null, mSgValue = null;
    public int mCmpssError = -1, mFpssError = -1, mMpssError = -1, mSgError = -1;

    @Override
    public void displayConversionFromCentimetersPerSecondSquared(List<String> results, int errorCode) {
        if (results != null) {
            mFpssValue = results.get(0);
            mMpssValue = results.get(1);
            mSgValue = results.get(2);
        } else {
            mFpssValue = null;
            mMpssValue = null;
            mSgValue = null;
        }

        mCmpssError = errorCode;
    }

    @Override
    public void displayConversionFromFeetPerSecondSquared(List<String> results, int errorCode) {
        if (results != null) {
            mCmpssValue = results.get(0);
            mMpssValue = results.get(1);
            mSgValue = results.get(2);
        } else {
            mCmpssValue = null;
            mMpssValue = null;
            mSgValue = null;
        }

        mFpssError = errorCode;
    }

    @Override
    public void displayConversionFromMetersPerSecondSquared(List<String> results, int errorCode) {
        if (results != null) {
            mCmpssValue = results.get(0);
            mFpssValue = results.get(1);
            mSgValue = results.get(2);
        } else {
            mCmpssValue = null;
            mFpssValue = null;
            mSgValue = null;
        }

        mMpssError = errorCode;
    }

    @Override
    public void displayConversionFromStandardGravity(List<String> results, int errorCode) {
        if (results != null) {
            mCmpssValue = results.get(0);
            mFpssValue = results.get(1);
            mMpssValue = results.get(2);
        } else {
            mCmpssValue = null;
            mFpssValue = null;
            mMpssValue = null;
        }

        mSgError = errorCode;
    }

    @Override
    public void addTextChangedListeners(String callingClassName) {
        // No relevant implementation for testing
    }

    @Override
    public void removeTextChangedListeners(String callingClassName) {
        // No relevant implementation for testing
    }
}
