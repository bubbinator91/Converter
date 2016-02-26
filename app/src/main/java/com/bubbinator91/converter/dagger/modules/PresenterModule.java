package com.bubbinator91.converter.dagger.modules;

import com.bubbinator91.converter.dagger.scopes.ActivityScope;
import com.bubbinator91.converter.interfaces.presenter.*;
import com.bubbinator91.converter.presenters.*;
import com.bubbinator91.converter.util.Globals;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * A dagger {@link Module} that provides the specific implementation of
 * {@link com.bubbinator91.converter.interfaces.base.IPresenter} to each specific fragment.
 */
@Module
public class PresenterModule {
    @Provides
    @ActivityScope
    IAccelerationPresenter providesAccelerationPresenter(
            @Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
            @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler) {
        return new AccelerationPresenter(mainScheduler, computationScheduler);
    }

    @Provides
    @ActivityScope
    IDataTransferSpeedPresenter providesDataTransferSpeedPresenter(
            @Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
            @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler) {
        return new DataTransferSpeedPresenter(mainScheduler, computationScheduler);
    }

    @Provides
    @ActivityScope
    IFuelConsumptionPresenter providesFuelConsumptionPresenter(
            @Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
            @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler) {
        return new FuelConsumptionPresenter(mainScheduler, computationScheduler);
    }

    @Provides
    @ActivityScope
    ILengthPresenter providesLengthPresenter(
            @Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
            @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler) {
        return new LengthPresenter(mainScheduler, computationScheduler);
    }

    @Provides
    @ActivityScope
    ISpeedPresenter providesSpeedPresenter(
            @Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
            @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler) {
        return new SpeedPresenter(mainScheduler, computationScheduler);
    }

    @Provides
    @ActivityScope
    ITemperaturePresenter providesTemperaturePresenter(
            @Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
            @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler) {
        return new TemperaturePresenter(mainScheduler, computationScheduler);
    }
}
