package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.interfaces.view.IDataTransferSpeedView;

import java.util.List;

public class MockDataTransferSpeedView implements IDataTransferSpeedView {

    public String bpsValue = null, bypsValue = null, kbpsValue = null, kbypsValue = null,
            mbpsValue = null, mbypsValue = null, gbpsValue = null, gbypsValue = null,
            tbpsValue = null, tbypsValue = null;
    public boolean bpsError = false, bypsError = false, kbpsError = false, kbypsError = false,
            mbpsError = false, mbypsError = false, gbpsError = false, gbypsError = false,
            tbpsError = false, tbypsError = false;

    @Override
    public void displayConversionFromBitsPerSecondResults(List<String> results) {
        bypsValue = results.get(0);
        kbpsValue = results.get(1);
        kbypsValue = results.get(2);
        mbpsValue = results.get(3);
        mbypsValue = results.get(4);
        gbpsValue = results.get(5);
        gbypsValue = results.get(6);
        tbpsValue = results.get(7);
        tbypsValue = results.get(8);
        bpsError = false;
    }

    @Override
    public void displayConversionFromBitsPerSecondError(Throwable error) {
        bypsValue = null;
        kbpsValue = null;
        kbypsValue = null;
        mbpsValue = null;
        mbypsValue = null;
        gbpsValue = null;
        gbypsValue = null;
        tbpsValue = null;
        tbypsValue = null;
        bpsError = true;
    }

    @Override
    public void displayConversionFromBytesPerSecondResults(List<String> results) {
        bpsValue = results.get(0);
        kbpsValue = results.get(1);
        kbypsValue = results.get(2);
        mbpsValue = results.get(3);
        mbypsValue = results.get(4);
        gbpsValue = results.get(5);
        gbypsValue = results.get(6);
        tbpsValue = results.get(7);
        tbypsValue = results.get(8);
        bypsError = false;
    }

    @Override
    public void displayConversionFromBytesPerSecondError(Throwable error) {
        bpsValue = null;
        kbpsValue = null;
        kbypsValue = null;
        mbpsValue = null;
        mbypsValue = null;
        gbpsValue = null;
        gbypsValue = null;
        tbpsValue = null;
        tbypsValue = null;
        bypsError = true;
    }

    @Override
    public void displayConversionFromKilobitsPerSecondResults(List<String> results) {
        bpsValue = results.get(0);
        bypsValue = results.get(1);
        kbypsValue = results.get(2);
        mbpsValue = results.get(3);
        mbypsValue = results.get(4);
        gbpsValue = results.get(5);
        gbypsValue = results.get(6);
        tbpsValue = results.get(7);
        tbypsValue = results.get(8);
        kbpsError = false;
    }

    @Override
    public void displayConversionFromKilobitsPerSecondError(Throwable error) {
        bpsValue = null;
        bypsValue = null;
        kbypsValue = null;
        mbpsValue = null;
        mbypsValue = null;
        gbpsValue = null;
        gbypsValue = null;
        tbpsValue = null;
        tbypsValue = null;
        kbpsError = true;
    }

    @Override
    public void displayConversionFromKilobytesPerSecondResults(List<String> results) {
        bpsValue = results.get(0);
        bypsValue = results.get(1);
        kbpsValue = results.get(2);
        mbpsValue = results.get(3);
        mbypsValue = results.get(4);
        gbpsValue = results.get(5);
        gbypsValue = results.get(6);
        tbpsValue = results.get(7);
        tbypsValue = results.get(8);
        kbypsError = false;
    }

    @Override
    public void displayConversionFromKilobytesPerSecondError(Throwable error) {
        bpsValue = null;
        bypsValue = null;
        kbpsValue = null;
        mbpsValue = null;
        mbypsValue = null;
        gbpsValue = null;
        gbypsValue = null;
        tbpsValue = null;
        tbypsValue = null;
        kbypsError = true;
    }

    @Override
    public void displayConversionFromMegabitsPerSecondResults(List<String> results) {
        bpsValue = results.get(0);
        bypsValue = results.get(1);
        kbpsValue = results.get(2);
        kbypsValue = results.get(3);
        mbypsValue = results.get(4);
        gbpsValue = results.get(5);
        gbypsValue = results.get(6);
        tbpsValue = results.get(7);
        tbypsValue = results.get(8);
        mbpsError = false;
    }

    @Override
    public void displayConversionFromMegabitsPerSecondError(Throwable error) {
        bpsValue = null;
        bypsValue = null;
        kbpsValue = null;
        kbypsValue = null;
        mbypsValue = null;
        gbpsValue = null;
        gbypsValue = null;
        tbpsValue = null;
        tbypsValue = null;
        mbpsError = true;
    }

    @Override
    public void displayConversionFromMegabytesPerSecondResults(List<String> results) {
        bpsValue = results.get(0);
        bypsValue = results.get(1);
        kbpsValue = results.get(2);
        kbypsValue = results.get(3);
        mbpsValue = results.get(4);
        gbpsValue = results.get(5);
        gbypsValue = results.get(6);
        tbpsValue = results.get(7);
        tbypsValue = results.get(8);
        mbypsError = false;
    }

    @Override
    public void displayConversionFromMegabytesPerSecondError(Throwable error) {
        bpsValue = null;
        bypsValue = null;
        kbpsValue = null;
        kbypsValue = null;
        mbpsValue = null;
        gbpsValue = null;
        gbypsValue = null;
        tbpsValue = null;
        tbypsValue = null;
        mbypsError = true;
    }

    @Override
    public void displayConversionFromGigabitsPerSecondResults(List<String> results) {
        bpsValue = results.get(0);
        bypsValue = results.get(1);
        kbpsValue = results.get(2);
        kbypsValue = results.get(3);
        mbpsValue = results.get(4);
        mbypsValue = results.get(5);
        gbypsValue = results.get(6);
        tbpsValue = results.get(7);
        tbypsValue = results.get(8);
        gbpsError = false;
    }

    @Override
    public void displayConversionFromGigabitsPerSecondError(Throwable error) {
        bpsValue = null;
        bypsValue = null;
        kbpsValue = null;
        kbypsValue = null;
        mbpsValue = null;
        mbypsValue = null;
        gbypsValue = null;
        tbpsValue = null;
        tbypsValue = null;
        gbpsError = true;
    }

    @Override
    public void displayConversionFromGigabytesPerSecondResults(List<String> results) {
        bpsValue = results.get(0);
        bypsValue = results.get(1);
        kbpsValue = results.get(2);
        kbypsValue = results.get(3);
        mbpsValue = results.get(4);
        mbypsValue = results.get(5);
        gbpsValue = results.get(6);
        tbpsValue = results.get(7);
        tbypsValue = results.get(8);
        gbypsError = false;
    }

    @Override
    public void displayConversionFromGigabytesPerSecondError(Throwable error) {
        bpsValue = null;
        bypsValue = null;
        kbpsValue = null;
        kbypsValue = null;
        mbpsValue = null;
        mbypsValue = null;
        gbpsValue = null;
        tbpsValue = null;
        tbypsValue = null;
        gbypsError = true;
    }

    @Override
    public void displayConversionFromTerabitsPerSecondResults(List<String> results) {
        bpsValue = results.get(0);
        bypsValue = results.get(1);
        kbpsValue = results.get(2);
        kbypsValue = results.get(3);
        mbpsValue = results.get(4);
        mbypsValue = results.get(5);
        gbpsValue = results.get(6);
        gbypsValue = results.get(7);
        tbypsValue = results.get(8);
        tbpsError = false;
    }

    @Override
    public void displayConversionFromTerabitsPerSecondError(Throwable error) {
        bpsValue = null;
        bypsValue = null;
        kbpsValue = null;
        kbypsValue = null;
        mbpsValue = null;
        mbypsValue = null;
        gbpsValue = null;
        gbypsValue = null;
        tbypsValue = null;
        tbpsError = true;
    }

    @Override
    public void displayConversionFromTerabytesPerSecondResults(List<String> results) {
        bpsValue = results.get(0);
        bypsValue = results.get(1);
        kbpsValue = results.get(2);
        kbypsValue = results.get(3);
        mbpsValue = results.get(4);
        mbypsValue = results.get(5);
        gbpsValue = results.get(6);
        gbypsValue = results.get(7);
        tbpsValue = results.get(8);
        tbypsError = false;
    }

    @Override
    public void displayConversionFromTerabytesPerSecondError(Throwable error) {
        bpsValue = null;
        bypsValue = null;
        kbpsValue = null;
        kbypsValue = null;
        mbpsValue = null;
        mbypsValue = null;
        gbpsValue = null;
        gbypsValue = null;
        tbpsValue = null;
        tbypsError = true;
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
        bpsValue = null;
        bypsValue = null;
        kbpsValue = null;
        kbypsValue = null;
        mbpsValue = null;
        mbypsValue = null;
        gbpsValue = null;
        gbypsValue = null;
        tbpsValue = null;
        tbypsValue = null;

        bpsError = false;
        bypsError = false;
        kbpsError = false;
        kbypsError = false;
        mbpsError = false;
        mbypsError = false;
        gbpsError = false;
        gbypsError = false;
        tbpsError = false;
        tbypsError = false;
    }
}
