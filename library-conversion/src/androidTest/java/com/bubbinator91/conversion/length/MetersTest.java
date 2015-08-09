package com.bubbinator91.conversion.length;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link Meters} class.
 */
@RunWith(AndroidJUnit4.class)
public class MetersTest {

    @Test
    public void testToAll() throws Exception {
        List<String> results = Meters.toAll("824.3642518597643169", 10).first;

        assertNotNull(results);
        assertEquals("32455.2855062899", results.get(0));
        assertEquals("2704.6071255242", results.get(1));
        assertEquals("901.5357085081", results.get(2));
        assertEquals("0.512236198", results.get(3));
        assertEquals("824364.2518597643", results.get(4));
        assertEquals("82436.4251859764", results.get(5));
        assertEquals("0.8243642519", results.get(6));

        results = Meters.toAll("824.3642518597643169", 5).first;

        assertNotNull(results);
        assertEquals("32455.28551", results.get(0));
        assertEquals("2704.60713", results.get(1));
        assertEquals("901.53571", results.get(2));
        assertEquals("0.51224", results.get(3));
        assertEquals("824364.25186", results.get(4));
        assertEquals("82436.42519", results.get(5));
        assertEquals("0.82436", results.get(6));
    }

    @Test
    public void testToInches() throws Exception {
        assertEquals("32455.2855062899", Meters.toInches("824.3642518597643169", 10));
        assertEquals("32455.28551", Meters.toInches("824.3642518597643169", 5));
    }

    @Test
    public void testToFeet() throws Exception {
        assertEquals("2704.6071255242", Meters.toFeet("824.3642518597643169", 10));
        assertEquals("2704.60713", Meters.toFeet("824.3642518597643169", 5));
    }

    @Test
    public void testToYards() throws Exception {
        assertEquals("901.5357085081", Meters.toYards("824.3642518597643169", 10));
        assertEquals("901.53571", Meters.toYards("824.3642518597643169", 5));
    }

    @Test
    public void testToMiles() throws Exception {
        assertEquals("0.512236198", Meters.toMiles("824.3642518597643169", 10));
        assertEquals("0.51224", Meters.toMiles("824.3642518597643169", 5));
    }

    @Test
    public void testToMillimeters() throws Exception {
        assertEquals("824364.2518597643", Meters.toMillimeters("824.3642518597643169", 10));
        assertEquals("824364.25186", Meters.toMillimeters("824.3642518597643169", 5));
    }

    @Test
    public void testToCentimeters() throws Exception {
        assertEquals("82436.4251859764", Meters.toCentimeters("824.3642518597643169", 10));
        assertEquals("82436.42519", Meters.toCentimeters("824.3642518597643169", 5));
    }

    @Test
    public void testToKilometers() throws Exception {
        assertEquals("0.8243642519", Meters.toKilometers("824.3642518597643169", 10));
        assertEquals("0.82436", Meters.toKilometers("824.3642518597643169", 5));
    }
}