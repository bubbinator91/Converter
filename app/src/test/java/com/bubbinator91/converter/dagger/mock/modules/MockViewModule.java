package com.bubbinator91.converter.dagger.mock.modules;

import com.bubbinator91.converter.dagger.scopes.ActivityScope;
import com.bubbinator91.converter.mock.views.MockTemperatureView;
import com.bubbinator91.converter.ui.interfaces.temperature.ITemperaturePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MockViewModule {
    @Provides
    @ActivityScope
    MockTemperatureView providesMockTemperatureView(ITemperaturePresenter presenter) {
        MockTemperatureView mockTemperatureView = new MockTemperatureView();
        presenter.registerView(mockTemperatureView);
        return mockTemperatureView;
    }
}
