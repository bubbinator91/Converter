package com.bubbinator91.conversion.fuelconsumption;

/**
 * Coordinates getting an instance of the specific fuel consumption conversion class to convert from.
 */
public class FuelConsumption {
    public FuelConsumption() {}

    public KilometersPerLiter KilometersPerLiter() {
        return KilometersPerLiter.getInstance();
    }

    public LitersPer100Kilometers LitersPer100Kilometers() {
        return LitersPer100Kilometers.getInstance();
    }

    public UKMilesPerGallon UKMilesPerGallon() {
        return UKMilesPerGallon.getInstance();
    }

    public USMilesPerGallon USMilesPerGallon() {
        return USMilesPerGallon.getInstance();
    }
}
