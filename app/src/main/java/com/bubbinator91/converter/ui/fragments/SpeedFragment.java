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
import com.bubbinator91.converter.interfaces.presenter.ISpeedPresenter;
import com.bubbinator91.converter.interfaces.view.ISpeedView;
import com.bubbinator91.converter.util.PresenterCache;
import com.bubbinator91.converter.util.SimpleTextWatcher;
import com.bubbinator91.converter.util.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * mph, ft/s, m/s, kph, knot
 * Conversions comply with the conversions through Google.com
 */
public class SpeedFragment
        extends BaseFragment<ISpeedPresenter>
        implements ISpeedView {
    private final String TAG = SpeedFragment.class.getSimpleName();

    private static final int LAST_EDIT_TEXT_FOCUSED_FPS = 0;
    private static final int LAST_EDIT_TEXT_FOCUSED_KNOT = 1;
    private static final int LAST_EDIT_TEXT_FOCUSED_KPH = 2;
    private static final int LAST_EDIT_TEXT_FOCUSED_MPS = 3;
    private static final int LAST_EDIT_TEXT_FOCUSED_MPH = 4;

    private static final String FPS_VALUE_PERSIST_KEY = "SPEED_FRAGMENT_FPS_VALUE";
    private static final String KNOT_VALUE_PERSIST_KEY = "SPEED_FRAGMENT_KNOT_VALUE";
    private static final String KPH_VALUE_PERSIST_KEY = "SPEED_FRAGMENT_KPH_VALUE";
    private static final String MPS_VALUE_PERSIST_KEY = "SPEED_FRAGMENT_MPS_VALUE";
    private static final String MPH_VALUE_PERSIST_KEY = "SPEED_FRAGMENT_MPH_VALUE";
    private static final String LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY = "SPEED_FRAGMENT_LETF_VALUE";

    private SimpleTextWatcher textWatcherFps, textWatcherKnot, textWatcherKph, textWatcherMps,
            textWatcherMph;

    @Inject ISpeedPresenter speedPresenter;

    @Bind(R.id.editText_speed_fps)  AppCompatEditText editTextFps;
    @Bind(R.id.editText_speed_knot) AppCompatEditText editTextKnot;
    @Bind(R.id.editText_speed_kph)  AppCompatEditText editTextKph;
    @Bind(R.id.editText_speed_mps)  AppCompatEditText editTextMps;
    @Bind(R.id.editText_speed_mph)  AppCompatEditText editTextMph;

    @Bind(R.id.textInputLayout_speed_fps)   TextInputLayout textInputLayoutFps;
    @Bind(R.id.textInputLayout_speed_knot)  TextInputLayout textInputLayoutKnot;
    @Bind(R.id.textInputLayout_speed_kph)   TextInputLayout textInputLayoutKph;
    @Bind(R.id.textInputLayout_speed_mps)   TextInputLayout textInputLayoutMps;
    @Bind(R.id.textInputLayout_speed_mph)   TextInputLayout textInputLayoutMph;

    // region Lifecycle methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            speedPresenter = PresenterCache.getInstance().restorePresenter(savedInstanceState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Timber.tag(TAG + ".onCreateView").i("Entered");

        if (getRootView() != null) {
            ButterKnife.bind(this, getRootView());

            textWatcherFps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_FPS);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherFps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherFps");
                        getPresenter().getConversionFromFeetPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherKnot = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_KNOT);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherKnot");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherKnot");
                        getPresenter().getConversionFromKnots(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherKph = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_KPH);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherKph");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherKph");
                        getPresenter().getConversionFromKilometersPerHour(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherMps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_MPS);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherMps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherMps");
                        getPresenter().getConversionFromMetersPerSecond(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherMph = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    setLastEditTextFocused(LAST_EDIT_TEXT_FOCUSED_MPH);

                    if (s != null) {
                        removeTextChangedListeners("textWatcherMph");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherMph");
                        getPresenter().getConversionFromMilesPerHour(
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
            if ((getSharedPreferences().getString(FPS_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(KNOT_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(KPH_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(MPS_VALUE_PERSIST_KEY, null) != null)
                    && (getSharedPreferences().getString(MPH_VALUE_PERSIST_KEY, null) != null)) {
                editTextFps.setText(getSharedPreferences().getString(FPS_VALUE_PERSIST_KEY, ""));
                editTextKnot.setText(getSharedPreferences().getString(KNOT_VALUE_PERSIST_KEY, ""));
                editTextKph.setText(getSharedPreferences().getString(KPH_VALUE_PERSIST_KEY, ""));
                editTextMps.setText(getSharedPreferences().getString(MPS_VALUE_PERSIST_KEY, ""));
                editTextMph.setText(getSharedPreferences().getString(MPH_VALUE_PERSIST_KEY, ""));
            }
            if (getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, -1) != -1) {
                setLastEditTextFocused(getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, 0));
            }
        }

        if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_FPS) {
            if (editTextFps.getText() != null) {
                Utils.sanitizeEditable(editTextFps.getText());
                getPresenter().getConversionFromFeetPerSecond(
                        editTextFps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_KNOT) {
            if (editTextKnot.getText() != null) {
                Utils.sanitizeEditable(editTextKnot.getText());
                getPresenter().getConversionFromKnots(
                        editTextKnot.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_KPH) {
            if (editTextKph.getText() != null) {
                Utils.sanitizeEditable(editTextKph.getText());
                getPresenter().getConversionFromKilometersPerHour(
                        editTextKph.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_MPS) {
            if (editTextMps.getText() != null) {
                Utils.sanitizeEditable(editTextMps.getText());
                getPresenter().getConversionFromMetersPerSecond(
                        editTextMps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (getLastEditTextFocused() == LAST_EDIT_TEXT_FOCUSED_MPH) {
            if (editTextMph.getText() != null) {
                Utils.sanitizeEditable(editTextMph.getText());
                getPresenter().getConversionFromMilesPerHour(
                        editTextMph.getText().toString(),
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
                    .putString(FPS_VALUE_PERSIST_KEY, editTextFps.getText().toString())
                    .putString(KNOT_VALUE_PERSIST_KEY, editTextKnot.getText().toString())
                    .putString(KPH_VALUE_PERSIST_KEY, editTextKph.getText().toString())
                    .putString(MPS_VALUE_PERSIST_KEY, editTextMps.getText().toString())
                    .putString(MPH_VALUE_PERSIST_KEY, editTextMph.getText().toString())
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

    // region Overridden ISpeedView methods

    @Override
    public void displayConversionFromFeetPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromFeetPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromFeetPerSecondResults");

        textInputLayoutFps.setErrorEnabled(false);
        textInputLayoutKnot.setErrorEnabled(false);
        textInputLayoutKph.setErrorEnabled(false);
        textInputLayoutMps.setErrorEnabled(false);
        textInputLayoutMph.setErrorEnabled(false);

        editTextKnot.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextKph.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextMps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextMph.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromFeetPerSecondResults");
    }

    @Override
    public void displayConversionFromFeetPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutFps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutFps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutFps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromFeetPerSecondError");
        editTextKnot.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKph.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMph.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromFeetPerSecondError");
    }

    @Override
    public void displayConversionFromKilometersPerHourResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromKilometersPerHourResults").i("Entered");
        removeTextChangedListeners("displayConversionFromKilometersPerHourResults");

        textInputLayoutFps.setErrorEnabled(false);
        textInputLayoutKnot.setErrorEnabled(false);
        textInputLayoutKph.setErrorEnabled(false);
        textInputLayoutMps.setErrorEnabled(false);
        textInputLayoutMph.setErrorEnabled(false);

        editTextFps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextKnot.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextMps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextMph.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromKilometersPerHourResults");
    }

    @Override
    public void displayConversionFromKilometersPerHourError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutKph.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutKph.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutKph.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromKilometersPerHourError");
        editTextFps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKnot.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMph.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromKilometersPerHourError");
    }

    @Override
    public void displayConversionFromKnotsResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromKnotsResults").i("Entered");
        removeTextChangedListeners("displayConversionFromKnotsResults");

        textInputLayoutFps.setErrorEnabled(false);
        textInputLayoutKnot.setErrorEnabled(false);
        textInputLayoutKph.setErrorEnabled(false);
        textInputLayoutMps.setErrorEnabled(false);
        textInputLayoutMph.setErrorEnabled(false);

        editTextFps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextKph.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextMps.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextMph.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromKnotsResults");
    }

    @Override
    public void displayConversionFromKnotsError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutKnot.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutKnot.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutKnot.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromKnotsError");
        editTextFps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKph.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMph.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromKnotsError");
    }

    @Override
    public void displayConversionFromMetersPerSecondResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromMetersPerSecondResults").i("Entered");
        removeTextChangedListeners("displayConversionFromMetersPerSecondResults");

        textInputLayoutFps.setErrorEnabled(false);
        textInputLayoutKnot.setErrorEnabled(false);
        textInputLayoutKph.setErrorEnabled(false);
        textInputLayoutMps.setErrorEnabled(false);
        textInputLayoutMph.setErrorEnabled(false);

        editTextFps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextKnot.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextKph.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextMph.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromMetersPerSecondResults");
    }

    @Override
    public void displayConversionFromMetersPerSecondError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutMps.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutMps.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutMps.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromMetersPerSecondError");
        editTextFps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKnot.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKph.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMph.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromMetersPerSecondError");
    }

    @Override
    public void displayConversionFromMilesPerHourResults(List<String> results) {
        Timber.tag(TAG + ".displayConversionFromMilesPerHourResults").i("Entered");
        removeTextChangedListeners("displayConversionFromMilesPerHourResults");

        textInputLayoutFps.setErrorEnabled(false);
        textInputLayoutKnot.setErrorEnabled(false);
        textInputLayoutKph.setErrorEnabled(false);
        textInputLayoutMps.setErrorEnabled(false);
        textInputLayoutMph.setErrorEnabled(false);

        editTextFps.setText(results.get(0), AppCompatTextView.BufferType.EDITABLE);
        editTextKnot.setText(results.get(1), AppCompatTextView.BufferType.EDITABLE);
        editTextKph.setText(results.get(2), AppCompatTextView.BufferType.EDITABLE);
        editTextMps.setText(results.get(3), AppCompatTextView.BufferType.EDITABLE);

        addTextChangedListeners("displayConversionFromMilesPerHourResults");
    }

    @Override
    public void displayConversionFromMilesPerHourError(Throwable error) {
        if (error instanceof NumberFormatException) {
            textInputLayoutMph.setError(getString(R.string.conversion_error_input_not_numeric));
        } else if (error instanceof ValueBelowZeroException) {
            textInputLayoutMph.setError(getString(R.string.conversion_error_below_zero));
        } else {
            textInputLayoutMph.setError(getString(R.string.conversion_error_conversion_error));
        }

        removeTextChangedListeners("displayConversionFromMilesPerHourError");
        editTextFps.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKnot.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextKph.setText("", AppCompatTextView.BufferType.EDITABLE);
        editTextMps.setText("", AppCompatTextView.BufferType.EDITABLE);
        addTextChangedListeners("displayConversionFromMilesPerHourError");
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

        editTextFps.addTextChangedListener(textWatcherFps);
        editTextKnot.addTextChangedListener(textWatcherKnot);
        editTextKph.addTextChangedListener(textWatcherKph);
        editTextMps.addTextChangedListener(textWatcherMps);
        editTextMph.addTextChangedListener(textWatcherMph);
    }

    @Override
    public void removeTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".removeTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".removeTextChangedListeners").i("Entered");
        }

        editTextFps.removeTextChangedListener(textWatcherFps);
        editTextKnot.removeTextChangedListener(textWatcherKnot);
        editTextKph.removeTextChangedListener(textWatcherKph);
        editTextMps.removeTextChangedListener(textWatcherMps);
        editTextMph.removeTextChangedListener(textWatcherMph);
    }

    // endregion

    // region Overridden BaseFragment methods

    @Override
    protected String getChildTag() {
        return TAG;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_speed;
    }

    @Override
    protected int getScrollViewResource() {
        return R.id.fragment_speed;
    }

    @Override
    protected ISpeedPresenter getPresenter() {
        if (speedPresenter == null) {
            DaggerFragmentInjectorComponent
                    .create()
                    .inject(this);
        }

        return speedPresenter;
    }

    @Override
    protected void registerViewWithPresenter() {
        getPresenter().registerView(this);
    }

    // endregion
}