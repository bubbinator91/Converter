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

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.conversion.datatransferspeed.Bps;
import com.bubbinator91.converter.conversion.datatransferspeed.Byps;
import com.bubbinator91.converter.conversion.datatransferspeed.GByps;
import com.bubbinator91.converter.conversion.datatransferspeed.Gbps;
import com.bubbinator91.converter.conversion.datatransferspeed.KByps;
import com.bubbinator91.converter.conversion.datatransferspeed.Kbps;
import com.bubbinator91.converter.conversion.datatransferspeed.MByps;
import com.bubbinator91.converter.conversion.datatransferspeed.Mbps;
import com.bubbinator91.converter.conversion.datatransferspeed.TByps;
import com.bubbinator91.converter.conversion.datatransferspeed.Tbps;
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

    private AppCompatEditText editTextBps, editTextByps, editTextKbps, editTextKbyps, editTextMbps,
            editTextMbyps, editTextGbps, editTextGbyps, editTextTbps, editTextTbyps;

    private LastEditTextFocused lastEditTextFocused;

    // region TextWatchers

    private TextWatcher textWatcherBps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.BPS;

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

    private TextWatcher textWatcherByps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.BYPS;

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

    private TextWatcher textWatcherKbps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.KBPS;

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

    private TextWatcher textWatcherKbyps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.KBYPS;

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

    private TextWatcher textWatcherMbps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.MBPS;

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

    private TextWatcher textWatcherMbyps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.MBYPS;

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

    private TextWatcher textWatcherGbps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.GBPS;

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

    private TextWatcher textWatcherGbyps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.GBYPS;

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

    private TextWatcher textWatcherTbps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.TBPS;

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

    private TextWatcher textWatcherTbyps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.TBYPS;

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
            AppCompatTextView textViewBps =
                    ((AppCompatTextView) getRootView()
                            .findViewById(R.id.textView_data_transfer_speed_bps));
            AppCompatTextView textViewByps =
                    ((AppCompatTextView) getRootView()
                            .findViewById(R.id.textView_data_transfer_speed_byps));
            AppCompatTextView textViewKbps =
                    ((AppCompatTextView) getRootView()
                            .findViewById(R.id.textView_data_transfer_speed_kbps));
            AppCompatTextView textViewKbyps =
                    ((AppCompatTextView) getRootView()
                            .findViewById(R.id.textView_data_transfer_speed_kbyps));
            AppCompatTextView textViewMbps =
                    ((AppCompatTextView) getRootView()
                            .findViewById(R.id.textView_data_transfer_speed_mbps));
            AppCompatTextView textViewMbyps =
                    ((AppCompatTextView) getRootView()
                            .findViewById(R.id.textView_data_transfer_speed_mbyps));
            AppCompatTextView textViewGbps =
                    ((AppCompatTextView) getRootView()
                            .findViewById(R.id.textView_data_transfer_speed_gbps));
            AppCompatTextView textViewGbyps =
                    ((AppCompatTextView) getRootView()
                            .findViewById(R.id.textView_data_transfer_speed_gbyps));
            AppCompatTextView textViewTbps =
                    ((AppCompatTextView) getRootView()
                            .findViewById(R.id.textView_data_transfer_speed_tbps));
            AppCompatTextView textViewTbyps =
                    ((AppCompatTextView) getRootView()
                            .findViewById(R.id.textView_data_transfer_speed_tbyps));

            if (getTypeFace() != null) {
                textViewBps.setTypeface(getTypeFace());
                textViewByps.setTypeface(getTypeFace());
                textViewKbps.setTypeface(getTypeFace());
                textViewKbyps.setTypeface(getTypeFace());
                textViewMbps.setTypeface(getTypeFace());
                textViewMbyps.setTypeface(getTypeFace());
                textViewGbps.setTypeface(getTypeFace());
                textViewGbyps.setTypeface(getTypeFace());
                textViewTbps.setTypeface(getTypeFace());
                textViewTbyps.setTypeface(getTypeFace());
            }

            editTextBps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_bps));
            editTextByps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_byps));
            editTextKbps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_kbps));
            editTextKbyps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_kbyps));
            editTextMbps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_mbps));
            editTextMbyps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_mbyps));
            editTextGbps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_gbps));
            editTextGbyps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_gbyps));
            editTextTbps =
                    ((AppCompatEditText) getRootView()
                            .findViewById(R.id.editText_data_transfer_speed_tbps));
            editTextTbyps =
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

        if (lastEditTextFocused == LastEditTextFocused.BPS) {
            if ((getHandler() != null) && (editTextBps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextBps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromBpsRunnable(editTextBps.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.BYPS) {
            if ((getHandler() != null) && (editTextByps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextByps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromBypsRunnable(editTextByps.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.KBPS) {
            if ((getHandler() != null) && (editTextKbps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextKbps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromKbpsRunnable(editTextKbps.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.KBYPS) {
            if ((getHandler() != null) && (editTextKbyps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextKbyps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromKbypsRunnable(editTextKbyps.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.MBPS) {
            if ((getHandler() != null) && (editTextMbps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextMbps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMbpsRunnable(editTextMbps.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.MBYPS) {
            if ((getHandler() != null) && (editTextMbyps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextMbyps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMbypsRunnable(editTextMbyps.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.GBPS) {
            if ((getHandler() != null) && (editTextGbps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextGbps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromGbpsRunnable(editTextGbps.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.GBYPS) {
            if ((getHandler() != null) && (editTextGbyps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextGbyps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromGbypsRunnable(editTextGbyps.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.TBPS) {
            if ((getHandler() != null) && (editTextTbps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextTbps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromTbpsRunnable(editTextTbps.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.TBYPS) {
            if ((getHandler() != null) && (editTextTbyps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextTbyps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromTbypsRunnable(editTextTbyps.getText(),
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

    private void removeTextChangedListeners(String callingClassName) {
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

        public ConversionFromBpsRunnable(@NonNull Editable editableBps,
                                         @NonNull String callingClassName) {
            mEditableBps = editableBps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableBps != null) {
                final List<String> results =
                        Bps.toAll(mEditableBps.toString(), getNumOfDecimalPlaces());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        editTextByps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
                        editTextKbps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
                        editTextKbyps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
                        editTextMbps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
                        editTextMbyps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
                        editTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
                        editTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
                        editTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
                        editTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromBypsRunnable implements Runnable {
        private final String TAG = "ConversionFromBypsRunnable";

        private Editable mEditableByps;
        private String mCallingClassName;

        public ConversionFromBypsRunnable(@NonNull Editable editableByps,
                                          @NonNull String callingClassName) {
            mEditableByps = editableByps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableByps != null) {
                final List<String> results =
                        Byps.toAll(mEditableByps.toString(), getNumOfDecimalPlaces());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        editTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
                        editTextKbps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
                        editTextKbyps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
                        editTextMbps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
                        editTextMbyps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
                        editTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
                        editTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
                        editTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
                        editTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
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
                        editTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
                        editTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
                        editTextKbyps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
                        editTextMbps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
                        editTextMbyps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
                        editTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
                        editTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
                        editTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
                        editTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);
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
                        editTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
                        editTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
                        editTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
                        editTextMbps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
                        editTextMbyps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
                        editTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
                        editTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
                        editTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
                        editTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);
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
                        editTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
                        editTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
                        editTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
                        editTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
                        editTextMbyps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
                        editTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
                        editTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
                        editTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
                        editTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);
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
                        editTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
                        editTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
                        editTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
                        editTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
                        editTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
                        editTextGbps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
                        editTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
                        editTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
                        editTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);
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
                        editTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
                        editTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
                        editTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
                        editTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
                        editTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
                        editTextMbyps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
                        editTextGbyps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
                        editTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
                        editTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);
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
                        editTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
                        editTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
                        editTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
                        editTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
                        editTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
                        editTextMbyps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
                        editTextGbps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
                        editTextTbps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
                        editTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);
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
                        editTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
                        editTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
                        editTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
                        editTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
                        editTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
                        editTextMbyps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
                        editTextGbps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
                        editTextGbyps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
                        editTextTbyps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);
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
                        editTextBps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
                        editTextByps.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
                        editTextKbps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
                        editTextKbyps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
                        editTextMbps.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
                        editTextMbyps.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
                        editTextGbps.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);
                        editTextGbyps.setText(results.get(7), AppCompatTextView.BufferType.EDITABLE);
                        editTextTbps.setText(results.get(8), AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    // endregion
}
