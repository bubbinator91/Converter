package com.bubbinator91.converter.conversion.acceleration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link CentimetersPerSecondSquared} class.
 */
@RunWith(JUnit4.class)
public class CentimetersPerSecondSquaredTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        CentimetersPerSecondSquared.toAll("8.0235641526359876", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("0.2632402937", results.get(0));
                    assertEquals("0.0802356415", results.get(1));
                    assertEquals("0.0081817585", results.get(2));
                });

        CentimetersPerSecondSquared.toAll("8.0235641526359876", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("0.26324", results.get(0));
                    assertEquals("0.08024", results.get(1));
                    assertEquals("0.00818", results.get(2));
                });
    }

    @Test
    public void testToFeetPerSecondSquared() throws Exception {
        assertEquals("0.2632402937",
                CentimetersPerSecondSquared.toFeetPerSecondSquared("8.0235641526359876", 10));
        assertEquals("0.26324",
                CentimetersPerSecondSquared.toFeetPerSecondSquared("8.0235641526359876", 5));
    }

    @Test
    public void testToMetersPerSecondSquared() throws Exception {
        assertEquals("0.0802356415",
                CentimetersPerSecondSquared.toMetersPerSecondSquared("8.0235641526359876", 10));
        assertEquals("0.08024",
                CentimetersPerSecondSquared.toMetersPerSecondSquared("8.0235641526359876", 5));
    }

    @Test
    public void testToStandardGravity() throws Exception {
        assertEquals("0.0081817585",
                CentimetersPerSecondSquared.toStandardGravity("8.0235641526359876", 10));
        assertEquals("0.00818",
                CentimetersPerSecondSquared.toStandardGravity("8.0235641526359876", 5));
    }
}