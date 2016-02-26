package com.bubbinator91.converter.dagger.mock.modules;

import com.bubbinator91.converter.dagger.scopes.ActivityScope;
import com.bubbinator91.converter.mock.views.*;
import com.bubbinator91.converter.interfaces.presenter.*;

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
    MockFuelConsumptionView providesMockFuelConsumptionView(IFuelConsumptionPresenter presenter) {
        MockFuelConsumptionView mockFuelConsumptionView = new MockFuelConsumptionView();
        presenter.registerView(mockFuelConsumptionView);
        return mockFuelConsumptionView;
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
    MockSpeedView providesMockSpeedView(ISpeedPresenter presenter) {
        MockSpeedView mockSpeedView = new MockSpeedView();
        presenter.registerView(mockSpeedView);
        return mockSpeedView;
    }

    @Provides
    @ActivityScope
    MockTemperatureView providesMockTemperatureView(ITemperaturePresenter presenter) {
        MockTemperatureView mockTemperatureView = new MockTemperatureView();
        presenter.registerView(mockTemperatureView);
        return mockTemperatureView;
    }
}
