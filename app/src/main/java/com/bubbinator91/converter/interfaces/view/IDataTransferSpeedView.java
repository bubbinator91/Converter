package com.bubbinator91.converter.interfaces.view;

import com.bubbinator91.converter.interfaces.base.IConverterView;

import java.util.List;

/**
 * View interface for the {@link com.bubbinator91.converter.ui.fragments.DataTransferSpeedFragment}
 */
public interface IDataTransferSpeedView extends IConverterView {
    void displayConversionFromBitsPerSecondResults(List<String> results);

    void displayConversionFromBitsPerSecondError(Throwable error);

    void displayConversionFromBytesPerSecondResults(List<String> results);

    void displayConversionFromBytesPerSecondError(Throwable error);

    void displayConversionFromKilobitsPerSecondResults(List<String> results);

    void displayConversionFromKilobitsPerSecondError(Throwable error);

    void displayConversionFromKilobytesPerSecondResults(List<String> results);

    void displayConversionFromKilobytesPerSecondError(Throwable error);

    void displayConversionFromMegabitsPerSecondResults(List<String> results);

    void displayConversionFromMegabitsPerSecondError(Throwable error);

    void displayConversionFromMegabytesPerSecondResults(List<String> results);

    void displayConversionFromMegabytesPerSecondError(Throwable error);

    void displayConversionFromGigabitsPerSecondResults(List<String> results);

    void displayConversionFromGigabitsPerSecondError(Throwable error);

    void displayConversionFromGigabytesPerSecondResults(List<String> results);

    void displayConversionFromGigabytesPerSecondError(Throwable error);

    void displayConversionFromTerabitsPerSecondResults(List<String> results);

    void displayConversionFromTerabitsPerSecondError(Throwable error);

    void displayConversionFromTerabytesPerSecondResults(List<String> results);

    void displayConversionFromTerabytesPerSecondError(Throwable error);
}
