package com.bubbinator91.converter.interfaces.base;

/**
 * View interface for any fragment that does conversion
 */
public interface IConverterView extends IView {
    void addTextChangedListeners(String callingClassName);

    void removeTextChangedListeners(String callingClassName);
}
