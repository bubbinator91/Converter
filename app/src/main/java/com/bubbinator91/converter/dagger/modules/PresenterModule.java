package com.bubbinator91.converter.dagger.modules;

import com.bubbinator91.conversion.acceleration.Acceleration;
import com.bubbinator91.conversion.datatransferspeed.DataTransferSpeed;
import com.bubbinator91.conversion.fuelconsumption.FuelConsumption;
import com.bubbinator91.conversion.length.Length;
import com.bubbinator91.conversion.speed.Speed;
import com.bubbinator91.conversion.temperature.Temperature;
import com.bubbinator91.converter.dagger.scopes.ActivityScope;
import com.bubbinator91.converter.ui.interfaces.acceleration.IAccelerationPresenter;
import com.bubbinator91.converter.ui.interfaces.datatransferspeed.IDataTransferSpeedPresenter;
import com.bubbinator91.converter.ui.interfaces.fuelconsumption.IFuelConsumptionPresenter;
import com.bubbinator91.converter.ui.interfaces.length.ILengthPresenter;
import com.bubbinator91.converter.ui.interfaces.speed.ISpeedPresenter;
import com.bubbinator91.converter.ui.interfaces.temperature.ITemperaturePresenter;
import com.bubbinator91.converter.ui.presenters.AccelerationPresenter;
import com.bubbinator91.converter.ui.presenters.DataTransferSpeedPresenter;
import com.bubbinator91.converter.ui.presenters.FuelConsumptionPresenter;
import com.bubbinator91.converter.ui.presenters.LengthPresenter;
import com.bubbinator91.converter.ui.presenters.SpeedPresenter;
import com.bubbinator91.converter.ui.presenters.TemperaturePresenter;
import com.bubbinator91.converter.util.Globals;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * A dagger {@link Module} that provides the specific implementation of
 * {@link com.bubbinator91.converter.ui.interfaces.base.IPresenter} to each specific fragment.
 */
@Module
public class PresenterModule {
    @Provides
    @ActivityScope
    IAccelerationPresenter providesAccelerationPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                                                         @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler,
                                                         Acceleration acceleration) {
        return new AccelerationPresenter(mainScheduler, computationScheduler, acceleration);
    }

    @Provides
    @ActivityScope
    IDataTransferSpeedPresenter providesDataTransferSpeedPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                                                                   @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler,
                                                                   DataTransferSpeed dataTransferSpeed) {
        return new DataTransferSpeedPresenter(mainScheduler, computationScheduler, dataTransferSpeed);
    }

    @Provides
    @ActivityScope
    IFuelConsumptionPresenter providesFuelConsumptionPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                                                               @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler,
                                                               FuelConsumption fuelConsumption) {
        return new FuelConsumptionPresenter(mainScheduler, computationScheduler, fuelConsumption);
    }

    @Provides
    @ActivityScope
    ILengthPresenter providesLengthPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                                             @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler,
                                             Length length) {
        return new LengthPresenter(mainScheduler, computationScheduler, length);
    }

    @Provides
    @ActivityScope
    ISpeedPresenter providesSpeedPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                                           @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler,
                                           Speed speed) {
        return new SpeedPresenter(mainScheduler, computationScheduler, speed);
    }

    @Provides
    @ActivityScope
    ITemperaturePresenter providesTemperaturePresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                                                       @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler,
                                                       Temperature temperature) {
        return new TemperaturePresenter(mainScheduler, computationScheduler, temperature);
    }
}
