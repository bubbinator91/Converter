package com.bubbinator91.converter.dagger.modules;

import com.bubbinator91.conversion.acceleration.Acceleration;
import com.bubbinator91.conversion.length.Length;
import com.bubbinator91.conversion.speed.Speed;
import com.bubbinator91.conversion.temperature.Temperature;
import com.bubbinator91.converter.dagger.scopes.ActivityScope;
import com.bubbinator91.converter.ui.interfaces.acceleration.IAccelerationPresenter;
import com.bubbinator91.converter.ui.interfaces.length.ILengthPresenter;
import com.bubbinator91.converter.ui.interfaces.speed.ISpeedPresenter;
import com.bubbinator91.converter.ui.interfaces.temperature.ITemperaturePresenter;
import com.bubbinator91.converter.ui.presenters.AccelerationPresenter;
import com.bubbinator91.converter.ui.presenters.LengthPresenter;
import com.bubbinator91.converter.ui.presenters.SpeedPresenter;
import com.bubbinator91.converter.ui.presenters.TemperaturePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * A dagger {@link Module} that provides the specific implementation of
 * {@link com.bubbinator91.converter.ui.interfaces.base.IPresenter} to each specific fragment.
 */
@Module
public class PresenterModule {
    @Provides
    @ActivityScope
    IAccelerationPresenter providesAccelerationPresenter(Acceleration acceleration) {
        return new AccelerationPresenter(acceleration);
    }

    @Provides
    @ActivityScope
    ILengthPresenter providesLengthPresenter(Length length) {
        return new LengthPresenter(length);
    }

    @Provides
    @ActivityScope
    ISpeedPresenter providesSpeedPresenter(Speed speed) {
        return new SpeedPresenter(speed);
    }

    @Provides
    @ActivityScope
    ITemperaturePresenter providesTemperaturePresenter(Temperature temperature) {
        return new TemperaturePresenter(temperature);
    }
}
