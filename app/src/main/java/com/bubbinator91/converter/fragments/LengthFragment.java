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
import com.bubbinator91.converter.tasks.length.FromCentimetersTask;
import com.bubbinator91.converter.tasks.length.FromFeetTask;
import com.bubbinator91.converter.tasks.length.FromInchesTask;
import com.bubbinator91.converter.tasks.length.FromKilometersTask;
import com.bubbinator91.converter.tasks.length.FromMetersTask;
import com.bubbinator91.converter.tasks.length.FromMilesTask;
import com.bubbinator91.converter.tasks.length.FromMillimetersTask;
import com.bubbinator91.converter.tasks.length.FromYardsTask;
import com.bubbinator91.converter.util.Utils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
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

    @Bind(R.id.editText_length_inch)        AppCompatEditText mEditTextInch;
    @Bind(R.id.editText_length_foot)        AppCompatEditText mEditTextFoot;
    @Bind(R.id.editText_length_yard)        AppCompatEditText mEditTextYard;
    @Bind(R.id.editText_length_mile)        AppCompatEditText mEditTextMile;
    @Bind(R.id.editText_length_millimeter)  AppCompatEditText mEditTextMillimeter;
    @Bind(R.id.editText_length_centimeter)  AppCompatEditText mEditTextCentimeter;
    @Bind(R.id.editText_length_meter)       AppCompatEditText mEditTextMeter;
    @Bind(R.id.editText_length_kilometer)   AppCompatEditText mEditTextKilometer;

    @Bind(R.id.textInputLayout_length_inch)         TextInputLayout mTextInputLayoutInch;
    @Bind(R.id.textInputLayout_length_foot)         TextInputLayout mTextInputLayoutFoot;
    @Bind(R.id.textInputLayout_length_yard)         TextInputLayout mTextInputLayoutYard;
    @Bind(R.id.textInputLayout_length_mile)         TextInputLayout mTextInputLayoutMile;
    @Bind(R.id.textInputLayout_length_millimeter)   TextInputLayout mTextInputLayoutMillimeter;
    @Bind(R.id.textInputLayout_length_centimeter)   TextInputLayout mTextInputLayoutCentimeter;
    @Bind(R.id.textInputLayout_length_meter)        TextInputLayout mTextInputLayoutMeter;
    @Bind(R.id.textInputLayout_length_kilometer)    TextInputLayout mTextInputLayoutKilometer;

    private LastEditTextFocused mLastEditTextFocused;

    // region TextWatchers

    private TextWatcher mTextWatcherInch = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mLastEditTextFocused = LastEditTextFocused.INCH;

            if (s != null) {
                removeTextChangedListeners("mTextWatcherInch");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherInch");
                convertFromInches(s.toString());
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

            if (s != null) {
                removeTextChangedListeners("mTextWatcherFoot");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherFoot");
                convertFromFeet(s.toString());
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

            if (s != null) {
                removeTextChangedListeners("mTextWatcherYard");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherYard");
                convertFromYards(s.toString());
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

            if (s != null) {
                removeTextChangedListeners("mTextWatcherMile");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherMile");
                convertFromMiles(s.toString());
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

            if (s != null) {
                removeTextChangedListeners("mTextWatcherMillimeter");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherMillimeter");
                convertFromMillimeters(s.toString());
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

            if (s != null) {
                removeTextChangedListeners("mTextWatcherCentimeter");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherCentimeter");
                convertFromCentimeters(s.toString());
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

            if (s != null) {
                removeTextChangedListeners("mTextWatcherMeter");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherMeter");
                convertFromMeters(s.toString());
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

            if (s != null) {
                removeTextChangedListeners("mTextWatcherKilometer");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("mTextWatcherKilometer");
                convertFromKilometers(s.toString());
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

        if (mLastEditTextFocused == LastEditTextFocused.INCH) {
            if (mEditTextInch.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextInch.getText());
                addTextChangedListeners("onResume");
                convertFromInches(mEditTextInch.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.FOOT) {
            if (mEditTextFoot.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextFoot.getText());
                addTextChangedListeners("onResume");
                convertFromFeet(mEditTextFoot.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.YARD) {
            if (mEditTextYard.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextYard.getText());
                addTextChangedListeners("onResume");
                convertFromYards(mEditTextYard.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.MILE) {
            if (mEditTextMile.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextMile.getText());
                addTextChangedListeners("onResume");
                convertFromMiles(mEditTextMile.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.MILLIMETER) {
            if (mEditTextMillimeter.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextMillimeter.getText());
                addTextChangedListeners("onResume");
                convertFromMillimeters(mEditTextMillimeter.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.CENTIMETER) {
            if (mEditTextCentimeter.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextCentimeter.getText());
                addTextChangedListeners("onResume");
                convertFromCentimeters(mEditTextCentimeter.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.METER) {
            if (mEditTextMeter.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextMeter.getText());
                addTextChangedListeners("onResume");
                convertFromMeters(mEditTextMeter.getText().toString());
            }
        } else if (mLastEditTextFocused == LastEditTextFocused.KILOMETER) {
            if (mEditTextKilometer.getText() != null) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(mEditTextKilometer.getText());
                addTextChangedListeners("onResume");
                convertFromKilometers(mEditTextKilometer.getText().toString());
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

    private void convertFromCentimeters(String centimeters) {
        String[] params = new String[2];
        params[0] = centimeters;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromCentimetersTask task = new FromCentimetersTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
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

                    mEditTextInch.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextFoot.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextYard.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMile.setText(results.first.get(3),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMillimeter.setText(results.first.get(4),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMeter.setText(results.first.get(5),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextKilometer.setText(results.first.get(6),
                            AppCompatTextView.BufferType.EDITABLE);

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromFeet(String feet) {
        String[] params = new String[2];
        params[0] = feet;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromFeetTask task = new FromFeetTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
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

                    mEditTextInch.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextYard.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMile.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMillimeter.setText(results.first.get(3),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextCentimeter.setText(results.first.get(4),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMeter.setText(results.first.get(5),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextKilometer.setText(results.first.get(6),
                            AppCompatTextView.BufferType.EDITABLE);

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromInches(String inches) {
        String[] params = new String[2];
        params[0] = inches;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromInchesTask task = new FromInchesTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
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

                    mEditTextFoot.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextYard.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMile.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMillimeter.setText(results.first.get(3),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextCentimeter.setText(results.first.get(4),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMeter.setText(results.first.get(5),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextKilometer.setText(results.first.get(6),
                            AppCompatTextView.BufferType.EDITABLE);

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromKilometers(String kilometers) {
        String[] params = new String[2];
        params[0] = kilometers;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromKilometersTask task = new FromKilometersTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
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

                    mEditTextInch.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextFoot.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextYard.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMile.setText(results.first.get(3),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMillimeter.setText(results.first.get(4),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextCentimeter.setText(results.first.get(5),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMeter.setText(results.first.get(6),
                            AppCompatTextView.BufferType.EDITABLE);

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromMeters(String meters) {
        String[] params = new String[2];
        params[0] = meters;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromMetersTask task = new FromMetersTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
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

                    mEditTextInch.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextFoot.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextYard.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMile.setText(results.first.get(3),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMillimeter.setText(results.first.get(4),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextCentimeter.setText(results.first.get(5),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextKilometer.setText(results.first.get(6),
                            AppCompatTextView.BufferType.EDITABLE);

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromMiles(String miles) {
        String[] params = new String[2];
        params[0] = miles;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromMilesTask task = new FromMilesTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
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

                    mEditTextInch.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextFoot.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextYard.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMillimeter.setText(results.first.get(3),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextCentimeter.setText(results.first.get(4),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMeter.setText(results.first.get(5),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextKilometer.setText(results.first.get(6),
                            AppCompatTextView.BufferType.EDITABLE);

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromMillimeters(String millimeters) {
        String[] params = new String[2];
        params[0] = millimeters;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromMillimetersTask task = new FromMillimetersTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
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

                    mEditTextInch.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextFoot.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextYard.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMile.setText(results.first.get(3),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextCentimeter.setText(results.first.get(4),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMeter.setText(results.first.get(5),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextKilometer.setText(results.first.get(6),
                            AppCompatTextView.BufferType.EDITABLE);

                    addTextChangedListeners(TAG + ".onPostExecute");
                }
            }
        };
        task.execute(params);
    }

    private void convertFromYards(String yards) {
        String[] params = new String[2];
        params[0] = yards;
        params[1] = Integer.toString(getNumOfDecimalPlaces());
        FromYardsTask task = new FromYardsTask() {
            @Override
            protected void onPostExecute(Pair<List<String>, ConversionErrorCodes> results) {
                Timber.tag(TAG + ".onPostExecute").i("Entered");

                if (results != null) {
                    removeTextChangedListeners(TAG + ".onPostExecute");

                    switch (results.second) {
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

                    mEditTextInch.setText(results.first.get(0),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextFoot.setText(results.first.get(1),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMile.setText(results.first.get(2),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMillimeter.setText(results.first.get(3),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextCentimeter.setText(results.first.get(4),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextMeter.setText(results.first.get(5),
                            AppCompatTextView.BufferType.EDITABLE);
                    mEditTextKilometer.setText(results.first.get(6),
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
    protected int getLayoutResource() { return R.layout.fragment_length; }

    @Override
    protected int getScrollViewResource() { return  R.id.fragment_length; }

    // endregion
}
