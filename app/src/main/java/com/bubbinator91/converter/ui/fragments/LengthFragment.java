package com.bubbinator91.converter.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bubbinator91.converter.conversion.util.ConversionErrorCodes;
import com.bubbinator91.converter.R;
import com.bubbinator91.converter.dagger.components.DaggerFragmentInjectorComponent;
import com.bubbinator91.converter.interfaces.presenter.ILengthPresenter;
import com.bubbinator91.converter.interfaces.view.ILengthView;
import com.bubbinator91.converter.util.SimpleTextWatcher;
import com.bubbinator91.converter.util.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Inch, Foot, Yard, Mile, Millimeter, Centimeter, Meter, Kilometer
 * Conversions comply with the conversions through Google.com
 */

public class LengthFragment
        extends BaseFragment<ILengthPresenter>
        implements ILengthView {
    private final String TAG = LengthFragment.class.getSimpleName();

    private static final int LAST_EDIT_TEXT_FOCUSED_INCH = 0;
    private static final int LAST_EDIT_TEXT_FOCUSED_FOOT = 1;
    private static final int LAST_EDIT_TEXT_FOCUSED_YARD = 2;
    private static final int LAST_EDIT_TEXT_FOCUSED_MILE = 3;
    private static final int LAST_EDIT_TEXT_FOCUSED_MILLIMETER = 4;
    private static final int LAST_EDIT_TEXT_FOCUSED_CENTIMETER = 5;
    private static final int LAST_EDIT_TEXT_FOCUSED_METER = 6;
    private static final int LAST_EDIT_TEXT_FOCUSED_KILOMETER = 7;

    private static final String INCH_VALUE_PERSIST_KEY = "LENGTH_FRAGMENT_INCH_VALUE";
    private static final String FOOT_VALUE_PERSIST_KEY = "LENGTH_FRAGMENT_FOOT_VALUE";
    private static final String YARD_VALUE_PERSIST_KEY = "LENGTH_FRAGMENT_YARD_VALUE";
    private static final String MILE_VALUE_PERSIST_KEY = "LENGTH_FRAGMENT_MILE_VALUE";
    private static final String MILLIMETER_VALUE_PERSIST_KEY = "LENGTH_FRAGMENT_MILLIMETER_VALUE";
    private static final String CENTIMETER_VALUE_PERSIST_KEY = "LENGTH_FRAGMENT_CENTIMETER_VALUE";
    private static final String METER_VALUE_PERSIST_KEY = "LENGTH_FRAGMENT_METER_VALUE";
    private static final String KILOMETER_VALUE_PERSIST_KEY = "LENGTH_FRAGMENT_KILOMETER_VALUE";
    private static final String LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY = "LENGTH_FRAGMENT_LETF_VALUE";

    private SimpleTextWatcher mTextWatcherInch, mTextWatcherFoot, mTextWatcherYard,
            mTextWatcherMile, mTextWatcherMillimeter, mTextWatcherCentimeter, mTextWatcherMeter,
            mTextWatcherKilometer;

    private int mLastEditTextFocused;
    
    @Inject ILengthPresenter mLengthPresenter;

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
    
    // region Lifecycle methods

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Timber.tag(TAG + ".onCreateView").i("Entered");

        if (getRootView() != null) {
            ButterKnife.bind(this, getRootView());

            mTextWatcherInch = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_INCH;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherInch");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherInch");
                        getPresenter().getConversionFromInchesResults(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherFoot = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_FOOT;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherFoot");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherFoot");
                        getPresenter().getConversionFromFeetResults(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherYard = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_YARD;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherYard");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherYard");
                        getPresenter().getConversionFromYardsResults(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherMile = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_MILE;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherMile");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherMile");
                        getPresenter().getConversionFromMilesResults(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherMillimeter = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_MILLIMETER;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherMillimeter");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherMillimeter");
                        getPresenter().getConversionFromMillimetersResults(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherCentimeter = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_CENTIMETER;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherCentimeter");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherCentimeter");
                        getPresenter().getConversionFromCentimetersResults(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherMeter = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_METER;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherMeter");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherMeter");
                        getPresenter().getConversionFromMetersResults(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherKilometer = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_KILOMETER;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherKilometer");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherKilometer");
                        getPresenter().getConversionFromKilometersResults(
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
            if ((getSharedPreferences().getString(INCH_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(FOOT_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(YARD_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(MILE_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(MILLIMETER_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(CENTIMETER_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(METER_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(KILOMETER_VALUE_PERSIST_KEY, null) != null)) {
                mEditTextInch.setText(getSharedPreferences().getString(INCH_VALUE_PERSIST_KEY, ""));
                mEditTextFoot.setText(getSharedPreferences().getString(FOOT_VALUE_PERSIST_KEY, ""));
                mEditTextYard.setText(getSharedPreferences().getString(YARD_VALUE_PERSIST_KEY, ""));
                mEditTextMile.setText(getSharedPreferences().getString(MILE_VALUE_PERSIST_KEY, ""));
                mEditTextMillimeter.setText(getSharedPreferences().getString(MILLIMETER_VALUE_PERSIST_KEY, ""));
                mEditTextCentimeter.setText(getSharedPreferences().getString(CENTIMETER_VALUE_PERSIST_KEY, ""));
                mEditTextMeter.setText(getSharedPreferences().getString(METER_VALUE_PERSIST_KEY, ""));
                mEditTextKilometer.setText(getSharedPreferences().getString(KILOMETER_VALUE_PERSIST_KEY, ""));
            }
            if (getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, -1) != -1) {
                mLastEditTextFocused = getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, 0);
            }
        }

        if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_INCH) {
            if (mEditTextInch.getText() != null) {
                Utils.sanitizeEditable(mEditTextInch.getText());
                getPresenter().getConversionFromInchesResults(
                        mEditTextInch.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_FOOT) {
            if (mEditTextFoot.getText() != null) {
                Utils.sanitizeEditable(mEditTextFoot.getText());
                getPresenter().getConversionFromFeetResults(
                        mEditTextFoot.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_YARD) {
            if (mEditTextYard.getText() != null) {
                Utils.sanitizeEditable(mEditTextYard.getText());
                getPresenter().getConversionFromYardsResults(
                        mEditTextYard.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_MILE) {
            if (mEditTextMile.getText() != null) {
                Utils.sanitizeEditable(mEditTextMile.getText());
                getPresenter().getConversionFromMilesResults(
                        mEditTextMile.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_MILLIMETER) {
            if (mEditTextMillimeter.getText() != null) {
                Utils.sanitizeEditable(mEditTextMillimeter.getText());
                getPresenter().getConversionFromMillimetersResults(
                        mEditTextMillimeter.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_CENTIMETER) {
            if (mEditTextCentimeter.getText() != null) {
                Utils.sanitizeEditable(mEditTextCentimeter.getText());
                getPresenter().getConversionFromCentimetersResults(
                        mEditTextCentimeter.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_METER) {
            if (mEditTextMeter.getText() != null) {
                Utils.sanitizeEditable(mEditTextMeter.getText());
                getPresenter().getConversionFromMetersResults(
                        mEditTextMeter.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_KILOMETER) {
            if (mEditTextKilometer.getText() != null) {
                Utils.sanitizeEditable(mEditTextKilometer.getText());
                getPresenter().getConversionFromKilometersResults(
                        mEditTextKilometer.getText().toString(),
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
                    .putString(INCH_VALUE_PERSIST_KEY, mEditTextInch.getText().toString())
                    .putString(FOOT_VALUE_PERSIST_KEY, mEditTextFoot.getText().toString())
                    .putString(YARD_VALUE_PERSIST_KEY, mEditTextYard.getText().toString())
                    .putString(MILE_VALUE_PERSIST_KEY, mEditTextMile.getText().toString())
                    .putString(MILLIMETER_VALUE_PERSIST_KEY, mEditTextMillimeter.getText().toString())
                    .putString(CENTIMETER_VALUE_PERSIST_KEY, mEditTextCentimeter.getText().toString())
                    .putString(METER_VALUE_PERSIST_KEY, mEditTextMeter.getText().toString())
                    .putString(KILOMETER_VALUE_PERSIST_KEY, mEditTextKilometer.getText().toString())
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

    // region Overridden ILengthView methods
    
    @Override
    public void displayConversionFromCentimetersResults(List<String> results, int errorCode) {
        Timber.tag(TAG + ".displayConversionFromCentimetersResults").i("Entered");

        if (results != null) {
            removeTextChangedListeners(".displayConversionFromCentimetersResults");

            switch (errorCode) {
                case ConversionErrorCodes.ERROR_BELOW_ZERO:
                    mTextInputLayoutCentimeter.setError(getString(
                            R.string.conversion_error_below_zero
                    ));
                    break;
                case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                    mTextInputLayoutCentimeter.setError(getString(
                            R.string.conversion_error_input_not_numeric
                    ));
                    break;
                case ConversionErrorCodes.ERROR_UNKNOWN:
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

            mEditTextInch.setText(results.get(0),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextFoot.setText(results.get(1),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextYard.setText(results.get(2),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMile.setText(results.get(3),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMillimeter.setText(results.get(4),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMeter.setText(results.get(5),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextKilometer.setText(results.get(6),
                    AppCompatTextView.BufferType.EDITABLE);

            addTextChangedListeners(".displayConversionFromCentimetersResults");
        }
    }

    @Override
    public void displayConversionFromFeetResults(List<String> results, int errorCode) {
        Timber.tag(TAG + ".displayConversionFromFeetResults").i("Entered");

        if (results != null) {
            removeTextChangedListeners(".displayConversionFromFeetResults");

            switch (errorCode) {
                case ConversionErrorCodes.ERROR_BELOW_ZERO:
                    mTextInputLayoutFoot.setError(getString(
                            R.string.conversion_error_below_zero
                    ));
                    break;
                case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                    mTextInputLayoutFoot.setError(getString(
                            R.string.conversion_error_input_not_numeric
                    ));
                    break;
                case ConversionErrorCodes.ERROR_UNKNOWN:
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

            mEditTextInch.setText(results.get(0),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextYard.setText(results.get(1),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMile.setText(results.get(2),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMillimeter.setText(results.get(3),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextCentimeter.setText(results.get(4),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMeter.setText(results.get(5),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextKilometer.setText(results.get(6),
                    AppCompatTextView.BufferType.EDITABLE);

            addTextChangedListeners(".displayConversionFromFeetResults");
        }
    }

    @Override
    public void displayConversionFromInchesResults(List<String> results, int errorCode) {
        Timber.tag(TAG + ".displayConversionFromInchesResults").i("Entered");

        if (results != null) {
            removeTextChangedListeners(".displayConversionFromInchesResults");

            switch (errorCode) {
                case ConversionErrorCodes.ERROR_BELOW_ZERO:
                    mTextInputLayoutInch.setError(getString(
                            R.string.conversion_error_below_zero
                    ));
                    break;
                case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                    mTextInputLayoutInch.setError(getString(
                            R.string.conversion_error_input_not_numeric
                    ));
                    break;
                case ConversionErrorCodes.ERROR_UNKNOWN:
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

            mEditTextFoot.setText(results.get(0),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextYard.setText(results.get(1),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMile.setText(results.get(2),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMillimeter.setText(results.get(3),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextCentimeter.setText(results.get(4),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMeter.setText(results.get(5),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextKilometer.setText(results.get(6),
                    AppCompatTextView.BufferType.EDITABLE);

            addTextChangedListeners(".displayConversionFromInchesResults");
        }
    }

    @Override
    public void displayConversionFromKilometersResults(List<String> results, int errorCode) {
        Timber.tag(TAG + ".displayConversionFromKilometersResults").i("Entered");

        if (results != null) {
            removeTextChangedListeners(".displayConversionFromKilometersResults");

            switch (errorCode) {
                case ConversionErrorCodes.ERROR_BELOW_ZERO:
                    mTextInputLayoutKilometer.setError(getString(
                            R.string.conversion_error_below_zero
                    ));
                    break;
                case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                    mTextInputLayoutKilometer.setError(getString(
                            R.string.conversion_error_input_not_numeric
                    ));
                    break;
                case ConversionErrorCodes.ERROR_UNKNOWN:
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

            mEditTextInch.setText(results.get(0),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextFoot.setText(results.get(1),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextYard.setText(results.get(2),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMile.setText(results.get(3),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMillimeter.setText(results.get(4),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextCentimeter.setText(results.get(5),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMeter.setText(results.get(6),
                    AppCompatTextView.BufferType.EDITABLE);

            addTextChangedListeners(".displayConversionFromKilometersResults");
        }
    }

    @Override
    public void displayConversionFromMetersResults(List<String> results, int errorCode) {
        Timber.tag(TAG + ".displayConversionFromMetersResults").i("Entered");

        if (results != null) {
            removeTextChangedListeners(".displayConversionFromMetersResults");

            switch (errorCode) {
                case ConversionErrorCodes.ERROR_BELOW_ZERO:
                    mTextInputLayoutMeter.setError(getString(
                            R.string.conversion_error_below_zero
                    ));
                    break;
                case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                    mTextInputLayoutMeter.setError(getString(
                            R.string.conversion_error_input_not_numeric
                    ));
                    break;
                case ConversionErrorCodes.ERROR_UNKNOWN:
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

            mEditTextInch.setText(results.get(0),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextFoot.setText(results.get(1),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextYard.setText(results.get(2),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMile.setText(results.get(3),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMillimeter.setText(results.get(4),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextCentimeter.setText(results.get(5),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextKilometer.setText(results.get(6),
                    AppCompatTextView.BufferType.EDITABLE);

            addTextChangedListeners(".displayConversionFromMetersResults");
        }
    }

    @Override
    public void displayConversionFromMilesResults(List<String> results, int errorCode) {
        Timber.tag(TAG + ".displayConversionFromMilesResults").i("Entered");

        if (results != null) {
            removeTextChangedListeners(".displayConversionFromMilesResults");

            switch (errorCode) {
                case ConversionErrorCodes.ERROR_BELOW_ZERO:
                    mTextInputLayoutMile.setError(getString(
                            R.string.conversion_error_below_zero
                    ));
                    break;
                case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                    mTextInputLayoutMile.setError(getString(
                            R.string.conversion_error_input_not_numeric
                    ));
                    break;
                case ConversionErrorCodes.ERROR_UNKNOWN:
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

            mEditTextInch.setText(results.get(0),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextFoot.setText(results.get(1),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextYard.setText(results.get(2),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMillimeter.setText(results.get(3),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextCentimeter.setText(results.get(4),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMeter.setText(results.get(5),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextKilometer.setText(results.get(6),
                    AppCompatTextView.BufferType.EDITABLE);

            addTextChangedListeners(".displayConversionFromMilesResults");
        }
    }

    @Override
    public void displayConversionFromMillimetersResults(List<String> results, int errorCode) {
        Timber.tag(TAG + ".displayConversionFromMillimetersResults").i("Entered");

        if (results != null) {
            removeTextChangedListeners(".displayConversionFromMillimetersResults");

            switch (errorCode) {
                case ConversionErrorCodes.ERROR_BELOW_ZERO:
                    mTextInputLayoutMillimeter.setError(getString(
                            R.string.conversion_error_below_zero
                    ));
                    break;
                case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                    mTextInputLayoutMillimeter.setError(getString(
                            R.string.conversion_error_input_not_numeric
                    ));
                    break;
                case ConversionErrorCodes.ERROR_UNKNOWN:
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

            mEditTextInch.setText(results.get(0),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextFoot.setText(results.get(1),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextYard.setText(results.get(2),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMile.setText(results.get(3),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextCentimeter.setText(results.get(4),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMeter.setText(results.get(5),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextKilometer.setText(results.get(6),
                    AppCompatTextView.BufferType.EDITABLE);

            addTextChangedListeners(".displayConversionFromMillimetersResults");
        }
    }

    @Override
    public void displayConversionFromYardsResults(List<String> results, int errorCode) {
        Timber.tag(TAG + ".displayConversionFromYardsResults").i("Entered");

        if (results != null) {
            removeTextChangedListeners(".displayConversionFromYardsResults");

            switch (errorCode) {
                case ConversionErrorCodes.ERROR_BELOW_ZERO:
                    mTextInputLayoutYard.setError(getString(
                            R.string.conversion_error_below_zero
                    ));
                    break;
                case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                    mTextInputLayoutYard.setError(getString(
                            R.string.conversion_error_input_not_numeric
                    ));
                    break;
                case ConversionErrorCodes.ERROR_UNKNOWN:
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

            mEditTextInch.setText(results.get(0),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextFoot.setText(results.get(1),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMile.setText(results.get(2),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMillimeter.setText(results.get(3),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextCentimeter.setText(results.get(4),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMeter.setText(results.get(5),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextKilometer.setText(results.get(6),
                    AppCompatTextView.BufferType.EDITABLE);

            addTextChangedListeners(".displayConversionFromYardsResults");
        }
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

        mEditTextInch.addTextChangedListener(mTextWatcherInch);
        mEditTextFoot.addTextChangedListener(mTextWatcherFoot);
        mEditTextYard.addTextChangedListener(mTextWatcherYard);
        mEditTextMile.addTextChangedListener(mTextWatcherMile);
        mEditTextMillimeter.addTextChangedListener(mTextWatcherMillimeter);
        mEditTextCentimeter.addTextChangedListener(mTextWatcherCentimeter);
        mEditTextMeter.addTextChangedListener(mTextWatcherMeter);
        mEditTextKilometer.addTextChangedListener(mTextWatcherKilometer);
    }

    @Override
    public void removeTextChangedListeners(String callingClassName) {
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

    @Override
    protected ILengthPresenter getPresenter() {
        if (mLengthPresenter == null) {
            DaggerFragmentInjectorComponent
                    .create()
                    .inject(this);
        }
        
        return mLengthPresenter;
    }

    @Override
    protected void registerViewWithPresenter() {
        getPresenter().registerView(this);
    }

    // endregion
}
