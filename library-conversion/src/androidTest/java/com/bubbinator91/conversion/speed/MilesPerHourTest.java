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
public class MilesPerHourTest {

    @Test
    public void testToAll() throws Exception {
        List<String> results = MilesPerHour.toAll("5.346592653461", 10).first;

        assertNotNull(results);
        assertEquals("7.8416692251", results.get(0));
        assertEquals("4.646061991", results.get(1));
        assertEquals("8.6045068073", results.get(2));
        assertEquals("2.3901407798", results.get(3));

        results = MilesPerHour.toAll("5.346592653461", 5).first;

        assertNotNull(results);
        assertEquals("7.84167", results.get(0));
        assertEquals("4.64606", results.get(1));
        assertEquals("8.60451", results.get(2));
        assertEquals("2.39014", results.get(3));
    }

    @Test
    public void testToFeetPerSecond() throws Exception {
        assertEquals("7.8416692251", MilesPerHour.toFeetPerSecond("5.346592653461", 10));
        assertEquals("7.84167", MilesPerHour.toFeetPerSecond("5.346592653461", 5));
    }

    @Test
    public void testToKnots() throws Exception {
        assertEquals("4.646061991", MilesPerHour.toKnots("5.346592653461", 10));
        assertEquals("4.64606", MilesPerHour.toKnots("5.346592653461", 5));
    }

    @Test
    public void testToKilometersPerHour() throws Exception {
        assertEquals("8.6045068073", MilesPerHour.toKilometersPerHour("5.346592653461", 10));
        assertEquals("8.60451", MilesPerHour.toKilometersPerHour("5.346592653461", 5));
    }

    @Test
    public void testToMetersPerSecond() throws Exception {
        assertEquals("2.3901407798", MilesPerHour.toMetersPerSecond("5.346592653461", 10));
        assertEquals("2.39014", MilesPerHour.toMetersPerSecond("5.346592653461", 5));
    }
}