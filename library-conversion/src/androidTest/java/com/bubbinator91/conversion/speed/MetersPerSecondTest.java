package com.bubbinator91.conversion.speed;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link MetersPerSecond} class.
 */
@RunWith(AndroidJUnit4.class)
public class MetersPerSecondTest {

    @Test
    public void testToAll() throws Exception {
        List<String> results = MetersPerSecond.toAll("2.894653261712", 10).first;

        assertNotNull(results);
        assertEquals("9.4968939033", results.get(0));
        assertEquals("5.6267558003", results.get(1));
        assertEquals("10.4207517422", results.get(2));
        assertEquals("6.475154934", results.get(3));

        results = MetersPerSecond.toAll("2.894653261712", 5).first;

        assertNotNull(results);
        assertEquals("9.49689", results.get(0));
        assertEquals("5.62676", results.get(1));
        assertEquals("10.42075", results.get(2));
        assertEquals("6.47515", results.get(3));
    }

    @Test
    public void testToFeetPerSecond() throws Exception {
        assertEquals("9.4968939033", MetersPerSecond.toFeetPerSecond("2.894653261712", 10));
        assertEquals("9.49689", MetersPerSecond.toFeetPerSecond("2.894653261712", 5));
    }

    @Test
    public void testToKnots() throws Exception {
        assertEquals("5.6267558003", MetersPerSecond.toKnots("2.894653261712", 10));
        assertEquals("5.62676", MetersPerSecond.toKnots("2.894653261712", 5));
    }

    @Test
    public void testToKilometersPerHour() throws Exception {
        assertEquals("10.4207517422", MetersPerSecond.toKilometersPerHour("2.894653261712", 10));
        assertEquals("10.42075", MetersPerSecond.toKilometersPerHour("2.894653261712", 5));
    }

    @Test
    public void testToMilesPerHour() throws Exception {
        assertEquals("6.475154934", MetersPerSecond.toMilesPerHour("2.894653261712", 10));
        assertEquals("6.47515", MetersPerSecond.toMilesPerHour("2.894653261712", 5));
    }
}