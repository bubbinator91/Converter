package com.bubbinator91.converter.conversion.acceleration;

/**
 * Coordinates getting an instance of the specific acceleration conversion class to convert from.
 */
public class Acceleration {
    public Acceleration() {}

    public static CentimetersPerSecondSquared centimetersPerSecondSquared() {
        return CentimetersPerSecondSquared.getInstance();
    }

    public static FeetPerSecondSquared feetPerSecondSquared() {
        return FeetPerSecondSquared.getInstance();
    }

    public static MetersPerSecondSquared metersPerSecondSquared() {
        return MetersPerSecondSquared.getInstance();
    }

    public static StandardGravity standardGravity() {
        return StandardGravity.getInstance();
    }
}
