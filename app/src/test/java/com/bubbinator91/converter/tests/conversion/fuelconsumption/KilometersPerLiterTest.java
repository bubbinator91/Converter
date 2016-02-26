package com.bubbinator91.converter.tests.conversion.fuelconsumption;

import com.bubbinator91.converter.conversion.fuelconsumption.KilometersPerLiter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link KilometersPerLiter} class.
 */
@RunWith(JUnit4.class)
public class KilometersPerLiterTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        KilometersPerLiter.toAll("4.5613258749654", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("10.7289036498", results.get(0));
                    assertEquals("12.8848760393", results.get(1));
                    assertEquals("21.923450054", results.get(2));
                });

        KilometersPerLiter.toAll("4.5613258749654", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("10.7289", results.get(0));
                    assertEquals("12.88488", results.get(1));
                    assertEquals("21.92345", results.get(2));
                });
    }

    @Test
    public void testToUSMilesPerGallon() throws Exception {
        assertEquals("10.7289036498",
                KilometersPerLiter.toUSMilesPerGallon("4.5613258749654", 10));
        assertEquals("10.7289",
                KilometersPerLiter.toUSMilesPerGallon("4.5613258749654", 5));
    }

    @Test
    public void testToUKMilesPerGallon() throws Exception {
        assertEquals("12.8848760393",
                KilometersPerLiter.toUKMilesPerGallon("4.5613258749654", 10));
        assertEquals("12.88488",
                KilometersPerLiter.toUKMilesPerGallon("4.5613258749654", 5));
    }

    @Test
    public void testToLitersPer100Kilometers() throws Exception {
        assertEquals("21.923450054",
                KilometersPerLiter.toLitersPer100Kilometers("4.5613258749654", 10));
        assertEquals("21.92345",
                KilometersPerLiter.toLitersPer100Kilometers("4.5613258749654", 5));
    }
}