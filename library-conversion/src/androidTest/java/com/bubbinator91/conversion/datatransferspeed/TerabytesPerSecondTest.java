package com.bubbinator91.conversion.datatransferspeed;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link TerabytesPerSecond} class.
 */
@RunWith(AndroidJUnit4.class)
public class TerabytesPerSecondTest {

    @Test
    public void testToAll() throws Exception {
        List<String> results = TerabytesPerSecond.toAll("0.0264976532649164376495623", 10).first;

        assertNotNull(results);
        assertEquals("211981226119.3315011965", results.get(0));
        assertEquals("26497653264.9164376496", results.get(1));
        assertEquals("211981226.1193315012", results.get(2));
        assertEquals("26497653.2649164376", results.get(3));
        assertEquals("211981.2261193315", results.get(4));
        assertEquals("26497.6532649164", results.get(5));
        assertEquals("211.9812261193", results.get(6));
        assertEquals("26.4976532649", results.get(7));
        assertEquals("0.2119812261", results.get(8));

        results =  TerabytesPerSecond.toAll("0.0264976532649164376495623", 5).first;

        assertNotNull(results);
        assertEquals("211981226119.3315", results.get(0));
        assertEquals("26497653264.91644", results.get(1));
        assertEquals("211981226.11933", results.get(2));
        assertEquals("26497653.26492", results.get(3));
        assertEquals("211981.22612", results.get(4));
        assertEquals("26497.65326", results.get(5));
        assertEquals("211.98123", results.get(6));
        assertEquals("26.49765", results.get(7));
        assertEquals("0.21198", results.get(8));
    }

    @Test
    public void testToBitsPerSecond() throws Exception {
        assertEquals("211981226119.3315011965", TerabytesPerSecond.toBitsPerSecond("0.0264976532649164376495623", 10));
        assertEquals("211981226119.3315", TerabytesPerSecond.toBitsPerSecond("0.0264976532649164376495623", 5));
    }

    @Test
    public void testToBytesPerSecond() throws Exception {
        assertEquals("26497653264.9164376496", TerabytesPerSecond.toBytesPerSecond("0.0264976532649164376495623", 10));
        assertEquals("26497653264.91644", TerabytesPerSecond.toBytesPerSecond("0.0264976532649164376495623", 5));
    }

    @Test
    public void testToKilobitsPerSecond() throws Exception {
        assertEquals("211981226.1193315012", TerabytesPerSecond.toKilobitsPerSecond("0.0264976532649164376495623", 10));
        assertEquals("211981226.11933", TerabytesPerSecond.toKilobitsPerSecond("0.0264976532649164376495623", 5));
    }

    @Test
    public void testToKilobytesPerSecond() throws Exception {
        assertEquals("26497653.2649164376", TerabytesPerSecond.toKilobytesPerSecond("0.0264976532649164376495623", 10));
        assertEquals("26497653.26492", TerabytesPerSecond.toKilobytesPerSecond("0.0264976532649164376495623", 5));
    }

    @Test
    public void testToMegabitsPerSecond() throws Exception {
        assertEquals("211981.2261193315", TerabytesPerSecond.toMegabitsPerSecond("0.0264976532649164376495623", 10));
        assertEquals("211981.22612", TerabytesPerSecond.toMegabitsPerSecond("0.0264976532649164376495623", 5));
    }

    @Test
    public void testToMegabytesPerSecond() throws Exception {
        assertEquals("26497.6532649164", TerabytesPerSecond.toMegabytesPerSecond("0.0264976532649164376495623", 10));
        assertEquals("26497.65326", TerabytesPerSecond.toMegabytesPerSecond("0.0264976532649164376495623", 5));
    }

    @Test
    public void testToGigabitsPerSecond() throws Exception {
        assertEquals("211.9812261193", TerabytesPerSecond.toGigabitsPerSecond("0.0264976532649164376495623", 10));
        assertEquals("211.98123", TerabytesPerSecond.toGigabitsPerSecond("0.0264976532649164376495623", 5));
    }

    @Test
    public void testToGigabytesPerSecond() throws Exception {
        assertEquals("26.4976532649", TerabytesPerSecond.toGigabytesPerSecond("0.0264976532649164376495623", 10));
        assertEquals("26.49765", TerabytesPerSecond.toGigabytesPerSecond("0.0264976532649164376495623", 5));
    }

    @Test
    public void testToTerabitsPerSecond() throws Exception {
        assertEquals("0.2119812261", TerabytesPerSecond.toTerabitsPerSecond("0.0264976532649164376495623", 10));
        assertEquals("0.21198", TerabytesPerSecond.toTerabitsPerSecond("0.0264976532649164376495623", 5));
    }
}