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
import com.bubbinator91.converter.util.PresenterCache;
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

    private SimpleTextWatcher textWatcherInch, textWatcherFoot, textWatcherYard, textWatcherMile,
            textWatcherMillimeter, textWatcherCentimeter, textWatcherMeter, textWatcherKilometer;
    
    @Inject ILengthPresenter lengthPresenter;

    @Bind(R.id.editText_length_inch)        AppCompatEditText editTextInch;
    @Bind(R.id.editText_length_foot)        AppCompatEditText editTextFoot;
    @Bind(R.id.editText_length_yard)        AppCompatEditText editTextYard;
    @Bind(R.id.editText_length_mile)        AppCompatEditText editTextMile;
    @Bind(R.id.editText_length_millimeter)  AppCompatEditText editTextMillimeter;
    @Bind(R.id.editText_length_centimeter)  AppCompatEditText editTextCentimeter;
    @Bind(R.id.editText_length_meter)       AppCompatEditText editTextMeter;
    @Bind(R.id.editText_length_kilometer)   AppCompatEditText editTextKilometer;

    @Bind(R.id.textInputLayout_length_inch)         TextInputLayout textInputLayoutInch;
    @Bind(R.id.textInputLayout_length_foot)         TextInputLayout textInputLayoutFoot;
    @Bind(R.id.textInputLayout_length_yard)         TextInputLayout textInputLayoutYard;
    @Bind(R.id.textInputLayout_length_mile)         TextInputLayout textInputLayoutMile;
    @Bind(R.id.textInputLayout_length_millimeter)   TextInputLayout textInputLayoutMillimeter;
    @Bind(R.id.textInputLayout_length_centimeter)   TextInputLayout textInputLayoutCentimeter;
    @Bind(R.id.textInputLayout_length_meter)        TextInputLayout textInputLayoutMeter;
    @Bind(R.id.textInputLayout_length_kilometer)    TextInputLayout textInputLayoutKilometer;
    
    // region Lifecycle methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            lengthPresenter = PresenterCache.getInstance().restorePresenter(savedInstanceState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Timber.tag(TAG + ".onCreateView").i("Entered");

        if (getRootView() != null) {
            ButterKnife.bind(this, getRootView());

            textWatcherInch = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_INCH);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherInch");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherInch");
                        getPresenter().getConversionFromInches(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherFoot = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_FOOT);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherFoot");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherFoot");
                        getPresenter().getConversionFromFeet(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherYard = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_YARD);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherYard");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherYard");
                        getPresenter().getConversionFromYards(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherMile = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_MILE);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherMile");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherMile");
                        getPresenter().getConversionFromMiles(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherMillimeter = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_MILLIMETER);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherMillimeter");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherMillimeter");
                        getPresenter().getConversionFromMillimeters(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherCentimeter = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_CENTIMETER);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherCentimeter");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherCentimeter");
                        getPresenter().getConversionFromCentimeters(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherMeter = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_METER);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherMeter");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherMeter");
                        getPresenter().getConversionFromMeters(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherKilometer = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_KILOMETER);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherKilometer");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherKilometer");
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
                editTextInch.setText(getSharedPreferences().getString(INCH_VALUE_PERSIST_KEY, ""));
                editTextFoot.setText(getSharedPreferences().getString(FOOT_VALUE_PERSIST_KEY, ""));
                editTextYard.setText(getSharedPreferences().getString(YARD_VALUE_PERSIST_KEY, ""));
                editTextMile.setText(getSharedPreferences().getString(MILE_VALUE_PERSIST_KEY, ""));
                editTextMillimeter.setText(getSharedPreferences().getString(MILLIMETER_VALUE_PERSIST_KEY, ""));
                editTextCentimeter.setText(getSharedPreferences().getString(CENTIMETER_VALUE_PERSIST_KEY, ""));
                editTextMeter.setText(getSharedPreferences().getString(METER_VALUE_PERSIST_KEY, ""));
                editTextKilometer.setText(getSharedPreferences().getString(KILOMETER_VALUE_PERSIST_KEY, ""));
            }
            if (getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, -1) != -1) {
                setLastEditTextFocused(getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, 0));
            }
        }

        if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_INCH) {
            if (editTextInch.getText() != null) {
                Utils.sanitizeEditable(editTextInch.getText());
                getPresenter().getConversionFromInches(
                        editTextInch.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_FOOT) {
            if (editTextFoot.getText() != null) {
                Utils.sanitizeEditable(editTextFoot.getText());
                getPresenter().getConversionFromFeet(
                        editTextFoot.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_YARD) {
            if (editTextYard.getText() != null) {
                Utils.sanitizeEditable(editTextYard.getText());
                getPresenter().getConversionFromYards(
                        editTextYard.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_MILE) {
            if (editTextMile.getText() != null) {
                Utils.sanitizeEditable(editTextMile.getText());
                getPresenter().getConversionFromMiles(
                        editTextMile.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_MILLIMETER) {
            if (editTextMillimeter.getText() != null) {
                Utils.sanitizeEditable(editTextMillimeter.getText());
                getPresenter().getConversionFromMillimeters(
                        editTextMillimeter.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_CENTIMETER) {
            if (editTextCentimeter.getText() != null) {
                Utils.sanitizeEditable(editTextCentimeter.getText());
                getPresenter().getConversionFromCentimeters(
                        editTextCentimeter.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_METER) {
            if (editTextMeter.getText() != null) {
                Utils.sanitizeEditable(editTextMeter.getText());
                getPresenter().getConversionFromMeters(
                        editTextMeter.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_KILOMETER) {
            if (editTextKilometer.getText() != null) {
                Utils.sanitizeEditable(editTextKilometer.getText());
                getPresenter().getConversionFromKilometers(
                        editTextKilometer.getText().toString(),
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
                    .putString(INCH_VALUE_PERSIST_KEY, editTextInch.getText().toString())
                    .putString(FOOT_VALUE_PERSIST_KEY, editTextFoot.getText().toString())
                    .putString(YARD_VALUE_PERSIST_KEY, editTextYard.getText().toString())
                    .putString(MILE_VALUE_PERSIST_KEY, editTextMile.getText().toString())
                    .putString(MILLIMETER_VALUE_PERSIST_KEY, editTextMillimeter.getText().toString())
                    .putString(CENTIMETER_VALUE_PERSIST_KEY, editTextCentimeter.getText().toString())
                    .putString(METER_VALUE_PERSIST_KEY, editTextMeter.getText().toString())
                    .putString(KILOMETER_VALUE_PERSIST_KEY, editTextKilometer.getText().toString())
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

    // region Overridden ILengthView methods

    @Override
    public void displayConversionFromInchesResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromInchesResults").i("Entered");
        removeTextChangedListeners("displayConversionFromInchesResults");

        textInputLayoutInch.setErrorEnabled(false);
        textInputLayoutFoot.setErrorEnabled(false);
        textInputLayoutYard.setErrorEnabled(false);
        textInputLayoutMile.setErrorEnabled(false);
        textInputLayoutMillimeter.setErrorEnabled(false);
        textInputLayoutCentimeter.setErrorEnabled(false);
        textInputLayoutMeter.setErrorEnabled(false);
        textInputLayoutKilometer.setErrorEnabled(false);

        editTextFoot.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextYard.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextMile.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextMillimeter.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        editTextCentimeter.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        editTextMeter.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        editTextKilometer.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromInchesResults");
    }

    @Override
    public void displayConversionFromInchesError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutInch.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutInch.setError(getString(
                    R.string.conversion_error_below_zero
            ));
        } else {
            textInputLayoutInch.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromInchesError");
        editTextFoot.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextYard.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMile.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMillimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextCentimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKilometer.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromInchesError");
    }

    @Override
    public void displayConversionFromFeetResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromFeetResults").i("Entered");
        removeTextChangedListeners("displayConversionFromFeetResults");

        textInputLayoutInch.setErrorEnabled(false);
        textInputLayoutFoot.setErrorEnabled(false);
        textInputLayoutYard.setErrorEnabled(false);
        textInputLayoutMile.setErrorEnabled(false);
        textInputLayoutMillimeter.setErrorEnabled(false);
        textInputLayoutCentimeter.setErrorEnabled(false);
        textInputLayoutMeter.setErrorEnabled(false);
        textInputLayoutKilometer.setErrorEnabled(false);

        editTextInch.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextYard.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextMile.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextMillimeter.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        editTextCentimeter.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        editTextMeter.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        editTextKilometer.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromFeetResults");
    }

    @Override
    public void displayConversionFromFeetError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutFoot.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutFoot.setError(getString(
                    R.string.conversion_error_below_zero
            ));
        } else {
            textInputLayoutFoot.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromFeetError");
        editTextInch.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextYard.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMile.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMillimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextCentimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKilometer.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromFeetError");
    }

    @Override
    public void displayConversionFromYardsResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromYardsResults").i("Entered");
        removeTextChangedListeners("displayConversionFromYardsResults");

        textInputLayoutInch.setErrorEnabled(false);
        textInputLayoutFoot.setErrorEnabled(false);
        textInputLayoutYard.setErrorEnabled(false);
        textInputLayoutMile.setErrorEnabled(false);
        textInputLayoutMillimeter.setErrorEnabled(false);
        textInputLayoutCentimeter.setErrorEnabled(false);
        textInputLayoutMeter.setErrorEnabled(false);
        textInputLayoutKilometer.setErrorEnabled(false);

        editTextInch.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextFoot.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextMile.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextMillimeter.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        editTextCentimeter.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        editTextMeter.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        editTextKilometer.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromYardsResults");
    }

    @Override
    public void displayConversionFromYardsError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutYard.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutYard.setError(getString(
                    R.string.conversion_error_below_zero
            ));
        } else {
            textInputLayoutYard.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromYardsError");
        editTextInch.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextFoot.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMile.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMillimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextCentimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKilometer.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromYardsError");
    }

    @Override
    public void displayConversionFromMilesResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromMilesResults").i("Entered");
        removeTextChangedListeners("displayConversionFromMilesResults");

        textInputLayoutInch.setErrorEnabled(false);
        textInputLayoutFoot.setErrorEnabled(false);
        textInputLayoutYard.setErrorEnabled(false);
        textInputLayoutMile.setErrorEnabled(false);
        textInputLayoutMillimeter.setErrorEnabled(false);
        textInputLayoutCentimeter.setErrorEnabled(false);
        textInputLayoutMeter.setErrorEnabled(false);
        textInputLayoutKilometer.setErrorEnabled(false);

        editTextInch.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextFoot.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextYard.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextMillimeter.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        editTextCentimeter.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        editTextMeter.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        editTextKilometer.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromMilesResults");
    }

    @Override
    public void displayConversionFromMilesError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutMile.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutMile.setError(getString(
                    R.string.conversion_error_below_zero
            ));
        } else {
            textInputLayoutMile.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromMilesError");
        editTextInch.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextFoot.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextYard.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMillimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextCentimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKilometer.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromMilesError");
    }

    @Override
    public void displayConversionFromMillimetersResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromMillimetersResults").i("Entered");
        removeTextChangedListeners("displayConversionFromMillimetersResults");

        textInputLayoutInch.setErrorEnabled(false);
        textInputLayoutFoot.setErrorEnabled(false);
        textInputLayoutYard.setErrorEnabled(false);
        textInputLayoutMile.setErrorEnabled(false);
        textInputLayoutMillimeter.setErrorEnabled(false);
        textInputLayoutCentimeter.setErrorEnabled(false);
        textInputLayoutMeter.setErrorEnabled(false);
        textInputLayoutKilometer.setErrorEnabled(false);

        editTextInch.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextFoot.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextYard.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextMile.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        editTextCentimeter.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        editTextMeter.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        editTextKilometer.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromMillimetersResults");
    }

    @Override
    public void displayConversionFromMillimetersError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutMillimeter.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutMillimeter.setError(getString(
                    R.string.conversion_error_below_zero
            ));
        } else {
            textInputLayoutMillimeter.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromMillimetersError");
        editTextInch.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextFoot.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextYard.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMile.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextCentimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKilometer.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromMillimetersError");
    }

    @Override
    public void displayConversionFromCentimetersResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromCentimetersResults").i("Entered");
        removeTextChangedListeners("displayConversionFromCentimetersResults");

        textInputLayoutInch.setErrorEnabled(false);
        textInputLayoutFoot.setErrorEnabled(false);
        textInputLayoutYard.setErrorEnabled(false);
        textInputLayoutMile.setErrorEnabled(false);
        textInputLayoutMillimeter.setErrorEnabled(false);
        textInputLayoutCentimeter.setErrorEnabled(false);
        textInputLayoutMeter.setErrorEnabled(false);
        textInputLayoutKilometer.setErrorEnabled(false);

        editTextInch.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextFoot.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextYard.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextMile.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        editTextMillimeter.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        editTextMeter.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        editTextKilometer.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromCentimetersResults");
    }

    @Override
    public void displayConversionFromCentimetersError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutCentimeter.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutCentimeter.setError(getString(
                    R.string.conversion_error_below_zero
            ));
        } else {
            textInputLayoutCentimeter.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromCentimetersError");
        editTextInch.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextFoot.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextYard.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMile.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMillimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKilometer.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromCentimetersError");
    }

    @Override
    public void displayConversionFromMetersResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromMetersResults").i("Entered");
        removeTextChangedListeners("displayConversionFromMetersResults");

        textInputLayoutInch.setErrorEnabled(false);
        textInputLayoutFoot.setErrorEnabled(false);
        textInputLayoutYard.setErrorEnabled(false);
        textInputLayoutMile.setErrorEnabled(false);
        textInputLayoutMillimeter.setErrorEnabled(false);
        textInputLayoutCentimeter.setErrorEnabled(false);
        textInputLayoutMeter.setErrorEnabled(false);
        textInputLayoutKilometer.setErrorEnabled(false);

        editTextInch.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextFoot.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextYard.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextMile.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        editTextMillimeter.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        editTextCentimeter.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        editTextKilometer.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromMetersResults");
    }

    @Override
    public void displayConversionFromMetersError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutMeter.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutMeter.setError(getString(
                    R.string.conversion_error_below_zero
            ));
        } else {
            textInputLayoutMeter.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromMetersError");
        editTextInch.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextFoot.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextYard.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMile.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMillimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextCentimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKilometer.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromMetersError");
    }

    @Override
    public void displayConversionFromKilometersResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromKilometersResults").i("Entered");
        removeTextChangedListeners("displayConversionFromKilometersResults");

        textInputLayoutInch.setErrorEnabled(false);
        textInputLayoutFoot.setErrorEnabled(false);
        textInputLayoutYard.setErrorEnabled(false);
        textInputLayoutMile.setErrorEnabled(false);
        textInputLayoutMillimeter.setErrorEnabled(false);
        textInputLayoutCentimeter.setErrorEnabled(false);
        textInputLayoutMeter.setErrorEnabled(false);
        textInputLayoutKilometer.setErrorEnabled(false);

        editTextInch.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextFoot.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextYard.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextMile.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);
        editTextMillimeter.setText(results.get(4), AppCompatTextView.BufferType.EDITABLE);
        editTextCentimeter.setText(results.get(5), AppCompatTextView.BufferType.EDITABLE);
        editTextMeter.setText(results.get(6), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromKilometersResults");
    }

    @Override
    public void displayConversionFromKilometersError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutKilometer.setError(getString(
                    R.string.conversion_error_input_not_numeric
            ));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutKilometer.setError(getString(
                    R.string.conversion_error_below_zero
            ));
        } else {
            textInputLayoutKilometer.setError(getString(
                    R.string.conversion_error_conversion_error
            ));
        }

        removeTextChangedListeners("displayConversionFromKilometersError");
        editTextInch.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextFoot.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextYard.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMile.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMillimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextCentimeter.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMeter.setText("", AppCompatTextView.BufferType.EDITABLE);
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

        editTextInch.addTextChangedListener(textWatcherInch);
        editTextFoot.addTextChangedListener(textWatcherFoot);
        editTextYard.addTextChangedListener(textWatcherYard);
        editTextMile.addTextChangedListener(textWatcherMile);
        editTextMillimeter.addTextChangedListener(textWatcherMillimeter);
        editTextCentimeter.addTextChangedListener(textWatcherCentimeter);
        editTextMeter.addTextChangedListener(textWatcherMeter);
        editTextKilometer.addTextChangedListener(textWatcherKilometer);
    }

    @Override
    public void removeTextChangedListeners(String callingClassName) {
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

    @Override
    protected ILengthPresenter getPresenter() {
        if (lengthPresenter == null) {
            DaggerFragmentInjectorComponent
                    .create()
                    .inject(this);
        }
        
        return lengthPresenter;
    }

    @Override
    protected void registerViewWithPresenter() {
        getPresenter().registerView(this);
    }

    // endregion
}
