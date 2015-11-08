package com.bubbinator91.converter.presenters;

import com.bubbinator91.conversion.temperature.Temperature;
import com.bubbinator91.conversion.util.ValueBelowZeroException;
import com.bubbinator91.converter.interfaces.presenter.ITemperaturePresenter;
import com.bubbinator91.converter.interfaces.view.ITemperatureView;
import com.bubbinator91.converter.util.Globals;

import java.util.List;

import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Implementation of the {@link ITemperaturePresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.TemperatureFragment}
 */
public class TemperaturePresenter implements ITemperaturePresenter {
    private final Scheduler mainScheduler;
    private final Scheduler computationScheduler;

    private ITemperatureView mTemperatureView;

    public TemperaturePresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                                @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler) {
        this.mainScheduler = mainScheduler;
        this.computationScheduler = computationScheduler;
    }

    @Override
    public void registerView(ITemperatureView activity) {
        mTemperatureView = activity;
    }

    @Override
    public void getConversionFromCelsiusResults(String celsius, int decimalPlaces) {
        Observable.<List<String>>create(subscriber -> {
            try {
                subscriber.onNext(Temperature.celsius().toAll(celsius, decimalPlaces));
            } catch (NumberFormatException | ValueBelowZeroException e) {
                subscriber.onError(e);
            }
        })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mTemperatureView::displayConversionFromCelsiusResults,
                        mTemperatureView::displayConversionFromCelsiusError
                );
    }

    @Override
    public void getConversionFromFahrenheitResults(String fahrenheit, int decimalPlaces) {
        Observable.<List<String>>create(subscriber -> {
            try {
                subscriber.onNext(Temperature.fahrenheit().toAll(fahrenheit, decimalPlaces));
            } catch (NumberFormatException | ValueBelowZeroException e) {
                subscriber.onError(e);
            }
        })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mTemperatureView::displayConversionFromFahrenheitResults,
                        mTemperatureView::displayConversionFromFahrenheitError
                );
    }

    @Override
    public void getConversionFromKelvinResults(String kelvin, int decimalPlaces) {
        Observable.<List<String>>create(subscriber -> {
            try {
                subscriber.onNext(Temperature.kelvin().toAll(kelvin, decimalPlaces));
            } catch (NumberFormatException | ValueBelowZeroException e) {
                subscriber.onError(e);
            }
        })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mTemperatureView::displayConversionFromKelvinResults,
                        mTemperatureView::displayConversionFromKelvinError
                );
    }
}
