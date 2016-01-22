package com.bubbinator91.converter.conversion.fuelconsumption;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link USMilesPerGallon} class.
 */
@RunWith(JUnit4.class)
public class USMilesPerGallonTest {

    @Test
    public void testToAll() throws Exception {
        // Should be using a TestSubscriber here, but this works just fine

        USMilesPerGallon.toAll("2.15235645123659", 10)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("2.5848723198", results.get(0));
                    assertEquals("0.9150608015", results.get(1));
                    assertEquals("109.2823557168", results.get(2));
                });

        USMilesPerGallon.toAll("2.15235645123659", 5)
                .subscribe(results -> {
                    assertNotNull(results);
                    assertEquals("2.58487", results.get(0));
                    assertEquals("0.91506", results.get(1));
                    assertEquals("109.28236", results.get(2));
                });
    }

    @Test
    public void testToUKMilesPerGallon() throws Exception {
        assertEquals("2.5848723198",
                USMilesPerGallon.toUKMilesPerGallon("2.15235645123659", 10));
        assertEquals("2.58487",
                USMilesPerGallon.toUKMilesPerGallon("2.15235645123659", 5));
    }

    @Test
    public void testToKilometersPerLiter() throws Exception {
        assertEquals("0.9150608015",
                USMilesPerGallon.toKilometersPerLiter("2.15235645123659", 10));
        assertEquals("0.91506",
                USMilesPerGallon.toKilometersPerLiter("2.15235645123659", 5));
    }

    @Test
    public void testToLitersPer100Kilometers() throws Exception {
        assertEquals("109.2823557168",
                USMilesPerGallon.toLitersPer100Kilometers("2.15235645123659", 10));
        assertEquals("109.28236",
                USMilesPerGallon.toLitersPer100Kilometers("2.15235645123659", 5));
    }
}