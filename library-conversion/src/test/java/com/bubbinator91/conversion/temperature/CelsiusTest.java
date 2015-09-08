package com.bubbinator91.conversion.temperature;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link Celsius} class.
 */
@RunWith(JUnit4.class)
public class CelsiusTest {

    @Test
    public void testGetInstance() throws Exception {
        Celsius celsius = Celsius.getInstance();

        assertNotNull(celsius);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results = Celsius.getInstance().toAll("20.9764956236", 10);

        assertNotNull(results);
        assertEquals("69.7576921225", results.get(0));
        assertEquals("294.1264956236", results.get(1));

        results = Celsius.getInstance().toAll("20.9764956236", 5);

        assertNotNull(results);
        assertEquals("69.75769", results.get(0));
        assertEquals("294.1265", results.get(1));
    }

    @Test
    public void testToFahrenheit() throws Exception {
        assertEquals("69.7576921225", Celsius.getInstance().toFahrenheit("20.9764956236", 10));
        assertEquals("69.75769", Celsius.getInstance().toFahrenheit("20.9764956236", 5));
    }

    @Test
    public void testToKelvin() throws Exception {
        assertEquals("294.1264956236", Celsius.getInstance().toKelvin("20.9764956236", 10));
        assertEquals("294.1265", Celsius.getInstance().toKelvin("20.9764956236", 5));
    }
}