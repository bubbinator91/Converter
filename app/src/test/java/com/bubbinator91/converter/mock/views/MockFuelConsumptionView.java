package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.interfaces.view.IFuelConsumptionView;

import java.util.List;

public class MockFuelConsumptionView implements IFuelConsumptionView {

    public String usmpgValue = null, ukmpgValue = null, kplValue = null, l100kValue = null;
    public boolean usmpgError = false, ukmpgError = false, kplError = false, l100kError = false;

    @Override
    public void displayConversionFromUSMilesPerGallonResults(List<String> results) {
        ukmpgValue = results.get(0);
        kplValue = results.get(1);
        l100kValue = results.get(2);
        usmpgError = false;
    }

    @Override
    public void displayConversionFromUSMilesPerGallonError(Throwable error) {
        ukmpgValue = null;
        kplValue = null;
        l100kValue = null;
        usmpgError = true;
    }

    @Override
    public void displayConversionFromUKMilesPerGallonResults(List<String> results) {
        usmpgValue = results.get(0);
        kplValue = results.get(1);
        l100kValue = results.get(2);
        ukmpgError = false;
    }

    @Override
    public void displayConversionFromUKMilesPerGallonError(Throwable error) {
        usmpgValue = null;
        kplValue = null;
        l100kValue = null;
        ukmpgError = true;
    }

    @Override
    public void displayConversionFromKilometersPerLiterResults(List<String> results) {
        usmpgValue = results.get(0);
        ukmpgValue = results.get(1);
        l100kValue = results.get(2);
        kplError = false;
    }

    @Override
    public void displayConversionFromKilometersPerLiterError(Throwable error) {
        usmpgValue = null;
        ukmpgValue = null;
        l100kValue = null;
        kplError = true;
    }

    @Override
    public void displayConversionFromLitersPer100KilometersResults(List<String> results) {
        usmpgValue = results.get(0);
        ukmpgValue = results.get(1);
        kplValue = results.get(2);
        l100kError = false;
    }

    @Override
    public void displayConversionFromLitersPer100KilometersError(Throwable error) {
        usmpgValue = null;
        ukmpgValue = null;
        kplValue = null;
        l100kError = true;
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
        usmpgValue = null;
        ukmpgValue = null;
        kplValue = null;
        l100kValue = null;

        usmpgError = false;
        ukmpgError = false;
        kplError = false;
        l100kError = false;
    }
}
