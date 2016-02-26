package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.interfaces.view.ITemperatureView;
import com.bubbinator91.converter.models.TemperatureModel;

import java.util.List;

public class MockTemperatureView implements ITemperatureView {
    public String celsiusValue = null, fahrenheitValue = null, kelvinValue = null;
    public boolean celsiusError = false, fahrenheitError = false, kelvinError = false;

    @Override
    public void showNewValuesFromModel(TemperatureModel model) {
        this.celsiusValue = model.getCelsius();
        this.fahrenheitValue = model.getFahrenheit();
        this.kelvinValue = model.getKelvin();

        celsiusError = false;
        fahrenheitError = false;
        kelvinError = false;
    }

    @Override
    public void showNewValuesFromModelExcludingSource(TemperatureModel model, TemperatureModel.TemperatureValues source) {
        if (source != TemperatureModel.TemperatureValues.celsius) {
            celsiusValue = model.getCelsius();
        }
        if (source != TemperatureModel.TemperatureValues.fahrenheit) {
            fahrenheitValue = model.getFahrenheit();
        }
        if (source != TemperatureModel.TemperatureValues.kelvin) {
            kelvinValue = model.getKelvin();
        }

        celsiusError = false;
        fahrenheitError = false;
        kelvinError = false;
    }

    @Override
    public void showErrorForSource(Throwable error, TemperatureModel.TemperatureValues source) {
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
