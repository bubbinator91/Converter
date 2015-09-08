package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.ui.interfaces.fuelconsumption.IFuelConsumptionView;

import java.util.List;

public class MockFuelConsumptionView implements IFuelConsumptionView {

    public String mUsmpgValue = null, mUkmpgValue = null, mKplValue = null, mL100kValue = null;
    public int mUsmpgError = -1, mUkmpgError = -1, mKplError = -1, mL100kError = -1;

    @Override
    public void displayConversionFromUSMilesPerGallonResults(List<String> results, int errorCode) {
        if (results != null) {
            mUkmpgValue = results.get(0);
            mKplValue = results.get(1);
            mL100kValue = results.get(2);
        } else {
            mUkmpgValue = null;
            mKplValue = null;
            mL100kValue = null;
        }

        mUsmpgError = errorCode;
    }

    @Override
    public void displayConversionFromUKMilesPerGallonResults(List<String> results, int errorCode) {
        if (results != null) {
            mUsmpgValue = results.get(0);
            mKplValue = results.get(1);
            mL100kValue = results.get(2);
        } else {
            mUsmpgValue = null;
            mKplValue = null;
            mL100kValue = null;
        }

        mUkmpgError = errorCode;
    }

    @Override
    public void displayConversionFromKilometersPerLiterResults(List<String> results, int errorCode) {
        if (results != null) {
            mUsmpgValue = results.get(0);
            mUkmpgValue = results.get(1);
            mL100kValue = results.get(2);
        } else {
            mUsmpgValue = null;
            mUkmpgValue = null;
            mL100kValue = null;
        }

        mKplError = errorCode;
    }

    @Override
    public void displayConversionFromLitersPer100KilometersResults(List<String> results, int errorCode) {
        if (results != null) {
            mUsmpgValue = results.get(0);
            mUkmpgValue = results.get(1);
            mKplValue = results.get(2);
        } else {
            mUsmpgValue = null;
            mUkmpgValue = null;
            mKplValue = null;
        }

        mL100kError = errorCode;
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
        mUsmpgValue = null;
        mUkmpgValue = null;
        mKplValue = null;
        mL100kValue = null;

        mUsmpgError = -1;
        mUkmpgError = -1;
        mKplError = -1;
        mL100kError = -1;
    }
}
