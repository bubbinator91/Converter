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
import com.bubbinator91.converter.interfaces.presenter.IAccelerationPresenter;
import com.bubbinator91.converter.interfaces.view.IAccelerationView;
import com.bubbinator91.converter.util.PresenterCache;
import com.bubbinator91.converter.util.SimpleTextWatcher;
import com.bubbinator91.converter.util.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Centimeters per Second Squared (cm/s^2), Feet per Second Squared (ft/s^2), Meters per Second
 * Squared (m/s^2), and Standard Gravity
 */
public class AccelerationFragment
        extends BaseFragment<IAccelerationPresenter>
        implements IAccelerationView {
    private static final String TAG = AccelerationFragment.class.getSimpleName();

    private static final int LAST_EDIT_TEXT_FOCUSED_CMPSS = 0;
    private static final int LAST_EDIT_TEXT_FOCUSED_FPSS = 1;
    private static final int LAST_EDIT_TEXT_FOCUSED_MPSS = 2;
    private static final int LAST_EDIT_TEXT_FOCUSED_SG = 3;

    private static final String CMPSS_VALUE_PERSIST_KEY = "ACCELERATION_FRAGMENT_CMPSS_VALUE";
    private static final String FPSS_VALUE_PERSIST_KEY = "ACCELERATION_FRAGMENT_FPSS_VALUE";
    private static final String MPSS_VALUE_PERSIST_KEY = "ACCELERATION_FRAGMENT_MPSS_VALUE";
    private static final String SG_VALUE_PERSIST_KEY = "ACCELERATION_FRAGMENT_SG_VALUE";
    private static final String LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY = "ACCELERATION_FRAGMENT_LETF_VALUE";

    private SimpleTextWatcher textWatcherCmpss, textWatcherFpss, textWatcherMpss, textWatcherSg;

    @Inject IAccelerationPresenter accelerationPresenter;

    @Bind(R.id.editText_acceleration_cmpss) AppCompatEditText editTextCmpss;
    @Bind(R.id.editText_acceleration_fpss)  AppCompatEditText editTextFpss;
    @Bind(R.id.editText_acceleration_mpss)  AppCompatEditText editTextMpss;
    @Bind(R.id.editText_acceleration_sg)    AppCompatEditText editTextSg;

    @Bind(R.id.textInputLayout_acceleration_cmpss)  TextInputLayout textInputLayoutCmpss;
    @Bind(R.id.textInputLayout_acceleration_fpss)   TextInputLayout textInputLayoutFpss;
    @Bind(R.id.textInputLayout_acceleration_mpss)   TextInputLayout textInputLayoutMpss;
    @Bind(R.id.textInputLayout_acceleration_sg)     TextInputLayout textInputLayoutSg;

    // region Lifecycle methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            accelerationPresenter = PresenterCache.getInstance().restorePresenter(savedInstanceState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Timber.tag(TAG + ".onCreateView").i("Entered");

        if (getRootView() != null) {
            ButterKnife.bind(this, getRootView());

            textWatcherCmpss = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_CMPSS);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherCmpss");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherCmpss");
                        getPresenter().getConversionFromCentimetersPerSecondSquared(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherFpss = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_FPSS);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherFpss");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherFpss");
                        getPresenter().getConversionFromFeetPerSecondSquared(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherMpss = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_MPSS);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherMpss");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherMpss");
                        getPresenter().getConversionFromMetersPerSecondSquared(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherSg = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_SG);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherSg");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherSg");
                        getPresenter().getConversionFromStandardGravity(
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
            if ((getSharedPreferences().getString(CMPSS_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(FPSS_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(MPSS_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(SG_VALUE_PERSIST_KEY, null) != null)) {
                editTextCmpss.setText(getSharedPreferences().getString(CMPSS_VALUE_PERSIST_KEY, ""));
                editTextFpss.setText(getSharedPreferences().getString(FPSS_VALUE_PERSIST_KEY, ""));
                editTextMpss.setText(getSharedPreferences().getString(MPSS_VALUE_PERSIST_KEY, ""));
                editTextSg.setText(getSharedPreferences().getString(SG_VALUE_PERSIST_KEY, ""));
            }
            if (getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, -1) != -1) {
                setLastEditTextFocused(getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, 0));
            }
        }

        if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_CMPSS) {
            if (editTextCmpss.getText() != null) {
                Utils.sanitizeEditable(editTextCmpss.getText());
                getPresenter().getConversionFromCentimetersPerSecondSquared(
                        editTextCmpss.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_FPSS) {
            if (editTextFpss.getText() != null) {
                Utils.sanitizeEditable(editTextFpss.getText());
                getPresenter().getConversionFromFeetPerSecondSquared(
                        editTextFpss.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_MPSS) {
            if (editTextMpss.getText() != null) {
                Utils.sanitizeEditable(editTextMpss.getText());
                getPresenter().getConversionFromMetersPerSecondSquared(
                        editTextMpss.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_SG) {
            if (editTextSg.getText() != null) {
                Utils.sanitizeEditable(editTextSg.getText());
                getPresenter().getConversionFromStandardGravity(
                        editTextSg.getText().toString(),
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
                    .putString(CMPSS_VALUE_PERSIST_KEY, editTextCmpss.getText().toString())
                    .putString(FPSS_VALUE_PERSIST_KEY, editTextFpss.getText().toString())
                    .putString(MPSS_VALUE_PERSIST_KEY, editTextMpss.getText().toString())
                    .putString(SG_VALUE_PERSIST_KEY, editTextSg.getText().toString())
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

    // region Overridden IAccelerationView methods

    @Override
    public void displayConversionFromCentimetersPerSecondSquaredResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromCentimetersPerSecondSquaredResults").i("Entered");
        removeTextChangedListeners(".displayConversionFromCentimetersPerSecondSquaredResults");

        textInputLayoutCmpss.setErrorEnabled(false);
        textInputLayoutFpss.setErrorEnabled(false);
        textInputLayoutMpss.setErrorEnabled(false);
        textInputLayoutSg.setErrorEnabled(false);

        editTextFpss.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextMpss.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextSg.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners(".displayConversionFromCentimetersPerSecondSquaredResults");
    }

    @Override
    public void displayConversionFromCentimetersPerSecondSquaredError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutCmpss.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutCmpss.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutCmpss.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners(".displayConversionFromCentimetersPerSecondSquaredError");
        editTextFpss.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMpss.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextSg.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners(".displayConversionFromCentimetersPerSecondSquaredError");
    }

    @Override
    public void displayConversionFromFeetPerSecondSquaredResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromFeetPerSecondSquaredResults").i("Entered");
        removeTextChangedListeners(".displayConversionFromFeetPerSecondSquaredResults");

        textInputLayoutCmpss.setErrorEnabled(false);
        textInputLayoutFpss.setErrorEnabled(false);
        textInputLayoutMpss.setErrorEnabled(false);
        textInputLayoutSg.setErrorEnabled(false);

        editTextCmpss.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextMpss.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextSg.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners(".displayConversionFromFeetPerSecondSquaredResults");
    }

    @Override
    public void displayConversionFromFeetPerSecondSquaredError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutFpss.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutFpss.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutFpss.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners(".displayConversionFromFeetPerSecondSquaredError");
        editTextCmpss.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMpss.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextSg.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners(".displayConversionFromFeetPerSecondSquaredError");
    }

    @Override
    public void displayConversionFromMetersPerSecondSquaredResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromMetersPerSecondSquaredResults").i("Entered");
        removeTextChangedListeners(".displayConversionFromMetersPerSecondSquaredResults");

        textInputLayoutCmpss.setErrorEnabled(false);
        textInputLayoutFpss.setErrorEnabled(false);
        textInputLayoutMpss.setErrorEnabled(false);
        textInputLayoutSg.setErrorEnabled(false);

        editTextCmpss.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextFpss.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextSg.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners(".displayConversionFromMetersPerSecondSquaredResults");
    }

    @Override
    public void displayConversionFromMetersPerSecondSquaredError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutMpss.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutMpss.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutMpss.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners(".displayConversionFromMetersPerSecondSquaredError");
        editTextCmpss.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextFpss.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextSg.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners(".displayConversionFromMetersPerSecondSquaredError");
    }

    @Override
    public void displayConversionFromStandardGravityResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromStandardGravityResults").i("Entered");
        removeTextChangedListeners(".displayConversionFromStandardGravityResults");

        textInputLayoutCmpss.setErrorEnabled(false);
        textInputLayoutFpss.setErrorEnabled(false);
        textInputLayoutMpss.setErrorEnabled(false);
        textInputLayoutSg.setErrorEnabled(false);

        editTextCmpss.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextFpss.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextMpss.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners(".displayConversionFromStandardGravityResults");
    }

    @Override
    public void displayConversionFromStandardGravityError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutSg.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutSg.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutSg.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners(".displayConversionFromStandardGravityError");
        editTextCmpss.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextFpss.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMpss.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners(".displayConversionFromStandardGravityError");
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

        editTextCmpss.addTextChangedListener(textWatcherCmpss);
        editTextFpss.addTextChangedListener(textWatcherFpss);
        editTextMpss.addTextChangedListener(textWatcherMpss);
        editTextSg.addTextChangedListener(textWatcherSg);
    }

    @Override
    public void removeTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".removeTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".removeTextChangedListeners").i("Entered");
        }

        editTextCmpss.removeTextChangedListener(textWatcherCmpss);
        editTextFpss.removeTextChangedListener(textWatcherFpss);
        editTextMpss.removeTextChangedListener(textWatcherMpss);
        editTextSg.removeTextChangedListener(textWatcherSg);
    }

    // endregion

    // region Overridden BaseFragment methods

    @Override
    protected String getChildTag() {
        return TAG;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_acceleration;
    }

    @Override
    protected int getScrollViewResource() {
        return R.id.fragment_acceleration;
    }

    @Override
    protected IAccelerationPresenter getPresenter() {
        if (accelerationPresenter == null) {
            DaggerFragmentInjectorComponent
                    .create()
                    .inject(this);
        }

        return accelerationPresenter;
    }

    @Override
    protected void registerViewWithPresenter() {
        getPresenter().registerView(this);
    }

    // endregion
}
