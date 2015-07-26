package com.bubbinator91.conversion.temperature;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link Fahrenheit} class.
 */
@RunWith(AndroidJUnit4.class)
public class FahrenheitTest {

    @Test
    public void testToAll() throws Exception {
        List<String> results = Fahrenheit.toAll("80.9467316594316", 10).first;

        assertNotNull(results);
        assertEquals("27.1926286997", results.get(0));
        assertEquals("300.3426286997", results.get(1));

        results = Fahrenheit.toAll("80.9467316594316", 5).first;

        assertNotNull(results);
        assertEquals("27.19263", results.get(0));
        assertEquals("300.34263", results.get(1));
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