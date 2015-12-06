package com.bubbinator91.converter.conversion.fuelconsumption;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link USMilesPerGallon} class.
 */
@RunWith(JUnit4.class)
public class USMilesPerGallonTest {

    @Test
    public void testGetInstance() throws Exception {
        USMilesPerGallon usmpg = USMilesPerGallon.getInstance();

        assertNotNull(usmpg);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results = USMilesPerGallon.getInstance().toAll("2.15235645123659", 10);

        assertNotNull(results);
        assertEquals("2.5848723198", results.get(0));
        assertEquals("0.9150608015", results.get(1));
        assertEquals("109.2823557168", results.get(2));

        results = USMilesPerGallon.getInstance().toAll("2.15235645123659", 5);

        assertNotNull(results);
        assertEquals("2.58487", results.get(0));
        assertEquals("0.91506", results.get(1));
        assertEquals("109.28236", results.get(2));
    }

    @Test
    public void testToUKMilesPerGallon() throws Exception {
        assertEquals("2.5848723198",
                USMilesPerGallon.getInstance().toUKMilesPerGallon("2.15235645123659", 10));
        assertEquals("2.58487",
                USMilesPerGallon.getInstance().toUKMilesPerGallon("2.15235645123659", 5));
    }

    @Test
    public void testToKilometersPerLiter() throws Exception {
        assertEquals("0.9150608015",
                USMilesPerGallon.getInstance().toKilometersPerLiter("2.15235645123659", 10));
        assertEquals("0.91506",
                USMilesPerGallon.getInstance().toKilometersPerLiter("2.15235645123659", 5));
    }

    @Test
    public void testToLitersPer100Kilometers() throws Exception {
        assertEquals("109.2823557168",
                USMilesPerGallon.getInstance().toLitersPer100Kilometers("2.15235645123659", 10));
        assertEquals("109.28236",
                USMilesPerGallon.getInstance().toLitersPer100Kilometers("2.15235645123659", 5));
    }
}