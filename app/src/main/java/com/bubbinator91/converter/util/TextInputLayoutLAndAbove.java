package com.bubbinator91.converter.util;

import android.content.Context;
import android.graphics.Canvas;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Custom class that extends TextInputLayout. Fixes a bug in the stock TextInputLayout where the
 * hint for the AppCompatEditText/EditText wouldn't render properly when the UI is first shown. When
 * the bug is fixed in the stock implementation, this class will be redundant and will be removed.
 *
 * Original class developed by GitHub user ljubisa987 and can be found at
 * (https://gist.github.com/ljubisa987/e33cd5597da07172c55d).
 */
public class TextInputLayoutLAndAbove extends TextInputLayout {
    private boolean isHintSet = false;
    private CharSequence mHint = null;

    public TextInputLayoutLAndAbove(Context context) { super(context); }

    public TextInputLayoutLAndAbove(Context context, AttributeSet attrs) { super(context, attrs); }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof AppCompatEditText) {
            mHint = ((AppCompatEditText) child).getHint();
        } else if (child instanceof EditText) {
            mHint = ((EditText) child).getHint();
        }

        super.addView(child, index, params);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isHintSet && ViewCompat.isLaidOut(this)) {
            setHint(null);

            CharSequence currentHint = getEditText().getHint();
            if ((currentHint != null) && (currentHint.length() > 0)) {
                mHint = currentHint;
            }

            setHint(mHint);
            isHintSet = true;
        }
    }
}
