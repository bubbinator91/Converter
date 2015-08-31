package com.bubbinator91.converter.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.converter.R;
import com.bubbinator91.converter.dagger.components.DaggerFragmentInjectorComponent;
import com.bubbinator91.converter.ui.interfaces.temperature.ITemperaturePresenter;
import com.bubbinator91.converter.ui.interfaces.temperature.ITemperatureView;
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

    private SimpleTextWatcher mTextWatcherCelsius, mTextWatcherFahrenheit, mTextWatcherKelvin;

    private int mLastEditTextFocused;

    @Inject ITemperaturePresenter mTemperaturePresenter;

    @Bind(R.id.editText_temperature_celsius)    AppCompatEditText mEditTextCelsius;
    @Bind(R.id.editText_temperature_fahrenheit) AppCompatEditText mEditTextFahrenheit;
    @Bind(R.id.editText_temperature_kelvin)     AppCompatEditText mEditTextKelvin;

    @Bind(R.id.textInputLayout_temperature_celsius)     TextInputLayout mTextInputLayoutCelsius;
    @Bind(R.id.textInputLayout_temperature_fahrenheit)  TextInputLayout mTextInputLayoutFahrenheit;
    @Bind(R.id.textInputLayout_temperature_kelvin)      TextInputLayout mTextInputLayoutKelvin;

    // region Lifecycle methods

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Timber.tag(TAG + ".onCreateView").i("Entered");

        if (getRootView() != null) {
            ButterKnife.bind(this, getRootView());

            mTextWatcherCelsius = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(final Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_CELSIUS;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherCelsius");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherCelsius");
                        getPresenter().getConversionFromCelsiusResults(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherFahrenheit = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(final Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_FAHRENHEIT;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherFahrenheit");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherFahrenheit");
                        getPresenter().getConversionFromFahrenheitResults(s.toString(),
                                getNumOfDecimalPlaces());
                    }
                }
            };

            mTextWatcherKelvin = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(final Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_KELVIN;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherKelvin");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherKelvin");
                        getPresenter().getConversionFromKelvinResults(
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
                mEditTextCelsius.setText(getSharedPreferences().getString(CELSIUS_VALUE_PERSIST_KEY, ""));
                mEditTextFahrenheit.setText(getSharedPreferences().getString(FAHRENHEIT_VALUE_PERSIST_KEY, ""));
                mEditTextKelvin.setText(getSharedPreferences().getString(KELVIN_VALUE_PERSIST_KEY, ""));
            }
            if (getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, -1) != -1) {
                mLastEditTextFocused = getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, 0);
            }
        }

        if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_CELSIUS) {
            if (mEditTextCelsius.getText() != null) {
                Utils.sanitizeEditable(mEditTextCelsius.getText());
                getPresenter().getConversionFromCelsiusResults(
                        mEditTextCelsius.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_FAHRENHEIT) {
            if (mEditTextFahrenheit.getText() != null) {
                Utils.sanitizeEditable(mEditTextFahrenheit.getText());
                getPresenter().getConversionFromFahrenheitResults(
                        mEditTextFahrenheit.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_KELVIN) {
            if (mEditTextKelvin.getText() != null) {
                Utils.sanitizeEditable(mEditTextKelvin.getText());
                getPresenter().getConversionFromKelvinResults(
                        mEditTextKelvin.getText().toString(),
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
                    .putString(CELSIUS_VALUE_PERSIST_KEY, mEditTextCelsius.getText().toString())
                    .putString(FAHRENHEIT_VALUE_PERSIST_KEY, mEditTextFahrenheit.getText().toString())
                    .putString(KELVIN_VALUE_PERSIST_KEY, mEditTextKelvin.getText().toString())
                    .putInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, mLastEditTextFocused)
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
    public void displayConversionFromCelsiusResults(List<String> results, int errorCode) {
        Timber.tag(TAG + ".displayConversionFromCelsiusResults").i("Entered");

        if (results != null) {
            removeTextChangedListeners(".displayConversionFromCelsiusResults");

            switch (errorCode) {
                case ConversionErrorCodes.ERROR_BELOW_ZERO:
                    mTextInputLayoutCelsius.setError(getString(
                            R.string.conversion_temperature_error_below_absolute_zero
                    ));
                    break;
                case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                    mTextInputLayoutCelsius.setError(getString(
                            R.string.conversion_error_input_not_numeric
                    ));
                    break;
                case ConversionErrorCodes.ERROR_UNKNOWN:
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

            mEditTextFahrenheit.setText(results.get(0),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextKelvin.setText(results.get(1),
                    AppCompatTextView.BufferType.EDITABLE);

            addTextChangedListeners(".displayConversionFromCelsiusResults");
        }
    }

    @Override
    public void displayConversionFromFahrenheitResults(List<String> results, int errorCode) {
        Timber.tag(TAG + ".displayConversionFromFahrenheitResults").i("Entered");

        if (results != null) {
            removeTextChangedListeners(".displayConversionFromFahrenheitResults");

            switch (errorCode) {
                case ConversionErrorCodes.ERROR_BELOW_ZERO:
                    mTextInputLayoutFahrenheit.setError(getString(
                            R.string.conversion_temperature_error_below_absolute_zero
                    ));
                    break;
                case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                    mTextInputLayoutFahrenheit.setError(getString(
                            R.string.conversion_error_input_not_numeric
                    ));
                    break;
                case ConversionErrorCodes.ERROR_UNKNOWN:
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

            mEditTextCelsius.setText(results.get(0),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextKelvin.setText(results.get(1),
                    AppCompatTextView.BufferType.EDITABLE);

            addTextChangedListeners(".displayConversionFromFahrenheitResults");
        }
    }

    @Override
    public void displayConversionFromKelvinResults(List<String> results, int errorCode) {
        Timber.tag(TAG + ".displayConversionFromKelvinResults").i("Entered");

        if (results != null) {
            removeTextChangedListeners(".onPostExecute");

            switch (errorCode) {
                case ConversionErrorCodes.ERROR_BELOW_ZERO:
                    mTextInputLayoutKelvin.setError(getString(
                            R.string.conversion_temperature_error_below_absolute_zero
                    ));
                    break;
                case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                    mTextInputLayoutKelvin.setError(getString(
                            R.string.conversion_error_input_not_numeric
                    ));
                    break;
                case ConversionErrorCodes.ERROR_UNKNOWN:
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

            mEditTextCelsius.setText(results.get(0),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextFahrenheit.setText(results.get(1),
                    AppCompatTextView.BufferType.EDITABLE);

            addTextChangedListeners(".onPostExecute");
        }
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

        mEditTextCelsius.addTextChangedListener(mTextWatcherCelsius);
        mEditTextFahrenheit.addTextChangedListener(mTextWatcherFahrenheit);
        mEditTextKelvin.addTextChangedListener(mTextWatcherKelvin);
    }

    @Override
    public void removeTextChangedListeners(String callingClassName) {
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
        if (mTemperaturePresenter == null) {
            DaggerFragmentInjectorComponent
                    .create()
                    .inject(this);
        }

        return mTemperaturePresenter;
    }

    @Override
    protected void registerViewWithPresenter() {
        getPresenter().registerView(this);
    }

    // endregion
}
