package com.bubbinator91.converter.views.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bubbinator91.converter.conversion.util.ValueBelowZeroException;
import com.bubbinator91.converter.R;
import com.bubbinator91.converter.dagger.components.DaggerFragmentInjectorComponent;
import com.bubbinator91.converter.interfaces.presenter.IAccelerationPresenter;
import com.bubbinator91.converter.interfaces.view.IAccelerationView;
import com.bubbinator91.converter.models.AccelerationModel;
import com.bubbinator91.converter.models.AccelerationModel.AccelerationUnits;
import com.bubbinator91.converter.util.PresenterCache;
import com.bubbinator91.converter.util.SimpleTextWatcher;
import com.bubbinator91.converter.util.Utils;
import com.bubbinator91.converter.views.base.BaseFragment2;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Centimeters per Second Squared (cm/s^2), Feet per Second Squared (ft/s^2), Meters per Second
 * Squared (m/s^2), and Standard Gravity
 */
public class AccelerationFragment
        extends BaseFragment2<IAccelerationPresenter>
        implements IAccelerationView {
    private static final String TAG = AccelerationFragment.class.getSimpleName();

    private static final String CMPSS_VALUE_PERSIST_KEY = "ACCELERATION_FRAGMENT_CMPSS_VALUE";
    private static final String FPSS_VALUE_PERSIST_KEY = "ACCELERATION_FRAGMENT_FPSS_VALUE";
    private static final String MPSS_VALUE_PERSIST_KEY = "ACCELERATION_FRAGMENT_MPSS_VALUE";
    private static final String SG_VALUE_PERSIST_KEY = "ACCELERATION_FRAGMENT_SG_VALUE";

    private SimpleTextWatcher textWatcherCmpss, textWatcherFpss, textWatcherMpss, textWatcherSg;

    @Inject IAccelerationPresenter accelerationPresenter;

    @Bind(R.id.editText_acceleration_cmpss) AppCompatEditText editTextCmpss;
    @Bind(R.id.editText_acceleration_fpss)  AppCompatEditText editTextFpss;
    @Bind(R.id.editText_acceleration_mpss)  AppCompatEditText editTextMpss;
    @Bind(R.id.editText_acceleration_sg)    AppCompatEditText editTextSg;

    @Bind(R.id.textInputLayout_acceleration_cmpss)  TextInputLayout textInputLayoutCmpss;
    @Bind(R.id.textInputLayout_acceleration_fpss)   TextInputLayout textInputLayoutFpss;
    @Bind(R.id.textInputLayout_acceleration_mpss)   TextInputLayout textInputLayoutMpss;
    @Bind(R.id.textInputLayout_acceleration_sg)     TextInputLayout textInputLayoutSg;

    // region Lifecycle methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            accelerationPresenter = PresenterCache.getInstance().restorePresenter(savedInstanceState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Timber.tag(TAG + ".onCreateView").i("Entered");

        if (getRootView() != null) {
            ButterKnife.bind(this, getRootView());

            textWatcherCmpss = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    if (s != null) {
                        removeTextChangedListeners("textWatcherCmpss");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherCmpss");
                        getPresenter().afterCmpssTextChanged(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherFpss = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    if (s != null) {
                        removeTextChangedListeners("textWatcherFpss");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherFpss");
                        getPresenter().afterFpssTextChanged(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherMpss = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    if (s != null) {
                        removeTextChangedListeners("textWatcherMpss");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherMpss");
                        getPresenter().afterMpssTextChanged(
                                s.toString(),
                                getNumOfDecimalPlaces()
                        );
                    }
                }
            };

            textWatcherSg = new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    if (s != null) {
                        removeTextChangedListeners("textWatcherSg");
                        Utils.sanitizeEditable(s);
                        addTextChangedListeners("textWatcherSg");
                        getPresenter().afterSgTextChanged(
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

        getPresenter().registerView(this);
        getPresenter().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.tag(TAG + ".onPause").i("Entered");

        getPresenter().onPause();
        getPresenter().unregisterView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Timber.tag(TAG + ".onDestroyView").i("Entered");
        ButterKnife.unbind(this);
    }

    // endregion

    // region Interface methods

    @Override
    public void showNewValuesFromModel(AccelerationModel model) {
        Timber.tag(TAG + ".showNewValuesFromModel").i("Entered");

        showNewValuesFromModelExcludingSource(model, null);
    }

    @Override
    public void showNewValuesFromModelExcludingSource(AccelerationModel model, AccelerationUnits source) {
        Timber.tag(TAG + ".showNewValuesFromModelExcludingSource").i("Entered");
        removeTextChangedListeners("showNewValuesFromModelExcludingSource");

        textInputLayoutCmpss.setErrorEnabled(false);
        textInputLayoutFpss.setErrorEnabled(false);
        textInputLayoutMpss.setErrorEnabled(false);
        textInputLayoutSg.setErrorEnabled(false);

        if (source != AccelerationUnits.cmpss) {
            editTextCmpss.setText(model.getCmpss(), AppCompatTextView.BufferType.EDITABLE);
        }
        if (source != AccelerationUnits.fpss) {
            editTextFpss.setText(model.getFpss(), AppCompatTextView.BufferType.EDITABLE);
        }
        if (source != AccelerationUnits.mpss) {
            editTextMpss.setText(model.getMpss(), AppCompatTextView.BufferType.EDITABLE);
        }
        if (source != AccelerationUnits.sg) {
            editTextSg.setText(model.getSg(), AppCompatTextView.BufferType.EDITABLE);
        }

        addTextChangedListeners("showNewValuesFromModelExcludingSource");
    }

    @Override
    public void showErrorForSource(Throwable error, AccelerationUnits source) {
        Timber.tag(TAG + ".showErrorForSource").i("Entered");
        removeTextChangedListeners("showErrorForSource");

        String errorText;
        if (error instanceof NumberFormatException) {
            errorText = getString(R.string.conversion_error_input_not_numeric);
        } else if (error instanceof ValueBelowZeroException) {
            errorText = getString(R.string.conversion_error_below_zero);
        } else {
            errorText = getString(R.string.conversion_error_conversion_error);
        }

        switch (source) {
            case cmpss:
                textInputLayoutCmpss.setError(errorText);
                editTextFpss.setText("", AppCompatTextView.BufferType.EDITABLE);
                editTextMpss.setText("", AppCompatTextView.BufferType.EDITABLE);
                editTextSg.setText("", AppCompatTextView.BufferType.EDITABLE);
                break;
            case fpss:
                textInputLayoutFpss.setError(errorText);
                editTextCmpss.setText("", AppCompatTextView.BufferType.EDITABLE);
                editTextMpss.setText("", AppCompatTextView.BufferType.EDITABLE);
                editTextSg.setText("", AppCompatTextView.BufferType.EDITABLE);
                break;
            case mpss:
                textInputLayoutMpss.setError(errorText);
                editTextCmpss.setText("", AppCompatTextView.BufferType.EDITABLE);
                editTextFpss.setText("", AppCompatTextView.BufferType.EDITABLE);
                editTextSg.setText("", AppCompatTextView.BufferType.EDITABLE);
                break;
            case sg:
                textInputLayoutSg.setError(errorText);
                editTextCmpss.setText("", AppCompatTextView.BufferType.EDITABLE);
                editTextFpss.setText("", AppCompatTextView.BufferType.EDITABLE);
                editTextMpss.setText("", AppCompatTextView.BufferType.EDITABLE);
                break;
            default:
                break;
        }

        addTextChangedListeners("showErrorForSource");
    }

    @Override
    public AccelerationModel loadModel() {
        if (getSharedPreferences() != null) {
            return new AccelerationModel(
                    getSharedPreferences().getString(CMPSS_VALUE_PERSIST_KEY, "1"),
                    getSharedPreferences().getString(FPSS_VALUE_PERSIST_KEY, "0.03280839895"),
                    getSharedPreferences().getString(MPSS_VALUE_PERSIST_KEY, "0.01"),
                    getSharedPreferences().getString(SG_VALUE_PERSIST_KEY, "0.001019716213")
            );
        } else {
            return new AccelerationModel();
        }
    }

    @Override
    public void saveModel(AccelerationModel model) {
        if (getSharedPreferences() != null) {
            getSharedPreferences().edit()
                    .putString(CMPSS_VALUE_PERSIST_KEY, model.getCmpss())
                    .putString(FPSS_VALUE_PERSIST_KEY, model.getFpss())
                    .putString(MPSS_VALUE_PERSIST_KEY, model.getMpss())
                    .putString(SG_VALUE_PERSIST_KEY, model.getSg())
                    .apply();
        }
    }

    // endregion

    // region Abstract methods

    @Override
    public void addTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".addTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".addTextChangedListeners").i("Entered");
        }

        editTextCmpss.addTextChangedListener(textWatcherCmpss);
        editTextFpss.addTextChangedListener(textWatcherFpss);
        editTextMpss.addTextChangedListener(textWatcherMpss);
        editTextSg.addTextChangedListener(textWatcherSg);
    }

    @Override
    public void removeTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".removeTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".removeTextChangedListeners").i("Entered");
        }

        editTextCmpss.removeTextChangedListener(textWatcherCmpss);
        editTextFpss.removeTextChangedListener(textWatcherFpss);
        editTextMpss.removeTextChangedListener(textWatcherMpss);
        editTextSg.removeTextChangedListener(textWatcherSg);
    }

    @Override
    protected String getChildTag() {
        return TAG;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_acceleration;
    }

    @Override
    protected int getScrollViewResource() {
        return R.id.fragment_acceleration;
    }

    @Override
    protected IAccelerationPresenter getPresenter() {
        if (accelerationPresenter == null) {
            DaggerFragmentInjectorComponent
                    .create()
                    .inject(this);
        }

        return accelerationPresenter;
    }

    // endregion
}
