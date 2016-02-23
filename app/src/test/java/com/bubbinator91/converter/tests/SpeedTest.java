package com.bubbinator91.converter.tests;

import com.bubbinator91.converter.mock.views.MockSpeedView;
import com.bubbinator91.converter.interfaces.presenter.ISpeedPresenter;
import com.bubbinator91.converter.util.TestHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * A collection of JUnit tests that test the speed presenter's interaction with a mock of the speed
 * view.
 */
@RunWith(JUnit4.class)
public class SpeedTest {

    @Inject
    MockSpeedView speedView;
    @Inject
    ISpeedPresenter speedPresenter;

    @Before
    public void setUp() throws Exception {
        TestHelper.getTestClassInjector()
                .inject(this);

        assertNotNull(speedView);
        assertNotNull(speedPresenter);
    }

    @Test
    public void testFeetPerSecondConversion() throws Exception {
        speedPresenter.getConversionFromFeetPerSecond("4.3465986431", 10);
        TestHelper.waitFor(() -> ((speedView.knotValue != null)
                && (speedView.kphValue != null)
                && (speedView.mpsValue != null)
                && (speedView.mphValue != null)));

        assertEquals(false, speedView.fpsError);
        assertEquals("2.5752892868", speedView.knotValue);
        assertEquals("4.7694357591", speedView.kphValue);
        assertEquals("1.3248432664", speedView.mpsValue);
        assertEquals("2.9635899839", speedView.mphValue);

        speedPresenter.getConversionFromFeetPerSecond("4.3465986431", 5);
        TestHelper.waitFor(() -> ((speedView.knotValue != null)
                && (speedView.kphValue != null)
                && (speedView.mpsValue != null)
                && (speedView.mphValue != null)));

        assertEquals(false, speedView.fpsError);
        assertEquals("2.57529", speedView.knotValue);
        assertEquals("4.76944", speedView.kphValue);
        assertEquals("1.32484", speedView.mpsValue);
        assertEquals("2.96359", speedView.mphValue);

        speedView.resetValues();
    }

    @Test
    public void testKnotsConversion() throws Exception {
        speedPresenter.getConversionFromKnots("3.465923564312", 10);
        TestHelper.waitFor(() -> ((speedView.fpsValue != null)
                && (speedView.kphValue != null)
                && (speedView.mpsValue != null)
                && (speedView.mphValue != null)));

        assertEquals(false, speedView.knotError);
        assertEquals("5.8498199558", speedView.fpsValue);
        assertEquals("6.4188904411", speedView.kphValue);
        assertEquals("1.7830251225", speedView.mpsValue);
        assertEquals("3.9885136062", speedView.mphValue);

        speedPresenter.getConversionFromKnots("3.465923564312", 5);
        TestHelper.waitFor(() -> ((speedView.fpsValue != null)
                && (speedView.kphValue != null)
                && (speedView.mpsValue != null)
                && (speedView.mphValue != null)));

        assertEquals(false, speedView.knotError);
        assertEquals("5.84982", speedView.fpsValue);
        assertEquals("6.41889", speedView.kphValue);
        assertEquals("1.78303", speedView.mpsValue);
        assertEquals("3.98851", speedView.mphValue);

        speedView.resetValues();
    }

    @Test
    public void testKilometersPerHourConversion() throws Exception {
        speedPresenter.getConversionFromKilometersPerHour("7.5806235947", 10);
        TestHelper.waitFor(() -> ((speedView.fpsValue != null)
                && (speedView.knotValue != null)
                && (speedView.mpsValue != null)
                && (speedView.mphValue != null)));

        assertEquals(false, speedView.kphError);
        assertEquals("6.9085589774", speedView.fpsValue);
        assertEquals("4.0932092844", speedView.knotValue);
        assertEquals("2.1057287763", speedView.mpsValue);
        assertEquals("4.7103811209", speedView.mphValue);

        speedPresenter.getConversionFromKilometersPerHour("7.5806235947", 5);
        TestHelper.waitFor(() -> ((speedView.fpsValue != null)
                && (speedView.knotValue != null)
                && (speedView.mpsValue != null)
                && (speedView.mphValue != null)));

        assertEquals(false, speedView.kphError);
        assertEquals("6.90856", speedView.fpsValue);
        assertEquals("4.09321", speedView.knotValue);
        assertEquals("2.10573", speedView.mpsValue);
        assertEquals("4.71038", speedView.mphValue);

        speedView.resetValues();
    }

    @Test
    public void testMetersPerSecondConversion() throws Exception {
        speedPresenter.getConversionFromMetersPerSecond("2.894653261712", 10);
        TestHelper.waitFor(() -> ((speedView.fpsValue != null)
                && (speedView.knotValue != null)
                && (speedView.kphValue != null)
                && (speedView.mphValue != null)));

        assertEquals(false, speedView.mpsError);
        assertEquals("9.4968939033", speedView.fpsValue);
        assertEquals("5.6267558003", speedView.knotValue);
        assertEquals("10.4207517422", speedView.kphValue);
        assertEquals("6.475154934", speedView.mphValue);

        speedPresenter.getConversionFromMetersPerSecond("2.894653261712", 5);
        TestHelper.waitFor(() -> ((speedView.fpsValue != null)
                && (speedView.knotValue != null)
                && (speedView.kphValue != null)
                && (speedView.mphValue != null)));

        assertEquals(false, speedView.mpsError);
        assertEquals("9.49689", speedView.fpsValue);
        assertEquals("5.62676", speedView.knotValue);
        assertEquals("10.42075", speedView.kphValue);
        assertEquals("6.47515", speedView.mphValue);

        speedView.resetValues();
    }

    @Test
    public void testMilesPerHourConversion() throws Exception {
        speedPresenter.getConversionFromMilesPerHour("5.346592653461", 10);
        TestHelper.waitFor(() -> ((speedView.fpsValue != null)
                && (speedView.knotValue != null)
                && (speedView.kphValue != null)
                && (speedView.mpsValue != null)));

        assertEquals(false, speedView.mphError);
        assertEquals("7.8416692251", speedView.fpsValue);
        assertEquals("4.646061991", speedView.knotValue);
        assertEquals("8.6045068073", speedView.kphValue);
        assertEquals("2.3901407798", speedView.mpsValue);

        speedPresenter.getConversionFromMilesPerHour("5.346592653461", 5);
        TestHelper.waitFor(() -> ((speedView.fpsValue != null)
                && (speedView.knotValue != null)
                && (speedView.kphValue != null)
                && (speedView.mpsValue != null)));

        assertEquals(false, speedView.mphError);
        assertEquals("7.84167", speedView.fpsValue);
        assertEquals("4.64606", speedView.knotValue);
        assertEquals("8.60451", speedView.kphValue);
        assertEquals("2.39014", speedView.mpsValue);

        speedView.resetValues();
    }
}
