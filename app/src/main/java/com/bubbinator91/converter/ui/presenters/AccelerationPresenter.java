package com.bubbinator91.converter.ui.presenters;

import com.bubbinator91.conversion.acceleration.Acceleration;
import com.bubbinator91.converter.ui.interfaces.acceleration.IAccelerationPresenter;
import com.bubbinator91.converter.ui.interfaces.acceleration.IAccelerationView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Implementation of the {@link AccelerationPresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.AccelerationFragment}
 */
public class AccelerationPresenter implements IAccelerationPresenter {

    private IAccelerationView mAccelerationView;

    private final Acceleration acceleration;

    public AccelerationPresenter(Acceleration acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public void registerView(IAccelerationView activity) {
        mAccelerationView = activity;
    }

    @Override
    public void getConversionFromCentimetersPerSecondSquaredResults(String cmpss, int decimalPlaces) {
        Observable.just(acceleration.centimetersPerSecondSquared().toAll(cmpss, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
        Observable.just(acceleration.feetPerSecondSquared().toAll(fpss, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
        Observable.just(acceleration.metersPerSecondSquared().toAll(mpss, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
        Observable.just(acceleration.standardGravity().toAll(sg, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
