package com.bubbinator91.conversion.temperature;

/**
 * Coordinates getting an instance of the specific temperature conversion class to convert from.
 */
public class Temperature {
    public Temperature() {}

    public Celsius Celsius() {
        return Celsius.getInstance();
    }

    public Fahrenheit Fahrenheit() {
        return Fahrenheit.getInstance();
    }

    public Kelvin Kelvin() {
        return Kelvin.getInstance();
    }
}
