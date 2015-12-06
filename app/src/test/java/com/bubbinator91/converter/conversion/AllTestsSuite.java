package com.bubbinator91.converter.conversion;

import com.bubbinator91.converter.conversion.acceleration.AccelerationSuite;
import com.bubbinator91.converter.conversion.datatransferspeed.DataTransferSpeedSuite;
import com.bubbinator91.converter.conversion.fuelconsumption.FuelConsumptionSuite;
import com.bubbinator91.converter.conversion.length.LengthSuite;
import com.bubbinator91.converter.conversion.speed.SpeedSuite;
import com.bubbinator91.converter.conversion.temperature.TemperatureSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all of the tests in all of the suites.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccelerationSuite.class,
        DataTransferSpeedSuite.class,
        FuelConsumptionSuite.class,
        LengthSuite.class,
        SpeedSuite.class,
        TemperatureSuite.class
})
public class AllTestsSuite {}
