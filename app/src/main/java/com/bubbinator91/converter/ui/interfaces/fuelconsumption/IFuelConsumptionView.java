package com.bubbinator91.converter.ui.interfaces.fuelconsumption;

import com.bubbinator91.converter.ui.interfaces.base.IConverterView;

import java.util.List;

/**
 * View interface for the {@link com.bubbinator91.converter.ui.fragments.FuelConsumptionFragment}
 */
public interface IFuelConsumptionView extends IConverterView {
    void displayConversionFromKilometersPerLiterResults(List<String> results);

    void displayConversionFromKilometersPerLiterError(Throwable error);

    void displayConversionFromLitersPer100KilometersResults(List<String> results);

    void displayConversionFromLitersPer100KilometersError(Throwable error);

    void displayConversionFromUKMilesPerGallonResults(List<String> results);

    void displayConversionFromUKMilesPerGallonError(Throwable error);

    void displayConversionFromUSMilesPerGallonResults(List<String> results);

    void displayConversionFromUSMilesPerGallonError(Throwable error);
}
