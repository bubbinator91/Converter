package com.bubbinator91.converter.conversion.speed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link Knots} class.
 */
@RunWith(JUnit4.class)
public class KnotsTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        Knots.toAll("3.465923564312", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("5.8498199558", results.get(0));
                    assertEquals("6.4188904411", results.get(1));
                    assertEquals("1.7830251225", results.get(2));
                    assertEquals("3.9885136062", results.get(3));
                });

        Knots.toAll("3.465923564312", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("5.84982", results.get(0));
                    assertEquals("6.41889", results.get(1));
                    assertEquals("1.78303", results.get(2));
                    assertEquals("3.98851", results.get(3));
                });
    }

    @Test
    public void testToFeetPerSecond() throws Exception {
        assertEquals("5.8498199558", Knots.toFeetPerSecond("3.465923564312", 10));
        assertEquals("5.84982", Knots.toFeetPerSecond("3.465923564312", 5));
    }

    @Test
    public void testToKilometersPerHour() throws Exception {
        assertEquals("6.4188904411", Knots.toKilometersPerHour("3.465923564312", 10));
        assertEquals("6.41889", Knots.toKilometersPerHour("3.465923564312", 5));
    }

    @Test
    public void testToMetersPerSecond() throws Exception {
        assertEquals("1.7830251225", Knots.toMetersPerSecond("3.465923564312", 10));
        assertEquals("1.78303", Knots.toMetersPerSecond("3.465923564312", 5));
    }

    @Test
    public void testToMilesPerHour() throws Exception {
        assertEquals("3.9885136062", Knots.toMilesPerHour("3.465923564312", 10));
        assertEquals("3.98851", Knots.toMilesPerHour("3.465923564312", 5));
    }
}