package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.interfaces.view.ITemperatureView;
import com.bubbinator91.converter.models.TemperatureModel;
import com.bubbinator91.converter.models.TemperatureModel.TemperatureUnits;

public class MockTemperatureView implements ITemperatureView {
    public String celsiusValue = null, fahrenheitValue = null, kelvinValue = null;
    public boolean celsiusError = false, fahrenheitError = false, kelvinError = false;

    @Override
    public void showNewValuesFromModel(TemperatureModel model) {
        celsiusValue = model.getCelsius();
        fahrenheitValue = model.getFahrenheit();
        kelvinValue = model.getKelvin();

        celsiusError = false;
        fahrenheitError = false;
        kelvinError = false;
    }

    @Override
    public void showNewValuesFromModelExcludingSource(TemperatureModel model, TemperatureUnits source) {
        if (source != TemperatureUnits.celsius) {
            celsiusValue = model.getCelsius();
        }
        if (source != TemperatureUnits.fahrenheit) {
            fahrenheitValue = model.getFahrenheit();
        }
        if (source != TemperatureUnits.kelvin) {
            kelvinValue = model.getKelvin();
        }

        celsiusError = false;
        fahrenheitError = false;
        kelvinError = false;
    }

    @Override
    public void showErrorForSource(Throwable error, TemperatureUnits source) {
        switch (source) {
            case celsius:
                celsiusError = true;
                fahrenheitValue = null;
                kelvinValue = null;
                break;
            case fahrenheit:
                fahrenheitError = true;
                celsiusValue = null;
                kelvinValue = null;
                break;
            case kelvin:
                kelvinError = true;
                celsiusValue = null;
                fahrenheitValue = null;
                break;
            default:
                break;
        }
    }

    @Override
    public TemperatureModel loadModel() {
        return new TemperatureModel();
    }

    // No relevant implementation for testing
    @Override
    public void saveModel(TemperatureModel model) {}

    public void resetValues() {
        celsiusValue = null;
        fahrenheitValue = null;
        kelvinValue = null;

        celsiusError = false;
        fahrenheitError = false;
        kelvinError = false;
    }
}
