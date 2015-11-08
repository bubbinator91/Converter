package com.bubbinator91.converter.interfaces.base;

/**
 * Base presenter interface for MVP
 */
public interface IPresenter<T extends IView> {
    void registerView(T activity);
}
