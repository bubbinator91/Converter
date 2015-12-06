package com.bubbinator91.converter.conversion.length;

/**
 * Coordinates getting an instance of the specific length conversion class to convert from.
 */
public class Length {
    public Length() {}

    public Centimeters Centimeters() {
        return Centimeters.getInstance();
    }

    public Feet Feet() {
        return Feet.getInstance();
    }

    public Inches Inches() {
        return Inches.getInstance();
    }

    public Kilometers Kilometers() {
        return Kilometers.getInstance();
    }

    public Meters Meters() {
        return Meters.getInstance();
    }

    public Miles Miles() {
        return Miles.getInstance();
    }

    public Millimeters Millimeters() {
        return Millimeters.getInstance();
    }

    public Yards Yards() {
        return Yards.getInstance();
    }
}
