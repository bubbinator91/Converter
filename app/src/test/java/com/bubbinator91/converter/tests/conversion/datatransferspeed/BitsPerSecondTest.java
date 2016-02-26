package com.bubbinator91.converter.tests.conversion.datatransferspeed;

import com.bubbinator91.converter.conversion.datatransferspeed.BitsPerSecond;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link BitsPerSecond} class.
 */
@RunWith(JUnit4.class)
public class BitsPerSecondTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        BitsPerSecond.toAll("8000000000.9764326598653", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("1000000000.1220540825", results.get(0));
                    assertEquals("8000000.0009764327", results.get(1));
                    assertEquals("1000000.0001220541", results.get(2));
                    assertEquals("8000.0000009764", results.get(3));
                    assertEquals("1000.0000001221", results.get(4));
                    assertEquals("8.000000001", results.get(5));
                    assertEquals("1.0000000001", results.get(6));
                    assertEquals("0.008", results.get(7));
                    assertEquals("0.001", results.get(8));
                });

        BitsPerSecond.toAll("8000000000.9764326598653", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("1000000000.12205", results.get(0));
                    assertEquals("8000000.00098", results.get(1));
                    assertEquals("1000000.00012", results.get(2));
                    assertEquals("8000", results.get(3));
                    assertEquals("1000", results.get(4));
                    assertEquals("8", results.get(5));
                    assertEquals("1", results.get(6));
                    assertEquals("0.008", results.get(7));
                    assertEquals("0.001", results.get(8));
                });
    }

    @Test
    public void testToBytesPerSecond() throws Exception {
        assertEquals("1000000000.1220540825",
                BitsPerSecond.toBytesPerSecond("8000000000.9764326598653", 10));
        assertEquals("1000000000.12205",
                BitsPerSecond.toBytesPerSecond("8000000000.9764326598653", 5));
    }

    @Test
    public void testToKilobitsPerSecond() throws Exception {
        assertEquals("8000000.0009764327",
                BitsPerSecond.toKilobitsPerSecond("8000000000.9764326598653", 10));
        assertEquals("8000000.00098",
                BitsPerSecond.toKilobitsPerSecond("8000000000.9764326598653", 5));
    }

    @Test
    public void testToKilobytesPerSecond() throws Exception {
        assertEquals("1000000.0001220541",
                BitsPerSecond.toKilobytesPerSecond("8000000000.9764326598653", 10));
        assertEquals("1000000.00012",
                BitsPerSecond.toKilobytesPerSecond("8000000000.9764326598653", 5));
    }

    @Test
    public void testToMegabitsPerSecond() throws Exception {
        assertEquals("8000.0000009764",
                BitsPerSecond.toMegabitsPerSecond("8000000000.9764326598653", 10));
        assertEquals("8000",
                BitsPerSecond.toMegabitsPerSecond("8000000000.9764326598653", 5));
    }

    @Test
    public void testToMegabytesPerSecond() throws Exception {
        assertEquals("1000.0000001221",
                BitsPerSecond.toMegabytesPerSecond("8000000000.9764326598653", 10));
        assertEquals("1000",
                BitsPerSecond.toMegabytesPerSecond("8000000000.9764326598653", 5));
    }

    @Test
    public void testToGigabitsPerSecond() throws Exception {
        assertEquals("8.000000001",
                BitsPerSecond.toGigabitsPerSecond("8000000000.9764326598653", 10));
        assertEquals("8",
                BitsPerSecond.toGigabitsPerSecond("8000000000.9764326598653", 5));
    }

    @Test
    public void testToGigabytesPerSecond() throws Exception {
        assertEquals("1.0000000001",
                BitsPerSecond.toGigabytesPerSecond("8000000000.9764326598653", 10));
        assertEquals("1",
                BitsPerSecond.toGigabytesPerSecond("8000000000.9764326598653", 5));
    }

    @Test
    public void testToTerabitsPerSecond() throws Exception {
        assertEquals("0.008",
                BitsPerSecond.toTerabitsPerSecond("8000000000.9764326598653", 10));
        assertEquals("0.008",
                BitsPerSecond.toTerabitsPerSecond("8000000000.9764326598653", 5));
    }

    @Test
    public void testToTerabytesPerSecond() throws Exception {
        assertEquals("0.001",
                BitsPerSecond.toTerabytesPerSecond("8000000000.9764326598653", 10));
        assertEquals("0.001",
                BitsPerSecond.toTerabytesPerSecond("8000000000.9764326598653", 5));
    }
}