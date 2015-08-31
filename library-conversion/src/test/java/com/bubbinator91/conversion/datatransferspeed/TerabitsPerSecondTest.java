package com.bubbinator91.conversion.datatransferspeed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link TerabitsPerSecond} class.
 */
@RunWith(JUnit4.class)
public class TerabitsPerSecondTest {

    @Test
    public void testGetInstance() throws Exception {
        TerabitsPerSecond tbps = TerabitsPerSecond.getInstance();

        assertNotNull(tbps);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results =
                TerabitsPerSecond
                        .getInstance()
                        .toAll("0.59461326596492331649564319", 10)
                        .getFirst();

        assertNotNull(results);
        assertEquals("594613265964.9233164956", results.get(0));
        assertEquals("74326658245.615414562", results.get(1));
        assertEquals("594613265.9649233165", results.get(2));
        assertEquals("74326658.2456154146", results.get(3));
        assertEquals("594613.2659649233", results.get(4));
        assertEquals("74326.6582456154", results.get(5));
        assertEquals("594.6132659649", results.get(6));
        assertEquals("74.3266582456", results.get(7));
        assertEquals("0.0743266582", results.get(8));

        results =
                TerabitsPerSecond
                        .getInstance()
                        .toAll("0.59461326596492331649564319", 5)
                        .getFirst();

        assertNotNull(results);
        assertEquals("594613265964.92332", results.get(0));
        assertEquals("74326658245.61541", results.get(1));
        assertEquals("594613265.96492", results.get(2));
        assertEquals("74326658.24562", results.get(3));
        assertEquals("594613.26596", results.get(4));
        assertEquals("74326.65825", results.get(5));
        assertEquals("594.61327", results.get(6));
        assertEquals("74.32666", results.get(7));
        assertEquals("0.07433", results.get(8));
    }

    @Test
    public void testToBitsPerSecond() throws Exception {
        assertEquals("594613265964.9233164956",
                TerabitsPerSecond
                        .getInstance()
                        .toBitsPerSecond("0.59461326596492331649564319", 10));
        assertEquals("594613265964.92332",
                TerabitsPerSecond
                        .getInstance()
                        .toBitsPerSecond("0.59461326596492331649564319", 5));
    }

    @Test
    public void testToBytesPerSecond() throws Exception {
        assertEquals("74326658245.615414562",
                TerabitsPerSecond
                        .getInstance()
                        .toBytesPerSecond("0.59461326596492331649564319", 10));
        assertEquals("74326658245.61541",
                TerabitsPerSecond
                        .getInstance()
                        .toBytesPerSecond("0.59461326596492331649564319", 5));
    }

    @Test
    public void testToKilobitsPerSecond() throws Exception {
        assertEquals("594613265.9649233165",
                TerabitsPerSecond
                        .getInstance()
                        .toKilobitsPerSecond("0.59461326596492331649564319", 10));
        assertEquals("594613265.96492",
                TerabitsPerSecond
                        .getInstance()
                        .toKilobitsPerSecond("0.59461326596492331649564319", 5));
    }

    @Test
    public void testToKilobytesPerSecond() throws Exception {
        assertEquals("74326658.2456154146",
                TerabitsPerSecond
                        .getInstance()
                        .toKilobytesPerSecond("0.59461326596492331649564319", 10));
        assertEquals("74326658.24562",
                TerabitsPerSecond
                        .getInstance()
                        .toKilobytesPerSecond("0.59461326596492331649564319", 5));
    }

    @Test
    public void testToMegabitsPerSecond() throws Exception {
        assertEquals("594613.2659649233",
                TerabitsPerSecond
                        .getInstance()
                        .toMegabitsPerSecond("0.59461326596492331649564319", 10));
        assertEquals("594613.26596",
                TerabitsPerSecond
                        .getInstance()
                        .toMegabitsPerSecond("0.59461326596492331649564319", 5));
    }

    @Test
    public void testToMegabytesPerSecond() throws Exception {
        assertEquals("74326.6582456154",
                TerabitsPerSecond
                        .getInstance()
                        .toMegabytesPerSecond("0.59461326596492331649564319", 10));
        assertEquals("74326.65825",
                TerabitsPerSecond
                        .getInstance()
                        .toMegabytesPerSecond("0.59461326596492331649564319", 5));
    }

    @Test
    public void testToGigabitsPerSecond() throws Exception {
        assertEquals("594.6132659649",
                TerabitsPerSecond
                        .getInstance()
                        .toGigabitsPerSecond("0.59461326596492331649564319", 10));
        assertEquals("594.61327",
                TerabitsPerSecond
                        .getInstance()
                        .toGigabitsPerSecond("0.59461326596492331649564319", 5));
    }

    @Test
    public void testToGigabytesPerSecond() throws Exception {
        assertEquals("74.3266582456",
                TerabitsPerSecond
                        .getInstance()
                        .toGigabytesPerSecond("0.59461326596492331649564319", 10));
        assertEquals("74.32666",
                TerabitsPerSecond
                        .getInstance()
                        .toGigabytesPerSecond("0.59461326596492331649564319", 5));
    }

    @Test
    public void testToTerabytesPerSecond() throws Exception {
        assertEquals("0.0743266582",
                TerabitsPerSecond
                        .getInstance()
                        .toTerabytesPerSecond("0.59461326596492331649564319", 10));
        assertEquals("0.07433",
                TerabitsPerSecond
                        .getInstance()
                        .toTerabytesPerSecond("0.59461326596492331649564319", 5));
    }
}