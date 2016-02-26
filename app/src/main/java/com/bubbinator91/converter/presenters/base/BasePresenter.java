package com.bubbinator91.converter.presenters.base;

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

    protected abstract void updateView();
}
