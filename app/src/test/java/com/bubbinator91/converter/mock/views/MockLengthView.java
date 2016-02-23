package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.interfaces.view.ILengthView;

import java.util.List;

public class MockLengthView implements ILengthView {

    public String inchValue = null, footValue = null, yardValue = null, mileValue = null,
            millimeterValue = null, centimeterValue = null, meterValue = null,
            kilometerValue = null;
    public boolean inchError = false, footError = false, yardError = false, mileError = false,
            millimeterError = false, centimeterError = false, meterError = false,
            kilometerError = false;

    @Override
    public void displayConversionFromInchesResults(List<String> results) {
        footValue = results.get(0);
        yardValue = results.get(1);
        mileValue = results.get(2);
        millimeterValue = results.get(3);
        centimeterValue = results.get(4);
        meterValue = results.get(5);
        kilometerValue = results.get(6);
        inchError = false;
    }

    @Override
    public void displayConversionFromInchesError(Throwable error) {
        footValue = null;
        yardValue = null;
        mileValue = null;
        millimeterValue = null;
        centimeterValue = null;
        meterValue = null;
        kilometerValue = null;
        inchError = true;
    }

    @Override
    public void displayConversionFromFeetResults(List<String> results) {
        inchValue = results.get(0);
        yardValue = results.get(1);
        mileValue = results.get(2);
        millimeterValue = results.get(3);
        centimeterValue = results.get(4);
        meterValue = results.get(5);
        kilometerValue = results.get(6);
        footError = false;
    }

    @Override
    public void displayConversionFromFeetError(Throwable error) {
        inchValue = null;
        yardValue = null;
        mileValue = null;
        millimeterValue = null;
        centimeterValue = null;
        meterValue = null;
        kilometerValue = null;
        footError = true;
    }

    @Override
    public void displayConversionFromYardsResults(List<String> results) {
        inchValue = results.get(0);
        footValue = results.get(1);
        mileValue = results.get(2);
        millimeterValue = results.get(3);
        centimeterValue = results.get(4);
        meterValue = results.get(5);
        kilometerValue = results.get(6);
        yardError = false;
    }

    @Override
    public void displayConversionFromYardsError(Throwable error) {
        inchValue = null;
        footValue = null;
        mileValue = null;
        millimeterValue = null;
        centimeterValue = null;
        meterValue = null;
        kilometerValue = null;
        yardError = true;
    }

    @Override
    public void displayConversionFromMilesResults(List<String> results) {
        inchValue = results.get(0);
        footValue = results.get(1);
        yardValue = results.get(2);
        millimeterValue = results.get(3);
        centimeterValue = results.get(4);
        meterValue = results.get(5);
        kilometerValue = results.get(6);
        mileError = false;
    }

    @Override
    public void displayConversionFromMilesError(Throwable error) {
        inchValue = null;
        footValue = null;
        yardValue = null;
        millimeterValue = null;
        centimeterValue = null;
        meterValue = null;
        kilometerValue = null;
        mileError = true;
    }

    @Override
    public void displayConversionFromMillimetersResults(List<String> results) {
        inchValue = results.get(0);
        footValue = results.get(1);
        yardValue = results.get(2);
        mileValue = results.get(3);
        centimeterValue = results.get(4);
        meterValue = results.get(5);
        kilometerValue = results.get(6);
        millimeterError = false;
    }

    @Override
    public void displayConversionFromMillimetersError(Throwable error) {
        inchValue = null;
        footValue = null;
        yardValue = null;
        mileValue = null;
        centimeterValue = null;
        meterValue = null;
        kilometerValue = null;
        millimeterError = true;
    }

    @Override
    public void displayConversionFromCentimetersResults(List<String> results) {
        inchValue = results.get(0);
        footValue = results.get(1);
        yardValue = results.get(2);
        mileValue = results.get(3);
        millimeterValue = results.get(4);
        meterValue = results.get(5);
        kilometerValue = results.get(6);
        centimeterError = false;
    }

    @Override
    public void displayConversionFromCentimetersError(Throwable error) {
        inchValue = null;
        footValue = null;
        yardValue = null;
        mileValue = null;
        millimeterValue = null;
        meterValue = null;
        kilometerValue = null;
        centimeterError = true;
    }

    @Override
    public void displayConversionFromMetersResults(List<String> results) {
        inchValue = results.get(0);
        footValue = results.get(1);
        yardValue = results.get(2);
        mileValue = results.get(3);
        millimeterValue = results.get(4);
        centimeterValue = results.get(5);
        kilometerValue = results.get(6);
        meterError = false;
    }

    @Override
    public void displayConversionFromMetersError(Throwable error) {
        inchValue = null;
        footValue = null;
        yardValue = null;
        mileValue = null;
        millimeterValue = null;
        centimeterValue = null;
        kilometerValue = null;
        meterError = true;
    }

    @Override
    public void displayConversionFromKilometersResults(List<String> results) {
        inchValue = results.get(0);
        footValue = results.get(1);
        yardValue = results.get(2);
        mileValue = results.get(3);
        millimeterValue = results.get(4);
        centimeterValue = results.get(5);
        meterValue = results.get(6);
        kilometerError = false;
    }

    @Override
    public void displayConversionFromKilometersError(Throwable error) {
        inchValue = null;
        footValue = null;
        yardValue = null;
        mileValue = null;
        millimeterValue = null;
        centimeterValue = null;
        meterValue = null;
        kilometerError = true;
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
        inchValue = null;
        footValue = null;
        yardValue = null;
        mileValue = null;
        millimeterValue = null;
        centimeterValue = null;
        meterValue = null;
        kilometerValue = null;

        inchError = false;
        footError = false;
        yardError = false;
        mileError = false;
        millimeterError = false;
        centimeterError = false;
        meterError = false;
        kilometerError = false;
    }
}
