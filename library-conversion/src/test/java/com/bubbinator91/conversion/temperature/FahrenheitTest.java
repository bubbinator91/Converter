package com.bubbinator91.conversion.temperature;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link Fahrenheit} class.
 */
@RunWith(JUnit4.class)
public class FahrenheitTest {

    @Test
    public void testGetInstance() throws Exception {
        Fahrenheit fahrenheit = Fahrenheit.getInstance();

        assertNotNull(fahrenheit);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results = Fahrenheit.getInstance().toAll("80.9467316594316", 10).getFirst();

        assertNotNull(results);
        assertEquals("27.1926286997", results.get(0));
        assertEquals("300.3426286997", results.get(1));

        results = Fahrenheit.getInstance().toAll("80.9467316594316", 5).getFirst();

        assertNotNull(results);
        assertEquals("27.19263", results.get(0));
        assertEquals("300.34263", results.get(1));
    }

    @Test
    public void testToCelsius() throws Exception {
        assertEquals("27.1926286997", Fahrenheit.getInstance().toCelsius("80.9467316594316", 10));
        assertEquals("27.19263", Fahrenheit.getInstance().toCelsius("80.9467316594316", 5));
    }

    @Test
    public void testToKelvin() throws Exception {
        assertEquals("300.3426286997", Fahrenheit.getInstance().toKelvin("80.9467316594316", 10));
        assertEquals("300.34263", Fahrenheit.getInstance().toKelvin("80.9467316594316", 5));
    }
}