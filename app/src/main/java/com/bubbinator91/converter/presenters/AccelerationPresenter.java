package com.bubbinator91.converter.presenters;

import com.bubbinator91.converter.conversion.acceleration.Acceleration;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;
import com.bubbinator91.converter.interfaces.presenter.IAccelerationPresenter;
import com.bubbinator91.converter.interfaces.view.IAccelerationView;
import com.bubbinator91.converter.util.Globals;

import java.util.List;

import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Implementation of the {@link IAccelerationPresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.AccelerationFragment}
 */
public class AccelerationPresenter implements IAccelerationPresenter {
    private final Scheduler mainScheduler;
    private final Scheduler computationScheduler;

    private IAccelerationView mAccelerationView;

    public AccelerationPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                                 @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler) {
        this.mainScheduler = mainScheduler;
        this.computationScheduler = computationScheduler;
    }

    @Override
    public void registerView(IAccelerationView activity) {
        mAccelerationView = activity;
    }

    @Override
    public void getConversionFromCentimetersPerSecondSquaredResults(String cmpss, int decimalPlaces) {
        Observable.<List<String>>create(subscriber -> {
            try {
                subscriber.onNext(Acceleration.centimetersPerSecondSquared().toAll(cmpss, decimalPlaces));
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
                subscriber.onNext(Acceleration.feetPerSecondSquared().toAll(fpss, decimalPlaces));
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
                subscriber.onNext(Acceleration.metersPerSecondSquared().toAll(mpss, decimalPlaces));
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
                subscriber.onNext(Acceleration.standardGravity().toAll(sg, decimalPlaces));
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
