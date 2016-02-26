package com.bubbinator91.converter.presenters.base;

import java.util.List;

/**
 * The base presenter that all presenters should inherit from. Handles storing the model and the
 * view, provides methods for getting and setting the model and view, and makes sure that classes
 * that inherit from this provide methods for updating the model and view.
 *
 * @param <M>   The type of model that will be used.
 * @param <V>   The type of view that will be used.
 */
public abstract class BasePresenter<M, V> {
    private M model;
    private V view;

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
        if (setupDone()) {
            updateView();
        }
    }

    public void unsetView() {
        this.view = null;
    }

    public M getModel() {
        return model;
    }

    public void setModel(M model) {
        this.model = model;
        if (setupDone()) {
            updateView();
        }
    }

    protected boolean setupDone() {
        return (model != null) && (view != null);
    }

    protected abstract void updateModel(List<String> values);

    protected abstract void updateView();
}
