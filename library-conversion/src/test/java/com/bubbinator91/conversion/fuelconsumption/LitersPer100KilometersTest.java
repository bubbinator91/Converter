package com.bubbinator91.conversion.fuelconsumption;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link LitersPer100Kilometers} class.
 */
@RunWith(JUnit4.class)
public class LitersPer100KilometersTest {

    @Test
    public void testGetInstance() throws Exception {
        LitersPer100Kilometers l100k = LitersPer100Kilometers.getInstance();

        assertNotNull(l100k);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results =
                LitersPer100Kilometers.getInstance().toAll("8.56498658974125", 10).getFirst();

        assertNotNull(results);
        assertEquals("27.4623411104", results.get(0));
        assertEquals("32.9808965107", results.get(1));
        assertEquals("11.6754415144", results.get(2));

        results =
                LitersPer100Kilometers.getInstance().toAll("8.56498658974125", 5).getFirst();

        assertNotNull(results);
        assertEquals("27.46234", results.get(0));
        assertEquals("32.9809", results.get(1));
        assertEquals("11.67544", results.get(2));
    }

    @Test
    public void testToUSMilesPerGallon() throws Exception {
        assertEquals("27.4623411104",
                LitersPer100Kilometers.getInstance().toUSMilesPerGallon("8.56498658974125", 10));
        assertEquals("27.46234",
                LitersPer100Kilometers.getInstance().toUSMilesPerGallon("8.56498658974125", 5));
    }

    @Test
    public void testToUKMilesPerGallon() throws Exception {
        assertEquals("32.9808965107",
                LitersPer100Kilometers.getInstance().toUKMilesPerGallon("8.56498658974125", 10));
        assertEquals("32.9809",
                LitersPer100Kilometers.getInstance().toUKMilesPerGallon("8.56498658974125", 5));
    }

    @Test
    public void testToKilometersPerLiter() throws Exception {
        assertEquals("11.6754415144",
                LitersPer100Kilometers.getInstance().toKilometersPerLiter("8.56498658974125", 10));
        assertEquals("11.67544",
                LitersPer100Kilometers.getInstance().toKilometersPerLiter("8.56498658974125", 5));
    }
}