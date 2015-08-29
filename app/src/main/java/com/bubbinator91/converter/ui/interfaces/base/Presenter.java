package com.bubbinator91.converter.ui.interfaces.base;

/**
 * Base presenter interface for MVP
 */
public interface Presenter<T extends PView> {
    void registerView(T activity);
}
