package com.bubbinator91.converter.ui.presenters;

import com.bubbinator91.conversion.length.Length;
import com.bubbinator91.converter.ui.interfaces.length.ILengthPresenter;
import com.bubbinator91.converter.ui.interfaces.length.ILengthView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Implementation of the {@link ILengthPresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.LengthFragment}
 */
public class LengthPresenter implements ILengthPresenter {

    private ILengthView mLengthView;

    private final Length length;

    public LengthPresenter(Length length) {
        this.length = length;
    }

    @Override
    public void registerView(ILengthView activity) {
        mLengthView = activity;
    }

    @Override
    public void getConversionFromCentimetersResults(String centimeters, int decimalPlaces) {
        Observable.just(length.Centimeters().toAll(centimeters, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
