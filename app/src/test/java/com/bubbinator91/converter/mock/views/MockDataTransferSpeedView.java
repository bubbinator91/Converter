package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.interfaces.view.IDataTransferSpeedView;

import java.util.List;

public class MockDataTransferSpeedView implements IDataTransferSpeedView {

    public String mBpsValue = null, mBypsValue = null, mKbpsValue = null, mKbypsValue = null,
            mMbpsValue = null, mMbypsValue = null, mGbpsValue = null, mGbypsValue = null,
            mTbpsValue = null, mTbypsValue = null;
    public boolean mBpsError = false, mBypsError = false, mKbpsError = false, mKbypsError = false,
            mMbpsError = false, mMbypsError = false, mGbpsError = false, mGbypsError = false,
            mTbpsError = false, mTbypsError = false;

    @Override
    public void displayConversionFromBitsPerSecondResults(List<String> results) {
        mBypsValue = results.get(0);
        mKbpsValue = results.get(1);
        mKbypsValue = results.get(2);
        mMbpsValue = results.get(3);
        mMbypsValue = results.get(4);
        mGbpsValue = results.get(5);
        mGbypsValue = results.get(6);
        mTbpsValue = results.get(7);
        mTbypsValue = results.get(8);
        mBpsError = false;
    }

    @Override
    public void displayConversionFromBitsPerSecondError(Throwable error) {
        mBypsValue = null;
        mKbpsValue = null;
        mKbypsValue = null;
        mMbpsValue = null;
        mMbypsValue = null;
        mGbpsValue = null;
        mGbypsValue = null;
        mTbpsValue = null;
        mTbypsValue = null;
        mBpsError = true;
    }

    @Override
    public void displayConversionFromBytesPerSecondResults(List<String> results) {
        mBpsValue = results.get(0);
        mKbpsValue = results.get(1);
        mKbypsValue = results.get(2);
        mMbpsValue = results.get(3);
        mMbypsValue = results.get(4);
        mGbpsValue = results.get(5);
        mGbypsValue = results.get(6);
        mTbpsValue = results.get(7);
        mTbypsValue = results.get(8);
        mBypsError = false;
    }

    @Override
    public void displayConversionFromBytesPerSecondError(Throwable error) {
        mBpsValue = null;
        mKbpsValue = null;
        mKbypsValue = null;
        mMbpsValue = null;
        mMbypsValue = null;
        mGbpsValue = null;
        mGbypsValue = null;
        mTbpsValue = null;
        mTbypsValue = null;
        mBypsError = true;
    }

    @Override
    public void displayConversionFromKilobitsPerSecondResults(List<String> results) {
        mBpsValue = results.get(0);
        mBypsValue = results.get(1);
        mKbypsValue = results.get(2);
        mMbpsValue = results.get(3);
        mMbypsValue = results.get(4);
        mGbpsValue = results.get(5);
        mGbypsValue = results.get(6);
        mTbpsValue = results.get(7);
        mTbypsValue = results.get(8);
        mKbpsError = false;
    }

    @Override
    public void displayConversionFromKilobitsPerSecondError(Throwable error) {
        mBpsValue = null;
        mBypsValue = null;
        mKbypsValue = null;
        mMbpsValue = null;
        mMbypsValue = null;
        mGbpsValue = null;
        mGbypsValue = null;
        mTbpsValue = null;
        mTbypsValue = null;
        mKbpsError = true;
    }

    @Override
    public void displayConversionFromKilobytesPerSecondResults(List<String> results) {
        mBpsValue = results.get(0);
        mBypsValue = results.get(1);
        mKbpsValue = results.get(2);
        mMbpsValue = results.get(3);
        mMbypsValue = results.get(4);
        mGbpsValue = results.get(5);
        mGbypsValue = results.get(6);
        mTbpsValue = results.get(7);
        mTbypsValue = results.get(8);
        mKbypsError = false;
    }

    @Override
    public void displayConversionFromKilobytesPerSecondError(Throwable error) {
        mBpsValue = null;
        mBypsValue = null;
        mKbpsValue = null;
        mMbpsValue = null;
        mMbypsValue = null;
        mGbpsValue = null;
        mGbypsValue = null;
        mTbpsValue = null;
        mTbypsValue = null;
        mKbypsError = true;
    }

    @Override
    public void displayConversionFromMegabitsPerSecondResults(List<String> results) {
        mBpsValue = results.get(0);
        mBypsValue = results.get(1);
        mKbpsValue = results.get(2);
        mKbypsValue = results.get(3);
        mMbypsValue = results.get(4);
        mGbpsValue = results.get(5);
        mGbypsValue = results.get(6);
        mTbpsValue = results.get(7);
        mTbypsValue = results.get(8);
        mMbpsError = false;
    }

    @Override
    public void displayConversionFromMegabitsPerSecondError(Throwable error) {
        mBpsValue = null;
        mBypsValue = null;
        mKbpsValue = null;
        mKbypsValue = null;
        mMbypsValue = null;
        mGbpsValue = null;
        mGbypsValue = null;
        mTbpsValue = null;
        mTbypsValue = null;
        mMbpsError = true;
    }

    @Override
    public void displayConversionFromMegabytesPerSecondResults(List<String> results) {
        mBpsValue = results.get(0);
        mBypsValue = results.get(1);
        mKbpsValue = results.get(2);
        mKbypsValue = results.get(3);
        mMbpsValue = results.get(4);
        mGbpsValue = results.get(5);
        mGbypsValue = results.get(6);
        mTbpsValue = results.get(7);
        mTbypsValue = results.get(8);
        mMbypsError = false;
    }

    @Override
    public void displayConversionFromMegabytesPerSecondError(Throwable error) {
        mBpsValue = null;
        mBypsValue = null;
        mKbpsValue = null;
        mKbypsValue = null;
        mMbpsValue = null;
        mGbpsValue = null;
        mGbypsValue = null;
        mTbpsValue = null;
        mTbypsValue = null;
        mMbypsError = true;
    }

    @Override
    public void displayConversionFromGigabitsPerSecondResults(List<String> results) {
        mBpsValue = results.get(0);
        mBypsValue = results.get(1);
        mKbpsValue = results.get(2);
        mKbypsValue = results.get(3);
        mMbpsValue = results.get(4);
        mMbypsValue = results.get(5);
        mGbypsValue = results.get(6);
        mTbpsValue = results.get(7);
        mTbypsValue = results.get(8);
        mGbpsError = false;
    }

    @Override
    public void displayConversionFromGigabitsPerSecondError(Throwable error) {
        mBpsValue = null;
        mBypsValue = null;
        mKbpsValue = null;
        mKbypsValue = null;
        mMbpsValue = null;
        mMbypsValue = null;
        mGbypsValue = null;
        mTbpsValue = null;
        mTbypsValue = null;
        mGbpsError = true;
    }

    @Override
    public void displayConversionFromGigabytesPerSecondResults(List<String> results) {
        mBpsValue = results.get(0);
        mBypsValue = results.get(1);
        mKbpsValue = results.get(2);
        mKbypsValue = results.get(3);
        mMbpsValue = results.get(4);
        mMbypsValue = results.get(5);
        mGbpsValue = results.get(6);
        mTbpsValue = results.get(7);
        mTbypsValue = results.get(8);
        mGbypsError = false;
    }

    @Override
    public void displayConversionFromGigabytesPerSecondError(Throwable error) {
        mBpsValue = null;
        mBypsValue = null;
        mKbpsValue = null;
        mKbypsValue = null;
        mMbpsValue = null;
        mMbypsValue = null;
        mGbpsValue = null;
        mTbpsValue = null;
        mTbypsValue = null;
        mGbypsError = true;
    }

    @Override
    public void displayConversionFromTerabitsPerSecondResults(List<String> results) {
        mBpsValue = results.get(0);
        mBypsValue = results.get(1);
        mKbpsValue = results.get(2);
        mKbypsValue = results.get(3);
        mMbpsValue = results.get(4);
        mMbypsValue = results.get(5);
        mGbpsValue = results.get(6);
        mGbypsValue = results.get(7);
        mTbypsValue = results.get(8);
        mTbpsError = false;
    }

    @Override
    public void displayConversionFromTerabitsPerSecondError(Throwable error) {
        mBpsValue = null;
        mBypsValue = null;
        mKbpsValue = null;
        mKbypsValue = null;
        mMbpsValue = null;
        mMbypsValue = null;
        mGbpsValue = null;
        mGbypsValue = null;
        mTbypsValue = null;
        mTbpsError = true;
    }

    @Override
    public void displayConversionFromTerabytesPerSecondResults(List<String> results) {
        mBpsValue = results.get(0);
        mBypsValue = results.get(1);
        mKbpsValue = results.get(2);
        mKbypsValue = results.get(3);
        mMbpsValue = results.get(4);
        mMbypsValue = results.get(5);
        mGbpsValue = results.get(6);
        mGbypsValue = results.get(7);
        mTbpsValue = results.get(8);
        mTbypsError = false;
    }

    @Override
    public void displayConversionFromTerabytesPerSecondError(Throwable error) {
        mBpsValue = null;
        mBypsValue = null;
        mKbpsValue = null;
        mKbypsValue = null;
        mMbpsValue = null;
        mMbypsValue = null;
        mGbpsValue = null;
        mGbypsValue = null;
        mTbpsValue = null;
        mTbypsError = true;
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
        mBpsValue = null;
        mBypsValue = null;
        mKbpsValue = null;
        mKbypsValue = null;
        mMbpsValue = null;
        mMbypsValue = null;
        mGbpsValue = null;
        mGbypsValue = null;
        mTbpsValue = null;
        mTbypsValue = null;

        mBpsError = false;
        mBypsError = false;
        mKbpsError = false;
        mKbypsError = false;
        mMbpsError = false;
        mMbypsError = false;
        mGbpsError = false;
        mGbypsError = false;
        mTbpsError = false;
        mTbypsError = false;
    }
}
