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

import com.bubbinator91.conversion.length.Centimeters;
import com.bubbinator91.conversion.length.Feet;
import com.bubbinator91.conversion.length.Inches;
import com.bubbinator91.conversion.length.Kilometers;
import com.bubbinator91.conversion.length.Meters;
import com.bubbinator91.conversion.length.Miles;
import com.bubbinator91.conversion.length.Millimeters;
import com.bubbinator91.conversion.length.Yards;
import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.conversion.util.Tuple;
import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.Utils;

import java.util.List;

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

    private AppCompatEditText mEditTextInch, mEditTextFoot, mEditTextYard, mEditTextMile,
            mEditTextMillimeter, mEditTextCentimeter, mEditTextMeter, mEditTextKilometer;

    private TextInputLayout mTextInputLayoutInch, mTextInputLayoutFoot, mTextInputLayoutYard,
            mTextInputLayoutMile, mTextInputLayoutMillimeter, mTextInputLayoutCentimeter,
            mTextInputLayoutMeter, mTextInputLayoutKilometer;

    private LastEditTextFocused mLastEditTextFocused;

    // region TextWatchers

    private TextWatcher mTextWatcherInch = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.INCH;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherInch");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherInch");
                new Thread(new ConversionFromInchRunnable(s, "mTextWatcherInch")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherFoot = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.FOOT;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherFoot");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherFoot");
                new Thread(new ConversionFromFootRunnable(s, "mTextWatcherFoot")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherYard = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.YARD;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherYard");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherYard");
                new Thread(new ConversionFromYardRunnable(s, "mTextWatcherYard")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherMile = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.MILE;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherMile");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherMile");
                new Thread(new ConversionFromMileRunnable(s, "mTextWatcherMile")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherMillimeter = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.MILLIMETER;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherMillimeter");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherMillimeter");
                new Thread(new ConversionFromMillimeterRunnable(s, "mTextWatcherMillimeter")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherCentimeter = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.CENTIMETER;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherCentimeter");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherCentimeter");
                new Thread(new ConversionFromCentimeterRunnable(s, "mTextWatcherCentimeter")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherMeter = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.METER;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherMeter");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherMeter");
                new Thread(new ConversionFromMeterRunnable(s, "mTextWatcherMeter")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher mTextWatcherKilometer = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.KILOMETER;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("mTextWatcherKilometer");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherKilometer");
                new Thread(new ConversionFromKilometerRunnable(s, "mTextWatcherKilometer")).start();
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
            mTextInputLayoutInch =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_length_inch));
            mTextInputLayoutFoot =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_length_foot));
            mTextInputLayoutYard =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_length_yard));
            mTextInputLayoutMile =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_length_mile));
            mTextInputLayoutMillimeter =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_length_millimeter));
            mTextInputLayoutCentimeter =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_length_centimeter));
            mTextInputLayoutMeter =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_length_meter));
            mTextInputLayoutKilometer =
                    ((TextInputLayout) getRootView()
                            .findViewById(R.id.textInputLayout_length_kilometer));

            mEditTextInch =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_length_inch));
            mEditTextFoot =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_length_foot));
            mEditTextYard =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_length_yard));
            mEditTextMile =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_length_mile));
            mEditTextMillimeter =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_length_millimeter));
            mEditTextCentimeter =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_length_centimeter));
            mEditTextMeter =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_length_meter));
            mEditTextKilometer =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_length_kilometer));

            addTextChangedListeners(null);
        }

        return getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.tag(TAG + ".onResume").i("Entered");

        if (mLastEditTextFocused == LastEditTextFocused.INCH) {
            if ((getHandler() != null) && (mEditTextInch.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextInch.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromInchRunnable(mEditTextInch.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.FOOT) {
            if ((getHandler() != null) && (mEditTextFoot.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextFoot.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromFootRunnable(mEditTextFoot.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.YARD) {
            if ((getHandler() != null) && (mEditTextYard.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextYard.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromYardRunnable(mEditTextYard.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.MILE) {
            if ((getHandler() != null) && (mEditTextMile.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextMile.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMileRunnable(mEditTextMile.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.MILLIMETER) {
            if ((getHandler() != null) && (mEditTextMillimeter.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextMillimeter.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMillimeterRunnable(mEditTextMillimeter.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.CENTIMETER) {
            if ((getHandler() != null) && (mEditTextCentimeter.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextCentimeter.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromCentimeterRunnable(mEditTextCentimeter.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.METER) {
            if ((getHandler() != null) && (mEditTextMeter.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextMeter.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMeterRunnable(mEditTextMeter.getText(),
                        "onResume")).start();
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.KILOMETER) {
            if ((getHandler() != null) && (mEditTextKilometer.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextKilometer.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromKilometerRunnable(mEditTextKilometer.getText(),
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

        mEditTextInch.addTextChangedListener(mTextWatcherInch);
        mEditTextFoot.addTextChangedListener(mTextWatcherFoot);
        mEditTextYard.addTextChangedListener(mTextWatcherYard);
        mEditTextMile.addTextChangedListener(mTextWatcherMile);
        mEditTextMillimeter.addTextChangedListener(mTextWatcherMillimeter);
        mEditTextCentimeter.addTextChangedListener(mTextWatcherCentimeter);
        mEditTextMeter.addTextChangedListener(mTextWatcherMeter);
        mEditTextKilometer.addTextChangedListener(mTextWatcherKilometer);
    }

    private void removeTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".removeTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".removeTextChangedListeners").i("Entered");
        }

        mEditTextInch.removeTextChangedListener(mTextWatcherInch);
        mEditTextFoot.removeTextChangedListener(mTextWatcherFoot);
        mEditTextYard.removeTextChangedListener(mTextWatcherYard);
        mEditTextMile.removeTextChangedListener(mTextWatcherMile);
        mEditTextMillimeter.removeTextChangedListener(mTextWatcherMillimeter);
        mEditTextCentimeter.removeTextChangedListener(mTextWatcherCentimeter);
        mEditTextMeter.removeTextChangedListener(mTextWatcherMeter);
        mEditTextKilometer.removeTextChangedListener(mTextWatcherKilometer);
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
        private final String TAG = ConversionFromInchRunnable.class.getSimpleName();

        private Editable mEditableInch;
        private String mCallingClassName;

        public ConversionFromInchRunnable(Editable editableInch, String callingClassName) {
            mEditableInch = editableInch;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableInch != null) {
                final Tuple<List<String>, ConversionErrorCodes> results =
                        Inches.toAll(mEditableInch.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutInch.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutInch.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutInch.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutInch.setErrorEnabled(false);
                                    mTextInputLayoutFoot.setErrorEnabled(false);
                                    mTextInputLayoutYard.setErrorEnabled(false);
                                    mTextInputLayoutMile.setErrorEnabled(false);
                                    mTextInputLayoutMillimeter.setErrorEnabled(false);
                                    mTextInputLayoutCentimeter.setErrorEnabled(false);
                                    mTextInputLayoutMeter.setErrorEnabled(false);
                                    mTextInputLayoutKilometer.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextFoot.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextYard.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMile.setText(results.getValue0().get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMillimeter.setText(results.getValue0().get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextCentimeter.setText(results.getValue0().get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMeter.setText(results.getValue0().get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKilometer.setText(results.getValue0().get(6),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromFootRunnable implements Runnable {
        private final String TAG = ConversionFromFootRunnable.class.getSimpleName();

        private Editable mEditableFoot;
        private String mCallingClassName;

        public ConversionFromFootRunnable(Editable editableFoot, String callingClassName) {
            mEditableFoot = editableFoot;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableFoot != null) {
                final Tuple<List<String>, ConversionErrorCodes> results =
                        Feet.toAll(mEditableFoot.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutFoot.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutFoot.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutFoot.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutInch.setErrorEnabled(false);
                                    mTextInputLayoutFoot.setErrorEnabled(false);
                                    mTextInputLayoutYard.setErrorEnabled(false);
                                    mTextInputLayoutMile.setErrorEnabled(false);
                                    mTextInputLayoutMillimeter.setErrorEnabled(false);
                                    mTextInputLayoutCentimeter.setErrorEnabled(false);
                                    mTextInputLayoutMeter.setErrorEnabled(false);
                                    mTextInputLayoutKilometer.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextInch.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextYard.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMile.setText(results.getValue0().get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMillimeter.setText(results.getValue0().get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextCentimeter.setText(results.getValue0().get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMeter.setText(results.getValue0().get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKilometer.setText(results.getValue0().get(6),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromYardRunnable implements Runnable {
        private final String TAG = ConversionFromYardRunnable.class.getSimpleName();

        private Editable mEditableYard;
        private String mCallingClassName;

        public ConversionFromYardRunnable(Editable editableYard, String callingClassName) {
            mEditableYard = editableYard;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableYard != null) {
                final Tuple<List<String>, ConversionErrorCodes> results =
                        Yards.toAll(mEditableYard.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutYard.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutYard.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutYard.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutInch.setErrorEnabled(false);
                                    mTextInputLayoutFoot.setErrorEnabled(false);
                                    mTextInputLayoutYard.setErrorEnabled(false);
                                    mTextInputLayoutMile.setErrorEnabled(false);
                                    mTextInputLayoutMillimeter.setErrorEnabled(false);
                                    mTextInputLayoutCentimeter.setErrorEnabled(false);
                                    mTextInputLayoutMeter.setErrorEnabled(false);
                                    mTextInputLayoutKilometer.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextInch.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextFoot.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMile.setText(results.getValue0().get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMillimeter.setText(results.getValue0().get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextCentimeter.setText(results.getValue0().get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMeter.setText(results.getValue0().get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKilometer.setText(results.getValue0().get(6),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromMileRunnable implements Runnable {
        private final String TAG = ConversionFromMileRunnable.class.getSimpleName();

        private Editable mEditableMile;
        private String mCallingClassName;

        public ConversionFromMileRunnable(Editable editableMile, String callingClassName) {
            mEditableMile = editableMile;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableMile != null) {
                final Tuple<List<String>, ConversionErrorCodes> results =
                        Miles.toAll(mEditableMile.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutMile.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutMile.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutMile.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutInch.setErrorEnabled(false);
                                    mTextInputLayoutFoot.setErrorEnabled(false);
                                    mTextInputLayoutYard.setErrorEnabled(false);
                                    mTextInputLayoutMile.setErrorEnabled(false);
                                    mTextInputLayoutMillimeter.setErrorEnabled(false);
                                    mTextInputLayoutCentimeter.setErrorEnabled(false);
                                    mTextInputLayoutMeter.setErrorEnabled(false);
                                    mTextInputLayoutKilometer.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextInch.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextFoot.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextYard.setText(results.getValue0().get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMillimeter.setText(results.getValue0().get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextCentimeter.setText(results.getValue0().get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMeter.setText(results.getValue0().get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKilometer.setText(results.getValue0().get(6),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromMillimeterRunnable implements Runnable {
        private final String TAG = ConversionFromMillimeterRunnable.class.getSimpleName();

        private Editable mEditableMillimeter;
        private String mCallingClassName;

        public ConversionFromMillimeterRunnable(Editable editableMillimeter,
                                                String callingClassName) {
            mEditableMillimeter = editableMillimeter;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableMillimeter != null) {
                final Tuple<List<String>, ConversionErrorCodes> results =
                        Millimeters.toAll(mEditableMillimeter.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutMillimeter.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutMillimeter.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutMillimeter.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutInch.setErrorEnabled(false);
                                    mTextInputLayoutFoot.setErrorEnabled(false);
                                    mTextInputLayoutYard.setErrorEnabled(false);
                                    mTextInputLayoutMile.setErrorEnabled(false);
                                    mTextInputLayoutMillimeter.setErrorEnabled(false);
                                    mTextInputLayoutCentimeter.setErrorEnabled(false);
                                    mTextInputLayoutMeter.setErrorEnabled(false);
                                    mTextInputLayoutKilometer.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextInch.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextFoot.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextYard.setText(results.getValue0().get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMile.setText(results.getValue0().get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextCentimeter.setText(results.getValue0().get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMeter.setText(results.getValue0().get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKilometer.setText(results.getValue0().get(6),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromCentimeterRunnable implements Runnable {
        private final String TAG = ConversionFromCentimeterRunnable.class.getSimpleName();

        private Editable mEditableCentimeter;
        private String mCallingClassName;

        public ConversionFromCentimeterRunnable(Editable editableCentimeter,
                                                String callingClassName) {
            mEditableCentimeter = editableCentimeter;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableCentimeter != null) {
                final Tuple<List<String>, ConversionErrorCodes> results =
                        Centimeters.toAll(mEditableCentimeter.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutCentimeter.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutCentimeter.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutCentimeter.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutInch.setErrorEnabled(false);
                                    mTextInputLayoutFoot.setErrorEnabled(false);
                                    mTextInputLayoutYard.setErrorEnabled(false);
                                    mTextInputLayoutMile.setErrorEnabled(false);
                                    mTextInputLayoutMillimeter.setErrorEnabled(false);
                                    mTextInputLayoutCentimeter.setErrorEnabled(false);
                                    mTextInputLayoutMeter.setErrorEnabled(false);
                                    mTextInputLayoutKilometer.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextInch.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextFoot.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextYard.setText(results.getValue0().get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMile.setText(results.getValue0().get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMillimeter.setText(results.getValue0().get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMeter.setText(results.getValue0().get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKilometer.setText(results.getValue0().get(6),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromMeterRunnable implements Runnable {
        private final String TAG = ConversionFromMeterRunnable.class.getSimpleName();

        private Editable mEditableMeter;
        private String mCallingClassName;

        public ConversionFromMeterRunnable(Editable editableMeter, String callingClassName) {
            mEditableMeter = editableMeter;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableMeter != null) {
                final Tuple<List<String>, ConversionErrorCodes> results =
                        Meters.toAll(mEditableMeter.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutMeter.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutMeter.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutMeter.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutInch.setErrorEnabled(false);
                                    mTextInputLayoutFoot.setErrorEnabled(false);
                                    mTextInputLayoutYard.setErrorEnabled(false);
                                    mTextInputLayoutMile.setErrorEnabled(false);
                                    mTextInputLayoutMillimeter.setErrorEnabled(false);
                                    mTextInputLayoutCentimeter.setErrorEnabled(false);
                                    mTextInputLayoutMeter.setErrorEnabled(false);
                                    mTextInputLayoutKilometer.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextInch.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextFoot.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextYard.setText(results.getValue0().get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMile.setText(results.getValue0().get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMillimeter.setText(results.getValue0().get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextCentimeter.setText(results.getValue0().get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextKilometer.setText(results.getValue0().get(6),
                                    AppCompatTextView.BufferType.EDITABLE);

                            addTextChangedListeners(TAG + "." + mCallingClassName);
                        }
                    });
                }
            }
        }
    }

    private class ConversionFromKilometerRunnable implements Runnable {
        private final String TAG = ConversionFromKilometerRunnable.class.getSimpleName();

        private Editable mEditableKilometer;
        private String mCallingClassName;

        public ConversionFromKilometerRunnable(Editable editableKilometer,
                                               String callingClassName) {
            mEditableKilometer = editableKilometer;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableKilometer != null) {
                final Tuple<List<String>, ConversionErrorCodes> results =
                        Kilometers.toAll(mEditableKilometer.toString(), getNumOfDecimalPlaces());

                if (results != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            removeTextChangedListeners(TAG + "." + mCallingClassName);

                            switch (results.getValue1()) {
                                case ERROR_BELOW_ZERO:
                                    mTextInputLayoutKilometer.setError(getString(
                                            R.string.conversion_error_below_zero
                                    ));
                                    break;
                                case ERROR_INPUT_NOT_NUMERIC:
                                    mTextInputLayoutKilometer.setError(getString(
                                            R.string.conversion_error_input_not_numeric
                                    ));
                                    break;
                                case ERROR_UNKNOWN:
                                    mTextInputLayoutKilometer.setError(getString(
                                            R.string.conversion_error_conversion_error
                                    ));
                                    break;
                                default:
                                    mTextInputLayoutInch.setErrorEnabled(false);
                                    mTextInputLayoutFoot.setErrorEnabled(false);
                                    mTextInputLayoutYard.setErrorEnabled(false);
                                    mTextInputLayoutMile.setErrorEnabled(false);
                                    mTextInputLayoutMillimeter.setErrorEnabled(false);
                                    mTextInputLayoutCentimeter.setErrorEnabled(false);
                                    mTextInputLayoutMeter.setErrorEnabled(false);
                                    mTextInputLayoutKilometer.setErrorEnabled(false);
                                    break;
                            }

                            mEditTextInch.setText(results.getValue0().get(0),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextFoot.setText(results.getValue0().get(1),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextYard.setText(results.getValue0().get(2),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMile.setText(results.getValue0().get(3),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMillimeter.setText(results.getValue0().get(4),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextCentimeter.setText(results.getValue0().get(5),
                                    AppCompatTextView.BufferType.EDITABLE);
                            mEditTextMeter.setText(results.getValue0().get(6),
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
