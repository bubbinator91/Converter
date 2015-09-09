package com.bubbinator91.conversion.acceleration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link CentimetersPerSecondSquared} class.
 */
@RunWith(JUnit4.class)
public class CentimetersPerSecondSquaredTest {

    @Test
    public void testGetInstance() throws Exception {
        CentimetersPerSecondSquared cmpss = CentimetersPerSecondSquared.getInstance();

        assertNotNull(cmpss);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results = CentimetersPerSecondSquared
                .getInstance()
                .toAll("8.0235641526359876", 10);

        assertNotNull(results);
        assertEquals("0.2632402937", results.get(0));
        assertEquals("0.0802356415", results.get(1));
        assertEquals("0.0081817585", results.get(2));

        results = CentimetersPerSecondSquared
                .getInstance()
                .toAll("8.0235641526359876", 5);

        assertNotNull(results);
        assertEquals("0.26324", results.get(0));
        assertEquals("0.08024", results.get(1));
        assertEquals("0.00818", results.get(2));
    }

    @Test
    public void testToFeetPerSecondSquared() throws Exception {
        assertEquals("0.2632402937",
                CentimetersPerSecondSquared
                        .getInstance()
                        .toFeetPerSecondSquared("8.0235641526359876", 10));
        assertEquals("0.26324",
                CentimetersPerSecondSquared
                        .getInstance()
                        .toFeetPerSecondSquared("8.0235641526359876", 5));
    }

    @Test
    public void testToMetersPerSecondSquared() throws Exception {
        assertEquals("0.0802356415",
                CentimetersPerSecondSquared
                        .getInstance()
                        .toMetersPerSecondSquared("8.0235641526359876", 10));
        assertEquals("0.08024",
                CentimetersPerSecondSquared
                        .getInstance()
                        .toMetersPerSecondSquared("8.0235641526359876", 5));
    }

    @Test
    public void testToStandardGravity() throws Exception {
        assertEquals("0.0081817585",
                CentimetersPerSecondSquared
                        .getInstance()
                        .toStandardGravity("8.0235641526359876", 10));
        assertEquals("0.00818",
                CentimetersPerSecondSquared
                        .getInstance()
                        .toStandardGravity("8.0235641526359876", 5));
    }
}