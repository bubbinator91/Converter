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

/**
 * Inch, Foot, Yard, Mile, Millimeter, Centimeter, Meter, Kilometer
 * Conversions comply with the conversions through Google.com
 */

public class LengthFragment extends BaseFragment {
    private enum LastEditTextFocused {
        INCH,
        FOOT,
        YARD,
        MILE,
        MILLIMETER,
        CENTIMETER,
        METER,
        KILOMETER
    }

    private final String TAG = "FragmentLength";

    private EditText editTextInch, editTextFoot, editTextYard, editTextMile, editTextMillimeter,
            editTextCentimeter, editTextMeter, editTextKilometer;

    private LastEditTextFocused lastEditTextFocused;

    // region TextWatchers

    private TextWatcher textWatcherInch = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.INCH;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("textWatcherInch");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherInch");
                new Thread(new ConversionFromInchRunnable(s, "textWatcherInch")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherFoot = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.FOOT;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("textWatcherFoot");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherFoot");
                new Thread(new ConversionFromFootRunnable(s, "textWatcherFoot")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherYard = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.YARD;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("textWatcherYard");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherYard");
                new Thread(new ConversionFromYardRunnable(s, "textWatcherYard")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherMile = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.MILE;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("textWatcherMile");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherMile");
                new Thread(new ConversionFromMileRunnable(s, "textWatcherMile")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherMillimeter = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.MILLIMETER;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("textWatcherMillimeter");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherMillimeter");
                new Thread(new ConversionFromMillimeterRunnable(s, "textWatcherMillimeter")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherCentimeter = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.CENTIMETER;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("textWatcherCentimeter");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherCentimeter");
                new Thread(new ConversionFromCentimeterRunnable(s, "textWatcherCentimeter")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherMeter = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.METER;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("textWatcherMeter");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherMeter");
                new Thread(new ConversionFromMeterRunnable(s, "textWatcherMeter")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherKilometer = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.KILOMETER;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("textWatcherKilometer");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherKilometer");
                new Thread(new ConversionFromKilometerRunnable(s, "textWatcherKilometer")).start();
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

            TextView textViewInch = ((TextView) getRootView().findViewById(R.id.textView_length_inch));
            TextView textViewFoot = ((TextView) getRootView().findViewById(R.id.textView_length_foot));
            TextView textViewYard = ((TextView) getRootView().findViewById(R.id.textView_length_yard));
            TextView textViewMile = ((TextView) getRootView().findViewById(R.id.textView_length_mile));
            TextView textViewMillimeter = ((TextView) getRootView().findViewById(R.id.textView_length_millimeter));
            TextView textViewCentimeter = ((TextView) getRootView().findViewById(R.id.textView_length_centimeter));
            TextView textViewMeter = ((TextView) getRootView().findViewById(R.id.textView_length_meter));
            TextView textViewKilometer = ((TextView) getRootView().findViewById(R.id.textView_length_kilometer));

            textViewInch.setTypeface(tf);
            textViewFoot.setTypeface(tf);
            textViewYard.setTypeface(tf);
            textViewMile.setTypeface(tf);
            textViewMillimeter.setTypeface(tf);
            textViewCentimeter.setTypeface(tf);
            textViewMeter.setTypeface(tf);
            textViewKilometer.setTypeface(tf);

            editTextInch = ((EditText) getRootView().findViewById(R.id.editText_length_inch));
            editTextFoot = ((EditText) getRootView().findViewById(R.id.editText_length_foot));
            editTextYard = ((EditText) getRootView().findViewById(R.id.editText_length_yard));
            editTextMile = ((EditText) getRootView().findViewById(R.id.editText_length_mile));
            editTextMillimeter = ((EditText) getRootView().findViewById(R.id.editText_length_millimeter));
            editTextCentimeter = ((EditText) getRootView().findViewById(R.id.editText_length_centimeter));
            editTextMeter = ((EditText) getRootView().findViewById(R.id.editText_length_meter));
            editTextKilometer = ((EditText) getRootView().findViewById(R.id.editText_length_kilometer));

            addTextChangedListeners(null);
        }

        return getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.tag(TAG + ".onResume").i("Entered");

        if (lastEditTextFocused == LastEditTextFocused.INCH) {
            if ((getHandler() != null) && (editTextInch.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextInch.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromInchRunnable(editTextInch.getText(), "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.FOOT) {
            if ((getHandler() != null) && (editTextFoot.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextFoot.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromFootRunnable(editTextFoot.getText(), "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.YARD) {
            if ((getHandler() != null) && (editTextYard.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextYard.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromYardRunnable(editTextYard.getText(), "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.MILE) {
            if ((getHandler() != null) && (editTextMile.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextMile.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMileRunnable(editTextMile.getText(), "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.MILLIMETER) {
            if ((getHandler() != null) && (editTextMillimeter.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextMillimeter.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMillimeterRunnable(editTextMillimeter.getText(), "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.CENTIMETER) {
            if ((getHandler() != null) && (editTextCentimeter.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextCentimeter.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromCentimeterRunnable(editTextCentimeter.getText(), "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.METER) {
            if ((getHandler() != null) && (editTextMeter.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextMeter.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMeterRunnable(editTextMeter.getText(), "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.KILOMETER) {
            if ((getHandler() != null) && (editTextKilometer.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextKilometer.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromKilometerRunnable(editTextKilometer.getText(), "onResume")).start();
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

        editTextInch.addTextChangedListener(textWatcherInch);
        editTextFoot.addTextChangedListener(textWatcherFoot);
        editTextYard.addTextChangedListener(textWatcherYard);
        editTextMile.addTextChangedListener(textWatcherMile);
        editTextMillimeter.addTextChangedListener(textWatcherMillimeter);
        editTextCentimeter.addTextChangedListener(textWatcherCentimeter);
        editTextMeter.addTextChangedListener(textWatcherMeter);
        editTextKilometer.addTextChangedListener(textWatcherKilometer);
    }

    private void removeTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".removeTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".removeTextChangedListeners").i("Entered");
        }

        editTextInch.removeTextChangedListener(textWatcherInch);
        editTextFoot.removeTextChangedListener(textWatcherFoot);
        editTextYard.removeTextChangedListener(textWatcherYard);
        editTextMile.removeTextChangedListener(textWatcherMile);
        editTextMillimeter.removeTextChangedListener(textWatcherMillimeter);
        editTextCentimeter.removeTextChangedListener(textWatcherCentimeter);
        editTextMeter.removeTextChangedListener(textWatcherMeter);
        editTextKilometer.removeTextChangedListener(textWatcherKilometer);
    }

    // endregion

    // region Overridden BaseFragment methods

    @Override
    protected String getChildTag() { return TAG; }

    @Override
    protected int getLayoutResource() { return R.layout.fragment_length; }

    @Override
    protected int getScrollViewResource() { return  R.id.fragment_length; }

    // endregion

    // region Private classes

    private class ConversionFromInchRunnable implements Runnable {
        private final String TAG = "ConversionFromInchRunnable";

        private Editable mEditableInch;
        private String mCallingClassName;

        public ConversionFromInchRunnable(Editable editableInch, String callingClassName) {
            mEditableInch = editableInch;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableInch != null) {
                if (Utils.isNumeric(mEditableInch.toString())) {
                    try {
                        BigDecimal inch = new BigDecimal(mEditableInch.toString());
                        BigDecimal foot = inch.divide(new BigDecimal("12")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal yard = foot.divide(new BigDecimal("3")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal mile = foot.divide(new BigDecimal("5280")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal millimeter = inch.multiply(new BigDecimal("25.4"));
                        BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal meter = millimeter.divide(new BigDecimal("1000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);

                        results.add(foot
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(yard
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mile
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(millimeter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(centimeter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(meter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kilometer
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 7);
                    }
                } else {
                    addWhitespaceItems(results, 7);
                }
            } else {
                addWhitespaceItems(results, 7);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextFoot.setText(results.get(0), TextView.BufferType.EDITABLE);
                    editTextYard.setText(results.get(1), TextView.BufferType.EDITABLE);
                    editTextMile.setText(results.get(2), TextView.BufferType.EDITABLE);
                    editTextMillimeter.setText(results.get(3), TextView.BufferType.EDITABLE);
                    editTextCentimeter.setText(results.get(4), TextView.BufferType.EDITABLE);
                    editTextMeter.setText(results.get(5), TextView.BufferType.EDITABLE);
                    editTextKilometer.setText(results.get(6), TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
        }
    }

    private class ConversionFromFootRunnable implements Runnable {
        private final String TAG = "ConversionFromFootRunnable";

        private Editable mEditableFoot;
        private String mCallingClassName;

        public ConversionFromFootRunnable(Editable editableFoot, String callingClassName) {
            mEditableFoot = editableFoot;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableFoot != null) {
                if (Utils.isNumeric(mEditableFoot.toString())) {
                    try {
                        BigDecimal foot = new BigDecimal(mEditableFoot.toString());
                        BigDecimal inch = foot.multiply(new BigDecimal("12"));
                        BigDecimal yard = foot.divide(new BigDecimal("3")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal mile = foot.divide(new BigDecimal("5280")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal millimeter = inch.multiply(new BigDecimal("25.4"));
                        BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal meter = millimeter.divide(new BigDecimal("1000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);

                        results.add(inch
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(yard
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mile
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(millimeter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(centimeter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(meter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kilometer
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 7);
                    }
                } else {
                    addWhitespaceItems(results, 7);
                }
            } else {
                addWhitespaceItems(results, 7);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextInch.setText(results.get(0), TextView.BufferType.EDITABLE);
                    editTextYard.setText(results.get(1), TextView.BufferType.EDITABLE);
                    editTextMile.setText(results.get(2), TextView.BufferType.EDITABLE);
                    editTextMillimeter.setText(results.get(3), TextView.BufferType.EDITABLE);
                    editTextCentimeter.setText(results.get(4), TextView.BufferType.EDITABLE);
                    editTextMeter.setText(results.get(5), TextView.BufferType.EDITABLE);
                    editTextKilometer.setText(results.get(6), TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
        }
    }

    private class ConversionFromYardRunnable implements Runnable {
        private final String TAG = "ConversionFromYardRunnable";

        private Editable mEditableYard;
        private String mCallingClassName;

        public ConversionFromYardRunnable(Editable editableYard, String callingClassName) {
            mEditableYard = editableYard;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableYard != null) {
                if (Utils.isNumeric(mEditableYard.toString())) {
                    try {
                        BigDecimal yard = new BigDecimal(mEditableYard.toString());
                        BigDecimal inch = yard.multiply(new BigDecimal("36"));
                        BigDecimal foot = yard.multiply(new BigDecimal("3"));
                        BigDecimal mile = foot.divide(new BigDecimal("5280")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal millimeter = inch.multiply(new BigDecimal("25.4"));
                        BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal meter = millimeter.divide(new BigDecimal("1000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);

                        results.add(inch
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(foot
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mile
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(millimeter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(centimeter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(meter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kilometer
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 7);
                    }
                } else {
                    addWhitespaceItems(results, 7);
                }
            } else {
                addWhitespaceItems(results, 7);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextInch.setText(results.get(0), TextView.BufferType.EDITABLE);
                    editTextFoot.setText(results.get(1), TextView.BufferType.EDITABLE);
                    editTextMile.setText(results.get(2), TextView.BufferType.EDITABLE);
                    editTextMillimeter.setText(results.get(3), TextView.BufferType.EDITABLE);
                    editTextCentimeter.setText(results.get(4), TextView.BufferType.EDITABLE);
                    editTextMeter.setText(results.get(5), TextView.BufferType.EDITABLE);
                    editTextKilometer.setText(results.get(6), TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
        }
    }

    private class ConversionFromMileRunnable implements Runnable {
        private final String TAG = "ConversionFromMileRunnable";

        private Editable mEditableMile;
        private String mCallingClassName;

        public ConversionFromMileRunnable(Editable editableMile, String callingClassName) {
            mEditableMile = editableMile;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableMile != null) {
                if (Utils.isNumeric(mEditableMile.toString())) {
                    try {
                        BigDecimal mile = new BigDecimal(mEditableMile.toString());
                        BigDecimal inch = mile.multiply(new BigDecimal("63360"));
                        BigDecimal foot = mile.multiply(new BigDecimal("5280"));
                        BigDecimal yard = mile.multiply(new BigDecimal("1760"));
                        BigDecimal millimeter = inch.multiply(new BigDecimal("25.4"));
                        BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal meter = millimeter.divide(new BigDecimal("1000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);

                        results.add(inch
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(foot
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(yard
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(millimeter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(centimeter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(meter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kilometer
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 7);
                    }
                } else {
                    addWhitespaceItems(results, 7);
                }
            } else {
                addWhitespaceItems(results, 7);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextInch.setText(results.get(0), TextView.BufferType.EDITABLE);
                    editTextFoot.setText(results.get(1), TextView.BufferType.EDITABLE);
                    editTextYard.setText(results.get(2), TextView.BufferType.EDITABLE);
                    editTextMillimeter.setText(results.get(3), TextView.BufferType.EDITABLE);
                    editTextCentimeter.setText(results.get(4), TextView.BufferType.EDITABLE);
                    editTextMeter.setText(results.get(5), TextView.BufferType.EDITABLE);
                    editTextKilometer.setText(results.get(6), TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
        }
    }

    private class ConversionFromMillimeterRunnable implements Runnable {
        private final String TAG = "ConversionFromMillimeterRunnable";

        private Editable mEditableMillimeter;
        private String mCallingClassName;

        public ConversionFromMillimeterRunnable(Editable editableMillimeter, String callingClassName) {
            mEditableMillimeter = editableMillimeter;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableMillimeter != null) {
                if (Utils.isNumeric(mEditableMillimeter.toString())) {
                    try {
                        BigDecimal millimeter = new BigDecimal(mEditableMillimeter.toString());
                        BigDecimal inch = millimeter.multiply(new BigDecimal("0.03937007874015748031496062992126"));
                        BigDecimal foot = millimeter.multiply(new BigDecimal("0.00328083989501312335958005249344"));
                        BigDecimal yard = millimeter.multiply(new BigDecimal("0.00109361329833770778652668416448"));
                        BigDecimal mile = foot.divide(new BigDecimal("5280")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal meter = millimeter.divide(new BigDecimal("1000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);

                        results.add(inch
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(foot
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(yard
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mile
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(centimeter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(meter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kilometer
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 7);
                    }
                } else {
                    addWhitespaceItems(results, 7);
                }
            } else {
                addWhitespaceItems(results, 7);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextInch.setText(results.get(0), TextView.BufferType.EDITABLE);
                    editTextFoot.setText(results.get(1), TextView.BufferType.EDITABLE);
                    editTextYard.setText(results.get(2), TextView.BufferType.EDITABLE);
                    editTextMile.setText(results.get(3), TextView.BufferType.EDITABLE);
                    editTextCentimeter.setText(results.get(4), TextView.BufferType.EDITABLE);
                    editTextMeter.setText(results.get(5), TextView.BufferType.EDITABLE);
                    editTextKilometer.setText(results.get(6), TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
        }
    }

    private class ConversionFromCentimeterRunnable implements Runnable {
        private final String TAG = "ConversionFromCentimeterRunnable";

        private Editable mEditableCentimeter;
        private String mCallingClassName;

        public ConversionFromCentimeterRunnable(Editable editableCentimeter, String callingClassName) {
            mEditableCentimeter = editableCentimeter;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableCentimeter != null) {
                if (Utils.isNumeric(mEditableCentimeter.toString())) {
                    try {
                        BigDecimal centimeter = new BigDecimal(mEditableCentimeter.toString());
                        BigDecimal inch = centimeter.multiply(new BigDecimal("0.3937007874015748031496062992126"));
                        BigDecimal foot = centimeter.multiply(new BigDecimal("0.0328083989501312335958005249344"));
                        BigDecimal yard = centimeter.multiply(new BigDecimal("0.0109361329833770778652668416448"));
                        BigDecimal mile = foot.divide(new BigDecimal("5280")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal millimeter = centimeter.multiply(new BigDecimal("10"));
                        BigDecimal meter = centimeter.divide(new BigDecimal("100")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal kilometer = centimeter.divide(new BigDecimal("100000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);

                        results.add(inch
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(foot
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(yard
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mile
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(millimeter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(meter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kilometer
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 7);
                    }
                } else {
                    addWhitespaceItems(results, 7);
                }
            } else {
                addWhitespaceItems(results, 7);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextInch.setText(results.get(0), TextView.BufferType.EDITABLE);
                    editTextFoot.setText(results.get(1), TextView.BufferType.EDITABLE);
                    editTextYard.setText(results.get(2), TextView.BufferType.EDITABLE);
                    editTextMile.setText(results.get(3), TextView.BufferType.EDITABLE);
                    editTextMillimeter.setText(results.get(4), TextView.BufferType.EDITABLE);
                    editTextMeter.setText(results.get(5), TextView.BufferType.EDITABLE);
                    editTextKilometer.setText(results.get(6), TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
        }
    }

    private class ConversionFromMeterRunnable implements Runnable {
        private final String TAG = "ConversionFromMeterRunnable";

        private Editable mEditableMeter;
        private String mCallingClassName;

        public ConversionFromMeterRunnable(Editable editableMeter, String callingClassName) {
            mEditableMeter = editableMeter;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableMeter != null) {
                if (Utils.isNumeric(mEditableMeter.toString())) {
                    try {
                        BigDecimal meter = new BigDecimal(mEditableMeter.toString());
                        BigDecimal inch = meter.multiply(new BigDecimal("39.37007874015748031496062992126"));
                        BigDecimal foot = meter.multiply(new BigDecimal("3.28083989501312335958005249344"));
                        BigDecimal yard = meter.multiply(new BigDecimal("1.09361329833770778652668416448"));
                        BigDecimal mile = foot.divide(new BigDecimal("5280")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal millimeter = meter.multiply(new BigDecimal("1000"));
                        BigDecimal centimeter = meter.multiply(new BigDecimal("100"));
                        BigDecimal kilometer = meter.divide(new BigDecimal("1000")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);

                        results.add(inch
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(foot
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(yard
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mile
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(millimeter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(centimeter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(kilometer
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 7);
                    }
                } else {
                    addWhitespaceItems(results, 7);
                }
            } else {
                addWhitespaceItems(results, 7);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextInch.setText(results.get(0), TextView.BufferType.EDITABLE);
                    editTextFoot.setText(results.get(1), TextView.BufferType.EDITABLE);
                    editTextYard.setText(results.get(2), TextView.BufferType.EDITABLE);
                    editTextMile.setText(results.get(3), TextView.BufferType.EDITABLE);
                    editTextMillimeter.setText(results.get(4), TextView.BufferType.EDITABLE);
                    editTextCentimeter.setText(results.get(5), TextView.BufferType.EDITABLE);
                    editTextKilometer.setText(results.get(6), TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
        }
    }

    private class ConversionFromKilometerRunnable implements Runnable {
        private final String TAG = "ConversionFromKilometerRunnable";

        private Editable mEditableKilometer;
        private String mCallingClassName;

        public ConversionFromKilometerRunnable(Editable editableKilometer, String callingClassName) {
            mEditableKilometer = editableKilometer;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            final ArrayList<String> results = new ArrayList<>();
            if (mEditableKilometer != null) {
                if (Utils.isNumeric(mEditableKilometer.toString())) {
                    try {
                        BigDecimal kilometer = new BigDecimal(mEditableKilometer.toString());
                        BigDecimal inch = kilometer.multiply(new BigDecimal("39370.07874015748031496062992126"));
                        BigDecimal foot = kilometer.multiply(new BigDecimal("3280.83989501312335958005249344"));
                        BigDecimal yard = kilometer.multiply(new BigDecimal("1093.61329833770778652668416448"));
                        BigDecimal mile = foot.divide(new BigDecimal("5280")
                                , getFieldLength()
                                , BigDecimal.ROUND_HALF_UP);
                        BigDecimal millimeter = kilometer.multiply(new BigDecimal("1000000"));
                        BigDecimal centimeter = kilometer.multiply(new BigDecimal("100000"));
                        BigDecimal meter = kilometer.multiply(new BigDecimal("1000"));

                        results.add(inch
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(foot
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(yard
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(mile
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(millimeter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(centimeter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                        results.add(meter
                                .setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
                                .stripTrailingZeros()
                                .toPlainString());
                    } catch (NumberFormatException e) {
                        Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
                        addWhitespaceItems(results, 7);
                    }
                } else {
                    addWhitespaceItems(results, 7);
                }
            } else {
                addWhitespaceItems(results, 7);
            }

            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    removeTextChangedListeners(TAG + "." + mCallingClassName);
                    editTextInch.setText(results.get(0), TextView.BufferType.EDITABLE);
                    editTextFoot.setText(results.get(1), TextView.BufferType.EDITABLE);
                    editTextYard.setText(results.get(2), TextView.BufferType.EDITABLE);
                    editTextMile.setText(results.get(3), TextView.BufferType.EDITABLE);
                    editTextMillimeter.setText(results.get(4), TextView.BufferType.EDITABLE);
                    editTextCentimeter.setText(results.get(5), TextView.BufferType.EDITABLE);
                    editTextMeter.setText(results.get(6), TextView.BufferType.EDITABLE);
                    addTextChangedListeners(TAG + "." + mCallingClassName);
                }
            });
        }
    }

    // endregion
}
