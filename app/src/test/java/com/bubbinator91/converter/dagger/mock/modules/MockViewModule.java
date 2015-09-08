package com.bubbinator91.converter.dagger.mock.modules;

import com.bubbinator91.converter.dagger.scopes.ActivityScope;
import com.bubbinator91.converter.mock.views.MockAccelerationView;
import com.bubbinator91.converter.mock.views.MockDataTransferSpeedView;
import com.bubbinator91.converter.mock.views.MockLengthView;
import com.bubbinator91.converter.mock.views.MockTemperatureView;
import com.bubbinator91.converter.ui.interfaces.acceleration.IAccelerationPresenter;
import com.bubbinator91.converter.ui.interfaces.datatransferspeed.IDataTransferSpeedPresenter;
import com.bubbinator91.converter.ui.interfaces.length.ILengthPresenter;
import com.bubbinator91.converter.ui.interfaces.temperature.ITemperaturePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MockViewModule {
    @Provides
    @ActivityScope
    MockAccelerationView providesMockAccelerationView(IAccelerationPresenter presenter) {
        MockAccelerationView mockAccelerationView = new MockAccelerationView();
        presenter.registerView(mockAccelerationView);
        return mockAccelerationView;
    }

    @Provides
    @ActivityScope
    MockDataTransferSpeedView providesMockDataTransferSpeedView(IDataTransferSpeedPresenter presenter) {
        MockDataTransferSpeedView mockDataTransferSpeedView = new MockDataTransferSpeedView();
        presenter.registerView(mockDataTransferSpeedView);
        return mockDataTransferSpeedView;
    }

    @Provides
    @ActivityScope
    MockLengthView providesMockLengthView(ILengthPresenter presenter) {
        MockLengthView mockLengthView = new MockLengthView();
        presenter.registerView(mockLengthView);
        return mockLengthView;
    }

    @Provides
    @ActivityScope
    MockTemperatureView providesMockTemperatureView(ITemperaturePresenter presenter) {
        MockTemperatureView mockTemperatureView = new MockTemperatureView();
        presenter.registerView(mockTemperatureView);
        return mockTemperatureView;
    }
}
