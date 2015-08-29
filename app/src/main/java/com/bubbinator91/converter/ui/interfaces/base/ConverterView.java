package com.bubbinator91.converter.ui.interfaces.base;

/**
 * View interface for any fragment that does conversion
 */
public interface ConverterView extends PView {
    void addTextChangedListeners(String callingClassName);

    void removeTextChangedListeners(String callingClassName);
}
