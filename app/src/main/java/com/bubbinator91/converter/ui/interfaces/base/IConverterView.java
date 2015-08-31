package com.bubbinator91.converter.ui.interfaces.base;

/**
 * View interface for any fragment that does conversion
 */
public interface IConverterView extends IView {
    void addTextChangedListeners(String callingClassName);

    void removeTextChangedListeners(String callingClassName);
}
