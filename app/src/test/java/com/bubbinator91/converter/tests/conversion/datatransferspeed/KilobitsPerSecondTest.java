package com.bubbinator91.converter.tests.conversion.datatransferspeed;

import com.bubbinator91.converter.conversion.datatransferspeed.KilobitsPerSecond;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link KilobitsPerSecond} class.
 */
@RunWith(JUnit4.class)
public class KilobitsPerSecondTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        KilobitsPerSecond.toAll("8000008.9764953164976", 10)
                .subscribe(results -> {
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
                });

        KilobitsPerSecond.toAll("8000008.9764953164976", 5)
                .subscribe(results -> {
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
                });
    }

    @Test
    public void testToBitsPerSecond() throws Exception {
        assertEquals("8000008976.4953164976",
                KilobitsPerSecond.toBitsPerSecond("8000008.9764953164976", 10));
        assertEquals("8000008976.49532",
                KilobitsPerSecond.toBitsPerSecond("8000008.9764953164976", 5));
    }

    @Test
    public void testToBytesPerSecond() throws Exception {
        assertEquals("1000001122.0619145622",
                KilobitsPerSecond.toBytesPerSecond("8000008.9764953164976", 10));
        assertEquals("1000001122.06191",
                KilobitsPerSecond.toBytesPerSecond("8000008.9764953164976", 5));
    }

    @Test
    public void testToKilobytesPerSecond() throws Exception {
        assertEquals("1000001.1220619146",
                KilobitsPerSecond.toKilobytesPerSecond("8000008.9764953164976", 10));
        assertEquals("1000001.12206",
                KilobitsPerSecond.toKilobytesPerSecond("8000008.9764953164976", 5));
    }

    @Test
    public void testToMegabitsPerSecond() throws Exception {
        assertEquals("8000.0089764953",
                KilobitsPerSecond.toMegabitsPerSecond("8000008.9764953164976", 10));
        assertEquals("8000.00898",
                KilobitsPerSecond.toMegabitsPerSecond("8000008.9764953164976", 5));
    }

    @Test
    public void testToMegabytesPerSecond() throws Exception {
        assertEquals("1000.0011220619",
                KilobitsPerSecond.toMegabytesPerSecond("8000008.9764953164976", 10));
        assertEquals("1000.00112",
                KilobitsPerSecond.toMegabytesPerSecond("8000008.9764953164976", 5));
    }

    @Test
    public void testToGigabitsPerSecond() throws Exception {
        assertEquals("8.0000089765",
                KilobitsPerSecond.toGigabitsPerSecond("8000008.9764953164976", 10));
        assertEquals("8.00001",
                KilobitsPerSecond.toGigabitsPerSecond("8000008.9764953164976", 5));
    }

    @Test
    public void testToGigabytesPerSecond() throws Exception {
        assertEquals("1.0000011221",
                KilobitsPerSecond.toGigabytesPerSecond("8000008.9764953164976", 10));
        assertEquals("1",
                KilobitsPerSecond.toGigabytesPerSecond("8000008.9764953164976", 5));
    }

    @Test
    public void testToTerabitsPerSecond() throws Exception {
        assertEquals("0.008000009",
                KilobitsPerSecond.toTerabitsPerSecond("8000008.9764953164976", 10));
        assertEquals("0.008",
                KilobitsPerSecond.toTerabitsPerSecond("8000008.9764953164976", 5));
    }

    @Test
    public void testToTerabytesPerSecond() throws Exception {
        assertEquals("0.0010000011",
                KilobitsPerSecond.toTerabytesPerSecond("8000008.9764953164976", 10));
        assertEquals("0.001",
                KilobitsPerSecond.toTerabytesPerSecond("8000008.9764953164976", 5));
    }
}