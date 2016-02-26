package com.bubbinator91.converter.dagger.components;

import com.bubbinator91.converter.dagger.modules.PresenterModule;
import com.bubbinator91.converter.dagger.modules.ThreadModule;
import com.bubbinator91.converter.dagger.mock.modules.MockViewModule;
import com.bubbinator91.converter.dagger.scopes.ActivityScope;
import com.bubbinator91.converter.tests.main.*;

import dagger.Component;

@ActivityScope
@Component(modules = {PresenterModule.class, MockViewModule.class, ThreadModule.class})
public interface TestClassInjector {
    void inject(AccelerationTest accelerationTest);

    void inject(DataTransferSpeedTest dataTransferSpeedTest);

    void inject(FuelConsumptionTest fuelConsumptionTest);

    void inject(LengthTest lengthTest);

    void inject(SpeedTest speedTest);

    void inject(TemperatureTest temperatureTest);
}
