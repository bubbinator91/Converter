package com.bubbinator91.converter.interfaces.base;

/**
 * Base presenter interface for MVP
 */
public interface IPresenter2<V extends IView2> {
    void onResume();

    void onPause();

    void registerView(V view);

    void unregisterView();
}
