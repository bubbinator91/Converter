package com.bubbinator91.converter.conversion.speed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link FeetPerSecond} class.
 */
@RunWith(JUnit4.class)
public class FeetPerSecondTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        FeetPerSecond.toAll("4.3465986431", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("2.5752892868", results.get(0));
                    assertEquals("4.7694357591", results.get(1));
                    assertEquals("1.3248432664", results.get(2));
                    assertEquals("2.9635899839", results.get(3));
                });

        FeetPerSecond.toAll("4.3465986431", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("2.57529", results.get(0));
                    assertEquals("4.76944", results.get(1));
                    assertEquals("1.32484", results.get(2));
                    assertEquals("2.96359", results.get(3));
                });
    }

    @Test
    public void testToKnots() throws Exception {
        assertEquals("2.5752892868", FeetPerSecond.toKnots("4.3465986431", 10));
        assertEquals("2.57529", FeetPerSecond.toKnots("4.3465986431", 5));
    }

    @Test
    public void testToKilometersPerHour() throws Exception {
        assertEquals("4.7694357591", FeetPerSecond.toKilometersPerHour("4.3465986431", 10));
        assertEquals("4.76944", FeetPerSecond.toKilometersPerHour("4.3465986431", 5));
    }

    @Test
    public void testToMetersPerSecond() throws Exception {
        assertEquals("1.3248432664", FeetPerSecond.toMetersPerSecond("4.3465986431", 10));
        assertEquals("1.32484", FeetPerSecond.toMetersPerSecond("4.3465986431", 5));
    }

    @Test
    public void testToMilesPerHour() throws Exception {
        assertEquals("2.9635899839", FeetPerSecond.toMilesPerHour("4.3465986431", 10));
        assertEquals("2.96359", FeetPerSecond.toMilesPerHour("4.3465986431", 5));
    }
}