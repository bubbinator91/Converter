package com.bubbinator91.converter.tests.conversion.speed;

import com.bubbinator91.converter.conversion.speed.KilometersPerHour;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link KilometersPerHour} class.
 */
@RunWith(JUnit4.class)
public class KilometersPerHourTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        KilometersPerHour.toAll("7.5806235947", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("6.9085589774", results.get(0));
                    assertEquals("4.0932092844", results.get(1));
                    assertEquals("2.1057287763", results.get(2));
                    assertEquals("4.7103811209", results.get(3));
                });

        KilometersPerHour.toAll("7.5806235947", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("6.90856", results.get(0));
                    assertEquals("4.09321", results.get(1));
                    assertEquals("2.10573", results.get(2));
                    assertEquals("4.71038", results.get(3));
                });
    }

    @Test
    public void testToFeetPerSecond() throws Exception {
        assertEquals("6.9085589774", KilometersPerHour.toFeetPerSecond("7.5806235947", 10));
        assertEquals("6.90856", KilometersPerHour.toFeetPerSecond("7.5806235947", 5));
    }

    @Test
    public void testToKnots() throws Exception {
        assertEquals("4.0932092844", KilometersPerHour.toKnots("7.5806235947", 10));
        assertEquals("4.09321", KilometersPerHour.toKnots("7.5806235947", 5));
    }

    @Test
    public void testToMetersPerSecond() throws Exception {
        assertEquals("2.1057287763", KilometersPerHour.toMetersPerSecond("7.5806235947", 10));
        assertEquals("2.10573", KilometersPerHour.toMetersPerSecond("7.5806235947", 5));
    }

    @Test
    public void testToMilesPerHour() throws Exception {
        assertEquals("4.7103811209", KilometersPerHour.toMilesPerHour("7.5806235947", 10));
        assertEquals("4.71038", KilometersPerHour.toMilesPerHour("7.5806235947", 5));
    }
}