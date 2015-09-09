package com.bubbinator91.conversion.acceleration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link MetersPerSecondSquared} class.
 */
@RunWith(JUnit4.class)
public class MetersPerSecondSquaredTest {

    @Test
    public void testGetInstance() throws Exception {
        MetersPerSecondSquared mpss = MetersPerSecondSquared.getInstance();

        assertNotNull(mpss);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results = MetersPerSecondSquared
                .getInstance()
                .toAll("1.568946531647201", 10);

        assertNotNull(results);
        assertEquals("156.8946531647", results.get(0));
        assertEquals("5.1474623742", results.get(1));
        assertEquals("0.1599880216", results.get(2));

        results = MetersPerSecondSquared
                .getInstance()
                .toAll("1.568946531647201", 5);

        assertNotNull(results);
        assertEquals("156.89465", results.get(0));
        assertEquals("5.14746", results.get(1));
        assertEquals("0.15999", results.get(2));
    }

    @Test
    public void testToCentimetersPerSecondSquared() throws Exception {
        assertEquals("156.8946531647",
                MetersPerSecondSquared
                        .getInstance()
                        .toCentimetersPerSecondSquared("1.568946531647201", 10));
        assertEquals("156.89465",
                MetersPerSecondSquared
                        .getInstance()
                        .toCentimetersPerSecondSquared("1.568946531647201", 5));
    }

    @Test
    public void testToFeetPerSecondSquared() throws Exception {
        assertEquals("5.1474623742",
                MetersPerSecondSquared
                        .getInstance()
                        .toFeetPerSecondSquared("1.568946531647201", 10));
        assertEquals("5.14746",
                MetersPerSecondSquared
                        .getInstance()
                        .toFeetPerSecondSquared("1.568946531647201", 5));
    }

    @Test
    public void testToStandardGravity() throws Exception {
        assertEquals("0.1599880216",
                MetersPerSecondSquared
                        .getInstance()
                        .toStandardGravity("1.568946531647201", 10));
        assertEquals("0.15999",
                MetersPerSecondSquared
                        .getInstance()
                        .toStandardGravity("1.568946531647201", 5));
    }
}