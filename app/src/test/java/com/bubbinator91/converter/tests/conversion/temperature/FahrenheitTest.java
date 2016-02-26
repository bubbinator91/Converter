package com.bubbinator91.converter.tests.conversion.temperature;

import com.bubbinator91.converter.conversion.temperature.Fahrenheit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link Fahrenheit} class.
 */
@RunWith(JUnit4.class)
public class FahrenheitTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        Fahrenheit.toAll("80.9467316594316", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("27.1926286997", results.get(0));
                    assertEquals("300.3426286997", results.get(1));
                });

        Fahrenheit.toAll("80.9467316594316", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("27.19263", results.get(0));
                    assertEquals("300.34263", results.get(1));
                });
    }

    @Test
    public void testToCelsius() throws Exception {
        assertEquals("27.1926286997", Fahrenheit.toCelsius("80.9467316594316", 10));
        assertEquals("27.19263", Fahrenheit.toCelsius("80.9467316594316", 5));
    }

    @Test
    public void testToKelvin() throws Exception {
        assertEquals("300.3426286997", Fahrenheit.toKelvin("80.9467316594316", 10));
        assertEquals("300.34263", Fahrenheit.toKelvin("80.9467316594316", 5));
    }
}