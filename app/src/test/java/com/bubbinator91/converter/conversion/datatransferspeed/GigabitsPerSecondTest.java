package com.bubbinator91.converter.conversion.datatransferspeed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link GigabitsPerSecond} class.
 */
@RunWith(JUnit4.class)
public class GigabitsPerSecondTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        GigabitsPerSecond.toAll("8.946731926437619", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("8946731926.437619", results.get(0));
                    assertEquals("1118341490.804702375", results.get(1));
                    assertEquals("8946731.926437619", results.get(2));
                    assertEquals("1118341.4908047024", results.get(3));
                    assertEquals("8946.7319264376", results.get(4));
                    assertEquals("1118.3414908047", results.get(5));
                    assertEquals("1.1183414908", results.get(6));
                    assertEquals("0.0089467319", results.get(7));
                    assertEquals("0.0011183415", results.get(8));
                });

        GigabitsPerSecond.toAll("8.946731926437619", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("8946731926.43762", results.get(0));
                    assertEquals("1118341490.8047", results.get(1));
                    assertEquals("8946731.92644", results.get(2));
                    assertEquals("1118341.4908", results.get(3));
                    assertEquals("8946.73193", results.get(4));
                    assertEquals("1118.34149", results.get(5));
                    assertEquals("1.11834", results.get(6));
                    assertEquals("0.00895", results.get(7));
                    assertEquals("0.00112", results.get(8));
                });
    }

    @Test
    public void testToBitsPerSecond() throws Exception {
        assertEquals("8946731926.437619",
                GigabitsPerSecond.toBitsPerSecond("8.946731926437619", 10));
        assertEquals("8946731926.43762",
                GigabitsPerSecond.toBitsPerSecond("8.946731926437619", 5));
    }

    @Test
    public void testToBytesPerSecond() throws Exception {
        assertEquals("1118341490.804702375",
                GigabitsPerSecond.toBytesPerSecond("8.946731926437619", 10));
        assertEquals("1118341490.8047",
                GigabitsPerSecond.toBytesPerSecond("8.946731926437619", 5));
    }

    @Test
    public void testToKilobitsPerSecond() throws Exception {
        assertEquals("8946731.926437619",
                GigabitsPerSecond.toKilobitsPerSecond("8.946731926437619", 10));
        assertEquals("8946731.92644",
                GigabitsPerSecond.toKilobitsPerSecond("8.946731926437619", 5));
    }

    @Test
    public void testToKilobytesPerSecond() throws Exception {
        assertEquals("1118341.4908047024",
                GigabitsPerSecond.toKilobytesPerSecond("8.946731926437619", 10));
        assertEquals("1118341.4908",
                GigabitsPerSecond.toKilobytesPerSecond("8.946731926437619", 5));
    }

    @Test
    public void testToMegabitsPerSecond() throws Exception {
        assertEquals("8946.7319264376",
                GigabitsPerSecond.toMegabitsPerSecond("8.946731926437619", 10));
        assertEquals("8946.73193",
                GigabitsPerSecond.toMegabitsPerSecond("8.946731926437619", 5));
    }

    @Test
    public void testToMegabytesPerSecond() throws Exception {
        assertEquals("1118.3414908047",
                GigabitsPerSecond.toMegabytesPerSecond("8.946731926437619", 10));
        assertEquals("1118.34149",
                GigabitsPerSecond.toMegabytesPerSecond("8.946731926437619", 5));
    }

    @Test
    public void testToGigabytesPerSecond() throws Exception {
        assertEquals("1.1183414908",
                GigabitsPerSecond.toGigabytesPerSecond("8.946731926437619", 10));
        assertEquals("1.11834",
                GigabitsPerSecond.toGigabytesPerSecond("8.946731926437619", 5));
    }

    @Test
    public void testToTerabitsPerSecond() throws Exception {
        assertEquals("0.0089467319",
                GigabitsPerSecond.toTerabitsPerSecond("8.946731926437619", 10));
        assertEquals("0.00895",
                GigabitsPerSecond.toTerabitsPerSecond("8.946731926437619", 5));
    }

    @Test
    public void testToTerabytesPerSecond() throws Exception {
        assertEquals("0.0011183415",
                GigabitsPerSecond.toTerabytesPerSecond("8.946731926437619", 10));
        assertEquals("0.00112",
                GigabitsPerSecond.toTerabytesPerSecond("8.946731926437619", 5));
    }
}