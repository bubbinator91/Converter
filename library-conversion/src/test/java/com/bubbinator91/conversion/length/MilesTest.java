package com.bubbinator91.conversion.length;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link Miles} class.
 */
@RunWith(JUnit4.class)
public class MilesTest {

    @Test
    public void testGetInstance() throws Exception {

    }

    @Test
    public void testToAll() throws Exception {
        List<String> results = Miles.getInstance().toAll("8.569852369523695441288", 10).getFirst();

        assertNotNull(results);
        assertEquals("542985.8461330213", results.get(0));
        assertEquals("45248.8205110851", results.get(1));
        assertEquals("15082.9401703617", results.get(2));
        assertEquals("13791840.4917787421", results.get(3));
        assertEquals("1379184.0491778742", results.get(4));
        assertEquals("13791.8404917787", results.get(5));
        assertEquals("13.7918404918", results.get(6));

        results = Miles.getInstance().toAll("8.569852369523695441288", 5).getFirst();

        assertNotNull(results);
        assertEquals("542985.84613", results.get(0));
        assertEquals("45248.82051", results.get(1));
        assertEquals("15082.94017", results.get(2));
        assertEquals("13791840.49178", results.get(3));
        assertEquals("1379184.04918", results.get(4));
        assertEquals("13791.84049", results.get(5));
        assertEquals("13.79184", results.get(6));
    }

    @Test
    public void testToInches() throws Exception {
        assertEquals("542985.8461330213",
                Miles.getInstance().toInches("8.569852369523695441288", 10));
        assertEquals("542985.84613",
                Miles.getInstance().toInches("8.569852369523695441288", 5));
    }

    @Test
    public void testToFeet() throws Exception {
        assertEquals("45248.8205110851",
                Miles.getInstance().toFeet("8.569852369523695441288", 10));
        assertEquals("45248.82051",
                Miles.getInstance().toFeet("8.569852369523695441288", 5));
    }

    @Test
    public void testToYards() throws Exception {
        assertEquals("15082.9401703617",
                Miles.getInstance().toYards("8.569852369523695441288", 10));
        assertEquals("15082.94017",
                Miles.getInstance().toYards("8.569852369523695441288", 5));
    }

    @Test
    public void testToMillimeters() throws Exception {
        assertEquals("13791840.4917787421",
                Miles.getInstance().toMillimeters("8.569852369523695441288", 10));
        assertEquals("13791840.49178",
                Miles.getInstance().toMillimeters("8.569852369523695441288", 5));
    }

    @Test
    public void testToCentimeters() throws Exception {
        assertEquals("1379184.0491778742",
                Miles.getInstance().toCentimeters("8.569852369523695441288", 10));
        assertEquals("1379184.04918",
                Miles.getInstance().toCentimeters("8.569852369523695441288", 5));
    }

    @Test
    public void testToMeters() throws Exception {
        assertEquals("13791.8404917787",
                Miles.getInstance().toMeters("8.569852369523695441288", 10));
        assertEquals("13791.84049",
                Miles.getInstance().toMeters("8.569852369523695441288", 5));
    }

    @Test
    public void testToKilometers() throws Exception {
        assertEquals("13.7918404918",
                Miles.getInstance().toKilometers("8.569852369523695441288", 10));
        assertEquals("13.79184",
                Miles.getInstance().toKilometers("8.569852369523695441288", 5));
    }
}