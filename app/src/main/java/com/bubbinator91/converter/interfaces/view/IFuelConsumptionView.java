package com.bubbinator91.converter.interfaces.view;

import com.bubbinator91.converter.interfaces.base.IConverterView;
import com.bubbinator91.converter.views.fragments.FuelConsumptionFragment;

import java.util.List;

/**
 * View interface for the {@link FuelConsumptionFragment}
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
