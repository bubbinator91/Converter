package com.bubbinator91.converter.conversion.fuelconsumption;

/**
 * Coordinates getting an instance of the specific fuel consumption conversion class to convert from.
 */
public class FuelConsumption {
    public FuelConsumption() {}

    public static KilometersPerLiter kilometersPerLiter() {
        return KilometersPerLiter.getInstance();
    }

    public static LitersPer100Kilometers litersPer100Kilometers() {
        return LitersPer100Kilometers.getInstance();
    }

    public static UKMilesPerGallon UKMilesPerGallon() {
        return UKMilesPerGallon.getInstance();
    }

    public static USMilesPerGallon USMilesPerGallon() {
        return USMilesPerGallon.getInstance();
    }
}
