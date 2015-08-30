package com.bubbinator91.conversion.temperature;

/**
 * Coordinates getting an instance of the specific conversion class to convert from.
 */
public class Temperature {
    public Temperature() {}

    public Celsius celsius() {
        return Celsius.getInstance();
    }

    public Fahrenheit fahrenheit() {
        return Fahrenheit.getInstance();
    }

    public Kelvin kelvin() {
        return Kelvin.getInstance();
    }
}
