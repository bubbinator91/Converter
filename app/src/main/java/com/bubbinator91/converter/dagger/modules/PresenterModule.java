package com.bubbinator91.converter.dagger.modules;

import com.bubbinator91.converter.dagger.scopes.ActivityScope;
import com.bubbinator91.converter.ui.interfaces.temperature.TemperaturePresenter;
import com.bubbinator91.converter.ui.presenters.TemperaturePresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * A dagger {@link Module} that provides the specific implementation of
 * {@link com.bubbinator91.converter.ui.interfaces.base.Presenter} to each specific fragment.
 */
@Module
@ActivityScope
public class PresenterModule {
    @Provides
    TemperaturePresenter providesTemperaturePresenter() {
        return new TemperaturePresenterImpl();
    }
}
