package com.bubbinator91.converter.ui.presenters;

import com.bubbinator91.conversion.speed.Speed;
import com.bubbinator91.converter.ui.interfaces.speed.SpeedPresenter;
import com.bubbinator91.converter.ui.interfaces.speed.SpeedView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Implementation of the {@link SpeedPresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.SpeedFragment}
 */
public class SpeedPresenterImpl implements SpeedPresenter {

    private SpeedView mSpeedView;

    private final Speed speed;

    public SpeedPresenterImpl(Speed speed) {
        this.speed = speed;
    }

    @Override
    public void registerView(SpeedView activity) {
        mSpeedView = activity;
    }

    @Override
    public void getConversionFromFeetPerSecondResults(String fps, int decimalPlaces) {
        Observable.just(speed.feetPerSecond().toAll(fps, decimalPlaces))
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
        Observable.just(speed.kilometersPerHour().toAll(kph, decimalPlaces))
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
        Observable.just(speed.knots().toAll(knots, decimalPlaces))
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
        Observable.just(speed.metersPerSecond().toAll(mps, decimalPlaces))
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
        Observable.just(speed.milesPerHour().toAll(mph, decimalPlaces))
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
