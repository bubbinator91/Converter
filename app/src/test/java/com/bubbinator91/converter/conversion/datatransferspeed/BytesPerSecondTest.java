package com.bubbinator91.converter.conversion.datatransferspeed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link BytesPerSecond} class.
 */
@RunWith(JUnit4.class)
public class BytesPerSecondTest {

    @Test
    public void testGetInstance() throws Exception {
        BytesPerSecond byps = BytesPerSecond.getInstance();

        assertNotNull(byps);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results =
                BytesPerSecond.getInstance().toAll("1000000009.764953261346", 10).getFirst();

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

        results =
                BytesPerSecond.getInstance().toAll("1000000009.764953261346", 5).getFirst();

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
    }

    @Test
    public void testToBitsPerSecond() throws Exception {
        assertEquals("8000000078.1196260908",
                BytesPerSecond.getInstance().toBitsPerSecond("1000000009.764953261346", 10));
        assertEquals("8000000078.11963",
                BytesPerSecond.getInstance().toBitsPerSecond("1000000009.764953261346", 5));
    }

    @Test
    public void testToKilobitsPerSecond() throws Exception {
        assertEquals("8000000.0781196261",
                BytesPerSecond.getInstance().toKilobitsPerSecond("1000000009.764953261346", 10));
        assertEquals("8000000.07812",
                BytesPerSecond.getInstance().toKilobitsPerSecond("1000000009.764953261346", 5));
    }

    @Test
    public void testToKilobytesPerSecond() throws Exception {
        assertEquals("1000000.0097649533",
                BytesPerSecond.getInstance().toKilobytesPerSecond("1000000009.764953261346", 10));
        assertEquals("1000000.00976",
                BytesPerSecond.getInstance().toKilobytesPerSecond("1000000009.764953261346", 5));
    }

    @Test
    public void testToMegabitsPerSecond() throws Exception {
        assertEquals("8000.0000781196",
                BytesPerSecond.getInstance().toMegabitsPerSecond("1000000009.764953261346", 10));
        assertEquals("8000.00008",
                BytesPerSecond.getInstance().toMegabitsPerSecond("1000000009.764953261346", 5));
    }

    @Test
    public void testToMegabytesPerSecond() throws Exception {
        assertEquals("1000.000009765",
                BytesPerSecond.getInstance().toMegabytesPerSecond("1000000009.764953261346", 10));
        assertEquals("1000.00001",
                BytesPerSecond.getInstance().toMegabytesPerSecond("1000000009.764953261346", 5));
    }

    @Test
    public void testToGigabitsPerSecond() throws Exception {
        assertEquals("8.0000000781",
                BytesPerSecond.getInstance().toGigabitsPerSecond("1000000009.764953261346", 10));
        assertEquals("8",
                BytesPerSecond.getInstance().toGigabitsPerSecond("1000000009.764953261346", 5));
    }

    @Test
    public void testToGigabytesPerSecond() throws Exception {
        assertEquals("1.0000000098",
                BytesPerSecond.getInstance().toGigabytesPerSecond("1000000009.764953261346", 10));
        assertEquals("1",
                BytesPerSecond.getInstance().toGigabytesPerSecond("1000000009.764953261346", 5));
    }

    @Test
    public void testToTerabitsPerSecond() throws Exception {
        assertEquals("0.0080000001",
                BytesPerSecond.getInstance().toTerabitsPerSecond("1000000009.764953261346", 10));
        assertEquals("0.008",
                BytesPerSecond.getInstance().toTerabitsPerSecond("1000000009.764953261346", 5));
    }

    @Test
    public void testToTerabytesPerSecond() throws Exception {
        assertEquals("0.001",
                BytesPerSecond.getInstance().toTerabytesPerSecond("1000000009.764953261346", 10));
        assertEquals("0.001",
                BytesPerSecond.getInstance().toTerabytesPerSecond("1000000009.764953261346", 5));
    }
}