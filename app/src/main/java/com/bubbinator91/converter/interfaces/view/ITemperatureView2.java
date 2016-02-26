package com.bubbinator91.converter.interfaces.view;

import com.bubbinator91.converter.interfaces.base.IView2;
import com.bubbinator91.converter.models.TemperatureModel;
import com.bubbinator91.converter.models.TemperatureModel.TemperatureValues;

/**
 * View interface for the {@link com.bubbinator91.converter.views.fragments.TemperatureFragment2}
 */
public interface ITemperatureView2 extends IView2 {
    void showNewValuesFromModel(TemperatureModel model);

    void showNewValuesFromModelExcludingSource(TemperatureModel model, TemperatureValues source);

    void showErrorForSource(Throwable error, TemperatureValues source);

    TemperatureModel loadModel();

    void saveModel(TemperatureModel model);
}
