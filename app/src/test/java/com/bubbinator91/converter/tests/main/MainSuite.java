package com.bubbinator91.converter.tests.main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all of the JUnit tests corresponding to testing interactions between presenter and view
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccelerationTest.class,
        DataTransferSpeedTest.class,
        FuelConsumptionTest.class,
        LengthTest.class,
        SpeedTest.class,
        TemperatureTest.class
})
public class MainSuite {}
