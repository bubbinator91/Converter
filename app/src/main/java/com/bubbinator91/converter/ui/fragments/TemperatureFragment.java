package com.bubbinator91.converter.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;
import com.bubbinator91.converter.dagger.components.DaggerFragmentInjectorComponent;
import com.bubbinator91.converter.interfaces.presenter.ITemperaturePresenter;
import com.bubbinator91.converter.interfaces.view.ITemperatureView;
import com.bubbinator91.converter.util.SimpleTextWatcher;
import com.bubbinator91.converter.util.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Celsius, Fahrenheit, Kelvin
 */
public class TemperatureFragment
        extends BaseFragment<ITemperaturePresenter>
        implements ITemperatureView {
    private final String TAG = TemperatureFragment.class.getSimpleName();

    private static final int LAST_EDIT_TEXT_FOCUSED_CELSIUS = 0;
    private static final int LAST_EDIT_TEXT_FOCUSED_FAHRENHEIT = 1;
    private static final int LAST_EDIT_TEXT_FOCUSED_KELVIN = 2;

    private static final String CELSIUS_VALUE_PERSIST_KEY = "TEMP_FRAGMENT_CELSIUS_VALUE";
    private static final String FAHRENHEIT_VALUE_PERSIST_KEY = "TEMP_FRAGMENT_FAHRENHEIT_VALUE";
    private static final String KELVIN_VALUE_PERSIST_KEY = "TEMP_FRAGMENT_KELVIN_VALUE";
    private static final String LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY = "ACCELERATION_FRAGMENT_LETF_VALUE";

    private SimpleTextWatcher textWatcherCelsius, textWatcherFahrenheit, textWatcherKelvin;

    @Inject ITemperaturePresenter temperaturePresenter;

    @Bind(R.id.editText_temperature_celsius)    AppCompatEditText editTextCelsius;
    @Bind(R.id.editText_temperature_fahrenheit) AppCompatEditText editTextFahrenheit;
    @Bind(R.id.editText_temperature_kelvin)     AppCompatEditText editTextKelvin;

    @Bind(R.id.textInputLayout_temperature_celsius)     TextInputLayout textInputLayoutCelsius;
    @Bind(R.id.textInputLayout_temperature_fahrenheit)  TextInputLayout textInputLayoutFahrenheit;
    @Bind(R.id.textInputLayout_temperature_kelvin)      TextInputLayout textInputLayoutKelvin;

    // region Lifecycle methods

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Timber.tag(TAG + ".onCreateView").i("Entered");

        if (getRootView() != null) {
            ButterKnife.bind(this, getRootView());

            textWatcherCelsius = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(final Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_CELSIUS);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherCelsius");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherCelsius");
                        getPresenter().getConversionFromCelsius(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherFahrenheit = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(final Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_FAHRENHEIT);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherFahrenheit");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherFahrenheit");
                        getPresenter().getConversionFromFahrenheit(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherKelvin = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(final Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_KELVIN);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherKelvin");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherKelvin");
                        getPresenter().getConversionFromKelvin(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };
        }
        return getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.tag(TAG + ".onResume").i("Entered");

        removeTextChangedListeners("onResume");

        if (!wasResumed() && (getSharedPreferences() != null)) {
            if ((getSharedPreferences().getString(CELSIUS_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(FAHRENHEIT_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(KELVIN_VALUE_PERSIST_KEY, null) != null)) {
                editTextCelsius.setText(getSharedPreferences().getString(CELSIUS_VALUE_PERSIST_KEY, ""));
                editTextFahrenheit.setText(getSharedPreferences().getString(FAHRENHEIT_VALUE_PERSIST_KEY, ""));
                editTextKelvin.setText(getSharedPreferences().getString(KELVIN_VALUE_PERSIST_KEY, ""));
            }
            if (getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, -1) != -1) {
                setLastEditTextFocused(getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, 0));
            }
        }

        if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_CELSIUS) {
            if (editTextCelsius.getText() != null) {
                Utils.sanitizeEditable(editTextCelsius.getText());
                getPresenter().getConversionFromCelsius(
                        editTextCelsius.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_FAHRENHEIT) {
            if (editTextFahrenheit.getText() != null) {
                Utils.sanitizeEditable(editTextFahrenheit.getText());
                getPresenter().getConversionFromFahrenheit(
                        editTextFahrenheit.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_KELVIN) {
            if (editTextKelvin.getText() != null) {
                Utils.sanitizeEditable(editTextKelvin.getText());
                getPresenter().getConversionFromKelvin(
                        editTextKelvin.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else {
            addTextChangedListeners("onResume");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.tag(TAG + ".onPause").i("Entered");

        if (getSharedPreferences() != null) {
            getSharedPreferences()
                    .edit()
                    .putString(CELSIUS_VALUE_PERSIST_KEY, editTextCelsius.getText().toString())
                    .putString(FAHRENHEIT_VALUE_PERSIST_KEY, editTextFahrenheit.getText().toString())
                    .putString(KELVIN_VALUE_PERSIST_KEY, editTextKelvin.getText().toString())
                    .putInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, getLastEditTextFocused())
                    .apply();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Timber.tag(TAG + ".onDestroyView").i("Entered");
        ButterKnife.unbind(this);
    }

    // endregion

    // region Overridden ITemperatureView methods

    @Override
    public void displayConversionFromCelsiusResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromCelsiusResults").i("Entered");
        removeTextChangedListeners("displayConversionFromCelsiusResults");

        textInputLayoutCelsius.setErrorEnabled(false);
        textInputLayoutFahrenheit.setErrorEnabled(false);
        textInputLayoutKelvin.setErrorEnabled(false);

        editTextFahrenheit.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextKelvin.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromCelsiusResults");
    }

    @Override
    public void displayConversionFromCelsiusError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutCelsius.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutCelsius.setError(getString(
                    R.string.conversion_temperature_error_below_absolute_zero
            ));
        } else {
            textInputLayoutCelsius.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromCelsiusError");
        editTextFahrenheit.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKelvin.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromCelsiusError");
    }

    @Override
    public void displayConversionFromFahrenheitResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromFahrenheitResults").i("Entered");
        removeTextChangedListeners("displayConversionFromFahrenheitResults");

        textInputLayoutCelsius.setErrorEnabled(false);
        textInputLayoutFahrenheit.setErrorEnabled(false);
        textInputLayoutKelvin.setErrorEnabled(false);

        editTextCelsius.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextKelvin.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromFahrenheitResults");
    }

    @Override
    public void displayConversionFromFahrenheitError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutFahrenheit.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutFahrenheit.setError(getString(
                    R.string.conversion_temperature_error_below_absolute_zero
            ));
        } else {
            textInputLayoutFahrenheit.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromFahrenheitError");
        editTextCelsius.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKelvin.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromFahrenheitError");
    }

    @Override
    public void displayConversionFromKelvinResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromKelvinResults").i("Entered");
        removeTextChangedListeners("displayConversionFromKelvinResults");

        textInputLayoutCelsius.setErrorEnabled(false);
        textInputLayoutFahrenheit.setErrorEnabled(false);
        textInputLayoutKelvin.setErrorEnabled(false);

        editTextCelsius.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextFahrenheit.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromKelvinResults");
    }

    @Override
    public void displayConversionFromKelvinError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutKelvin.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutKelvin.setError(getString(
                    R.string.conversion_temperature_error_below_absolute_zero
            ));
        } else {
            textInputLayoutKelvin.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromKelvinError");
        editTextCelsius.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextFahrenheit.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromKelvinError");
    }

    // endregion

    // region Overridden IConverterView methods

    @Override
    public void addTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".addTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".addTextChangedListeners").i("Entered");
        }

        editTextCelsius.addTextChangedListener(textWatcherCelsius);
        editTextFahrenheit.addTextChangedListener(textWatcherFahrenheit);
        editTextKelvin.addTextChangedListener(textWatcherKelvin);
    }

    @Override
    public void removeTextChangedListeners(String callingClassName) {
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
    protected String getChildTag() {
        return TAG;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_temperature;
    }

    @Override
    protected int getScrollViewResource() {
        return R.id.fragment_temperature;
    }

    @Override
    protected ITemperaturePresenter getPresenter() {
        if (temperaturePresenter == null) {
            DaggerFragmentInjectorComponent
                    .create()
                    .inject(this);
        }

        return temperaturePresenter;
    }

    @Override
    protected void registerViewWithPresenter() {
        getPresenter().registerView(this);
    }

    // endregion
}
