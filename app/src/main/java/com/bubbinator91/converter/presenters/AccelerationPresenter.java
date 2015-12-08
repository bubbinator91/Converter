package com.bubbinator91.converter.presenters;

import com.bubbinator91.converter.conversion.acceleration.CentimetersPerSecondSquared;
import com.bubbinator91.converter.conversion.acceleration.FeetPerSecondSquared;
import com.bubbinator91.converter.conversion.acceleration.MetersPerSecondSquared;
import com.bubbinator91.converter.conversion.acceleration.StandardGravity;
import com.bubbinator91.converter.interfaces.presenter.IAccelerationPresenter;
import com.bubbinator91.converter.interfaces.view.IAccelerationView;
import com.bubbinator91.converter.util.Globals;

import javax.inject.Named;

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
    public void getConversionFromCentimetersPerSecondSquared(String cmpss, int decimalPlaces) {
        CentimetersPerSecondSquared.toAll(cmpss, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mAccelerationView::displayConversionFromCentimetersPerSecondSquaredResults,
                        mAccelerationView::displayConversionFromCentimetersPerSecondSquaredError
                );
    }

    @Override
    public void getConversionFromFeetPerSecondSquared(String fpss, int decimalPlaces) {
        FeetPerSecondSquared.toAll(fpss, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mAccelerationView::displayConversionFromFeetPerSecondSquaredResults,
                        mAccelerationView::displayConversionFromFeetPerSecondSquaredError
                );
    }

    @Override
    public void getConversionFromMetersPerSecondSquared(String mpss, int decimalPlaces) {
        MetersPerSecondSquared.toAll(mpss, decimalPlaces)
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
        StandardGravity.toAll(sg, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mAccelerationView::displayConversionFromStandardGravityResults,
                        mAccelerationView::displayConversionFromStandardGravityError
                );
    }
}
