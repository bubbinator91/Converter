package com.bubbinator91.converter.interfaces.view;

import com.bubbinator91.converter.interfaces.base.IView2;
import com.bubbinator91.converter.models.TemperatureModel;
import com.bubbinator91.converter.models.TemperatureModel.TemperatureUnits;
import com.bubbinator91.converter.views.fragments.TemperatureFragment;

/**
 * View interface for the {@link TemperatureFragment}
 */
public interface ITemperatureView extends IView2 {
    void showNewValuesFromModel(TemperatureModel model);

    void showNewValuesFromModelExcludingSource(TemperatureModel model, TemperatureUnits source);

    void showErrorForSource(Throwable error, TemperatureUnits source);

    TemperatureModel loadModel();

    void saveModel(TemperatureModel model);
}
