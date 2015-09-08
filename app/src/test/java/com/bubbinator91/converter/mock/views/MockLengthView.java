package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.ui.interfaces.length.ILengthView;

import java.util.List;

public class MockLengthView implements ILengthView {

    public String mInchValue = null, mFootValue = null, mYardValue = null, mMileValue = null,
            mMillimeterValue = null, mCentimeterValue = null, mMeterValue = null,
            mKilometerValue = null;
    public int mInchError = -1, mFootError = -1, mYardError = -1, mMileError = -1,
            mMillimeterError = -1, mCentimeterError = -1, mMeterError = -1, mKilometerError = -1;

    @Override
    public void displayConversionFromInchesResults(List<String> results, int errorCode) {
        if (results != null) {
            mFootValue = results.get(0);
            mYardValue = results.get(1);
            mMileValue = results.get(2);
            mMillimeterValue = results.get(3);
            mCentimeterValue = results.get(4);
            mMeterValue = results.get(5);
            mKilometerValue = results.get(6);
        } else {
            mFootValue = null;
            mYardValue = null;
            mMileValue = null;
            mMillimeterValue = null;
            mCentimeterValue = null;
            mMeterValue = null;
            mKilometerValue = null;
        }

        mInchError = errorCode;
    }

    @Override
    public void displayConversionFromFeetResults(List<String> results, int errorCode) {
        if (results != null) {
            mInchValue = results.get(0);
            mYardValue = results.get(1);
            mMileValue = results.get(2);
            mMillimeterValue = results.get(3);
            mCentimeterValue = results.get(4);
            mMeterValue = results.get(5);
            mKilometerValue = results.get(6);
        } else {
            mInchValue = null;
            mYardValue = null;
            mMileValue = null;
            mMillimeterValue = null;
            mCentimeterValue = null;
            mMeterValue = null;
            mKilometerValue = null;
        }

        mFootError = errorCode;
    }

    @Override
    public void displayConversionFromYardsResults(List<String> results, int errorCode) {
        if (results != null) {
            mInchValue = results.get(0);
            mFootValue = results.get(1);
            mMileValue = results.get(2);
            mMillimeterValue = results.get(3);
            mCentimeterValue = results.get(4);
            mMeterValue = results.get(5);
            mKilometerValue = results.get(6);
        } else {
            mInchValue = null;
            mFootValue = null;
            mMileValue = null;
            mMillimeterValue = null;
            mCentimeterValue = null;
            mMeterValue = null;
            mKilometerValue = null;
        }

        mYardError = errorCode;
    }

    @Override
    public void displayConversionFromMilesResults(List<String> results, int errorCode) {
        if (results != null) {
            mInchValue = results.get(0);
            mFootValue = results.get(1);
            mYardValue = results.get(2);
            mMillimeterValue = results.get(3);
            mCentimeterValue = results.get(4);
            mMeterValue = results.get(5);
            mKilometerValue = results.get(6);
        } else {
            mInchValue = null;
            mFootValue = null;
            mYardValue = null;
            mMillimeterValue = null;
            mCentimeterValue = null;
            mMeterValue = null;
            mKilometerValue = null;
        }

        mMileError = errorCode;
    }

    @Override
    public void displayConversionFromMillimetersResults(List<String> results, int errorCode) {
        if (results != null) {
            mInchValue = results.get(0);
            mFootValue = results.get(1);
            mYardValue = results.get(2);
            mMileValue = results.get(3);
            mCentimeterValue = results.get(4);
            mMeterValue = results.get(5);
            mKilometerValue = results.get(6);
        } else {
            mInchValue = null;
            mFootValue = null;
            mYardValue = null;
            mMileValue = null;
            mCentimeterValue = null;
            mMeterValue = null;
            mKilometerValue = null;
        }

        mMillimeterError = errorCode;
    }

    @Override
    public void displayConversionFromCentimetersResults(List<String> results, int errorCode) {
        if (results != null) {
            mInchValue = results.get(0);
            mFootValue = results.get(1);
            mYardValue = results.get(2);
            mMileValue = results.get(3);
            mMillimeterValue = results.get(4);
            mMeterValue = results.get(5);
            mKilometerValue = results.get(6);
        } else {
            mInchValue = null;
            mFootValue = null;
            mYardValue = null;
            mMileValue = null;
            mMillimeterValue = null;
            mMeterValue = null;
            mKilometerValue = null;
        }

        mCentimeterError = errorCode;
    }

    @Override
    public void displayConversionFromMetersResults(List<String> results, int errorCode) {
        if (results != null) {
            mInchValue = results.get(0);
            mFootValue = results.get(1);
            mYardValue = results.get(2);
            mMileValue = results.get(3);
            mMillimeterValue = results.get(4);
            mCentimeterValue = results.get(5);
            mKilometerValue = results.get(6);
        } else {
            mInchValue = null;
            mFootValue = null;
            mYardValue = null;
            mMileValue = null;
            mMillimeterValue = null;
            mCentimeterValue = null;
            mKilometerValue = null;
        }

        mMeterError = errorCode;
    }

    @Override
    public void displayConversionFromKilometersResults(List<String> results, int errorCode) {
        if (results != null) {
            mInchValue = results.get(0);
            mFootValue = results.get(1);
            mYardValue = results.get(2);
            mMileValue = results.get(3);
            mMillimeterValue = results.get(4);
            mCentimeterValue = results.get(5);
            mMeterValue = results.get(6);
        } else {
            mInchValue = null;
            mFootValue = null;
            mYardValue = null;
            mMileValue = null;
            mMillimeterValue = null;
            mCentimeterValue = null;
            mMeterValue = null;
        }

        mKilometerError = errorCode;
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
        mInchValue = null;
        mFootValue = null;
        mYardValue = null;
        mMileValue = null;
        mMillimeterValue = null;
        mCentimeterValue = null;
        mMeterValue = null;
        mKilometerValue = null;

        mInchError = -1;
        mFootError = -1;
        mYardError = -1;
        mMileError = -1;
        mMillimeterError = -1;
        mCentimeterError = -1;
        mMeterError = -1;
        mKilometerError = -1;
    }
}
