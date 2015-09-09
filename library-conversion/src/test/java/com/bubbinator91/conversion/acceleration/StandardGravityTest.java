package com.bubbinator91.conversion.acceleration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link StandardGravity} class.
 */
@RunWith(JUnit4.class)
public class StandardGravityTest {

    @Test
    public void testGetInstance() throws Exception {
        StandardGravity sg = StandardGravity.getInstance();

        assertNotNull(sg);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results = StandardGravity
                .getInstance()
                .toAll("0.564859764825136", 10);

        assertNotNull(results);
        assertEquals("553.9382012603", results.get(0));
        assertEquals("18.1738255006", results.get(1));
        assertEquals("5.5393820126", results.get(2));

        results = StandardGravity
                .getInstance()
                .toAll("0.564859764825136", 5);

        assertNotNull(results);
        assertEquals("553.9382", results.get(0));
        assertEquals("18.17383", results.get(1));
        assertEquals("5.53938", results.get(2));
    }

    @Test
    public void testToCentimetersPerSecondSquared() throws Exception {
        assertEquals("553.9382012603",
                StandardGravity
                        .getInstance()
                        .toCentimetersPerSecondSquared("0.564859764825136", 10));
        assertEquals("553.9382",
                StandardGravity
                        .getInstance()
                        .toCentimetersPerSecondSquared("0.564859764825136", 5));
    }

    @Test
    public void testToFeetPerSecondSquared() throws Exception {
        assertEquals("18.1738255006",
                StandardGravity
                        .getInstance()
                        .toFeetPerSecondSquared("0.564859764825136", 10));
        assertEquals("18.17383",
                StandardGravity
                        .getInstance()
                        .toFeetPerSecondSquared("0.564859764825136", 5));
    }

    @Test
    public void testToMetersPerSecondSquared() throws Exception {
        assertEquals("5.5393820126",
                StandardGravity
                        .getInstance()
                        .toMetersPerSecondSquared("0.564859764825136", 10));
        assertEquals("5.53938",
                StandardGravity
                        .getInstance()
                        .toMetersPerSecondSquared("0.564859764825136", 5));
    }
}