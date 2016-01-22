package com.bubbinator91.converter.tests;

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
    MockAccelerationView mAccelerationView;
    @Inject
    IAccelerationPresenter mAccelerationPresenter;

    @Before
    public void setUp() throws Exception {
        TestHelper.getTestClassInjector()
                .inject(this);

        assertNotNull(mAccelerationView);
        assertNotNull(mAccelerationPresenter);
    }

    @Test
    public void testCentimetersPerSecondSquaredConversion() throws Exception {
        mAccelerationPresenter
                .getConversionFromCentimetersPerSecondSquared("8.0235641526359876", 10);
        TestHelper.waitFor(() ->
                        ((mAccelerationView.mFpssValue != null)
                                && (mAccelerationView.mMpssValue != null)
                                && (mAccelerationView.mSgValue != null))
        );

        assertEquals(false, mAccelerationView.mCmpssError);
        assertEquals("0.2632402937", mAccelerationView.mFpssValue);
        assertEquals("0.0802356415", mAccelerationView.mMpssValue);
        assertEquals("0.0081817585", mAccelerationView.mSgValue);

        mAccelerationPresenter
                .getConversionFromCentimetersPerSecondSquared("8.0235641526359876", 5);
        TestHelper.waitFor(() ->
                        ((mAccelerationView.mFpssValue != null)
                                && (mAccelerationView.mMpssValue != null)
                                && (mAccelerationView.mSgValue != null))
        );

        assertEquals(false, mAccelerationView.mCmpssError);
        assertEquals("0.26324", mAccelerationView.mFpssValue);
        assertEquals("0.08024", mAccelerationView.mMpssValue);
        assertEquals("0.00818", mAccelerationView.mSgValue);

        mAccelerationView.resetValues();
    }

    @Test
    public void testFeetPerSecondSquaredConversion() throws Exception {
        mAccelerationPresenter
                .getConversionFromFeetPerSecondSquared("2.5064318986478525", 10);
        TestHelper.waitFor(() ->
                        ((mAccelerationView.mCmpssValue != null)
                                && (mAccelerationView.mMpssValue != null)
                                && (mAccelerationView.mSgValue != null))
        );

        assertEquals(false, mAccelerationView.mFpssError);
        assertEquals("76.3960442711", mAccelerationView.mCmpssValue);
        assertEquals("0.7639604427", mAccelerationView.mMpssValue);
        assertEquals("0.077902285", mAccelerationView.mSgValue);

        mAccelerationPresenter
                .getConversionFromFeetPerSecondSquared("2.5064318986478525", 5);
        TestHelper.waitFor(() ->
                        ((mAccelerationView.mCmpssValue != null)
                                && (mAccelerationView.mMpssValue != null)
                                && (mAccelerationView.mSgValue != null))
        );

        assertEquals(false, mAccelerationView.mFpssError);
        assertEquals("76.39604", mAccelerationView.mCmpssValue);
        assertEquals("0.76396", mAccelerationView.mMpssValue);
        assertEquals("0.0779", mAccelerationView.mSgValue);

        mAccelerationView.resetValues();
    }

    @Test
    public void testMetersPerSecondSquaredConversion() throws Exception {
        mAccelerationPresenter
                .getConversionFromMetersPerSecondSquared("1.568946531647201", 10);
        TestHelper.waitFor(() ->
                        ((mAccelerationView.mCmpssValue != null)
                                && (mAccelerationView.mFpssValue != null)
                                && (mAccelerationView.mSgValue != null))
        );

        assertEquals(false, mAccelerationView.mMpssError);
        assertEquals("156.8946531647", mAccelerationView.mCmpssValue);
        assertEquals("5.1474623742", mAccelerationView.mFpssValue);
        assertEquals("0.1599880216", mAccelerationView.mSgValue);

        mAccelerationPresenter
                .getConversionFromMetersPerSecondSquared("1.568946531647201", 5);
        TestHelper.waitFor(() ->
                        ((mAccelerationView.mCmpssValue != null)
                                && (mAccelerationView.mFpssValue != null)
                                && (mAccelerationView.mSgValue != null))
        );

        assertEquals(false, mAccelerationView.mMpssError);
        assertEquals("156.89465", mAccelerationView.mCmpssValue);
        assertEquals("5.14746", mAccelerationView.mFpssValue);
        assertEquals("0.15999", mAccelerationView.mSgValue);

        mAccelerationView.resetValues();
    }

    @Test
    public void testStandardGravityConversion() throws Exception {
        mAccelerationPresenter
                .getConversionFromStandardGravity("0.564859764825136", 10);
        TestHelper.waitFor(() ->
                        ((mAccelerationView.mCmpssValue != null)
                                && (mAccelerationView.mFpssValue != null)
                                && (mAccelerationView.mMpssValue != null))
        );

        assertEquals(false, mAccelerationView.mSgError);
        assertEquals("553.9382012603", mAccelerationView.mCmpssValue);
        assertEquals("18.1738255006", mAccelerationView.mFpssValue);
        assertEquals("5.5393820126", mAccelerationView.mMpssValue);

        mAccelerationPresenter
                .getConversionFromStandardGravity("0.564859764825136", 5);
        TestHelper.waitFor(() ->
                        ((mAccelerationView.mCmpssValue != null)
                                && (mAccelerationView.mFpssValue != null)
                                && (mAccelerationView.mMpssValue != null))
        );

        assertEquals(false, mAccelerationView.mSgError);
        assertEquals("553.9382", mAccelerationView.mCmpssValue);
        assertEquals("18.17383", mAccelerationView.mFpssValue);
        assertEquals("5.53938", mAccelerationView.mMpssValue);

        mAccelerationView.resetValues();
    }
}
