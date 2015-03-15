package com.bubbinator91.converter.fragments;

import android.app.Fragment;
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
import com.bubbinator91.converter.Util;

public class FragmentTemperature extends Fragment {
    private final boolean DEBUG = false;
    private final String TAG = "FragmentTemperature";

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
                            double c = Double.parseDouble(s.toString());
                            double f = (((9.0 / 5.0) * c) + 32.0);
                            double k = c + 273.15;

                            editTextFahrenheit.setText(Double.toString(f), TextView.BufferType.EDITABLE);
                            editTextKelvin.setText(Double.toString(k), TextView.BufferType.EDITABLE);
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

            if (DEBUG)
                Log.d(TAG + ".textWatcherCelsius.s.after", s.toString());

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
                            double f = Double.parseDouble(s.toString());
                            double c = ((f - 32.0) * (5.0 / 9.0));
                            double k = c + 273.15;

                            editTextCelsius.setText(Double.toString(c), TextView.BufferType.EDITABLE);
                            editTextKelvin.setText(Double.toString(k), TextView.BufferType.EDITABLE);
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

            if (DEBUG)
                Log.d(TAG + ".textWatcherFahrenheit.s.after", s.toString());

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
                            double k = Double.parseDouble(s.toString());
                            double c = k - 273.15;
                            double f = (((9.0 / 5.0) * c) + 32.0);

                            editTextFahrenheit.setText(Double.toString(f), TextView.BufferType.EDITABLE);
                            editTextCelsius.setText(Double.toString(c), TextView.BufferType.EDITABLE);
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

            if (DEBUG)
                Log.d(TAG + ".textWatcherKelvin.s.after", s.toString());

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
            Log.d(TAG, "Entered onCreateView()");

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
