package com.bubbinator91.converter.ui.presenters;

import com.bubbinator91.conversion.temperature.Temperature;
import com.bubbinator91.converter.ui.interfaces.temperature.ITemperaturePresenter;
import com.bubbinator91.converter.ui.interfaces.temperature.ITemperatureView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Implementation of the {@link ITemperaturePresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.TemperatureFragment}
 */
public class TemperaturePresenter implements ITemperaturePresenter {

    private ITemperatureView mTemperatureView;

    private final Temperature temperature;

    public TemperaturePresenter(Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public void registerView(ITemperatureView activity) {
        mTemperatureView = activity;
    }

    @Override
    public void getConversionFromCelsiusResults(String celsius, int decimalPlaces) {
        Observable.just(temperature.Celsius().toAll(celsius, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
