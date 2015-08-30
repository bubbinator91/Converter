package com.bubbinator91.conversion;

import com.bubbinator91.conversion.acceleration.AccelerationSuite;
import com.bubbinator91.conversion.datatransferspeed.DataTransferSpeedSuite;
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
        DataTransferSpeedSuite.class,
        LengthTestSuite.class,
})
public class AllTestSuite {}
