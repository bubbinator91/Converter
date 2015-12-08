package com.bubbinator91.converter.interfaces.presenter;

import com.bubbinator91.converter.interfaces.base.IConverterPresenter;
import com.bubbinator91.converter.interfaces.view.IFuelConsumptionView;

/**
 * Presenter interface for the presenter of
 * {@link com.bubbinator91.converter.ui.fragments.FuelConsumptionFragment}
 */
public interface IFuelConsumptionPresenter extends IConverterPresenter<IFuelConsumptionView> {
    void getConversionFromKilometersPerLiter(String kpl, int decimalPlaces);

    void getConversionFromLitersPer100Kilometers(String l100km, int decimalPlaces);

    void getConversionFromUKMilesPerGallon(String ukmpg, int decimalPlaces);

    void getConversionFromUSMilesPerGallon(String usmpg, int decimalPlaces);
}
