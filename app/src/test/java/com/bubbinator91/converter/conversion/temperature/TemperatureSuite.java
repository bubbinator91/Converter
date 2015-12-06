package com.bubbinator91.converter.conversion.temperature;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all of the temperature conversion tests.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CelsiusTest.class,
        FahrenheitTest.class,
        KelvinTest.class
})
public class TemperatureSuite {}
