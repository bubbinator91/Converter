package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.interfaces.view.IFuelConsumptionView;
import com.bubbinator91.converter.models.FuelConsumptionModel;
import com.bubbinator91.converter.models.FuelConsumptionModel.FuelConsumptionUnits;

public class MockFuelConsumptionView implements IFuelConsumptionView {
    public String usmpgValue = null, ukmpgValue = null, kplValue = null, l100kValue = null;
    public boolean usmpgError = false, ukmpgError = false, kplError = false, l100kError = false;

    @Override
    public void showNewValuesFromModel(FuelConsumptionModel model) {
        this.usmpgValue = model.getUsmpg();
        this.ukmpgValue = model.getUkmpg();
        this.kplValue = model.getKpl();
        this.l100kValue = model.getL100km();

        usmpgError = false;
        ukmpgError = false;
        kplError = false;
        l100kError = false;
    }

    @Override
    public void showNewValuesFromModelExcludingSource(FuelConsumptionModel model, FuelConsumptionUnits source) {
        if (source != FuelConsumptionUnits.usmpg) {
            usmpgValue = model.getUsmpg();
        }
        if (source != FuelConsumptionUnits.ukmpg) {
            ukmpgValue = model.getUkmpg();
        }
        if (source != FuelConsumptionUnits.kpl) {
            kplValue = model.getKpl();
        }
        if (source != FuelConsumptionUnits.l100km) {
            l100kValue = model.getL100km();
        }

        usmpgError = false;
        ukmpgError = false;
        kplError = false;
        l100kError = false;
    }

    @Override
    public void showErrorForSource(Throwable error, FuelConsumptionUnits source) {
        switch (source) {
            case usmpg:
                usmpgError = true;
                ukmpgValue = null;
                kplValue = null;
                l100kValue = null;
                break;
            case ukmpg:
                ukmpgError = true;
                usmpgValue = null;
                kplValue = null;
                l100kValue = null;
                break;
            case kpl:
                kplError = true;
                usmpgValue = null;
                ukmpgValue = null;
                l100kValue = null;
                break;
            case l100km:
                l100kError = true;
                usmpgValue = null;
                ukmpgValue = null;
                kplValue = null;
                break;
            default:
                break;
        }
    }

    @Override
    public FuelConsumptionModel loadModel() {
        return new FuelConsumptionModel();
    }

    // No relevant implementation for testing
    @Override
    public void saveModel(FuelConsumptionModel model) {}

    public void resetValues() {
        usmpgValue = null;
        ukmpgValue = null;
        kplValue = null;
        l100kValue = null;

        usmpgError = false;
        ukmpgError = false;
        kplError = false;
        l100kError = false;
    }
}
