package com.bubbinator91.converter.conversion.datatransferspeed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the conversion methods in the {@link MegabitsPerSecond} class.
 */
@RunWith(JUnit4.class)
public class MegabitsPerSecondTest {

    @Test
    public void testGetInstance() throws Exception {
        MegabitsPerSecond mbps = MegabitsPerSecond.getInstance();

        assertNotNull(mbps);
    }

    @Test
    public void testToAll() throws Exception {
        List<String> results =
                MegabitsPerSecond.getInstance().toAll("8009.976435623164", 10).getFirst();

        assertNotNull(results);
        assertEquals("8009976435.623164", results.get(0));
        assertEquals("1001247054.4528955", results.get(1));
        assertEquals("8009976.435623164", results.get(2));
        assertEquals("1001247.0544528955", results.get(3));
        assertEquals("1001.2470544529", results.get(4));
        assertEquals("8.0099764356", results.get(5));
        assertEquals("1.0012470545", results.get(6));
        assertEquals("0.0080099764", results.get(7));
        assertEquals("0.0010012471", results.get(8));

        results =
                MegabitsPerSecond.getInstance().toAll("8009.976435623164", 5).getFirst();

        assertNotNull(results);
        assertEquals("8009976435.62316", results.get(0));
        assertEquals("1001247054.4529", results.get(1));
        assertEquals("8009976.43562", results.get(2));
        assertEquals("1001247.05445", results.get(3));
        assertEquals("1001.24705", results.get(4));
        assertEquals("8.00998", results.get(5));
        assertEquals("1.00125", results.get(6));
        assertEquals("0.00801", results.get(7));
        assertEquals("0.001", results.get(8));
    }

    @Test
    public void testToBitsPerSecond() throws Exception {
        assertEquals("8009976435.623164",
                MegabitsPerSecond.getInstance().toBitsPerSecond("8009.976435623164", 10));
        assertEquals("8009976435.62316",
                MegabitsPerSecond.getInstance().toBitsPerSecond("8009.976435623164", 5));
    }

    @Test
    public void testToBytesPerSecond() throws Exception {
        assertEquals("1001247054.4528955",
                MegabitsPerSecond.getInstance().toBytesPerSecond("8009.976435623164", 10));
        assertEquals("1001247054.4529",
                MegabitsPerSecond.getInstance().toBytesPerSecond("8009.976435623164", 5));
    }

    @Test
    public void testToKilobitsPerSecond() throws Exception {
        assertEquals("8009976.435623164",
                MegabitsPerSecond.getInstance().toKilobitsPerSecond("8009.976435623164", 10));
        assertEquals("8009976.43562",
                MegabitsPerSecond.getInstance().toKilobitsPerSecond("8009.976435623164", 5));
    }

    @Test
    public void testToKilobytesPerSecond() throws Exception {
        assertEquals("1001247.0544528955",
                MegabitsPerSecond.getInstance().toKilobytesPerSecond("8009.976435623164", 10));
        assertEquals("1001247.05445",
                MegabitsPerSecond.getInstance().toKilobytesPerSecond("8009.976435623164", 5));
    }

    @Test
    public void testToMegabytesPerSecond() throws Exception {
        assertEquals("1001.2470544529",
                MegabitsPerSecond.getInstance().toMegabytesPerSecond("8009.976435623164", 10));
        assertEquals("1001.24705",
                MegabitsPerSecond.getInstance().toMegabytesPerSecond("8009.976435623164", 5));
    }

    @Test
    public void testToGigabitsPerSecond() throws Exception {
        assertEquals("8.0099764356",
                MegabitsPerSecond.getInstance().toGigabitsPerSecond("8009.976435623164", 10));
        assertEquals("8.00998",
                MegabitsPerSecond.getInstance().toGigabitsPerSecond("8009.976435623164", 5));
    }

    @Test
    public void testToGigabytesPerSecond() throws Exception {
        assertEquals("1.0012470545",
                MegabitsPerSecond.getInstance().toGigabytesPerSecond("8009.976435623164", 10));
        assertEquals("1.00125",
                MegabitsPerSecond.getInstance().toGigabytesPerSecond("8009.976435623164", 5));
    }

    @Test
    public void testToTerabitsPerSecond() throws Exception {
        assertEquals("0.0080099764",
                MegabitsPerSecond.getInstance().toTerabitsPerSecond("8009.976435623164", 10));
        assertEquals("0.00801",
                MegabitsPerSecond.getInstance().toTerabitsPerSecond("8009.976435623164", 5));
    }

    @Test
    public void testToTerabytesPerSecond() throws Exception {
        assertEquals("0.0010012471",
                MegabitsPerSecond.getInstance().toTerabytesPerSecond("8009.976435623164", 10));
        assertEquals("0.001",
                MegabitsPerSecond.getInstance().toTerabytesPerSecond("8009.976435623164", 5));
    }
}