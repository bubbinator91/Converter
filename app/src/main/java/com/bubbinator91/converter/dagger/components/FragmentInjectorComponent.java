package com.bubbinator91.converter.dagger.components;

import com.bubbinator91.converter.dagger.modules.PresenterModule;
import com.bubbinator91.converter.dagger.modules.ThreadModule;
import com.bubbinator91.converter.dagger.scopes.ActivityScope;
import com.bubbinator91.converter.views.fragments.*;

import dagger.Component;

/**
 * A dagger {@link Component} that facilitates the injection of dependencies into the fragments of
 * the app.
 */
@ActivityScope
@Component(modules = {PresenterModule.class, ThreadModule.class})
public interface FragmentInjectorComponent {
    void inject(AccelerationFragment accelerationFragment);

    void inject(DataTransferSpeedFragment dataTransferSpeedFragment);

    void inject(FuelConsumptionFragment fuelConsumptionFragment);

    void inject(LengthFragment lengthFragment);

    void inject(SpeedFragment speedFragment);

    void inject(TemperatureFragment temperatureFragment);
}
