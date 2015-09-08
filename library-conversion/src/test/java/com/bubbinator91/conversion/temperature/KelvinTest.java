package com.bubbinator91.conversion.temperature;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link Kelvin} class.
 */
@RunWith(JUnit4.class)
public class KelvinTest {

    @Test
    public void testGetInstance() throws Exception {
        Kelvin kelvin = Kelvin.getInstance();

        assertNotNull(kelvin);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results = Kelvin.getInstance().toAll("310.9764326946", 10);

        assertNotNull(results);
        assertEquals("37.8264326946", results.get(0));
        assertEquals("100.0875788503", results.get(1));

        results = Kelvin.getInstance().toAll("310.9764326946", 5);

        assertNotNull(results);
        assertEquals("37.82643", results.get(0));
        assertEquals("100.08758", results.get(1));
    }

    @Test
    public void testToCelsius() throws Exception {
        assertEquals("37.8264326946", Kelvin.getInstance().toCelsius("310.9764326946", 10));
        assertEquals("37.82643", Kelvin.getInstance().toCelsius("310.9764326946", 5));
    }

    @Test
    public void testToFahrenheit() throws Exception {
        assertEquals("100.0875788503", Kelvin.getInstance().toFahrenheit("310.9764326946", 10));
        assertEquals("100.08758", Kelvin.getInstance().toFahrenheit("310.9764326946", 5));
    }
}