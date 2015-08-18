package com.bubbinator91.conversion.acceleration;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link FeetPerSecondSquared} class.
 */
@RunWith(AndroidJUnit4.class)
public class FeetPerSecondSquaredTest {

    @Test
    public void testToAll() throws Exception {
        List<String> results = FeetPerSecondSquared.toAll("2.5064318986478525", 10).first;

        assertNotNull(results);
        assertEquals("76.3960442711", results.get(0));
        assertEquals("0.7639604427", results.get(1));
        assertEquals("0.077902285", results.get(2));

        results = FeetPerSecondSquared.toAll("2.5064318986478525", 5).first;

        assertNotNull(results);
        assertEquals("76.39604", results.get(0));
        assertEquals("0.76396", results.get(1));
        assertEquals("0.0779", results.get(2));
    }

    @Test
    public void testToCentimetersPerSecondSquared() throws Exception {
        assertEquals("76.3960442711", FeetPerSecondSquared.toCentimetersPerSecondSquared("2.5064318986478525", 10));
        assertEquals("76.39604", FeetPerSecondSquared.toCentimetersPerSecondSquared("2.5064318986478525", 5));
    }

    @Test
    public void testToMetersPerSecondSquared() throws Exception {
        assertEquals("0.7639604427", FeetPerSecondSquared.toMetersPerSecondSquared("2.5064318986478525", 10));
        assertEquals("0.76396", FeetPerSecondSquared.toMetersPerSecondSquared("2.5064318986478525", 5));
    }

    @Test
    public void testToStandardGravity() throws Exception {
        assertEquals("0.077902285", FeetPerSecondSquared.toStandardGravity("2.5064318986478525", 10));
        assertEquals("0.0779", FeetPerSecondSquared.toStandardGravity("2.5064318986478525", 5));
    }
}