package com.bubbinator91.converter.ui.interfaces.length;

import com.bubbinator91.converter.ui.interfaces.base.IConverterView;

import java.util.List;

/**
 * View interface for the {@link com.bubbinator91.converter.ui.fragments.LengthFragment}
 */
public interface ILengthView extends IConverterView {
    void displayConversionFromCentimetersResults(List<String> results, int errorCode);

    void displayConversionFromFeetResults(List<String> results, int errorCode);

    void displayConversionFromInchesResults(List<String> results, int errorCode);

    void displayConversionFromKilometersResults(List<String> results, int errorCode);

    void displayConversionFromMetersResults(List<String> results, int errorCode);

    void displayConversionFromMilesResults(List<String> results, int errorCode);

    void displayConversionFromMillimetersResults(List<String> results, int errorCode);

    void displayConversionFromYardsResults(List<String> results, int errorCode);
}
