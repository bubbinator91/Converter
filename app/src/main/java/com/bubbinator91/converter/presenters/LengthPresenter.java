package com.bubbinator91.converter.presenters;

import com.bubbinator91.converter.conversion.length.Length;
import com.bubbinator91.converter.interfaces.presenter.ILengthPresenter;
import com.bubbinator91.converter.interfaces.view.ILengthView;
import com.bubbinator91.converter.util.Globals;

import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Implementation of the {@link ILengthPresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.LengthFragment}
 */
public class LengthPresenter implements ILengthPresenter {

    private final Length length;
    private final Scheduler mainScheduler;
    private final Scheduler computationScheduler;

    private ILengthView mLengthView;

    public LengthPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                           @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler,
                           Length length) {
        this.mainScheduler = mainScheduler;
        this.computationScheduler = computationScheduler;
        this.length = length;
    }

    @Override
    public void registerView(ILengthView activity) {
        mLengthView = activity;
    }

    @Override
    public void getConversionFromCentimetersResults(String centimeters, int decimalPlaces) {
        Observable.just(length.Centimeters().toAll(centimeters, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mLengthView
                                .displayConversionFromCentimetersResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromFeetResults(String feet, int decimalPlaces) {
        Observable.just(length.Feet().toAll(feet, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mLengthView
                                .displayConversionFromFeetResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromInchesResults(String inches, int decimalPlaces) {
        Observable.just(length.Inches().toAll(inches, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mLengthView
                                .displayConversionFromInchesResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromKilometersResults(String kilometers, int decimalPlaces) {
        Observable.just(length.Kilometers().toAll(kilometers, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mLengthView
                                .displayConversionFromKilometersResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromMetersResults(String meters, int decimalPlaces) {
        Observable.just(length.Meters().toAll(meters, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mLengthView
                                .displayConversionFromMetersResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromMilesResults(String miles, int decimalPlaces) {
        Observable.just(length.Miles().toAll(miles, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mLengthView
                                .displayConversionFromMilesResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromMillimetersResults(String millimeters, int decimalPlaces) {
        Observable.just(length.Millimeters().toAll(millimeters, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mLengthView
                                .displayConversionFromMillimetersResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromYardsResults(String yards, int decimalPlaces) {
        Observable.just(length.Yards().toAll(yards, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mLengthView
                                .displayConversionFromYardsResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }
}
