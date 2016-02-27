package com.bubbinator91.converter.views.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;
import com.bubbinator91.converter.R;
import com.bubbinator91.converter.dagger.components.DaggerFragmentInjectorComponent;
import com.bubbinator91.converter.interfaces.presenter.IFuelConsumptionPresenter;
import com.bubbinator91.converter.interfaces.view.IFuelConsumptionView;
import com.bubbinator91.converter.models.FuelConsumptionModel;
import com.bubbinator91.converter.models.FuelConsumptionModel.FuelConsumptionUnits;
import com.bubbinator91.converter.util.PresenterCache;
import com.bubbinator91.converter.util.SimpleTextWatcher;
import com.bubbinator91.converter.util.Utils;
import com.bubbinator91.converter.views.base.BaseFragment2;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public class FuelConsumptionFragment
        extends BaseFragment2<IFuelConsumptionPresenter>
        implements IFuelConsumptionView {
    private final String TAG = FuelConsumptionFragment.class.getSimpleName();

    private static final String USMPG_VALUE_PERSIST_KEY = "FUEL_CONSUMPTION_FRAGMENT_USMPG_VALUE";
    private static final String UKMPG_VALUE_PERSIST_KEY = "FUEL_CONSUMPTION_FRAGMENT_UKMPG_VALUE";
    private static final String KPL_VALUE_PERSIST_KEY = "FUEL_CONSUMPTION_FRAGMENT_KPL_VALUE";
    private static final String L100K_VALUE_PERSIST_KEY = "FUEL_CONSUMPTION_FRAGMENT_L100K_VALUE";

    private SimpleTextWatcher textWatcherUsmpg, textWatcherUkmpg, textWatcherKpl, textWatcherL100k;

    @Inject IFuelConsumptionPresenter fuelConsumptionPresenter;

    @Bind(R.id.editText_fuel_consumption_usmpg) AppCompatEditText editTextUsmpg;
    @Bind(R.id.editText_fuel_consumption_ukmpg) AppCompatEditText editTextUkmpg;
    @Bind(R.id.editText_fuel_consumption_kpl)   AppCompatEditText editTextKpl;
    @Bind(R.id.editText_fuel_consumption_l100k) AppCompatEditText editTextL100k;

    @Bind(R.id.textInputLayout_fuel_consumption_usmpg)  TextInputLayout textInputLayoutUsmpg;
    @Bind(R.id.textInputLayout_fuel_consumption_ukmpg)  TextInputLayout textInputLayoutUkmpg;
    @Bind(R.id.textInputLayout_fuel_consumption_kpl)    TextInputLayout textInputLayoutKpl;
    @Bind(R.id.textInputLayout_fuel_consumption_l100k)  TextInputLayout textInputLayoutL100k;

    // region Lifecycle methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            fuelConsumptionPresenter = PresenterCache.getInstance().restorePresenter(savedInstanceState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Timber.tag(TAG + ".onCreateView").i("Entered");

        if (getRootView() != null) {
            ButterKnife.bind(this, getRootView());

            textWatcherUsmpg = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    if (s != null) {
                        removeTextChangedListeners("textWatcherUsmpg");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherUsmpg");
                        getPresenter().afterUsmpgTextChanged(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherUkmpg = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    if (s != null) {
                        removeTextChangedListeners("textWatcherUkmpg");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherUkmpg");
                        getPresenter().afterUkmpgTextChanged(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherKpl = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    if (s != null) {
                        removeTextChangedListeners("textWatcherKpl");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherKpl");
                        getPresenter().afterKplTextChanged(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherL100k = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    if (s != null) {
                        removeTextChangedListeners("textWatcherL100k");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherL100k");
                        getPresenter().afterL100kmTextChanged(
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
    public void showNewValuesFromModel(FuelConsumptionModel model) {
        Timber.tag(TAG + ".showNewValuesFromModel").i("Entered");

        showNewValuesFromModelExcludingSource(model, null);
    }

    @Override
    public void showNewValuesFromModelExcludingSource(FuelConsumptionModel model, FuelConsumptionUnits source) {
        Timber.tag(TAG + ".showNewValuesFromModelExcludingSource").i("Entered");
        removeTextChangedListeners("showNewValuesFromModelExcludingSource");

        textInputLayoutUsmpg.setErrorEnabled(false);
        textInputLayoutUkmpg.setErrorEnabled(false);
        textInputLayoutKpl.setErrorEnabled(false);
        textInputLayoutL100k.setErrorEnabled(false);

        if (source != FuelConsumptionUnits.usmpg) {
            editTextUsmpg.setText(model.getUsmpg(), AppCompatEditText.BufferType.EDITABLE);
        }
        if (source != FuelConsumptionUnits.ukmpg) {
            editTextUkmpg.setText(model.getUkmpg(), AppCompatEditText.BufferType.EDITABLE);
        }
        if (source != FuelConsumptionUnits.kpl) {
            editTextKpl.setText(model.getKpl(), AppCompatEditText.BufferType.EDITABLE);
        }
        if (source != FuelConsumptionUnits.l100km) {
            editTextL100k.setText(model.getL100km(), AppCompatEditText.BufferType.EDITABLE);
        }

        addTextChangedListeners("showNewValuesFromModelExcludingSource");
    }

    @Override
    public void showErrorForSource(Throwable error, FuelConsumptionUnits source) {
        Timber.tag(TAG + ".showErrorForSource").i("Entered");
        removeTextChangedListeners("showErrorForSource");

        String errorText;
        if (error instanceof NumberFormatException) {
            errorText = getString(R.string.conversion_error_input_not_numeric);
        } else if (error instanceof ValueBelowZeroException) {
            errorText = getString(R.string.conversion_error_below_zero);
        } else {
            errorText = getString(R.string.conversion_error_conversion_error);
        }

        switch (source) {
            case usmpg:
                textInputLayoutUsmpg.setError(errorText);
                editTextUkmpg.setText("", AppCompatEditText.BufferType.EDITABLE);
                editTextKpl.setText("", AppCompatEditText.BufferType.EDITABLE);
                editTextL100k.setText("", AppCompatEditText.BufferType.EDITABLE);
                break;
            case ukmpg:
                textInputLayoutUkmpg.setError(errorText);
                editTextUsmpg.setText("", AppCompatEditText.BufferType.EDITABLE);
                editTextKpl.setText("", AppCompatEditText.BufferType.EDITABLE);
                editTextL100k.setText("", AppCompatEditText.BufferType.EDITABLE);
                break;
            case kpl:
                textInputLayoutKpl.setError(errorText);
                editTextUsmpg.setText("", AppCompatEditText.BufferType.EDITABLE);
                editTextUkmpg.setText("", AppCompatEditText.BufferType.EDITABLE);
                editTextL100k.setText("", AppCompatEditText.BufferType.EDITABLE);
                break;
            case l100km:
                textInputLayoutL100k.setError(errorText);
                editTextUsmpg.setText("", AppCompatEditText.BufferType.EDITABLE);
                editTextUkmpg.setText("", AppCompatEditText.BufferType.EDITABLE);
                editTextKpl.setText("", AppCompatEditText.BufferType.EDITABLE);
                break;
            default:
                break;
        }

        addTextChangedListeners("showErrorForSource");
    }

    @Override
    public FuelConsumptionModel loadModel() {
        if (getSharedPreferences() != null) {
            return new FuelConsumptionModel(
                    getSharedPreferences().getString(USMPG_VALUE_PERSIST_KEY, "1"),
                    getSharedPreferences().getString(UKMPG_VALUE_PERSIST_KEY, "1.2009499255398"),
                    getSharedPreferences().getString(KPL_VALUE_PERSIST_KEY, "0.42514370749052"),
                    getSharedPreferences().getString(L100K_VALUE_PERSIST_KEY, "1.609344")
            );
        } else {
            return new FuelConsumptionModel();
        }
    }

    @Override
    public void saveModel(FuelConsumptionModel model) {
        if (getSharedPreferences() != null) {
            getSharedPreferences().edit()
                    .putString(USMPG_VALUE_PERSIST_KEY, model.getUsmpg())
                    .putString(UKMPG_VALUE_PERSIST_KEY, model.getUkmpg())
                    .putString(KPL_VALUE_PERSIST_KEY, model.getKpl())
                    .putString(L100K_VALUE_PERSIST_KEY, model.getL100km())
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

        editTextUsmpg.addTextChangedListener(textWatcherUsmpg);
        editTextUkmpg.addTextChangedListener(textWatcherUkmpg);
        editTextKpl.addTextChangedListener(textWatcherKpl);
        editTextL100k.addTextChangedListener(textWatcherL100k);
    }

    @Override
    public void removeTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".removeTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".removeTextChangedListeners").i("Entered");
        }

        editTextUsmpg.removeTextChangedListener(textWatcherUsmpg);
        editTextUkmpg.removeTextChangedListener(textWatcherUkmpg);
        editTextKpl.removeTextChangedListener(textWatcherKpl);
        editTextL100k.removeTextChangedListener(textWatcherL100k);
    }

    @Override
    protected String getChildTag() {
        return TAG;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_fuel_consumption;
    }

    @Override
    protected int getScrollViewResource() {
        return R.id.fragment_fuel_consumption;
    }

    @Override
    protected IFuelConsumptionPresenter getPresenter() {
        if (fuelConsumptionPresenter == null) {
            DaggerFragmentInjectorComponent
                    .create()
                    .inject(this);
        }

        return fuelConsumptionPresenter;
    }

    // endregion
}
