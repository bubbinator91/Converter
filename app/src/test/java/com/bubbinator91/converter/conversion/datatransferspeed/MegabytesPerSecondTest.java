package com.bubbinator91.converter.conversion.datatransferspeed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link MegabytesPerSecond} class.
 */
@RunWith(JUnit4.class)
public class MegabytesPerSecondTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        MegabytesPerSecond.toAll("1005.9764326594613", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("8047811461.2756904", results.get(0));
                    assertEquals("1005976432.6594613", results.get(1));
                    assertEquals("8047811.4612756904", results.get(2));
                    assertEquals("1005976.4326594613", results.get(3));
                    assertEquals("8047.8114612757", results.get(4));
                    assertEquals("8.0478114613", results.get(5));
                    assertEquals("1.0059764327", results.get(6));
                    assertEquals("0.0080478115", results.get(7));
                    assertEquals("0.0010059764", results.get(8));
                });

        MegabytesPerSecond.toAll("1005.9764326594613", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("8047811461.27569", results.get(0));
                    assertEquals("1005976432.65946", results.get(1));
                    assertEquals("8047811.46128", results.get(2));
                    assertEquals("1005976.43266", results.get(3));
                    assertEquals("8047.81146", results.get(4));
                    assertEquals("8.04781", results.get(5));
                    assertEquals("1.00598", results.get(6));
                    assertEquals("0.00805", results.get(7));
                    assertEquals("0.00101", results.get(8));
                });
    }

    @Test
    public void testToBitsPerSecond() throws Exception {
        assertEquals("8047811461.2756904",
                MegabytesPerSecond.toBitsPerSecond("1005.9764326594613", 10));
        assertEquals("8047811461.27569",
                MegabytesPerSecond.toBitsPerSecond("1005.9764326594613", 5));
    }

    @Test
    public void testToBytesPerSecond() throws Exception {
        assertEquals("1005976432.6594613",
                MegabytesPerSecond.toBytesPerSecond("1005.9764326594613", 10));
        assertEquals("1005976432.65946",
                MegabytesPerSecond.toBytesPerSecond("1005.9764326594613", 5));
    }

    @Test
    public void testToKilobitsPerSecond() throws Exception {
        assertEquals("8047811.4612756904",
                MegabytesPerSecond.toKilobitsPerSecond("1005.9764326594613", 10));
        assertEquals("8047811.46128",
                MegabytesPerSecond.toKilobitsPerSecond("1005.9764326594613", 5));
    }

    @Test
    public void testToKilobytesPerSecond() throws Exception {
        assertEquals("1005976.4326594613",
                MegabytesPerSecond.toKilobytesPerSecond("1005.9764326594613", 10));
        assertEquals("1005976.43266",
                MegabytesPerSecond.toKilobytesPerSecond("1005.9764326594613", 5));
    }

    @Test
    public void testToMegabitsPerSecond() throws Exception {
        assertEquals("8047.8114612757",
                MegabytesPerSecond.toMegabitsPerSecond("1005.9764326594613", 10));
        assertEquals("8047.81146",
                MegabytesPerSecond.toMegabitsPerSecond("1005.9764326594613", 5));
    }

    @Test
    public void testToGigabitsPerSecond() throws Exception {
        assertEquals("8.0478114613",
                MegabytesPerSecond.toGigabitsPerSecond("1005.9764326594613", 10));
        assertEquals("8.04781",
                MegabytesPerSecond.toGigabitsPerSecond("1005.9764326594613", 5));
    }

    @Test
    public void testToGigabytesPerSecond() throws Exception {
        assertEquals("1.0059764327",
                MegabytesPerSecond.toGigabytesPerSecond("1005.9764326594613", 10));
        assertEquals("1.00598",
                MegabytesPerSecond.toGigabytesPerSecond("1005.9764326594613", 5));
    }

    @Test
    public void testToTerabitsPerSecond() throws Exception {
        assertEquals("0.0080478115",
                MegabytesPerSecond.toTerabitsPerSecond("1005.9764326594613", 10));
        assertEquals("0.00805",
                MegabytesPerSecond.toTerabitsPerSecond("1005.9764326594613", 5));
    }

    @Test
    public void testToTerabytesPerSecond() throws Exception {
        assertEquals("0.0010059764",
                MegabytesPerSecond.toTerabytesPerSecond("1005.9764326594613", 10));
        assertEquals("0.00101",
                MegabytesPerSecond.toTerabytesPerSecond("1005.9764326594613", 5));
    }
}