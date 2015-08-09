package com.bubbinator91.conversion.length;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link Kilometers} class.
 */
@RunWith(AndroidJUnit4.class)
public class KilometersTest {

    @Test
    public void testToAll() throws Exception {
        List<String> results = Kilometers.toAll("8.5649764312526591", 10).first;

        assertNotNull(results);
        assertEquals("337203.7965060102", results.get(0));
        assertEquals("28100.3163755009", results.get(1));
        assertEquals("9366.772125167", results.get(2));
        assertEquals("5.3220296166", results.get(3));
        assertEquals("8564976.4312526591", results.get(4));
        assertEquals("856497.6431252659", results.get(5));
        assertEquals("8564.9764312527", results.get(6));

        results = Kilometers.toAll("8.5649764312526591", 5).first;

        assertNotNull(results);
        assertEquals("337203.79651", results.get(0));
        assertEquals("28100.31638", results.get(1));
        assertEquals("9366.77213", results.get(2));
        assertEquals("5.32203", results.get(3));
        assertEquals("8564976.43125", results.get(4));
        assertEquals("856497.64313", results.get(5));
        assertEquals("8564.97643", results.get(6));
    }

    @Test
    public void testToInches() throws Exception {
        assertEquals("337203.7965060102", Kilometers.toInches("8.5649764312526591", 10));
        assertEquals("337203.79651", Kilometers.toInches("8.5649764312526591", 5));
    }

    @Test
    public void testToFeet() throws Exception {
        assertEquals("28100.3163755009", Kilometers.toFeet("8.5649764312526591", 10));
        assertEquals("28100.31638", Kilometers.toFeet("8.5649764312526591", 5));
    }

    @Test
    public void testToYards() throws Exception {
        assertEquals("9366.772125167", Kilometers.toYards("8.5649764312526591", 10));
        assertEquals("9366.77213", Kilometers.toYards("8.5649764312526591", 5));
    }

    @Test
    public void testToMiles() throws Exception {
        assertEquals("5.3220296166", Kilometers.toMiles("8.5649764312526591", 10));
        assertEquals("5.32203", Kilometers.toMiles("8.5649764312526591", 5));
    }

    @Test
    public void testToMillimeters() throws Exception {
        assertEquals("8564976.4312526591", Kilometers.toMillimeters("8.5649764312526591", 10));
        assertEquals("8564976.43125", Kilometers.toMillimeters("8.5649764312526591", 5));
    }

    @Test
    public void testToCentimeters() throws Exception {
        assertEquals("856497.6431252659", Kilometers.toCentimeters("8.5649764312526591", 10));
        assertEquals("856497.64313", Kilometers.toCentimeters("8.5649764312526591", 5));
    }

    @Test
    public void testToMeters() throws Exception {
        assertEquals("8564.9764312527", Kilometers.toMeters("8.5649764312526591", 10));
        assertEquals("8564.97643", Kilometers.toMeters("8.5649764312526591", 5));
    }
}