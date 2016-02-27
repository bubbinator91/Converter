package com.bubbinator91.converter.interfaces.view;

import com.bubbinator91.converter.interfaces.base.IView2;
import com.bubbinator91.converter.models.FuelConsumptionModel;
import com.bubbinator91.converter.models.FuelConsumptionModel.FuelConsumptionUnits;
import com.bubbinator91.converter.views.fragments.FuelConsumptionFragment;

/**
 * View interface for the {@link FuelConsumptionFragment}
 */
public interface IFuelConsumptionView extends IView2 {
    void showNewValuesFromModel(FuelConsumptionModel model);

    void showNewValuesFromModelExcludingSource(FuelConsumptionModel model, FuelConsumptionUnits source);

    void showErrorForSource(Throwable error, FuelConsumptionUnits source);

    FuelConsumptionModel loadModel();

    void saveModel(FuelConsumptionModel model);
}
