package com.bubbinator91.converter.ui.presenters;

import com.bubbinator91.conversion.temperature.Celsius;
import com.bubbinator91.conversion.temperature.Fahrenheit;
import com.bubbinator91.conversion.temperature.Kelvin;
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

    public void registerView(TemperatureView activity) {
        mTemperatureView = activity;
    }

    public void getConversionFromCelsiusResults(String celsius, int decimalPlaces) {
        Observable.just(Celsius.toAll(celsius, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mTemperatureView
                                .displayConversionFromCelsiusResults(conversionResults.first,
                                        conversionResults.second);
                    }
                });
    }

    public void getConversionFromFahrenheitResults(String fahrenheit, int decimalPlaces) {
        Observable.just(Fahrenheit.toAll(fahrenheit, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mTemperatureView
                                .displayConversionFromFahrenheitResults(conversionResults.first,
                                        conversionResults.second);
                    }
                });
    }

    public void getConversionFromKelvinResults(String kelvin, int decimalPlaces) {
        Observable.just(Kelvin.toAll(kelvin, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mTemperatureView
                                .displayConversionFromKelvinResults(conversionResults.first,
                                        conversionResults.second);
                    }
                });
    }
}
