package com.bubbinator91.converter.tests.main;

import com.bubbinator91.converter.mock.views.MockFuelConsumptionView;
import com.bubbinator91.converter.interfaces.presenter.IFuelConsumptionPresenter;
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
    MockFuelConsumptionView fuelConsumptionView;
    @Inject
    IFuelConsumptionPresenter fuelConsumptionPresenter;

    @Before
    public void setUp() throws Exception {
        TestHelper.getTestClassInjector()
                .inject(this);

        assertNotNull(fuelConsumptionView);
        assertNotNull(fuelConsumptionPresenter);
    }

    @Test
    public void testUSMilesPerGallonConversion() throws Exception {
        fuelConsumptionPresenter
                .getConversionFromUSMilesPerGallon("2.15235645123659", 10);
        TestHelper.waitFor(() -> ((fuelConsumptionView.ukmpgValue != null)
                && (fuelConsumptionView.kplValue != null)
                && (fuelConsumptionView.l100kValue != null)));

        assertEquals(false, fuelConsumptionView.usmpgError);
        assertEquals("2.5848723198", fuelConsumptionView.ukmpgValue);
        assertEquals("0.9150608015", fuelConsumptionView.kplValue);
        assertEquals("109.2823557168", fuelConsumptionView.l100kValue);

        fuelConsumptionPresenter
                .getConversionFromUSMilesPerGallon("2.15235645123659", 5);
        TestHelper.waitFor(() -> ((fuelConsumptionView.ukmpgValue != null)
                && (fuelConsumptionView.kplValue != null)
                && (fuelConsumptionView.l100kValue != null)));

        assertEquals(false, fuelConsumptionView.usmpgError);
        assertEquals("2.58487", fuelConsumptionView.ukmpgValue);
        assertEquals("0.91506", fuelConsumptionView.kplValue);
        assertEquals("109.28236", fuelConsumptionView.l100kValue);

        fuelConsumptionView.resetValues();
    }

    @Test
    public void testUKMilesPerGallonConversion() throws Exception {
        fuelConsumptionPresenter
                .getConversionFromUKMilesPerGallon("3.6589456897564", 10);
        TestHelper.waitFor(() -> ((fuelConsumptionView.usmpgValue != null)
                && (fuelConsumptionView.kplValue != null)
                && (fuelConsumptionView.l100kValue != null)));

        assertEquals(false, fuelConsumptionView.ukmpgError);
        assertEquals("3.0467096187", fuelConsumptionView.usmpgValue);
        assertEquals("1.295289423", fuelConsumptionView.kplValue);
        assertEquals("77.2028229669", fuelConsumptionView.l100kValue);

        fuelConsumptionPresenter
                .getConversionFromUKMilesPerGallon("3.6589456897564", 5);
        TestHelper.waitFor(() -> ((fuelConsumptionView.usmpgValue != null)
                && (fuelConsumptionView.kplValue != null)
                && (fuelConsumptionView.l100kValue != null)));

        assertEquals(false, fuelConsumptionView.ukmpgError);
        assertEquals("3.04671", fuelConsumptionView.usmpgValue);
        assertEquals("1.29529", fuelConsumptionView.kplValue);
        assertEquals("77.20282", fuelConsumptionView.l100kValue);

        fuelConsumptionView.resetValues();
    }

    @Test
    public void testKilometersPerLiterConversion() throws Exception {
        fuelConsumptionPresenter
                .getConversionFromKilometersPerLiter("4.5613258749654", 10);
        TestHelper.waitFor(() -> ((fuelConsumptionView.usmpgValue != null)
                && (fuelConsumptionView.ukmpgValue != null)
                && (fuelConsumptionView.l100kValue != null)));

        assertEquals(false, fuelConsumptionView.kplError);
        assertEquals("10.7289036498", fuelConsumptionView.usmpgValue);
        assertEquals("12.8848760393", fuelConsumptionView.ukmpgValue);
        assertEquals("21.923450054", fuelConsumptionView.l100kValue);

        fuelConsumptionPresenter
                .getConversionFromKilometersPerLiter("4.5613258749654", 5);
        TestHelper.waitFor(() -> ((fuelConsumptionView.usmpgValue != null)
                && (fuelConsumptionView.ukmpgValue != null)
                && (fuelConsumptionView.l100kValue != null)));

        assertEquals(false, fuelConsumptionView.kplError);
        assertEquals("10.7289", fuelConsumptionView.usmpgValue);
        assertEquals("12.88488", fuelConsumptionView.ukmpgValue);
        assertEquals("21.92345", fuelConsumptionView.l100kValue);

        fuelConsumptionView.resetValues();
    }

    @Test
    public void testLitersPer100KilometersConversion() throws Exception {
        fuelConsumptionPresenter
                .getConversionFromLitersPer100Kilometers("8.56498658974125", 10);
        TestHelper.waitFor(() -> ((fuelConsumptionView.usmpgValue != null)
                && (fuelConsumptionView.ukmpgValue != null)
                && (fuelConsumptionView.kplValue != null)));

        assertEquals(false, fuelConsumptionView.l100kError);
        assertEquals("27.4623411104", fuelConsumptionView.usmpgValue);
        assertEquals("32.9808965107", fuelConsumptionView.ukmpgValue);
        assertEquals("11.6754415144", fuelConsumptionView.kplValue);

        fuelConsumptionPresenter
                .getConversionFromLitersPer100Kilometers("8.56498658974125", 5);
        TestHelper.waitFor(() -> ((fuelConsumptionView.usmpgValue != null)
                && (fuelConsumptionView.ukmpgValue != null)
                && (fuelConsumptionView.kplValue != null)));

        assertEquals(false, fuelConsumptionView.l100kError);
        assertEquals("27.46234", fuelConsumptionView.usmpgValue);
        assertEquals("32.9809", fuelConsumptionView.ukmpgValue);
        assertEquals("11.67544", fuelConsumptionView.kplValue);

        fuelConsumptionView.resetValues();
    }
}
