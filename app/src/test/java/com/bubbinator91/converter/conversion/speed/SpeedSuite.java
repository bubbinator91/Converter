package com.bubbinator91.converter.conversion.speed;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all of the speed conversion tests.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        FeetPerSecondTest.class,
        KilometersPerHourTest.class,
        KnotsTest.class,
        MetersPerSecondTest.class,
        MilesPerHourTest.class,
        SpeedTest.class
})
public class SpeedSuite {}
