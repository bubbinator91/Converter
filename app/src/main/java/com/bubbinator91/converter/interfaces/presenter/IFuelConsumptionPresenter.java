package com.bubbinator91.converter.interfaces.presenter;

import com.bubbinator91.converter.interfaces.base.IConverterPresenter;
import com.bubbinator91.converter.interfaces.view.IFuelConsumptionView;

/**
 * Presenter interface for the presenter of
 * {@link com.bubbinator91.converter.ui.fragments.FuelConsumptionFragment}
 */
public interface IFuelConsumptionPresenter extends IConverterPresenter<IFuelConsumptionView> {
    void getConversionFromKilometersPerLiterResults(String kpl, int decimalPlaces);

    void getConversionFromLitersPer100KilometersResults(String l100k, int decimalPlaces);

    void getConversionFromUKMilesPerGallonResults(String ukmpg, int decimalPlaces);

    void getConversionFromUSMilesPerGallonResults(String usmpg, int decimalPlaces);
}
