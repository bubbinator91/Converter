package com.bubbinator91.converter.conversion.length;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link Millimeters} class.
 */
@RunWith(JUnit4.class)
public class MillimetersTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        Millimeters.toAll("58.85236982258084", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("2.317022434", results.get(0));
                    assertEquals("0.1930852028", results.get(1));
                    assertEquals("0.0643617343", results.get(2));
                    assertEquals("0.0000365692", results.get(3));
                    assertEquals("5.8852369823", results.get(4));
                    assertEquals("0.0588523698", results.get(5));
                    assertEquals("0.0000588524", results.get(6));
                });

        Millimeters.toAll("58.85236982258084", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("2.31702", results.get(0));
                    assertEquals("0.19309", results.get(1));
                    assertEquals("0.06436", results.get(2));
                    assertEquals("0.00004", results.get(3));
                    assertEquals("5.88524", results.get(4));
                    assertEquals("0.05885", results.get(5));
                    assertEquals("0.00006", results.get(6));
                });
    }

    @Test
    public void testToInches() throws Exception {
        assertEquals("2.317022434", Millimeters.toInches("58.85236982258084", 10));
        assertEquals("2.31702", Millimeters.toInches("58.85236982258084", 5));
    }

    @Test
    public void testToFeet() throws Exception {
        assertEquals("0.1930852028", Millimeters.toFeet("58.85236982258084", 10));
        assertEquals("0.19309", Millimeters.toFeet("58.85236982258084", 5));
    }

    @Test
    public void testToYards() throws Exception {
        assertEquals("0.0643617343", Millimeters.toYards("58.85236982258084", 10));
        assertEquals("0.06436", Millimeters.toYards("58.85236982258084", 5));
    }

    @Test
    public void testToMiles() throws Exception {
        assertEquals("0.0000365692", Millimeters.toMiles("58.85236982258084", 10));
        assertEquals("0.00004", Millimeters.toMiles("58.85236982258084", 5));
    }

    @Test
    public void testToCentimeters() throws Exception {
        assertEquals("5.8852369823", Millimeters.toCentimeters("58.85236982258084", 10));
        assertEquals("5.88524", Millimeters.toCentimeters("58.85236982258084", 5));
    }

    @Test
    public void testToMeters() throws Exception {
        assertEquals("0.0588523698", Millimeters.toMeters("58.85236982258084", 10));
        assertEquals("0.05885", Millimeters.toMeters("58.85236982258084", 5));
    }

    @Test
    public void testToKilometers() throws Exception {
        assertEquals("0.0000588524", Millimeters.toKilometers("58.85236982258084", 10));
        assertEquals("0.00006", Millimeters.toKilometers("58.85236982258084", 5));
    }
}