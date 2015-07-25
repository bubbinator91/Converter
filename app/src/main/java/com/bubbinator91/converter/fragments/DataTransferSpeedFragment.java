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

import com.bubbinator91.conversion.datatransferspeed.BitsPerSecond;
import com.bubbinator91.conversion.datatransferspeed.BytesPerSecond;
import com.bubbinator91.conversion.datatransferspeed.GigabitsPerSecond;
import com.bubbinator91.conversion.datatransferspeed.GigabytesPerSecond;
import com.bubbinator91.conversion.datatransferspeed.KilobitsPerSecond;
import com.bubbinator91.conversion.datatransferspeed.KilobytesPerSecond;
import com.bubbinator91.conversion.datatransferspeed.MegabitsPerSecond;
import com.bubbinator91.conversion.datatransferspeed.MegabytesPerSecond;
import com.bubbinator91.conversion.datatransferspeed.TerabitsPerSecond;
import com.bubbinator91.conversion.datatransferspeed.TerabytesPerSecond;
import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.Utils;

import java.util.List;

import timber.log.Timber;

public class DataTransferSpeedFragment extends BaseFragment {
    private enum LastEditTextFocused {
        BPS,
        BYPS,
        KBPS,
        KBYPS,
        MBPS,
        MBYPS,
        GBPS,
        GBYPS,
        TBPS,
        TBYPS
    }
    private final String TAG = "FragmentDataTransferSpeed";

    private AppCompatEditText mEditTextBps, mEditTextByps, mEditTextKbps, mEditTextKbyps,
            mEditTextMbps, mEditTextMbyps, mEditTextGbps, mEditTextGbyps, mEditTextTbps,
            mEditTextTbyps;

    private TextInputLayout mTextInputLayoutBps, mTextInputLayoutByps, mTextInputLayoutKbps,
            mTextInputLayoutKbyps, mTextInputLayoutMbps, mTextInputLayoutMbyps, mTextInputLayoutGbps,
            mTextInputLayoutGbyps, mTextInputLayoutTbps, mTextInputLayoutTbyps;

    private LastEditTextFocused mLastEditTextFocused;

    // region TextWatchers

    private TextWatcher mTextWatcherBps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.BPS;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherBps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherBps");
                new Thread(new ConversionFromBpsRunnable(s, "mTextWatcherBps")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherByps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.BYPS;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherByps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherByps");
                new Thread(new ConversionFromBypsRunnable(s, "mTextWatcherByps")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherKbps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.KBPS;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherKbps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherKbps");
                new Thread(new ConversionFromKbpsRunnable(s, "mTextWatcherKbps")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherKbyps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.KBYPS;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherKbyps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherKbyps");
                new Thread(new ConversionFromKbypsRunnable(s, "mTextWatcherKbyps")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherMbps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.MBPS;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherMbps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherMbps");
                new Thread(new ConversionFromMbpsRunnable(s, "mTextWatcherMbps")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherMbyps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.MBYPS;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherMbyps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherMbyps");
                new Thread(new ConversionFromMbypsRunnable(s, "mTextWatcherMbyps")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherGbps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.GBPS;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherGbps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherGbps");
                new Thread(new ConversionFromGbpsRunnable(s, "mTextWatcherGbps")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherGbyps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.GBYPS;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherGbyps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherGbyps");
                new Thread(new ConversionFromGbypsRunnable(s, "mTextWatcherGbyps")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherTbps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.TBPS;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherTbps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherTbps");
                new Thread(new ConversionFromTbpsRunnable(s, "mTextWatcherTbps")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherTbyps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.TBYPS;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherTbyps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherTbyps");
                new Thread(new ConversionFromTbypsRunnable(s, "mTextWatcherTbyps")).start();
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
            mTextInputLayoutBps =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_bps));
            mTextInputLayoutByps =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_byps));
            mTextInputLayoutKbps =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_kbps));
            mTextInputLayoutKbyps =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_kbyps));
            mTextInputLayoutMbps =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_mbps));
            mTextInputLayoutMbyps =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_mbyps));
            mTextInputLayoutGbps =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_gbps));
            mTextInputLayoutGbyps =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_gbyps));
            mTextInputLayoutTbps =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_tbps));
            mTextInputLayoutTbyps =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_tbyps));

            mEditTextBps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_bps));
            mEditTextByps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_byps));
            mEditTextKbps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_kbps));
            mEditTextKbyps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_kbyps));
            mEditTextMbps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_mbps));
            mEditTextMbyps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_mbyps));
            mEditTextGbps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_gbps));
            mEditTextGbyps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_gbyps));
            mEditTextTbps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_tbps));
            mEditTextTbyps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_tbyps));

            addTextChangedListeners(null);
        }

        return getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.tag(TAG + ".onResume").i("Entered");

        if (mLastEditTextFocused == LastEditTextFocused.BPS) {
            if ((getHandler() != null) && (mEditTextBps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextBps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromBpsRunnable(mEditTextBps.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.BYPS) {
            if ((getHandler() != null) && (mEditTextByps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextByps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromBypsRunnable(mEditTextByps.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.KBPS) {
            if ((getHandler() != null) && (mEditTextKbps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextKbps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromKbpsRunnable(mEditTextKbps.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.KBYPS) {
            if ((getHandler() != null) && (mEditTextKbyps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextKbyps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromKbypsRunnable(mEditTextKbyps.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.MBPS) {
            if ((getHandler() != null) && (mEditTextMbps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextMbps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMbpsRunnable(mEditTextMbps.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.MBYPS) {
            if ((getHandler() != null) && (mEditTextMbyps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextMbyps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMbypsRunnable(mEditTextMbyps.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.GBPS) {
            if ((getHandler() != null) && (mEditTextGbps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextGbps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromGbpsRunnable(mEditTextGbps.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.GBYPS) {
            if ((getHandler() != null) && (mEditTextGbyps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextGbyps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromGbypsRunnable(mEditTextGbyps.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.TBPS) {
            if ((getHandler() != null) && (mEditTextTbps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextTbps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromTbpsRunnable(mEditTextTbps.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.TBYPS) {
            if ((getHandler() != null) && (mEditTextTbyps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextTbyps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromTbypsRunnable(mEditTextTbyps.getText(),
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

        mEditTextBps.addTextChangedListener(mTextWatcherBps);
        mEditTextByps.addTextChangedListener(mTextWatcherByps);
        mEditTextKbps.addTextChangedListener(mTextWatcherKbps);
        mEditTextKbyps.addTextChangedListener(mTextWatcherKbyps);
        mEditTextMbps.addTextChangedListener(mTextWatcherMbps);
        mEditTextMbyps.addTextChangedListener(mTextWatcherMbyps);
        mEditTextGbps.addTextChangedListener(mTextWatcherGbps);
        mEditTextGbyps.addTextChangedListener(mTextWatcherGbyps);
        mEditTextTbps.addTextChangedListener(mTextWatcherTbps);
        mEditTextTbyps.addTextChangedListener(mTextWatcherTbyps);
    }

    private void removeTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".removeTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".removeTextChangedListeners").i("Entered");
        }

        mEditTextBps.removeTextChangedListener(mTextWatcherBps);
        mEditTextByps.removeTextChangedListener(mTextWatcherByps);
        mEditTextKbps.removeTextChangedListener(mTextWatcherKbps);
        mEditTextKbyps.removeTextChangedListener(mTextWatcherKbyps);
        mEditTextMbps.removeTextChangedListener(mTextWatcherMbps);
        mEditTextMbyps.removeTextChangedListener(mTextWatcherMbyps);
        mEditTextGbps.removeTextChangedListener(mTextWatcherGbps);
        mEditTextGbyps.removeTextChangedListener(mTextWatcherGbyps);
        mEditTextTbps.removeTextChangedListener(mTextWatcherTbps);
        mEditTextTbyps.removeTextChangedListener(mTextWatcherTbyps);
    }

    // endregion

    // region Overridden BaseFragment methods

    @Override
    protected String getChildTag() { return TAG; }

    @Override
    protected int getLayoutResource() { return R.layout.fragment_data_transfer_speed; }

    @Override
    protected int getScrollViewResource() { return  R.id.fragment_dts; }

    // endregion

    // region Private classes

    private class ConversionFromBpsRunnable implements Runnable {
        private final String TAG = ConversionFromBpsRunnable.class.getSimpleName();

        private Editable mEditableBps;
        private String mCallingClassName;

        public ConversionFromBpsRunnable(Editable editableBps, String callingClassName) {
            mEditableBps = editableBps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableBps != null) {
                final Pair<List<String>, ConversionErrorCodes> results =
                        BitsPerSecond.toAll(mEditableBps.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.second) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutBps.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutBps.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutBps.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutBps.setErrorEnabled(false);
                                    mTextInputLayoutByps.setErrorEnabled(false);
                                    mTextInputLayoutKbps.setErrorEnabled(false);
                                    mTextInputLayoutKbyps.setErrorEnabled(false);
                                    mTextInputLayoutMbps.setErrorEnabled(false);
                                    mTextInputLayoutMbyps.setErrorEnabled(false);
                                    mTextInputLayoutGbps.setErrorEnabled(false);
                                    mTextInputLayoutGbyps.setErrorEnabled(false);
                                    mTextInputLayoutTbps.setErrorEnabled(false);
                                    mTextInputLayoutTbyps.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextByps.setText(results.first.get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbps.setText(results.first.get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbyps.setText(results.first.get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbps.setText(results.first.get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbyps.setText(results.first.get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbps.setText(results.first.get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbyps.setText(results.first.get(6),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbps.setText(results.first.get(7),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbyps.setText(results.first.get(8),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromBypsRunnable implements Runnable {
        private final String TAG = ConversionFromBypsRunnable.class.getSimpleName();

        private Editable mEditableByps;
        private String mCallingClassName;

        public ConversionFromBypsRunnable(Editable editableByps, String callingClassName) {
            mEditableByps = editableByps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableByps != null) {
                final Pair<List<String>, ConversionErrorCodes> results =
                        BytesPerSecond.toAll(mEditableByps.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.second) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutByps.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutByps.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutByps.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutBps.setErrorEnabled(false);
                                    mTextInputLayoutByps.setErrorEnabled(false);
                                    mTextInputLayoutKbps.setErrorEnabled(false);
                                    mTextInputLayoutKbyps.setErrorEnabled(false);
                                    mTextInputLayoutMbps.setErrorEnabled(false);
                                    mTextInputLayoutMbyps.setErrorEnabled(false);
                                    mTextInputLayoutGbps.setErrorEnabled(false);
                                    mTextInputLayoutGbyps.setErrorEnabled(false);
                                    mTextInputLayoutTbps.setErrorEnabled(false);
                                    mTextInputLayoutTbyps.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextBps.setText(results.first.get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbps.setText(results.first.get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbyps.setText(results.first.get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbps.setText(results.first.get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbyps.setText(results.first.get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbps.setText(results.first.get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbyps.setText(results.first.get(6),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbps.setText(results.first.get(7),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbyps.setText(results.first.get(8),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromKbpsRunnable implements Runnable {
        private final String TAG = ConversionFromKbpsRunnable.class.getSimpleName();

        private Editable mEditableKbps;
        private String mCallingClassName;

        public ConversionFromKbpsRunnable(Editable editableKbps, String callingClassName) {
            mEditableKbps = editableKbps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableKbps != null) {
                final Pair<List<String>, ConversionErrorCodes> results =
                        KilobitsPerSecond.toAll(mEditableKbps.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.second) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutKbps.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutKbps.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutKbps.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutBps.setErrorEnabled(false);
                                    mTextInputLayoutByps.setErrorEnabled(false);
                                    mTextInputLayoutKbps.setErrorEnabled(false);
                                    mTextInputLayoutKbyps.setErrorEnabled(false);
                                    mTextInputLayoutMbps.setErrorEnabled(false);
                                    mTextInputLayoutMbyps.setErrorEnabled(false);
                                    mTextInputLayoutGbps.setErrorEnabled(false);
                                    mTextInputLayoutGbyps.setErrorEnabled(false);
                                    mTextInputLayoutTbps.setErrorEnabled(false);
                                    mTextInputLayoutTbyps.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextBps.setText(results.first.get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextByps.setText(results.first.get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbyps.setText(results.first.get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbps.setText(results.first.get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbyps.setText(results.first.get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbps.setText(results.first.get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbyps.setText(results.first.get(6),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbps.setText(results.first.get(7),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbyps.setText(results.first.get(8),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromKbypsRunnable implements Runnable {
        private final String TAG = ConversionFromKbypsRunnable.class.getSimpleName();

        private Editable mEditableKbyps;
        private String mCallingClassName;

        public ConversionFromKbypsRunnable(Editable editableKbyps, String callingClassName) {
            mEditableKbyps = editableKbyps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableKbyps != null) {
                final Pair<List<String>, ConversionErrorCodes> results =
                        KilobytesPerSecond.toAll(mEditableKbyps.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.second) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutKbyps.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutKbyps.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutKbyps.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutBps.setErrorEnabled(false);
                                    mTextInputLayoutByps.setErrorEnabled(false);
                                    mTextInputLayoutKbps.setErrorEnabled(false);
                                    mTextInputLayoutKbyps.setErrorEnabled(false);
                                    mTextInputLayoutMbps.setErrorEnabled(false);
                                    mTextInputLayoutMbyps.setErrorEnabled(false);
                                    mTextInputLayoutGbps.setErrorEnabled(false);
                                    mTextInputLayoutGbyps.setErrorEnabled(false);
                                    mTextInputLayoutTbps.setErrorEnabled(false);
                                    mTextInputLayoutTbyps.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextBps.setText(results.first.get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextByps.setText(results.first.get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbps.setText(results.first.get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbps.setText(results.first.get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbyps.setText(results.first.get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbps.setText(results.first.get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbyps.setText(results.first.get(6),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbps.setText(results.first.get(7),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbyps.setText(results.first.get(8),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromMbpsRunnable implements Runnable {
        private final String TAG = ConversionFromMbpsRunnable.class.getSimpleName();

        private Editable mEditableMbps;
        private String mCallingClassName;

        public ConversionFromMbpsRunnable(Editable editableMbps, String callingClassName) {
            mEditableMbps = editableMbps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableMbps != null) {
                final Pair<List<String>, ConversionErrorCodes> results =
                        MegabitsPerSecond.toAll(mEditableMbps.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.second) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutMbps.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutMbps.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutMbps.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutBps.setErrorEnabled(false);
                                    mTextInputLayoutByps.setErrorEnabled(false);
                                    mTextInputLayoutKbps.setErrorEnabled(false);
                                    mTextInputLayoutKbyps.setErrorEnabled(false);
                                    mTextInputLayoutMbps.setErrorEnabled(false);
                                    mTextInputLayoutMbyps.setErrorEnabled(false);
                                    mTextInputLayoutGbps.setErrorEnabled(false);
                                    mTextInputLayoutGbyps.setErrorEnabled(false);
                                    mTextInputLayoutTbps.setErrorEnabled(false);
                                    mTextInputLayoutTbyps.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextBps.setText(results.first.get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextByps.setText(results.first.get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbps.setText(results.first.get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbyps.setText(results.first.get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbyps.setText(results.first.get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbps.setText(results.first.get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbyps.setText(results.first.get(6),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbps.setText(results.first.get(7),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbyps.setText(results.first.get(8),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromMbypsRunnable implements Runnable {
        private final String TAG = ConversionFromMbypsRunnable.class.getSimpleName();

        private Editable mEditableMbyps;
        private String mCallingClassName;

        public ConversionFromMbypsRunnable(Editable editableMbyps, String callingClassName) {
            mEditableMbyps = editableMbyps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableMbyps != null) {
                final Pair<List<String>, ConversionErrorCodes> results =
                        MegabytesPerSecond.toAll(mEditableMbyps.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.second) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutMbyps.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutMbyps.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutMbyps.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutBps.setErrorEnabled(false);
                                    mTextInputLayoutByps.setErrorEnabled(false);
                                    mTextInputLayoutKbps.setErrorEnabled(false);
                                    mTextInputLayoutKbyps.setErrorEnabled(false);
                                    mTextInputLayoutMbps.setErrorEnabled(false);
                                    mTextInputLayoutMbyps.setErrorEnabled(false);
                                    mTextInputLayoutGbps.setErrorEnabled(false);
                                    mTextInputLayoutGbyps.setErrorEnabled(false);
                                    mTextInputLayoutTbps.setErrorEnabled(false);
                                    mTextInputLayoutTbyps.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextBps.setText(results.first.get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextByps.setText(results.first.get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbps.setText(results.first.get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbyps.setText(results.first.get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbps.setText(results.first.get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbps.setText(results.first.get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbyps.setText(results.first.get(6),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbps.setText(results.first.get(7),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbyps.setText(results.first.get(8),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromGbpsRunnable implements Runnable {
        private final String TAG = ConversionFromGbpsRunnable.class.getSimpleName();

        private Editable mEditableGbps;
        private String mCallingClassName;

        public ConversionFromGbpsRunnable(Editable editableGbps, String callingClassName) {
            mEditableGbps = editableGbps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableGbps != null) {
                final Pair<List<String>, ConversionErrorCodes> results =
                        GigabitsPerSecond.toAll(mEditableGbps.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.second) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutGbps.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutGbps.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutGbps.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutBps.setErrorEnabled(false);
                                    mTextInputLayoutByps.setErrorEnabled(false);
                                    mTextInputLayoutKbps.setErrorEnabled(false);
                                    mTextInputLayoutKbyps.setErrorEnabled(false);
                                    mTextInputLayoutMbps.setErrorEnabled(false);
                                    mTextInputLayoutMbyps.setErrorEnabled(false);
                                    mTextInputLayoutGbps.setErrorEnabled(false);
                                    mTextInputLayoutGbyps.setErrorEnabled(false);
                                    mTextInputLayoutTbps.setErrorEnabled(false);
                                    mTextInputLayoutTbyps.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextBps.setText(results.first.get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextByps.setText(results.first.get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbps.setText(results.first.get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbyps.setText(results.first.get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbps.setText(results.first.get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbyps.setText(results.first.get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbyps.setText(results.first.get(6),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbps.setText(results.first.get(7),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbyps.setText(results.first.get(8),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromGbypsRunnable implements Runnable {
        private final String TAG = ConversionFromGbypsRunnable.class.getSimpleName();

        private Editable mEditableGbyps;
        private String mCallingClassName;

        public ConversionFromGbypsRunnable(Editable editableGbyps, String callingClassName) {
            mEditableGbyps = editableGbyps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableGbyps != null) {
                final Pair<List<String>, ConversionErrorCodes> results =
                        GigabytesPerSecond.toAll(mEditableGbyps.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.second) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutGbyps.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutGbyps.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutGbyps.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutBps.setErrorEnabled(false);
                                    mTextInputLayoutByps.setErrorEnabled(false);
                                    mTextInputLayoutKbps.setErrorEnabled(false);
                                    mTextInputLayoutKbyps.setErrorEnabled(false);
                                    mTextInputLayoutMbps.setErrorEnabled(false);
                                    mTextInputLayoutMbyps.setErrorEnabled(false);
                                    mTextInputLayoutGbps.setErrorEnabled(false);
                                    mTextInputLayoutGbyps.setErrorEnabled(false);
                                    mTextInputLayoutTbps.setErrorEnabled(false);
                                    mTextInputLayoutTbyps.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextBps.setText(results.first.get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextByps.setText(results.first.get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbps.setText(results.first.get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbyps.setText(results.first.get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbps.setText(results.first.get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbyps.setText(results.first.get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbps.setText(results.first.get(6),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbps.setText(results.first.get(7),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbyps.setText(results.first.get(8),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromTbpsRunnable implements Runnable {
        private final String TAG = ConversionFromTbpsRunnable.class.getSimpleName();

        private Editable mEditableTbps;
        private String mCallingClassName;

        public ConversionFromTbpsRunnable(Editable editableTbps, String callingClassName) {
            mEditableTbps = editableTbps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableTbps != null) {
                final Pair<List<String>, ConversionErrorCodes> results =
                        TerabitsPerSecond.toAll(mEditableTbps.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.second) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutTbps.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutTbps.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutTbps.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutBps.setErrorEnabled(false);
                                    mTextInputLayoutByps.setErrorEnabled(false);
                                    mTextInputLayoutKbps.setErrorEnabled(false);
                                    mTextInputLayoutKbyps.setErrorEnabled(false);
                                    mTextInputLayoutMbps.setErrorEnabled(false);
                                    mTextInputLayoutMbyps.setErrorEnabled(false);
                                    mTextInputLayoutGbps.setErrorEnabled(false);
                                    mTextInputLayoutGbyps.setErrorEnabled(false);
                                    mTextInputLayoutTbps.setErrorEnabled(false);
                                    mTextInputLayoutTbyps.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextBps.setText(results.first.get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextByps.setText(results.first.get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbps.setText(results.first.get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbyps.setText(results.first.get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbps.setText(results.first.get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbyps.setText(results.first.get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbps.setText(results.first.get(6),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbyps.setText(results.first.get(7),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbyps.setText(results.first.get(8),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromTbypsRunnable implements Runnable {
        private final String TAG = ConversionFromTbypsRunnable.class.getSimpleName();

        private Editable mEditableTbyps;
        private String mCallingClassName;

        public ConversionFromTbypsRunnable(Editable editableTbyps, String callingClassName) {
            mEditableTbyps = editableTbyps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableTbyps != null) {
                final Pair<List<String>, ConversionErrorCodes> results =
                        TerabytesPerSecond.toAll(mEditableTbyps.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.second) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutTbyps.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutTbyps.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutTbyps.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutBps.setErrorEnabled(false);
                                    mTextInputLayoutByps.setErrorEnabled(false);
                                    mTextInputLayoutKbps.setErrorEnabled(false);
                                    mTextInputLayoutKbyps.setErrorEnabled(false);
                                    mTextInputLayoutMbps.setErrorEnabled(false);
                                    mTextInputLayoutMbyps.setErrorEnabled(false);
                                    mTextInputLayoutGbps.setErrorEnabled(false);
                                    mTextInputLayoutGbyps.setErrorEnabled(false);
                                    mTextInputLayoutTbps.setErrorEnabled(false);
                                    mTextInputLayoutTbyps.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextBps.setText(results.first.get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextByps.setText(results.first.get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbps.setText(results.first.get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbyps.setText(results.first.get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbps.setText(results.first.get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbyps.setText(results.first.get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbps.setText(results.first.get(6),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbyps.setText(results.first.get(7),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbps.setText(results.first.get(8),
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
