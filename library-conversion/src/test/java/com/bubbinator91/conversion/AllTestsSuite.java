package com.bubbinator91.conversion;

import com.bubbinator91.conversion.acceleration.AccelerationSuite;
import com.bubbinator91.conversion.fuelconsumption.FuelConsumptionSuite;
import com.bubbinator91.conversion.length.LengthSuite;
import com.bubbinator91.conversion.speed.SpeedSuite;
import com.bubbinator91.conversion.temperature.TemperatureSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all of the tests in all of the suites.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccelerationSuite.class,
        //DataTransferSpeedSuite.class,
        FuelConsumptionSuite.class,
        LengthSuite.class,
        SpeedSuite.class,
        TemperatureSuite.class
})
public class AllTestsSuite {}
