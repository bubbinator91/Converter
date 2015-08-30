package com.bubbinator91.conversion.speed;

/**
 * Coordinates getting an instance of the specific conversion class to convert from.
 */
public class Speed {
    public Speed() {}

    public FeetPerSecond feetPerSecond() {
        return FeetPerSecond.getInstance();
    }

    public KilometersPerHour kilometersPerHour() {
        return KilometersPerHour.getInstance();
    }

    public Knots knots() {
        return Knots.getInstance();
    }

    public MetersPerSecond metersPerSecond() {
        return MetersPerSecond.getInstance();
    }

    public MilesPerHour milesPerHour() {
        return MilesPerHour.getInstance();
    }
}
