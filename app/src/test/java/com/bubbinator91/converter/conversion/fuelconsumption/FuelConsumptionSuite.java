package com.bubbinator91.converter.conversion.fuelconsumption;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all of the fuel consumption conversion tests.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        USMilesPerGallonTest.class,
        UKMilesPerGallonTest.class,
        KilometersPerLiterTest.class,
        LitersPer100KilometersTest.class
})
public class FuelConsumptionSuite {}
