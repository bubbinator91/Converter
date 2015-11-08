package com.bubbinator91.converter.interfaces.view;

import com.bubbinator91.converter.interfaces.base.IConverterView;

import java.util.List;

/**
 * View interface for the {@link com.bubbinator91.converter.ui.fragments.DataTransferSpeedFragment}
 */
public interface IDataTransferSpeedView extends IConverterView {
    void displayConversionFromBitsPerSecondResults(List<String> results, int errorCode);

    void displayConversionFromBytesPerSecondResults(List<String> results, int errorCode);

    void displayConversionFromKilobitsPerSecondResults(List<String> results, int errorCode);

    void displayConversionFromKilobytesPerSecondResults(List<String> results, int errorCode);

    void displayConversionFromMegabitsPerSecondResults(List<String> results, int errorCode);

    void displayConversionFromMegabytesPerSecondResults(List<String> results, int errorCode);

    void displayConversionFromGigabitsPerSecondResults(List<String> results, int errorCode);

    void displayConversionFromGigabytesPerSecondResults(List<String> results, int errorCode);

    void displayConversionFromTerabitsPerSecondResults(List<String> results, int errorCode);

    void displayConversionFromTerabytesPerSecondResults(List<String> results, int errorCode);
}
