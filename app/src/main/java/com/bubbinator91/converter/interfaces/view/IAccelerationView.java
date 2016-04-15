package com.bubbinator91.converter.interfaces.view;

import com.bubbinator91.converter.interfaces.base.IView2;
import com.bubbinator91.converter.models.AccelerationModel;
import com.bubbinator91.converter.models.AccelerationModel.AccelerationUnits;
import com.bubbinator91.converter.views.fragments.AccelerationFragment;

/**
 * View interface for the {@link AccelerationFragment}
 */
public interface IAccelerationView extends IView2 {
    void showNewValuesFromModel(AccelerationModel model);

    void showNewValuesFromModelExcludingSource(AccelerationModel model, AccelerationUnits source);

    void showErrorForSource(Throwable error, AccelerationUnits source);

    AccelerationModel loadModel();

    void saveModel(AccelerationModel model);
}
