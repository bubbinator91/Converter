package com.bubbinator91.converter.conversion.datatransferspeed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link KilobytesPerSecond} class.
 */
@RunWith(JUnit4.class)
public class KilobytesPerSecondTest {

    @Test
    public void testGetInstance() throws Exception {
        KilobytesPerSecond kbyps = KilobytesPerSecond.getInstance();

        assertNotNull(kbyps);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results =
                KilobytesPerSecond.getInstance().toAll("1000001.122061915316", 10).getFirst();

        assertNotNull(results);
        assertEquals("8000008976.495322528", results.get(0));
        assertEquals("1000001122.061915316", results.get(1));
        assertEquals("8000008.9764953225", results.get(2));
        assertEquals("8000.0089764953", results.get(3));
        assertEquals("1000.0011220619", results.get(4));
        assertEquals("8.0000089765", results.get(5));
        assertEquals("1.0000011221", results.get(6));
        assertEquals("0.008000009", results.get(7));
        assertEquals("0.0010000011", results.get(8));

        results =
                KilobytesPerSecond.getInstance().toAll("1000001.122061915316", 5).getFirst();

        assertNotNull(results);
        assertEquals("8000008976.49532", results.get(0));
        assertEquals("1000001122.06192", results.get(1));
        assertEquals("8000008.9765", results.get(2));
        assertEquals("8000.00898", results.get(3));
        assertEquals("1000.00112", results.get(4));
        assertEquals("8.00001", results.get(5));
        assertEquals("1", results.get(6));
        assertEquals("0.008", results.get(7));
        assertEquals("0.001", results.get(8));
    }

    @Test
    public void testToBitsPerSecond() throws Exception {
        assertEquals("8000008976.495322528",
                KilobytesPerSecond.getInstance().toBitsPerSecond("1000001.122061915316", 10));
        assertEquals("8000008976.49532",
                KilobytesPerSecond.getInstance().toBitsPerSecond("1000001.122061915316", 5));
    }

    @Test
    public void testToBytesPerSecond() throws Exception {
        assertEquals("1000001122.061915316",
                KilobytesPerSecond.getInstance().toBytesPerSecond("1000001.122061915316", 10));
        assertEquals("1000001122.06192",
                KilobytesPerSecond.getInstance().toBytesPerSecond("1000001.122061915316", 5));
    }

    @Test
    public void testToKilobitsPerSecond() throws Exception {
        assertEquals("8000008.9764953225",
                KilobytesPerSecond.getInstance().toKilobitsPerSecond("1000001.122061915316", 10));
        assertEquals("8000008.9765",
                KilobytesPerSecond.getInstance().toKilobitsPerSecond("1000001.122061915316", 5));
    }

    @Test
    public void testToMegabitsPerSecond() throws Exception {
        assertEquals("8000.0089764953",
                KilobytesPerSecond.getInstance().toMegabitsPerSecond("1000001.122061915316", 10));
        assertEquals("8000.00898",
                KilobytesPerSecond.getInstance().toMegabitsPerSecond("1000001.122061915316", 5));
    }

    @Test
    public void testToMegabytesPerSecond() throws Exception {
        assertEquals("1000.0011220619",
                KilobytesPerSecond.getInstance().toMegabytesPerSecond("1000001.122061915316", 10));
        assertEquals("1000.00112",
                KilobytesPerSecond.getInstance().toMegabytesPerSecond("1000001.122061915316", 5));
    }

    @Test
    public void testToGigabitsPerSecond() throws Exception {
        assertEquals("8.0000089765",
                KilobytesPerSecond.getInstance().toGigabitsPerSecond("1000001.122061915316", 10));
        assertEquals("8.00001",
                KilobytesPerSecond.getInstance().toGigabitsPerSecond("1000001.122061915316", 5));
    }

    @Test
    public void testToGigabytesPerSecond() throws Exception {
        assertEquals("1.0000011221",
                KilobytesPerSecond.getInstance().toGigabytesPerSecond("1000001.122061915316", 10));
        assertEquals("1",
                KilobytesPerSecond.getInstance().toGigabytesPerSecond("1000001.122061915316", 5));
    }

    @Test
    public void testToTerabitsPerSecond() throws Exception {
        assertEquals("0.008000009",
                KilobytesPerSecond.getInstance().toTerabitsPerSecond("1000001.122061915316", 10));
        assertEquals("0.008",
                KilobytesPerSecond.getInstance().toTerabitsPerSecond("1000001.122061915316", 5));
    }

    @Test
    public void testToTerabytesPerSecond() throws Exception {
        assertEquals("0.0010000011",
                KilobytesPerSecond.getInstance().toTerabytesPerSecond("1000001.122061915316", 10));
        assertEquals("0.001",
                KilobytesPerSecond.getInstance().toTerabytesPerSecond("1000001.122061915316", 5));
    }
}