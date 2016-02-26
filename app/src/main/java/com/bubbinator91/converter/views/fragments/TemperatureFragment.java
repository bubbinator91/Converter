package com.bubbinator91.converter.views.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;
import com.bubbinator91.converter.dagger.components.DaggerFragmentInjectorComponent;
import com.bubbinator91.converter.interfaces.presenter.ITemperaturePresenter;
import com.bubbinator91.converter.interfaces.view.ITemperatureView;
import com.bubbinator91.converter.models.TemperatureModel;
import com.bubbinator91.converter.util.PresenterCache;
import com.bubbinator91.converter.util.SimpleTextWatcher;
import com.bubbinator91.converter.util.Utils;
import com.bubbinator91.converter.views.base.BaseFragment2;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Celsius, Fahrenheit, Kelvin
 */
public class TemperatureFragment
        extends BaseFragment2<ITemperaturePresenter>
        implements ITemperatureView {
    private final String TAG = TemperatureFragment.class.getSimpleName();

    private static final String CELSIUS_VALUE_PERSIST_KEY = "TEMP_FRAGMENT_CELSIUS_VALUE";
    private static final String FAHRENHEIT_VALUE_PERSIST_KEY = "TEMP_FRAGMENT_FAHRENHEIT_VALUE";
    private static final String KELVIN_VALUE_PERSIST_KEY = "TEMP_FRAGMENT_KELVIN_VALUE";

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            temperaturePresenter = PresenterCache.getInstance().restorePresenter(savedInstanceState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Timber.tag(TAG + ".onCreateView").i("Entered");

        if (getRootView() != null) {
            ButterKnife.bind(this, getRootView());

            textWatcherCelsius = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(final Editable s) {
                    if (s != null) {
                        removeTextChangedListeners("textWatcherCelsius");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherCelsius");
                        getPresenter().afterCelsiusTextChanged(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherFahrenheit = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(final Editable s) {
                    if (s != null) {
                        removeTextChangedListeners("textWatcherFahrenheit");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherFahrenheit");
                        getPresenter().afterFahrenheitTextChanged(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherKelvin = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(final Editable s) {
                    if (s != null) {
                        removeTextChangedListeners("textWatcherKelvin");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherKelvin");
                        getPresenter().afterKelvinTextChanged(
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

        getPresenter().registerView(this);
        getPresenter().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.tag(TAG + ".onPause").i("Entered");

        getPresenter().onPause();
        getPresenter().unregisterView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Timber.tag(TAG + ".onDestroyView").i("Entered");
        ButterKnife.unbind(this);
    }

    // endregion

    // region Interface methods

    @Override
    public void showNewValuesFromModel(TemperatureModel model) {
        Timber.tag(TAG + ".showNewValuesFromModel").i("Entered");

        showNewValuesFromModelExcludingSource(model, null);
    }

    @Override
    public void showNewValuesFromModelExcludingSource(TemperatureModel model, TemperatureModel.TemperatureValues source) {
        Timber.tag(TAG + ".showNewValuesFromModelExcludingSource").i("Entered");
        removeTextChangedListeners("showNewValuesFromModelExcludingSource");

        textInputLayoutCelsius.setErrorEnabled(false);
        textInputLayoutFahrenheit.setErrorEnabled(false);
        textInputLayoutKelvin.setErrorEnabled(false);

        if (source != TemperatureModel.TemperatureValues.celsius) {
            editTextCelsius.setText(model.getCelsius(), AppCompatEditText.BufferType.EDITABLE);
        }
        if (source != TemperatureModel.TemperatureValues.fahrenheit) {
            editTextFahrenheit.setText(model.getFahrenheit(), AppCompatEditText.BufferType.EDITABLE);
        }
        if (source != TemperatureModel.TemperatureValues.kelvin) {
            editTextKelvin.setText(model.getKelvin(), AppCompatEditText.BufferType.EDITABLE);
        }

        addTextChangedListeners("showNewValuesFromModelExcludingSource");
    }

    @Override
    public void showErrorForSource(Throwable error, TemperatureModel.TemperatureValues source) {
        Timber.tag(TAG + ".showErrorForSource").i("Entered");
        removeTextChangedListeners("showErrorForSource");

        String errorText;
        if (error instanceof NumberFormatException) {
            errorText = getString(R.string.conversion_error_input_not_numeric);
        } else if (error instanceof ValueBelowZeroException) {
            errorText = getString(R.string.conversion_temperature_error_below_absolute_zero);
        } else {
            errorText = getString(R.string.conversion_error_conversion_error);
        }

        switch (source) {
            case celsius:
                textInputLayoutCelsius.setError(errorText);
                editTextFahrenheit.setText("", AppCompatEditText.BufferType.EDITABLE);
                editTextKelvin.setText("", AppCompatEditText.BufferType.EDITABLE);
                break;
            case fahrenheit:
                textInputLayoutFahrenheit.setError(errorText);
                editTextCelsius.setText("", AppCompatEditText.BufferType.EDITABLE);
                editTextKelvin.setText("", AppCompatEditText.BufferType.EDITABLE);
                break;
            case kelvin:
                textInputLayoutKelvin.setError(errorText);
                editTextCelsius.setText("", AppCompatEditText.BufferType.EDITABLE);
                editTextFahrenheit.setText("", AppCompatEditText.BufferType.EDITABLE);
                break;
            default:
                break;
        }

        addTextChangedListeners("showErrorForSource");
    }

    @Override
    public TemperatureModel loadModel() {
        if (getSharedPreferences() != null) {
            return new TemperatureModel(
                    getSharedPreferences().getString(CELSIUS_VALUE_PERSIST_KEY, "0"),
                    getSharedPreferences().getString(FAHRENHEIT_VALUE_PERSIST_KEY, "32"),
                    getSharedPreferences().getString(KELVIN_VALUE_PERSIST_KEY, "273.15")
            );
        } else {
            return new TemperatureModel();
        }
    }

    @Override
    public void saveModel(TemperatureModel model) {
        if (getSharedPreferences() != null) {
            getSharedPreferences().edit()
                    .putString(CELSIUS_VALUE_PERSIST_KEY, model.getCelsius())
                    .putString(FAHRENHEIT_VALUE_PERSIST_KEY, model.getFahrenheit())
                    .putString(KELVIN_VALUE_PERSIST_KEY, model.getKelvin())
                    .apply();
        }
    }

    // endregion

    // region Abstract methods

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

    // endregion
}
