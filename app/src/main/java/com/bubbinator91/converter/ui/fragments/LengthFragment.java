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
                        getPresenter().getConversionFromInches(
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
                        getPresenter().getConversionFromFeet(
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
                        getPresenter().getConversionFromYards(
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
                        getPresenter().getConversionFromMiles(
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
                        getPresenter().getConversionFromMillimeters(
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
                        getPresenter().getConversionFromCentimeters(
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
                        getPresenter().getConversionFromMeters(
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
                        getPresenter().getConversionFromKilometers(
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
                getPresenter().getConversionFromInches(
                        mEditTextInch.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_FOOT) {
            if (mEditTextFoot.getText() != null) {
                Utils.sanitizeEditable(mEditTextFoot.getText());
                getPresenter().getConversionFromFeet(
                        mEditTextFoot.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_YARD) {
            if (mEditTextYard.getText() != null) {
                Utils.sanitizeEditable(mEditTextYard.getText());
                getPresenter().getConversionFromYards(
                        mEditTextYard.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_MILE) {
            if (mEditTextMile.getText() != null) {
                Utils.sanitizeEditable(mEditTextMile.getText());
                getPresenter().getConversionFromMiles(
                        mEditTextMile.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_MILLIMETER) {
            if (mEditTextMillimeter.getText() != null) {
                Utils.sanitizeEditable(mEditTextMillimeter.getText());
                getPresenter().getConversionFromMillimeters(
                        mEditTextMillimeter.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_CENTIMETER) {
            if (mEditTextCentimeter.getText() != null) {
                Utils.sanitizeEditable(mEditTextCentimeter.getText());
                getPresenter().getConversionFromCentimeters(
                        mEditTextCentimeter.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_METER) {
            if (mEditTextMeter.getText() != null) {
                Utils.sanitizeEditable(mEditTextMeter.getText());
                getPresenter().getConversionFromMeters(
                        mEditTextMeter.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_KILOMETER) {
            if (mEditTextKilometer.getText() != null) {
                Utils.sanitizeEditable(mEditTextKilometer.getText());
                getPresenter().getConversionFromKilometers(
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
    public void displayConversionFromInchesResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromInchesResults").i("Entered");
        removeTextChangedListeners("displayConversionFromInchesResults");

        mTextInputLayoutInch.setErrorEnabled(false);
        mTextInputLayoutFoot.setErrorEnabled(false);
        mTextInputLayoutYard.setErrorEnabled(false);
        mTextInputLayoutMile.setErrorEnabled(false);
        mTextInputLayoutMillimeter.setErrorEnabled(false);
        mTextInputLayoutCentimeter.setErrorEnabled(false);
        mTextInputLayoutMeter.setErrorEnabled(false);
        mTextInputLayoutKilometer.setErrorEnabled(false);

        mEditTextFoot.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextYard.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMile.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMillimeter.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        mEditTextCentimeter.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMeter.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKilometer.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromInchesResults");
    }

    @Override
    public void displayConversionFromInchesError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutInch.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutInch.setError(getString(
                    R.string.conversion_error_below_zero
            ));
        } else {
            mTextInputLayoutInch.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromInchesError");
        mEditTextFoot.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextYard.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMile.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMillimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextCentimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKilometer.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromInchesError");
    }

    @Override
    public void displayConversionFromFeetResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromFeetResults").i("Entered");
        removeTextChangedListeners("displayConversionFromFeetResults");

        mTextInputLayoutInch.setErrorEnabled(false);
        mTextInputLayoutFoot.setErrorEnabled(false);
        mTextInputLayoutYard.setErrorEnabled(false);
        mTextInputLayoutMile.setErrorEnabled(false);
        mTextInputLayoutMillimeter.setErrorEnabled(false);
        mTextInputLayoutCentimeter.setErrorEnabled(false);
        mTextInputLayoutMeter.setErrorEnabled(false);
        mTextInputLayoutKilometer.setErrorEnabled(false);

        mEditTextInch.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextYard.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMile.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMillimeter.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        mEditTextCentimeter.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMeter.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKilometer.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromFeetResults");
    }

    @Override
    public void displayConversionFromFeetError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutFoot.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutFoot.setError(getString(
                    R.string.conversion_error_below_zero
            ));
        } else {
            mTextInputLayoutFoot.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromFeetError");
        mEditTextInch.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextYard.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMile.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMillimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextCentimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKilometer.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromFeetError");
    }

    @Override
    public void displayConversionFromYardsResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromYardsResults").i("Entered");
        removeTextChangedListeners("displayConversionFromYardsResults");

        mTextInputLayoutInch.setErrorEnabled(false);
        mTextInputLayoutFoot.setErrorEnabled(false);
        mTextInputLayoutYard.setErrorEnabled(false);
        mTextInputLayoutMile.setErrorEnabled(false);
        mTextInputLayoutMillimeter.setErrorEnabled(false);
        mTextInputLayoutCentimeter.setErrorEnabled(false);
        mTextInputLayoutMeter.setErrorEnabled(false);
        mTextInputLayoutKilometer.setErrorEnabled(false);

        mEditTextInch.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextFoot.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMile.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMillimeter.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        mEditTextCentimeter.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMeter.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKilometer.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromYardsResults");
    }

    @Override
    public void displayConversionFromYardsError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutYard.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutYard.setError(getString(
                    R.string.conversion_error_below_zero
            ));
        } else {
            mTextInputLayoutYard.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromYardsError");
        mEditTextInch.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextFoot.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMile.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMillimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextCentimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKilometer.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromYardsError");
    }

    @Override
    public void displayConversionFromMilesResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromMilesResults").i("Entered");
        removeTextChangedListeners("displayConversionFromMilesResults");

        mTextInputLayoutInch.setErrorEnabled(false);
        mTextInputLayoutFoot.setErrorEnabled(false);
        mTextInputLayoutYard.setErrorEnabled(false);
        mTextInputLayoutMile.setErrorEnabled(false);
        mTextInputLayoutMillimeter.setErrorEnabled(false);
        mTextInputLayoutCentimeter.setErrorEnabled(false);
        mTextInputLayoutMeter.setErrorEnabled(false);
        mTextInputLayoutKilometer.setErrorEnabled(false);

        mEditTextInch.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextFoot.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextYard.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMillimeter.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        mEditTextCentimeter.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMeter.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKilometer.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromMilesResults");
    }

    @Override
    public void displayConversionFromMilesError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutMile.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutMile.setError(getString(
                    R.string.conversion_error_below_zero
            ));
        } else {
            mTextInputLayoutMile.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromMilesError");
        mEditTextInch.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextFoot.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextYard.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMillimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextCentimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKilometer.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromMilesError");
    }

    @Override
    public void displayConversionFromMillimetersResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromMillimetersResults").i("Entered");
        removeTextChangedListeners("displayConversionFromMillimetersResults");

        mTextInputLayoutInch.setErrorEnabled(false);
        mTextInputLayoutFoot.setErrorEnabled(false);
        mTextInputLayoutYard.setErrorEnabled(false);
        mTextInputLayoutMile.setErrorEnabled(false);
        mTextInputLayoutMillimeter.setErrorEnabled(false);
        mTextInputLayoutCentimeter.setErrorEnabled(false);
        mTextInputLayoutMeter.setErrorEnabled(false);
        mTextInputLayoutKilometer.setErrorEnabled(false);

        mEditTextInch.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextFoot.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextYard.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMile.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        mEditTextCentimeter.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMeter.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKilometer.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromMillimetersResults");
    }

    @Override
    public void displayConversionFromMillimetersError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutMillimeter.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutMillimeter.setError(getString(
                    R.string.conversion_error_below_zero
            ));
        } else {
            mTextInputLayoutMillimeter.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromMillimetersError");
        mEditTextInch.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextFoot.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextYard.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMile.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextCentimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKilometer.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromMillimetersError");
    }

    @Override
    public void displayConversionFromCentimetersResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromCentimetersResults").i("Entered");
        removeTextChangedListeners("displayConversionFromCentimetersResults");

        mTextInputLayoutInch.setErrorEnabled(false);
        mTextInputLayoutFoot.setErrorEnabled(false);
        mTextInputLayoutYard.setErrorEnabled(false);
        mTextInputLayoutMile.setErrorEnabled(false);
        mTextInputLayoutMillimeter.setErrorEnabled(false);
        mTextInputLayoutCentimeter.setErrorEnabled(false);
        mTextInputLayoutMeter.setErrorEnabled(false);
        mTextInputLayoutKilometer.setErrorEnabled(false);

        mEditTextInch.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextFoot.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextYard.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMile.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMillimeter.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMeter.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKilometer.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromCentimetersResults");
    }

    @Override
    public void displayConversionFromCentimetersError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutCentimeter.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutCentimeter.setError(getString(
                    R.string.conversion_error_below_zero
            ));
        } else {
            mTextInputLayoutCentimeter.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromCentimetersError");
        mEditTextInch.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextFoot.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextYard.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMile.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMillimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKilometer.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromCentimetersError");
    }

    @Override
    public void displayConversionFromMetersResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromMetersResults").i("Entered");
        removeTextChangedListeners("displayConversionFromMetersResults");

        mTextInputLayoutInch.setErrorEnabled(false);
        mTextInputLayoutFoot.setErrorEnabled(false);
        mTextInputLayoutYard.setErrorEnabled(false);
        mTextInputLayoutMile.setErrorEnabled(false);
        mTextInputLayoutMillimeter.setErrorEnabled(false);
        mTextInputLayoutCentimeter.setErrorEnabled(false);
        mTextInputLayoutMeter.setErrorEnabled(false);
        mTextInputLayoutKilometer.setErrorEnabled(false);

        mEditTextInch.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextFoot.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextYard.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMile.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMillimeter.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        mEditTextCentimeter.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        mEditTextKilometer.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromMetersResults");
    }

    @Override
    public void displayConversionFromMetersError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutMeter.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutMeter.setError(getString(
                    R.string.conversion_error_below_zero
            ));
        } else {
            mTextInputLayoutMeter.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromMetersError");
        mEditTextInch.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextFoot.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextYard.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMile.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMillimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextCentimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextKilometer.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromMetersError");
    }

    @Override
    public void displayConversionFromKilometersResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromKilometersResults").i("Entered");
        removeTextChangedListeners("displayConversionFromKilometersResults");

        mTextInputLayoutInch.setErrorEnabled(false);
        mTextInputLayoutFoot.setErrorEnabled(false);
        mTextInputLayoutYard.setErrorEnabled(false);
        mTextInputLayoutMile.setErrorEnabled(false);
        mTextInputLayoutMillimeter.setErrorEnabled(false);
        mTextInputLayoutCentimeter.setErrorEnabled(false);
        mTextInputLayoutMeter.setErrorEnabled(false);
        mTextInputLayoutKilometer.setErrorEnabled(false);

        mEditTextInch.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        mEditTextFoot.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        mEditTextYard.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMile.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMillimeter.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        mEditTextCentimeter.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        mEditTextMeter.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromKilometersResults");
    }

    @Override
    public void displayConversionFromKilometersError(Throwable error) {
        if (error instanceof NumberFormatException) {
            mTextInputLayoutKilometer.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            mTextInputLayoutKilometer.setError(getString(
                    R.string.conversion_error_below_zero
            ));
        } else {
            mTextInputLayoutKilometer.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromKilometersError");
        mEditTextInch.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextFoot.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextYard.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMile.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMillimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextCentimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        mEditTextMeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromKilometersError");
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
