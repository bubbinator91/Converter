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
import com.bubbinator91.converter.util.PresenterCache;
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

    private SimpleTextWatcher textWatcherBps, textWatcherByps, textWatcherKbps, textWatcherKbyps,
            textWatcherMbps, textWatcherMbyps, textWatcherGbps, textWatcherGbyps, textWatcherTbps,
            textWatcherTbyps;
    
    @Inject IDataTransferSpeedPresenter dataTransferSpeedPresenter;

    @Bind(R.id.editText_data_transfer_speed_bps)    AppCompatEditText editTextBps;
    @Bind(R.id.editText_data_transfer_speed_byps)   AppCompatEditText editTextByps;
    @Bind(R.id.editText_data_transfer_speed_kbps)   AppCompatEditText editTextKbps;
    @Bind(R.id.editText_data_transfer_speed_kbyps)  AppCompatEditText editTextKbyps;
    @Bind(R.id.editText_data_transfer_speed_mbps)   AppCompatEditText editTextMbps;
    @Bind(R.id.editText_data_transfer_speed_mbyps)  AppCompatEditText editTextMbyps;
    @Bind(R.id.editText_data_transfer_speed_gbps)   AppCompatEditText editTextGbps;
    @Bind(R.id.editText_data_transfer_speed_gbyps)  AppCompatEditText editTextGbyps;
    @Bind(R.id.editText_data_transfer_speed_tbps)   AppCompatEditText editTextTbps;
    @Bind(R.id.editText_data_transfer_speed_tbyps)  AppCompatEditText editTextTbyps;

    @Bind(R.id.textInputLayout_data_transfer_speed_bps)     TextInputLayout textInputLayoutBps;
    @Bind(R.id.textInputLayout_data_transfer_speed_byps)    TextInputLayout textInputLayoutByps;
    @Bind(R.id.textInputLayout_data_transfer_speed_kbps)    TextInputLayout textInputLayoutKbps;
    @Bind(R.id.textInputLayout_data_transfer_speed_kbyps)   TextInputLayout textInputLayoutKbyps;
    @Bind(R.id.textInputLayout_data_transfer_speed_mbps)    TextInputLayout textInputLayoutMbps;
    @Bind(R.id.textInputLayout_data_transfer_speed_mbyps)   TextInputLayout textInputLayoutMbyps;
    @Bind(R.id.textInputLayout_data_transfer_speed_gbps)    TextInputLayout textInputLayoutGbps;
    @Bind(R.id.textInputLayout_data_transfer_speed_gbyps)   TextInputLayout textInputLayoutGbyps;
    @Bind(R.id.textInputLayout_data_transfer_speed_tbps)    TextInputLayout textInputLayoutTbps;
    @Bind(R.id.textInputLayout_data_transfer_speed_tbyps)   TextInputLayout textInputLayoutTbyps;

    // region Lifecycle methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            dataTransferSpeedPresenter = PresenterCache.getInstance().restorePresenter(savedInstanceState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Timber.tag(TAG + ".onCreateView").i("Entered");

        if (getRootView() != null) {
            ButterKnife.bind(this, getRootView());

            textWatcherBps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_BPS);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherBps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherBps");
                        getPresenter().getConversionFromBitsPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherByps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_BYPS);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherByps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherByps");
                        getPresenter().getConversionFromBytesPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherKbps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_KBPS);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherKbps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherKbps");
                        getPresenter().getConversionFromKilobitsPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherKbyps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_KBYPS);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherKbyps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherKbyps");
                        getPresenter().getConversionFromKilobytesPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherMbps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_MBPS);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherMbps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherMbps");
                        getPresenter().getConversionFromMegabitsPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherMbyps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_MBYPS);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherMbyps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherMbyps");
                        getPresenter().getConversionFromMegabytesPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherGbps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_GBPS);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherGbps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherGbps");
                        getPresenter().getConversionFromGigabitsPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherGbyps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_GBYPS);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherGbyps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherGbyps");
                        getPresenter().getConversionFromGigabytesPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherTbps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_TBPS);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherTbps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherTbps");
                        getPresenter().getConversionFromTerabitsPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherTbyps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_TBYPS);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherTbyps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherTbyps");
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
                editTextBps.setText(getSharedPreferences().getString(BPS_VALUE_PERSIST_KEY, ""));
                editTextByps.setText(getSharedPreferences().getString(BYPS_VALUE_PERSIST_KEY, ""));
                editTextKbps.setText(getSharedPreferences().getString(KBPS_VALUE_PERSIST_KEY, ""));
                editTextKbyps.setText(getSharedPreferences().getString(KBYPS_VALUE_PERSIST_KEY, ""));
                editTextMbps.setText(getSharedPreferences().getString(MBPS_VALUE_PERSIST_KEY, ""));
                editTextMbyps.setText(getSharedPreferences().getString(MBYPS_VALUE_PERSIST_KEY, ""));
                editTextGbps.setText(getSharedPreferences().getString(GBPS_VALUE_PERSIST_KEY, ""));
                editTextGbyps.setText(getSharedPreferences().getString(GBYPS_VALUE_PERSIST_KEY, ""));
                editTextTbps.setText(getSharedPreferences().getString(TBPS_VALUE_PERSIST_KEY, ""));
                editTextTbyps.setText(getSharedPreferences().getString(TBYPS_VALUE_PERSIST_KEY, ""));
            }
            if (getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, -1) != -1) {
                setLastEditTextFocused(getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, 0));
            }
        }

        if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_BPS) {
            if (editTextBps.getText() != null) {
                Utils.sanitizeEditable(editTextBps.getText());
                getPresenter().getConversionFromBitsPerSecond(
                        editTextBps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_BYPS) {
            if (editTextByps.getText() != null) {
                Utils.sanitizeEditable(editTextByps.getText());
                getPresenter().getConversionFromBytesPerSecond(
                        editTextByps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_KBPS) {
            if (editTextKbps.getText() != null) {
                Utils.sanitizeEditable(editTextKbps.getText());
                getPresenter().getConversionFromKilobitsPerSecond(
                        editTextKbps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_KBYPS) {
            if (editTextKbyps.getText() != null) {
                Utils.sanitizeEditable(editTextKbyps.getText());
                getPresenter().getConversionFromKilobytesPerSecond(
                        editTextKbyps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_MBPS) {
            if (editTextMbps.getText() != null) {
                Utils.sanitizeEditable(editTextMbps.getText());
                getPresenter().getConversionFromMegabitsPerSecond(
                        editTextMbps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_MBYPS) {
            if (editTextMbyps.getText() != null) {
                Utils.sanitizeEditable(editTextMbyps.getText());
                getPresenter().getConversionFromMegabytesPerSecond(
                        editTextMbyps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_GBPS) {
            if (editTextGbps.getText() != null) {
                Utils.sanitizeEditable(editTextGbps.getText());
                getPresenter().getConversionFromGigabitsPerSecond(
                        editTextGbps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_GBYPS) {
            if (editTextGbyps.getText() != null) {
                Utils.sanitizeEditable(editTextGbyps.getText());
                getPresenter().getConversionFromGigabytesPerSecond(
                        editTextGbyps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_TBPS) {
            if (editTextTbps.getText() != null) {
                Utils.sanitizeEditable(editTextTbps.getText());
                getPresenter().getConversionFromTerabitsPerSecond(
                        editTextTbps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_TBYPS) {
            if (editTextTbyps.getText() != null) {
                Utils.sanitizeEditable(editTextTbyps.getText());
                getPresenter().getConversionFromTerabytesPerSecond(
                        editTextTbyps.getText().toString(),
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
                    .putString(BPS_VALUE_PERSIST_KEY, editTextBps.getText().toString())
                    .putString(BYPS_VALUE_PERSIST_KEY, editTextByps.getText().toString())
                    .putString(KBPS_VALUE_PERSIST_KEY, editTextKbps.getText().toString())
                    .putString(KBYPS_VALUE_PERSIST_KEY, editTextKbyps.getText().toString())
                    .putString(MBPS_VALUE_PERSIST_KEY, editTextMbps.getText().toString())
                    .putString(MBYPS_VALUE_PERSIST_KEY, editTextMbyps.getText().toString())
                    .putString(GBPS_VALUE_PERSIST_KEY, editTextGbps.getText().toString())
                    .putString(GBYPS_VALUE_PERSIST_KEY, editTextGbyps.getText().toString())
                    .putString(TBPS_VALUE_PERSIST_KEY, editTextTbps.getText().toString())
                    .putString(TBYPS_VALUE_PERSIST_KEY, editTextTbyps.getText().toString())
                    .putInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, getLastEditTextFocused())
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

        textInputLayoutBps.setErrorEnabled(false);
        textInputLayoutByps.setErrorEnabled(false);
        textInputLayoutKbps.setErrorEnabled(false);
        textInputLayoutKbyps.setErrorEnabled(false);
        textInputLayoutMbps.setErrorEnabled(false);
        textInputLayoutMbyps.setErrorEnabled(false);
        textInputLayoutGbps.setErrorEnabled(false);
        textInputLayoutGbyps.setErrorEnabled(false);
        textInputLayoutTbps.setErrorEnabled(false);
        textInputLayoutTbyps.setErrorEnabled(false);

        editTextByps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextKbps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextKbyps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextMbps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        editTextMbyps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        editTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        editTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        editTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        editTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromBitsPerSecondResults");
    }

    @Override
    public void displayConversionFromBitsPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutBps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutBps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutBps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromBitsPerSecondError");
        editTextByps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextGbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextGbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextTbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextTbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromBitsPerSecondError");
    }

    @Override
    public void displayConversionFromBytesPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromBytesPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromBytesPerSecondResults");

        textInputLayoutBps.setErrorEnabled(false);
        textInputLayoutByps.setErrorEnabled(false);
        textInputLayoutKbps.setErrorEnabled(false);
        textInputLayoutKbyps.setErrorEnabled(false);
        textInputLayoutMbps.setErrorEnabled(false);
        textInputLayoutMbyps.setErrorEnabled(false);
        textInputLayoutGbps.setErrorEnabled(false);
        textInputLayoutGbyps.setErrorEnabled(false);
        textInputLayoutTbps.setErrorEnabled(false);
        textInputLayoutTbyps.setErrorEnabled(false);

        editTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextKbps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextKbyps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextMbps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        editTextMbyps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        editTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        editTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        editTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        editTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromBytesPerSecondResults");
    }

    @Override
    public void displayConversionFromBytesPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutByps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutByps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutByps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromBytesPerSecondError");
        editTextBps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextGbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextGbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextTbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextTbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromBytesPerSecondError");
    }

    @Override
    public void displayConversionFromKilobitsPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromKilobitsPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromKilobitsPerSecondResults");

        textInputLayoutBps.setErrorEnabled(false);
        textInputLayoutByps.setErrorEnabled(false);
        textInputLayoutKbps.setErrorEnabled(false);
        textInputLayoutKbyps.setErrorEnabled(false);
        textInputLayoutMbps.setErrorEnabled(false);
        textInputLayoutMbyps.setErrorEnabled(false);
        textInputLayoutGbps.setErrorEnabled(false);
        textInputLayoutGbyps.setErrorEnabled(false);
        textInputLayoutTbps.setErrorEnabled(false);
        textInputLayoutTbyps.setErrorEnabled(false);

        editTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextKbyps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextMbps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        editTextMbyps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        editTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        editTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        editTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        editTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromKilobitsPerSecondResults");
    }

    @Override
    public void displayConversionFromKilobitsPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutKbps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutKbps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutKbps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromKilobitsPerSecondError");
        editTextBps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextByps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextGbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextGbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextTbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextTbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromKilobitsPerSecondError");
    }

    @Override
    public void displayConversionFromKilobytesPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromKilobytesPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromKilobytesPerSecondResults");

        textInputLayoutBps.setErrorEnabled(false);
        textInputLayoutByps.setErrorEnabled(false);
        textInputLayoutKbps.setErrorEnabled(false);
        textInputLayoutKbyps.setErrorEnabled(false);
        textInputLayoutMbps.setErrorEnabled(false);
        textInputLayoutMbyps.setErrorEnabled(false);
        textInputLayoutGbps.setErrorEnabled(false);
        textInputLayoutGbyps.setErrorEnabled(false);
        textInputLayoutTbps.setErrorEnabled(false);
        textInputLayoutTbyps.setErrorEnabled(false);

        editTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextMbps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        editTextMbyps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        editTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        editTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        editTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        editTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromKilobytesPerSecondResults");
    }

    @Override
    public void displayConversionFromKilobytesPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutKbyps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutKbyps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutKbyps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromKilobytesPerSecondError");
        editTextBps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextByps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextGbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextGbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextTbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextTbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromKilobytesPerSecondError");
    }

    @Override
    public void displayConversionFromMegabitsPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromMegabitsPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromMegabitsPerSecondResults");

        textInputLayoutBps.setErrorEnabled(false);
        textInputLayoutByps.setErrorEnabled(false);
        textInputLayoutKbps.setErrorEnabled(false);
        textInputLayoutKbyps.setErrorEnabled(false);
        textInputLayoutMbps.setErrorEnabled(false);
        textInputLayoutMbyps.setErrorEnabled(false);
        textInputLayoutGbps.setErrorEnabled(false);
        textInputLayoutGbyps.setErrorEnabled(false);
        textInputLayoutTbps.setErrorEnabled(false);
        textInputLayoutTbyps.setErrorEnabled(false);

        editTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        editTextMbyps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        editTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        editTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        editTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        editTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromMegabitsPerSecondResults");
    }

    @Override
    public void displayConversionFromMegabitsPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutMbps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutMbps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutMbps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromMegabitsPerSecondError");
        editTextBps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextByps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextGbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextGbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextTbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextTbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromMegabitsPerSecondError");
    }

    @Override
    public void displayConversionFromMegabytesPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromMegabytesPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromMegabytesPerSecondResults");

        textInputLayoutBps.setErrorEnabled(false);
        textInputLayoutByps.setErrorEnabled(false);
        textInputLayoutKbps.setErrorEnabled(false);
        textInputLayoutKbyps.setErrorEnabled(false);
        textInputLayoutMbps.setErrorEnabled(false);
        textInputLayoutMbyps.setErrorEnabled(false);
        textInputLayoutGbps.setErrorEnabled(false);
        textInputLayoutGbyps.setErrorEnabled(false);
        textInputLayoutTbps.setErrorEnabled(false);
        textInputLayoutTbyps.setErrorEnabled(false);

        editTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        editTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        editTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        editTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        editTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        editTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromMegabytesPerSecondResults");
    }

    @Override
    public void displayConversionFromMegabytesPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutMbyps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutMbyps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutMbyps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromMegabytesPerSecondError");
        editTextBps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextByps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextGbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextGbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextTbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextTbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromMegabytesPerSecondError");
    }

    @Override
    public void displayConversionFromGigabitsPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromGigabitsPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromGigabitsPerSecondResults");

        textInputLayoutBps.setErrorEnabled(false);
        textInputLayoutByps.setErrorEnabled(false);
        textInputLayoutKbps.setErrorEnabled(false);
        textInputLayoutKbyps.setErrorEnabled(false);
        textInputLayoutMbps.setErrorEnabled(false);
        textInputLayoutMbyps.setErrorEnabled(false);
        textInputLayoutGbps.setErrorEnabled(false);
        textInputLayoutGbyps.setErrorEnabled(false);
        textInputLayoutTbps.setErrorEnabled(false);
        textInputLayoutTbyps.setErrorEnabled(false);

        editTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        editTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        editTextMbyps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        editTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        editTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        editTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromGigabitsPerSecondResults");
    }

    @Override
    public void displayConversionFromGigabitsPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutGbps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutGbps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutGbps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromGigabitsPerSecondError");
        editTextBps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextByps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextGbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextTbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextTbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromGigabitsPerSecondError");
    }

    @Override
    public void displayConversionFromGigabytesPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromGigabytesPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromGigabytesPerSecondResults");

        textInputLayoutBps.setErrorEnabled(false);
        textInputLayoutByps.setErrorEnabled(false);
        textInputLayoutKbps.setErrorEnabled(false);
        textInputLayoutKbyps.setErrorEnabled(false);
        textInputLayoutMbps.setErrorEnabled(false);
        textInputLayoutMbyps.setErrorEnabled(false);
        textInputLayoutGbps.setErrorEnabled(false);
        textInputLayoutGbyps.setErrorEnabled(false);
        textInputLayoutTbps.setErrorEnabled(false);
        textInputLayoutTbyps.setErrorEnabled(false);

        editTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        editTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        editTextMbyps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        editTextGbps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        editTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        editTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromGigabytesPerSecondResults");
    }

    @Override
    public void displayConversionFromGigabytesPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutGbyps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutGbyps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutGbyps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromGigabytesPerSecondError");
        editTextBps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextByps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextGbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextTbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextTbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromGigabytesPerSecondError");
    }

    @Override
    public void displayConversionFromTerabitsPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromTerabitsPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromTerabitsPerSecondResults");

        textInputLayoutBps.setErrorEnabled(false);
        textInputLayoutByps.setErrorEnabled(false);
        textInputLayoutKbps.setErrorEnabled(false);
        textInputLayoutKbyps.setErrorEnabled(false);
        textInputLayoutMbps.setErrorEnabled(false);
        textInputLayoutMbyps.setErrorEnabled(false);
        textInputLayoutGbps.setErrorEnabled(false);
        textInputLayoutGbyps.setErrorEnabled(false);
        textInputLayoutTbps.setErrorEnabled(false);
        textInputLayoutTbyps.setErrorEnabled(false);

        editTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        editTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        editTextMbyps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        editTextGbps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        editTextGbyps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        editTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromTerabitsPerSecondResults");
    }

    @Override
    public void displayConversionFromTerabitsPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutTbps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutTbps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutTbps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromTerabitsPerSecondError");
        editTextBps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextByps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextGbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextGbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextTbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromTerabitsPerSecondError");
    }

    @Override
    public void displayConversionFromTerabytesPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromTerabytesPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromTerabytesPerSecondResults");

        textInputLayoutBps.setErrorEnabled(false);
        textInputLayoutByps.setErrorEnabled(false);
        textInputLayoutKbps.setErrorEnabled(false);
        textInputLayoutKbyps.setErrorEnabled(false);
        textInputLayoutMbps.setErrorEnabled(false);
        textInputLayoutMbyps.setErrorEnabled(false);
        textInputLayoutGbps.setErrorEnabled(false);
        textInputLayoutGbyps.setErrorEnabled(false);
        textInputLayoutTbps.setErrorEnabled(false);
        textInputLayoutTbyps.setErrorEnabled(false);

        editTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        editTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        editTextMbyps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        editTextGbps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
        editTextGbyps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
        editTextTbps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromTerabytesPerSecondResults");
    }

    @Override
    public void displayConversionFromTerabytesPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutTbyps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutTbyps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutTbyps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromTerabytesPerSecondError");
        editTextBps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextByps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextGbps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextGbyps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextTbps.setText("", AppCompatTextView.BufferType.EDITABLE);
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

        editTextBps.addTextChangedListener(textWatcherBps);
        editTextByps.addTextChangedListener(textWatcherByps);
        editTextKbps.addTextChangedListener(textWatcherKbps);
        editTextKbyps.addTextChangedListener(textWatcherKbyps);
        editTextMbps.addTextChangedListener(textWatcherMbps);
        editTextMbyps.addTextChangedListener(textWatcherMbyps);
        editTextGbps.addTextChangedListener(textWatcherGbps);
        editTextGbyps.addTextChangedListener(textWatcherGbyps);
        editTextTbps.addTextChangedListener(textWatcherTbps);
        editTextTbyps.addTextChangedListener(textWatcherTbyps);
    }

    @Override
    public void removeTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".removeTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".removeTextChangedListeners").i("Entered");
        }

        editTextBps.removeTextChangedListener(textWatcherBps);
        editTextByps.removeTextChangedListener(textWatcherByps);
        editTextKbps.removeTextChangedListener(textWatcherKbps);
        editTextKbyps.removeTextChangedListener(textWatcherKbyps);
        editTextMbps.removeTextChangedListener(textWatcherMbps);
        editTextMbyps.removeTextChangedListener(textWatcherMbyps);
        editTextGbps.removeTextChangedListener(textWatcherGbps);
        editTextGbyps.removeTextChangedListener(textWatcherGbyps);
        editTextTbps.removeTextChangedListener(textWatcherTbps);
        editTextTbyps.removeTextChangedListener(textWatcherTbyps);
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
        if (dataTransferSpeedPresenter == null) {
            DaggerFragmentInjectorComponent
                    .create()
                    .inject(this);
        }

        return dataTransferSpeedPresenter;
    }

    @Override
    protected void registerViewWithPresenter() {
        getPresenter().registerView(this);
    }

    // endregion
}
