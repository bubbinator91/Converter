package com.bubbinator91.converter.presenters.base;

import com.bubbinator91.converter.models.TemperatureModel.TemperatureValues;
import com.bubbinator91.converter.presenters.TemperaturePresenter;

/**
 * The base presenter for the {@link TemperaturePresenter} that extends the functionality of the
 * {@link BasePresenter} class. Adds abstract methods that are specific to the
 * {@link TemperaturePresenter} class.
 *
 * @param <M>   The type of model that will be used; passed through to the {@link BasePresenter}.
 * @param <V>   The type of view that will be used; passed through to the {@link BasePresenter}.
 */
public abstract class BaseTemperaturePresenter<M, V> extends BasePresenter<M, V> {
    protected abstract void updateViewExceptSource(TemperatureValues source);

    protected abstract void updateViewForError(Throwable error, TemperatureValues source);
}
