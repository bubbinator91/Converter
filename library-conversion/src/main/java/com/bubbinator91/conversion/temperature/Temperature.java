package com.bubbinator91.conversion.temperature;

/**
 * Coordinates getting an instance of the specific temperature conversion class to convert from.
 */
public class Temperature {
    public Temperature() {}

    public static Celsius celsius() {
        return Celsius.getInstance();
    }

    public static Fahrenheit fahrenheit() {
        return Fahrenheit.getInstance();
    }

    public static Kelvin kelvin() {
        return Kelvin.getInstance();
    }
}
