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
import com.bubbinator91.converter.conversion.length.Centimeter;
import com.bubbinator91.converter.conversion.length.Foot;
import com.bubbinator91.converter.conversion.length.Inch;
import com.bubbinator91.converter.conversion.length.Kilometer;
import com.bubbinator91.converter.conversion.length.Meter;
import com.bubbinator91.converter.conversion.length.Mile;
import com.bubbinator91.converter.conversion.length.Millimeter;
import com.bubbinator91.converter.conversion.length.Yard;
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

    private AppCompatEditText editTextInch, editTextFoot, editTextYard, editTextMile,
            editTextMillimeter, editTextCentimeter, editTextMeter, editTextKilometer;

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
            editTextInch =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_length_inch));
            editTextFoot =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_length_foot));
            editTextYard =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_length_yard));
            editTextMile =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_length_mile));
            editTextMillimeter =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_length_millimeter));
            editTextCentimeter =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_length_centimeter));
            editTextMeter =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_length_meter));
            editTextKilometer =
                    ((AppCompatEditText) getRootView().findViewById(R.id.editText_length_kilometer));

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
                new Thread(new ConversionFromInchRunnable(editTextInch.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.FOOT) {
            if ((getHandler() != null) && (editTextFoot.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextFoot.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromFootRunnable(editTextFoot.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.YARD) {
            if ((getHandler() != null) && (editTextYard.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextYard.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromYardRunnable(editTextYard.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.MILE) {
            if ((getHandler() != null) && (editTextMile.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextMile.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMileRunnable(editTextMile.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.MILLIMETER) {
            if ((getHandler() != null) && (editTextMillimeter.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextMillimeter.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMillimeterRunnable(editTextMillimeter.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.CENTIMETER) {
            if ((getHandler() != null) && (editTextCentimeter.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextCentimeter.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromCentimeterRunnable(editTextCentimeter.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.METER) {
            if ((getHandler() != null) && (editTextMeter.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextMeter.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMeterRunnable(editTextMeter.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.KILOMETER) {
            if ((getHandler() != null) && (editTextKilometer.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextKilometer.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromKilometerRunnable(editTextKilometer.getText(),
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

        public ConversionFromInchRunnable(@NonNull Editable editableInch,
                                          @NonNull String callingClassName) {
            mEditableInch = editableInch;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableInch != null) {
                final List<String> results =
                        Inch.toAll(mEditableInch.toString(), getNumOfDecimalPlaces());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        editTextFoot.setText(results.get(0),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextYard.setText(results.get(1),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMile.setText(results.get(2),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMillimeter.setText(results.get(3),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextCentimeter.setText(results.get(4),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMeter.setText(results.get(5),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextKilometer.setText(results.get(6),
                                AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromFootRunnable implements Runnable {
        private final String TAG = "ConversionFromFootRunnable";

        private Editable mEditableFoot;
        private String mCallingClassName;

        public ConversionFromFootRunnable(@NonNull Editable editableFoot,
                                          @NonNull String callingClassName) {
            mEditableFoot = editableFoot;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableFoot != null) {
                final List<String> results =
                        Foot.toAll(mEditableFoot.toString(), getNumOfDecimalPlaces());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        editTextInch.setText(results.get(0),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextYard.setText(results.get(1),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMile.setText(results.get(2),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMillimeter.setText(results.get(3),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextCentimeter.setText(results.get(4),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMeter.setText(results.get(5),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextKilometer.setText(results.get(6),
                                AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromYardRunnable implements Runnable {
        private final String TAG = "ConversionFromYardRunnable";

        private Editable mEditableYard;
        private String mCallingClassName;

        public ConversionFromYardRunnable(@NonNull Editable editableYard,
                                          @NonNull String callingClassName) {
            mEditableYard = editableYard;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableYard != null) {
                final List<String> results =
                        Yard.toAll(mEditableYard.toString(), getNumOfDecimalPlaces());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        editTextInch.setText(results.get(0),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextFoot.setText(results.get(1),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMile.setText(results.get(2),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMillimeter.setText(results.get(3),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextCentimeter.setText(results.get(4),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMeter.setText(results.get(5),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextKilometer.setText(results.get(6),
                                AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromMileRunnable implements Runnable {
        private final String TAG = "ConversionFromMileRunnable";

        private Editable mEditableMile;
        private String mCallingClassName;

        public ConversionFromMileRunnable(@NonNull Editable editableMile,
                                          @NonNull String callingClassName) {
            mEditableMile = editableMile;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableMile != null) {
                final List<String> results =
                        Mile.toAll(mEditableMile.toString(), getNumOfDecimalPlaces());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        editTextInch.setText(results.get(0),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextFoot.setText(results.get(1),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextYard.setText(results.get(2),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMillimeter.setText(results.get(3),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextCentimeter.setText(results.get(4),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMeter.setText(results.get(5),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextKilometer.setText(results.get(6),
                                AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromMillimeterRunnable implements Runnable {
        private final String TAG = "ConversionFromMillimeterRunnable";

        private Editable mEditableMillimeter;
        private String mCallingClassName;

        public ConversionFromMillimeterRunnable(@NonNull Editable editableMillimeter,
                                                @NonNull String callingClassName) {
            mEditableMillimeter = editableMillimeter;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableMillimeter != null) {
                final List<String> results =
                        Millimeter.toAll(mEditableMillimeter.toString(), getNumOfDecimalPlaces());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        editTextInch.setText(results.get(0),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextFoot.setText(results.get(1),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextYard.setText(results.get(2),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMile.setText(results.get(3),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextCentimeter.setText(results.get(4),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMeter.setText(results.get(5),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextKilometer.setText(results.get(6),
                                AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromCentimeterRunnable implements Runnable {
        private final String TAG = "ConversionFromCentimeterRunnable";

        private Editable mEditableCentimeter;
        private String mCallingClassName;

        public ConversionFromCentimeterRunnable(@NonNull Editable editableCentimeter,
                                                @NonNull String callingClassName) {
            mEditableCentimeter = editableCentimeter;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableCentimeter != null) {
                final List<String> results =
                        Centimeter.toAll(mEditableCentimeter.toString(), getNumOfDecimalPlaces());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        editTextInch.setText(results.get(0),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextFoot.setText(results.get(1),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextYard.setText(results.get(2),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMile.setText(results.get(3),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMillimeter.setText(results.get(4),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMeter.setText(results.get(5),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextKilometer.setText(results.get(6),
                                AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromMeterRunnable implements Runnable {
        private final String TAG = "ConversionFromMeterRunnable";

        private Editable mEditableMeter;
        private String mCallingClassName;

        public ConversionFromMeterRunnable(@NonNull Editable editableMeter,
                                           @NonNull String callingClassName) {
            mEditableMeter = editableMeter;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableMeter != null) {
                final List<String> results =
                        Meter.toAll(mEditableMeter.toString(), getNumOfDecimalPlaces());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        editTextInch.setText(results.get(0),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextFoot.setText(results.get(1),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextYard.setText(results.get(2),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMile.setText(results.get(3),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMillimeter.setText(results.get(4),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextCentimeter.setText(results.get(5),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextKilometer.setText(results.get(6),
                                AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromKilometerRunnable implements Runnable {
        private final String TAG = "ConversionFromKilometerRunnable";

        private Editable mEditableKilometer;
        private String mCallingClassName;

        public ConversionFromKilometerRunnable(@NonNull Editable editableKilometer,
                                               @NonNull String callingClassName) {
            mEditableKilometer = editableKilometer;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableKilometer != null) {
                final List<String> results =
                        Kilometer.toAll(mEditableKilometer.toString(), getNumOfDecimalPlaces());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        editTextInch.setText(results.get(0),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextFoot.setText(results.get(1),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextYard.setText(results.get(2),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMile.setText(results.get(3),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMillimeter.setText(results.get(4),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextCentimeter.setText(results.get(5),
                                AppCompatTextView.BufferType.EDITABLE);
                        editTextMeter.setText(results.get(6),
                                AppCompatTextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    // endregion
}
