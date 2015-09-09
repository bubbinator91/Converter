package com.bubbinator91.conversion.acceleration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link FeetPerSecondSquared} class.
 */
@RunWith(JUnit4.class)
public class FeetPerSecondSquaredTest {

    @Test
    public void testGetInstance() throws Exception {
        FeetPerSecondSquared fpss = FeetPerSecondSquared.getInstance();

        assertNotNull(fpss);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results = FeetPerSecondSquared
                .getInstance()
                .toAll("2.5064318986478525", 10);

        assertNotNull(results);
        assertEquals("76.3960442711", results.get(0));
        assertEquals("0.7639604427", results.get(1));
        assertEquals("0.077902285", results.get(2));

        results = FeetPerSecondSquared
                .getInstance()
                .toAll("2.5064318986478525", 5);

        assertNotNull(results);
        assertEquals("76.39604", results.get(0));
        assertEquals("0.76396", results.get(1));
        assertEquals("0.0779", results.get(2));
    }

    @Test
    public void testToCentimetersPerSecondSquared() throws Exception {
        assertEquals("76.3960442711",
                FeetPerSecondSquared
                        .getInstance()
                        .toCentimetersPerSecondSquared("2.5064318986478525", 10));
        assertEquals("76.39604",
                FeetPerSecondSquared
                        .getInstance()
                        .toCentimetersPerSecondSquared("2.5064318986478525", 5));
    }

    @Test
    public void testToMetersPerSecondSquared() throws Exception {
        assertEquals("0.7639604427",
                FeetPerSecondSquared
                        .getInstance()
                        .toMetersPerSecondSquared("2.5064318986478525", 10));
        assertEquals("0.76396",
                FeetPerSecondSquared
                        .getInstance()
                        .toMetersPerSecondSquared("2.5064318986478525", 5));
    }

    @Test
    public void testToStandardGravity() throws Exception {
        assertEquals("0.077902285",
                FeetPerSecondSquared
                        .getInstance()
                        .toStandardGravity("2.5064318986478525", 10));
        assertEquals("0.0779",
                FeetPerSecondSquared
                        .getInstance()
                        .toStandardGravity("2.5064318986478525", 5));
    }
}