package com.bubbinator91.converter.interfaces.presenter;

import com.bubbinator91.converter.interfaces.base.IConverterPresenter;
import com.bubbinator91.converter.interfaces.view.ILengthView;
import com.bubbinator91.converter.views.fragments.LengthFragment;

/**
 * Presenter interface for the presenter of {@link LengthFragment}
 */
public interface ILengthPresenter extends IConverterPresenter<ILengthView> {
    void getConversionFromInches(String inches, int decimalPlaces);

    void getConversionFromFeet(String feet, int decimalPlaces);

    void getConversionFromYards(String yards, int decimalPlaces);

    void getConversionFromMiles(String miles, int decimalPlaces);

    void getConversionFromMillimeters(String millimeters, int decimalPlaces);

    void getConversionFromCentimeters(String centimeters, int decimalPlaces);

    void getConversionFromMeters(String meters, int decimalPlaces);

    void getConversionFromKilometers(String kilometers, int decimalPlaces);
}
