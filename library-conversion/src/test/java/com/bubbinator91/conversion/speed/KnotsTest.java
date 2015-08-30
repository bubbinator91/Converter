package com.bubbinator91.conversion.speed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link Knots} class.
 */
@RunWith(JUnit4.class)
public class KnotsTest {

    @Test
    public void testGetInstance() throws Exception {
        Knots knots = Knots.getInstance();

        assertNotNull(knots);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results = Knots.getInstance().toAll("3.465923564312", 10).getFirst();

        assertNotNull(results);
        assertEquals("5.8498199558", results.get(0));
        assertEquals("6.4188904411", results.get(1));
        assertEquals("1.7830251225", results.get(2));
        assertEquals("3.9885136062", results.get(3));

        results = Knots.getInstance().toAll("3.465923564312", 5).getFirst();

        assertNotNull(results);
        assertEquals("5.84982", results.get(0));
        assertEquals("6.41889", results.get(1));
        assertEquals("1.78303", results.get(2));
        assertEquals("3.98851", results.get(3));
    }

    @Test
    public void testToFeetPerSecond() throws Exception {
        assertEquals("5.8498199558", Knots.getInstance().toFeetPerSecond("3.465923564312", 10));
        assertEquals("5.84982", Knots.getInstance().toFeetPerSecond("3.465923564312", 5));
    }

    @Test
    public void testToKilometersPerHour() throws Exception {
        assertEquals("6.4188904411", Knots.getInstance().toKilometersPerHour("3.465923564312", 10));
        assertEquals("6.41889", Knots.getInstance().toKilometersPerHour("3.465923564312", 5));
    }

    @Test
    public void testToMetersPerSecond() throws Exception {
        assertEquals("1.7830251225", Knots.getInstance().toMetersPerSecond("3.465923564312", 10));
        assertEquals("1.78303", Knots.getInstance().toMetersPerSecond("3.465923564312", 5));
    }

    @Test
    public void testToMilesPerHour() throws Exception {
        assertEquals("3.9885136062", Knots.getInstance().toMilesPerHour("3.465923564312", 10));
        assertEquals("3.98851", Knots.getInstance().toMilesPerHour("3.465923564312", 5));
    }
}