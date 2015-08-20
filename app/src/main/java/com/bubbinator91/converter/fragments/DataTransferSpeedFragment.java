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
import com.bubbinator91.converter.tasks.datatransferspeed.FromBitsPerSecondTask;
import com.bubbinator91.converter.tasks.datatransferspeed.FromBytesPerSecondTask;
import com.bubbinator91.converter.tasks.datatransferspeed.FromGigabitsPerSecondTask;
import com.bubbinator91.converter.tasks.datatransferspeed.FromGigabytesPerSecondTask;
import com.bubbinator91.converter.tasks.datatransferspeed.FromKilobitsPerSecondTask;
import com.bubbinator91.converter.tasks.datatransferspeed.FromKilobytesPerSecondTask;
import com.bubbinator91.converter.tasks.datatransferspeed.FromMegabitsPerSecondTask;
import com.bubbinator91.converter.tasks.datatransferspeed.FromMegabytesPerSecondTask;
import com.bubbinator91.converter.tasks.datatransferspeed.FromTerabitsPerSecondTask;
import com.bubbinator91.converter.tasks.datatransferspeed.FromTerabytesPerSecondTask;
import com.bubbinator91.converter.util.Utils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
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

    @Bind(R.id.editText_data_transfer_speed_bps)    AppCompatEditText mEditTextBps;
    @Bind(R.id.editText_data_transfer_speed_byps)   AppCompatEditText mEditTextByps;
    @Bind(R.id.editText_data_transfer_speed_kbps)   AppCompatEditText mEditTextKbps;
    @Bind(R.id.editText_data_transfer_speed_kbyps)  AppCompatEditText mEditTextKbyps;
    @Bind(R.id.editText_data_transfer_speed_mbps)   AppCompatEditText mEditTextMbps;
    @Bind(R.id.editText_data_transfer_speed_mbyps)  AppCompatEditText mEditTextMbyps;
    @Bind(R.id.editText_data_transfer_speed_gbps)   AppCompatEditText mEditTextGbps;
    @Bind(R.id.editText_data_transfer_speed_gbyps)  AppCompatEditText mEditTextGbyps;
    @Bind(R.id.editText_data_transfer_speed_tbps)   AppCompatEditText mEditTextTbps;
    @Bind(R.id.editText_data_transfer_speed_tbyps)  AppCompatEditText mEditTextTbyps;

    @Bind(R.id.textInputLayout_data_transfer_speed_bps)     TextInputLayout mTextInputLayoutBps;
    @Bind(R.id.textInputLayout_data_transfer_speed_byps)    TextInputLayout mTextInputLayoutByps;
    @Bind(R.id.textInputLayout_data_transfer_speed_kbps)    TextInputLayout mTextInputLayoutKbps;
    @Bind(R.id.textInputLayout_data_transfer_speed_kbyps)   TextInputLayout mTextInputLayoutKbyps;
    @Bind(R.id.textInputLayout_data_transfer_speed_mbps)    TextInputLayout mTextInputLayoutMbps;
    @Bind(R.id.textInputLayout_data_transfer_speed_mbyps)   TextInputLayout mTextInputLayoutMbyps;
    @Bind(R.id.textInputLayout_data_transfer_speed_gbps)    TextInputLayout mTextInputLayoutGbps;
    @Bind(R.id.textInputLayout_data_transfer_speed_gbyps)   TextInputLayout mTextInputLayoutGbyps;
    @Bind(R.id.textInputLayout_data_transfer_speed_tbps)    TextInputLayout mTextInputLayoutTbps;
    @Bind(R.id.textInputLayout_data_transfer_speed_tbyps)   TextInputLayout mTextInputLayoutTbyps;

    private LastEditTextFocused mLastEditTextFocused;

    // region TextWatchers

    private TextWatcher mTextWatcherBps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.BPS;

            if (s != null) {
                removeTextChangedListeners("mTextWatcherBps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherBps");
                convertFromBitsPerSecond(s.toString());
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

            if (s != null) {
                removeTextChangedListeners("mTextWatcherByps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherByps");
                convertFromBytesPerSecond(s.toString());
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

            if (s != null) {
                removeTextChangedListeners("mTextWatcherKbps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherKbps");
                convertFromKilobitsPerSecond(s.toString());
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

            if (s != null) {
                removeTextChangedListeners("mTextWatcherKbyps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherKbyps");
                convertFromKilobytesPerSecond(s.toString());
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

            if (s != null) {
                removeTextChangedListeners("mTextWatcherMbps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherMbps");
                convertFromMegabitsPerSecond(s.toString());
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

            if (s != null) {
                removeTextChangedListeners("mTextWatcherMbyps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherMbyps");
                convertFromMegabytesPerSecond(s.toString());
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

            if (s != null) {
                removeTextChangedListeners("mTextWatcherGbps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherGbps");
                convertFromGigabitsPerSecond(s.toString());
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

            if (s != null) {
                removeTextChangedListeners("mTextWatcherGbyps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherGbyps");
                convertFromGigabytesPerSecond(s.toString());
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

            if (s != null) {
                removeTextChangedListeners("mTextWatcherTbps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherTbps");
                convertFromTerabitsPerSecond(s.toString());
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

            if (s != null) {
                removeTextChangedListeners("mTextWatcherTbyps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherTbyps");
                convertFromTerabytesPerSecond(s.toString());
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
            ButterKnife.bind(this, getRootView());
            addTextChangedListeners(null);
        }

        return getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.tag(TAG + ".onResume").i("Entered");

        if (mLastEditTextFocused == LastEditTextFocused.BPS) {
            if (mEditTextBps.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextBps.getText());
                addTextChangedListeners("onResume");
                convertFromBitsPerSecond(mEditTextBps.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.BYPS) {
            if (mEditTextByps.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextByps.getText());
                addTextChangedListeners("onResume");
                convertFromBytesPerSecond(mEditTextByps.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.KBPS) {
            if (mEditTextKbps.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextKbps.getText());
                addTextChangedListeners("onResume");
                convertFromKilobitsPerSecond(mEditTextKbps.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.KBYPS) {
            if (mEditTextKbyps.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextKbyps.getText());
                addTextChangedListeners("onResume");
                convertFromKilobytesPerSecond(mEditTextKbyps.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.MBPS) {
            if (mEditTextMbps.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextMbps.getText());
                addTextChangedListeners("onResume");
                convertFromMegabitsPerSecond(mEditTextMbps.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.MBYPS) {
            if (mEditTextMbyps.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextMbyps.getText());
                addTextChangedListeners("onResume");
                convertFromMegabytesPerSecond(mEditTextMbyps.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.GBPS) {
            if (mEditTextGbps.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextGbps.getText());
                addTextChangedListeners("onResume");
                convertFromGigabitsPerSecond(mEditTextGbps.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.GBYPS) {
            if (mEditTextGbyps.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextGbyps.getText());
                addTextChangedListeners("onResume");
                convertFromGigabytesPerSecond(mEditTextGbyps.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.TBPS) {
            if (mEditTextTbps.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextTbps.getText());
                addTextChangedListeners("onResume");
                convertFromTerabitsPerSecond(mEditTextTbps.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.TBYPS) {
            if (mEditTextTbyps.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextTbyps.getText());
                addTextChangedListeners("onResume");
                convertFromTerabytesPerSecond(mEditTextTbyps.getText().toString());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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

    private void convertFromBitsPerSecond(String bps) {
        String[] params = new String[2];
        params[0] = bps;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromBitsPerSecondTask task = new FromBitsPerSecondTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

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

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromBytesPerSecond(String byps) {
        String[] params = new String[2];
        params[0] = byps;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromBytesPerSecondTask task = new FromBytesPerSecondTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

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

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromKilobitsPerSecond(String kbps) {
        String[] params = new String[2];
        params[0] = kbps;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromKilobitsPerSecondTask task = new FromKilobitsPerSecondTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

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

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromKilobytesPerSecond(String kbyps) {
        String[] params = new String[2];
        params[0] = kbyps;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromKilobytesPerSecondTask task = new FromKilobytesPerSecondTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

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

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromMegabitsPerSecond(String mbps) {
        String[] params = new String[2];
        params[0] = mbps;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromMegabitsPerSecondTask task = new FromMegabitsPerSecondTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

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

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromMegabytesPerSecond(String mbyps) {
        String[] params = new String[2];
        params[0] = mbyps;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromMegabytesPerSecondTask task = new FromMegabytesPerSecondTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

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

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromGigabitsPerSecond(String gbps) {
        String[] params = new String[2];
        params[0] = gbps;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromGigabitsPerSecondTask task = new FromGigabitsPerSecondTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

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

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromGigabytesPerSecond(String gbyps) {
        String[] params = new String[2];
        params[0] = gbyps;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromGigabytesPerSecondTask task = new FromGigabytesPerSecondTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

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

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromTerabitsPerSecond(String tbps) {
        String[] params = new String[2];
        params[0] = tbps;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromTerabitsPerSecondTask task = new FromTerabitsPerSecondTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

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

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromTerabytesPerSecond(String tbyps) {
        String[] params = new String[2];
        params[0] = tbyps;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromTerabytesPerSecondTask task = new FromTerabytesPerSecondTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

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
    protected int getLayoutResource() { return R.layout.fragment_data_transfer_speed; }

    @Override
    protected int getScrollViewResource() { return  R.id.fragment_dts; }

    // endregion
}
