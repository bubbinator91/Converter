package com.bubbinator91.converter.ui.interfaces.fuelconsumption;

import com.bubbinator91.converter.ui.interfaces.base.IConverterView;

import java.util.List;

/**
 * View interface for the {@link com.bubbinator91.converter.ui.fragments.FuelConsumptionFragment}
 */
public interface IFuelConsumptionView extends IConverterView {
    void displayConversionFromKilometersPerLiterResults(List<String> results, int errorCode);

    void displayConversionFromLitersPer100KilometersResults(List<String> results, int errorCode);

    void displayConversionFromUKMilesPerGallonResults(List<String> results, int errorCode);

    void displayConversionFromUSMilesPerGallonResults(List<String> results, int errorCode);
}
