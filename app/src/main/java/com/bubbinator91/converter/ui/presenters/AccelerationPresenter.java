package com.bubbinator91.converter.ui.presenters;

import com.bubbinator91.conversion.acceleration.Acceleration;
import com.bubbinator91.converter.ui.interfaces.acceleration.IAccelerationPresenter;
import com.bubbinator91.converter.ui.interfaces.acceleration.IAccelerationView;
import com.bubbinator91.converter.util.Globals;

import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Implementation of the {@link IAccelerationPresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.AccelerationFragment}
 */
public class AccelerationPresenter implements IAccelerationPresenter {

    private final Acceleration acceleration;
    private final Scheduler mainScheduler;
    private final Scheduler computationScheduler;

    private IAccelerationView mAccelerationView;

    public AccelerationPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                                 @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler,
                                 Acceleration acceleration) {
        this.mainScheduler = mainScheduler;
        this.computationScheduler = computationScheduler;
        this.acceleration = acceleration;
    }

    @Override
    public void registerView(IAccelerationView activity) {
        mAccelerationView = activity;
    }

    @Override
    public void getConversionFromCentimetersPerSecondSquaredResults(String cmpss, int decimalPlaces) {
        Observable.just(acceleration.CentimetersPerSecondSquared().toAll(cmpss, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mAccelerationView
                                .displayConversionFromCentimetersPerSecondSquared(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromFeetPerSecondSquaredResults(String fpss, int decimalPlaces) {
        Observable.just(acceleration.FeetPerSecondSquared().toAll(fpss, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mAccelerationView
                                .displayConversionFromFeetPerSecondSquared(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromMetersPerSecondSquaredResults(String mpss, int decimalPlaces) {
        Observable.just(acceleration.MetersPerSecondSquared().toAll(mpss, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mAccelerationView
                                .displayConversionFromMetersPerSecondSquared(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromStandardGravity(String sg, int decimalPlaces) {
        Observable.just(acceleration.StandardGravity().toAll(sg, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mAccelerationView
                                .displayConversionFromStandardGravity(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }
}
