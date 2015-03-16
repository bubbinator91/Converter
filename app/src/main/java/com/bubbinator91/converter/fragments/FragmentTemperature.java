package com.bubbinator91.converter.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.Util;

import java.math.BigDecimal;

/**
 * Celsius, Fahrenheit, Kelvin
 * Conversions comply with the conversions through Google.com
 */

public class FragmentTemperature extends Fragment {
    private boolean DEBUG = false;
    private final String TAG = "FragmentTemperature";

	private SharedPreferences mPrefs;

	private int fieldLength = -1;

    private EditText editTextCelsius, editTextFahrenheit, editTextKelvin;

    private TextWatcher textWatcherCelsius = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            editTextCelsius.removeTextChangedListener(textWatcherCelsius);
            editTextFahrenheit.removeTextChangedListener(textWatcherFahrenheit);
            editTextKelvin.removeTextChangedListener(textWatcherKelvin);

            if (DEBUG)
                Log.d(TAG + ".textWatcherCelsius.s.before", s.toString());

            if (s.length() != 0) {
                s = Util.sanitizeEditable(s);
                if (s != null) {
                    if (Util.isNumeric(s.toString())) {
                        try {
							BigDecimal celsius = new BigDecimal(s.toString());
							BigDecimal fahrenheit = celsius.multiply(new BigDecimal("1.8"))
															.add(new BigDecimal("32"));
							BigDecimal kelvin = celsius.add(new BigDecimal("273.15"));

                            editTextFahrenheit.setText(fahrenheit.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
                            editTextKelvin.setText(kelvin.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														   .stripTrailingZeros()
														   .toPlainString()
														  , TextView.BufferType.EDITABLE);
                        } catch (NumberFormatException e) {
                            if (DEBUG)
                                e.printStackTrace();
                        }
                    }
                }
            } else {
                editTextFahrenheit.setText("", TextView.BufferType.EDITABLE);
                editTextKelvin.setText("", TextView.BufferType.EDITABLE);
            }

			if (DEBUG && (s != null))
                Log.d(TAG + ".textWatcherCelsius.s.after", s.toString());
			else if (DEBUG && (s == null))
				Log.d(TAG + ".textWatcherCelsius.s.after", "null");

            editTextCelsius.addTextChangedListener(textWatcherCelsius);
            editTextFahrenheit.addTextChangedListener(textWatcherFahrenheit);
            editTextKelvin.addTextChangedListener(textWatcherKelvin);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherFahrenheit = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            editTextCelsius.removeTextChangedListener(textWatcherCelsius);
            editTextFahrenheit.removeTextChangedListener(textWatcherFahrenheit);
            editTextKelvin.removeTextChangedListener(textWatcherKelvin);

            if (DEBUG)
                Log.d(TAG + ".textWatcherFahrenheit.s.before", s.toString());

            if (s.length() != 0) {
                s = Util.sanitizeEditable(s);
                if (s != null) {
                    if (Util.isNumeric(s.toString())) {
                        try {
							BigDecimal fahrenheit = new BigDecimal(s.toString());
							BigDecimal celsius = fahrenheit.subtract(new BigDecimal("32"))
														 .multiply(new BigDecimal(5.0 / 9.0));
							BigDecimal kelvin = celsius.add(new BigDecimal("273.15"));

                            editTextCelsius.setText(celsius.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
															.stripTrailingZeros()
															.toPlainString()
														   , TextView.BufferType.EDITABLE);
                            editTextKelvin.setText(kelvin.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														   .stripTrailingZeros()
														   .toPlainString()
														  , TextView.BufferType.EDITABLE);
                        } catch (NumberFormatException e) {
                            if (DEBUG)
                                e.printStackTrace();
                        }
                    }
                }
            } else {
                editTextCelsius.setText("", TextView.BufferType.EDITABLE);
                editTextKelvin.setText("", TextView.BufferType.EDITABLE);
            }

			if (DEBUG && (s != null))
				Log.d(TAG + ".textWatcherFahrenheit.s.after", s.toString());
			else if (DEBUG && (s == null))
				Log.d(TAG + ".textWatcherFahrenheit.s.after", "null");

            editTextCelsius.addTextChangedListener(textWatcherCelsius);
            editTextFahrenheit.addTextChangedListener(textWatcherFahrenheit);
            editTextKelvin.addTextChangedListener(textWatcherKelvin);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherKelvin = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            editTextCelsius.removeTextChangedListener(textWatcherCelsius);
            editTextFahrenheit.removeTextChangedListener(textWatcherFahrenheit);
            editTextKelvin.removeTextChangedListener(textWatcherKelvin);

            if (DEBUG)
                Log.d(TAG + ".textWatcherKelvin.s.before", s.toString());

            if (s.length() != 0) {
                s = Util.sanitizeEditable(s);
                if (s != null) {
                    if (Util.isNumeric(s.toString())) {
                        try {
							BigDecimal kelvin = new BigDecimal(s.toString());
							BigDecimal celsius = kelvin.subtract(new BigDecimal("273.15"));
							BigDecimal fahrenheit = celsius.multiply(new BigDecimal("1.8"))
															.add(new BigDecimal("32"));

                            editTextCelsius.setText(celsius.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
															.stripTrailingZeros()
															.toPlainString()
														   , TextView.BufferType.EDITABLE);
                            editTextFahrenheit.setText(fahrenheit.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
                        } catch (NumberFormatException e) {
                            if (DEBUG)
                                e.printStackTrace();
                        }
                    }
                }
            } else {
                editTextCelsius.setText("", TextView.BufferType.EDITABLE);
                editTextFahrenheit.setText("", TextView.BufferType.EDITABLE);
            }

			if (DEBUG && (s != null))
				Log.d(TAG + ".textWatcherKelvin.s.after", s.toString());
			else if (DEBUG && (s == null))
				Log.d(TAG + ".textWatcherKelvin.s.after", "null");

            editTextCelsius.addTextChangedListener(textWatcherCelsius);
            editTextFahrenheit.addTextChangedListener(textWatcherFahrenheit);
            editTextKelvin.addTextChangedListener(textWatcherKelvin);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (DEBUG)
            Log.d(TAG + "onCreateView", "Entered");

		mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

		if (mPrefs != null) {
			fieldLength = mPrefs.getInt(Util.PREFERENCE_FIELD_LENGTH, -1);
			if (mPrefs.getInt(Util.PREFERENCE_DEBUG, -1) == 1)
				DEBUG = true;
		}
		if (fieldLength == -1)
			fieldLength = 8;

        View rootView = inflater.inflate(R.layout.fragment_temperature, container, false);

        editTextCelsius = ((EditText) rootView.findViewById(R.id.editText_temperature_celsius));
        editTextFahrenheit = ((EditText) rootView.findViewById(R.id.editText_temperature_fahrenheit));
        editTextKelvin = ((EditText) rootView.findViewById(R.id.editText_temperature_kelvin));

        editTextCelsius.addTextChangedListener(textWatcherCelsius);
        editTextFahrenheit.addTextChangedListener(textWatcherFahrenheit);
        editTextKelvin.addTextChangedListener(textWatcherKelvin);

        return rootView;
    }
}
