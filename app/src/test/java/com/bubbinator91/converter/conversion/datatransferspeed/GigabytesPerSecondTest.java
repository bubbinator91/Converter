package com.bubbinator91.converter.conversion.datatransferspeed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link GigabytesPerSecond} class.
 */
@RunWith(JUnit4.class)
public class GigabytesPerSecondTest {

    @Test
    public void testGetInstance() throws Exception {
        GigabytesPerSecond gbyps = GigabytesPerSecond.getInstance();

        assertNotNull(gbyps);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results =
                GigabytesPerSecond.getInstance().toAll("2.8946231649761316296", 10).getFirst();

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

        results =
                GigabytesPerSecond.getInstance().toAll("2.8946231649761316296", 5).getFirst();

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
    }

    @Test
    public void testToBitsPerSecond() throws Exception {
        assertEquals("23156985319.8090530368",
                GigabytesPerSecond.getInstance().toBitsPerSecond("2.8946231649761316296", 10));
        assertEquals("23156985319.80905",
                GigabytesPerSecond.getInstance().toBitsPerSecond("2.8946231649761316296", 5));
    }

    @Test
    public void testToBytesPerSecond() throws Exception {
        assertEquals("2894623164.9761316296",
                GigabytesPerSecond.getInstance().toBytesPerSecond("2.8946231649761316296", 10));
        assertEquals("2894623164.97613",
                GigabytesPerSecond.getInstance().toBytesPerSecond("2.8946231649761316296", 5));
    }

    @Test
    public void testToKilobitsPerSecond() throws Exception {
        assertEquals("23156985.319809053",
                GigabytesPerSecond.getInstance().toKilobitsPerSecond("2.8946231649761316296", 10));
        assertEquals("23156985.31981",
                GigabytesPerSecond.getInstance().toKilobitsPerSecond("2.8946231649761316296", 5));
    }

    @Test
    public void testToKilobytesPerSecond() throws Exception {
        assertEquals("2894623.1649761316",
                GigabytesPerSecond.getInstance().toKilobytesPerSecond("2.8946231649761316296", 10));
        assertEquals("2894623.16498",
                GigabytesPerSecond.getInstance().toKilobytesPerSecond("2.8946231649761316296", 5));
    }

    @Test
    public void testToMegabitsPerSecond() throws Exception {
        assertEquals("23156.9853198091",
                GigabytesPerSecond.getInstance().toMegabitsPerSecond("2.8946231649761316296", 10));
        assertEquals("23156.98532",
                GigabytesPerSecond.getInstance().toMegabitsPerSecond("2.8946231649761316296", 5));
    }

    @Test
    public void testToMegabytesPerSecond() throws Exception {
        assertEquals("2894.6231649761",
                GigabytesPerSecond.getInstance().toMegabytesPerSecond("2.8946231649761316296", 10));
        assertEquals("2894.62316",
                GigabytesPerSecond.getInstance().toMegabytesPerSecond("2.8946231649761316296", 5));
    }

    @Test
    public void testToGigabitsPerSecond() throws Exception {
        assertEquals("23.1569853198",
                GigabytesPerSecond.getInstance().toGigabitsPerSecond("2.8946231649761316296", 10));
        assertEquals("23.15699",
                GigabytesPerSecond.getInstance().toGigabitsPerSecond("2.8946231649761316296", 5));
    }

    @Test
    public void testToTerabitsPerSecond() throws Exception {
        assertEquals("0.0231569853",
                GigabytesPerSecond.getInstance().toTerabitsPerSecond("2.8946231649761316296", 10));
        assertEquals("0.02316",
                GigabytesPerSecond.getInstance().toTerabitsPerSecond("2.8946231649761316296", 5));
    }

    @Test
    public void testToTerabytesPerSecond() throws Exception {
        assertEquals("0.0028946232",
                GigabytesPerSecond.getInstance().toTerabytesPerSecond("2.8946231649761316296", 10));
        assertEquals("0.00289",
                GigabytesPerSecond.getInstance().toTerabytesPerSecond("2.8946231649761316296", 5));
    }
}