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

    private final String TAG = "FragmentSpeed";

    private AppCompatEditText mEditTextFps, mEditTextKnot, mEditTextKph, mEditTextMps, mEditTextMph;

    private TextInputLayout mTextInputLayoutFps, mTextInputLayoutKnot, mTextInputLayoutKph,
            mTextInputLayoutMps, mTextInputLayoutMph;

    private LastEditTextFocused mLastEditTextFocused;

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

    // region Lifecycle methods

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Timber.tag(TAG + ".onCreateView").i("Entered");

        if (getRootView() != null) {
            mTextInputLayoutFps =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_speed_fps));
            mTextInputLayoutKnot =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_speed_knot));
            mTextInputLayoutKph =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_speed_kph));
            mTextInputLayoutMps =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_speed_mps));
            mTextInputLayoutMph =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_speed_mph));

            mEditTextFps =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_speed_fps));
            mEditTextKnot =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_speed_knot));
            mEditTextKph =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_speed_kph));
            mEditTextMps =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_speed_mps));
            mEditTextMph =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_speed_mph));

            addTextChangedListeners(null);
        }

        return getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.tag(TAG + ".onResume").i("Entered");

        if (mLastEditTextFocused == LastEditTextFocused.FPS) {
            if (mEditTextFps.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextFps.getText());
                addTextChangedListeners("onResume");
                convertFromFeetPerSecond(mEditTextFps.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.KNOT) {
            if (mEditTextKnot.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextKnot.getText());
                addTextChangedListeners("onResume");
                convertFromKnots(mEditTextKnot.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.KPH) {
            if (mEditTextKph.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextKph.getText());
                addTextChangedListeners("onResume");
                convertFromKilometersPerHour(mEditTextKph.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.MPS) {
            if (mEditTextMps.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextMps.getText());
                addTextChangedListeners("onResume");
                convertFromMetersPerSecond(mEditTextMps.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.MPH) {
            if (mEditTextMph.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextMph.getText());
                addTextChangedListeners("onResume");
                convertFromMilesPerHour(mEditTextMph.getText().toString());
            }
        }
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