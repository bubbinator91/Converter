package com.bubbinator91.converter.ui.presenters;

import com.bubbinator91.conversion.speed.Speed;
import com.bubbinator91.converter.ui.interfaces.speed.ISpeedPresenter;
import com.bubbinator91.converter.ui.interfaces.speed.ISpeedView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Implementation of the {@link ISpeedPresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.SpeedFragment}
 */
public class SpeedPresenter implements ISpeedPresenter {

    private ISpeedView mSpeedView;

    private final Speed speed;

    public SpeedPresenter(Speed speed) {
        this.speed = speed;
    }

    @Override
    public void registerView(ISpeedView activity) {
        mSpeedView = activity;
    }

    @Override
    public void getConversionFromFeetPerSecondResults(String fps, int decimalPlaces) {
        Observable.just(speed.FeetPerSecond().toAll(fps, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mSpeedView
                                .displayConversionFromFeetPerSecondResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromKilometersPerHourResults(String kph, int decimalPlaces) {
        Observable.just(speed.KilometersPerHour().toAll(kph, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mSpeedView
                                .displayConversionFromKilometersPerHourResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromKnotsResults(String knots, int decimalPlaces) {
        Observable.just(speed.Knots().toAll(knots, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mSpeedView
                                .displayConversionFromKnotsResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromMetersPerSecondResults(String mps, int decimalPlaces) {
        Observable.just(speed.MetersPerSecond().toAll(mps, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mSpeedView
                                .displayConversionFromMetersPerSecondResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromMilesPerHourResults(String mph, int decimalPlaces) {
        Observable.just(speed.MilesPerHour().toAll(mph, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mSpeedView
                                .displayConversionFromMilesPerHourResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }
}
