package com.bubbinator91.converter.tests.conversion.datatransferspeed;

import com.bubbinator91.converter.conversion.datatransferspeed.BytesPerSecond;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link BytesPerSecond} class.
 */
@RunWith(JUnit4.class)
public class BytesPerSecondTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        BytesPerSecond.toAll("1000000009.764953261346", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("8000000078.1196260908", results.get(0));
                    assertEquals("8000000.0781196261", results.get(1));
                    assertEquals("1000000.0097649533", results.get(2));
                    assertEquals("8000.0000781196", results.get(3));
                    assertEquals("1000.000009765", results.get(4));
                    assertEquals("8.0000000781", results.get(5));
                    assertEquals("1.0000000098", results.get(6));
                    assertEquals("0.0080000001", results.get(7));
                    assertEquals("0.001", results.get(8));
                });

        BytesPerSecond.toAll("1000000009.764953261346", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("8000000078.11963", results.get(0));
                    assertEquals("8000000.07812", results.get(1));
                    assertEquals("1000000.00976", results.get(2));
                    assertEquals("8000.00008", results.get(3));
                    assertEquals("1000.00001", results.get(4));
                    assertEquals("8", results.get(5));
                    assertEquals("1", results.get(6));
                    assertEquals("0.008", results.get(7));
                    assertEquals("0.001", results.get(8));
                });
    }

    @Test
    public void testToBitsPerSecond() throws Exception {
        assertEquals("8000000078.1196260908",
                BytesPerSecond.toBitsPerSecond("1000000009.764953261346", 10));
        assertEquals("8000000078.11963",
                BytesPerSecond.toBitsPerSecond("1000000009.764953261346", 5));
    }

    @Test
    public void testToKilobitsPerSecond() throws Exception {
        assertEquals("8000000.0781196261",
                BytesPerSecond.toKilobitsPerSecond("1000000009.764953261346", 10));
        assertEquals("8000000.07812",
                BytesPerSecond.toKilobitsPerSecond("1000000009.764953261346", 5));
    }

    @Test
    public void testToKilobytesPerSecond() throws Exception {
        assertEquals("1000000.0097649533",
                BytesPerSecond.toKilobytesPerSecond("1000000009.764953261346", 10));
        assertEquals("1000000.00976",
                BytesPerSecond.toKilobytesPerSecond("1000000009.764953261346", 5));
    }

    @Test
    public void testToMegabitsPerSecond() throws Exception {
        assertEquals("8000.0000781196",
                BytesPerSecond.toMegabitsPerSecond("1000000009.764953261346", 10));
        assertEquals("8000.00008",
                BytesPerSecond.toMegabitsPerSecond("1000000009.764953261346", 5));
    }

    @Test
    public void testToMegabytesPerSecond() throws Exception {
        assertEquals("1000.000009765",
                BytesPerSecond.toMegabytesPerSecond("1000000009.764953261346", 10));
        assertEquals("1000.00001",
                BytesPerSecond.toMegabytesPerSecond("1000000009.764953261346", 5));
    }

    @Test
    public void testToGigabitsPerSecond() throws Exception {
        assertEquals("8.0000000781",
                BytesPerSecond.toGigabitsPerSecond("1000000009.764953261346", 10));
        assertEquals("8",
                BytesPerSecond.toGigabitsPerSecond("1000000009.764953261346", 5));
    }

    @Test
    public void testToGigabytesPerSecond() throws Exception {
        assertEquals("1.0000000098",
                BytesPerSecond.toGigabytesPerSecond("1000000009.764953261346", 10));
        assertEquals("1",
                BytesPerSecond.toGigabytesPerSecond("1000000009.764953261346", 5));
    }

    @Test
    public void testToTerabitsPerSecond() throws Exception {
        assertEquals("0.0080000001",
                BytesPerSecond.toTerabitsPerSecond("1000000009.764953261346", 10));
        assertEquals("0.008",
                BytesPerSecond.toTerabitsPerSecond("1000000009.764953261346", 5));
    }

    @Test
    public void testToTerabytesPerSecond() throws Exception {
        assertEquals("0.001",
                BytesPerSecond.toTerabytesPerSecond("1000000009.764953261346", 10));
        assertEquals("0.001",
                BytesPerSecond.toTerabytesPerSecond("1000000009.764953261346", 5));
    }
}