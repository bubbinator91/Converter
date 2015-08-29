package com.bubbinator91.converter.dagger.modules;

import com.bubbinator91.conversion.Acceleration;
import com.bubbinator91.converter.dagger.scopes.ActivityScope;
import com.bubbinator91.converter.ui.interfaces.acceleration.AccelerationPresenter;
import com.bubbinator91.converter.ui.interfaces.temperature.TemperaturePresenter;
import com.bubbinator91.converter.ui.presenters.AccelerationPresenterImpl;
import com.bubbinator91.converter.ui.presenters.TemperaturePresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * A dagger {@link Module} that provides the specific implementation of
 * {@link com.bubbinator91.converter.ui.interfaces.base.Presenter} to each specific fragment.
 */
@Module
@Singleton
public class PresenterModule {
    @Provides
    @ActivityScope
    AccelerationPresenter providesAccelerationPresenter(Acceleration acceleration) {
        return new AccelerationPresenterImpl(acceleration);
    }

    @Provides
    @ActivityScope
    TemperaturePresenter providesTemperaturePresenter() {
        return new TemperaturePresenterImpl();
    }
}
