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
    private final String TAG = DialogPreference.class.getSimpleName();

    private NumberPicker numberPicker;
    private Context context;

    public NumberPickerPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Timber.tag(TAG).i("Entered");

        setDialogLayoutResource(R.layout.util_preference_number_picker);
        setPersistent(false);
        this.context = context;
    }

    @Override
    protected void onBindDialogView(@NonNull View view) {
        super.onBindDialogView(view);
        Timber.tag(TAG + ".onBindDialogView").i("Entered");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        numberPicker = ((NumberPicker)view.findViewById(R.id.preferences_number_picker));
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(20);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setValue(
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
            editor.putString(Globals.PREFERENCE_DECIMAL_PLACES, Integer.toString(numberPicker.getValue()));
            editor.commit();
        }
    }

    @Override
    protected Object onGetDefaultValue(TypedArray typedArray, int index) {
        Timber.tag(TAG + ".onGetDefaultValue").i("Entered");
        return typedArray.getInteger(index, 8);
    }
}
