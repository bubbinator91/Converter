package com.bubbinator91.converter.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.NumberPicker;

import com.bubbinator91.converter.R;

import timber.log.Timber;

public class NumberPickerPreference extends DialogPreference {
    private final String TAG = "NumberPickerPreference";

    private NumberPicker mNumberPicker;
    private Context mContext;

    public NumberPickerPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Timber.tag(TAG).i("Entered");

        setDialogLayoutResource(R.layout.util_preference_number_picker);
        setPersistent(false);
        mContext = context;
    }

    @Override
    protected void onBindDialogView(@NonNull View view) {
        super.onBindDialogView(view);
        Timber.tag(TAG + ".onBindDialogView").i("Entered");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        mNumberPicker = ((NumberPicker)view.findViewById(R.id.preferences_number_picker));
        mNumberPicker.setMinValue(0);
        mNumberPicker.setMaxValue(20);
        mNumberPicker.setWrapSelectorWheel(true);
        mNumberPicker.setValue(
                Integer.parseInt(
                        sharedPreferences.getString(
                                Globals.PREFERENCE_DECIMAL_PLACES,
                                "8"
                        )
                )
        );
    }

    @Override
    protected void onDialogClosed(boolean result) {
        super.onDialogClosed(result);
        Timber.tag(TAG + ".onDialogClosed").i("Entered");
        Timber.tag(TAG + ".onDialogClosed").i("result = " + result);

        if (result) {
            SharedPreferences.Editor editor = getEditor();
            editor.putString(Globals.PREFERENCE_DECIMAL_PLACES, Integer.toString(mNumberPicker.getValue()));
            editor.commit();
        }
    }

    @Override
    protected Object onGetDefaultValue(TypedArray typedArray, int index) {
        Timber.tag(TAG + ".onGetDefaultValue").i("Entered");
        return typedArray.getInteger(index, 8);
    }
}
