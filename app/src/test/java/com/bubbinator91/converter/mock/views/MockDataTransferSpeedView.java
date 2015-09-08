package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.ui.interfaces.datatransferspeed.IDataTransferSpeedView;

import java.util.List;

public class MockDataTransferSpeedView implements IDataTransferSpeedView {

    public String mBpsValue = null, mBypsValue = null, mKbpsValue = null, mKbypsValue = null,
            mMbpsValue = null, mMbypsValue = null, mGbpsValue = null, mGbypsValue = null,
            mTbpsValue = null, mTbypsValue = null;
    public int mBpsError = -1, mBypsError = -1, mKbpsError = -1, mKbypsError = -1, mMbpsError = -1,
            mMbypsError = -1, mGbpsError = -1, mGbypsError = -1, mTbpsError = -1, mTbypsError = -1;

    @Override
    public void displayConversionFromBitsPerSecondResults(List<String> results, int errorCode) {
        if (results != null) {
            mBypsValue = results.get(0);
            mKbpsValue = results.get(1);
            mKbypsValue = results.get(2);
            mMbpsValue = results.get(3);
            mMbypsValue = results.get(4);
            mGbpsValue = results.get(5);
            mGbypsValue = results.get(6);
            mTbpsValue = results.get(7);
            mTbypsValue = results.get(8);
        } else {
            mBypsValue = null;
            mKbpsValue = null;
            mKbypsValue = null;
            mMbpsValue = null;
            mMbypsValue = null;
            mGbpsValue = null;
            mGbypsValue = null;
            mTbpsValue = null;
            mTbypsValue = null;
        }

        mBpsError = errorCode;
    }

    @Override
    public void displayConversionFromBytesPerSecondResults(List<String> results, int errorCode) {
        if (results != null) {
            mBpsValue = results.get(0);
            mKbpsValue = results.get(1);
            mKbypsValue = results.get(2);
            mMbpsValue = results.get(3);
            mMbypsValue = results.get(4);
            mGbpsValue = results.get(5);
            mGbypsValue = results.get(6);
            mTbpsValue = results.get(7);
            mTbypsValue = results.get(8);
        } else {
            mBpsValue = null;
            mKbpsValue = null;
            mKbypsValue = null;
            mMbpsValue = null;
            mMbypsValue = null;
            mGbpsValue = null;
            mGbypsValue = null;
            mTbpsValue = null;
            mTbypsValue = null;
        }

        mBypsError = errorCode;
    }

    @Override
    public void displayConversionFromKilobitsPerSecondResults(List<String> results, int errorCode) {
        if (results != null) {
            mBpsValue = results.get(0);
            mBypsValue = results.get(1);
            mKbypsValue = results.get(2);
            mMbpsValue = results.get(3);
            mMbypsValue = results.get(4);
            mGbpsValue = results.get(5);
            mGbypsValue = results.get(6);
            mTbpsValue = results.get(7);
            mTbypsValue = results.get(8);
        } else {
            mBpsValue = null;
            mBypsValue = null;
            mKbypsValue = null;
            mMbpsValue = null;
            mMbypsValue = null;
            mGbpsValue = null;
            mGbypsValue = null;
            mTbpsValue = null;
            mTbypsValue = null;
        }

        mKbpsError = errorCode;
    }

    @Override
    public void displayConversionFromKilobytesPerSecondResults(List<String> results, int errorCode) {
        if (results != null) {
            mBpsValue = results.get(0);
            mBypsValue = results.get(1);
            mKbpsValue = results.get(2);
            mMbpsValue = results.get(3);
            mMbypsValue = results.get(4);
            mGbpsValue = results.get(5);
            mGbypsValue = results.get(6);
            mTbpsValue = results.get(7);
            mTbypsValue = results.get(8);
        } else {
            mBpsValue = null;
            mBypsValue = null;
            mKbpsValue = null;
            mMbpsValue = null;
            mMbypsValue = null;
            mGbpsValue = null;
            mGbypsValue = null;
            mTbpsValue = null;
            mTbypsValue = null;
        }

        mKbypsError = errorCode;
    }

    @Override
    public void displayConversionFromMegabitsPerSecondResults(List<String> results, int errorCode) {
        if (results != null) {
            mBpsValue = results.get(0);
            mBypsValue = results.get(1);
            mKbpsValue = results.get(2);
            mKbypsValue = results.get(3);
            mMbypsValue = results.get(4);
            mGbpsValue = results.get(5);
            mGbypsValue = results.get(6);
            mTbpsValue = results.get(7);
            mTbypsValue = results.get(8);
        } else {
            mBpsValue = null;
            mBypsValue = null;
            mKbpsValue = null;
            mKbypsValue = null;
            mMbypsValue = null;
            mGbpsValue = null;
            mGbypsValue = null;
            mTbpsValue = null;
            mTbypsValue = null;
        }

        mMbpsError = errorCode;
    }

    @Override
    public void displayConversionFromMegabytesPerSecondResults(List<String> results, int errorCode) {
        if (results != null) {
            mBpsValue = results.get(0);
            mBypsValue = results.get(1);
            mKbpsValue = results.get(2);
            mKbypsValue = results.get(3);
            mMbpsValue = results.get(4);
            mGbpsValue = results.get(5);
            mGbypsValue = results.get(6);
            mTbpsValue = results.get(7);
            mTbypsValue = results.get(8);
        } else {
            mBpsValue = null;
            mBypsValue = null;
            mKbpsValue = null;
            mKbypsValue = null;
            mMbpsValue = null;
            mGbpsValue = null;
            mGbypsValue = null;
            mTbpsValue = null;
            mTbypsValue = null;
        }

        mMbypsError = errorCode;
    }

    @Override
    public void displayConversionFromGigabitsPerSecondResults(List<String> results, int errorCode) {
        if (results != null) {
            mBpsValue = results.get(0);
            mBypsValue = results.get(1);
            mKbpsValue = results.get(2);
            mKbypsValue = results.get(3);
            mMbpsValue = results.get(4);
            mMbypsValue = results.get(5);
            mGbypsValue = results.get(6);
            mTbpsValue = results.get(7);
            mTbypsValue = results.get(8);
        } else {
            mBpsValue = null;
            mBypsValue = null;
            mKbpsValue = null;
            mKbypsValue = null;
            mMbpsValue = null;
            mMbypsValue = null;
            mGbypsValue = null;
            mTbpsValue = null;
            mTbypsValue = null;
        }

        mGbpsError = errorCode;
    }

    @Override
    public void displayConversionFromGigabytesPerSecondResults(List<String> results, int errorCode) {
        if (results != null) {
            mBpsValue = results.get(0);
            mBypsValue = results.get(1);
            mKbpsValue = results.get(2);
            mKbypsValue = results.get(3);
            mMbpsValue = results.get(4);
            mMbypsValue = results.get(5);
            mGbpsValue = results.get(6);
            mTbpsValue = results.get(7);
            mTbypsValue = results.get(8);
        } else {
            mBpsValue = null;
            mBypsValue = null;
            mKbpsValue = null;
            mKbypsValue = null;
            mMbpsValue = null;
            mMbypsValue = null;
            mGbpsValue = null;
            mTbpsValue = null;
            mTbypsValue = null;
        }

        mGbypsError = errorCode;
    }

    @Override
    public void displayConversionFromTerabitsPerSecondResults(List<String> results, int errorCode) {
        if (results != null) {
            mBpsValue = results.get(0);
            mBypsValue = results.get(1);
            mKbpsValue = results.get(2);
            mKbypsValue = results.get(3);
            mMbpsValue = results.get(4);
            mMbypsValue = results.get(5);
            mGbpsValue = results.get(6);
            mGbypsValue = results.get(7);
            mTbypsValue = results.get(8);
        } else {
            mBpsValue = null;
            mBypsValue = null;
            mKbpsValue = null;
            mKbypsValue = null;
            mMbpsValue = null;
            mMbypsValue = null;
            mGbpsValue = null;
            mGbypsValue = null;
            mTbypsValue = null;
        }

        mTbpsError = errorCode;
    }

    @Override
    public void displayConversionFromTerabytesPerSecondResults(List<String> results, int errorCode) {
        if (results != null) {
            mBpsValue = results.get(0);
            mBypsValue = results.get(1);
            mKbpsValue = results.get(2);
            mKbypsValue = results.get(3);
            mMbpsValue = results.get(4);
            mMbypsValue = results.get(5);
            mGbpsValue = results.get(6);
            mGbypsValue = results.get(7);
            mTbpsValue = results.get(8);
        } else {
            mBpsValue = null;
            mBypsValue = null;
            mKbpsValue = null;
            mKbypsValue = null;
            mMbpsValue = null;
            mMbypsValue = null;
            mGbpsValue = null;
            mGbypsValue = null;
            mTbpsValue = null;
        }

        mTbypsError = errorCode;
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

        mBpsError = -1;
        mBypsError = -1;
        mKbpsError = -1;
        mKbypsError = -1;
        mMbpsError = -1;
        mMbypsError = -1;
        mGbpsError = -1;
        mGbypsError = -1;
        mTbpsError = -1;
        mTbypsError = -1;
    }
}
