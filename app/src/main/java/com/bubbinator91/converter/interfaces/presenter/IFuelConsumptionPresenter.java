package com.bubbinator91.converter.interfaces.presenter;

import com.bubbinator91.converter.interfaces.base.IPresenter2;
import com.bubbinator91.converter.interfaces.view.IFuelConsumptionView;
import com.bubbinator91.converter.views.fragments.FuelConsumptionFragment;

/**
 * Presenter interface for the presenter of {@link FuelConsumptionFragment}
 */
public interface IFuelConsumptionPresenter extends IPresenter2<IFuelConsumptionView> {
    void afterUsmpgTextChanged(String usmpg, int decimalPlaces);

    void afterUkmpgTextChanged(String ukmpg, int decimalPlaces);

    void afterKplTextChanged(String kpl, int decimalPlaces);

    void afterL100kmTextChanged(String l100km, int decimalPlaces);
}
