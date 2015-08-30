package com.bubbinator91.converter.dagger.modules;

import com.bubbinator91.conversion.acceleration.Acceleration;
import com.bubbinator91.conversion.temperature.Temperature;
import com.bubbinator91.converter.dagger.scopes.ActivityScope;
import com.bubbinator91.converter.ui.interfaces.acceleration.AccelerationPresenter;
import com.bubbinator91.converter.ui.interfaces.temperature.TemperaturePresenter;
import com.bubbinator91.converter.ui.presenters.AccelerationPresenterImpl;
import com.bubbinator91.converter.ui.presenters.TemperaturePresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * A dagger {@link Module} that provides the specific implementation of
 * {@link com.bubbinator91.converter.ui.interfaces.base.Presenter} to each specific fragment.
 */
@Module
public class PresenterModule {
    @Provides
    @ActivityScope
    AccelerationPresenter providesAccelerationPresenter(Acceleration acceleration) {
        return new AccelerationPresenterImpl(acceleration);
    }

    @Provides
    @ActivityScope
    TemperaturePresenter providesTemperaturePresenter(Temperature temperature) {
        return new TemperaturePresenterImpl(temperature);
    }
}
