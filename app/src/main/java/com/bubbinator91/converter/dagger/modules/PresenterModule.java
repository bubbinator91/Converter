package com.bubbinator91.converter.dagger.modules;

import com.bubbinator91.conversion.acceleration.Acceleration;
import com.bubbinator91.conversion.length.Length;
import com.bubbinator91.conversion.speed.Speed;
import com.bubbinator91.conversion.temperature.Temperature;
import com.bubbinator91.converter.dagger.scopes.ActivityScope;
import com.bubbinator91.converter.ui.interfaces.acceleration.AccelerationPresenter;
import com.bubbinator91.converter.ui.interfaces.length.ILengthPresenter;
import com.bubbinator91.converter.ui.interfaces.speed.SpeedPresenter;
import com.bubbinator91.converter.ui.interfaces.temperature.TemperaturePresenter;
import com.bubbinator91.converter.ui.presenters.AccelerationPresenterImpl;
import com.bubbinator91.converter.ui.presenters.LengthPresenter;
import com.bubbinator91.converter.ui.presenters.SpeedPresenterImpl;
import com.bubbinator91.converter.ui.presenters.TemperaturePresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * A dagger {@link Module} that provides the specific implementation of
 * {@link com.bubbinator91.converter.ui.interfaces.base.Presenter} to each specific fragment.
 */
@Module
public class PresenterModule {
    @Provides
    @ActivityScope
    AccelerationPresenter providesAccelerationPresenter(Acceleration acceleration) {
        return new AccelerationPresenterImpl(acceleration);
    }

    @Provides
    @ActivityScope
    ILengthPresenter providesLengthPresenter(Length length) {
        return new LengthPresenter(length);
    }

    @Provides
    @ActivityScope
    SpeedPresenter providesSpeedPresenter(Speed speed) {
        return new SpeedPresenterImpl(speed);
    }

    @Provides
    @ActivityScope
    TemperaturePresenter providesTemperaturePresenter(Temperature temperature) {
        return new TemperaturePresenterImpl(temperature);
    }
}
