package com.bubbinator91.converter.ui.presenters;

import com.bubbinator91.conversion.acceleration.Acceleration;
import com.bubbinator91.conversion.util.ValueBelowZeroException;
import com.bubbinator91.converter.ui.interfaces.acceleration.IAccelerationPresenter;
import com.bubbinator91.converter.ui.interfaces.acceleration.IAccelerationView;
import com.bubbinator91.converter.util.Globals;

import java.util.List;

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
        Observable.<List<String>>create(subscriber -> {
            try {
                subscriber.onNext(acceleration.CentimetersPerSecondSquared().toAll(cmpss, decimalPlaces));
            } catch (NumberFormatException | ValueBelowZeroException e) {
                subscriber.onError(e);
            }
        })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mAccelerationView::displayConversionFromCentimetersPerSecondSquaredResults,
                        mAccelerationView::displayConversionFromCentimetersPerSecondSquaredError
                );
    }

    @Override
    public void getConversionFromFeetPerSecondSquaredResults(String fpss, int decimalPlaces) {
        Observable.<List<String>>create(subscriber -> {
            try {
                subscriber.onNext(acceleration.FeetPerSecondSquared().toAll(fpss, decimalPlaces));
            } catch (NumberFormatException | ValueBelowZeroException e) {
                subscriber.onError(e);
            }
        })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mAccelerationView::displayConversionFromFeetPerSecondSquaredResults,
                        mAccelerationView::displayConversionFromFeetPerSecondSquaredError
                );
    }

    @Override
    public void getConversionFromMetersPerSecondSquaredResults(String mpss, int decimalPlaces) {
        Observable.<List<String>>create(subscriber -> {
            try {
                subscriber.onNext(acceleration.MetersPerSecondSquared().toAll(mpss, decimalPlaces));
            } catch (NumberFormatException | ValueBelowZeroException e) {
                subscriber.onError(e);
            }
        })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mAccelerationView::displayConversionFromMetersPerSecondSquaredResults,
                        mAccelerationView::displayConversionFromMetersPerSecondSquaredError
                );
    }

    @Override
    public void getConversionFromStandardGravity(String sg, int decimalPlaces) {
        Observable.<List<String>>create(subscriber -> {
            try {
                subscriber.onNext(acceleration.StandardGravity().toAll(sg, decimalPlaces));
            } catch (NumberFormatException | ValueBelowZeroException e) {
                subscriber.onError(e);
            }
        })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mAccelerationView::displayConversionFromStandardGravityResults,
                        mAccelerationView::displayConversionFromStandardGravityError
                );
    }
}
