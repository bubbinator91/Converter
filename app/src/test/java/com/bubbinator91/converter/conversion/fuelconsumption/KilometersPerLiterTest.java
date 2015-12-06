package com.bubbinator91.converter.conversion.fuelconsumption;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link KilometersPerLiter} class.
 */
@RunWith(JUnit4.class)
public class KilometersPerLiterTest {

    @Test
    public void testGetInstance() throws Exception {
        KilometersPerLiter kpl = KilometersPerLiter.getInstance();

        assertNotNull(kpl);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results = KilometersPerLiter.getInstance().toAll("4.5613258749654", 10);

        assertNotNull(results);
        assertEquals("10.7289036498", results.get(0));
        assertEquals("12.8848760393", results.get(1));
        assertEquals("21.923450054", results.get(2));

        results = KilometersPerLiter.getInstance().toAll("4.5613258749654", 5);

        assertNotNull(results);
        assertEquals("10.7289", results.get(0));
        assertEquals("12.88488", results.get(1));
        assertEquals("21.92345", results.get(2));
    }

    @Test
    public void testToUSMilesPerGallon() throws Exception {
        assertEquals("10.7289036498",
                KilometersPerLiter.getInstance().toUSMilesPerGallon("4.5613258749654", 10));
        assertEquals("10.7289",
                KilometersPerLiter.getInstance().toUSMilesPerGallon("4.5613258749654", 5));
    }

    @Test
    public void testToUKMilesPerGallon() throws Exception {
        assertEquals("12.8848760393",
                KilometersPerLiter.getInstance().toUKMilesPerGallon("4.5613258749654", 10));
        assertEquals("12.88488",
                KilometersPerLiter.getInstance().toUKMilesPerGallon("4.5613258749654", 5));
    }

    @Test
    public void testToLitersPer100Kilometers() throws Exception {
        assertEquals("21.923450054",
                KilometersPerLiter.getInstance().toLitersPer100Kilometers("4.5613258749654", 10));
        assertEquals("21.92345",
                KilometersPerLiter.getInstance().toLitersPer100Kilometers("4.5613258749654", 5));
    }
}