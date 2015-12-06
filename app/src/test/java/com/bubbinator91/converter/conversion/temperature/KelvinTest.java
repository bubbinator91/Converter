package com.bubbinator91.converter.conversion.temperature;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link Kelvin} class.
 */
@RunWith(JUnit4.class)
public class KelvinTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        Kelvin.toAll("310.9764326946", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("37.8264326946", results.get(0));
                    assertEquals("100.0875788503", results.get(1));
                });

        Kelvin.toAll("310.9764326946", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("37.82643", results.get(0));
                    assertEquals("100.08758", results.get(1));
                });
    }

    @Test
    public void testToCelsius() throws Exception {
        assertEquals("37.8264326946", Kelvin.toCelsius("310.9764326946", 10));
        assertEquals("37.82643", Kelvin.toCelsius("310.9764326946", 5));
    }

    @Test
    public void testToFahrenheit() throws Exception {
        assertEquals("100.0875788503", Kelvin.toFahrenheit("310.9764326946", 10));
        assertEquals("100.08758", Kelvin.toFahrenheit("310.9764326946", 5));
    }
}