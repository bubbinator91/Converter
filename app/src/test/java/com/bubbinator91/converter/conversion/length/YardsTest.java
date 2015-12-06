package com.bubbinator91.converter.conversion.length;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link Yards} class.
 */
@RunWith(JUnit4.class)
public class YardsTest {

    @Test
    public void testGetInstance() throws Exception {
        Yards yards = Yards.getInstance();

        assertNotNull(yards);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results = Yards.getInstance().toAll("94.677446", 10).getFirst();

        assertNotNull(results);
        assertEquals("3408.388056", results.get(0));
        assertEquals("284.032338", results.get(1));
        assertEquals("0.0537940034", results.get(2));
        assertEquals("86573.0566224", results.get(3));
        assertEquals("8657.30566224", results.get(4));
        assertEquals("86.5730566224", results.get(5));
        assertEquals("0.0865730566", results.get(6));

        results = Yards.getInstance().toAll("94.677446", 5).getFirst();

        assertNotNull(results);
        assertEquals("3408.38806", results.get(0));
        assertEquals("284.03234", results.get(1));
        assertEquals("0.05379", results.get(2));
        assertEquals("86573.05662", results.get(3));
        assertEquals("8657.30566", results.get(4));
        assertEquals("86.57306", results.get(5));
        assertEquals("0.08657", results.get(6));
    }

    @Test
    public void testToInches() throws Exception {
        assertEquals("3408.388056", Yards.getInstance().toInches("94.677446", 10));
        assertEquals("3408.38806", Yards.getInstance().toInches("94.677446", 5));
    }

    @Test
    public void testToFeet() throws Exception {
        assertEquals("284.032338", Yards.getInstance().toFeet("94.677446", 10));
        assertEquals("284.03234", Yards.getInstance().toFeet("94.677446", 5));
    }

    @Test
    public void testToMiles() throws Exception {
        assertEquals("0.0537940034", Yards.getInstance().toMiles("94.677446", 10));
        assertEquals("0.05379", Yards.getInstance().toMiles("94.677446", 5));
    }

    @Test
    public void testToMillimeters() throws Exception {
        assertEquals("86573.0566224", Yards.getInstance().toMillimeters("94.677446", 10));
        assertEquals("86573.05662", Yards.getInstance().toMillimeters("94.677446", 5));
    }

    @Test
    public void testToCentimeters() throws Exception {
        assertEquals("8657.30566224", Yards.getInstance().toCentimeters("94.677446", 10));
        assertEquals("8657.30566", Yards.getInstance().toCentimeters("94.677446", 5));
    }

    @Test
    public void testToMeters() throws Exception {
        assertEquals("86.5730566224", Yards.getInstance().toMeters("94.677446", 10));
        assertEquals("86.57306", Yards.getInstance().toMeters("94.677446", 5));
    }

    @Test
    public void testToKilometers() throws Exception {
        assertEquals("0.0865730566", Yards.getInstance().toKilometers("94.677446", 10));
        assertEquals("0.08657", Yards.getInstance().toKilometers("94.677446", 5));
    }
}