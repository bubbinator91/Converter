package com.bubbinator91.conversion.length;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all of the length conversion tests.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CentimetersTest.class,
        FeetTest.class,
        InchesTest.class,
        KilometersTest.class,
        MetersTest.class,
        MilesTest.class,
        MillimetersTest.class,
        YardsTest.class
})
public class LengthTestSuite {}
