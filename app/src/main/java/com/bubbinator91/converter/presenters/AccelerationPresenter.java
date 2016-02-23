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

    private IAccelerationView accelerationView;

    public AccelerationPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                                 @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler) {
        this.mainScheduler = mainScheduler;
        this.computationScheduler = computationScheduler;
    }

    @Override
    public void registerView(IAccelerationView view) {
        accelerationView = view;
    }
    
    @Override
    public void unregisterView() {
        accelerationView = null;
    }

    @Override
    public void getConversionFromCentimetersPerSecondSquared(String cmpss, int decimalPlaces) {
        CentimetersPerSecondSquared.toAll(cmpss, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        accelerationView::displayConversionFromCentimetersPerSecondSquaredResults,
                        accelerationView::displayConversionFromCentimetersPerSecondSquaredError
                );
    }

    @Override
    public void getConversionFromFeetPerSecondSquared(String fpss, int decimalPlaces) {
        FeetPerSecondSquared.toAll(fpss, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        accelerationView::displayConversionFromFeetPerSecondSquaredResults,
                        accelerationView::displayConversionFromFeetPerSecondSquaredError
                );
    }

    @Override
    public void getConversionFromMetersPerSecondSquared(String mpss, int decimalPlaces) {
        MetersPerSecondSquared.toAll(mpss, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        accelerationView::displayConversionFromMetersPerSecondSquaredResults,
                        accelerationView::displayConversionFromMetersPerSecondSquaredError
                );
    }

    @Override
    public void getConversionFromStandardGravity(String sg, int decimalPlaces) {
        StandardGravity.toAll(sg, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        accelerationView::displayConversionFromStandardGravityResults,
                        accelerationView::displayConversionFromStandardGravityError
                );
    }
}
