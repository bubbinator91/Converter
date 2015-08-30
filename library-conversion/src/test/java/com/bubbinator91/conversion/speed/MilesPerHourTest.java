package com.bubbinator91.conversion.speed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link MilesPerHour} class.
 */
@RunWith(JUnit4.class)
public class MilesPerHourTest {

    @Test
    public void testGetInstance() throws Exception {
        MilesPerHour mph = MilesPerHour.getInstance();

        assertNotNull(mph);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results = MilesPerHour
                .getInstance()
                .toAll("5.346592653461", 10)
                .getFirst();

        assertNotNull(results);
        assertEquals("7.8416692251", results.get(0));
        assertEquals("4.646061991", results.get(1));
        assertEquals("8.6045068073", results.get(2));
        assertEquals("2.3901407798", results.get(3));

        results = MilesPerHour
                .getInstance()
                .toAll("5.346592653461", 5)
                .getFirst();

        assertNotNull(results);
        assertEquals("7.84167", results.get(0));
        assertEquals("4.64606", results.get(1));
        assertEquals("8.60451", results.get(2));
        assertEquals("2.39014", results.get(3));
    }

    @Test
    public void testToFeetPerSecond() throws Exception {
        assertEquals("7.8416692251",
                MilesPerHour
                        .getInstance()
                        .toFeetPerSecond("5.346592653461", 10));
        assertEquals("7.84167",
                MilesPerHour
                        .getInstance()
                        .toFeetPerSecond("5.346592653461", 5));
    }

    @Test
    public void testToKnots() throws Exception {
        assertEquals("4.646061991",
                MilesPerHour
                        .getInstance()
                        .toKnots("5.346592653461", 10));
        assertEquals("4.64606",
                MilesPerHour
                        .getInstance()
                        .toKnots("5.346592653461", 5));
    }

    @Test
    public void testToKilometersPerHour() throws Exception {
        assertEquals("8.6045068073",
                MilesPerHour
                        .getInstance()
                        .toKilometersPerHour("5.346592653461", 10));
        assertEquals("8.60451",
                MilesPerHour
                        .getInstance()
                        .toKilometersPerHour("5.346592653461", 5));
    }

    @Test
    public void testToMetersPerSecond() throws Exception {
        assertEquals("2.3901407798",
                MilesPerHour
                        .getInstance()
                        .toMetersPerSecond("5.346592653461", 10));
        assertEquals("2.39014",
                MilesPerHour
                        .getInstance()
                        .toMetersPerSecond("5.346592653461", 5));
    }
}