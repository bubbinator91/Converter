package com.bubbinator91.converter.tests;

import com.bubbinator91.converter.mock.views.MockFuelConsumptionView;
import com.bubbinator91.converter.ui.interfaces.fuelconsumption.IFuelConsumptionPresenter;
import com.bubbinator91.converter.util.TestHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * A collection of JUnit tests that test the temperature presenter's interaction with a mock of the
 * temperature view.
 */
@RunWith(JUnit4.class)
public class FuelConsumptionTest {

    @Inject
    MockFuelConsumptionView mFuelConsumptionView;
    @Inject
    IFuelConsumptionPresenter mFuelConsumptionPresenter;

    @Before
    public void setUp() throws Exception {
        TestHelper.getTestClassInjector()
                .inject(this);

        assertNotNull(mFuelConsumptionView);
        assertNotNull(mFuelConsumptionPresenter);
    }

    @Test
    public void testUSMilesPerGallonConversion() throws Exception {
        mFuelConsumptionPresenter
                .getConversionFromUSMilesPerGallonResults("2.15235645123659", 10);
        TestHelper.waitFor(() -> ((mFuelConsumptionView.mUkmpgValue != null)
                && (mFuelConsumptionView.mKplValue != null)
                && (mFuelConsumptionView.mL100kValue != null)));

        assertEquals(0, mFuelConsumptionView.mUsmpgError);
        assertEquals("2.5848723198", mFuelConsumptionView.mUkmpgValue);
        assertEquals("0.9150608015", mFuelConsumptionView.mKplValue);
        assertEquals("109.2823557168", mFuelConsumptionView.mL100kValue);

        mFuelConsumptionPresenter
                .getConversionFromUSMilesPerGallonResults("2.15235645123659", 5);
        TestHelper.waitFor(() -> ((mFuelConsumptionView.mUkmpgValue != null)
                && (mFuelConsumptionView.mKplValue != null)
                && (mFuelConsumptionView.mL100kValue != null)));

        assertEquals(0, mFuelConsumptionView.mUsmpgError);
        assertEquals("2.58487", mFuelConsumptionView.mUkmpgValue);
        assertEquals("0.91506", mFuelConsumptionView.mKplValue);
        assertEquals("109.28236", mFuelConsumptionView.mL100kValue);

        mFuelConsumptionView.resetValues();
    }

    @Test
    public void testUKMilesPerGallonConversion() throws Exception {
        mFuelConsumptionPresenter
                .getConversionFromUKMilesPerGallonResults("3.6589456897564", 10);
        TestHelper.waitFor(() -> ((mFuelConsumptionView.mUsmpgValue != null)
                && (mFuelConsumptionView.mKplValue != null)
                && (mFuelConsumptionView.mL100kValue != null)));

        assertEquals(0, mFuelConsumptionView.mUkmpgError);
        assertEquals("3.0467096187", mFuelConsumptionView.mUsmpgValue);
        assertEquals("1.295289423", mFuelConsumptionView.mKplValue);
        assertEquals("77.2028229669", mFuelConsumptionView.mL100kValue);

        mFuelConsumptionPresenter
                .getConversionFromUKMilesPerGallonResults("3.6589456897564", 5);
        TestHelper.waitFor(() -> ((mFuelConsumptionView.mUsmpgValue != null)
                && (mFuelConsumptionView.mKplValue != null)
                && (mFuelConsumptionView.mL100kValue != null)));

        assertEquals(0, mFuelConsumptionView.mUkmpgError);
        assertEquals("3.04671", mFuelConsumptionView.mUsmpgValue);
        assertEquals("1.29529", mFuelConsumptionView.mKplValue);
        assertEquals("77.20282", mFuelConsumptionView.mL100kValue);

        mFuelConsumptionView.resetValues();
    }

    @Test
    public void testKilometersPerLiterConversion() throws Exception {
        mFuelConsumptionPresenter
                .getConversionFromKilometersPerLiterResults("4.5613258749654", 10);
        TestHelper.waitFor(() -> ((mFuelConsumptionView.mUsmpgValue != null)
                && (mFuelConsumptionView.mUkmpgValue != null)
                && (mFuelConsumptionView.mL100kValue != null)));

        assertEquals(0, mFuelConsumptionView.mKplError);
        assertEquals("10.7289036498", mFuelConsumptionView.mUsmpgValue);
        assertEquals("12.8848760393", mFuelConsumptionView.mUkmpgValue);
        assertEquals("21.923450054", mFuelConsumptionView.mL100kValue);

        mFuelConsumptionPresenter
                .getConversionFromKilometersPerLiterResults("4.5613258749654", 5);
        TestHelper.waitFor(() -> ((mFuelConsumptionView.mUsmpgValue != null)
                && (mFuelConsumptionView.mUkmpgValue != null)
                && (mFuelConsumptionView.mL100kValue != null)));

        assertEquals(0, mFuelConsumptionView.mKplError);
        assertEquals("10.7289", mFuelConsumptionView.mUsmpgValue);
        assertEquals("12.88488", mFuelConsumptionView.mUkmpgValue);
        assertEquals("21.92345", mFuelConsumptionView.mL100kValue);

        mFuelConsumptionView.resetValues();
    }

    @Test
    public void testLitersPer100KilometersConversion() throws Exception {
        mFuelConsumptionPresenter
                .getConversionFromLitersPer100KilometersResults("8.56498658974125", 10);
        TestHelper.waitFor(() -> ((mFuelConsumptionView.mUsmpgValue != null)
                && (mFuelConsumptionView.mUkmpgValue != null)
                && (mFuelConsumptionView.mKplValue != null)));

        assertEquals(0, mFuelConsumptionView.mL100kError);
        assertEquals("27.4623411104", mFuelConsumptionView.mUsmpgValue);
        assertEquals("32.9808965107", mFuelConsumptionView.mUkmpgValue);
        assertEquals("11.6754415144", mFuelConsumptionView.mKplValue);

        mFuelConsumptionPresenter
                .getConversionFromLitersPer100KilometersResults("8.56498658974125", 5);
        TestHelper.waitFor(() -> ((mFuelConsumptionView.mUsmpgValue != null)
                && (mFuelConsumptionView.mUkmpgValue != null)
                && (mFuelConsumptionView.mKplValue != null)));

        assertEquals(0, mFuelConsumptionView.mL100kError);
        assertEquals("27.46234", mFuelConsumptionView.mUsmpgValue);
        assertEquals("32.9809", mFuelConsumptionView.mUkmpgValue);
        assertEquals("11.67544", mFuelConsumptionView.mKplValue);

        mFuelConsumptionView.resetValues();
    }
}
