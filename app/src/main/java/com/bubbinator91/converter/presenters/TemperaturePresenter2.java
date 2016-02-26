package com.bubbinator91.converter.presenters;

import com.bubbinator91.converter.conversion.temperature.Celsius;
import com.bubbinator91.converter.conversion.temperature.Fahrenheit;
import com.bubbinator91.converter.conversion.temperature.Kelvin;
import com.bubbinator91.converter.interfaces.presenter.ITemperaturePresenter2;
import com.bubbinator91.converter.interfaces.view.ITemperatureView2;
import com.bubbinator91.converter.models.TemperatureModel;
import com.bubbinator91.converter.models.TemperatureModel.TemperatureValues;
import com.bubbinator91.converter.presenters.base.BaseTemperaturePresenter;
import com.bubbinator91.converter.util.Globals;

import rx.Scheduler;

import javax.inject.Named;

import java.util.List;

public class TemperaturePresenter2
        extends BaseTemperaturePresenter<TemperatureModel, ITemperatureView2>
        implements ITemperaturePresenter2 {
    private final Scheduler mainScheduler, computationScheduler;

    public TemperaturePresenter2(
            @Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
            @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler) {
        this.mainScheduler = mainScheduler;
        this.computationScheduler = computationScheduler;
    }

    @Override
    public void onResume() {
        setModel(getView().loadModel());
    }

    @Override
    public void onPause() {
        getView().saveModel(getModel());
    }

    @Override
    protected void updateView() {
        getView().showNewValuesFromModel(getModel());
    }

    @Override
    public void registerView(ITemperatureView2 view) {
        setView(view);
    }

    @Override
    public void unregisterView() {
        unsetView();
    }

    @Override
    public void updateModel(List<String> valuesList) {
        getModel().setCelsius(valuesList.get(0));
        getModel().setFahrenheit(valuesList.get(1));
        getModel().setKelvin(valuesList.get(2));
    }

    @Override
    public void afterCelsiusTextChanged(String celsius, int decimalPlaces) {
        Celsius.toAll(celsius, decimalPlaces)
                .filter(resultsList -> resultsList != null)
                .map(resultsList -> {
                    resultsList.add(0, celsius);
                    updateModel(resultsList);
                    return null;
                })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(notUsed -> {
                    getView().showNewValuesFromModelExcludingSource(getModel(), TemperatureValues.celsius);
                }, error -> {
                    getView().showErrorForSource(error, TemperatureValues.celsius);
                });
    }

    @Override
    public void afterFahrenheitTextChanged(String fahrenheit, int decimalPlaces) {
        Fahrenheit.toAll(fahrenheit, decimalPlaces)
                .filter(resultsList -> resultsList != null)
                .map(resultsList -> {
                    resultsList.add(1, fahrenheit);
                    updateModel(resultsList);
                    return null;
                })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(notUsed -> {
                    getView().showNewValuesFromModelExcludingSource(getModel(), TemperatureValues.fahrenheit);
                }, error -> {
                    getView().showErrorForSource(error, TemperatureValues.fahrenheit);
                });
    }

    @Override
    public void afterKelvinTextChanged(String kelvin, int decimalPlaces) {
        Kelvin.toAll(kelvin, decimalPlaces)
                .filter(resultsList -> resultsList != null)
                .map(resultsList -> {
                    resultsList.add(kelvin);
                    updateModel(resultsList);
                    return null;
                })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(notUsed -> {
                    getView().showNewValuesFromModelExcludingSource(getModel(), TemperatureValues.kelvin);
                }, error -> {
                    getView().showErrorForSource(error, TemperatureValues.kelvin);
                });
    }
}
