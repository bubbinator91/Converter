package com.bubbinator91.converter.dagger.modules;

import com.bubbinator91.converter.conversion.acceleration.Acceleration;
import com.bubbinator91.converter.conversion.datatransferspeed.DataTransferSpeed;
import com.bubbinator91.converter.conversion.fuelconsumption.FuelConsumption;
import com.bubbinator91.converter.conversion.length.Length;
import com.bubbinator91.converter.conversion.speed.Speed;
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
}
