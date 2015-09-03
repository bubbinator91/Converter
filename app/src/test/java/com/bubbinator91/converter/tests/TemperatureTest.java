package com.bubbinator91.converter.tests;

import com.bubbinator91.converter.mock.views.MockTemperatureView;
import com.bubbinator91.converter.ui.interfaces.temperature.ITemperaturePresenter;
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
public class TemperatureTest {

    @Inject
    MockTemperatureView mTemperatureView;
    @Inject
    ITemperaturePresenter mTemperaturePresenter;

    @Before
    public void setUp() throws Exception {
        TestHelper.getTestClassInjector()
                .inject(this);

        assertNotNull(mTemperatureView);
        assertNotNull(mTemperaturePresenter);
    }

    @Test
    public void testCelsiusConversion() throws Exception {
        mTemperaturePresenter.getConversionFromCelsiusResults("20.9764956236", 10);
        TestHelper.waitFor(() ->
                        ((mTemperatureView.mFahrenheitValue != null) && (mTemperatureView.mKelvinValue != null))
        );

        assertEquals(0, mTemperatureView.mCelsiusError);
        assertEquals("69.7576921225", mTemperatureView.mFahrenheitValue);
        assertEquals("294.1264956236", mTemperatureView.mKelvinValue);

        mTemperaturePresenter.getConversionFromCelsiusResults("20.9764956236", 5);
        TestHelper.waitFor(() ->
                        ((mTemperatureView.mFahrenheitValue != null) && (mTemperatureView.mKelvinValue != null))
        );

        assertEquals(0, mTemperatureView.mCelsiusError);
        assertEquals("69.75769", mTemperatureView.mFahrenheitValue);
        assertEquals("294.1265", mTemperatureView.mKelvinValue);
    }

    @Test
    public void testFahrenheitConversion() throws Exception {
        mTemperaturePresenter.getConversionFromFahrenheitResults("80.9467316594316", 10);
        TestHelper.waitFor(() ->
                        ((mTemperatureView.mCelsiusValue != null) && (mTemperatureView.mKelvinValue != null))
        );

        assertEquals(0, mTemperatureView.mFahrenheitError);
        assertEquals("27.1926286997", mTemperatureView.mCelsiusValue);
        assertEquals("300.3426286997", mTemperatureView.mKelvinValue);

        mTemperaturePresenter.getConversionFromFahrenheitResults("80.9467316594316", 5);
        TestHelper.waitFor(() ->
                        ((mTemperatureView.mCelsiusValue != null) && (mTemperatureView.mKelvinValue != null))
        );

        assertEquals(0, mTemperatureView.mFahrenheitError);
        assertEquals("27.19263", mTemperatureView.mCelsiusValue);
        assertEquals("300.34263", mTemperatureView.mKelvinValue);
    }

    @Test
    public void testKelvinConversion() throws Exception {
        mTemperaturePresenter.getConversionFromKelvinResults("310.9764326946", 10);
        TestHelper.waitFor(() ->
                        ((mTemperatureView.mCelsiusValue != null) && (mTemperatureView.mFahrenheitValue != null))
        );

        assertEquals(0, mTemperatureView.mKelvinError);
        assertEquals("37.8264326946", mTemperatureView.mCelsiusValue);
        assertEquals("100.0875788503", mTemperatureView.mFahrenheitValue);

        mTemperaturePresenter.getConversionFromKelvinResults("310.9764326946", 5);
        TestHelper.waitFor(() ->
                        ((mTemperatureView.mCelsiusValue != null) && (mTemperatureView.mFahrenheitValue != null))
        );

        assertEquals(0, mTemperatureView.mKelvinError);
        assertEquals("37.82643", mTemperatureView.mCelsiusValue);
        assertEquals("100.08758", mTemperatureView.mFahrenheitValue);
    }
}
