package com.bubbinator91.converter.tests.conversion.fuelconsumption;

import com.bubbinator91.converter.conversion.fuelconsumption.LitersPer100Kilometers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link LitersPer100Kilometers} class.
 */
@RunWith(JUnit4.class)
public class LitersPer100KilometersTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        LitersPer100Kilometers.toAll("8.56498658974125", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("27.4623411104", results.get(0));
                    assertEquals("32.9808965107", results.get(1));
                    assertEquals("11.6754415144", results.get(2));
                });

        LitersPer100Kilometers.toAll("8.56498658974125", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("27.46234", results.get(0));
                    assertEquals("32.9809", results.get(1));
                    assertEquals("11.67544", results.get(2));
                });
    }

    @Test
    public void testToUSMilesPerGallon() throws Exception {
        assertEquals("27.4623411104",
                LitersPer100Kilometers.toUSMilesPerGallon("8.56498658974125", 10));
        assertEquals("27.46234",
                LitersPer100Kilometers.toUSMilesPerGallon("8.56498658974125", 5));
    }

    @Test
    public void testToUKMilesPerGallon() throws Exception {
        assertEquals("32.9808965107",
                LitersPer100Kilometers.toUKMilesPerGallon("8.56498658974125", 10));
        assertEquals("32.9809",
                LitersPer100Kilometers.toUKMilesPerGallon("8.56498658974125", 5));
    }

    @Test
    public void testToKilometersPerLiter() throws Exception {
        assertEquals("11.6754415144",
                LitersPer100Kilometers.toKilometersPerLiter("8.56498658974125", 10));
        assertEquals("11.67544",
                LitersPer100Kilometers.toKilometersPerLiter("8.56498658974125", 5));
    }
}