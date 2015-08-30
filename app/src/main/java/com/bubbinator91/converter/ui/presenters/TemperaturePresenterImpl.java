package com.bubbinator91.converter.ui.presenters;

import com.bubbinator91.conversion.temperature.Temperature;
import com.bubbinator91.converter.ui.interfaces.temperature.TemperaturePresenter;
import com.bubbinator91.converter.ui.interfaces.temperature.TemperatureView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Implementation of the {@link TemperaturePresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.TemperatureFragment}
 */
public class TemperaturePresenterImpl implements TemperaturePresenter {

    private TemperatureView mTemperatureView;

    private final Temperature temperature;

    public TemperaturePresenterImpl(Temperature temperature) {
        this.temperature = temperature;
    }

    public void registerView(TemperatureView activity) {
        mTemperatureView = activity;
    }

    public void getConversionFromCelsiusResults(String celsius, int decimalPlaces) {
        Observable.just(temperature.celsius().toAll(celsius, decimalPlaces))
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

    public void getConversionFromFahrenheitResults(String fahrenheit, int decimalPlaces) {
        Observable.just(temperature.fahrenheit().toAll(fahrenheit, decimalPlaces))
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

    public void getConversionFromKelvinResults(String kelvin, int decimalPlaces) {
        Observable.just(temperature.kelvin().toAll(kelvin, decimalPlaces))
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
