package com.bubbinator91.converter.dagger.modules;

import com.bubbinator91.conversion.acceleration.Acceleration;
import com.bubbinator91.conversion.datatransferspeed.DataTransferSpeed;
import com.bubbinator91.conversion.fuelconsumption.FuelConsumption;
import com.bubbinator91.conversion.length.Length;
import com.bubbinator91.conversion.speed.Speed;
import com.bubbinator91.conversion.temperature.Temperature;
import com.bubbinator91.converter.dagger.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * A dagger {@link Module} that provides the specific implementation of each conversion api to each
 * Presenter.
 */
@Module
public class ConversionModule {
    @Provides
    @ActivityScope
    protected Acceleration providesAcceleration() {
        return new Acceleration();
    }

    @Provides
    @ActivityScope
    protected DataTransferSpeed providesDataTransferSpeed() {
        return new DataTransferSpeed();
    }

    @Provides
    @ActivityScope
    protected FuelConsumption providesFuelConsumption() {
        return new FuelConsumption();
    }

    @Provides
    @ActivityScope
    protected Length providesLength() {
        return new Length();
    }

    @Provides
    @ActivityScope
    protected Speed providesSpeed() {
        return new Speed();
    }

    @Provides
    @ActivityScope
    protected Temperature providesTemperature() {
        return new Temperature();
    }
}
