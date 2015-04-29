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
 * Celsius, Fahrenheit, Kelvin
 * Conversions comply with the conversions through Google.com
 */

public class TemperatureFragment extends BaseFragment {
	private enum LastEditTextFocused {
		CELSIUS,
		FAHRENHEIT,
		KELVIN
	};

    private final String TAG = "FragmentTemperature";

    private EditText editTextCelsius, editTextFahrenheit, editTextKelvin;
	private LastEditTextFocused lastEditTextFocused;

    private TextWatcher textWatcherCelsius = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
			afterTextChangedCelsius(s);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherFahrenheit = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
			afterTextChangedFahrenheit(s);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherKelvin = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
			afterTextChangedKelvin(s);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + "onCreateView", "Entered");
		}

		super.onCreateView(inflater, container, savedInstanceState);

		setShouldHideToolbarOnScroll(false);

		if (getRootView() != null) {
			Typeface tf = Typeface.createFromAsset(getCurrentActivity().getAssets(), "fonts/Roboto-Regular.ttf");

			TextView textViewCelsius = ((TextView) getRootView().findViewById(R.id.textView_temperature_celsius));
			TextView textViewFahrenheit = ((TextView) getRootView().findViewById(R.id.textView_temperature_fahrenheit));
			TextView textViewKelvin = ((TextView) getRootView().findViewById(R.id.textView_temperature_kelvin));

			textViewCelsius.setTypeface(tf);
			textViewFahrenheit.setTypeface(tf);
			textViewKelvin.setTypeface(tf);

			editTextCelsius = ((EditText) getRootView().findViewById(R.id.editText_temperature_celsius));
			editTextFahrenheit = ((EditText) getRootView().findViewById(R.id.editText_temperature_fahrenheit));
			editTextKelvin = ((EditText) getRootView().findViewById(R.id.editText_temperature_kelvin));

			addTextChangedListeners();
		}

        return getRootView();
    }

	@Override
	public void onResume() {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + "onResume", "Entered");
		}
		super.onResume();

		if (lastEditTextFocused == LastEditTextFocused.CELSIUS) {
			if (!editTextCelsius.getText().toString().isEmpty()) {
				afterTextChangedCelsius(editTextCelsius.getText());
			}
		} else if (lastEditTextFocused == LastEditTextFocused.FAHRENHEIT) {
			if (!editTextFahrenheit.getText().toString().isEmpty()) {
				afterTextChangedFahrenheit(editTextFahrenheit.getText());
			}
		} else if (lastEditTextFocused == LastEditTextFocused.KELVIN) {
			if (!editTextKelvin.getText().toString().isEmpty()) {
				afterTextChangedKelvin(editTextKelvin.getText());
			}
		}
	}

	private void addTextChangedListeners() {
		editTextCelsius.addTextChangedListener(textWatcherCelsius);
		editTextFahrenheit.addTextChangedListener(textWatcherFahrenheit);
		editTextKelvin.addTextChangedListener(textWatcherKelvin);
	}

	private void removeTextChangedListeners() {
		editTextCelsius.removeTextChangedListener(textWatcherCelsius);
		editTextFahrenheit.removeTextChangedListener(textWatcherFahrenheit);
		editTextKelvin.removeTextChangedListener(textWatcherKelvin);
	}

	private void afterTextChangedCelsius(Editable editableCelsius) {
		removeTextChangedListeners();

		lastEditTextFocused = LastEditTextFocused.CELSIUS;

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedCelsius.before", editableCelsius.toString());
		}

		if (editableCelsius.length() != 0) {
			editableCelsius = Utils.sanitizeEditable(editableCelsius);
			if (editableCelsius != null) {
				if (Utils.isNumeric(editableCelsius.toString())) {
					try {
						BigDecimal celsius = new BigDecimal(editableCelsius.toString());
						BigDecimal fahrenheit = celsius.multiply(new BigDecimal("1.8"))
														.add(new BigDecimal("32"));
						BigDecimal kelvin = celsius.add(new BigDecimal("273.15"));

						editTextFahrenheit.setText(fahrenheit.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														   .stripTrailingZeros()
														   .toPlainString()
														  , TextView.BufferType.EDITABLE);
						editTextKelvin.setText(kelvin.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
			editTextFahrenheit.setText("", TextView.BufferType.EDITABLE);
			editTextKelvin.setText("", TextView.BufferType.EDITABLE);
		}

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableCelsius != null)) {
			Log.d(TAG + ".afterTextChangedCelsius.after", editableCelsius.toString());
		} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableCelsius == null)) {
			Log.d(TAG + ".afterTextChangedCelsius.after", "null");
		}

		addTextChangedListeners();
	}

	public void afterTextChangedFahrenheit(Editable editableFahrenheit) {
		removeTextChangedListeners();

		lastEditTextFocused = LastEditTextFocused.FAHRENHEIT;

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedFahrenheit.before", editableFahrenheit.toString());
		}

		if (editableFahrenheit.length() != 0) {
			editableFahrenheit = Utils.sanitizeEditable(editableFahrenheit);
			if (editableFahrenheit != null) {
				if (Utils.isNumeric(editableFahrenheit.toString())) {
					try {
						BigDecimal fahrenheit = new BigDecimal(editableFahrenheit.toString());
						BigDecimal celsius = fahrenheit.subtract(new BigDecimal("32"))
													 .multiply(new BigDecimal(5.0 / 9.0));
						BigDecimal kelvin = celsius.add(new BigDecimal("273.15"));

						editTextCelsius.setText(celsius.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
						editTextKelvin.setText(kelvin.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
			editTextCelsius.setText("", TextView.BufferType.EDITABLE);
			editTextKelvin.setText("", TextView.BufferType.EDITABLE);
		}

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableFahrenheit != null)) {
			Log.d(TAG + ".afterTextChangedFahrenheit.after", editableFahrenheit.toString());
		} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableFahrenheit == null)) {
			Log.d(TAG + ".afterTextChangedFahrenheit.after", "null");
		}

		addTextChangedListeners();
	}

	private void afterTextChangedKelvin(Editable editableKelvin) {
		removeTextChangedListeners();

		lastEditTextFocused = LastEditTextFocused.KELVIN;

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedKelvin.before", editableKelvin.toString());
		}

		if (editableKelvin.length() != 0) {
			editableKelvin = Utils.sanitizeEditable(editableKelvin);
			if (editableKelvin != null) {
				if (Utils.isNumeric(editableKelvin.toString())) {
					try {
						BigDecimal kelvin = new BigDecimal(editableKelvin.toString());
						BigDecimal celsius = kelvin.subtract(new BigDecimal("273.15"));
						BigDecimal fahrenheit = celsius.multiply(new BigDecimal("1.8"))
														.add(new BigDecimal("32"));

						editTextCelsius.setText(celsius.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
						editTextFahrenheit.setText(fahrenheit.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
			editTextCelsius.setText("", TextView.BufferType.EDITABLE);
			editTextFahrenheit.setText("", TextView.BufferType.EDITABLE);
		}

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableKelvin != null)) {
			Log.d(TAG + ".afterTextChangedKelvin.after", editableKelvin.toString());
		} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableKelvin == null)) {
			Log.d(TAG + ".afterTextChangedKelvin.after", "null");
		}

		addTextChangedListeners();
	}

	@Override
	protected String getChildTag() { return TAG; }

	@Override
	protected int getLayoutResource() { return R.layout.fragment_temperature; }

	@Override
	protected int getScrollViewResource() { return R.id.fragment_temperature; }
}
