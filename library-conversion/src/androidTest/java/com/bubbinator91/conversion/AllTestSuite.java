package com.bubbinator91.conversion;

import com.bubbinator91.conversion.length.LengthTestSuite;
import com.bubbinator91.conversion.speed.SpeedTestSuite;
import com.bubbinator91.conversion.temperature.TemperatureTestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all of the tests in all of the suites.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        LengthTestSuite.class,
        SpeedTestSuite.class,
        TemperatureTestSuite.class
})
public class AllTestSuite {}
