package com.bubbinator91.converter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bubbinator91.conversion.datatransferspeed.BitsPerSecond;
import com.bubbinator91.conversion.datatransferspeed.BytesPerSecond;
import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.conversion.util.Tuple;
import com.bubbinator91.converter.R;
import com.bubbinator91.converter.conversion.datatransferspeed.GByps;
import com.bubbinator91.converter.conversion.datatransferspeed.Gbps;
import com.bubbinator91.converter.conversion.datatransferspeed.KByps;
import com.bubbinator91.converter.conversion.datatransferspeed.Kbps;
import com.bubbinator91.converter.conversion.datatransferspeed.MByps;
import com.bubbinator91.converter.conversion.datatransferspeed.Mbps;
import com.bubbinator91.converter.conversion.datatransferspeed.TByps;
import com.bubbinator91.converter.conversion.datatransferspeed.Tbps;
import com.bubbinator91.converter.util.TextInputLayoutLAndAbove;
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

    private TextInputLayoutLAndAbove mTextInputLayoutBps, mTextInputLayoutByps, mTextInputLayoutKbps,
            mTextInputLayoutKbyps, mTextInputLayoutMbps, mTextInputLayoutMbyps, mTextInputLayoutGbps,
            mTextInputLayoutGbyps, mTextInputLayoutTbps, mTextInputLayoutTbyps;

    private LastEditTextFocused mLastEditTextFocused;

    // region TextWatchers

    private TextWatcher mTextWatcherBps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.BPS;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("textWatcherBps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherBps");
                new Thread(new ConversionFromBpsRunnable(s, "textWatcherBps")).start();
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
                removeTextChangedListeners("textWatcherByps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherByps");
                new Thread(new ConversionFromBypsRunnable(s, "textWatcherByps")).start();
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
                removeTextChangedListeners("textWatcherKbps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherKbps");
                new Thread(new ConversionFromKbpsRunnable(s, "textWatcherKbps")).start();
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
                removeTextChangedListeners("textWatcherKbyps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherKbyps");
                new Thread(new ConversionFromKbypsRunnable(s, "textWatcherKbyps")).start();
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
                removeTextChangedListeners("textWatcherMbps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherMbps");
                new Thread(new ConversionFromMbpsRunnable(s, "textWatcherMbps")).start();
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
                removeTextChangedListeners("textWatcherMbyps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherMbyps");
                new Thread(new ConversionFromMbypsRunnable(s, "textWatcherMbyps")).start();
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
                removeTextChangedListeners("textWatcherGbps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherGbps");
                new Thread(new ConversionFromGbpsRunnable(s, "textWatcherGbps")).start();
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
                removeTextChangedListeners("textWatcherGbyps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherGbyps");
                new Thread(new ConversionFromGbypsRunnable(s, "textWatcherGbyps")).start();
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
                removeTextChangedListeners("textWatcherTbps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherTbps");
                new Thread(new ConversionFromTbpsRunnable(s, "textWatcherTbps")).start();
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
                removeTextChangedListeners("textWatcherTbyps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherTbyps");
                new Thread(new ConversionFromTbypsRunnable(s, "textWatcherTbyps")).start();
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
                    ((TextInputLayoutLAndAbove) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_bps));
            mTextInputLayoutByps =
                    ((TextInputLayoutLAndAbove) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_byps));
            mTextInputLayoutKbps =
                    ((TextInputLayoutLAndAbove) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_kbps));
            mTextInputLayoutKbyps =
                    ((TextInputLayoutLAndAbove) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_kbyps));
            mTextInputLayoutMbps =
                    ((TextInputLayoutLAndAbove) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_mbps));
            mTextInputLayoutMbyps =
                    ((TextInputLayoutLAndAbove) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_mbyps));
            mTextInputLayoutGbps =
                    ((TextInputLayoutLAndAbove) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_gbps));
            mTextInputLayoutGbyps =
                    ((TextInputLayoutLAndAbove) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_gbyps));
            mTextInputLayoutTbps =
                    ((TextInputLayoutLAndAbove) getRootView()
                            .findViewById(R.id.textInputLayout_data_transfer_speed_tbps));
            mTextInputLayoutTbyps =
                    ((TextInputLayoutLAndAbove) getRootView()
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
        private final String TAG = "ConversionFromBpsRunnable";

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
                final Tuple<List<String>, ConversionErrorCodes> results =
                        BitsPerSecond.toAll(mEditableBps.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
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

                            mEditTextByps.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbps.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbyps.setText(results.getValue0().get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbps.setText(results.getValue0().get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbyps.setText(results.getValue0().get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbps.setText(results.getValue0().get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbyps.setText(results.getValue0().get(6),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbps.setText(results.getValue0().get(7),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbyps.setText(results.getValue0().get(8),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromBypsRunnable implements Runnable {
        private final String TAG = "ConversionFromBypsRunnable";

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
                final Tuple<List<String>, ConversionErrorCodes> results =
                        BytesPerSecond.toAll(mEditableByps.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
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

                            mEditTextBps.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbps.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKbyps.setText(results.getValue0().get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbps.setText(results.getValue0().get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMbyps.setText(results.getValue0().get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbps.setText(results.getValue0().get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextGbyps.setText(results.getValue0().get(6),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbps.setText(results.getValue0().get(7),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextTbyps.setText(results.getValue0().get(8),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromKbpsRunnable implements Runnable {
        private final String TAG = "ConversionFromKbpsRunnable";

        private Editable mEditableKbps;
        private String mCallingClassName;

        public ConversionFromKbpsRunnable(@NonNull Editable editableKbps,
                                          @NonNull String callingClassName) {
            mEditableKbps = editableKbps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableKbps != null) {
                final List<String> results =
                        Kbps.toAll(mEditableKbps.toString(), getNumOfDecimalPlaces());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        mEditTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextKbyps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextMbps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextMbyps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromKbypsRunnable implements Runnable {
        private final String TAG = "ConversionFromKbypsRunnable";

        private Editable mEditableKbyps;
        private String mCallingClassName;

        public ConversionFromKbypsRunnable(@NonNull Editable editableKbyps,
                                           @NonNull String callingClassName) {
            mEditableKbyps = editableKbyps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableKbyps != null) {
                final List<String> results =
                        KByps.toAll(mEditableKbyps.toString(), getNumOfDecimalPlaces());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        mEditTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextMbps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextMbyps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromMbpsRunnable implements Runnable {
        private final String TAG = "ConversionFromMbpsRunnable";

        private Editable mEditableMbps;
        private String mCallingClassName;

        public ConversionFromMbpsRunnable(@NonNull Editable editableMbps,
                                          @NonNull String callingClassName) {
            mEditableMbps = editableMbps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableMbps != null) {
                final List<String> results =
                        Mbps.toAll(mEditableMbps.toString(), getNumOfDecimalPlaces());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        mEditTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextMbyps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromMbypsRunnable implements Runnable {
        private final String TAG = "ConversionFromMbypsRunnable";

        private Editable mEditableMbyps;
        private String mCallingClassName;

        public ConversionFromMbypsRunnable(@NonNull Editable editableMbyps,
                                           @NonNull String callingClassName) {
            mEditableMbyps = editableMbyps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableMbyps != null) {
                final List<String> results =
                        MByps.toAll(mEditableMbyps.toString(), getNumOfDecimalPlaces());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        mEditTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromGbpsRunnable implements Runnable {
        private final String TAG = "ConversionFromGbpsRunnable";

        private Editable mEditableGbps;
        private String mCallingClassName;

        public ConversionFromGbpsRunnable(@NonNull Editable editableGbps,
                                          @NonNull String callingClassName) {
            mEditableGbps = editableGbps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableGbps != null) {
                final List<String> results =
                        Gbps.toAll(mEditableGbps.toString(), getNumOfDecimalPlaces());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        mEditTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextMbyps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromGbypsRunnable implements Runnable {
        private final String TAG = "ConversionFromGbypsRunnable";

        private Editable mEditableGbyps;
        private String mCallingClassName;

        public ConversionFromGbypsRunnable(@NonNull Editable editableGbyps,
                                           @NonNull String callingClassName) {
            mEditableGbyps = editableGbyps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableGbyps != null) {
                final List<String> results =
                        GByps.toAll(mEditableGbyps.toString(), getNumOfDecimalPlaces());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        mEditTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextMbyps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextGbps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromTbpsRunnable implements Runnable {
        private final String TAG = "ConversionFromTbpsRunnable";

        private Editable mEditableTbps;
        private String mCallingClassName;

        public ConversionFromTbpsRunnable(@NonNull Editable editableTbps,
                                          @NonNull String callingClassName) {
            mEditableTbps = editableTbps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableTbps != null) {
                final List<String> results =
                        Tbps.toAll(mEditableTbps.toString(), getNumOfDecimalPlaces());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        mEditTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextMbyps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextGbps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextGbyps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromTbypsRunnable implements Runnable {
        private final String TAG = "ConversionFromTbypsRunnable";

        private Editable mEditableTbyps;
        private String mCallingClassName;

        public ConversionFromTbypsRunnable(@NonNull Editable editableTbyps,
                                           @NonNull String callingClassName) {
            mEditableTbyps = editableTbyps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableTbyps != null) {
                final List<String> results =
                        TByps.toAll(mEditableTbyps.toString(), getNumOfDecimalPlaces());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        mEditTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextMbyps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextGbps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextGbyps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
                        mEditTextTbps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    // endregion
}
