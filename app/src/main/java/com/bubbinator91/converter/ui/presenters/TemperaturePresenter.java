package com.bubbinator91.converter.ui.presenters;

import com.bubbinator91.conversion.temperature.Temperature;
import com.bubbinator91.converter.ui.interfaces.temperature.ITemperaturePresenter;
import com.bubbinator91.converter.ui.interfaces.temperature.ITemperatureView;
import com.bubbinator91.converter.util.Globals;

import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Implementation of the {@link ITemperaturePresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.TemperatureFragment}
 */
public class TemperaturePresenter implements ITemperaturePresenter {

    private final Temperature temperature;
    private final Scheduler mainScheduler;
    private final Scheduler computationScheduler;

    private ITemperatureView mTemperatureView;

    public TemperaturePresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                                @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler,
                                Temperature temperature) {
        this.mainScheduler = mainScheduler;
        this.computationScheduler = computationScheduler;
        this.temperature = temperature;
    }

    @Override
    public void registerView(ITemperatureView activity) {
        mTemperatureView = activity;
    }

    @Override
    public void getConversionFromCelsiusResults(String celsius, int decimalPlaces) {
        Observable.just(temperature.Celsius().toAll(celsius, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mTemperatureView
                                .displayConversionFromCelsiusResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromFahrenheitResults(String fahrenheit, int decimalPlaces) {
        Observable.just(temperature.Fahrenheit().toAll(fahrenheit, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mTemperatureView
                                .displayConversionFromFahrenheitResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromKelvinResults(String kelvin, int decimalPlaces) {
        Observable.just(temperature.Kelvin().toAll(kelvin, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mTemperatureView
                                .displayConversionFromKelvinResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }
}
