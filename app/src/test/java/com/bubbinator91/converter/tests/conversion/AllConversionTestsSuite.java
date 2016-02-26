package com.bubbinator91.converter.tests.conversion;

import com.bubbinator91.converter.tests.conversion.acceleration.AccelerationSuite;
import com.bubbinator91.converter.tests.conversion.datatransferspeed.DataTransferSpeedSuite;
import com.bubbinator91.converter.tests.conversion.fuelconsumption.FuelConsumptionSuite;
import com.bubbinator91.converter.tests.conversion.length.LengthSuite;
import com.bubbinator91.converter.tests.conversion.speed.SpeedSuite;
import com.bubbinator91.converter.tests.conversion.temperature.TemperatureSuite;

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
public class AllConversionTestsSuite {}
