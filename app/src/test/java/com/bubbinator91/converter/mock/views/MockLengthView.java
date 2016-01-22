package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.interfaces.view.ILengthView;

import java.util.List;

public class MockLengthView implements ILengthView {

    public String mInchValue = null, mFootValue = null, mYardValue = null, mMileValue = null,
            mMillimeterValue = null, mCentimeterValue = null, mMeterValue = null,
            mKilometerValue = null;
    public boolean mInchError = false, mFootError = false, mYardError = false, mMileError = false,
            mMillimeterError = false, mCentimeterError = false, mMeterError = false,
            mKilometerError = false;

    @Override
    public void displayConversionFromInchesResults(List<String> results) {
        mFootValue = results.get(0);
        mYardValue = results.get(1);
        mMileValue = results.get(2);
        mMillimeterValue = results.get(3);
        mCentimeterValue = results.get(4);
        mMeterValue = results.get(5);
        mKilometerValue = results.get(6);
        mInchError = false;
    }

    @Override
    public void displayConversionFromInchesError(Throwable error) {
        mFootValue = null;
        mYardValue = null;
        mMileValue = null;
        mMillimeterValue = null;
        mCentimeterValue = null;
        mMeterValue = null;
        mKilometerValue = null;
        mInchError = true;
    }

    @Override
    public void displayConversionFromFeetResults(List<String> results) {
        mInchValue = results.get(0);
        mYardValue = results.get(1);
        mMileValue = results.get(2);
        mMillimeterValue = results.get(3);
        mCentimeterValue = results.get(4);
        mMeterValue = results.get(5);
        mKilometerValue = results.get(6);
        mFootError = false;
    }

    @Override
    public void displayConversionFromFeetError(Throwable error) {
        mInchValue = null;
        mYardValue = null;
        mMileValue = null;
        mMillimeterValue = null;
        mCentimeterValue = null;
        mMeterValue = null;
        mKilometerValue = null;
        mFootError = true;
    }

    @Override
    public void displayConversionFromYardsResults(List<String> results) {
        mInchValue = results.get(0);
        mFootValue = results.get(1);
        mMileValue = results.get(2);
        mMillimeterValue = results.get(3);
        mCentimeterValue = results.get(4);
        mMeterValue = results.get(5);
        mKilometerValue = results.get(6);
        mYardError = false;
    }

    @Override
    public void displayConversionFromYardsError(Throwable error) {
        mInchValue = null;
        mFootValue = null;
        mMileValue = null;
        mMillimeterValue = null;
        mCentimeterValue = null;
        mMeterValue = null;
        mKilometerValue = null;
        mYardError = true;
    }

    @Override
    public void displayConversionFromMilesResults(List<String> results) {
        mInchValue = results.get(0);
        mFootValue = results.get(1);
        mYardValue = results.get(2);
        mMillimeterValue = results.get(3);
        mCentimeterValue = results.get(4);
        mMeterValue = results.get(5);
        mKilometerValue = results.get(6);
        mMileError = false;
    }

    @Override
    public void displayConversionFromMilesError(Throwable error) {
        mInchValue = null;
        mFootValue = null;
        mYardValue = null;
        mMillimeterValue = null;
        mCentimeterValue = null;
        mMeterValue = null;
        mKilometerValue = null;
        mMileError = true;
    }

    @Override
    public void displayConversionFromMillimetersResults(List<String> results) {
        mInchValue = results.get(0);
        mFootValue = results.get(1);
        mYardValue = results.get(2);
        mMileValue = results.get(3);
        mCentimeterValue = results.get(4);
        mMeterValue = results.get(5);
        mKilometerValue = results.get(6);
        mMillimeterError = false;
    }

    @Override
    public void displayConversionFromMillimetersError(Throwable error) {
        mInchValue = null;
        mFootValue = null;
        mYardValue = null;
        mMileValue = null;
        mCentimeterValue = null;
        mMeterValue = null;
        mKilometerValue = null;
        mMillimeterError = true;
    }

    @Override
    public void displayConversionFromCentimetersResults(List<String> results) {
        mInchValue = results.get(0);
        mFootValue = results.get(1);
        mYardValue = results.get(2);
        mMileValue = results.get(3);
        mMillimeterValue = results.get(4);
        mMeterValue = results.get(5);
        mKilometerValue = results.get(6);
        mCentimeterError = false;
    }

    @Override
    public void displayConversionFromCentimetersError(Throwable error) {
        mInchValue = null;
        mFootValue = null;
        mYardValue = null;
        mMileValue = null;
        mMillimeterValue = null;
        mMeterValue = null;
        mKilometerValue = null;
        mCentimeterError = true;
    }

    @Override
    public void displayConversionFromMetersResults(List<String> results) {
        mInchValue = results.get(0);
        mFootValue = results.get(1);
        mYardValue = results.get(2);
        mMileValue = results.get(3);
        mMillimeterValue = results.get(4);
        mCentimeterValue = results.get(5);
        mKilometerValue = results.get(6);
        mMeterError = false;
    }

    @Override
    public void displayConversionFromMetersError(Throwable error) {
        mInchValue = null;
        mFootValue = null;
        mYardValue = null;
        mMileValue = null;
        mMillimeterValue = null;
        mCentimeterValue = null;
        mKilometerValue = null;
        mMeterError = true;
    }

    @Override
    public void displayConversionFromKilometersResults(List<String> results) {
        mInchValue = results.get(0);
        mFootValue = results.get(1);
        mYardValue = results.get(2);
        mMileValue = results.get(3);
        mMillimeterValue = results.get(4);
        mCentimeterValue = results.get(5);
        mMeterValue = results.get(6);
        mKilometerError = false;
    }

    @Override
    public void displayConversionFromKilometersError(Throwable error) {
        mInchValue = null;
        mFootValue = null;
        mYardValue = null;
        mMileValue = null;
        mMillimeterValue = null;
        mCentimeterValue = null;
        mMeterValue = null;
        mKilometerError = true;
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

        mInchError = false;
        mFootError = false;
        mYardError = false;
        mMileError = false;
        mMillimeterError = false;
        mCentimeterError = false;
        mMeterError = false;
        mKilometerError = false;
    }
}
