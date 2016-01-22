package com.bubbinator91.converter.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;
import com.bubbinator91.converter.dagger.components.DaggerFragmentInjectorComponent;
import com.bubbinator91.converter.interfaces.presenter.IDataTransferSpeedPresenter;
import com.bubbinator91.converter.interfaces.view.IDataTransferSpeedView;
import com.bubbinator91.converter.util.SimpleTextWatcher;
import com.bubbinator91.converter.util.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public class DataTransferSpeedFragment 
        extends BaseFragment<IDataTransferSpeedPresenter>
        implements IDataTransferSpeedView {
    private static final String TAG = DataTransferSpeedFragment.class.getSimpleName();

    private static final int LAST_EDIT_TEXT_FOCUSED_BPS = 0;
    private static final int LAST_EDIT_TEXT_FOCUSED_BYPS = 1;
    private static final int LAST_EDIT_TEXT_FOCUSED_KBPS = 2;
    private static final int LAST_EDIT_TEXT_FOCUSED_KBYPS = 3;
    private static final int LAST_EDIT_TEXT_FOCUSED_MBPS = 4;
    private static final int LAST_EDIT_TEXT_FOCUSED_MBYPS = 5;
    private static final int LAST_EDIT_TEXT_FOCUSED_GBPS = 6;
    private static final int LAST_EDIT_TEXT_FOCUSED_GBYPS = 7;
    private static final int LAST_EDIT_TEXT_FOCUSED_TBPS = 8;
    private static final int LAST_EDIT_TEXT_FOCUSED_TBYPS = 9;

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
    private static final String LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY = "DTS_FRAGMENT_LETF_VALUE";

    private SimpleTextWatcher mTextWatcherBps, mTextWatcherByps, mTextWatcherKbps,
            mTextWatcherKbyps, mTextWatcherMbps, mTextWatcherMbyps, mTextWatcherGbps,
            mTextWatcherGbyps, mTextWatcherTbps, mTextWatcherTbyps;

    private int mLastEditTextFocused;
    
    @Inject IDataTransferSpeedPresenter mDataTransferSpeedPresenter;

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

    // region Lifecycle methods

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Timber.tag(TAG + ".onCreateView").i("Entered");

        if (getRootView() != null) {
            ButterKnife.bind(this, getRootView());

            mTextWatcherBps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_BPS;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherBps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherBps");
                        getPresenter().getConversionFromBitsPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherByps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_BYPS;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherByps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherByps");
                        getPresenter().getConversionFromBytesPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherKbps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_KBPS;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherKbps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherKbps");
                        getPresenter().getConversionFromKilobitsPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherKbyps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_KBYPS;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherKbyps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherKbyps");
                        getPresenter().getConversionFromKilobytesPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherMbps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_MBPS;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherMbps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherMbps");
                        getPresenter().getConversionFromMegabitsPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherMbyps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_MBYPS;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherMbyps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherMbyps");
                        getPresenter().getConversionFromMegabytesPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherGbps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_GBPS;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherGbps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherGbps");
                        getPresenter().getConversionFromGigabitsPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherGbyps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_GBYPS;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherGbyps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherGbyps");
                        getPresenter().getConversionFromGigabytesPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherTbps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_TBPS;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherTbps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherTbps");
                        getPresenter().getConversionFromTerabitsPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherTbyps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_TBYPS;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherTbyps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherTbyps");
                        getPresenter().getConversionFromTerabytesPerSecond(
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
            if (getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, -1) != -1) {
                mLastEditTextFocused = getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, 0);
            }
        }

        if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_BPS) {
            if (mEditTextBps.getText() != null) {
                Utils.sanitizeEditable(mEditTextBps.getText());
                getPresenter().getConversionFromBitsPerSecond(
                        mEditTextBps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_BYPS) {
            if (mEditTextByps.getText() != null) {
                Utils.sanitizeEditable(mEditTextByps.getText());
                getPresenter().getConversionFromBytesPerSecond(
                        mEditTextByps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_KBPS) {
            if (mEditTextKbps.getText() != null) {
                Utils.sanitizeEditable(mEditTextKbps.getText());
                getPresenter().getConversionFromKilobitsPerSecond(
                        mEditTextKbps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_KBYPS) {
            if (mEditTextKbyps.getText() != null) {
                Utils.sanitizeEditable(mEditTextKbyps.getText());
                getPresenter().getConversionFromKilobytesPerSecond(
                        mEditTextKbyps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_MBPS) {
            if (mEditTextMbps.getText() != null) {
                Utils.sanitizeEditable(mEditTextMbps.getText());
                getPresenter().getConversionFromMegabitsPerSecond(
                        mEditTextMbps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_MBYPS) {
            if (mEditTextMbyps.getText() != null) {
                Utils.sanitizeEditable(mEditTextMbyps.getText());
                getPresenter().getConversionFromMegabytesPerSecond(
                        mEditTextMbyps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_GBPS) {
            if (mEditTextGbps.getText() != null) {
                Utils.sanitizeEditable(mEditTextGbps.getText());
                getPresenter().getConversionFromGigabitsPerSecond(
                        mEditTextGbps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_GBYPS) {
            if (mEditTextGbyps.getText() != null) {
                Utils.sanitizeEditable(mEditTextGbyps.getText());
                getPresenter().getConversionFromGigabytesPerSecond(
                        mEditTextGbyps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_TBPS) {
            if (mEditTextTbps.getText() != null) {
                Utils.sanitizeEditable(mEditTextTbps.getText());
                getPresenter().getConversionFromTerabitsPerSecond(
                        mEditTextTbps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_TBYPS) {
            if (mEditTextTbyps.getText() != null) {
                Utils.sanitizeEditable(mEditTextTbyps.getText());
                getPresenter().getConversionFromTerabytesPerSecond(
                        mEditTextTbyps.getText().toString(),
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

    // region Overridden IDataTransferSpeedView methods

    @Override
    public void displayConversionFromBitsPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromBitsPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromBitsPerSecondResults");

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

        mEditTextByps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbyps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbyps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromBitsPerSecondResults");
    }

    @Override
    public void displayConversionFromBitsPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutBps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutBps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            mTextInputLayoutBps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromBitsPerSecondError");
        mEditTextByps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromBitsPerSecondError");
    }

    @Override
    public void displayConversionFromBytesPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromBytesPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromBytesPerSecondResults");

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

        mEditTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbyps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbyps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromBytesPerSecondResults");
    }

    @Override
    public void displayConversionFromBytesPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutByps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutByps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            mTextInputLayoutByps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromBytesPerSecondError");
        mEditTextBps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromBytesPerSecondError");
    }

    @Override
    public void displayConversionFromKilobitsPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromKilobitsPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromKilobitsPerSecondResults");

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

        mEditTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbyps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbyps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromKilobitsPerSecondResults");
    }

    @Override
    public void displayConversionFromKilobitsPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutKbps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutKbps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            mTextInputLayoutKbps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromKilobitsPerSecondError");
        mEditTextBps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextByps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromKilobitsPerSecondError");
    }

    @Override
    public void displayConversionFromKilobytesPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromKilobytesPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromKilobytesPerSecondResults");

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

        mEditTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbyps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromKilobytesPerSecondResults");
    }

    @Override
    public void displayConversionFromKilobytesPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutKbyps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutKbyps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            mTextInputLayoutKbyps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromKilobytesPerSecondError");
        mEditTextBps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextByps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromKilobytesPerSecondError");
    }

    @Override
    public void displayConversionFromMegabitsPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromMegabitsPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromMegabitsPerSecondResults");

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

        mEditTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbyps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromMegabitsPerSecondResults");
    }

    @Override
    public void displayConversionFromMegabitsPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutMbps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutMbps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            mTextInputLayoutMbps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromMegabitsPerSecondError");
        mEditTextBps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextByps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromMegabitsPerSecondError");
    }

    @Override
    public void displayConversionFromMegabytesPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromMegabytesPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromMegabytesPerSecondResults");

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

        mEditTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromMegabytesPerSecondResults");
    }

    @Override
    public void displayConversionFromMegabytesPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutMbyps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutMbyps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            mTextInputLayoutMbyps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromMegabytesPerSecondError");
        mEditTextBps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextByps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromMegabytesPerSecondError");
    }

    @Override
    public void displayConversionFromGigabitsPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromGigabitsPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromGigabitsPerSecondResults");

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

        mEditTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbyps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromGigabitsPerSecondResults");
    }

    @Override
    public void displayConversionFromGigabitsPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutGbps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutGbps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            mTextInputLayoutGbps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromGigabitsPerSecondError");
        mEditTextBps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextByps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromGigabitsPerSecondError");
    }

    @Override
    public void displayConversionFromGigabytesPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromGigabytesPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromGigabytesPerSecondResults");

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

        mEditTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbyps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromGigabytesPerSecondResults");
    }

    @Override
    public void displayConversionFromGigabytesPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutGbyps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutGbyps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            mTextInputLayoutGbyps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromGigabytesPerSecondError");
        mEditTextBps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextByps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromGigabytesPerSecondError");
    }

    @Override
    public void displayConversionFromTerabitsPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromTerabitsPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromTerabitsPerSecondResults");

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

        mEditTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbyps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbyps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromTerabitsPerSecondResults");
    }

    @Override
    public void displayConversionFromTerabitsPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutTbps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutTbps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            mTextInputLayoutTbps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromTerabitsPerSecondError");
        mEditTextBps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextByps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromTerabitsPerSecondError");
    }

    @Override
    public void displayConversionFromTerabytesPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromTerabytesPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromTerabytesPerSecondResults");

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

        mEditTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbyps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbyps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromTerabytesPerSecondResults");
    }

    @Override
    public void displayConversionFromTerabytesPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutTbyps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutTbyps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            mTextInputLayoutTbyps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromTerabytesPerSecondError");
        mEditTextBps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextByps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextGbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextTbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromTerabytesPerSecondError");
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

    @Override
    public void removeTextChangedListeners(String callingClassName) {
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
    protected String getChildTag() {
        return TAG;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_data_transfer_speed;
    }

    @Override
    protected int getScrollViewResource() {
        return R.id.fragment_dts;
    }

    @Override
    protected IDataTransferSpeedPresenter getPresenter() {
        if (mDataTransferSpeedPresenter == null) {
            DaggerFragmentInjectorComponent
                    .create()
                    .inject(this);
        }

        return mDataTransferSpeedPresenter;
    }

    @Override
    protected void registerViewWithPresenter() {
        getPresenter().registerView(this);
    }

    // endregion
}
