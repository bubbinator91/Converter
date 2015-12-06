package com.bubbinator91.converter.conversion.speed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link KilometersPerHour} class.
 */
@RunWith(JUnit4.class)
public class KilometersPerHourTest {

    @Test
    public void testGetInstance() throws Exception {
        KilometersPerHour kph = KilometersPerHour.getInstance();

        assertNotNull(kph);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results = KilometersPerHour
                .getInstance()
                .toAll("7.5806235947", 10)
                .getFirst();

        assertNotNull(results);
        assertEquals("6.9085589774", results.get(0));
        assertEquals("4.0932092844", results.get(1));
        assertEquals("2.1057287763", results.get(2));
        assertEquals("4.7103811209", results.get(3));

        results = KilometersPerHour
                .getInstance()
                .toAll("7.5806235947", 5)
                .getFirst();

        assertNotNull(results);
        assertEquals("6.90856", results.get(0));
        assertEquals("4.09321", results.get(1));
        assertEquals("2.10573", results.get(2));
        assertEquals("4.71038", results.get(3));
    }

    @Test
    public void testToFeetPerSecond() throws Exception {
        assertEquals("6.9085589774",
                KilometersPerHour
                        .getInstance()
                        .toFeetPerSecond("7.5806235947", 10));
        assertEquals("6.90856",
                KilometersPerHour
                        .getInstance()
                        .toFeetPerSecond("7.5806235947", 5));
    }

    @Test
    public void testToKnots() throws Exception {
        assertEquals("4.0932092844",
                KilometersPerHour
                        .getInstance()
                        .toKnots("7.5806235947", 10));
        assertEquals("4.09321",
                KilometersPerHour
                        .getInstance()
                        .toKnots("7.5806235947", 5));
    }

    @Test
    public void testToMetersPerSecond() throws Exception {
        assertEquals("2.1057287763",
                KilometersPerHour
                        .getInstance()
                        .toMetersPerSecond("7.5806235947", 10));
        assertEquals("2.10573",
                KilometersPerHour
                        .getInstance()
                        .toMetersPerSecond("7.5806235947", 5));
    }

    @Test
    public void testToMilesPerHour() throws Exception {
        assertEquals("4.7103811209",
                KilometersPerHour
                        .getInstance()
                        .toMilesPerHour("7.5806235947", 10));
        assertEquals("4.71038",
                KilometersPerHour
                        .getInstance()
                        .toMilesPerHour("7.5806235947", 5));
    }
}