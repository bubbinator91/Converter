package com.bubbinator91.converter.presenters;

import com.bubbinator91.converter.conversion.speed.FeetPerSecond;
import com.bubbinator91.converter.conversion.speed.KilometersPerHour;
import com.bubbinator91.converter.conversion.speed.Knots;
import com.bubbinator91.converter.conversion.speed.MetersPerSecond;
import com.bubbinator91.converter.conversion.speed.MilesPerHour;
import com.bubbinator91.converter.interfaces.presenter.ISpeedPresenter;
import com.bubbinator91.converter.interfaces.view.ISpeedView;
import com.bubbinator91.converter.util.Globals;

import javax.inject.Named;

import rx.Scheduler;

/**
 * Implementation of the {@link ISpeedPresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.SpeedFragment}
 */
public class SpeedPresenter implements ISpeedPresenter {

    private final Scheduler mainScheduler;
    private final Scheduler computationScheduler;

    private ISpeedView mSpeedView;

    public SpeedPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                          @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler) {
        this.mainScheduler = mainScheduler;
        this.computationScheduler = computationScheduler;
    }

    @Override
    public void registerView(ISpeedView activity) {
        mSpeedView = activity;
    }

    @Override
    public void getConversionFromFeetPerSecond(String fps, int decimalPlaces) {
        FeetPerSecond.toAll(fps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(
                        mSpeedView::displayConversionFromFeetPerSecondResults,
                        mSpeedView::displayConversionFromFeetPerSecondError
                );
    }

    @Override
    public void getConversionFromKilometersPerHour(String kph, int decimalPlaces) {
        KilometersPerHour.toAll(kph, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(
                        mSpeedView::displayConversionFromKilometersPerHourResults,
                        mSpeedView::displayConversionFromKilometersPerHourError
                );
    }

    @Override
    public void getConversionFromKnots(String knots, int decimalPlaces) {
        Knots.toAll(knots, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(
                        mSpeedView::displayConversionFromKnotsResults,
                        mSpeedView::displayConversionFromKnotsError
                );
    }

    @Override
    public void getConversionFromMetersPerSecond(String mps, int decimalPlaces) {
        MetersPerSecond.toAll(mps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(
                        mSpeedView::displayConversionFromMetersPerSecondResults,
                        mSpeedView::displayConversionFromMetersPerSecondError
                );
    }

    @Override
    public void getConversionFromMilesPerHour(String mph, int decimalPlaces) {
        MilesPerHour.toAll(mph, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(
                        mSpeedView::displayConversionFromMilesPerHourResults,
                        mSpeedView::displayConversionFromMilesPerHourError
                );
    }
}
