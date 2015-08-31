package com.bubbinator91.conversion.acceleration;

/**
 * Coordinates getting an instance of the specific acceleration conversion class to convert from.
 */
public class Acceleration {
    public Acceleration() {}

    public CentimetersPerSecondSquared CentimetersPerSecondSquared() {
        return CentimetersPerSecondSquared.getInstance();
    }

    public FeetPerSecondSquared FeetPerSecondSquared() {
        return FeetPerSecondSquared.getInstance();
    }

    public MetersPerSecondSquared MetersPerSecondSquared() {
        return MetersPerSecondSquared.getInstance();
    }

    public StandardGravity StandardGravity() {
        return StandardGravity.getInstance();
    }
}
