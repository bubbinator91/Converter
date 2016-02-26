package com.bubbinator91.converter.tests.conversion.acceleration;

import com.bubbinator91.converter.conversion.acceleration.MetersPerSecondSquared;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link MetersPerSecondSquared} class.
 */
@RunWith(JUnit4.class)
public class MetersPerSecondSquaredTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        MetersPerSecondSquared.toAll("1.568946531647201", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("156.8946531647", results.get(0));
                    assertEquals("5.1474623742", results.get(1));
                    assertEquals("0.1599880216", results.get(2));
                });

        MetersPerSecondSquared.toAll("1.568946531647201", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("156.89465", results.get(0));
                    assertEquals("5.14746", results.get(1));
                    assertEquals("0.15999", results.get(2));
                });
    }

    @Test
    public void testToCentimetersPerSecondSquared() throws Exception {
        assertEquals("156.8946531647",
                MetersPerSecondSquared.toCentimetersPerSecondSquared("1.568946531647201", 10));
        assertEquals("156.89465",
                MetersPerSecondSquared.toCentimetersPerSecondSquared("1.568946531647201", 5));
    }

    @Test
    public void testToFeetPerSecondSquared() throws Exception {
        assertEquals("5.1474623742",
                MetersPerSecondSquared.toFeetPerSecondSquared("1.568946531647201", 10));
        assertEquals("5.14746",
                MetersPerSecondSquared.toFeetPerSecondSquared("1.568946531647201", 5));
    }

    @Test
    public void testToStandardGravity() throws Exception {
        assertEquals("0.1599880216",
                MetersPerSecondSquared.toStandardGravity("1.568946531647201", 10));
        assertEquals("0.15999",
                MetersPerSecondSquared.toStandardGravity("1.568946531647201", 5));
    }
}