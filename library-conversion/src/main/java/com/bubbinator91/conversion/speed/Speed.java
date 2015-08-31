package com.bubbinator91.conversion.speed;

/**
 * Coordinates getting an instance of the specific speed conversion class to convert from.
 */
public class Speed {
    public Speed() {}

    public FeetPerSecond FeetPerSecond() {
        return FeetPerSecond.getInstance();
    }

    public KilometersPerHour KilometersPerHour() {
        return KilometersPerHour.getInstance();
    }

    public Knots Knots() {
        return Knots.getInstance();
    }

    public MetersPerSecond MetersPerSecond() {
        return MetersPerSecond.getInstance();
    }

    public MilesPerHour MilesPerHour() {
        return MilesPerHour.getInstance();
    }
}
