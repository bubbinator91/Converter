package com.bubbinator91.converter.tests.main;

import com.bubbinator91.converter.mock.views.MockTemperatureView;
import com.bubbinator91.converter.interfaces.presenter.ITemperaturePresenter;
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
    MockTemperatureView temperatureView;
    @Inject
    ITemperaturePresenter temperaturePresenter;

    @Before
    public void setUp() throws Exception {
        TestHelper.getTestClassInjector()
                .inject(this);

        assertNotNull(temperatureView);
        assertNotNull(temperaturePresenter);

        temperaturePresenter.onResume();
    }

    @Test
    public void testCelsiusConversion() throws Exception {
        temperaturePresenter.afterCelsiusTextChanged("20.9764956236", 10);
        TestHelper.waitFor(() -> ((temperatureView.fahrenheitValue != null)
                && (temperatureView.kelvinValue != null)));

        assertEquals(false, temperatureView.celsiusError);
        assertEquals("69.7576921225", temperatureView.fahrenheitValue);
        assertEquals("294.1264956236", temperatureView.kelvinValue);

        temperaturePresenter.afterCelsiusTextChanged("20.9764956236", 5);
        TestHelper.waitFor(() -> ((temperatureView.fahrenheitValue != null)
                && (temperatureView.kelvinValue != null)));

        assertEquals(false, temperatureView.celsiusError);
        assertEquals("69.75769", temperatureView.fahrenheitValue);
        assertEquals("294.1265", temperatureView.kelvinValue);

        temperatureView.resetValues();
    }

    @Test
    public void testFahrenheitConversion() throws Exception {
        temperaturePresenter.afterFahrenheitTextChanged("80.9467316594316", 10);
        TestHelper.waitFor(() -> ((temperatureView.celsiusValue != null)
                && (temperatureView.kelvinValue != null)));

        assertEquals(false, temperatureView.fahrenheitError);
        assertEquals("27.1926286997", temperatureView.celsiusValue);
        assertEquals("300.3426286997", temperatureView.kelvinValue);

        temperaturePresenter.afterFahrenheitTextChanged("80.9467316594316", 5);
        TestHelper.waitFor(() -> ((temperatureView.celsiusValue != null)
                && (temperatureView.kelvinValue != null)));

        assertEquals(false, temperatureView.fahrenheitError);
        assertEquals("27.19263", temperatureView.celsiusValue);
        assertEquals("300.34263", temperatureView.kelvinValue);

        temperatureView.resetValues();
    }

    @Test
    public void testKelvinConversion() throws Exception {
        temperaturePresenter.afterKelvinTextChanged("310.9764326946", 10);
        TestHelper.waitFor(() -> ((temperatureView.celsiusValue != null)
                && (temperatureView.fahrenheitValue != null)));

        assertEquals(false, temperatureView.kelvinError);
        assertEquals("37.8264326946", temperatureView.celsiusValue);
        assertEquals("100.0875788503", temperatureView.fahrenheitValue);

        temperaturePresenter.afterKelvinTextChanged("310.9764326946", 5);
        TestHelper.waitFor(() -> ((temperatureView.celsiusValue != null)
                && (temperatureView.fahrenheitValue != null)));

        assertEquals(false, temperatureView.kelvinError);
        assertEquals("37.82643", temperatureView.celsiusValue);
        assertEquals("100.08758", temperatureView.fahrenheitValue);

        temperatureView.resetValues();
    }
}
