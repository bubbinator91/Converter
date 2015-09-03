package com.bubbinator91.converter.dagger.components;

import com.bubbinator91.converter.dagger.modules.ConversionModule;
import com.bubbinator91.converter.dagger.modules.PresenterModule;
import com.bubbinator91.converter.dagger.mock.modules.MockViewModule;
import com.bubbinator91.converter.dagger.modules.ThreadModule;
import com.bubbinator91.converter.dagger.scopes.ActivityScope;
import com.bubbinator91.converter.tests.AccelerationTest;
import com.bubbinator91.converter.tests.TemperatureTest;

import dagger.Component;

@ActivityScope
@Component(modules = {ConversionModule.class, PresenterModule.class, MockViewModule.class, ThreadModule.class})
public interface TestClassInjector {
    void inject(AccelerationTest accelerationTest);

    void inject(TemperatureTest temperatureTest);
}
