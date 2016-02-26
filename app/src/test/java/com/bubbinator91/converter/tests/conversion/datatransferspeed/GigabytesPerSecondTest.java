package com.bubbinator91.converter.tests.conversion.datatransferspeed;

import com.bubbinator91.converter.conversion.datatransferspeed.GigabytesPerSecond;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link GigabytesPerSecond} class.
 */
@RunWith(JUnit4.class)
public class GigabytesPerSecondTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        GigabytesPerSecond.toAll("2.8946231649761316296", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("23156985319.8090530368", results.get(0));
                    assertEquals("2894623164.9761316296", results.get(1));
                    assertEquals("23156985.319809053", results.get(2));
                    assertEquals("2894623.1649761316", results.get(3));
                    assertEquals("23156.9853198091", results.get(4));
                    assertEquals("2894.6231649761", results.get(5));
                    assertEquals("23.1569853198", results.get(6));
                    assertEquals("0.0231569853", results.get(7));
                    assertEquals("0.0028946232", results.get(8));
                });

        GigabytesPerSecond.toAll("2.8946231649761316296", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("23156985319.80905", results.get(0));
                    assertEquals("2894623164.97613", results.get(1));
                    assertEquals("23156985.31981", results.get(2));
                    assertEquals("2894623.16498", results.get(3));
                    assertEquals("23156.98532", results.get(4));
                    assertEquals("2894.62316", results.get(5));
                    assertEquals("23.15699", results.get(6));
                    assertEquals("0.02316", results.get(7));
                    assertEquals("0.00289", results.get(8));
                });
    }

    @Test
    public void testToBitsPerSecond() throws Exception {
        assertEquals("23156985319.8090530368",
                GigabytesPerSecond.toBitsPerSecond("2.8946231649761316296", 10));
        assertEquals("23156985319.80905",
                GigabytesPerSecond.toBitsPerSecond("2.8946231649761316296", 5));
    }

    @Test
    public void testToBytesPerSecond() throws Exception {
        assertEquals("2894623164.9761316296",
                GigabytesPerSecond.toBytesPerSecond("2.8946231649761316296", 10));
        assertEquals("2894623164.97613",
                GigabytesPerSecond.toBytesPerSecond("2.8946231649761316296", 5));
    }

    @Test
    public void testToKilobitsPerSecond() throws Exception {
        assertEquals("23156985.319809053",
                GigabytesPerSecond.toKilobitsPerSecond("2.8946231649761316296", 10));
        assertEquals("23156985.31981",
                GigabytesPerSecond.toKilobitsPerSecond("2.8946231649761316296", 5));
    }

    @Test
    public void testToKilobytesPerSecond() throws Exception {
        assertEquals("2894623.1649761316",
                GigabytesPerSecond.toKilobytesPerSecond("2.8946231649761316296", 10));
        assertEquals("2894623.16498",
                GigabytesPerSecond.toKilobytesPerSecond("2.8946231649761316296", 5));
    }

    @Test
    public void testToMegabitsPerSecond() throws Exception {
        assertEquals("23156.9853198091",
                GigabytesPerSecond.toMegabitsPerSecond("2.8946231649761316296", 10));
        assertEquals("23156.98532",
                GigabytesPerSecond.toMegabitsPerSecond("2.8946231649761316296", 5));
    }

    @Test
    public void testToMegabytesPerSecond() throws Exception {
        assertEquals("2894.6231649761",
                GigabytesPerSecond.toMegabytesPerSecond("2.8946231649761316296", 10));
        assertEquals("2894.62316",
                GigabytesPerSecond.toMegabytesPerSecond("2.8946231649761316296", 5));
    }

    @Test
    public void testToGigabitsPerSecond() throws Exception {
        assertEquals("23.1569853198",
                GigabytesPerSecond.toGigabitsPerSecond("2.8946231649761316296", 10));
        assertEquals("23.15699",
                GigabytesPerSecond.toGigabitsPerSecond("2.8946231649761316296", 5));
    }

    @Test
    public void testToTerabitsPerSecond() throws Exception {
        assertEquals("0.0231569853",
                GigabytesPerSecond.toTerabitsPerSecond("2.8946231649761316296", 10));
        assertEquals("0.02316",
                GigabytesPerSecond.toTerabitsPerSecond("2.8946231649761316296", 5));
    }

    @Test
    public void testToTerabytesPerSecond() throws Exception {
        assertEquals("0.0028946232",
                GigabytesPerSecond.toTerabytesPerSecond("2.8946231649761316296", 10));
        assertEquals("0.00289",
                GigabytesPerSecond.toTerabytesPerSecond("2.8946231649761316296", 5));
    }
}