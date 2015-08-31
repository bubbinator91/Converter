package com.bubbinator91.converter.ui.interfaces.length;

import com.bubbinator91.converter.ui.interfaces.base.ConverterPresenter;

/**
 * Presenter interface for the presenter of
 * {@link com.bubbinator91.converter.ui.fragments.LengthFragment}
 */
public interface ILengthPresenter extends ConverterPresenter<ILengthView> {
    void getConversionFromCentimetersResults(String centimeters, int decimalPlaces);

    void getConversionFromFeetResults(String feet, int decimalPlaces);

    void getConversionFromInchesResults(String inches, int decimalPlaces);

    void getConversionFromKilometersResults(String kilometers, int decimalPlaces);

    void getConversionFromMetersResults(String meters, int decimalPlaces);

    void getConversionFromMilesResults(String miles, int decimalPlaces);

    void getConversionFromMillimetersResults(String millimeters, int decimalPlaces);

    void getConversionFromYardsResults(String yards, int decimalPlaces);
}
