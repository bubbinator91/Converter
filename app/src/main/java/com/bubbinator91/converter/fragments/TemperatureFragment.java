package com.bubbinator91.converter.fragments;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bubbinator91.conversion.temperature.Celsius;
import com.bubbinator91.conversion.temperature.Fahrenheit;
import com.bubbinator91.conversion.temperature.Kelvin;
import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.conversion.util.Tuple;
import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.TextInputLayoutLAndAbove;
import com.bubbinator91.converter.util.Utils;

import java.util.List;

import timber.log.Timber;

/**
 * Celsius, Fahrenheit, Kelvin
 * Conversions comply with the conversions through Google.com
 */

public class TemperatureFragment extends BaseFragment {
    private enum LastEditTextFocused {
        CELSIUS,
        FAHRENHEIT,
        KELVIN
    }

    private final String TAG = "FragmentTemperature";

    private AppCompatEditText editTextCelsius, editTextFahrenheit, editTextKelvin;

    private TextInputLayoutLAndAbove textInputLayoutCelsius, textInputLayoutFahrenheit,
            textInputLayoutKelvin;

    private LastEditTextFocused lastEditTextFocused;

    // region TextWatchers

    private TextWatcher textWatcherCelsius = new TextWatcher() {
        @Override
        public void afterTextChanged(final Editable s) {
            lastEditTextFocused = LastEditTextFocused.CELSIUS;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("textWatcherCelsius");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherCelsius");
                new Thread(new ConversionFromCelsiusRunnable(s, "textWatcherCelsius")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherFahrenheit = new TextWatcher() {
        @Override
        public void afterTextChanged(final Editable s) {
            lastEditTextFocused = LastEditTextFocused.FAHRENHEIT;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("textWatcherFahrenheit");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherFahrenheit");
                new Thread(new ConversionFromFahrenheitRunnable(s, "textWatcherFahrenheit")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherKelvin = new TextWatcher() {
        @Override
        public void afterTextChanged(final Editable s) {
            lastEditTextFocused = LastEditTextFocused.KELVIN;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("textWatcherKelvin");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherKelvin");
                new Thread(new ConversionFromKelvinRunnable(s, "textWatcherKelvin")).start();
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
        Timber.tag(TAG + ".onCreateView").i("Entered");

        if (getRootView() != null) {
            textInputLayoutCelsius =
                    ((TextInputLayoutLAndAbove) getRootView()
                            .findViewById(R.id.textInputLayout_temperature_celsius));
            textInputLayoutFahrenheit =
                    ((TextInputLayoutLAndAbove) getRootView()
                            .findViewById(R.id.textInputLayout_temperature_fahrenheit));
            textInputLayoutKelvin =
                    ((TextInputLayoutLAndAbove) getRootView()
                            .findViewById(R.id.textInputLayout_temperature_kelvin));

            editTextCelsius =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_temperature_celsius));
            editTextFahrenheit =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_temperature_fahrenheit));
            editTextKelvin =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_temperature_kelvin));

            addTextChangedListeners(null);
        }

        return getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.tag(TAG + ".onResume").i("Entered");

        if (lastEditTextFocused == LastEditTextFocused.CELSIUS) {
            if ((getHandler() != null) && (editTextCelsius.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextCelsius.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromCelsiusRunnable(editTextCelsius.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.FAHRENHEIT) {
            if ((getHandler() != null) && (editTextFahrenheit.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextFahrenheit.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromFahrenheitRunnable(editTextFahrenheit.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.KELVIN) {
            if ((getHandler() != null) && (editTextKelvin.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextKelvin.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromKelvinRunnable(editTextKelvin.getText(),
                        "onResume")).start();
            }
        }
    }

    // endregion

    // region Helper methods

    private void addTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".addTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".addTextChangedListeners").i("Entered");
        }

        editTextCelsius.addTextChangedListener(textWatcherCelsius);
        editTextFahrenheit.addTextChangedListener(textWatcherFahrenheit);
        editTextKelvin.addTextChangedListener(textWatcherKelvin);
    }

    private void removeTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".removeTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".removeTextChangedListeners").i("Entered");
        }

        editTextCelsius.removeTextChangedListener(textWatcherCelsius);
        editTextFahrenheit.removeTextChangedListener(textWatcherFahrenheit);
        editTextKelvin.removeTextChangedListener(textWatcherKelvin);
    }

    // endregion

    // region Overridden BaseFragment methods

    @Override
    protected String getChildTag() { return TAG; }

    @Override
    protected int getLayoutResource() { return R.layout.fragment_temperature; }

    @Override
    protected int getScrollViewResource() { return R.id.fragment_temperature; }

    // endregion

    // region Private classes

    private class ConversionFromCelsiusRunnable implements Runnable {
        private final String TAG = "ConversionFromCelsiusRunnable";

        private Editable mEditableCelsius;
        private String mCallingClassName;

        public ConversionFromCelsiusRunnable(Editable editableCelsius, String callingClassName) {
            mEditableCelsius = editableCelsius;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableCelsius != null) {
                Tuple<List<String>, ConversionErrorCodes> results =
                        Celsius.toAll(mEditableCelsius.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    final List<String> conversionList = results.getValue0();
                    final ConversionErrorCodes error = results.getValue1();

                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (error) {
                                case ERROR_BELOW_ZERO:
                                    textInputLayoutCelsius.setError(getString(
                                            R.string.conversion_temperature_error_below_absolute_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    textInputLayoutCelsius.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    textInputLayoutCelsius.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    textInputLayoutCelsius.setErrorEnabled(false);
                                    textInputLayoutFahrenheit.setErrorEnabled(false);
                                    textInputLayoutKelvin.setErrorEnabled(false);
                                    break;
                            }

                            editTextFahrenheit.setText(conversionList.get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            editTextKelvin.setText(conversionList.get(1),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromFahrenheitRunnable implements Runnable {
        private final String TAG = "ConversionFromFahrenheitRunnable";

        private Editable mEditableFahrenheit;
        private String mCallingClassName;

        public ConversionFromFahrenheitRunnable(Editable editableFahrenheit, String callingClassName) {
            mEditableFahrenheit = editableFahrenheit;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableFahrenheit != null) {
                Tuple<List<String>, ConversionErrorCodes> results =
                        Fahrenheit.toAll(mEditableFahrenheit.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    final List<String> conversionList = results.getValue0();
                    final ConversionErrorCodes error = results.getValue1();

                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (error) {
                                case ERROR_BELOW_ZERO:
                                    textInputLayoutFahrenheit.setError(getString(
                                            R.string.conversion_temperature_error_below_absolute_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    textInputLayoutFahrenheit.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    textInputLayoutFahrenheit.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    textInputLayoutCelsius.setErrorEnabled(false);
                                    textInputLayoutFahrenheit.setErrorEnabled(false);
                                    textInputLayoutKelvin.setErrorEnabled(false);
                                    break;
                            }

                            editTextCelsius.setText(conversionList.get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            editTextKelvin.setText(conversionList.get(1),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromKelvinRunnable implements Runnable {
        private final String TAG = "ConversionFromKelvinRunnable";

        private Editable mEditableKelvin;
        private String mCallingClassName;

        public ConversionFromKelvinRunnable(Editable editableKelvin, String callingClassName) {
            mEditableKelvin = editableKelvin;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableKelvin != null) {
                Tuple<List<String>, ConversionErrorCodes> results =
                        Kelvin.toAll(mEditableKelvin.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    final List<String> conversionList = results.getValue0();
                    final ConversionErrorCodes error = results.getValue1();

                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (error) {
                                case ERROR_BELOW_ZERO:
                                    textInputLayoutKelvin.setError(getString(
                                            R.string.conversion_temperature_error_below_absolute_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    textInputLayoutKelvin.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    textInputLayoutKelvin.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    textInputLayoutCelsius.setErrorEnabled(false);
                                    textInputLayoutFahrenheit.setErrorEnabled(false);
                                    textInputLayoutKelvin.setErrorEnabled(false);
                                    break;
                            }

                            editTextCelsius.setText(conversionList.get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            editTextFahrenheit.setText(conversionList.get(1),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    // endregion
}
