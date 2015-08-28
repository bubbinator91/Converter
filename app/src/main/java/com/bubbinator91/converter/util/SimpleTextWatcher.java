package com.bubbinator91.converter.util;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * A simple wrapper for the TextWatcher interface that provides empty implementations of each of the
 * methods. This way, if you are only implementing functionality in one of the methods (say, in
 * afterTextChanged()), you only need to override one method. This allows the overall reduction of
 * non-functional code and makes things more readable.
 */
public class SimpleTextWatcher implements TextWatcher {
    @Override
    public void afterTextChanged(Editable s) {}

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}
}
