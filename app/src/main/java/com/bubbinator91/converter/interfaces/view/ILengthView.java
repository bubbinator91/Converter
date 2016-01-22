package com.bubbinator91.converter.interfaces.view;

import com.bubbinator91.converter.interfaces.base.IConverterView;

import java.util.List;

/**
 * View interface for the {@link com.bubbinator91.converter.ui.fragments.LengthFragment}
 */
public interface ILengthView extends IConverterView {
    void displayConversionFromInchesResults(List<String> results);

    void displayConversionFromInchesError(Throwable error);

    void displayConversionFromFeetResults(List<String> results);

    void displayConversionFromFeetError(Throwable error);

    void displayConversionFromYardsResults(List<String> results);

    void displayConversionFromYardsError(Throwable error);

    void displayConversionFromMilesResults(List<String> results);

    void displayConversionFromMilesError(Throwable error);

    void displayConversionFromMillimetersResults(List<String> results);

    void displayConversionFromMillimetersError(Throwable error);

    void displayConversionFromCentimetersResults(List<String> results);

    void displayConversionFromCentimetersError(Throwable error);

    void displayConversionFromMetersResults(List<String> results);

    void displayConversionFromMetersError(Throwable error);

    void displayConversionFromKilometersResults(List<String> results);

    void displayConversionFromKilometersError(Throwable error);
}
