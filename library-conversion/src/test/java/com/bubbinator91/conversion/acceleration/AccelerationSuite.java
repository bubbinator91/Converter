package com.bubbinator91.conversion.acceleration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all of the acceleration conversion tests.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccelerationTest.class,
        CentimetersPerSecondSquaredTest.class,
        FeetPerSecondSquaredTest.class,
        MetersPerSecondSquaredTest.class,
        StandardGravityTest.class
})
public class AccelerationSuite {}
