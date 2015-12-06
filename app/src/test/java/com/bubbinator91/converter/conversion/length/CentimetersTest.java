package com.bubbinator91.converter.conversion.length;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link Centimeters} class.
 */
@RunWith(JUnit4.class)
public class CentimetersTest {

    @Test
    public void testGetInstance() throws Exception {
        Centimeters centimeters = Centimeters.getInstance();

        assertNotNull(centimeters);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results = Centimeters
                .getInstance()
                .toAll("56943.98653164976", 10)
                .getFirst();

        assertNotNull(results);
        assertEquals("22418.8923352952", results.get(0));
        assertEquals("1868.2410279413", results.get(1));
        assertEquals("622.7470093138", results.get(2));
        assertEquals("0.353833528", results.get(3));
        assertEquals("569439.8653164976", results.get(4));
        assertEquals("569.4398653165", results.get(5));
        assertEquals("0.5694398653", results.get(6));

        results = Centimeters
                .getInstance()
                .toAll("56943.98653164976", 5)
                .getFirst();

        assertNotNull(results);
        assertEquals("22418.89234", results.get(0));
        assertEquals("1868.24103", results.get(1));
        assertEquals("622.74701", results.get(2));
        assertEquals("0.35383", results.get(3));
        assertEquals("569439.86532", results.get(4));
        assertEquals("569.43987", results.get(5));
        assertEquals("0.56944", results.get(6));
    }

    @Test
    public void testToInches() throws Exception {
        assertEquals("22418.8923352952",
                Centimeters
                        .getInstance()
                        .toInches("56943.98653164976", 10));
        assertEquals("22418.89234",
                Centimeters
                        .getInstance()
                        .toInches("56943.98653164976", 5));
    }

    @Test
    public void testToFeet() throws Exception {
        assertEquals("1868.2410279413",
                Centimeters
                        .getInstance()
                        .toFeet("56943.98653164976", 10));
        assertEquals("1868.24103",
                Centimeters
                        .getInstance()
                        .toFeet("56943.98653164976", 5));
    }

    @Test
    public void testToYards() throws Exception {
        assertEquals("622.7470093138",
                Centimeters
                        .getInstance()
                        .toYards("56943.98653164976", 10));
        assertEquals("622.74701",
                Centimeters
                        .getInstance()
                        .toYards("56943.98653164976", 5));
    }

    @Test
    public void testToMiles() throws Exception {
        assertEquals("0.353833528",
                Centimeters
                        .getInstance()
                        .toMiles("56943.98653164976", 10));
        assertEquals("0.35383",
                Centimeters
                        .getInstance()
                        .toMiles("56943.98653164976", 5));
    }

    @Test
    public void testToMillimeters() throws Exception {
        assertEquals("569439.8653164976",
                Centimeters
                        .getInstance()
                        .toMillimeters("56943.98653164976", 10));
        assertEquals("569439.86532",
                Centimeters
                        .getInstance()
                        .toMillimeters("56943.98653164976", 5));
    }

    @Test
    public void testToMeters() throws Exception {
        assertEquals("569.4398653165",
                Centimeters
                        .getInstance()
                        .toMeters("56943.98653164976", 10));
        assertEquals("569.43987",
                Centimeters
                        .getInstance()
                        .toMeters("56943.98653164976", 5));
    }

    @Test
    public void testToKilometers() throws Exception {
        assertEquals("0.5694398653",
                Centimeters
                        .getInstance()
                        .toKilometers("56943.98653164976", 10));
        assertEquals("0.56944",
                Centimeters
                        .getInstance()
                        .toKilometers("56943.98653164976", 5));
    }
}