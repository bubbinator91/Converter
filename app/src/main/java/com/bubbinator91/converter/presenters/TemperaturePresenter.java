package com.bubbinator91.converter.presenters;

import com.bubbinator91.converter.conversion.temperature.*;
import com.bubbinator91.converter.interfaces.presenter.ITemperaturePresenter;
import com.bubbinator91.converter.interfaces.view.ITemperatureView;
import com.bubbinator91.converter.models.TemperatureModel;
import com.bubbinator91.converter.models.TemperatureModel.TemperatureUnits;
import com.bubbinator91.converter.presenters.base.BaseTemperaturePresenter;
import com.bubbinator91.converter.util.Globals;
import com.bubbinator91.converter.views.fragments.TemperatureFragment;

import java.util.List;

import javax.inject.Named;

import rx.Scheduler;

/**
 * Implementation of the {@link ITemperaturePresenter} interface for the
 * {@link TemperatureFragment}
 */
public class TemperaturePresenter
        extends BaseTemperaturePresenter<TemperatureModel, ITemperatureView>
        implements ITemperaturePresenter {
    private final Scheduler mainScheduler, computationScheduler;

    public TemperaturePresenter(
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
    protected void updateModel(List<String> valuesList) {
        getModel().setCelsius(valuesList.get(0));
        getModel().setFahrenheit(valuesList.get(1));
        getModel().setKelvin(valuesList.get(2));
    }

    @Override
    protected void updateView() {
        getView().showNewValuesFromModel(getModel());
    }

    @Override
    protected void updateViewExceptSource(TemperatureUnits source) {
        getView().showNewValuesFromModelExcludingSource(getModel(), source);
    }

    @Override
    protected void updateViewForError(Throwable error, TemperatureUnits source) {
        getView().showErrorForSource(error, source);
    }

    @Override
    public void registerView(ITemperatureView view) {
        setView(view);
    }

    @Override
    public void unregisterView() {
        unsetView();
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
                    updateViewExceptSource(TemperatureUnits.celsius);
                }, error -> {
                    updateViewForError(error, TemperatureUnits.celsius);
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
                    updateViewExceptSource(TemperatureUnits.fahrenheit);
                }, error -> {
                    updateViewForError(error, TemperatureUnits.fahrenheit);
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
                    updateViewExceptSource(TemperatureUnits.kelvin);
                }, error -> {
                    updateViewForError(error, TemperatureUnits.kelvin);
                });
    }
}
