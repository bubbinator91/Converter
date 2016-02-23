package com.bubbinator91.converter.presenters;

import com.bubbinator91.converter.conversion.temperature.Celsius;
import com.bubbinator91.converter.conversion.temperature.Fahrenheit;
import com.bubbinator91.converter.conversion.temperature.Kelvin;
import com.bubbinator91.converter.interfaces.presenter.ITemperaturePresenter;
import com.bubbinator91.converter.interfaces.view.ITemperatureView;
import com.bubbinator91.converter.util.Globals;

import javax.inject.Named;

import rx.Scheduler;

/**
 * Implementation of the {@link ITemperaturePresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.TemperatureFragment}
 */
public class TemperaturePresenter implements ITemperaturePresenter {
    private final Scheduler mainScheduler;
    private final Scheduler computationScheduler;

    private ITemperatureView temperatureView;

    public TemperaturePresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                                @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler) {
        this.mainScheduler = mainScheduler;
        this.computationScheduler = computationScheduler;
    }

    @Override
    public void registerView(ITemperatureView view) {
        temperatureView = view;
    }

    @Override
    public void getConversionFromCelsius(String celsius, int decimalPlaces) {
        Celsius.toAll(celsius, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        temperatureView::displayConversionFromCelsiusResults,
                        temperatureView::displayConversionFromCelsiusError
                );
    }

    @Override
    public void getConversionFromFahrenheit(String fahrenheit, int decimalPlaces) {
        Fahrenheit.toAll(fahrenheit, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        temperatureView::displayConversionFromFahrenheitResults,
                        temperatureView::displayConversionFromFahrenheitError
                );
    }

    @Override
    public void getConversionFromKelvin(String kelvin, int decimalPlaces) {
        Kelvin.toAll(kelvin, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        temperatureView::displayConversionFromKelvinResults,
                        temperatureView::displayConversionFromKelvinError
                );
    }
}
