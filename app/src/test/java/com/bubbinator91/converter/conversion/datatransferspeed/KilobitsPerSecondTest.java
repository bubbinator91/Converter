package com.bubbinator91.converter.conversion.datatransferspeed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link KilobitsPerSecond} class.
 */
@RunWith(JUnit4.class)
public class KilobitsPerSecondTest {

    @Test
    public void testGetInstance() throws Exception {
        KilobitsPerSecond kbps = KilobitsPerSecond.getInstance();

        assertNotNull(kbps);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results =
                KilobitsPerSecond.getInstance().toAll("8000008.9764953164976", 10).getFirst();

        assertNotNull(results);
        assertEquals("8000008976.4953164976", results.get(0));
        assertEquals("1000001122.0619145622", results.get(1));
        assertEquals("1000001.1220619146", results.get(2));
        assertEquals("8000.0089764953", results.get(3));
        assertEquals("1000.0011220619", results.get(4));
        assertEquals("8.0000089765", results.get(5));
        assertEquals("1.0000011221", results.get(6));
        assertEquals("0.008000009", results.get(7));
        assertEquals("0.0010000011", results.get(8));

        results =
                KilobitsPerSecond.getInstance().toAll("8000008.9764953164976", 5).getFirst();

        assertNotNull(results);
        assertEquals("8000008976.49532", results.get(0));
        assertEquals("1000001122.06191", results.get(1));
        assertEquals("1000001.12206", results.get(2));
        assertEquals("8000.00898", results.get(3));
        assertEquals("1000.00112", results.get(4));
        assertEquals("8.00001", results.get(5));
        assertEquals("1", results.get(6));
        assertEquals("0.008", results.get(7));
        assertEquals("0.001", results.get(8));
    }

    @Test
    public void testToBitsPerSecond() throws Exception {
        assertEquals("8000008976.4953164976",
                KilobitsPerSecond.getInstance().toBitsPerSecond("8000008.9764953164976", 10));
        assertEquals("8000008976.49532",
                KilobitsPerSecond.getInstance().toBitsPerSecond("8000008.9764953164976", 5));
    }

    @Test
    public void testToBytesPerSecond() throws Exception {
        assertEquals("1000001122.0619145622",
                KilobitsPerSecond.getInstance().toBytesPerSecond("8000008.9764953164976", 10));
        assertEquals("1000001122.06191",
                KilobitsPerSecond.getInstance().toBytesPerSecond("8000008.9764953164976", 5));
    }

    @Test
    public void testToKilobytesPerSecond() throws Exception {
        assertEquals("1000001.1220619146",
                KilobitsPerSecond.getInstance().toKilobytesPerSecond("8000008.9764953164976", 10));
        assertEquals("1000001.12206",
                KilobitsPerSecond.getInstance().toKilobytesPerSecond("8000008.9764953164976", 5));
    }

    @Test
    public void testToMegabitsPerSecond() throws Exception {
        assertEquals("8000.0089764953",
                KilobitsPerSecond.getInstance().toMegabitsPerSecond("8000008.9764953164976", 10));
        assertEquals("8000.00898",
                KilobitsPerSecond.getInstance().toMegabitsPerSecond("8000008.9764953164976", 5));
    }

    @Test
    public void testToMegabytesPerSecond() throws Exception {
        assertEquals("1000.0011220619",
                KilobitsPerSecond.getInstance().toMegabytesPerSecond("8000008.9764953164976", 10));
        assertEquals("1000.00112",
                KilobitsPerSecond.getInstance().toMegabytesPerSecond("8000008.9764953164976", 5));
    }

    @Test
    public void testToGigabitsPerSecond() throws Exception {
        assertEquals("8.0000089765",
                KilobitsPerSecond.getInstance().toGigabitsPerSecond("8000008.9764953164976", 10));
        assertEquals("8.00001",
                KilobitsPerSecond.getInstance().toGigabitsPerSecond("8000008.9764953164976", 5));
    }

    @Test
    public void testToGigabytesPerSecond() throws Exception {
        assertEquals("1.0000011221",
                KilobitsPerSecond.getInstance().toGigabytesPerSecond("8000008.9764953164976", 10));
        assertEquals("1",
                KilobitsPerSecond.getInstance().toGigabytesPerSecond("8000008.9764953164976", 5));
    }

    @Test
    public void testToTerabitsPerSecond() throws Exception {
        assertEquals("0.008000009",
                KilobitsPerSecond.getInstance().toTerabitsPerSecond("8000008.9764953164976", 10));
        assertEquals("0.008",
                KilobitsPerSecond.getInstance().toTerabitsPerSecond("8000008.9764953164976", 5));
    }

    @Test
    public void testToTerabytesPerSecond() throws Exception {
        assertEquals("0.0010000011",
                KilobitsPerSecond.getInstance().toTerabytesPerSecond("8000008.9764953164976", 10));
        assertEquals("0.001",
                KilobitsPerSecond.getInstance().toTerabytesPerSecond("8000008.9764953164976", 5));
    }
}