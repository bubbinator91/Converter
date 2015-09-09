package com.bubbinator91.conversion.fuelconsumption;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link UKMilesPerGallon} class.
 */
@RunWith(JUnit4.class)
public class UKMilesPerGallonTest {

    @Test
    public void testGetInstance() throws Exception {
        UKMilesPerGallon ukmpg = UKMilesPerGallon.getInstance();

        assertNotNull(ukmpg);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results = UKMilesPerGallon.getInstance().toAll("3.6589456897564", 10);

        assertNotNull(results);
        assertEquals("3.0467096187", results.get(0));
        assertEquals("1.295289423", results.get(1));
        assertEquals("77.2028229669", results.get(2));

        results = UKMilesPerGallon.getInstance().toAll("3.6589456897564", 5);

        assertNotNull(results);
        assertEquals("3.04671", results.get(0));
        assertEquals("1.29529", results.get(1));
        assertEquals("77.20282", results.get(2));
    }

    @Test
    public void testToUSMilesPerGallon() throws Exception {
        assertEquals("3.0467096187",
                UKMilesPerGallon.getInstance().toUSMilesPerGallon("3.6589456897564", 10));
        assertEquals("3.04671",
                UKMilesPerGallon.getInstance().toUSMilesPerGallon("3.6589456897564", 5));
    }

    @Test
    public void testToKilometersPerLiter() throws Exception {
        assertEquals("1.295289423",
                UKMilesPerGallon.getInstance().toKilometersPerLiter("3.6589456897564", 10));
        assertEquals("1.29529",
                UKMilesPerGallon.getInstance().toKilometersPerLiter("3.6589456897564", 5));
    }

    @Test
    public void testToLitersPer100Kilometers() throws Exception {
        assertEquals("77.2028229669",
                UKMilesPerGallon.getInstance().toLitersPer100Kilometers("3.6589456897564", 10));
        assertEquals("77.20282",
                UKMilesPerGallon.getInstance().toLitersPer100Kilometers("3.6589456897564", 5));
    }
}