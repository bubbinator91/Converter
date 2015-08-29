package com.bubbinator91.converter.dagger.modules;

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
@ActivityScope
@Singleton
public class PresenterModule {
    @Provides
    AccelerationPresenter providesAccelerationPresenter() {
        return new AccelerationPresenterImpl();
    }

    @Provides
    TemperaturePresenter providesTemperaturePresenter() {
        return new TemperaturePresenterImpl();
    }
}
