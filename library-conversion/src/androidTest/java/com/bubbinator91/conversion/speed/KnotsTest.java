package com.bubbinator91.conversion.speed;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link Knots} class.
 */
@RunWith(AndroidJUnit4.class)
public class KnotsTest {

    @Test
    public void testToAll() throws Exception {
        List<String> results = Knots.toAll("3.465923564312", 10).first;

        assertNotNull(results);
        assertEquals("5.8498199558", results.get(0));
        assertEquals("6.4188904411", results.get(1));
        assertEquals("1.7830251225", results.get(2));
        assertEquals("3.9885136062", results.get(3));

        results = Knots.toAll("3.465923564312", 5).first;

        assertNotNull(results);
        assertEquals("5.84982", results.get(0));
        assertEquals("6.41889", results.get(1));
        assertEquals("1.78303", results.get(2));
        assertEquals("3.98851", results.get(3));
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