package com.bubbinator91.converter.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bubbinator91.conversion.util.ConversionErrorCodes;
import com.bubbinator91.converter.R;
import com.bubbinator91.converter.dagger.components.DaggerFragmentInjectorComponent;
import com.bubbinator91.converter.ui.interfaces.speed.ISpeedPresenter;
import com.bubbinator91.converter.ui.interfaces.speed.ISpeedView;
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

    private SimpleTextWatcher mTextWatcherFps, mTextWatcherKnot, mTextWatcherKph, mTextWatcherMps,
            mTextWatcherMph;

    private int mLastEditTextFocused;

    @Inject ISpeedPresenter mSpeedPresenter;

    @Bind(R.id.editText_speed_fps)  AppCompatEditText mEditTextFps;
    @Bind(R.id.editText_speed_knot) AppCompatEditText mEditTextKnot;
    @Bind(R.id.editText_speed_kph)  AppCompatEditText mEditTextKph;
    @Bind(R.id.editText_speed_mps)  AppCompatEditText mEditTextMps;
    @Bind(R.id.editText_speed_mph)  AppCompatEditText mEditTextMph;

    @Bind(R.id.textInputLayout_speed_fps)   TextInputLayout mTextInputLayoutFps;
    @Bind(R.id.textInputLayout_speed_knot)  TextInputLayout mTextInputLayoutKnot;
    @Bind(R.id.textInputLayout_speed_kph)   TextInputLayout mTextInputLayoutKph;
    @Bind(R.id.textInputLayout_speed_mps)   TextInputLayout mTextInputLayoutMps;
    @Bind(R.id.textInputLayout_speed_mph)   TextInputLayout mTextInputLayoutMph;

    // region Lifecycle methods

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Timber.tag(TAG + ".onCreateView").i("Entered");

        if (getRootView() != null) {
            ButterKnife.bind(this, getRootView());

            mTextWatcherFps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_FPS;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherFps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherFps");
                        getPresenter().getConversionFromFeetPerSecondResults(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherKnot = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_KNOT;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherKnot");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherKnot");
                        getPresenter().getConversionFromKnotsResults(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherKph = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_KPH;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherKph");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherKph");
                        getPresenter().getConversionFromKilometersPerHourResults(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherMps = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_MPS;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherMps");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherMps");
                        getPresenter().getConversionFromMetersPerSecondResults(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            mTextWatcherMph = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mLastEditTextFocused = LAST_EDIT_TEXT_FOCUSED_MPH;

                    if (s != null) {
                        removeTextChangedListeners("mTextWatcherMph");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("mTextWatcherMph");
                        getPresenter().getConversionFromMilesPerHourResults(
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
                mEditTextFps.setText(getSharedPreferences().getString(FPS_VALUE_PERSIST_KEY, ""));
                mEditTextKnot.setText(getSharedPreferences().getString(KNOT_VALUE_PERSIST_KEY, ""));
                mEditTextKph.setText(getSharedPreferences().getString(KPH_VALUE_PERSIST_KEY, ""));
                mEditTextMps.setText(getSharedPreferences().getString(MPS_VALUE_PERSIST_KEY, ""));
                mEditTextMph.setText(getSharedPreferences().getString(MPH_VALUE_PERSIST_KEY, ""));
            }
            if (getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, -1) != -1) {
                mLastEditTextFocused = getSharedPreferences().getInt(LAST_EDIT_TEXT_FOCUSED_PERSIST_KEY, 0);
            }
        }

        if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_FPS) {
            if (mEditTextFps.getText() != null) {
                Utils.sanitizeEditable(mEditTextFps.getText());
                getPresenter().getConversionFromFeetPerSecondResults(
                        mEditTextFps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_KNOT) {
            if (mEditTextKnot.getText() != null) {
                Utils.sanitizeEditable(mEditTextKnot.getText());
                getPresenter().getConversionFromKnotsResults(
                        mEditTextKnot.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_KPH) {
            if (mEditTextKph.getText() != null) {
                Utils.sanitizeEditable(mEditTextKph.getText());
                getPresenter().getConversionFromKilometersPerHourResults(
                        mEditTextKph.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_MPS) {
            if (mEditTextMps.getText() != null) {
                Utils.sanitizeEditable(mEditTextMps.getText());
                getPresenter().getConversionFromMetersPerSecondResults(
                        mEditTextMps.getText().toString(),
                        getNumOfDecimalPlaces()
                );
            }
        } else if (mLastEditTextFocused == LAST_EDIT_TEXT_FOCUSED_MPH) {
            if (mEditTextMph.getText() != null) {
                Utils.sanitizeEditable(mEditTextMph.getText());
                getPresenter().getConversionFromMilesPerHourResults(
                        mEditTextMph.getText().toString(),
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
                    .putString(FPS_VALUE_PERSIST_KEY, mEditTextFps.getText().toString())
                    .putString(KNOT_VALUE_PERSIST_KEY, mEditTextKnot.getText().toString())
                    .putString(KPH_VALUE_PERSIST_KEY, mEditTextKph.getText().toString())
                    .putString(MPS_VALUE_PERSIST_KEY, mEditTextMps.getText().toString())
                    .putString(MPH_VALUE_PERSIST_KEY, mEditTextMph.getText().toString())
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

    // region Overridden SpeedView methods

    @Override
    public void displayConversionFromFeetPerSecondResults(List<String> results, int errorCode) {
        Timber.tag(TAG + ".displayConversionFromFeetPerSecondResults").i("Entered");

        if (results != null) {
            removeTextChangedListeners(TAG + ".displayConversionFromFeetPerSecondResults");

            switch (errorCode) {
                case ConversionErrorCodes.ERROR_BELOW_ZERO:
                    mTextInputLayoutFps.setError(getString(
                            R.string.conversion_error_below_zero
                    ));
                    break;
                case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                    mTextInputLayoutFps.setError(getString(
                            R.string.conversion_error_input_not_numeric
                    ));
                    break;
                case ConversionErrorCodes.ERROR_UNKNOWN:
                    mTextInputLayoutFps.setError(getString(
                            R.string.conversion_error_conversion_error
                    ));
                    break;
                default:
                    mTextInputLayoutFps.setErrorEnabled(false);
                    mTextInputLayoutKnot.setErrorEnabled(false);
                    mTextInputLayoutKph.setErrorEnabled(false);
                    mTextInputLayoutMps.setErrorEnabled(false);
                    mTextInputLayoutMph.setErrorEnabled(false);
                    break;
            }

            mEditTextKnot.setText(results.get(0),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextKph.setText(results.get(1),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMps.setText(results.get(2),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMph.setText(results.get(3),
                    AppCompatTextView.BufferType.EDITABLE);

            addTextChangedListeners(TAG + ".displayConversionFromFeetPerSecondResults");
        }
    }

    @Override
    public void displayConversionFromKilometersPerHourResults(List<String> results, int errorCode) {
        Timber.tag(TAG + ".displayConversionFromKilometersPerHourResults").i("Entered");

        if (results != null) {
            removeTextChangedListeners(TAG + ".displayConversionFromKilometersPerHourResults");

            switch (errorCode) {
                case ConversionErrorCodes.ERROR_BELOW_ZERO:
                    mTextInputLayoutKph.setError(getString(
                            R.string.conversion_error_below_zero
                    ));
                    break;
                case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                    mTextInputLayoutKph.setError(getString(
                            R.string.conversion_error_input_not_numeric
                    ));
                    break;
                case ConversionErrorCodes.ERROR_UNKNOWN:
                    mTextInputLayoutKph.setError(getString(
                            R.string.conversion_error_conversion_error
                    ));
                    break;
                default:
                    mTextInputLayoutFps.setErrorEnabled(false);
                    mTextInputLayoutKnot.setErrorEnabled(false);
                    mTextInputLayoutKph.setErrorEnabled(false);
                    mTextInputLayoutMps.setErrorEnabled(false);
                    mTextInputLayoutMph.setErrorEnabled(false);
                    break;
            }

            mEditTextFps.setText(results.get(0),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextKnot.setText(results.get(1),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMps.setText(results.get(2),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMph.setText(results.get(3),
                    AppCompatTextView.BufferType.EDITABLE);

            addTextChangedListeners(TAG + ".displayConversionFromKilometersPerHourResults");
        }
    }

    @Override
    public void displayConversionFromKnotsResults(List<String> results, int errorCode) {
        Timber.tag(TAG + ".displayConversionFromKnotsResults").i("Entered");

        if (results != null) {
            removeTextChangedListeners(TAG + ".displayConversionFromKnotsResults");

            switch (errorCode) {
                case ConversionErrorCodes.ERROR_BELOW_ZERO:
                    mTextInputLayoutKnot.setError(getString(
                            R.string.conversion_error_below_zero
                    ));
                    break;
                case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                    mTextInputLayoutKnot.setError(getString(
                            R.string.conversion_error_input_not_numeric
                    ));
                    break;
                case ConversionErrorCodes.ERROR_UNKNOWN:
                    mTextInputLayoutKnot.setError(getString(
                            R.string.conversion_error_conversion_error
                    ));
                    break;
                default:
                    mTextInputLayoutFps.setErrorEnabled(false);
                    mTextInputLayoutKnot.setErrorEnabled(false);
                    mTextInputLayoutKph.setErrorEnabled(false);
                    mTextInputLayoutMps.setErrorEnabled(false);
                    mTextInputLayoutMph.setErrorEnabled(false);
                    break;
            }

            mEditTextFps.setText(results.get(0),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextKph.setText(results.get(1),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMps.setText(results.get(2),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMph.setText(results.get(3),
                    AppCompatTextView.BufferType.EDITABLE);

            addTextChangedListeners(TAG + ".displayConversionFromKnotsResults");
        }
    }

    @Override
    public void displayConversionFromMetersPerSecondResults(List<String> results, int errorCode) {
        Timber.tag(TAG + ".displayConversionFromMetersPerSecondResults").i("Entered");

        if (results != null) {
            removeTextChangedListeners(TAG + ".displayConversionFromMetersPerSecondResults");

            switch (errorCode) {
                case ConversionErrorCodes.ERROR_BELOW_ZERO:
                    mTextInputLayoutMps.setError(getString(
                            R.string.conversion_error_below_zero
                    ));
                    break;
                case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                    mTextInputLayoutMps.setError(getString(
                            R.string.conversion_error_input_not_numeric
                    ));
                    break;
                case ConversionErrorCodes.ERROR_UNKNOWN:
                    mTextInputLayoutMps.setError(getString(
                            R.string.conversion_error_conversion_error
                    ));
                    break;
                default:
                    mTextInputLayoutFps.setErrorEnabled(false);
                    mTextInputLayoutKnot.setErrorEnabled(false);
                    mTextInputLayoutKph.setErrorEnabled(false);
                    mTextInputLayoutMps.setErrorEnabled(false);
                    mTextInputLayoutMph.setErrorEnabled(false);
                    break;
            }

            mEditTextFps.setText(results.get(0),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextKnot.setText(results.get(1),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextKph.setText(results.get(2),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMph.setText(results.get(3),
                    AppCompatTextView.BufferType.EDITABLE);

            addTextChangedListeners(TAG + ".displayConversionFromMetersPerSecondResults");
        }
    }

    @Override
    public void displayConversionFromMilesPerHourResults(List<String> results, int errorCode) {
        Timber.tag(TAG + ".displayConversionFromMilesPerHourResults").i("Entered");

        if (results != null) {
            removeTextChangedListeners(TAG + ".displayConversionFromMilesPerHourResults");

            switch (errorCode) {
                case ConversionErrorCodes.ERROR_BELOW_ZERO:
                    mTextInputLayoutMph.setError(getString(
                            R.string.conversion_error_below_zero
                    ));
                    break;
                case ConversionErrorCodes.ERROR_INPUT_NOT_NUMERIC:
                    mTextInputLayoutMph.setError(getString(
                            R.string.conversion_error_input_not_numeric
                    ));
                    break;
                case ConversionErrorCodes.ERROR_UNKNOWN:
                    mTextInputLayoutMph.setError(getString(
                            R.string.conversion_error_conversion_error
                    ));
                    break;
                default:
                    mTextInputLayoutFps.setErrorEnabled(false);
                    mTextInputLayoutKnot.setErrorEnabled(false);
                    mTextInputLayoutKph.setErrorEnabled(false);
                    mTextInputLayoutMps.setErrorEnabled(false);
                    mTextInputLayoutMph.setErrorEnabled(false);
                    break;
            }

            mEditTextFps.setText(results.get(0),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextKnot.setText(results.get(1),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextKph.setText(results.get(2),
                    AppCompatTextView.BufferType.EDITABLE);
            mEditTextMps.setText(results.get(3),
                    AppCompatTextView.BufferType.EDITABLE);

            addTextChangedListeners(TAG + ".displayConversionFromMilesPerHourResults");
        }
    }

    // endregion

    // region Overridden ConverterView methods

    @Override
    public void addTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".addTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".addTextChangedListeners").i("Entered");
        }

        mEditTextFps.addTextChangedListener(mTextWatcherFps);
        mEditTextKnot.addTextChangedListener(mTextWatcherKnot);
        mEditTextKph.addTextChangedListener(mTextWatcherKph);
        mEditTextMps.addTextChangedListener(mTextWatcherMps);
        mEditTextMph.addTextChangedListener(mTextWatcherMph);
    }

    @Override
    public void removeTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".removeTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".removeTextChangedListeners").i("Entered");
        }

        mEditTextFps.removeTextChangedListener(mTextWatcherFps);
        mEditTextKnot.removeTextChangedListener(mTextWatcherKnot);
        mEditTextKph.removeTextChangedListener(mTextWatcherKph);
        mEditTextMps.removeTextChangedListener(mTextWatcherMps);
        mEditTextMph.removeTextChangedListener(mTextWatcherMph);
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
        if (mSpeedPresenter == null) {
            DaggerFragmentInjectorComponent
                    .create()
                    .inject(this);
        }

        return mSpeedPresenter;
    }

    @Override
    protected void registerViewWithPresenter() {
        getPresenter().registerView(this);
    }

    // endregion
}