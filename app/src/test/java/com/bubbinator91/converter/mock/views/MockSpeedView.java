package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.interfaces.view.ISpeedView;

import java.util.List;

public class MockSpeedView implements ISpeedView {

    public String mFpsValue = null, mKnotValue = null, mKphValue = null, mMpsValue = null,
            mMphValue = null;
    public int mFpsError = -1, mKnotError = -1, mKphError = -1, mMpsError = -1, mMphError = -1;

    @Override
    public void displayConversionFromFeetPerSecondResults(List<String> results, int errorCode) {
        if (results != null) {
            mKnotValue = results.get(0);
            mKphValue = results.get(1);
            mMpsValue = results.get(2);
            mMphValue = results.get(3);
        } else {
            mKnotValue = null;
            mKphValue = null;
            mMpsValue = null;
            mMphValue = null;
        }

        mFpsError = errorCode;
    }

    @Override
    public void displayConversionFromKnotsResults(List<String> results, int errorCode) {
        if (results != null) {
            mFpsValue = results.get(0);
            mKphValue = results.get(1);
            mMpsValue = results.get(2);
            mMphValue = results.get(3);
        } else {
            mFpsValue = null;
            mKphValue = null;
            mMpsValue = null;
            mMphValue = null;
        }

        mKnotError = errorCode;
    }

    @Override
    public void displayConversionFromKilometersPerHourResults(List<String> results, int errorCode) {
        if (results != null) {
            mFpsValue = results.get(0);
            mKnotValue = results.get(1);
            mMpsValue = results.get(2);
            mMphValue = results.get(3);
        } else {
            mFpsValue = null;
            mKnotValue = null;
            mMpsValue = null;
            mMphValue = null;
        }

        mKphError = errorCode;
    }

    @Override
    public void displayConversionFromMetersPerSecondResults(List<String> results, int errorCode) {
        if (results != null) {
            mFpsValue = results.get(0);
            mKnotValue = results.get(1);
            mKphValue = results.get(2);
            mMphValue = results.get(3);
        } else {
            mFpsValue = null;
            mKnotValue = null;
            mKphValue = null;
            mMphValue = null;
        }

        mMpsError = errorCode;
    }

    @Override
    public void displayConversionFromMilesPerHourResults(List<String> results, int errorCode) {
        if (results != null) {
            mFpsValue = results.get(0);
            mKnotValue = results.get(1);
            mKphValue = results.get(2);
            mMpsValue = results.get(3);
        } else {
            mFpsValue = null;
            mKnotValue = null;
            mKphValue = null;
            mMpsValue = null;
        }

        mMphError = errorCode;
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

        mFpsError = -1;
        mKnotError = -1;
        mKphError = -1;
        mMpsError = -1;
        mMphError = -1;
    }
}
