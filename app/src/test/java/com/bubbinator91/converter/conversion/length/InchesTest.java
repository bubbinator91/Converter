package com.bubbinator91.converter.conversion.length;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link Inches} class.
 */
@RunWith(JUnit4.class)
public class InchesTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine
        
        Inches.toAll("1.258633885023369824", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("0.1048861571", results.get(0));
                    assertEquals("0.0349620524", results.get(1));
                    assertEquals("0.0000198648", results.get(2));
                    assertEquals("31.9693006796", results.get(3));
                    assertEquals("3.196930068", results.get(4));
                    assertEquals("0.0319693007", results.get(5));
                    assertEquals("0.0000319693", results.get(6));
                });

        Inches.toAll("1.258633885023369824", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("0.10489", results.get(0));
                    assertEquals("0.03496", results.get(1));
                    assertEquals("0.00002", results.get(2));
                    assertEquals("31.9693", results.get(3));
                    assertEquals("3.19693", results.get(4));
                    assertEquals("0.03197", results.get(5));
                    assertEquals("0.00003", results.get(6));
                });
    }

    @Test
    public void testToFeet() throws Exception {
        assertEquals("0.1048861571", Inches.toFeet("1.258633885023369824", 10));
        assertEquals("0.10489", Inches.toFeet("1.258633885023369824", 5));
    }

    @Test
    public void testToYards() throws Exception {
        assertEquals("0.0349620524", Inches.toYards("1.258633885023369824", 10));
        assertEquals("0.03496", Inches.toYards("1.258633885023369824", 5));
    }

    @Test
    public void testToMiles() throws Exception {
        assertEquals("0.0000198648", Inches.toMiles("1.258633885023369824", 10));
        assertEquals("0.00002", Inches.toMiles("1.258633885023369824", 5));
    }

    @Test
    public void testToMillimeters() throws Exception {
        assertEquals("31.9693006796", Inches.toMillimeters("1.258633885023369824", 10));
        assertEquals("31.9693", Inches.toMillimeters("1.258633885023369824", 5));
    }

    @Test
    public void testToCentimeters() throws Exception {
        assertEquals("3.196930068", Inches.toCentimeters("1.258633885023369824", 10));
        assertEquals("3.19693", Inches.toCentimeters("1.258633885023369824", 5));
    }

    @Test
    public void testToMeters() throws Exception {
        assertEquals("0.0319693007", Inches.toMeters("1.258633885023369824", 10));
        assertEquals("0.03197", Inches.toMeters("1.258633885023369824", 5));
    }

    @Test
    public void testToKilometers() throws Exception {
        assertEquals("0.0000319693", Inches.toKilometers("1.258633885023369824", 10));
        assertEquals("0.00003", Inches.toKilometers("1.258633885023369824", 5));
    }
}