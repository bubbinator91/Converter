package com.bubbinator91.converter.conversion.speed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link MetersPerSecond} class.
 */
@RunWith(JUnit4.class)
public class MetersPerSecondTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        MetersPerSecond.toAll("2.894653261712", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("9.4968939033", results.get(0));
                    assertEquals("5.6267558003", results.get(1));
                    assertEquals("10.4207517422", results.get(2));
                    assertEquals("6.475154934", results.get(3));
                });

        MetersPerSecond.toAll("2.894653261712", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("9.49689", results.get(0));
                    assertEquals("5.62676", results.get(1));
                    assertEquals("10.42075", results.get(2));
                    assertEquals("6.47515", results.get(3));
                });
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