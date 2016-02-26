package com.bubbinator91.converter.dagger.components;

import com.bubbinator91.converter.dagger.modules.PresenterModule;
import com.bubbinator91.converter.dagger.modules.ThreadModule;
import com.bubbinator91.converter.dagger.scopes.ActivityScope;
import com.bubbinator91.converter.ui.fragments.AccelerationFragment;
import com.bubbinator91.converter.ui.fragments.DataTransferSpeedFragment;
import com.bubbinator91.converter.ui.fragments.FuelConsumptionFragment;
import com.bubbinator91.converter.ui.fragments.LengthFragment;
import com.bubbinator91.converter.ui.fragments.SpeedFragment;
import com.bubbinator91.converter.ui.fragments.TemperatureFragment;

import com.bubbinator91.converter.views.fragments.TemperatureFragment2;
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

    void inject(TemperatureFragment2 temperatureFragment2);
}
