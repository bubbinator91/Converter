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

    private static final String BPS_VALUE_PERSIST_KEY = "DTS_FRAGMENT_BPS_VALUE";
    private static final String BYPS_VALUE_PERSIST_KEY = "DTS_FRAGMENT_BYPS_VALUE";
    private static final String KBPS_VALUE_PERSIST_KEY = "DTS_FRAGMENT_KBPS_VALUE";
    private static final String KBYPS_VALUE_PERSIST_KEY = "DTS_FRAGMENT_KBYPS_VALUE";
    private static final String MBPS_VALUE_PERSIST_KEY = "DTS_FRAGMENT_MBPS_VALUE";
    private static final String MBYPS_VALUE_PERSIST_KEY = "DTS_FRAGMENT_MBYPS_VALUE";
    private static final String GBPS_VALUE_PERSIST_KEY = "DTS_FRAGMENT_GBPS_VALUE";
    private static final String GBYPS_VALUE_PERSIST_KEY = "DTS_FRAGMENT_GBYPS_VALUE";
    private static final String TBPS_VALUE_PERSIST_KEY = "DTS_FRAGMENT_TBPS_VALUE";
    private static final String TBYPS_VALUE_PERSIST_KEY = "DTS_FRAGMENT_TBYPS_VALUE";

    private final String TAG = DataTransferSpeedFragment.class.getSimpleName();

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

    private boolean wasOnlyResumed = false;

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

    // region Constructor(s)

    public DataTransferSpeedFragment() { setArguments(new Bundle()); }

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
                if ((getSharedPreferences().getString(BPS_VALUE_PERSIST_KEY, null) != null)
                        && (getSharedPreferences().getString(BYPS_VALUE_PERSIST_KEY, null) != null)
                        && (getSharedPreferences().getString(KBPS_VALUE_PERSIST_KEY, null) != null)
                        && (getSharedPreferences().getString(KBYPS_VALUE_PERSIST_KEY, null) != null)
                        && (getSharedPreferences().getString(MBPS_VALUE_PERSIST_KEY, null) != null)
                        && (getSharedPreferences().getString(MBYPS_VALUE_PERSIST_KEY, null) != null)
                        && (getSharedPreferences().getString(GBPS_VALUE_PERSIST_KEY, null) != null)
                        && (getSharedPreferences().getString(GBYPS_VALUE_PERSIST_KEY, null) != null)
                        && (getSharedPreferences().getString(TBPS_VALUE_PERSIST_KEY, null) != null)
                        && (getSharedPreferences().getString(TBYPS_VALUE_PERSIST_KEY, null) != null)) {
                    mEditTextBps.setText(getSharedPreferences().getString(BPS_VALUE_PERSIST_KEY, ""));
                    mEditTextByps.setText(getSharedPreferences().getString(BYPS_VALUE_PERSIST_KEY, ""));
                    mEditTextKbps.setText(getSharedPreferences().getString(KBPS_VALUE_PERSIST_KEY, ""));
                    mEditTextKbyps.setText(getSharedPreferences().getString(KBYPS_VALUE_PERSIST_KEY, ""));
                    mEditTextMbps.setText(getSharedPreferences().getString(MBPS_VALUE_PERSIST_KEY, ""));
                    mEditTextMbyps.setText(getSharedPreferences().getString(MBYPS_VALUE_PERSIST_KEY, ""));
                    mEditTextGbps.setText(getSharedPreferences().getString(GBPS_VALUE_PERSIST_KEY, ""));
                    mEditTextGbyps.setText(getSharedPreferences().getString(GBYPS_VALUE_PERSIST_KEY, ""));
                    mEditTextTbps.setText(getSharedPreferences().getString(TBPS_VALUE_PERSIST_KEY, ""));
                    mEditTextTbyps.setText(getSharedPreferences().getString(TBYPS_VALUE_PERSIST_KEY, ""));
                }
            }

            if ((getArguments().getString(BPS_VALUE_PERSIST_KEY) != null)
                    && (getArguments().getString(BYPS_VALUE_PERSIST_KEY) != null)
                    && (getArguments().getString(KBPS_VALUE_PERSIST_KEY) != null)
                    && (getArguments().getString(KBYPS_VALUE_PERSIST_KEY) != null)
                    && (getArguments().getString(MBPS_VALUE_PERSIST_KEY) != null)
                    && (getArguments().getString(MBYPS_VALUE_PERSIST_KEY) != null)
                    && (getArguments().getString(GBPS_VALUE_PERSIST_KEY) != null)
                    && (getArguments().getString(GBYPS_VALUE_PERSIST_KEY) != null)
                    && (getArguments().getString(TBPS_VALUE_PERSIST_KEY) != null)
                    && (getArguments().getString(TBYPS_VALUE_PERSIST_KEY) != null)) {
                mEditTextBps.setText(getArguments().getString(BPS_VALUE_PERSIST_KEY));
                mEditTextByps.setText(getArguments().getString(BYPS_VALUE_PERSIST_KEY));
                mEditTextKbps.setText(getArguments().getString(KBPS_VALUE_PERSIST_KEY));
                mEditTextKbyps.setText(getArguments().getString(KBYPS_VALUE_PERSIST_KEY));
                mEditTextMbps.setText(getArguments().getString(MBPS_VALUE_PERSIST_KEY));
                mEditTextMbyps.setText(getArguments().getString(MBYPS_VALUE_PERSIST_KEY));
                mEditTextGbps.setText(getArguments().getString(GBPS_VALUE_PERSIST_KEY));
                mEditTextGbyps.setText(getArguments().getString(GBYPS_VALUE_PERSIST_KEY));
                mEditTextTbps.setText(getArguments().getString(TBPS_VALUE_PERSIST_KEY));
                mEditTextTbyps.setText(getArguments().getString(TBYPS_VALUE_PERSIST_KEY));
            }
        } else {
            if (mLastEditTextFocused == LastEditTextFocused.BPS) {
                if (mEditTextBps.getText() != null) {
                    Utils.sanitizeEditable(mEditTextBps.getText());
                    convertFromBitsPerSecond(mEditTextBps.getText().toString());
                }
            } else if (mLastEditTextFocused == LastEditTextFocused.BYPS) {
                if (mEditTextByps.getText() != null) {
                    Utils.sanitizeEditable(mEditTextByps.getText());
                    convertFromBytesPerSecond(mEditTextByps.getText().toString());
                }
            } else if (mLastEditTextFocused == LastEditTextFocused.KBPS) {
                if (mEditTextKbps.getText() != null) {
                    Utils.sanitizeEditable(mEditTextKbps.getText());
                    convertFromKilobitsPerSecond(mEditTextKbps.getText().toString());
                }
            } else if (mLastEditTextFocused == LastEditTextFocused.KBYPS) {
                if (mEditTextKbyps.getText() != null) {
                    Utils.sanitizeEditable(mEditTextKbyps.getText());
                    convertFromKilobytesPerSecond(mEditTextKbyps.getText().toString());
                }
            } else if (mLastEditTextFocused == LastEditTextFocused.MBPS) {
                if (mEditTextMbps.getText() != null) {
                    Utils.sanitizeEditable(mEditTextMbps.getText());
                    convertFromMegabitsPerSecond(mEditTextMbps.getText().toString());
                }
            } else if (mLastEditTextFocused == LastEditTextFocused.MBYPS) {
                if (mEditTextMbyps.getText() != null) {
                    Utils.sanitizeEditable(mEditTextMbyps.getText());
                    convertFromMegabytesPerSecond(mEditTextMbyps.getText().toString());
                }
            } else if (mLastEditTextFocused == LastEditTextFocused.GBPS) {
                if (mEditTextGbps.getText() != null) {
                    Utils.sanitizeEditable(mEditTextGbps.getText());
                    convertFromGigabitsPerSecond(mEditTextGbps.getText().toString());
                }
            } else if (mLastEditTextFocused == LastEditTextFocused.GBYPS) {
                if (mEditTextGbyps.getText() != null) {
                    Utils.sanitizeEditable(mEditTextGbyps.getText());
                    convertFromGigabytesPerSecond(mEditTextGbyps.getText().toString());
                }
            } else if (mLastEditTextFocused == LastEditTextFocused.TBPS) {
                if (mEditTextTbps.getText() != null) {
                    Utils.sanitizeEditable(mEditTextTbps.getText());
                    convertFromTerabitsPerSecond(mEditTextTbps.getText().toString());
                }
            } else if (mLastEditTextFocused == LastEditTextFocused.TBYPS) {
                if (mEditTextTbyps.getText() != null) {
                    Utils.sanitizeEditable(mEditTextTbyps.getText());
                    convertFromTerabytesPerSecond(mEditTextTbyps.getText().toString());
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

        if (!mEditTextBps.getText().toString().isEmpty()
                && !mEditTextByps.getText().toString().isEmpty()
                && !mEditTextKbps.getText().toString().isEmpty()
                && !mEditTextKbyps.getText().toString().isEmpty()
                && !mEditTextMbps.getText().toString().isEmpty()
                && !mEditTextMbyps.getText().toString().isEmpty()
                && !mEditTextGbps.getText().toString().isEmpty()
                && !mEditTextGbyps.getText().toString().isEmpty()
                && !mEditTextTbps.getText().toString().isEmpty()
                && !mEditTextTbyps.getText().toString().isEmpty()) {
            getArguments().putString(BPS_VALUE_PERSIST_KEY, mEditTextBps.getText().toString());
            getArguments().putString(BYPS_VALUE_PERSIST_KEY, mEditTextByps.getText().toString());
            getArguments().putString(KBPS_VALUE_PERSIST_KEY, mEditTextKbps.getText().toString());
            getArguments().putString(KBYPS_VALUE_PERSIST_KEY, mEditTextKbyps.getText().toString());
            getArguments().putString(MBPS_VALUE_PERSIST_KEY, mEditTextMbps.getText().toString());
            getArguments().putString(MBYPS_VALUE_PERSIST_KEY, mEditTextMbyps.getText().toString());
            getArguments().putString(GBPS_VALUE_PERSIST_KEY, mEditTextGbps.getText().toString());
            getArguments().putString(GBYPS_VALUE_PERSIST_KEY, mEditTextGbyps.getText().toString());
            getArguments().putString(TBPS_VALUE_PERSIST_KEY, mEditTextTbps.getText().toString());
            getArguments().putString(TBYPS_VALUE_PERSIST_KEY, mEditTextTbyps.getText().toString());
        }

        if (shouldPersistValues() && (getSharedPreferences() != null)) {
            getSharedPreferences()
                    .edit()
                    .putString(BPS_VALUE_PERSIST_KEY, mEditTextBps.getText().toString())
                    .putString(BYPS_VALUE_PERSIST_KEY, mEditTextByps.getText().toString())
                    .putString(KBPS_VALUE_PERSIST_KEY, mEditTextKbps.getText().toString())
                    .putString(KBYPS_VALUE_PERSIST_KEY, mEditTextKbyps.getText().toString())
                    .putString(MBPS_VALUE_PERSIST_KEY, mEditTextMbps.getText().toString())
                    .putString(MBYPS_VALUE_PERSIST_KEY, mEditTextMbyps.getText().toString())
                    .putString(GBPS_VALUE_PERSIST_KEY, mEditTextGbps.getText().toString())
                    .putString(GBYPS_VALUE_PERSIST_KEY, mEditTextGbyps.getText().toString())
                    .putString(TBPS_VALUE_PERSIST_KEY, mEditTextTbps.getText().toString())
                    .putString(TBYPS_VALUE_PERSIST_KEY, mEditTextTbyps.getText().toString())
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
