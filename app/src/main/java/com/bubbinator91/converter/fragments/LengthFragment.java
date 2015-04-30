package com.bubbinator91.converter.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;

/**
 * Inch, Foot, Yard, Mile, Millimeter, Centimeter, Meter, Kilometer
 * Conversions comply with the conversions through Google.com
 */

// TODO Update to use global variables
// TODO Improve performance by using a second thread
public class LengthFragment extends BaseFragment {
    private enum LastEditTextFocused {
        INCH,
        FOOT,
        YARD,
        MILE,
        MILLIMETER,
        CENTIMETER,
        METER,
        KILOMETER
    }

    private final String TAG = "FragmentLength";

    private EditText editTextInch, editTextFoot, editTextYard, editTextMile, editTextMillimeter,
                    editTextCentimeter, editTextMeter, editTextKilometer;

    private LastEditTextFocused lastEditTextFocused;

	// region TextWatchers

    private TextWatcher textWatcherInch = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            if (s != null) {
                afterTextChangedInch(s);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherFoot = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            if (s != null) {
                afterTextChangedFoot(s);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherYard = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            if (s != null) {
                afterTextChangedYard(s);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherMile = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            if (s != null) {
                afterTextChangedMile(s);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherMillimeter = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            if (s != null) {
                afterTextChangedMillimeter(s);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherCentimeter = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            if (s != null) {
                afterTextChangedCentimeter(s);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherMeter = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            if (s != null) {
                afterTextChangedMeter(s);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherKilometer = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            if (s != null) {
                afterTextChangedKilometer(s);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

	// endregion

	// region Lifecycle methods

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".onCreateView", "Entered");
		}

		if (getRootView() != null) {
			Typeface tf = Typeface.createFromAsset(getCurrentActivity().getAssets(), "fonts/Roboto-Regular.ttf");

			TextView textViewInch = ((TextView) getRootView().findViewById(R.id.textView_length_inch));
			TextView textViewFoot = ((TextView) getRootView().findViewById(R.id.textView_length_foot));
			TextView textViewYard = ((TextView) getRootView().findViewById(R.id.textView_length_yard));
			TextView textViewMile = ((TextView) getRootView().findViewById(R.id.textView_length_mile));
			TextView textViewMillimeter = ((TextView) getRootView().findViewById(R.id.textView_length_millimeter));
			TextView textViewCentimeter = ((TextView) getRootView().findViewById(R.id.textView_length_centimeter));
			TextView textViewMeter = ((TextView) getRootView().findViewById(R.id.textView_length_meter));
			TextView textViewKilometer = ((TextView) getRootView().findViewById(R.id.textView_length_kilometer));

			textViewInch.setTypeface(tf);
			textViewFoot.setTypeface(tf);
			textViewYard.setTypeface(tf);
			textViewMile.setTypeface(tf);
			textViewMillimeter.setTypeface(tf);
			textViewCentimeter.setTypeface(tf);
			textViewMeter.setTypeface(tf);
			textViewKilometer.setTypeface(tf);

			editTextInch = ((EditText) getRootView().findViewById(R.id.editText_length_inch));
			editTextFoot = ((EditText) getRootView().findViewById(R.id.editText_length_foot));
			editTextYard = ((EditText) getRootView().findViewById(R.id.editText_length_yard));
			editTextMile = ((EditText) getRootView().findViewById(R.id.editText_length_mile));
			editTextMillimeter = ((EditText) getRootView().findViewById(R.id.editText_length_millimeter));
			editTextCentimeter = ((EditText) getRootView().findViewById(R.id.editText_length_centimeter));
			editTextMeter = ((EditText) getRootView().findViewById(R.id.editText_length_meter));
			editTextKilometer = ((EditText) getRootView().findViewById(R.id.editText_length_kilometer));

            addTextChangedListeners();
		}

        return getRootView();
    }

    @Override
    public void onResume() {
		super.onResume();
        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".onResume", "Entered");
        }

        if (lastEditTextFocused == LastEditTextFocused.INCH) {
            if (editTextInch.getText() != null) {
                afterTextChangedInch(editTextInch.getText());
            }
        } else if (lastEditTextFocused == LastEditTextFocused.FOOT) {
            if (editTextFoot.getText() != null) {
                afterTextChangedFoot(editTextFoot.getText());
            }
        } else if (lastEditTextFocused == LastEditTextFocused.YARD) {
            if (editTextYard.getText() != null) {
                afterTextChangedYard(editTextYard.getText());
            }
        } else if (lastEditTextFocused == LastEditTextFocused.MILE) {
            if (editTextMile.getText() != null) {
                afterTextChangedMile(editTextMile.getText());
            }
        } else if (lastEditTextFocused == LastEditTextFocused.MILLIMETER) {
            if (editTextMillimeter.getText() != null) {
                afterTextChangedMillimeter(editTextMillimeter.getText());
            }
        } else if (lastEditTextFocused == LastEditTextFocused.CENTIMETER) {
            if (editTextCentimeter.getText() != null) {
                afterTextChangedCentimeter(editTextCentimeter.getText());
            }
        } else if (lastEditTextFocused == LastEditTextFocused.METER) {
            if (editTextMeter.getText() != null) {
                afterTextChangedMeter(editTextMeter.getText());
            }
        } else if (lastEditTextFocused == LastEditTextFocused.KILOMETER) {
            if (editTextKilometer.getText() != null) {
                afterTextChangedKilometer(editTextKilometer.getText());
            }
        }
    }

	// endregion

	// region Helper methods

    private void addTextChangedListeners() {
        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".addTextChangedListeners", "Entered");
        }

        editTextInch.addTextChangedListener(textWatcherInch);
        editTextFoot.addTextChangedListener(textWatcherFoot);
        editTextYard.addTextChangedListener(textWatcherYard);
        editTextMile.addTextChangedListener(textWatcherMile);
        editTextMillimeter.addTextChangedListener(textWatcherMillimeter);
        editTextCentimeter.addTextChangedListener(textWatcherCentimeter);
        editTextMeter.addTextChangedListener(textWatcherMeter);
        editTextKilometer.addTextChangedListener(textWatcherKilometer);
    }

    private void removeTextChangedListeners() {
        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".removeTextChangedListeners", "Entered");
        }

        editTextInch.removeTextChangedListener(textWatcherInch);
        editTextFoot.removeTextChangedListener(textWatcherFoot);
        editTextYard.removeTextChangedListener(textWatcherYard);
        editTextMile.removeTextChangedListener(textWatcherMile);
        editTextMillimeter.removeTextChangedListener(textWatcherMillimeter);
        editTextCentimeter.removeTextChangedListener(textWatcherCentimeter);
        editTextMeter.removeTextChangedListener(textWatcherMeter);
        editTextKilometer.removeTextChangedListener(textWatcherKilometer);
    }

    private void afterTextChangedInch(Editable editableInch) {
        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".afterTextChangedInch", "Entered");
        }

        removeTextChangedListeners();

        lastEditTextFocused = LastEditTextFocused.INCH;

        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".afterTextChangedInch.before", editableInch.toString());
        }

        if (editableInch.length() != 0) {
            editableInch = Utils.sanitizeEditable(editableInch);
            if (editableInch != null) {
                if (Utils.isNumeric(editableInch.toString())) {
                    try {
                        BigDecimal inch = new BigDecimal(editableInch.toString());
                        BigDecimal foot = inch.divide(new BigDecimal("12")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal yard = foot.divide(new BigDecimal("3")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal mile = foot.divide(new BigDecimal("5280")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal millimeter = inch.multiply(new BigDecimal("25.4"));
                        BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal meter = millimeter.divide(new BigDecimal("1000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);

                        editTextFoot.setText(foot.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextYard.setText(yard.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMile.setText(mile.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMillimeter.setText(millimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextCentimeter.setText(centimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMeter.setText(meter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextKilometer.setText(kilometer.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                    } catch (NumberFormatException e) {
                        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            editTextFoot.setText("", TextView.BufferType.EDITABLE);
            editTextYard.setText("", TextView.BufferType.EDITABLE);
            editTextMile.setText("", TextView.BufferType.EDITABLE);
            editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
            editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
            editTextMeter.setText("", TextView.BufferType.EDITABLE);
            editTextKilometer.setText("", TextView.BufferType.EDITABLE);
        }

        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableInch != null)) {
            Log.d(TAG + ".afterTextChangedInch.after", editableInch.toString());
        } else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableInch == null)) {
            Log.d(TAG + ".afterTextChangedInch.after", "null");
        }

        addTextChangedListeners();
    }

    private void afterTextChangedFoot(Editable editableFoot) {
        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".afterTextChangedFoot", "Entered");
        }

        removeTextChangedListeners();

        lastEditTextFocused = LastEditTextFocused.FOOT;

        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".afterTextChangedFoot.before", editableFoot.toString());
        }

        if (editableFoot.length() != 0) {
            editableFoot = Utils.sanitizeEditable(editableFoot);
            if (editableFoot != null) {
                if (Utils.isNumeric(editableFoot.toString())) {
                    try {
                        BigDecimal foot = new BigDecimal(editableFoot.toString());
                        BigDecimal inch = foot.multiply(new BigDecimal("12"));
                        BigDecimal yard = foot.divide(new BigDecimal("3")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal mile = foot.divide(new BigDecimal("5280")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal millimeter = inch.multiply(new BigDecimal("25.4"));
                        BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal meter = millimeter.divide(new BigDecimal("1000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);

                        editTextInch.setText(inch.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextYard.setText(yard.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMile.setText(mile.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMillimeter.setText(millimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextCentimeter.setText(centimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMeter.setText(meter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextKilometer.setText(kilometer.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                    } catch (NumberFormatException e) {
                        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            editTextInch.setText("", TextView.BufferType.EDITABLE);
            editTextYard.setText("", TextView.BufferType.EDITABLE);
            editTextMile.setText("", TextView.BufferType.EDITABLE);
            editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
            editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
            editTextMeter.setText("", TextView.BufferType.EDITABLE);
            editTextKilometer.setText("", TextView.BufferType.EDITABLE);
        }

        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableFoot != null)) {
            Log.d(TAG + ".afterTextChangedFoot.after", editableFoot.toString());
        } else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableFoot == null)) {
            Log.d(TAG + ".afterTextChangedFoot.after", "null");
        }

        addTextChangedListeners();
    }

    private void afterTextChangedYard(Editable editableYard) {
        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".afterTextChangedYard", "Entered");
        }

        removeTextChangedListeners();

        lastEditTextFocused = LastEditTextFocused.YARD;

        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".afterTextChangedYard.before", editableYard.toString());
        }

        if (editableYard.length() != 0) {
            editableYard = Utils.sanitizeEditable(editableYard);
            if (editableYard != null) {
                if (Utils.isNumeric(editableYard.toString())) {
                    try {
                        BigDecimal yard = new BigDecimal(editableYard.toString());
                        BigDecimal inch = yard.multiply(new BigDecimal("36"));
                        BigDecimal foot = yard.multiply(new BigDecimal("3"));
                        BigDecimal mile = foot.divide(new BigDecimal("5280")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal millimeter = inch.multiply(new BigDecimal("25.4"));
                        BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal meter = millimeter.divide(new BigDecimal("1000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);

                        editTextInch.setText(inch.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextFoot.setText(foot.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMile.setText(mile.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMillimeter.setText(millimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextCentimeter.setText(centimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMeter.setText(meter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextKilometer.setText(kilometer.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                    } catch (NumberFormatException e) {
                        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            editTextInch.setText("", TextView.BufferType.EDITABLE);
            editTextFoot.setText("", TextView.BufferType.EDITABLE);
            editTextMile.setText("", TextView.BufferType.EDITABLE);
            editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
            editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
            editTextMeter.setText("", TextView.BufferType.EDITABLE);
            editTextKilometer.setText("", TextView.BufferType.EDITABLE);
        }

        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableYard != null)) {
            Log.d(TAG + ".afterTextChangedYard.after", editableYard.toString());
        } else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableYard == null)) {
            Log.d(TAG + ".afterTextChangedYard.after", "null");
        }

        addTextChangedListeners();
    }

    private void afterTextChangedMile(Editable editableMile) {
        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".afterTextChangedMile", "Entered");
        }

        removeTextChangedListeners();

        lastEditTextFocused = LastEditTextFocused.MILE;

        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".afterTextChangedMile.before", editableMile.toString());
        }

        if (editableMile.length() != 0) {
            editableMile = Utils.sanitizeEditable(editableMile);
            if (editableMile != null) {
                if (Utils.isNumeric(editableMile.toString())) {
                    try {
                        BigDecimal mile = new BigDecimal(editableMile.toString());
                        BigDecimal inch = mile.multiply(new BigDecimal("63360"));
                        BigDecimal foot = mile.multiply(new BigDecimal("5280"));
                        BigDecimal yard = mile.multiply(new BigDecimal("1760"));
                        BigDecimal millimeter = inch.multiply(new BigDecimal("25.4"));
                        BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal meter = millimeter.divide(new BigDecimal("1000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);

                        editTextInch.setText(inch.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextFoot.setText(foot.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextYard.setText(yard.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMillimeter.setText(millimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextCentimeter.setText(centimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMeter.setText(meter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextKilometer.setText(kilometer.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                    } catch (NumberFormatException e) {
                        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            editTextInch.setText("", TextView.BufferType.EDITABLE);
            editTextFoot.setText("", TextView.BufferType.EDITABLE);
            editTextYard.setText("", TextView.BufferType.EDITABLE);
            editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
            editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
            editTextMeter.setText("", TextView.BufferType.EDITABLE);
            editTextKilometer.setText("", TextView.BufferType.EDITABLE);
        }

        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableMile != null)) {
            Log.d(TAG + ".afterTextChangedMile.after", editableMile.toString());
        } else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableMile == null)) {
            Log.d(TAG + ".afterTextChangedMile.after", "null");
        }

        addTextChangedListeners();
    }

    private void afterTextChangedMillimeter(Editable editableMillimeter) {
        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".afterTextChangedMillimeter", "Entered");
        }

        removeTextChangedListeners();

        lastEditTextFocused = LastEditTextFocused.MILLIMETER;

        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".afterTextChangedMillimeter.before", editableMillimeter.toString());
        }

        if (editableMillimeter.length() != 0) {
            editableMillimeter = Utils.sanitizeEditable(editableMillimeter);
            if (editableMillimeter != null) {
                if (Utils.isNumeric(editableMillimeter.toString())) {
                    try {
                        BigDecimal millimeter = new BigDecimal(editableMillimeter.toString());
                        BigDecimal inch = millimeter.multiply(new BigDecimal("0.03937007874015748031496062992126"));
                        BigDecimal foot = millimeter.multiply(new BigDecimal("0.00328083989501312335958005249344"));
                        BigDecimal yard = millimeter.multiply(new BigDecimal("0.00109361329833770778652668416448"));
                        BigDecimal mile = foot.divide(new BigDecimal("5280")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal meter = millimeter.divide(new BigDecimal("1000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);

                        editTextInch.setText(inch.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextFoot.setText(foot.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextYard.setText(yard.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMile.setText(mile.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextCentimeter.setText(centimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMeter.setText(meter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextKilometer.setText(kilometer.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                    } catch (NumberFormatException e) {
                        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            editTextInch.setText("", TextView.BufferType.EDITABLE);
            editTextFoot.setText("", TextView.BufferType.EDITABLE);
            editTextYard.setText("", TextView.BufferType.EDITABLE);
            editTextMile.setText("", TextView.BufferType.EDITABLE);
            editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
            editTextMeter.setText("", TextView.BufferType.EDITABLE);
            editTextKilometer.setText("", TextView.BufferType.EDITABLE);
        }

        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableMillimeter != null)) {
            Log.d(TAG + ".afterTextChangedMillimeter.after", editableMillimeter.toString());
        } else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableMillimeter == null)) {
            Log.d(TAG + ".afterTextChangedMillimeter.after", "null");
        }

        addTextChangedListeners();
    }

    private void afterTextChangedCentimeter(Editable editableCentimeter) {
        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".afterTextChangedCentimeter", "Entered");
        }

        removeTextChangedListeners();

        lastEditTextFocused = LastEditTextFocused.CENTIMETER;

        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".afterTextChangedCentimeter.before", editableCentimeter.toString());
        }

        if (editableCentimeter.length() != 0) {
            editableCentimeter = Utils.sanitizeEditable(editableCentimeter);
            if (editableCentimeter != null) {
                if (Utils.isNumeric(editableCentimeter.toString())) {
                    try {
                        BigDecimal centimeter = new BigDecimal(editableCentimeter.toString());
                        BigDecimal inch = centimeter.multiply(new BigDecimal("0.3937007874015748031496062992126"));
                        BigDecimal foot = centimeter.multiply(new BigDecimal("0.0328083989501312335958005249344"));
                        BigDecimal yard = centimeter.multiply(new BigDecimal("0.0109361329833770778652668416448"));
                        BigDecimal mile = foot.divide(new BigDecimal("5280")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal millimeter = centimeter.multiply(new BigDecimal("10"));
                        BigDecimal meter = centimeter.divide(new BigDecimal("100")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal kilometer = centimeter.divide(new BigDecimal("100000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);

                        editTextInch.setText(inch.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextFoot.setText(foot.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextYard.setText(yard.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMile.setText(mile.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMillimeter.setText(millimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMeter.setText(meter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextKilometer.setText(kilometer.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                    } catch (NumberFormatException e) {
                        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            editTextInch.setText("", TextView.BufferType.EDITABLE);
            editTextFoot.setText("", TextView.BufferType.EDITABLE);
            editTextYard.setText("", TextView.BufferType.EDITABLE);
            editTextMile.setText("", TextView.BufferType.EDITABLE);
            editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
            editTextMeter.setText("", TextView.BufferType.EDITABLE);
            editTextKilometer.setText("", TextView.BufferType.EDITABLE);
        }

        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableCentimeter != null)) {
            Log.d(TAG + ".afterTextChangedCentimeter.after", editableCentimeter.toString());
        } else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableCentimeter == null)) {
            Log.d(TAG + ".afterTextChangedCentimeter.after", "null");
        }

        addTextChangedListeners();
    }

    private void afterTextChangedMeter(Editable editableMeter) {
        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".afterTextChangedMeter", "Entered");
        }

        removeTextChangedListeners();

        lastEditTextFocused = LastEditTextFocused.METER;

        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".textWatcherMeter.s.before", editableMeter.toString());
        }

        if (editableMeter.length() != 0) {
            editableMeter = Utils.sanitizeEditable(editableMeter);
            if (editableMeter != null) {
                if (Utils.isNumeric(editableMeter.toString())) {
                    try {
                        BigDecimal meter = new BigDecimal(editableMeter.toString());
                        BigDecimal inch = meter.multiply(new BigDecimal("39.37007874015748031496062992126"));
                        BigDecimal foot = meter.multiply(new BigDecimal("3.28083989501312335958005249344"));
                        BigDecimal yard = meter.multiply(new BigDecimal("1.09361329833770778652668416448"));
                        BigDecimal mile = foot.divide(new BigDecimal("5280")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal millimeter = meter.multiply(new BigDecimal("1000"));
                        BigDecimal centimeter = meter.multiply(new BigDecimal("100"));
                        BigDecimal kilometer = meter.divide(new BigDecimal("1000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);

                        editTextInch.setText(inch.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextFoot.setText(foot.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextYard.setText(yard.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMile.setText(mile.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMillimeter.setText(millimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextCentimeter.setText(centimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextKilometer.setText(kilometer.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                    } catch (NumberFormatException e) {
                        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            editTextInch.setText("", TextView.BufferType.EDITABLE);
            editTextFoot.setText("", TextView.BufferType.EDITABLE);
            editTextYard.setText("", TextView.BufferType.EDITABLE);
            editTextMile.setText("", TextView.BufferType.EDITABLE);
            editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
            editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
            editTextKilometer.setText("", TextView.BufferType.EDITABLE);
        }

        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableMeter != null)) {
            Log.d(TAG + ".textWatcherMeter.s.after", editableMeter.toString());
        } else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableMeter == null)) {
            Log.d(TAG + ".textWatcherMeter.s.after", "null");
        }

        addTextChangedListeners();
    }

    private void afterTextChangedKilometer(Editable editableKilometer) {
        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".afterTextChangedKilometer", "Entered");
        }

        removeTextChangedListeners();

        lastEditTextFocused = LastEditTextFocused.KILOMETER;

        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".afterTextChangedKilometer.before", editableKilometer.toString());
        }

        if (editableKilometer.length() != 0) {
            editableKilometer = Utils.sanitizeEditable(editableKilometer);
            if (editableKilometer != null) {
                if (Utils.isNumeric(editableKilometer.toString())) {
                    try {
                        BigDecimal kilometer = new BigDecimal(editableKilometer.toString());
                        BigDecimal inch = kilometer.multiply(new BigDecimal("39370.07874015748031496062992126"));
                        BigDecimal foot = kilometer.multiply(new BigDecimal("3280.83989501312335958005249344"));
                        BigDecimal yard = kilometer.multiply(new BigDecimal("1093.61329833770778652668416448"));
                        BigDecimal mile = foot.divide(new BigDecimal("5280")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal millimeter = kilometer.multiply(new BigDecimal("1000000"));
                        BigDecimal centimeter = kilometer.multiply(new BigDecimal("100000"));
                        BigDecimal meter = kilometer.multiply(new BigDecimal("1000"));

                        editTextInch.setText(inch.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextFoot.setText(foot.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextYard.setText(yard.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMile.setText(mile.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMillimeter.setText(millimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextCentimeter.setText(centimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                        editTextMeter.setText(meter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString()
                                , TextView.BufferType.EDITABLE);
                    } catch (NumberFormatException e) {
                        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            editTextInch.setText("", TextView.BufferType.EDITABLE);
            editTextFoot.setText("", TextView.BufferType.EDITABLE);
            editTextYard.setText("", TextView.BufferType.EDITABLE);
            editTextMile.setText("", TextView.BufferType.EDITABLE);
            editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
            editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
            editTextMeter.setText("", TextView.BufferType.EDITABLE);
        }

        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableKilometer != null)) {
            Log.d(TAG + ".afterTextChangedKilometer.after", editableKilometer.toString());
        } else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableKilometer == null)) {
            Log.d(TAG + ".afterTextChangedKilometer.after", "null");
        }

        addTextChangedListeners();
    }

	// endregion

	// region Overridden BaseFragment methods

	@Override
	protected String getChildTag() { return TAG; }

	@Override
	protected int getLayoutResource() { return R.layout.fragment_length; }

	@Override
	protected int getScrollViewResource() { return  R.id.fragment_length; }

	// endregion
}
