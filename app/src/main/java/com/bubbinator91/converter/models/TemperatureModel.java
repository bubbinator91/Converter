package com.bubbinator91.converter.models;

/**
 * The data model for temperature conversions
 */
public class TemperatureModel {
    private String celsius, fahrenheit, kelvin;

    public enum TemperatureUnits {
        celsius, fahrenheit, kelvin
    }

    public TemperatureModel() {
        celsius = "0";
        fahrenheit = "32";
        kelvin = "273.15";
    }

    public TemperatureModel(String celsius, String fahrenheit, String kelvin) {
        this.celsius = celsius;
        this.fahrenheit = fahrenheit;
        this.kelvin = kelvin;
    }

    public String getCelsius() {
        return celsius;
    }

    public String getFahrenheit() {
        return fahrenheit;
    }

    public String getKelvin() {
        return kelvin;
    }

    public void setCelsius(String celsius) {
        this.celsius = celsius;
    }

    public void setFahrenheit(String fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public void setKelvin(String kelvin) {
        this.kelvin = kelvin;
    }
}
