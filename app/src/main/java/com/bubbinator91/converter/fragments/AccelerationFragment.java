package com.bubbinator91.converter.fragments;

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
import com.bubbinator91.converter.tasks.acceleration.FromCentimetersPerSecondSquaredTask;
import com.bubbinator91.converter.tasks.acceleration.FromFeetPerSecondSquaredTask;
import com.bubbinator91.converter.tasks.acceleration.FromMetersPerSecondSquaredTask;
import com.bubbinator91.converter.tasks.acceleration.FromStandardGravityTask;
import com.bubbinator91.converter.util.SimpleTextWatcher;
import com.bubbinator91.converter.util.Utils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public class AccelerationFragment extends BaseFragment {
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

    @Bind(R.id.editText_acceleration_cmpss) AppCompatEditText mEditTextCmpss;
    @Bind(R.id.editText_acceleration_fpss)  AppCompatEditText mEditTextFpss;
    @Bind(R.id.editText_acceleration_mpss)  AppCompatEditText mEditTextMpss;
    @Bind(R.id.editText_acceleration_sg)    AppCompatEditText mEditTextSg;

    @Bind(R.id.textInputLayout_acceleration_cmpss)  TextInputLayout mTextInputLayoutCmpss;
    @Bind(R.id.textInputLayout_acceleration_fpss)   TextInputLayout mTextInputLayoutFpss;
    @Bind(R.id.textInputLayout_acceleration_mpss)   TextInputLayout mTextInputLayoutMpss;
    @Bind(R.id.textInputLayout_acceleration_sg)     TextInputLayout mTextInputLayoutSg;

    private SimpleTextWatcher mTextWatcherCmpss, mTextWatcherFpss, mTextWatcherMpss, mTextWatcherSg;

    private int mLastEditTextFocused;

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
                        convertFromCentimetersPerSecondSquared(s.toString());
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
                        convertFromFeetPerSecondSquared(s.toString());
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
                        convertFromMetersPerSecondSquared(s.toString());
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
                        convertFromStandardGravity(s.toString());
                    }
                }
            };
        }

        setWasOnCreateRan(true);
        return getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.tag(TAG + ".onResume").i("Entered");

        removeTextChangedListeners("onResume");

        if (wasOnCreateRan() && (getSharedPreferences() != null)) {
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
                convertFromCentimetersPerSecondSquared(mEditTextCmpss.getText().toString());
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_FPSS) {
            if (mEditTextFpss.getText() != null) {
                Utils.sanitizeEditable(mEditTextFpss.getText());
                convertFromFeetPerSecondSquared(mEditTextFpss.getText().toString());
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_MPSS) {
            if (mEditTextMpss.getText() != null) {
                Utils.sanitizeEditable(mEditTextMpss.getText());
                convertFromMetersPerSecondSquared(mEditTextMpss.getText().toString());
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_SG) {
            if (mEditTextSg.getText() != null) {
                Utils.sanitizeEditable(mEditTextSg.getText());
                convertFromStandardGravity(mEditTextSg.getText().toString());
            }
        } else {
            addTextChangedListeners("onResume");
        }

        setWasOnCreateRan(false);
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

    // region Helper methods

    private void addTextChangedListeners(String callingClassName) {
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

    private void removeTextChangedListeners(String callingClassName) {
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

    private void convertFromCentimetersPerSecondSquared(String cmpss) {
        String[] params = new String[2];
        params[0] = cmpss;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromCentimetersPerSecondSquaredTask task = new FromCentimetersPerSecondSquaredTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, Integer> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
                        case ConversionErrorCodes.ERROR_BELOW_ZERO:
                            mTextInputLayoutCmpss.setError(getString(
                                    R.string.conversion_error_below_zero
                            ));
                            break;
                        case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                            mTextInputLayoutCmpss.setError(getString(
                                    R.string.conversion_error_input_not_numeric
                            ));
                            break;
                        case ConversionErrorCodes.ERROR_UNKNOWN:
                            mTextInputLayoutCmpss.setError(getString(
                                    R.string.conversion_error_conversion_error
                            ));
                            break;
                        default:
                            mTextInputLayoutCmpss.setErrorEnabled(false);
                            mTextInputLayoutFpss.setErrorEnabled(false);
                            mTextInputLayoutMpss.setErrorEnabled(false);
                            mTextInputLayoutSg.setErrorEnabled(false);
                            break;
                    }

                    mEditTextFpss.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMpss.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextSg.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromFeetPerSecondSquared(String fpss) {
        String[] params = new String[2];
        params[0] = fpss;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromFeetPerSecondSquaredTask task = new FromFeetPerSecondSquaredTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, Integer> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
                        case ConversionErrorCodes.ERROR_BELOW_ZERO:
                            mTextInputLayoutFpss.setError(getString(
                                    R.string.conversion_error_below_zero
                            ));
                            break;
                        case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                            mTextInputLayoutFpss.setError(getString(
                                    R.string.conversion_error_input_not_numeric
                            ));
                            break;
                        case ConversionErrorCodes.ERROR_UNKNOWN:
                            mTextInputLayoutFpss.setError(getString(
                                    R.string.conversion_error_conversion_error
                            ));
                            break;
                        default:
                            mTextInputLayoutCmpss.setErrorEnabled(false);
                            mTextInputLayoutFpss.setErrorEnabled(false);
                            mTextInputLayoutMpss.setErrorEnabled(false);
                            mTextInputLayoutSg.setErrorEnabled(false);
                            break;
                    }

                    mEditTextCmpss.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMpss.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextSg.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromMetersPerSecondSquared(String mpss) {
        String[] params = new String[2];
        params[0] = mpss;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromMetersPerSecondSquaredTask task = new FromMetersPerSecondSquaredTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, Integer> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
                        case ConversionErrorCodes.ERROR_BELOW_ZERO:
                            mTextInputLayoutMpss.setError(getString(
                                    R.string.conversion_error_below_zero
                            ));
                            break;
                        case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                            mTextInputLayoutMpss.setError(getString(
                                    R.string.conversion_error_input_not_numeric
                            ));
                            break;
                        case ConversionErrorCodes.ERROR_UNKNOWN:
                            mTextInputLayoutMpss.setError(getString(
                                    R.string.conversion_error_conversion_error
                            ));
                            break;
                        default:
                            mTextInputLayoutCmpss.setErrorEnabled(false);
                            mTextInputLayoutFpss.setErrorEnabled(false);
                            mTextInputLayoutMpss.setErrorEnabled(false);
                            mTextInputLayoutSg.setErrorEnabled(false);
                            break;
                    }

                    mEditTextCmpss.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextFpss.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextSg.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromStandardGravity(String sg) {
        String[] params = new String[2];
        params[0] = sg;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromStandardGravityTask task = new FromStandardGravityTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, Integer> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
                        case ConversionErrorCodes.ERROR_BELOW_ZERO:
                            mTextInputLayoutSg.setError(getString(
                                    R.string.conversion_error_below_zero
                            ));
                            break;
                        case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                            mTextInputLayoutSg.setError(getString(
                                    R.string.conversion_error_input_not_numeric
                            ));
                            break;
                        case ConversionErrorCodes.ERROR_UNKNOWN:
                            mTextInputLayoutSg.setError(getString(
                                    R.string.conversion_error_conversion_error
                            ));
                            break;
                        default:
                            mTextInputLayoutCmpss.setErrorEnabled(false);
                            mTextInputLayoutFpss.setErrorEnabled(false);
                            mTextInputLayoutMpss.setErrorEnabled(false);
                            mTextInputLayoutSg.setErrorEnabled(false);
                            break;
                    }

                    mEditTextCmpss.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextFpss.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMpss.setText(results.first.get(2),
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
    protected int getLayoutResource() { return R.layout.fragment_acceleration; }

    @Override
    protected int getScrollViewResource() { return R.id.fragment_acceleration; }

    // endregion
}
