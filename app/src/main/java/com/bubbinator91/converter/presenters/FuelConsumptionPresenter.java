package com.bubbinator91.converter.presenters;

import com.bubbinator91.converter.conversion.fuelconsumption.*;
import com.bubbinator91.converter.interfaces.presenter.IFuelConsumptionPresenter;
import com.bubbinator91.converter.interfaces.view.IFuelConsumptionView;
import com.bubbinator91.converter.models.FuelConsumptionModel;
import com.bubbinator91.converter.models.FuelConsumptionModel.FuelConsumptionUnits;
import com.bubbinator91.converter.presenters.base.BasePresenter;
import com.bubbinator91.converter.util.Globals;
import com.bubbinator91.converter.views.fragments.FuelConsumptionFragment;

import java.util.List;

import javax.inject.Named;

import rx.Scheduler;

/**
 * Implementation of the {@link IFuelConsumptionPresenter} interface for the
 * {@link FuelConsumptionFragment}
 */
public class FuelConsumptionPresenter
        extends BasePresenter<FuelConsumptionModel, IFuelConsumptionView, FuelConsumptionUnits>
        implements IFuelConsumptionPresenter {
    private final Scheduler mainScheduler, computationScheduler;

    public FuelConsumptionPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
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
    protected void updateModel(List<String> values) {
        getModel().setUsmpg(values.get(0));
        getModel().setUkmpg(values.get(1));
        getModel().setKpl(values.get(2));
        getModel().setL100km(values.get(3));
    }

    @Override
    protected void updateView() {
        getView().showNewValuesFromModel(getModel());
    }

    @Override
    protected void updateViewExceptSource(FuelConsumptionUnits source) {
        getView().showNewValuesFromModelExcludingSource(getModel(), source);
    }

    @Override
    protected void updateViewForError(Throwable error, FuelConsumptionUnits source) {
        getView().showErrorForSource(error, source);
    }

    @Override
    public void registerView(IFuelConsumptionView view) {
        setView(view);
    }
    
    @Override
    public void unregisterView() {
        unsetView();
    }

    @Override
    public void afterUsmpgTextChanged(String usmpg, int decimalPlaces) {
        USMilesPerGallon.toAll(usmpg, decimalPlaces)
                .filter(resultsList -> resultsList != null)
                .map(resultsList -> {
                    resultsList.add(0, usmpg);
                    updateModel(resultsList);
                    return null;
                })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(notUsed -> {
                    updateViewExceptSource(FuelConsumptionUnits.usmpg);
                }, error -> {
                    updateViewForError(error, FuelConsumptionUnits.usmpg);
                });
    }

    @Override
    public void afterUkmpgTextChanged(String ukmpg, int decimalPlaces) {
        UKMilesPerGallon.toAll(ukmpg, decimalPlaces)
                .filter(resultsList -> resultsList != null)
                .map(resultsList -> {
                    resultsList.add(1, ukmpg);
                    updateModel(resultsList);
                    return null;
                })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(notUsed -> {
                    updateViewExceptSource(FuelConsumptionUnits.ukmpg);
                }, error -> {
                    updateViewForError(error, FuelConsumptionUnits.ukmpg);
                });
    }

    @Override
    public void afterKplTextChanged(String kpl, int decimalPlaces) {
        KilometersPerLiter.toAll(kpl, decimalPlaces)
                .filter(resultsList -> resultsList != null)
                .map(resultsList -> {
                    resultsList.add(2, kpl);
                    updateModel(resultsList);
                    return null;
                })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(notUsed -> {
                    updateViewExceptSource(FuelConsumptionUnits.kpl);
                }, error -> {
                    updateViewForError(error, FuelConsumptionUnits.kpl);
                });
    }

    @Override
    public void afterL100kmTextChanged(String l100km, int decimalPlaces) {
        LitersPer100Kilometers.toAll(l100km, decimalPlaces)
                .filter(resultsList -> resultsList != null)
                .map(resultsList -> {
                    resultsList.add(l100km);
                    updateModel(resultsList);
                    return null;
                })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(notUsed -> {
                    updateViewExceptSource(FuelConsumptionUnits.l100km);
                }, error -> {
                    updateViewForError(error, FuelConsumptionUnits.l100km);
                });
    }
}
