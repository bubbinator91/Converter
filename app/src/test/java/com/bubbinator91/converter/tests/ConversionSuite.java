package com.bubbinator91.converter.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all of the JUnit tests
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccelerationTest.class,
        DataTransferSpeedTest.class,
        //FuelConsumptionTest.class,
        LengthTest.class,
        SpeedTest.class,
        TemperatureTest.class
})
public class ConversionSuite {}
