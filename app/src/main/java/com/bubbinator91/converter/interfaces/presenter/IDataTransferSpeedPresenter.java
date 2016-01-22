package com.bubbinator91.converter.interfaces.presenter;

import com.bubbinator91.converter.interfaces.base.IConverterPresenter;
import com.bubbinator91.converter.interfaces.view.IDataTransferSpeedView;

/**
 * Presenter interface for the presenter of
 * {@link com.bubbinator91.converter.ui.fragments.DataTransferSpeedFragment}
 */
public interface IDataTransferSpeedPresenter extends IConverterPresenter<IDataTransferSpeedView> {
    void getConversionFromBitsPerSecond(String bps, int decimalPlaces);

    void getConversionFromBytesPerSecond(String byps, int decimalPlaces);

    void getConversionFromKilobitsPerSecond(String kbps, int decimalPlaces);

    void getConversionFromKilobytesPerSecond(String kbyps, int decimalPlaces);

    void getConversionFromMegabitsPerSecond(String mbps, int decimalPlaces);

    void getConversionFromMegabytesPerSecond(String mbyps, int decimalPlaces);

    void getConversionFromGigabitsPerSecond(String gbps, int decimalPlaces);

    void getConversionFromGigabytesPerSecond(String gbyps, int decimalPlaces);

    void getConversionFromTerabitsPerSecond(String tbps, int decimalPlaces);

    void getConversionFromTerabytesPerSecond(String tbyps, int decimalPlaces);
}
