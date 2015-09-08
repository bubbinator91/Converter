package com.bubbinator91.converter.tests;

import com.bubbinator91.converter.mock.views.MockSpeedView;
import com.bubbinator91.converter.ui.interfaces.speed.ISpeedPresenter;
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
    MockSpeedView mSpeedView;
    @Inject
    ISpeedPresenter mSpeedPresenter;

    @Before
    public void setUp() throws Exception {
        TestHelper.getTestClassInjector()
                .inject(this);

        assertNotNull(mSpeedView);
        assertNotNull(mSpeedPresenter);
    }

    @Test
    public void testFeetPerSecondConversion() throws Exception {
        mSpeedPresenter.getConversionFromFeetPerSecondResults("4.3465986431", 10);
        TestHelper.waitFor(() -> ((mSpeedView.mKnotValue != null)
                && (mSpeedView.mKphValue != null)
                && (mSpeedView.mMpsValue != null)
                && (mSpeedView.mMphValue != null)));

        assertEquals(0, mSpeedView.mFpsError);
        assertEquals("2.5752892868", mSpeedView.mKnotValue);
        assertEquals("4.7694357591", mSpeedView.mKphValue);
        assertEquals("1.3248432664", mSpeedView.mMpsValue);
        assertEquals("2.9635899839", mSpeedView.mMphValue);

        mSpeedPresenter.getConversionFromFeetPerSecondResults("4.3465986431", 5);
        TestHelper.waitFor(() -> ((mSpeedView.mKnotValue != null)
                && (mSpeedView.mKphValue != null)
                && (mSpeedView.mMpsValue != null)
                && (mSpeedView.mMphValue != null)));

        assertEquals(0, mSpeedView.mFpsError);
        assertEquals("2.57529", mSpeedView.mKnotValue);
        assertEquals("4.76944", mSpeedView.mKphValue);
        assertEquals("1.32484", mSpeedView.mMpsValue);
        assertEquals("2.96359", mSpeedView.mMphValue);

        mSpeedView.resetValues();
    }

    @Test
    public void testKnotsConversion() throws Exception {
        mSpeedPresenter.getConversionFromKnotsResults("3.465923564312", 10);
        TestHelper.waitFor(() -> ((mSpeedView.mFpsValue != null)
                && (mSpeedView.mKphValue != null)
                && (mSpeedView.mMpsValue != null)
                && (mSpeedView.mMphValue != null)));

        assertEquals(0, mSpeedView.mKnotError);
        assertEquals("5.8498199558", mSpeedView.mFpsValue);
        assertEquals("6.4188904411", mSpeedView.mKphValue);
        assertEquals("1.7830251225", mSpeedView.mMpsValue);
        assertEquals("3.9885136062", mSpeedView.mMphValue);

        mSpeedPresenter.getConversionFromKnotsResults("3.465923564312", 5);
        TestHelper.waitFor(() -> ((mSpeedView.mFpsValue != null)
                && (mSpeedView.mKphValue != null)
                && (mSpeedView.mMpsValue != null)
                && (mSpeedView.mMphValue != null)));

        assertEquals(0, mSpeedView.mKnotError);
        assertEquals("5.84982", mSpeedView.mFpsValue);
        assertEquals("6.41889", mSpeedView.mKphValue);
        assertEquals("1.78303", mSpeedView.mMpsValue);
        assertEquals("3.98851", mSpeedView.mMphValue);

        mSpeedView.resetValues();
    }

    @Test
    public void testKilometersPerHourConversion() throws Exception {
        mSpeedPresenter.getConversionFromKilometersPerHourResults("7.5806235947", 10);
        TestHelper.waitFor(() -> ((mSpeedView.mFpsValue != null)
                && (mSpeedView.mKnotValue != null)
                && (mSpeedView.mMpsValue != null)
                && (mSpeedView.mMphValue != null)));

        assertEquals(0, mSpeedView.mKphError);
        assertEquals("6.9085589774", mSpeedView.mFpsValue);
        assertEquals("4.0932092844", mSpeedView.mKnotValue);
        assertEquals("2.1057287763", mSpeedView.mMpsValue);
        assertEquals("4.7103811209", mSpeedView.mMphValue);

        mSpeedPresenter.getConversionFromKilometersPerHourResults("7.5806235947", 5);
        TestHelper.waitFor(() -> ((mSpeedView.mFpsValue != null)
                && (mSpeedView.mKnotValue != null)
                && (mSpeedView.mMpsValue != null)
                && (mSpeedView.mMphValue != null)));

        assertEquals(0, mSpeedView.mKphError);
        assertEquals("6.90856", mSpeedView.mFpsValue);
        assertEquals("4.09321", mSpeedView.mKnotValue);
        assertEquals("2.10573", mSpeedView.mMpsValue);
        assertEquals("4.71038", mSpeedView.mMphValue);

        mSpeedView.resetValues();
    }

    @Test
    public void testMetersPerSecondConversion() throws Exception {
        mSpeedPresenter.getConversionFromMetersPerSecondResults("2.894653261712", 10);
        TestHelper.waitFor(() -> ((mSpeedView.mFpsValue != null)
                && (mSpeedView.mKnotValue != null)
                && (mSpeedView.mKphValue != null)
                && (mSpeedView.mMphValue != null)));

        assertEquals(0, mSpeedView.mMpsError);
        assertEquals("9.4968939033", mSpeedView.mFpsValue);
        assertEquals("5.6267558003", mSpeedView.mKnotValue);
        assertEquals("10.4207517422", mSpeedView.mKphValue);
        assertEquals("6.475154934", mSpeedView.mMphValue);

        mSpeedPresenter.getConversionFromMetersPerSecondResults("2.894653261712", 5);
        TestHelper.waitFor(() -> ((mSpeedView.mFpsValue != null)
                && (mSpeedView.mKnotValue != null)
                && (mSpeedView.mKphValue != null)
                && (mSpeedView.mMphValue != null)));

        assertEquals(0, mSpeedView.mMpsError);
        assertEquals("9.49689", mSpeedView.mFpsValue);
        assertEquals("5.62676", mSpeedView.mKnotValue);
        assertEquals("10.42075", mSpeedView.mKphValue);
        assertEquals("6.47515", mSpeedView.mMphValue);

        mSpeedView.resetValues();
    }

    @Test
    public void testMilesPerHourConversion() throws Exception {
        mSpeedPresenter.getConversionFromMilesPerHourResults("5.346592653461", 10);
        TestHelper.waitFor(() -> ((mSpeedView.mFpsValue != null)
                && (mSpeedView.mKnotValue != null)
                && (mSpeedView.mKphValue != null)
                && (mSpeedView.mMpsValue != null)));

        assertEquals(0, mSpeedView.mMphError);
        assertEquals("7.8416692251", mSpeedView.mFpsValue);
        assertEquals("4.646061991", mSpeedView.mKnotValue);
        assertEquals("8.6045068073", mSpeedView.mKphValue);
        assertEquals("2.3901407798", mSpeedView.mMpsValue);

        mSpeedPresenter.getConversionFromMilesPerHourResults("5.346592653461", 5);
        TestHelper.waitFor(() -> ((mSpeedView.mFpsValue != null)
                && (mSpeedView.mKnotValue != null)
                && (mSpeedView.mKphValue != null)
                && (mSpeedView.mMpsValue != null)));

        assertEquals(0, mSpeedView.mMphError);
        assertEquals("7.84167", mSpeedView.mFpsValue);
        assertEquals("4.64606", mSpeedView.mKnotValue);
        assertEquals("8.60451", mSpeedView.mKphValue);
        assertEquals("2.39014", mSpeedView.mMpsValue);

        mSpeedView.resetValues();
    }
}
