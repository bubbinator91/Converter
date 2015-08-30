package com.bubbinator91.conversion.acceleration;

/**
 * Coordinates getting an instance of the specific conversion class to convert from.
 */
public class Acceleration {
    public Acceleration() {}

    public CentimetersPerSecondSquared centimetersPerSecondSquared() {
        return CentimetersPerSecondSquared.getInstance();
    }

    public FeetPerSecondSquared feetPerSecondSquared() {
        return FeetPerSecondSquared.getInstance();
    }

    public MetersPerSecondSquared metersPerSecondSquared() {
        return MetersPerSecondSquared.getInstance();
    }

    public StandardGravity standardGravity() {
        return StandardGravity.getInstance();
    }
}
