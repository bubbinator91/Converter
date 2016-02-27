package com.bubbinator91.converter.presenters.base;

import com.bubbinator91.converter.models.FuelConsumptionModel.FuelConsumptionUnits;
import com.bubbinator91.converter.presenters.FuelConsumptionPresenter;

/**
 * The base presenter for the {@link FuelConsumptionPresenter} that extends the functionality of the
 * {@link BasePresenter} class. Adds abstract methods that are specific to the
 * {@link FuelConsumptionPresenter} class.
 *
 * @param <M>   The type of model that will be used; passed through to the {@link BasePresenter}.
 * @param <V>   The type of view that will be used; passed through to the {@link BasePresenter}.
 */
public abstract class BaseFuelConsumptionPresenter<M, V> extends BasePresenter<M, V> {
    protected abstract void updateViewExceptSource(FuelConsumptionUnits source);

    protected abstract void updateViewForError(Throwable error, FuelConsumptionUnits source);
}
