package com.bubbinator91.converter.ui.interfaces.datatransferspeed;

import com.bubbinator91.converter.ui.interfaces.base.IConverterPresenter;

/**
 * Presenter interface for the presenter of
 * {@link com.bubbinator91.converter.ui.fragments.DataTransferSpeedFragment}
 */
public interface IDataTransferSpeedPresenter extends IConverterPresenter<IDataTransferSpeedView> {
    void getConversionFromBitsPerSecondResults(String bps, int decimalPlaces);

    void getConversionFromBytesPerSecondResults(String byps, int decimalPlaces);

    void getConversionFromKilobitsPerSecondResults(String kbps, int decimalPlaces);

    void getConversionFromKilobytesPerSecondResults(String kbyps, int decimalPlaces);

    void getConversionFromMegabitsPerSecondResults(String mbps, int decimalPlaces);

    void getConversionFromMegabytesPerSecondResults(String mbyps, int decimalPlaces);

    void getConversionFromGigabitsPerSecondResults(String gbps, int decimalPlaces);

    void getConversionFromGigabytesPerSecondResults(String gbyps, int decimalPlaces);

    void getConversionFromTerabitsPerSecondResults(String tbps, int decimalPlaces);

    void getConversionFromTerabytesPerSecondResults(String tbyps, int decimalPlaces);
}
