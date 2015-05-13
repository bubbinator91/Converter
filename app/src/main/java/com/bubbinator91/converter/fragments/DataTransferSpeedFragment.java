package com.bubbinator91.converter.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;

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

    private EditText editTextBps, editTextByps, editTextKbps, editTextKbyps, editTextMbps,
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
            Typeface tf = Typeface.createFromAsset(getCurrentActivity().getAssets(), "fonts/Roboto-Regular.ttf");

            TextView textViewBps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_bps));
            TextView textViewByps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_byps));
            TextView textViewKbps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_kbps));
            TextView textViewKbyps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_kbyps));
            TextView textViewMbps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_mbps));
            TextView textViewMbyps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_mbyps));
            TextView textViewGbps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_gbps));
            TextView textViewGbyps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_gbyps));
            TextView textViewTbps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_tbps));
            TextView textViewTbyps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_tbyps));

            textViewBps.setTypeface(tf);
            textViewByps.setTypeface(tf);
            textViewKbps.setTypeface(tf);
            textViewKbyps.setTypeface(tf);
            textViewMbps.setTypeface(tf);
            textViewMbyps.setTypeface(tf);
            textViewGbps.setTypeface(tf);
            textViewGbyps.setTypeface(tf);
            textViewTbps.setTypeface(tf);
            textViewTbyps.setTypeface(tf);

            editTextBps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_bps));
            editTextByps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_byps));
            editTextKbps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_kbps));
            editTextKbyps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_kbyps));
            editTextMbps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_mbps));
            editTextMbyps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_mbyps));
            editTextGbps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_gbps));
            editTextGbyps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_gbyps));
            editTextTbps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_tbps));
            editTextTbyps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_tbyps));

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
                new Thread(new ConversionFromBpsRunnable(editTextBps.getText(), "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.BYPS) {
            if ((getHandler() != null) && (editTextByps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextByps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromBypsRunnable(editTextByps.getText(), "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.KBPS) {
            if ((getHandler() != null) && (editTextKbps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextKbps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromKbpsRunnable(editTextKbps.getText(), "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.KBYPS) {
            if ((getHandler() != null) && (editTextKbyps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextKbyps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromKbypsRunnable(editTextKbyps.getText(), "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.MBPS) {
            if ((getHandler() != null) && (editTextMbps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextMbps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMbpsRunnable(editTextMbps.getText(), "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.MBYPS) {
            if ((getHandler() != null) && (editTextMbyps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextMbyps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMbypsRunnable(editTextMbyps.getText(), "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.GBPS) {
            if ((getHandler() != null) && (editTextGbps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextGbps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromGbpsRunnable(editTextGbps.getText(), "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.GBYPS) {
            if ((getHandler() != null) && (editTextGbyps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextGbyps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromGbypsRunnable(editTextGbyps.getText(), "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.TBPS) {
            if ((getHandler() != null) && (editTextTbps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextTbps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromTbpsRunnable(editTextTbps.getText(), "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.TBYPS) {
            if ((getHandler() != null) && (editTextTbyps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextTbyps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromTbypsRunnable(editTextTbyps.getText(), "onResume")).start();
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

        public ConversionFromBpsRunnable(Editable editableBps, String callingClassName) {
            mEditableBps = editableBps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableBps != null) {
                if (Utils.isNumeric(mEditableBps.toString())) {
                    try {
                        BigDecimal bps = new BigDecimal(mEditableBps.toString());
                        BigDecimal byps = bps.multiply(new BigDecimal("0.125"));
                        BigDecimal kbps = bps.multiply(new BigDecimal("0.001"));
                        BigDecimal kbyps = bps.multiply(new BigDecimal("0.000125"));
                        BigDecimal mbps = bps.multiply(new BigDecimal("0.000001"));
                        BigDecimal mbyps = bps.multiply(new BigDecimal("0.000000125"));
                        BigDecimal gbps = bps.multiply(new BigDecimal("0.000000001"));
                        BigDecimal gbyps = bps.multiply(new BigDecimal("0.000000000125"));
                        BigDecimal tbps = bps.multiply(new BigDecimal("0.000000000001"));
                        BigDecimal tbyps = bps.multiply(new BigDecimal("0.000000000000125"));

                        results.add(byps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(gbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(gbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(tbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(tbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 9);
                    }
                } else {
                    addWhitespaceItems(results, 9);
                }
            } else {
                addWhitespaceItems(results, 9);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextByps.setText(results.get(0)
                            , TextView.BufferType.EDITABLE);
                    editTextKbps.setText(results.get(1)
                            , TextView.BufferType.EDITABLE);
                    editTextKbyps.setText(results.get(2)
                            , TextView.BufferType.EDITABLE);
                    editTextMbps.setText(results.get(3)
                            , TextView.BufferType.EDITABLE);
                    editTextMbyps.setText(results.get(4)
                            , TextView.BufferType.EDITABLE);
                    editTextGbps.setText(results.get(5)
                            , TextView.BufferType.EDITABLE);
                    editTextGbyps.setText(results.get(6)
                            , TextView.BufferType.EDITABLE);
                    editTextTbps.setText(results.get(7)
                            , TextView.BufferType.EDITABLE);
                    editTextTbyps.setText(results.get(8)
                            , TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
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

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableByps != null) {
                if (Utils.isNumeric(mEditableByps.toString())) {
                    try {
                        BigDecimal byps = new BigDecimal(mEditableByps.toString());
                        BigDecimal bps = byps.multiply(new BigDecimal("8"));
                        BigDecimal kbps = byps.multiply(new BigDecimal("0.008"));
                        BigDecimal kbyps = byps.multiply(new BigDecimal("0.001"));
                        BigDecimal mbps = byps.multiply(new BigDecimal("0.000008"));
                        BigDecimal mbyps = byps.multiply(new BigDecimal("0.000001"));
                        BigDecimal gbps = byps.multiply(new BigDecimal("0.000000008"));
                        BigDecimal gbyps = byps.multiply(new BigDecimal("0.000000001"));
                        BigDecimal tbps = byps.multiply(new BigDecimal("0.000000000008"));
                        BigDecimal tbyps = byps.multiply(new BigDecimal("0.000000000001"));

                        results.add(bps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(gbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(gbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(tbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(tbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 9);
                    }
                } else {
                    addWhitespaceItems(results, 9);
                }
            } else {
                addWhitespaceItems(results, 9);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextBps.setText(results.get(0)
                            , TextView.BufferType.EDITABLE);
                    editTextKbps.setText(results.get(1)
                            , TextView.BufferType.EDITABLE);
                    editTextKbyps.setText(results.get(2)
                            , TextView.BufferType.EDITABLE);
                    editTextMbps.setText(results.get(3)
                            , TextView.BufferType.EDITABLE);
                    editTextMbyps.setText(results.get(4)
                            , TextView.BufferType.EDITABLE);
                    editTextGbps.setText(results.get(5)
                            , TextView.BufferType.EDITABLE);
                    editTextGbyps.setText(results.get(6)
                            , TextView.BufferType.EDITABLE);
                    editTextTbps.setText(results.get(7)
                            , TextView.BufferType.EDITABLE);
                    editTextTbyps.setText(results.get(8)
                            , TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
        }
    }

    private class ConversionFromKbpsRunnable implements Runnable {
        private final String TAG = "ConversionFromKbpsRunnable";

        private Editable mEditableKbps;
        private String mCallingClassName;

        public ConversionFromKbpsRunnable(Editable editableKbps, String callingClassName) {
            mEditableKbps = editableKbps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableKbps != null) {
                if (Utils.isNumeric(mEditableKbps.toString())) {
                    try {
                        BigDecimal kbps = new BigDecimal(mEditableKbps.toString());
                        BigDecimal bps = kbps.multiply(new BigDecimal("1000"));
                        BigDecimal byps = kbps.multiply(new BigDecimal("125"));
                        BigDecimal kbyps = kbps.multiply(new BigDecimal("0.125"));
                        BigDecimal mbps = kbps.multiply(new BigDecimal("0.001"));
                        BigDecimal mbyps = kbps.multiply(new BigDecimal("0.000125"));
                        BigDecimal gbps = kbps.multiply(new BigDecimal("0.000001"));
                        BigDecimal gbyps = kbps.multiply(new BigDecimal("0.000000125"));
                        BigDecimal tbps = kbps.multiply(new BigDecimal("0.000000001"));
                        BigDecimal tbyps = kbps.multiply(new BigDecimal("0.000000000125"));

                        results.add(bps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(byps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(gbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(gbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(tbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(tbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 9);
                    }
                } else {
                    addWhitespaceItems(results, 9);
                }
            } else {
                addWhitespaceItems(results, 9);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextBps.setText(results.get(0)
                            , TextView.BufferType.EDITABLE);
                    editTextByps.setText(results.get(1)
                            , TextView.BufferType.EDITABLE);
                    editTextKbyps.setText(results.get(2)
                            , TextView.BufferType.EDITABLE);
                    editTextMbps.setText(results.get(3)
                            , TextView.BufferType.EDITABLE);
                    editTextMbyps.setText(results.get(4)
                            , TextView.BufferType.EDITABLE);
                    editTextGbps.setText(results.get(5)
                            , TextView.BufferType.EDITABLE);
                    editTextGbyps.setText(results.get(6)
                            , TextView.BufferType.EDITABLE);
                    editTextTbps.setText(results.get(7)
                            , TextView.BufferType.EDITABLE);
                    editTextTbyps.setText(results.get(8)
                            , TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
        }
    }

    private class ConversionFromKbypsRunnable implements Runnable {
        private final String TAG = "ConversionFromKbypsRunnable";

        private Editable mEditableKbyps;
        private String mCallingClassName;

        public ConversionFromKbypsRunnable(Editable editableKbyps, String callingClassName) {
            mEditableKbyps = editableKbyps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableKbyps != null) {
                if (Utils.isNumeric(mEditableKbyps.toString())) {
                    try {
                        BigDecimal kbyps = new BigDecimal(mEditableKbyps.toString());
                        BigDecimal bps = kbyps.multiply(new BigDecimal("8000"));
                        BigDecimal byps = kbyps.multiply(new BigDecimal("1000"));
                        BigDecimal kbps = kbyps.multiply(new BigDecimal("8"));
                        BigDecimal mbps = kbyps.multiply(new BigDecimal("0.008"));
                        BigDecimal mbyps = kbyps.multiply(new BigDecimal("0.001"));
                        BigDecimal gbps = kbyps.multiply(new BigDecimal("0.000008"));
                        BigDecimal gbyps = kbyps.multiply(new BigDecimal("0.000001"));
                        BigDecimal tbps = kbyps.multiply(new BigDecimal("0.000000008"));
                        BigDecimal tbyps = kbyps.multiply(new BigDecimal("0.000000001"));

                        results.add(bps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(byps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(gbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(gbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(tbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(tbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 9);
                    }
                } else {
                    addWhitespaceItems(results, 9);
                }
            } else {
                addWhitespaceItems(results, 9);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextBps.setText(results.get(0)
                            , TextView.BufferType.EDITABLE);
                    editTextByps.setText(results.get(1)
                            , TextView.BufferType.EDITABLE);
                    editTextKbps.setText(results.get(2)
                            , TextView.BufferType.EDITABLE);
                    editTextMbps.setText(results.get(3)
                            , TextView.BufferType.EDITABLE);
                    editTextMbyps.setText(results.get(4)
                            , TextView.BufferType.EDITABLE);
                    editTextGbps.setText(results.get(5)
                            , TextView.BufferType.EDITABLE);
                    editTextGbyps.setText(results.get(6)
                            , TextView.BufferType.EDITABLE);
                    editTextTbps.setText(results.get(7)
                            , TextView.BufferType.EDITABLE);
                    editTextTbyps.setText(results.get(8)
                            , TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
        }
    }

    private class ConversionFromMbpsRunnable implements Runnable {
        private final String TAG = "ConversionFromMbpsRunnable";

        private Editable mEditableMbps;
        private String mCallingClassName;

        public ConversionFromMbpsRunnable(Editable editableMbps, String callingClassName) {
            mEditableMbps = editableMbps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableMbps != null) {
                if (Utils.isNumeric(mEditableMbps.toString())) {
                    try {
                        BigDecimal mbps = new BigDecimal(mEditableMbps.toString());
                        BigDecimal bps = mbps.multiply(new BigDecimal("1000000"));
                        BigDecimal byps = mbps.multiply(new BigDecimal("125000"));
                        BigDecimal kbps = mbps.multiply(new BigDecimal("1000"));
                        BigDecimal kbyps = mbps.multiply(new BigDecimal("125"));
                        BigDecimal mbyps = mbps.multiply(new BigDecimal("0.125"));
                        BigDecimal gbps = mbps.multiply(new BigDecimal("0.001"));
                        BigDecimal gbyps = mbps.multiply(new BigDecimal("0.000125"));
                        BigDecimal tbps = mbps.multiply(new BigDecimal("0.000001"));
                        BigDecimal tbyps = mbps.multiply(new BigDecimal("0.000000125"));

                        results.add(bps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(byps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(gbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(gbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(tbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(tbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 9);
                    }
                } else {
                    addWhitespaceItems(results, 9);
                }
            } else {
                addWhitespaceItems(results, 9);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextBps.setText(results.get(0)
                            , TextView.BufferType.EDITABLE);
                    editTextByps.setText(results.get(1)
                            , TextView.BufferType.EDITABLE);
                    editTextKbps.setText(results.get(2)
                            , TextView.BufferType.EDITABLE);
                    editTextKbyps.setText(results.get(3)
                            , TextView.BufferType.EDITABLE);
                    editTextMbyps.setText(results.get(4)
                            , TextView.BufferType.EDITABLE);
                    editTextGbps.setText(results.get(5)
                            , TextView.BufferType.EDITABLE);
                    editTextGbyps.setText(results.get(6)
                            , TextView.BufferType.EDITABLE);
                    editTextTbps.setText(results.get(7)
                            , TextView.BufferType.EDITABLE);
                    editTextTbyps.setText(results.get(8)
                            , TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
        }
    }

    private class ConversionFromMbypsRunnable implements Runnable {
        private final String TAG = "ConversionFromMbypsRunnable";

        private Editable mEditableMbyps;
        private String mCallingClassName;

        public ConversionFromMbypsRunnable(Editable editableMbyps, String callingClassName) {
            mEditableMbyps = editableMbyps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableMbyps != null) {
                if (Utils.isNumeric(mEditableMbyps.toString())) {
                    try {
                        BigDecimal mbyps = new BigDecimal(mEditableMbyps.toString());
                        BigDecimal bps = mbyps.multiply(new BigDecimal("8000000"));
                        BigDecimal byps = mbyps.multiply(new BigDecimal("1000000"));
                        BigDecimal kbps = mbyps.multiply(new BigDecimal("8000"));
                        BigDecimal kbyps = mbyps.multiply(new BigDecimal("1000"));
                        BigDecimal mbps = mbyps.multiply(new BigDecimal("8"));
                        BigDecimal gbps = mbyps.multiply(new BigDecimal("0.008"));
                        BigDecimal gbyps = mbyps.multiply(new BigDecimal("0.001"));
                        BigDecimal tbps = mbyps.multiply(new BigDecimal("0.000008"));
                        BigDecimal tbyps = mbyps.multiply(new BigDecimal("0.000001"));

                        results.add(bps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(byps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(gbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(gbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(tbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(tbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 9);
                    }
                } else {
                    addWhitespaceItems(results, 9);
                }
            } else {
                addWhitespaceItems(results, 9);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextBps.setText(results.get(0)
                            , TextView.BufferType.EDITABLE);
                    editTextByps.setText(results.get(1)
                            , TextView.BufferType.EDITABLE);
                    editTextKbps.setText(results.get(2)
                            , TextView.BufferType.EDITABLE);
                    editTextKbyps.setText(results.get(3)
                            , TextView.BufferType.EDITABLE);
                    editTextMbps.setText(results.get(4)
                            , TextView.BufferType.EDITABLE);
                    editTextGbps.setText(results.get(5)
                            , TextView.BufferType.EDITABLE);
                    editTextGbyps.setText(results.get(6)
                            , TextView.BufferType.EDITABLE);
                    editTextTbps.setText(results.get(7)
                            , TextView.BufferType.EDITABLE);
                    editTextTbyps.setText(results.get(8)
                            , TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
        }
    }

    private class ConversionFromGbpsRunnable implements Runnable {
        private final String TAG = "ConversionFromGbpsRunnable";

        private Editable mEditableGbps;
        private String mCallingClassName;

        public ConversionFromGbpsRunnable(Editable editableGbps, String callingClassName) {
            mEditableGbps = editableGbps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableGbps != null) {
                if (Utils.isNumeric(mEditableGbps.toString())) {
                    try {
                        BigDecimal gbps = new BigDecimal(mEditableGbps.toString());
                        BigDecimal bps = gbps.multiply(new BigDecimal("1000000000"));
                        BigDecimal byps = gbps.multiply(new BigDecimal("125000000"));
                        BigDecimal kbps = gbps.multiply(new BigDecimal("1000000"));
                        BigDecimal kbyps = gbps.multiply(new BigDecimal("125000"));
                        BigDecimal mbps = gbps.multiply(new BigDecimal("1000"));
                        BigDecimal mbyps = gbps.multiply(new BigDecimal("125"));
                        BigDecimal gbyps = gbps.multiply(new BigDecimal("0.125"));
                        BigDecimal tbps = gbps.multiply(new BigDecimal("0.001"));
                        BigDecimal tbyps = gbps.multiply(new BigDecimal("0.000125"));

                        results.add(bps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(byps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(gbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(tbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(tbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 9);
                    }
                } else {
                    addWhitespaceItems(results, 9);
                }
            } else {
                addWhitespaceItems(results, 9);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextBps.setText(results.get(0)
                            , TextView.BufferType.EDITABLE);
                    editTextByps.setText(results.get(1)
                            , TextView.BufferType.EDITABLE);
                    editTextKbps.setText(results.get(2)
                            , TextView.BufferType.EDITABLE);
                    editTextKbyps.setText(results.get(3)
                            , TextView.BufferType.EDITABLE);
                    editTextMbps.setText(results.get(4)
                            , TextView.BufferType.EDITABLE);
                    editTextMbyps.setText(results.get(5)
                            , TextView.BufferType.EDITABLE);
                    editTextGbyps.setText(results.get(6)
                            , TextView.BufferType.EDITABLE);
                    editTextTbps.setText(results.get(7)
                            , TextView.BufferType.EDITABLE);
                    editTextTbyps.setText(results.get(8)
                            , TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
        }
    }

    private class ConversionFromGbypsRunnable implements Runnable {
        private final String TAG = "ConversionFromGbypsRunnable";

        private Editable mEditableGbyps;
        private String mCallingClassName;

        public ConversionFromGbypsRunnable(Editable editableGbyps, String callingClassName) {
            mEditableGbyps = editableGbyps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableGbyps != null) {
                if (Utils.isNumeric(mEditableGbyps.toString())) {
                    try {
                        BigDecimal gbyps = new BigDecimal(mEditableGbyps.toString());
                        BigDecimal bps = gbyps.multiply(new BigDecimal("8000000000"));
                        BigDecimal byps = gbyps.multiply(new BigDecimal("1000000000"));
                        BigDecimal kbps = gbyps.multiply(new BigDecimal("8000000"));
                        BigDecimal kbyps = gbyps.multiply(new BigDecimal("1000000"));
                        BigDecimal mbps = gbyps.multiply(new BigDecimal("8000"));
                        BigDecimal mbyps = gbyps.multiply(new BigDecimal("1000"));
                        BigDecimal gbps = gbyps.multiply(new BigDecimal("8"));
                        BigDecimal tbps = gbyps.multiply(new BigDecimal("0.008"));
                        BigDecimal tbyps = gbyps.multiply(new BigDecimal("0.001"));

                        results.add(bps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(byps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(gbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(tbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(tbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 9);
                    }
                } else {
                    addWhitespaceItems(results, 9);
                }
            } else {
                addWhitespaceItems(results, 9);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextBps.setText(results.get(0)
                            , TextView.BufferType.EDITABLE);
                    editTextByps.setText(results.get(1)
                            , TextView.BufferType.EDITABLE);
                    editTextKbps.setText(results.get(2)
                            , TextView.BufferType.EDITABLE);
                    editTextKbyps.setText(results.get(3)
                            , TextView.BufferType.EDITABLE);
                    editTextMbps.setText(results.get(4)
                            , TextView.BufferType.EDITABLE);
                    editTextMbyps.setText(results.get(5)
                            , TextView.BufferType.EDITABLE);
                    editTextGbps.setText(results.get(6)
                            , TextView.BufferType.EDITABLE);
                    editTextTbps.setText(results.get(7)
                            , TextView.BufferType.EDITABLE);
                    editTextTbyps.setText(results.get(8)
                            , TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
        }
    }

    private class ConversionFromTbpsRunnable implements Runnable {
        private final String TAG = "ConversionFromTbpsRunnable";

        private Editable mEditableTbps;
        private String mCallingClassName;

        public ConversionFromTbpsRunnable(Editable editableTbps, String callingClassName) {
            mEditableTbps = editableTbps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableTbps != null) {
                if (Utils.isNumeric(mEditableTbps.toString())) {
                    try {
                        BigDecimal tbps = new BigDecimal(mEditableTbps.toString());
                        BigDecimal bps = tbps.multiply(new BigDecimal("1000000000000"));
                        BigDecimal byps = tbps.multiply(new BigDecimal("125000000000"));
                        BigDecimal kbps = tbps.multiply(new BigDecimal("1000000000"));
                        BigDecimal kbyps = tbps.multiply(new BigDecimal("125000000"));
                        BigDecimal mbps = tbps.multiply(new BigDecimal("1000000"));
                        BigDecimal mbyps = tbps.multiply(new BigDecimal("125000"));
                        BigDecimal gbps = tbps.multiply(new BigDecimal("1000"));
                        BigDecimal gbyps = tbps.multiply(new BigDecimal("125"));
                        BigDecimal tbyps = tbps.multiply(new BigDecimal("0.125"));

                        results.add(bps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(byps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(gbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(gbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(tbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 9);
                    }
                } else {
                    addWhitespaceItems(results, 9);
                }
            } else {
                addWhitespaceItems(results, 9);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextBps.setText(results.get(0)
                            , TextView.BufferType.EDITABLE);
                    editTextByps.setText(results.get(1)
                            , TextView.BufferType.EDITABLE);
                    editTextKbps.setText(results.get(2)
                            , TextView.BufferType.EDITABLE);
                    editTextKbyps.setText(results.get(3)
                            , TextView.BufferType.EDITABLE);
                    editTextMbps.setText(results.get(4)
                            , TextView.BufferType.EDITABLE);
                    editTextMbyps.setText(results.get(5)
                            , TextView.BufferType.EDITABLE);
                    editTextGbps.setText(results.get(6)
                            , TextView.BufferType.EDITABLE);
                    editTextGbyps.setText(results.get(7)
                            , TextView.BufferType.EDITABLE);
                    editTextTbyps.setText(results.get(8)
                            , TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
        }
    }

    private class ConversionFromTbypsRunnable implements Runnable {
        private final String TAG = "ConversionFromTbypsRunnable";

        private Editable mEditableTbyps;
        private String mCallingClassName;

        public ConversionFromTbypsRunnable(Editable editableTbyps, String callingClassName) {
            mEditableTbyps = editableTbyps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableTbyps != null) {
                if (Utils.isNumeric(mEditableTbyps.toString())) {
                    try {
                        BigDecimal tbyps = new BigDecimal(mEditableTbyps.toString());
                        BigDecimal bps = tbyps.multiply(new BigDecimal("8000000000000"));
                        BigDecimal byps = tbyps.multiply(new BigDecimal("1000000000000"));
                        BigDecimal kbps = tbyps.multiply(new BigDecimal("8000000000"));
                        BigDecimal kbyps = tbyps.multiply(new BigDecimal("1000000000"));
                        BigDecimal mbps = tbyps.multiply(new BigDecimal("8000000"));
                        BigDecimal mbyps = tbyps.multiply(new BigDecimal("1000000"));
                        BigDecimal gbps = tbyps.multiply(new BigDecimal("8000"));
                        BigDecimal gbyps = tbyps.multiply(new BigDecimal("1000"));
                        BigDecimal tbps = tbyps.multiply(new BigDecimal("8"));

                        results.add(bps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(byps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(gbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(gbyps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(tbps
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 9);
                    }
                } else {
                    addWhitespaceItems(results, 9);
                }
            } else {
                addWhitespaceItems(results, 9);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextBps.setText(results.get(0)
                            , TextView.BufferType.EDITABLE);
                    editTextByps.setText(results.get(1)
                            , TextView.BufferType.EDITABLE);
                    editTextKbps.setText(results.get(2)
                            , TextView.BufferType.EDITABLE);
                    editTextKbyps.setText(results.get(3)
                            , TextView.BufferType.EDITABLE);
                    editTextMbps.setText(results.get(4)
                            , TextView.BufferType.EDITABLE);
                    editTextMbyps.setText(results.get(5)
                            , TextView.BufferType.EDITABLE);
                    editTextGbps.setText(results.get(6)
                            , TextView.BufferType.EDITABLE);
                    editTextGbyps.setText(results.get(7)
                            , TextView.BufferType.EDITABLE);
                    editTextTbps.setText(results.get(8)
                            , TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
        }
    }

    // endregion
}
