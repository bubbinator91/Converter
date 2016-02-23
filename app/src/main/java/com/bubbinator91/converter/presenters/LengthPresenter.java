package com.bubbinator91.converter.presenters;

import com.bubbinator91.converter.conversion.length.Centimeters;
import com.bubbinator91.converter.conversion.length.Feet;
import com.bubbinator91.converter.conversion.length.Inches;
import com.bubbinator91.converter.conversion.length.Kilometers;
import com.bubbinator91.converter.conversion.length.Meters;
import com.bubbinator91.converter.conversion.length.Miles;
import com.bubbinator91.converter.conversion.length.Millimeters;
import com.bubbinator91.converter.conversion.length.Yards;
import com.bubbinator91.converter.interfaces.presenter.ILengthPresenter;
import com.bubbinator91.converter.interfaces.view.ILengthView;
import com.bubbinator91.converter.util.Globals;

import javax.inject.Named;

import rx.Scheduler;

/**
 * Implementation of the {@link ILengthPresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.LengthFragment}
 */
public class LengthPresenter implements ILengthPresenter {
    private final Scheduler mainScheduler;
    private final Scheduler computationScheduler;

    private ILengthView lengthView;

    public LengthPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                           @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler) {
        this.mainScheduler = mainScheduler;
        this.computationScheduler = computationScheduler;
    }

    @Override
    public void registerView(ILengthView view) {
        lengthView = view;
    }
    
    @Override
    public void unregisterView() {
        lengthView = null;
    }

    @Override
    public void getConversionFromInches(String inches, int decimalPlaces) {
        Inches.toAll(inches, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        lengthView::displayConversionFromInchesResults,
                        lengthView::displayConversionFromInchesError
                );
    }

    @Override
    public void getConversionFromFeet(String feet, int decimalPlaces) {
        Feet.toAll(feet, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        lengthView::displayConversionFromFeetResults,
                        lengthView::displayConversionFromFeetError
                );
    }

    @Override
    public void getConversionFromYards(String yards, int decimalPlaces) {
        Yards.toAll(yards, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        lengthView::displayConversionFromYardsResults,
                        lengthView::displayConversionFromYardsError
                );
    }

    @Override
    public void getConversionFromMiles(String miles, int decimalPlaces) {
        Miles.toAll(miles, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        lengthView::displayConversionFromMilesResults,
                        lengthView::displayConversionFromMilesError
                );
    }

    @Override
    public void getConversionFromMillimeters(String millimeters, int decimalPlaces) {
        Millimeters.toAll(millimeters, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        lengthView::displayConversionFromMillimetersResults,
                        lengthView::displayConversionFromMillimetersError
                );
    }

    @Override
    public void getConversionFromCentimeters(String centimeters, int decimalPlaces) {
        Centimeters.toAll(centimeters, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        lengthView::displayConversionFromCentimetersResults,
                        lengthView::displayConversionFromCentimetersError
                );
    }

    @Override
    public void getConversionFromMeters(String meters, int decimalPlaces) {
        Meters.toAll(meters, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        lengthView::displayConversionFromMetersResults,
                        lengthView::displayConversionFromMetersError
                );
    }

    @Override
    public void getConversionFromKilometers(String kilometers, int decimalPlaces) {
        Kilometers.toAll(kilometers, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        lengthView::displayConversionFromKilometersResults,
                        lengthView::displayConversionFromKilometersError
                );
    }
}
