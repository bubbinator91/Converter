package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.ui.interfaces.fuelconsumption.IFuelConsumptionView;

import java.util.List;

public class MockFuelConsumptionView implements IFuelConsumptionView {

    public String mUsmpgValue = null, mUkmpgValue = null, mKplValue = null, mL100kValue = null;
    public boolean mUsmpgError = false, mUkmpgError = false, mKplError = false, mL100kError = false;

    @Override
    public void displayConversionFromUSMilesPerGallonResults(List<String> results) {
        mUkmpgValue = results.get(0);
        mKplValue = results.get(1);
        mL100kValue = results.get(2);
        mUsmpgError = false;
    }

    @Override
    public void displayConversionFromUSMilesPerGallonError(Throwable error) {
        mUkmpgValue = null;
        mKplValue = null;
        mL100kValue = null;
        mUsmpgError = true;
    }

    @Override
    public void displayConversionFromUKMilesPerGallonResults(List<String> results) {
        mUsmpgValue = results.get(0);
        mKplValue = results.get(1);
        mL100kValue = results.get(2);
        mUkmpgError = false;
    }

    @Override
    public void displayConversionFromUKMilesPerGallonError(Throwable error) {
        mUsmpgValue = null;
        mKplValue = null;
        mL100kValue = null;
        mUkmpgError = true;
    }

    @Override
    public void displayConversionFromKilometersPerLiterResults(List<String> results) {
        mUsmpgValue = results.get(0);
        mUkmpgValue = results.get(1);
        mL100kValue = results.get(2);
        mKplError = false;
    }

    @Override
    public void displayConversionFromKilometersPerLiterError(Throwable error) {
        mUsmpgValue = null;
        mUkmpgValue = null;
        mL100kValue = null;
        mKplError = true;
    }

    @Override
    public void displayConversionFromLitersPer100KilometersResults(List<String> results) {
        mUsmpgValue = results.get(0);
        mUkmpgValue = results.get(1);
        mKplValue = results.get(2);
        mL100kError = false;
    }

    @Override
    public void displayConversionFromLitersPer100KilometersError(Throwable error) {
        mUsmpgValue = null;
        mUkmpgValue = null;
        mKplValue = null;
        mL100kError = true;
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

        mUsmpgError = false;
        mUkmpgError = false;
        mKplError = false;
        mL100kError = false;
    }
}
