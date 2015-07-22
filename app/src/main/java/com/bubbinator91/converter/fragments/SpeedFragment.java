package com.bubbinator91.converter.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bubbinator91.conversion.speed.FeetPerSecond;
import com.bubbinator91.conversion.speed.KilometersPerHour;
import com.bubbinator91.conversion.speed.Knots;
import com.bubbinator91.conversion.speed.MetersPerSecond;
import com.bubbinator91.conversion.speed.MilesPerHour;
import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.conversion.util.Tuple;
import com.bubbinator91.converter.R;
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

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherFps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherFps");
                new Thread(new ConversionFromFpsRunnable(s, "mTextWatcherFps")).start();
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

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherKnot");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherKnot");
                new Thread(new ConversionFromKnotRunnable(s, "mTextWatcherKnot")).start();
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

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherKph");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherKph");
                new Thread(new ConversionFromKphRunnable(s, "mTextWatcherKph")).start();
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

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherMps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherMps");
                new Thread(new ConversionFromMpsRunnable(s, "mTextWatcherMps")).start();
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

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherMph");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherMph");
                new Thread(new ConversionFromMphRunnable(s, "mTextWatcherMph")).start();
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
            if ((getHandler() != null) && (mEditTextFps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextFps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromFpsRunnable(mEditTextFps.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.KNOT) {
            if ((getHandler() != null) && (mEditTextKnot.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextKnot.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromKnotRunnable(mEditTextKnot.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.KPH) {
            if ((getHandler() != null) && (mEditTextKph.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextKph.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromKphRunnable(mEditTextKph.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.MPS) {
            if ((getHandler() != null) && (mEditTextMps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextMps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMpsRunnable(mEditTextMps.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.MPH) {
            if ((getHandler() != null) && (mEditTextMph.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextMph.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMphRunnable(mEditTextMph.getText(),
                        "onResume")).start();
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

    // endregion

    // region Overridden BaseFragment methods

    @Override
    protected String getChildTag() { return TAG; }

    @Override
    protected int getLayoutResource() { return R.layout.fragment_speed; }

    @Override
    protected int getScrollViewResource() { return  R.id.fragment_speed; }

    // endregion

    // region Private classes

    private class ConversionFromFpsRunnable implements Runnable {
        private final String TAG = ConversionFromFpsRunnable.class.getSimpleName();

        private Editable mEditableFps;
        private String mCallingClassName;

        public ConversionFromFpsRunnable(Editable editableFps, String callingClassName) {
            mEditableFps = editableFps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableFps != null) {
                final Tuple<List<String>, ConversionErrorCodes> results =
                        FeetPerSecond.toAll(mEditableFps.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
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

                            mEditTextKnot.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKph.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMps.setText(results.getValue0().get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMph.setText(results.getValue0().get(3),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromKnotRunnable implements Runnable {
        private final String TAG = ConversionFromKnotRunnable.class.getSimpleName();

        private Editable mEditableKnot;
        private String mCallingClassName;

        public ConversionFromKnotRunnable(Editable editableKnot, String callingClassName) {
            mEditableKnot = editableKnot;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableKnot != null) {
                final Tuple<List<String>, ConversionErrorCodes> results =
                        Knots.toAll(mEditableKnot.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
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

                            mEditTextFps.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKph.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMps.setText(results.getValue0().get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMph.setText(results.getValue0().get(3),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromKphRunnable implements Runnable {
        private final String TAG = ConversionFromKphRunnable.class.getSimpleName();

        private Editable mEditableKph;
        private String mCallingClassName;

        public ConversionFromKphRunnable(Editable editableKph, String callingClassName) {
            mEditableKph = editableKph;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableKph != null) {
                final Tuple<List<String>, ConversionErrorCodes> results =
                        KilometersPerHour.toAll(mEditableKph.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
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

                            mEditTextFps.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKnot.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMps.setText(results.getValue0().get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMph.setText(results.getValue0().get(3),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromMpsRunnable implements Runnable {
        private final String TAG = ConversionFromMpsRunnable.class.getSimpleName();

        private Editable mEditableMps;
        private String mCallingClassName;

        public ConversionFromMpsRunnable(Editable editableMps, String callingClassName) {
            mEditableMps = editableMps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableMps != null) {
                final Tuple<List<String>, ConversionErrorCodes> results =
                        MetersPerSecond.toAll(mEditableMps.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
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

                            mEditTextFps.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKnot.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKph.setText(results.getValue0().get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMph.setText(results.getValue0().get(3),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromMphRunnable implements Runnable {
        private final String TAG = ConversionFromKphRunnable.class.getSimpleName();

        private Editable mEditableMph;
        private String mCallingClassName;

        public ConversionFromMphRunnable(Editable editableMph, String callingClassName) {
            mEditableMph = editableMph;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableMph != null) {
                final Tuple<List<String>, ConversionErrorCodes> results =
                        MilesPerHour.toAll(mEditableMph.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
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

                            mEditTextFps.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKnot.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKph.setText(results.getValue0().get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMps.setText(results.getValue0().get(3),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    // endregion
}