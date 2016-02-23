package com.bubbinator91.converter.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;
import com.bubbinator91.converter.R;
import com.bubbinator91.converter.dagger.components.DaggerFragmentInjectorComponent;
import com.bubbinator91.converter.interfaces.presenter.IFuelConsumptionPresenter;
import com.bubbinator91.converter.interfaces.view.IFuelConsumptionView;
import com.bubbinator91.converter.util.SimpleTextWatcher;
import com.bubbinator91.converter.util.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public class FuelConsumptionFragment
        extends BaseFragment<IFuelConsumptionPresenter>
        implements IFuelConsumptionView {
    private final String TAG = FuelConsumptionFragment.class.getSimpleName();

    private static final int LAST_EDIT_TEXT_FOCUSED_USMPG = 0;
    private static final int LAST_EDIT_TEXT_FOCUSED_UKMPG = 1;
    private static final int LAST_EDIT_TEXT_FOCUSED_KPL = 2;
    private static final int LAST_EDIT_TEXT_FOCUSED_L100K = 3;

    private static final String USMPG_VALUE_PERSIST_KEY = "FUEL_CONSUMPTION_FRAGMENT_USMPG_VALUE";
    private static final String UKMPG_VALUE_PERSIST_KEY = "FUEL_CONSUMPTION_FRAGMENT_UKMPG_VALUE";
    private static final String KPL_VALUE_PERSIST_KEY = "FUEL_CONSUMPTION_FRAGMENT_KPL_VALUE";
    private static final String L100K_VALUE_PERSIST_KEY = "FUEL_CONSUMPTION_FRAGMENT_L100K_VALUE";
    private static final String LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY = "FUEL_CONSUMPTION_FRAGMENT_LETF_VALUE";

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Timber.tag(TAG + ".onCreateView").i("Entered");

        if (getRootView() != null) {
            ButterKnife.bind(this, getRootView());

            textWatcherUsmpg = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_USMPG);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherUsmpg");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherUsmpg");
                        getPresenter().getConversionFromUSMilesPerGallon(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherUkmpg = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_UKMPG);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherUkmpg");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherUkmpg");
                        getPresenter().getConversionFromUKMilesPerGallon(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherKpl = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_KPL);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherKpl");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherKpl");
                        getPresenter().getConversionFromKilometersPerLiter(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherL100k = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_L100K);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherL100k");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherL100k");
                        getPresenter().getConversionFromLitersPer100Kilometers(
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
            if ((getSharedPreferences().getString(USMPG_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(UKMPG_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(KPL_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(L100K_VALUE_PERSIST_KEY, null) != null)) {
                editTextUsmpg.setText(getSharedPreferences().getString(USMPG_VALUE_PERSIST_KEY, ""));
                editTextUkmpg.setText(getSharedPreferences().getString(UKMPG_VALUE_PERSIST_KEY, ""));
                editTextKpl.setText(getSharedPreferences().getString(KPL_VALUE_PERSIST_KEY, ""));
                editTextL100k.setText(getSharedPreferences().getString(L100K_VALUE_PERSIST_KEY, ""));
            }
            if (getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, -1) != -1) {
                setLastEditTextFocused(getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, 0));
            }
        }

        if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_USMPG) {
            if (editTextUsmpg.getText() != null) {
                Utils.sanitizeEditable(editTextUsmpg.getText());
                getPresenter().getConversionFromUSMilesPerGallon(
                        editTextUsmpg.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_UKMPG) {
            if (editTextUkmpg.getText() != null) {
                Utils.sanitizeEditable(editTextUkmpg.getText());
                getPresenter().getConversionFromUKMilesPerGallon(
                        editTextUkmpg.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_KPL) {
            if (editTextKpl.getText() != null) {
                Utils.sanitizeEditable(editTextKpl.getText());
                getPresenter().getConversionFromKilometersPerLiter(
                        editTextKpl.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_L100K) {
            if (editTextL100k.getText() != null) {
                Utils.sanitizeEditable(editTextL100k.getText());
                getPresenter().getConversionFromLitersPer100Kilometers(
                        editTextL100k.getText().toString(),
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
                    .putString(USMPG_VALUE_PERSIST_KEY, editTextUsmpg.getText().toString())
                    .putString(UKMPG_VALUE_PERSIST_KEY, editTextUkmpg.getText().toString())
                    .putString(KPL_VALUE_PERSIST_KEY, editTextKpl.getText().toString())
                    .putString(L100K_VALUE_PERSIST_KEY, editTextL100k.getText().toString())
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

    // region Overridden IFuelConsumptionView methods

    @Override
    public void displayConversionFromKilometersPerLiterResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromKilometersPerLiterResults").i("Entered");
        removeTextChangedListeners(".displayConversionFromKilometersPerLiterResults");

        textInputLayoutUsmpg.setErrorEnabled(false);
        textInputLayoutUkmpg.setErrorEnabled(false);
        textInputLayoutKpl.setErrorEnabled(false);
        textInputLayoutL100k.setErrorEnabled(false);

        editTextUsmpg.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextUkmpg.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextL100k.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners(".displayConversionFromKilometersPerLiterResults");
    }

    @Override
    public void displayConversionFromKilometersPerLiterError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutKpl.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutKpl.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutKpl.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners(".displayConversionFromKilometersPerLiterError");
        editTextUsmpg.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextUkmpg.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextL100k.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners(".displayConversionFromKilometersPerLiterError");
    }

    @Override
    public void displayConversionFromLitersPer100KilometersResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromLitersPer100KilometersResults").i("Entered");
        removeTextChangedListeners(".displayConversionFromLitersPer100KilometersResults");

        textInputLayoutUsmpg.setErrorEnabled(false);
        textInputLayoutUkmpg.setErrorEnabled(false);
        textInputLayoutKpl.setErrorEnabled(false);
        textInputLayoutL100k.setErrorEnabled(false);

        editTextUsmpg.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextUkmpg.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextKpl.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners(".displayConversionFromLitersPer100KilometersResults");
    }

    @Override
    public void displayConversionFromLitersPer100KilometersError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutL100k.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutL100k.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutL100k.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners(".displayConversionFromLitersPer100KilometersError");
        editTextUsmpg.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextUkmpg.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKpl.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners(".displayConversionFromLitersPer100KilometersError");
    }

    @Override
    public void displayConversionFromUKMilesPerGallonResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromUKMilesPerGallonResults").i("Entered");
        removeTextChangedListeners(".displayConversionFromUKMilesPerGallonResults");

        textInputLayoutUsmpg.setErrorEnabled(false);
        textInputLayoutUkmpg.setErrorEnabled(false);
        textInputLayoutKpl.setErrorEnabled(false);
        textInputLayoutL100k.setErrorEnabled(false);

        editTextUsmpg.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextKpl.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextL100k.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners(".displayConversionFromUKMilesPerGallonResults");
    }

    @Override
    public void displayConversionFromUKMilesPerGallonError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutUkmpg.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutUkmpg.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutUkmpg.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners(".displayConversionFromUKMilesPerGallonError");
        editTextUsmpg.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKpl.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextL100k.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners(".displayConversionFromUKMilesPerGallonError");
    }

    @Override
    public void displayConversionFromUSMilesPerGallonResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromUSMilesPerGallonResults").i("Entered");
        removeTextChangedListeners(".displayConversionFromUSMilesPerGallonResults");

        textInputLayoutUsmpg.setErrorEnabled(false);
        textInputLayoutUkmpg.setErrorEnabled(false);
        textInputLayoutKpl.setErrorEnabled(false);
        textInputLayoutL100k.setErrorEnabled(false);

        editTextUkmpg.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextKpl.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextL100k.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners(".displayConversionFromUSMilesPerGallonResults");
    }

    @Override
    public void displayConversionFromUSMilesPerGallonError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutUsmpg.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutUsmpg.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutUsmpg.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners(".displayConversionFromUSMilesPerGallonError");
        editTextUkmpg.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKpl.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextL100k.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners(".displayConversionFromUSMilesPerGallonError");
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

    // endregion

    // region Overridden BaseFragment methods

    @Override
    protected String getChildTag() { return TAG; }

    @Override
    protected int getLayoutResource() { return R.layout.fragment_fuel_consumption; }

    @Override
    protected int getScrollViewResource() { return R.id.fragment_fuel_consumption; }

    @Override
    protected IFuelConsumptionPresenter getPresenter() {
        if (fuelConsumptionPresenter == null) {
            DaggerFragmentInjectorComponent
                    .create()
                    .inject(this);
        }

        return fuelConsumptionPresenter;
    }

    @Override
    protected void registerViewWithPresenter() {
        getPresenter().registerView(this);
    }

    // endregion
}
