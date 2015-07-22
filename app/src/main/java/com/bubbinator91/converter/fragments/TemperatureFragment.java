package com.bubbinator91.converter.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
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

    private AppCompatEditText mEditTextCelsius, mEditTextFahrenheit, mEditTextKelvin;

    private TextInputLayout mTextInputLayoutCelsius, mTextInputLayoutFahrenheit,
            mTextInputLayoutKelvin;

    private LastEditTextFocused mLastEditTextFocused;

    // region TextWatchers

    private TextWatcher mTextWatcherCelsius = new TextWatcher() {
        @Override
        public void afterTextChanged(final Editable s) {
            mLastEditTextFocused = LastEditTextFocused.CELSIUS;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherCelsius");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherCelsius");
                new Thread(new ConversionFromCelsiusRunnable(s, "mTextWatcherCelsius")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherFahrenheit = new TextWatcher() {
        @Override
        public void afterTextChanged(final Editable s) {
            mLastEditTextFocused = LastEditTextFocused.FAHRENHEIT;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherFahrenheit");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherFahrenheit");
                new Thread(new ConversionFromFahrenheitRunnable(s, "mTextWatcherFahrenheit")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherKelvin = new TextWatcher() {
        @Override
        public void afterTextChanged(final Editable s) {
            mLastEditTextFocused = LastEditTextFocused.KELVIN;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherKelvin");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherKelvin");
                new Thread(new ConversionFromKelvinRunnable(s, "mTextWatcherKelvin")).start();
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
            mTextInputLayoutCelsius =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_temperature_celsius));
            mTextInputLayoutFahrenheit =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_temperature_fahrenheit));
            mTextInputLayoutKelvin =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_temperature_kelvin));

            mEditTextCelsius =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_temperature_celsius));
            mEditTextFahrenheit =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_temperature_fahrenheit));
            mEditTextKelvin =
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

        if (mLastEditTextFocused == LastEditTextFocused.CELSIUS) {
            if ((getHandler() != null) && (mEditTextCelsius.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextCelsius.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromCelsiusRunnable(mEditTextCelsius.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.FAHRENHEIT) {
            if ((getHandler() != null) && (mEditTextFahrenheit.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextFahrenheit.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromFahrenheitRunnable(mEditTextFahrenheit.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.KELVIN) {
            if ((getHandler() != null) && (mEditTextKelvin.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextKelvin.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromKelvinRunnable(mEditTextKelvin.getText(),
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

        mEditTextCelsius.addTextChangedListener(mTextWatcherCelsius);
        mEditTextFahrenheit.addTextChangedListener(mTextWatcherFahrenheit);
        mEditTextKelvin.addTextChangedListener(mTextWatcherKelvin);
    }

    private void removeTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".removeTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".removeTextChangedListeners").i("Entered");
        }

        mEditTextCelsius.removeTextChangedListener(mTextWatcherCelsius);
        mEditTextFahrenheit.removeTextChangedListener(mTextWatcherFahrenheit);
        mEditTextKelvin.removeTextChangedListener(mTextWatcherKelvin);
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
        private final String TAG = ConversionFromCelsiusRunnable.class.getSimpleName();

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
                final Tuple<List<String>, ConversionErrorCodes> results =
                        Celsius.toAll(mEditableCelsius.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutCelsius.setError(getString(
                                            R.string.conversion_temperature_error_below_absolute_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutCelsius.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutCelsius.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutCelsius.setErrorEnabled(false);
                                    mTextInputLayoutFahrenheit.setErrorEnabled(false);
                                    mTextInputLayoutKelvin.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextFahrenheit.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKelvin.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromFahrenheitRunnable implements Runnable {
        private final String TAG = ConversionFromFahrenheitRunnable.class.getSimpleName();

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
                final Tuple<List<String>, ConversionErrorCodes> results =
                        Fahrenheit.toAll(mEditableFahrenheit.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutFahrenheit.setError(getString(
                                            R.string.conversion_temperature_error_below_absolute_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutFahrenheit.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutFahrenheit.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutCelsius.setErrorEnabled(false);
                                    mTextInputLayoutFahrenheit.setErrorEnabled(false);
                                    mTextInputLayoutKelvin.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextCelsius.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKelvin.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromKelvinRunnable implements Runnable {
        private final String TAG = ConversionFromKelvinRunnable.class.getSimpleName();

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
                final Tuple<List<String>, ConversionErrorCodes> results =
                        Kelvin.toAll(mEditableKelvin.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutKelvin.setError(getString(
                                            R.string.conversion_temperature_error_below_absolute_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutKelvin.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutKelvin.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutCelsius.setErrorEnabled(false);
                                    mTextInputLayoutFahrenheit.setErrorEnabled(false);
                                    mTextInputLayoutKelvin.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextCelsius.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextFahrenheit.setText(results.getValue0().get(1),
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
