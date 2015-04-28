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
    private final String TAG = "FragmentTemperature";

    private EditText editTextCelsius, editTextFahrenheit, editTextKelvin;

    private TextWatcher textWatcherCelsius = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            editTextCelsius.removeTextChangedListener(textWatcherCelsius);
            editTextFahrenheit.removeTextChangedListener(textWatcherFahrenheit);
            editTextKelvin.removeTextChangedListener(textWatcherKelvin);

            if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherCelsius.s.before", s.toString());
			}

            if (s.length() != 0) {
                s = Utils.sanitizeEditable(s);
                if (s != null) {
                    if (Utils.isNumeric(s.toString())) {
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
                            if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
								e.printStackTrace();
							}
                        }
                    }
                }
            } else {
                editTextFahrenheit.setText("", TextView.BufferType.EDITABLE);
                editTextKelvin.setText("", TextView.BufferType.EDITABLE);
            }

			if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherCelsius.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherCelsius.s.after", "null");
			}

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

            if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherFahrenheit.s.before", s.toString());
			}

            if (s.length() != 0) {
                s = Utils.sanitizeEditable(s);
                if (s != null) {
                    if (Utils.isNumeric(s.toString())) {
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
                            if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
								e.printStackTrace();
							}
                        }
                    }
                }
            } else {
                editTextCelsius.setText("", TextView.BufferType.EDITABLE);
                editTextKelvin.setText("", TextView.BufferType.EDITABLE);
            }

			if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherFahrenheit.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherFahrenheit.s.after", "null");
			}

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

            if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherKelvin.s.before", s.toString());
			}

            if (s.length() != 0) {
                s = Utils.sanitizeEditable(s);
                if (s != null) {
                    if (Utils.isNumeric(s.toString())) {
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
                            if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
								e.printStackTrace();
							}
                        }
                    }
                }
            } else {
                editTextCelsius.setText("", TextView.BufferType.EDITABLE);
                editTextFahrenheit.setText("", TextView.BufferType.EDITABLE);
            }

			if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherKelvin.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherKelvin.s.after", "null");
			}

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
        if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
			Log.d(TAG + "onCreateView", "Entered");
		}

		super.onCreateView(inflater, container, savedInstanceState);

		shouldHideToolbarOnScroll = false;

		if (rootView != null) {
			Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");

			TextView textViewCelsius = ((TextView) rootView.findViewById(R.id.textView_temperature_celsius));
			TextView textViewFahrenheit = ((TextView) rootView.findViewById(R.id.textView_temperature_fahrenheit));
			TextView textViewKelvin = ((TextView) rootView.findViewById(R.id.textView_temperature_kelvin));

			textViewCelsius.setTypeface(tf);
			textViewFahrenheit.setTypeface(tf);
			textViewKelvin.setTypeface(tf);

			editTextCelsius = ((EditText) rootView.findViewById(R.id.editText_temperature_celsius));
			editTextFahrenheit = ((EditText) rootView.findViewById(R.id.editText_temperature_fahrenheit));
			editTextKelvin = ((EditText) rootView.findViewById(R.id.editText_temperature_kelvin));

			editTextCelsius.addTextChangedListener(textWatcherCelsius);
			editTextFahrenheit.addTextChangedListener(textWatcherFahrenheit);
			editTextKelvin.addTextChangedListener(textWatcherKelvin);
		}

        return rootView;
    }

	@Override
	protected int getLayoutResource() { return R.layout.fragment_temperature; }

	@Override
	protected int getScrollViewResource() { return R.id.fragment_temperature; }
}
