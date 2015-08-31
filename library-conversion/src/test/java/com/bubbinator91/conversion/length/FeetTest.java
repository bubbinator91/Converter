package com.bubbinator91.conversion.length;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link Feet} class.
 */
@RunWith(JUnit4.class)
public class FeetTest {

    @Test
    public void testGetInstance() throws Exception {
        Feet feet = Feet.getInstance();

        assertNotNull(feet);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results = Feet.getInstance().toAll("10.694673465926534", 10).getFirst();

        assertNotNull(results);
        assertEquals("128.3360815911", results.get(0));
        assertEquals("3.5648911553", results.get(1));
        assertEquals("0.0020255063", results.get(2));
        assertEquals("3259.7364724144", results.get(3));
        assertEquals("325.9736472414", results.get(4));
        assertEquals("3.2597364724", results.get(5));
        assertEquals("0.0032597365", results.get(6));

        results = Feet.getInstance().toAll("10.694673465926534", 5).getFirst();

        assertNotNull(results);
        assertEquals("128.33608", results.get(0));
        assertEquals("3.56489", results.get(1));
        assertEquals("0.00203", results.get(2));
        assertEquals("3259.73647", results.get(3));
        assertEquals("325.97365", results.get(4));
        assertEquals("3.25974", results.get(5));
        assertEquals("0.00326", results.get(6));
    }

    @Test
    public void testToInches() throws Exception {
        assertEquals("128.3360815911", Feet.getInstance().toInches("10.694673465926534", 10));
        assertEquals("128.33608", Feet.getInstance().toInches("10.694673465926534", 5));
    }

    @Test
    public void testToYards() throws Exception {
        assertEquals("3.5648911553", Feet.getInstance().toYards("10.694673465926534", 10));
        assertEquals("3.56489", Feet.getInstance().toYards("10.694673465926534", 5));
    }

    @Test
    public void testToMiles() throws Exception {
        assertEquals("0.0020255063", Feet.getInstance().toMiles("10.694673465926534", 10));
        assertEquals("0.00203", Feet.getInstance().toMiles("10.694673465926534", 5));
    }

    @Test
    public void testToMillimeters() throws Exception {
        assertEquals("3259.7364724144", Feet.getInstance().toMillimeters("10.694673465926534", 10));
        assertEquals("3259.73647", Feet.getInstance().toMillimeters("10.694673465926534", 5));
    }

    @Test
    public void testToCentimeters() throws Exception {
        assertEquals("325.9736472414", Feet.getInstance().toCentimeters("10.694673465926534", 10));
        assertEquals("325.97365", Feet.getInstance().toCentimeters("10.694673465926534", 5));
    }

    @Test
    public void testToMeters() throws Exception {
        assertEquals("3.2597364724", Feet.getInstance().toMeters("10.694673465926534", 10));
        assertEquals("3.25974", Feet.getInstance().toMeters("10.694673465926534", 5));
    }

    @Test
    public void testToKilometers() throws Exception {
        assertEquals("0.0032597365", Feet.getInstance().toKilometers("10.694673465926534", 10));
        assertEquals("0.00326", Feet.getInstance().toKilometers("10.694673465926534", 5));
    }
}