package com.bubbinator91.converter.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.converter.R;
import com.bubbinator91.converter.tasks.speed.FromFeetPerSecondTask;
import com.bubbinator91.converter.tasks.speed.FromKilometersPerHourTask;
import com.bubbinator91.converter.tasks.speed.FromKnotsTask;
import com.bubbinator91.converter.tasks.speed.FromMetersPerSecondTask;
import com.bubbinator91.converter.tasks.speed.FromMilesPerHourTask;
import com.bubbinator91.converter.util.Utils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * mph, ft/s, m/s, kph, knot
 * Conversions comply with the conversions through Google.com
 */

public class SpeedFragment extends BaseFragment {
    private enum LastEditTextFocused {
        FPS,
        KNOT,
        KPH,
        MPS,
        MPH
    }

    private static final String FPS_VALUE_PERSIST_KEY = "SPEED_FRAGMENT_FPS_VALUE";
    private static final String KNOT_VALUE_PERSIST_KEY = "SPEED_FRAGMENT_KNOT_VALUE";
    private static final String KPH_VALUE_PERSIST_KEY = "SPEED_FRAGMENT_KPH_VALUE";
    private static final String MPS_VALUE_PERSIST_KEY = "SPEED_FRAGMENT_MPS_VALUE";
    private static final String MPH_VALUE_PERSIST_KEY = "SPEED_FRAGMENT_MPH_VALUE";

    private final String TAG = SpeedFragment.class.getSimpleName();

    @Bind(R.id.editText_speed_fps)  AppCompatEditText mEditTextFps;
    @Bind(R.id.editText_speed_knot) AppCompatEditText mEditTextKnot;
    @Bind(R.id.editText_speed_kph)  AppCompatEditText mEditTextKph;
    @Bind(R.id.editText_speed_mps)  AppCompatEditText mEditTextMps;
    @Bind(R.id.editText_speed_mph)  AppCompatEditText mEditTextMph;

    @Bind(R.id.textInputLayout_speed_fps)   TextInputLayout mTextInputLayoutFps;
    @Bind(R.id.textInputLayout_speed_knot)  TextInputLayout mTextInputLayoutKnot;
    @Bind(R.id.textInputLayout_speed_kph)   TextInputLayout mTextInputLayoutKph;
    @Bind(R.id.textInputLayout_speed_mps)   TextInputLayout mTextInputLayoutMps;
    @Bind(R.id.textInputLayout_speed_mph)   TextInputLayout mTextInputLayoutMph;

    private LastEditTextFocused mLastEditTextFocused;

    private boolean wasOnlyResumed = false;

    // region TextWatchers

    private TextWatcher mTextWatcherFps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.FPS;

            if (s != null) {
                removeTextChangedListeners("mTextWatcherFps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherFps");
                convertFromFeetPerSecond(s.toString());
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherKnot = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.KNOT;

            if (s != null) {
                removeTextChangedListeners("mTextWatcherKnot");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherKnot");
                convertFromKnots(s.toString());
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherKph = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.KPH;

            if (s != null) {
                removeTextChangedListeners("mTextWatcherKph");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherKph");
                convertFromKilometersPerHour(s.toString());
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherMps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.MPS;

            if (s != null) {
                removeTextChangedListeners("mTextWatcherMps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherMps");
                convertFromMetersPerSecond(s.toString());
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherMph = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.MPH;

            if (s != null) {
                removeTextChangedListeners("mTextWatcherMph");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherMph");
                convertFromMilesPerHour(s.toString());
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    // endregion

    // region Constructor(s)

    public SpeedFragment() { setArguments(new Bundle()); }

    // endregion

    // region Lifecycle methods

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Timber.tag(TAG + ".onCreateView").i("Entered");

        if (getRootView() != null) {
            ButterKnife.bind(this, getRootView());
        }

        wasOnlyResumed = false;
        return getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.tag(TAG + ".onResume").i("Entered");

        removeTextChangedListeners("onResume");

        if (!wasOnlyResumed) {
            if (shouldPersistValues() && (getSharedPreferences() != null)) {
                if ((getSharedPreferences().getString(FPS_VALUE_PERSIST_KEY, null) != null)
                        && (getSharedPreferences().getString(KNOT_VALUE_PERSIST_KEY, null) != null)
                        && (getSharedPreferences().getString(KPH_VALUE_PERSIST_KEY, null) != null)
                        && (getSharedPreferences().getString(MPS_VALUE_PERSIST_KEY, null) != null)
                        && (getSharedPreferences().getString(MPH_VALUE_PERSIST_KEY, null) != null)) {
                    mEditTextFps.setText(getSharedPreferences().getString(FPS_VALUE_PERSIST_KEY, ""));
                    mEditTextKnot.setText(getSharedPreferences().getString(KNOT_VALUE_PERSIST_KEY, ""));
                    mEditTextKph.setText(getSharedPreferences().getString(KPH_VALUE_PERSIST_KEY, ""));
                    mEditTextMps.setText(getSharedPreferences().getString(MPS_VALUE_PERSIST_KEY, ""));
                    mEditTextMph.setText(getSharedPreferences().getString(MPH_VALUE_PERSIST_KEY, ""));
                }
            }

            if ((getArguments().getString(FPS_VALUE_PERSIST_KEY) != null)
                    && (getArguments().getString(KNOT_VALUE_PERSIST_KEY) != null)
                    && (getArguments().getString(KPH_VALUE_PERSIST_KEY) != null)
                    && (getArguments().getString(MPS_VALUE_PERSIST_KEY) != null)
                    && (getArguments().getString(MPH_VALUE_PERSIST_KEY) != null)) {
                mEditTextFps.setText(getArguments().getString(FPS_VALUE_PERSIST_KEY));
                mEditTextKnot.setText(getArguments().getString(KNOT_VALUE_PERSIST_KEY));
                mEditTextKph.setText(getArguments().getString(KPH_VALUE_PERSIST_KEY));
                mEditTextMps.setText(getArguments().getString(MPS_VALUE_PERSIST_KEY));
                mEditTextMph.setText(getArguments().getString(MPH_VALUE_PERSIST_KEY));
            }
        } else {
            if (mLastEditTextFocused == LastEditTextFocused.FPS) {
                if (mEditTextFps.getText() != null) {
                    Utils.sanitizeEditable(mEditTextFps.getText());
                    convertFromFeetPerSecond(mEditTextFps.getText().toString());
                }
            } else if (mLastEditTextFocused == LastEditTextFocused.KNOT) {
                if (mEditTextKnot.getText() != null) {
                    Utils.sanitizeEditable(mEditTextKnot.getText());
                    convertFromKnots(mEditTextKnot.getText().toString());
                }
            } else if (mLastEditTextFocused == LastEditTextFocused.KPH) {
                if (mEditTextKph.getText() != null) {
                    Utils.sanitizeEditable(mEditTextKph.getText());
                    convertFromKilometersPerHour(mEditTextKph.getText().toString());
                }
            } else if (mLastEditTextFocused == LastEditTextFocused.MPS) {
                if (mEditTextMps.getText() != null) {
                    Utils.sanitizeEditable(mEditTextMps.getText());
                    convertFromMetersPerSecond(mEditTextMps.getText().toString());
                }
            } else if (mLastEditTextFocused == LastEditTextFocused.MPH) {
                if (mEditTextMph.getText() != null) {
                    Utils.sanitizeEditable(mEditTextMph.getText());
                    convertFromMilesPerHour(mEditTextMph.getText().toString());
                }
            }
        }

        wasOnlyResumed = true;
        addTextChangedListeners("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.tag(TAG + ".onPause").i("Entered");

        if (!mEditTextFps.getText().toString().isEmpty()
                && !mEditTextKnot.getText().toString().isEmpty()
                && !mEditTextKph.getText().toString().isEmpty()
                && !mEditTextMps.getText().toString().isEmpty()
                && !mEditTextMph.getText().toString().isEmpty()) {
            getArguments().putString(FPS_VALUE_PERSIST_KEY, mEditTextFps.getText().toString());
            getArguments().putString(KNOT_VALUE_PERSIST_KEY, mEditTextKnot.getText().toString());
            getArguments().putString(KPH_VALUE_PERSIST_KEY, mEditTextKph.getText().toString());
            getArguments().putString(MPS_VALUE_PERSIST_KEY, mEditTextMps.getText().toString());
            getArguments().putString(MPH_VALUE_PERSIST_KEY, mEditTextMph.getText().toString());
        }

        if (shouldPersistValues() && (getSharedPreferences() != null)) {
            getSharedPreferences()
                    .edit()
                    .putString(FPS_VALUE_PERSIST_KEY, mEditTextFps.getText().toString())
                    .putString(KNOT_VALUE_PERSIST_KEY, mEditTextKnot.getText().toString())
                    .putString(KPH_VALUE_PERSIST_KEY, mEditTextKph.getText().toString())
                    .putString(MPS_VALUE_PERSIST_KEY, mEditTextMps.getText().toString())
                    .putString(MPH_VALUE_PERSIST_KEY, mEditTextMph.getText().toString())
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

        mEditTextFps.addTextChangedListener(mTextWatcherFps);
        mEditTextKnot.addTextChangedListener(mTextWatcherKnot);
        mEditTextKph.addTextChangedListener(mTextWatcherKph);
        mEditTextMps.addTextChangedListener(mTextWatcherMps);
        mEditTextMph.addTextChangedListener(mTextWatcherMph);
    }

    private void removeTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".removeTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".removeTextChangedListeners").i("Entered");
        }

        mEditTextFps.removeTextChangedListener(mTextWatcherFps);
        mEditTextKnot.removeTextChangedListener(mTextWatcherKnot);
        mEditTextKph.removeTextChangedListener(mTextWatcherKph);
        mEditTextMps.removeTextChangedListener(mTextWatcherMps);
        mEditTextMph.removeTextChangedListener(mTextWatcherMph);
    }

    private void convertFromFeetPerSecond(String fps) {
        String[] params = new String[2];
        params[0] = fps;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromFeetPerSecondTask task = new FromFeetPerSecondTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
                        case ERROR_BELOW_ZERO:
                            mTextInputLayoutFps.setError(getString(
                                    R.string.conversion_error_below_zero
                            ));
                            break;
                        case ERROR_INPUT_NOT_NUMERIC:
                            mTextInputLayoutFps.setError(getString(
                                    R.string.conversion_error_input_not_numeric
                            ));
                            break;
                        case ERROR_UNKNOWN:
                            mTextInputLayoutFps.setError(getString(
                                    R.string.conversion_error_conversion_error
                            ));
                            break;
                        default:
                            mTextInputLayoutFps.setErrorEnabled(false);
                            mTextInputLayoutKnot.setErrorEnabled(false);
                            mTextInputLayoutKph.setErrorEnabled(false);
                            mTextInputLayoutMps.setErrorEnabled(false);
                            mTextInputLayoutMph.setErrorEnabled(false);
                            break;
                    }

                    mEditTextKnot.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextKph.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMps.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMph.setText(results.first.get(3),
                            AppCompatTextView.BufferType.EDITABLE);

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromKilometersPerHour(String kph) {
        String[] params = new String[2];
        params[0] = kph;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromKilometersPerHourTask task = new FromKilometersPerHourTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
                        case ERROR_BELOW_ZERO:
                            mTextInputLayoutKph.setError(getString(
                                    R.string.conversion_error_below_zero
                            ));
                            break;
                        case ERROR_INPUT_NOT_NUMERIC:
                            mTextInputLayoutKph.setError(getString(
                                    R.string.conversion_error_input_not_numeric
                            ));
                            break;
                        case ERROR_UNKNOWN:
                            mTextInputLayoutKph.setError(getString(
                                    R.string.conversion_error_conversion_error
                            ));
                            break;
                        default:
                            mTextInputLayoutFps.setErrorEnabled(false);
                            mTextInputLayoutKnot.setErrorEnabled(false);
                            mTextInputLayoutKph.setErrorEnabled(false);
                            mTextInputLayoutMps.setErrorEnabled(false);
                            mTextInputLayoutMph.setErrorEnabled(false);
                            break;
                    }

                    mEditTextFps.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextKnot.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMps.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMph.setText(results.first.get(3),
                            AppCompatTextView.BufferType.EDITABLE);

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromKnots(String knots) {
        String[] params = new String[2];
        params[0] = knots;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromKnotsTask task = new FromKnotsTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
                        case ERROR_BELOW_ZERO:
                            mTextInputLayoutKnot.setError(getString(
                                    R.string.conversion_error_below_zero
                            ));
                            break;
                        case ERROR_INPUT_NOT_NUMERIC:
                            mTextInputLayoutKnot.setError(getString(
                                    R.string.conversion_error_input_not_numeric
                            ));
                            break;
                        case ERROR_UNKNOWN:
                            mTextInputLayoutKnot.setError(getString(
                                    R.string.conversion_error_conversion_error
                            ));
                            break;
                        default:
                            mTextInputLayoutFps.setErrorEnabled(false);
                            mTextInputLayoutKnot.setErrorEnabled(false);
                            mTextInputLayoutKph.setErrorEnabled(false);
                            mTextInputLayoutMps.setErrorEnabled(false);
                            mTextInputLayoutMph.setErrorEnabled(false);
                            break;
                    }

                    mEditTextFps.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextKph.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMps.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMph.setText(results.first.get(3),
                            AppCompatTextView.BufferType.EDITABLE);

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromMetersPerSecond(String mps) {
        String[] params = new String[2];
        params[0] = mps;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromMetersPerSecondTask task = new FromMetersPerSecondTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
                        case ERROR_BELOW_ZERO:
                            mTextInputLayoutMps.setError(getString(
                                    R.string.conversion_error_below_zero
                            ));
                            break;
                        case ERROR_INPUT_NOT_NUMERIC:
                            mTextInputLayoutMps.setError(getString(
                                    R.string.conversion_error_input_not_numeric
                            ));
                            break;
                        case ERROR_UNKNOWN:
                            mTextInputLayoutMps.setError(getString(
                                    R.string.conversion_error_conversion_error
                            ));
                            break;
                        default:
                            mTextInputLayoutFps.setErrorEnabled(false);
                            mTextInputLayoutKnot.setErrorEnabled(false);
                            mTextInputLayoutKph.setErrorEnabled(false);
                            mTextInputLayoutMps.setErrorEnabled(false);
                            mTextInputLayoutMph.setErrorEnabled(false);
                            break;
                    }

                    mEditTextFps.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextKnot.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextKph.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMph.setText(results.first.get(3),
                            AppCompatTextView.BufferType.EDITABLE);

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromMilesPerHour(String mph) {
        String[] params = new String[2];
        params[0] = mph;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromMilesPerHourTask task = new FromMilesPerHourTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
                        case ERROR_BELOW_ZERO:
                            mTextInputLayoutMph.setError(getString(
                                    R.string.conversion_error_below_zero
                            ));
                            break;
                        case ERROR_INPUT_NOT_NUMERIC:
                            mTextInputLayoutMph.setError(getString(
                                    R.string.conversion_error_input_not_numeric
                            ));
                            break;
                        case ERROR_UNKNOWN:
                            mTextInputLayoutMph.setError(getString(
                                    R.string.conversion_error_conversion_error
                            ));
                            break;
                        default:
                            mTextInputLayoutFps.setErrorEnabled(false);
                            mTextInputLayoutKnot.setErrorEnabled(false);
                            mTextInputLayoutKph.setErrorEnabled(false);
                            mTextInputLayoutMps.setErrorEnabled(false);
                            mTextInputLayoutMph.setErrorEnabled(false);
                            break;
                    }

                    mEditTextFps.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextKnot.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextKph.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMps.setText(results.first.get(3),
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
    protected int getLayoutResource() { return R.layout.fragment_speed; }

    @Override
    protected int getScrollViewResource() { return  R.id.fragment_speed; }

    // endregion
}