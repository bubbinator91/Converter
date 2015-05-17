package com.bubbinator91.converter.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;

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

    private EditText editTextCelsius, editTextFahrenheit, editTextKelvin;

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

        setShouldHideToolbarOnScroll(false);

        if (getRootView() != null) {
            TextView textViewCelsius =
                    ((TextView) getRootView().findViewById(R.id.textView_temperature_celsius));
            TextView textViewFahrenheit =
                    ((TextView) getRootView().findViewById(R.id.textView_temperature_fahrenheit));
            TextView textViewKelvin =
                    ((TextView) getRootView().findViewById(R.id.textView_temperature_kelvin));

            if (getTypeFace() != null) {
                textViewCelsius.setTypeface(getTypeFace());
                textViewFahrenheit.setTypeface(getTypeFace());
                textViewKelvin.setTypeface(getTypeFace());
            }

            editTextCelsius =
                    ((EditText) getRootView().findViewById(R.id.editText_temperature_celsius));
            editTextFahrenheit =
                    ((EditText) getRootView().findViewById(R.id.editText_temperature_fahrenheit));
            editTextKelvin =
                    ((EditText) getRootView().findViewById(R.id.editText_temperature_kelvin));

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

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableCelsius != null) {
                if (Utils.isNumeric(mEditableCelsius.toString())) {
                    try {
                        BigDecimal celsius = new BigDecimal(mEditableCelsius.toString());
                        if (celsius.compareTo(new BigDecimal("-273.15")) >= 0) {
                            BigDecimal fahrenheit = celsius.multiply(new BigDecimal("1.8"))
                                    .add(new BigDecimal("32"));
                            BigDecimal kelvin = celsius.add(new BigDecimal("273.15"));

                            results.add(fahrenheit
                                    .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                    .stripTrailingZeros()
                                    .toPlainString());
                            results.add(kelvin
                                    .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                    .stripTrailingZeros()
                                    .toPlainString());
                        } else {
                            results.add("Input is below absolute zero");
                            results.add("Input is below absolute zero");
                        }
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 2);
                    }
                } else {
                    addWhitespaceItems(results, 2);
                }
            } else {
                addWhitespaceItems(results, 2);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextFahrenheit.setText(results.get(0)
                            , TextView.BufferType.EDITABLE);
                    editTextKelvin.setText(results.get(1)
                            , TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
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

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableFahrenheit != null) {
                if (Utils.isNumeric(mEditableFahrenheit.toString())) {
                    try {
                        BigDecimal fahrenheit = new BigDecimal(mEditableFahrenheit.toString());
                        if (fahrenheit.compareTo(new BigDecimal("-459.67")) >= 0) {
                            BigDecimal celsius = fahrenheit.subtract(new BigDecimal("32"))
                                    .multiply(new BigDecimal(5.0 / 9.0));
                            BigDecimal kelvin = celsius.add(new BigDecimal("273.15"));

                            results.add(celsius
                                    .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                    .stripTrailingZeros()
                                    .toPlainString());
                            results.add(kelvin
                                    .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                    .stripTrailingZeros()
                                    .toPlainString());
                        } else {
                            results.add("Input is below absolute zero");
                            results.add("Input is below absolute zero");
                        }
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 2);
                    }
                } else {
                    addWhitespaceItems(results, 2);
                }
            } else {
                addWhitespaceItems(results, 2);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextCelsius.setText(results.get(0)
                            , TextView.BufferType.EDITABLE);
                    editTextKelvin.setText(results.get(1)
                            , TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
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

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableKelvin != null) {
                if (Utils.isNumeric(mEditableKelvin.toString())) {
                    try {
                        BigDecimal kelvin = new BigDecimal(mEditableKelvin.toString());
                        BigDecimal celsius = kelvin.subtract(new BigDecimal("273.15"));
                        BigDecimal fahrenheit = celsius.multiply(new BigDecimal("1.8"))
                                .add(new BigDecimal("32"));

                        results.add(celsius
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(fahrenheit
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 2);
                    }
                } else {
                    addWhitespaceItems(results, 2);
                }
            } else {
                addWhitespaceItems(results, 2);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextCelsius.setText(results.get(0)
                            , TextView.BufferType.EDITABLE);
                    editTextFahrenheit.setText(results.get(1)
                            , TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
        }
    }

    // endregion
}
