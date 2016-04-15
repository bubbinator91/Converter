package com.bubbinator91.converter.interfaces.presenter;

import com.bubbinator91.converter.interfaces.base.IPresenter2;
import com.bubbinator91.converter.interfaces.view.IAccelerationView;
import com.bubbinator91.converter.views.fragments.AccelerationFragment;

/**
 * Presenter interface for the presenter of {@link AccelerationFragment}
 */
public interface IAccelerationPresenter extends IPresenter2<IAccelerationView> {
    void afterCmpssTextChanged(String cmpss, int decimalPlaces);

    void afterFpssTextChanged(String fpss, int decimalPlaces);

    void afterMpssTextChanged(String mpss, int decimalPlaces);

    void afterSgTextChanged(String sg, int decimalPlaces);
}
