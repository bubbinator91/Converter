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

    private SimpleTextWatcher mTextWatcherCmpss, mTextWatcherFpss, mTextWatcherMpss, mTextWatcherSg;

    private int mLastEditTextFocused;

    @Inject IAccelerationPresenter mAccelerationPresenter;

    @Bind(R.id.editText_acceleration_cmpss) AppCompatEditText mEditTextCmpss;
    @Bind(R.id.editText_acceleration_fpss)  AppCompatEditText mEditTextFpss;
    @Bind(R.id.editText_acceleration_mpss)  AppCompatEditText mEditTextMpss;
    @Bind(R.id.editText_acceleration_sg)    AppCompatEditText mEditTextSg;

    @Bind(R.id.textInputLayout_acceleration_cmpss)  TextInputLayout mTextInputLayoutCmpss;
    @Bind(R.id.textInputLayout_acceleration_fpss)   TextInputLayout mTextInputLayoutFpss;
    @Bind(R.id.textInputLayout_acceleration_mpss)   TextInputLayout mTextInputLayoutMpss;
    @Bind(R.id.textInputLayout_acceleration_sg)     TextInputLayout mTextInputLayoutSg;

    // region Lifecycle methods

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Timber.tag(TAG + ".onCreateView").i("Entered");

        if (getRootView() != null) {
            ButterKnife.bind(this, getRootView());

            mTextWatcherCmpss = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_CMPSS;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherCmpss");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherCmpss");
                        getPresenter().getConversionFromCentimetersPerSecondSquared(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherFpss = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_FPSS;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherFpss");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherFpss");
                        getPresenter().getConversionFromFeetPerSecondSquared(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherMpss = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_MPSS;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherMpss");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherMpss");
                        getPresenter().getConversionFromMetersPerSecondSquared(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherSg = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_SG;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherSg");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherSg");
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
                mEditTextCmpss.setText(getSharedPreferences().getString(CMPSS_VALUE_PERSIST_KEY, ""));
                mEditTextFpss.setText(getSharedPreferences().getString(FPSS_VALUE_PERSIST_KEY, ""));
                mEditTextMpss.setText(getSharedPreferences().getString(MPSS_VALUE_PERSIST_KEY, ""));
                mEditTextSg.setText(getSharedPreferences().getString(SG_VALUE_PERSIST_KEY, ""));
            }
            if (getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, -1) != -1) {
                mLastEditTextFocused = getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, 0);
            }
        }

        if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_CMPSS) {
            if (mEditTextCmpss.getText() != null) {
                Utils.sanitizeEditable(mEditTextCmpss.getText());
                getPresenter().getConversionFromCentimetersPerSecondSquared(
                        mEditTextCmpss.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_FPSS) {
            if (mEditTextFpss.getText() != null) {
                Utils.sanitizeEditable(mEditTextFpss.getText());
                getPresenter().getConversionFromFeetPerSecondSquared(
                        mEditTextFpss.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_MPSS) {
            if (mEditTextMpss.getText() != null) {
                Utils.sanitizeEditable(mEditTextMpss.getText());
                getPresenter().getConversionFromMetersPerSecondSquared(
                        mEditTextMpss.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_SG) {
            if (mEditTextSg.getText() != null) {
                Utils.sanitizeEditable(mEditTextSg.getText());
                getPresenter().getConversionFromStandardGravity(
                        mEditTextSg.getText().toString(),
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
                    .putString(CMPSS_VALUE_PERSIST_KEY, mEditTextCmpss.getText().toString())
                    .putString(FPSS_VALUE_PERSIST_KEY, mEditTextFpss.getText().toString())
                    .putString(MPSS_VALUE_PERSIST_KEY, mEditTextMpss.getText().toString())
                    .putString(SG_VALUE_PERSIST_KEY, mEditTextSg.getText().toString())
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

    // region Overridden IAccelerationView methods

    @Override
    public void displayConversionFromCentimetersPerSecondSquaredResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromCentimetersPerSecondSquaredResults").i("Entered");
        removeTextChangedListeners(".displayConversionFromCentimetersPerSecondSquaredResults");

        mTextInputLayoutCmpss.setErrorEnabled(false);
        mTextInputLayoutFpss.setErrorEnabled(false);
        mTextInputLayoutMpss.setErrorEnabled(false);
        mTextInputLayoutSg.setErrorEnabled(false);

        mEditTextFpss.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMpss.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextSg.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners(".displayConversionFromCentimetersPerSecondSquaredResults");
    }

    @Override
    public void displayConversionFromCentimetersPerSecondSquaredError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutCmpss.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutCmpss.setError(getString(R.string.conversion_error_below_zero));
        } else {
            mTextInputLayoutCmpss.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners(".displayConversionFromCentimetersPerSecondSquaredError");
        mEditTextFpss.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMpss.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextSg.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners(".displayConversionFromCentimetersPerSecondSquaredError");
    }

    @Override
    public void displayConversionFromFeetPerSecondSquaredResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromFeetPerSecondSquaredResults").i("Entered");
        removeTextChangedListeners(".displayConversionFromFeetPerSecondSquaredResults");

        mTextInputLayoutCmpss.setErrorEnabled(false);
        mTextInputLayoutFpss.setErrorEnabled(false);
        mTextInputLayoutMpss.setErrorEnabled(false);
        mTextInputLayoutSg.setErrorEnabled(false);

        mEditTextCmpss.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMpss.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextSg.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners(".displayConversionFromFeetPerSecondSquaredResults");
    }

    @Override
    public void displayConversionFromFeetPerSecondSquaredError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutFpss.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutFpss.setError(getString(R.string.conversion_error_below_zero));
        } else {
            mTextInputLayoutFpss.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners(".displayConversionFromFeetPerSecondSquaredError");
        mEditTextCmpss.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMpss.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextSg.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners(".displayConversionFromFeetPerSecondSquaredError");
    }

    @Override
    public void displayConversionFromMetersPerSecondSquaredResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromMetersPerSecondSquaredResults").i("Entered");
        removeTextChangedListeners(".displayConversionFromMetersPerSecondSquaredResults");

        mTextInputLayoutCmpss.setErrorEnabled(false);
        mTextInputLayoutFpss.setErrorEnabled(false);
        mTextInputLayoutMpss.setErrorEnabled(false);
        mTextInputLayoutSg.setErrorEnabled(false);

        mEditTextCmpss.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextFpss.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextSg.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners(".displayConversionFromMetersPerSecondSquaredResults");
    }

    @Override
    public void displayConversionFromMetersPerSecondSquaredError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutMpss.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutMpss.setError(getString(R.string.conversion_error_below_zero));
        } else {
            mTextInputLayoutMpss.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners(".displayConversionFromMetersPerSecondSquaredError");
        mEditTextCmpss.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextFpss.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextSg.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners(".displayConversionFromMetersPerSecondSquaredError");
    }

    @Override
    public void displayConversionFromStandardGravityResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromStandardGravityResults").i("Entered");
        removeTextChangedListeners(".displayConversionFromStandardGravityResults");

        mTextInputLayoutCmpss.setErrorEnabled(false);
        mTextInputLayoutFpss.setErrorEnabled(false);
        mTextInputLayoutMpss.setErrorEnabled(false);
        mTextInputLayoutSg.setErrorEnabled(false);

        mEditTextCmpss.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextFpss.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMpss.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners(".displayConversionFromStandardGravityResults");
    }

    @Override
    public void displayConversionFromStandardGravityError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutSg.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutSg.setError(getString(R.string.conversion_error_below_zero));
        } else {
            mTextInputLayoutSg.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners(".displayConversionFromStandardGravityError");
        mEditTextCmpss.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextFpss.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMpss.setText("", AppCompatTextView.BufferType.EDITABLE);
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

        mEditTextCmpss.addTextChangedListener(mTextWatcherCmpss);
        mEditTextFpss.addTextChangedListener(mTextWatcherFpss);
        mEditTextMpss.addTextChangedListener(mTextWatcherMpss);
        mEditTextSg.addTextChangedListener(mTextWatcherSg);
    }

    @Override
    public void removeTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".removeTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".removeTextChangedListeners").i("Entered");
        }

        mEditTextCmpss.removeTextChangedListener(mTextWatcherCmpss);
        mEditTextFpss.removeTextChangedListener(mTextWatcherFpss);
        mEditTextMpss.removeTextChangedListener(mTextWatcherMpss);
        mEditTextSg.removeTextChangedListener(mTextWatcherSg);
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
        if (mAccelerationPresenter == null) {
            DaggerFragmentInjectorComponent
                    .create()
                    .inject(this);
        }

        return mAccelerationPresenter;
    }

    @Override
    protected void registerViewWithPresenter() {
        getPresenter().registerView(this);
    }

    // endregion
}
