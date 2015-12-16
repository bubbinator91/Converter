package com.bubbinator91.converter.conversion.length;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link Centimeters} class.
 */
@RunWith(JUnit4.class)
public class CentimetersTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        Centimeters.toAll("56943.98653164976", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("22418.8923352952", results.get(0));
                    assertEquals("1868.2410279413", results.get(1));
                    assertEquals("622.7470093138", results.get(2));
                    assertEquals("0.353833528", results.get(3));
                    assertEquals("569439.8653164976", results.get(4));
                    assertEquals("569.4398653165", results.get(5));
                    assertEquals("0.5694398653", results.get(6));
                });

        Centimeters.toAll("56943.98653164976", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("22418.89234", results.get(0));
                    assertEquals("1868.24103", results.get(1));
                    assertEquals("622.74701", results.get(2));
                    assertEquals("0.35383", results.get(3));
                    assertEquals("569439.86532", results.get(4));
                    assertEquals("569.43987", results.get(5));
                    assertEquals("0.56944", results.get(6));
                });
    }

    @Test
    public void testToInches() throws Exception {
        assertEquals("22418.8923352952", Centimeters.toInches("56943.98653164976", 10));
        assertEquals("22418.89234", Centimeters.toInches("56943.98653164976", 5));
    }

    @Test
    public void testToFeet() throws Exception {
        assertEquals("1868.2410279413", Centimeters.toFeet("56943.98653164976", 10));
        assertEquals("1868.24103", Centimeters.toFeet("56943.98653164976", 5));
    }

    @Test
    public void testToYards() throws Exception {
        assertEquals("622.7470093138", Centimeters.toYards("56943.98653164976", 10));
        assertEquals("622.74701", Centimeters.toYards("56943.98653164976", 5));
    }

    @Test
    public void testToMiles() throws Exception {
        assertEquals("0.353833528", Centimeters.toMiles("56943.98653164976", 10));
        assertEquals("0.35383", Centimeters.toMiles("56943.98653164976", 5));
    }

    @Test
    public void testToMillimeters() throws Exception {
        assertEquals("569439.8653164976", Centimeters.toMillimeters("56943.98653164976", 10));
        assertEquals("569439.86532",
                Centimeters.toMillimeters("56943.98653164976", 5));
    }

    @Test
    public void testToMeters() throws Exception {
        assertEquals("569.4398653165", Centimeters.toMeters("56943.98653164976", 10));
        assertEquals("569.43987", Centimeters.toMeters("56943.98653164976", 5));
    }

    @Test
    public void testToKilometers() throws Exception {
        assertEquals("0.5694398653", Centimeters.toKilometers("56943.98653164976", 10));
        assertEquals("0.56944", Centimeters.toKilometers("56943.98653164976", 5));
    }
}