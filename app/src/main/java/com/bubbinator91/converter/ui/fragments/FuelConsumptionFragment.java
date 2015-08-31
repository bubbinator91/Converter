package com.bubbinator91.converter.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.converter.R;
import com.bubbinator91.converter.tasks.fuelconsumption.FromKilometersPerLiterTask;
import com.bubbinator91.converter.tasks.fuelconsumption.FromLitersPer100KilometersTask;
import com.bubbinator91.converter.tasks.fuelconsumption.FromUKMilesPerGallonTask;
import com.bubbinator91.converter.tasks.fuelconsumption.FromUSMilesPerGallonTask;
import com.bubbinator91.converter.ui.interfaces.temperature.ITemperaturePresenter;
import com.bubbinator91.converter.util.SimpleTextWatcher;
import com.bubbinator91.converter.util.Utils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public class FuelConsumptionFragment extends BaseFragment<ITemperaturePresenter> {
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

    @Bind(R.id.editText_fuel_consumption_usmpg) AppCompatEditText mEditTextUsmpg;
    @Bind(R.id.editText_fuel_consumption_ukmpg) AppCompatEditText mEditTextUkmpg;
    @Bind(R.id.editText_fuel_consumption_kpl)   AppCompatEditText mEditTextKpl;
    @Bind(R.id.editText_fuel_consumption_l100k) AppCompatEditText mEditTextL100k;

    @Bind(R.id.textInputLayout_fuel_consumption_usmpg)  TextInputLayout mTextInputLayoutUsmpg;
    @Bind(R.id.textInputLayout_fuel_consumption_ukmpg)  TextInputLayout mTextInputLayoutUkmpg;
    @Bind(R.id.textInputLayout_fuel_consumption_kpl)    TextInputLayout mTextInputLayoutKpl;
    @Bind(R.id.textInputLayout_fuel_consumption_l100k)  TextInputLayout mTextInputLayoutL100k;

    private SimpleTextWatcher mTextWatcherUsmpg, mTextWatcherUkmpg, mTextWatcherKpl,
            mTextWatcherL100k;

    private int mLastEditTextFocused;

    // region Lifecycle methods

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Timber.tag(TAG + ".onCreateView").i("Entered");

        if (getRootView() != null) {
            ButterKnife.bind(this, getRootView());

            mTextWatcherUsmpg = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_USMPG;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherUsmpg");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherUsmpg");
                        convertFromUSMilesPerGallon(s.toString());
                    }
                }
            };

            mTextWatcherUkmpg = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_UKMPG;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherUkmpg");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherUkmpg");
                        convertFromUKMilesPerGallon(s.toString());
                    }
                }
            };

            mTextWatcherKpl = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_KPL;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherKpl");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherKpl");
                        convertFromKilometersPerLiter(s.toString());
                    }
                }
            };

            mTextWatcherL100k = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_L100K;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherL100k");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherL100k");
                        convertFromLitersPer100Kilometers(s.toString());
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
                mEditTextUsmpg.setText(getSharedPreferences().getString(USMPG_VALUE_PERSIST_KEY, ""));
                mEditTextUkmpg.setText(getSharedPreferences().getString(UKMPG_VALUE_PERSIST_KEY, ""));
                mEditTextKpl.setText(getSharedPreferences().getString(KPL_VALUE_PERSIST_KEY, ""));
                mEditTextL100k.setText(getSharedPreferences().getString(L100K_VALUE_PERSIST_KEY, ""));
            }
            if (getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, -1) != -1) {
                mLastEditTextFocused = getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, 0);
            }
        }

        if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_USMPG) {
            if (mEditTextUsmpg.getText() != null) {
                Utils.sanitizeEditable(mEditTextUsmpg.getText());
                convertFromUSMilesPerGallon(mEditTextUsmpg.getText().toString());
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_UKMPG) {
            if (mEditTextUkmpg.getText() != null) {
                Utils.sanitizeEditable(mEditTextUkmpg.getText());
                convertFromUKMilesPerGallon(mEditTextUkmpg.getText().toString());
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_KPL) {
            if (mEditTextKpl.getText() != null) {
                Utils.sanitizeEditable(mEditTextKpl.getText());
                convertFromKilometersPerLiter(mEditTextKpl.getText().toString());
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_L100K) {
            if (mEditTextL100k.getText() != null) {
                Utils.sanitizeEditable(mEditTextL100k.getText());
                convertFromLitersPer100Kilometers(mEditTextL100k.getText().toString());
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
                    .putString(USMPG_VALUE_PERSIST_KEY, mEditTextUsmpg.getText().toString())
                    .putString(UKMPG_VALUE_PERSIST_KEY, mEditTextUkmpg.getText().toString())
                    .putString(KPL_VALUE_PERSIST_KEY, mEditTextKpl.getText().toString())
                    .putString(L100K_VALUE_PERSIST_KEY, mEditTextL100k.getText().toString())
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

    // region Helper methods

    private void addTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".addTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".addTextChangedListeners").i("Entered");
        }

        mEditTextUsmpg.addTextChangedListener(mTextWatcherUsmpg);
        mEditTextUkmpg.addTextChangedListener(mTextWatcherUkmpg);
        mEditTextKpl.addTextChangedListener(mTextWatcherKpl);
        mEditTextL100k.addTextChangedListener(mTextWatcherL100k);
    }

    private void removeTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".addTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".addTextChangedListeners").i("Entered");
        }

        mEditTextUsmpg.removeTextChangedListener(mTextWatcherUsmpg);
        mEditTextUkmpg.removeTextChangedListener(mTextWatcherUkmpg);
        mEditTextKpl.removeTextChangedListener(mTextWatcherKpl);
        mEditTextL100k.removeTextChangedListener(mTextWatcherL100k);
    }

    private void convertFromUSMilesPerGallon(String usmpg) {
        String[] params = new String[2];
        params[0] = usmpg;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromUSMilesPerGallonTask task = new FromUSMilesPerGallonTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, Integer> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
                        case ConversionErrorCodes.ERROR_BELOW_ZERO:
                            mTextInputLayoutUsmpg.setError(getString(
                                    R.string.conversion_error_below_zero
                            ));
                            break;
                        case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                            mTextInputLayoutUsmpg.setError(getString(
                                    R.string.conversion_error_input_not_numeric
                            ));
                            break;
                        case ConversionErrorCodes.ERROR_UNKNOWN:
                            mTextInputLayoutUsmpg.setError(getString(
                                    R.string.conversion_error_conversion_error
                            ));
                            break;
                        default:
                            mTextInputLayoutUsmpg.setErrorEnabled(false);
                            mTextInputLayoutUkmpg.setErrorEnabled(false);
                            mTextInputLayoutKpl.setErrorEnabled(false);
                            mTextInputLayoutL100k.setErrorEnabled(false);
                            break;
                    }

                    mEditTextUkmpg.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextKpl.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextL100k.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromUKMilesPerGallon(String ukmpg) {
        String[] params = new String[2];
        params[0] = ukmpg;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromUKMilesPerGallonTask task = new FromUKMilesPerGallonTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, Integer> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
                        case ConversionErrorCodes.ERROR_BELOW_ZERO:
                            mTextInputLayoutUkmpg.setError(getString(
                                    R.string.conversion_error_below_zero
                            ));
                            break;
                        case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                            mTextInputLayoutUkmpg.setError(getString(
                                    R.string.conversion_error_input_not_numeric
                            ));
                            break;
                        case ConversionErrorCodes.ERROR_UNKNOWN:
                            mTextInputLayoutUkmpg.setError(getString(
                                    R.string.conversion_error_conversion_error
                            ));
                            break;
                        default:
                            mTextInputLayoutUsmpg.setErrorEnabled(false);
                            mTextInputLayoutUkmpg.setErrorEnabled(false);
                            mTextInputLayoutKpl.setErrorEnabled(false);
                            mTextInputLayoutL100k.setErrorEnabled(false);
                            break;
                    }

                    mEditTextUsmpg.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextKpl.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextL100k.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromKilometersPerLiter(String kpl) {
        String[] params = new String[2];
        params[0] = kpl;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromKilometersPerLiterTask task = new FromKilometersPerLiterTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, Integer> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
                        case ConversionErrorCodes.ERROR_BELOW_ZERO:
                            mTextInputLayoutKpl.setError(getString(
                                    R.string.conversion_error_below_zero
                            ));
                            break;
                        case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                            mTextInputLayoutKpl.setError(getString(
                                    R.string.conversion_error_input_not_numeric
                            ));
                            break;
                        case ConversionErrorCodes.ERROR_UNKNOWN:
                            mTextInputLayoutKpl.setError(getString(
                                    R.string.conversion_error_conversion_error
                            ));
                            break;
                        default:
                            mTextInputLayoutUsmpg.setErrorEnabled(false);
                            mTextInputLayoutUkmpg.setErrorEnabled(false);
                            mTextInputLayoutKpl.setErrorEnabled(false);
                            mTextInputLayoutL100k.setErrorEnabled(false);
                            break;
                    }

                    mEditTextUsmpg.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextUkmpg.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextL100k.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromLitersPer100Kilometers(String l100k) {
        String[] params = new String[2];
        params[0] = l100k;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromLitersPer100KilometersTask task = new FromLitersPer100KilometersTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, Integer> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
                        case ConversionErrorCodes.ERROR_BELOW_ZERO:
                            mTextInputLayoutL100k.setError(getString(
                                    R.string.conversion_error_below_zero
                            ));
                            break;
                        case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                            mTextInputLayoutL100k.setError(getString(
                                    R.string.conversion_error_input_not_numeric
                            ));
                            break;
                        case ConversionErrorCodes.ERROR_UNKNOWN:
                            mTextInputLayoutL100k.setError(getString(
                                    R.string.conversion_error_conversion_error
                            ));
                            break;
                        default:
                            mTextInputLayoutUsmpg.setErrorEnabled(false);
                            mTextInputLayoutUkmpg.setErrorEnabled(false);
                            mTextInputLayoutKpl.setErrorEnabled(false);
                            mTextInputLayoutL100k.setErrorEnabled(false);
                            break;
                    }

                    mEditTextUsmpg.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextUkmpg.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextKpl.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
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
    protected ITemperaturePresenter getPresenter() { return null; }

    @Override
    protected void registerViewWithPresenter() {}

    // endregion
}
