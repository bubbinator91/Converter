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

import com.bubbinator91.conversion.acceleration.CentimetersPerSecondSquared;
import com.bubbinator91.conversion.acceleration.FeetPerSecondSquared;
import com.bubbinator91.conversion.acceleration.MetersPerSecondSquared;
import com.bubbinator91.conversion.acceleration.StandardGravity;
import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.conversion.util.Tuple;
import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.Utils;

import java.util.List;

import timber.log.Timber;

public class AccelerationFragment extends BaseFragment {
    private enum LastEditTextFocused {
        CMPSS,
        FPSS,
        MPSS,
        SG
    }
    private final String TAG = "FragmentAcceleration";

    private AppCompatEditText mEditTextCmpss, mEditTextFpss, mEditTextMpss, mEditTextSg;

    private TextInputLayout mTextInputLayoutCmpss, mTextInputLayoutFpss, mTextInputLayoutMpss,
            mTextInputLayoutSg;

    private LastEditTextFocused mLastEditTextFocused;

    // region TextWatchers

    private TextWatcher mTextWatcherCmpss = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.CMPSS;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherCmpss");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherCmpss");
                new Thread(new ConversionFromCmpssRunnable(s, "mTextWatcherCmpss")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherFpss = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.FPSS;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherFpss");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherFpss");
                new Thread(new ConversionFromFpssRunnable(s, "mTextWatcherFpss")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherMpss = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.MPSS;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherMpss");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherMpss");
                new Thread(new ConversionFromMpssRunnable(s, "mTextWatcherMpss")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherSg = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.SG;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherSg");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherSg");
                new Thread(new ConversionFromSgRunnable(s, "mTextWatcherSg")).start();
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
            mTextInputLayoutCmpss =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_acceleration_cmpss));
            mTextInputLayoutFpss =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_acceleration_fpss));
            mTextInputLayoutMpss =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_acceleration_mpss));
            mTextInputLayoutSg =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_acceleration_sg));

            mEditTextCmpss =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_acceleration_cmpss));
            mEditTextFpss =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_acceleration_fpss));
            mEditTextMpss =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_acceleration_mpss));
            mEditTextSg =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_acceleration_sg));

            addTextChangedListeners(null);
        }

        return getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.tag(TAG + ".onResume").i("Entered");

        if (mLastEditTextFocused == LastEditTextFocused.CMPSS) {
            if ((getHandler() != null) && (mEditTextCmpss.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextCmpss.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromCmpssRunnable(mEditTextCmpss.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.FPSS) {
            if ((getHandler() != null) && (mEditTextFpss.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextFpss.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromFpssRunnable(mEditTextFpss.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.MPSS) {
            if ((getHandler() != null) && (mEditTextMpss.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextMpss.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMpssRunnable(mEditTextMpss.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.SG) {
            if ((getHandler() != null) && (mEditTextSg.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextSg.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromSgRunnable(mEditTextSg.getText(),
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

    // endregion

    // region Overridden BaseFragment methods

    @Override
    protected String getChildTag() { return TAG; }

    @Override
    protected int getLayoutResource() { return R.layout.fragment_acceleration; }

    @Override
    protected int getScrollViewResource() { return R.id.fragment_acceleration; }

    // endregion

    // region Private classes

    private class ConversionFromCmpssRunnable implements Runnable {
        private final String TAG = ConversionFromCmpssRunnable.class.getSimpleName();

        private Editable mEditableCmpss;
        private String mCallingClassName;

        public ConversionFromCmpssRunnable(Editable editableCmpss, String callingClassName) {
            mEditableCmpss = editableCmpss;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableCmpss != null) {
                final Tuple<List<String>, ConversionErrorCodes> results =
                        CentimetersPerSecondSquared.toAll(mEditableCmpss.toString(),
                                getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutCmpss.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutCmpss.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
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

                            mEditTextFpss.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMpss.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextSg.setText(results.getValue0().get(2),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromFpssRunnable implements Runnable {
        private final String TAG = ConversionFromFpssRunnable.class.getSimpleName();

        private Editable mEditableFpss;
        private String mCallingClassName;

        public ConversionFromFpssRunnable(Editable editableFpss, String callingClassName) {
            mEditableFpss = editableFpss;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableFpss != null) {
                final Tuple<List<String>, ConversionErrorCodes> results =
                        FeetPerSecondSquared.toAll(mEditableFpss.toString(),
                                getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutFpss.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutFpss.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
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

                            mEditTextCmpss.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMpss.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextSg.setText(results.getValue0().get(2),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromMpssRunnable implements Runnable {
        private final String TAG = ConversionFromMpssRunnable.class.getSimpleName();

        private Editable mEditableMpss;
        private String mCallingClassName;

        public ConversionFromMpssRunnable(Editable editableMpss, String callingClassName) {
            mEditableMpss = editableMpss;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableMpss != null) {
                final Tuple<List<String>, ConversionErrorCodes> results =
                        MetersPerSecondSquared.toAll(mEditableMpss.toString(),
                                getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutMpss.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutMpss.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
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

                            mEditTextCmpss.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextFpss.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextSg.setText(results.getValue0().get(2),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromSgRunnable implements Runnable {
        private final String TAG = ConversionFromSgRunnable.class.getSimpleName();

        private Editable mEditableSg;
        private String mCallingClassName;

        public ConversionFromSgRunnable(Editable editableSg, String callingClassName) {
            mEditableSg = editableSg;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableSg != null) {
                final Tuple<List<String>, ConversionErrorCodes> results =
                        StandardGravity.toAll(mEditableSg.toString(),
                                getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutSg.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutSg.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
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

                            mEditTextCmpss.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextFpss.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMpss.setText(results.getValue0().get(2),
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
