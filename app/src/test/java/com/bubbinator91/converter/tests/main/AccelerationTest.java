package com.bubbinator91.converter.tests.main;

import com.bubbinator91.converter.mock.views.MockAccelerationView;
import com.bubbinator91.converter.interfaces.presenter.IAccelerationPresenter;
import com.bubbinator91.converter.util.TestHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * A collection of JUnit tests that test the acceleration presenter's interaction with a mock of the
 * acceleration view.
 */
@RunWith(JUnit4.class)
public class AccelerationTest {
    @Inject
    MockAccelerationView accelerationView;
    @Inject
    IAccelerationPresenter accelerationPresenter;

    @Before
    public void setUp() throws Exception {
        TestHelper.getTestClassInjector()
                .inject(this);

        assertNotNull(accelerationView);
        assertNotNull(accelerationPresenter);

        accelerationPresenter.onResume();
    }

    @Test
    public void testCentimetersPerSecondSquaredConversion() throws Exception {
        accelerationPresenter.afterCmpssTextChanged("8.0235641526359876", 10);
        TestHelper.waitFor(() ->
                        ((accelerationView.fpssValue != null)
                                && (accelerationView.mpssValue != null)
                                && (accelerationView.sgValue != null))
        );

        assertEquals(false, accelerationView.cmpssError);
        assertEquals("0.2632402937", accelerationView.fpssValue);
        assertEquals("0.0802356415", accelerationView.mpssValue);
        assertEquals("0.0081817585", accelerationView.sgValue);

        accelerationPresenter.afterCmpssTextChanged("8.0235641526359876", 5);
        TestHelper.waitFor(() ->
                        ((accelerationView.fpssValue != null)
                                && (accelerationView.mpssValue != null)
                                && (accelerationView.sgValue != null))
        );

        assertEquals(false, accelerationView.cmpssError);
        assertEquals("0.26324", accelerationView.fpssValue);
        assertEquals("0.08024", accelerationView.mpssValue);
        assertEquals("0.00818", accelerationView.sgValue);

        accelerationView.resetValues();
    }

    @Test
    public void testFeetPerSecondSquaredConversion() throws Exception {
        accelerationPresenter.afterFpssTextChanged("2.5064318986478525", 10);
        TestHelper.waitFor(() ->
                        ((accelerationView.cmpssValue != null)
                                && (accelerationView.mpssValue != null)
                                && (accelerationView.sgValue != null))
        );

        assertEquals(false, accelerationView.fpssError);
        assertEquals("76.3960442711", accelerationView.cmpssValue);
        assertEquals("0.7639604427", accelerationView.mpssValue);
        assertEquals("0.077902285", accelerationView.sgValue);

        accelerationPresenter.afterFpssTextChanged("2.5064318986478525", 5);
        TestHelper.waitFor(() ->
                        ((accelerationView.cmpssValue != null)
                                && (accelerationView.mpssValue != null)
                                && (accelerationView.sgValue != null))
        );

        assertEquals(false, accelerationView.fpssError);
        assertEquals("76.39604", accelerationView.cmpssValue);
        assertEquals("0.76396", accelerationView.mpssValue);
        assertEquals("0.0779", accelerationView.sgValue);

        accelerationView.resetValues();
    }

    @Test
    public void testMetersPerSecondSquaredConversion() throws Exception {
        accelerationPresenter.afterMpssTextChanged("1.568946531647201", 10);
        TestHelper.waitFor(() ->
                        ((accelerationView.cmpssValue != null)
                                && (accelerationView.fpssValue != null)
                                && (accelerationView.sgValue != null))
        );

        assertEquals(false, accelerationView.mpssError);
        assertEquals("156.8946531647", accelerationView.cmpssValue);
        assertEquals("5.1474623742", accelerationView.fpssValue);
        assertEquals("0.1599880216", accelerationView.sgValue);

        accelerationPresenter.afterMpssTextChanged("1.568946531647201", 5);
        TestHelper.waitFor(() ->
                        ((accelerationView.cmpssValue != null)
                                && (accelerationView.fpssValue != null)
                                && (accelerationView.sgValue != null))
        );

        assertEquals(false, accelerationView.mpssError);
        assertEquals("156.89465", accelerationView.cmpssValue);
        assertEquals("5.14746", accelerationView.fpssValue);
        assertEquals("0.15999", accelerationView.sgValue);

        accelerationView.resetValues();
    }

    @Test
    public void testStandardGravityConversion() throws Exception {
        accelerationPresenter.afterSgTextChanged("0.564859764825136", 10);
        TestHelper.waitFor(() ->
                        ((accelerationView.cmpssValue != null)
                                && (accelerationView.fpssValue != null)
                                && (accelerationView.mpssValue != null))
        );

        assertEquals(false, accelerationView.sgError);
        assertEquals("553.9382012603", accelerationView.cmpssValue);
        assertEquals("18.1738255006", accelerationView.fpssValue);
        assertEquals("5.5393820126", accelerationView.mpssValue);

        accelerationPresenter.afterSgTextChanged("0.564859764825136", 5);
        TestHelper.waitFor(() ->
                        ((accelerationView.cmpssValue != null)
                                && (accelerationView.fpssValue != null)
                                && (accelerationView.mpssValue != null))
        );

        assertEquals(false, accelerationView.sgError);
        assertEquals("553.9382", accelerationView.cmpssValue);
        assertEquals("18.17383", accelerationView.fpssValue);
        assertEquals("5.53938", accelerationView.mpssValue);

        accelerationView.resetValues();
    }
}
