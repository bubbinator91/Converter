package com.bubbinator91.converter.conversion.datatransferspeed;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all of the data transfer speed conversion tests.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BitsPerSecondTest.class,
        BytesPerSecondTest.class,
        GigabitsPerSecondTest.class,
        GigabytesPerSecondTest.class,
        KilobitsPerSecondTest.class,
        KilobytesPerSecondTest.class,
        MegabitsPerSecondTest.class,
        MegabytesPerSecondTest.class,
        TerabitsPerSecondTest.class,
        TerabytesPerSecondTest.class
})
public class DataTransferSpeedSuite {}
