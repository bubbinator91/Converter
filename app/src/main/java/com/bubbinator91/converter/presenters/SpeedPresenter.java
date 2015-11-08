package com.bubbinator91.converter.presenters;

import com.bubbinator91.conversion.speed.Speed;
import com.bubbinator91.converter.interfaces.presenter.ISpeedPresenter;
import com.bubbinator91.converter.interfaces.view.ISpeedView;
import com.bubbinator91.converter.util.Globals;

import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Implementation of the {@link ISpeedPresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.SpeedFragment}
 */
public class SpeedPresenter implements ISpeedPresenter {

    private final Speed speed;
    private final Scheduler mainScheduler;
    private final Scheduler computationScheduler;

    private ISpeedView mSpeedView;

    public SpeedPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                          @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler,
                          Speed speed) {
        this.mainScheduler = mainScheduler;
        this.computationScheduler = computationScheduler;
        this.speed = speed;
    }

    @Override
    public void registerView(ISpeedView activity) {
        mSpeedView = activity;
    }

    @Override
    public void getConversionFromFeetPerSecondResults(String fps, int decimalPlaces) {
        Observable.just(speed.FeetPerSecond().toAll(fps, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
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
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
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
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
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
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
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
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
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
