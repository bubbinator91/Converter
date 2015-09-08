package com.bubbinator91.converter.tests;

import com.bubbinator91.converter.mock.views.MockLengthView;
import com.bubbinator91.converter.ui.interfaces.length.ILengthPresenter;
import com.bubbinator91.converter.util.TestHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * A collection of JUnit tests that test the length presenter's interaction with a mock of the
 * length view.
 */
@RunWith(JUnit4.class)
public class LengthTest {

    @Inject
    MockLengthView mLengthView;
    @Inject
    ILengthPresenter mLengthPresenter;

    @Before
    public void setUp() throws Exception {
        TestHelper.getTestClassInjector()
                .inject(this);

        assertNotNull(mLengthView);
        assertNotNull(mLengthPresenter);
    }

    @Test
    public void testInchesConversion() throws Exception {
        mLengthPresenter.getConversionFromInchesResults("1.258633885023369824", 10);
        TestHelper.waitFor(() -> ((mLengthView.mFootValue != null)
                && (mLengthView.mYardValue != null)
                && (mLengthView.mMileValue != null)
                && (mLengthView.mMillimeterValue != null)
                && (mLengthView.mCentimeterValue != null)
                && (mLengthView.mMeterValue != null)
                && (mLengthView.mKilometerValue != null)));

        assertEquals(0, mLengthView.mInchError);
        assertEquals("0.1048861571", mLengthView.mFootValue);
        assertEquals("0.0349620524", mLengthView.mYardValue);
        assertEquals("0.0000198648", mLengthView.mMileValue);
        assertEquals("31.9693006796", mLengthView.mMillimeterValue);
        assertEquals("3.196930068", mLengthView.mCentimeterValue);
        assertEquals("0.0319693007", mLengthView.mMeterValue);
        assertEquals("0.0000319693", mLengthView.mKilometerValue);

        mLengthPresenter.getConversionFromInchesResults("1.258633885023369824", 5);
        TestHelper.waitFor(() -> ((mLengthView.mFootValue != null)
                && (mLengthView.mYardValue != null)
                && (mLengthView.mMileValue != null)
                && (mLengthView.mMillimeterValue != null)
                && (mLengthView.mCentimeterValue != null)
                && (mLengthView.mMeterValue != null)
                && (mLengthView.mKilometerValue != null)));

        assertEquals(0, mLengthView.mInchError);
        assertEquals("0.10489", mLengthView.mFootValue);
        assertEquals("0.03496", mLengthView.mYardValue);
        assertEquals("0.00002", mLengthView.mMileValue);
        assertEquals("31.9693", mLengthView.mMillimeterValue);
        assertEquals("3.19693", mLengthView.mCentimeterValue);
        assertEquals("0.03197", mLengthView.mMeterValue);
        assertEquals("0.00003", mLengthView.mKilometerValue);

        mLengthView.resetValues();
    }

    @Test
    public void testFeetConversion() throws Exception {
        mLengthPresenter.getConversionFromFeetResults("10.694673465926534", 10);
        TestHelper.waitFor(() -> ((mLengthView.mInchValue != null)
                && (mLengthView.mYardValue != null)
                && (mLengthView.mMileValue != null)
                && (mLengthView.mMillimeterValue != null)
                && (mLengthView.mCentimeterValue != null)
                && (mLengthView.mMeterValue != null)
                && (mLengthView.mKilometerValue != null)));

        assertEquals(0, mLengthView.mFootError);
        assertEquals("128.3360815911", mLengthView.mInchValue);
        assertEquals("3.5648911553", mLengthView.mYardValue);
        assertEquals("0.0020255063", mLengthView.mMileValue);
        assertEquals("3259.7364724144", mLengthView.mMillimeterValue);
        assertEquals("325.9736472414", mLengthView.mCentimeterValue);
        assertEquals("3.2597364724", mLengthView.mMeterValue);
        assertEquals("0.0032597365", mLengthView.mKilometerValue);

        mLengthPresenter.getConversionFromFeetResults("10.694673465926534", 5);
        TestHelper.waitFor(() -> ((mLengthView.mInchValue != null)
                && (mLengthView.mYardValue != null)
                && (mLengthView.mMileValue != null)
                && (mLengthView.mMillimeterValue != null)
                && (mLengthView.mCentimeterValue != null)
                && (mLengthView.mMeterValue != null)
                && (mLengthView.mKilometerValue != null)));

        assertEquals(0, mLengthView.mFootError);
        assertEquals("128.33608", mLengthView.mInchValue);
        assertEquals("3.56489", mLengthView.mYardValue);
        assertEquals("0.00203", mLengthView.mMileValue);
        assertEquals("3259.73647", mLengthView.mMillimeterValue);
        assertEquals("325.97365", mLengthView.mCentimeterValue);
        assertEquals("3.25974", mLengthView.mMeterValue);
        assertEquals("0.00326", mLengthView.mKilometerValue);

        mLengthView.resetValues();
    }

    @Test
    public void testYardsConversion() throws Exception {
        mLengthPresenter.getConversionFromYardsResults("94.677446", 10);
        TestHelper.waitFor(() -> ((mLengthView.mInchValue != null)
                && (mLengthView.mFootValue != null)
                && (mLengthView.mMileValue != null)
                && (mLengthView.mMillimeterValue != null)
                && (mLengthView.mCentimeterValue != null)
                && (mLengthView.mMeterValue != null)
                && (mLengthView.mKilometerValue != null)));

        assertEquals(0, mLengthView.mYardError);
        assertEquals("3408.388056", mLengthView.mInchValue);
        assertEquals("284.032338", mLengthView.mFootValue);
        assertEquals("0.0537940034", mLengthView.mMileValue);
        assertEquals("86573.0566224", mLengthView.mMillimeterValue);
        assertEquals("8657.30566224", mLengthView.mCentimeterValue);
        assertEquals("86.5730566224", mLengthView.mMeterValue);
        assertEquals("0.0865730566", mLengthView.mKilometerValue);

        mLengthPresenter.getConversionFromYardsResults("94.677446", 5);
        TestHelper.waitFor(() -> ((mLengthView.mInchValue != null)
                && (mLengthView.mFootValue != null)
                && (mLengthView.mMileValue != null)
                && (mLengthView.mMillimeterValue != null)
                && (mLengthView.mCentimeterValue != null)
                && (mLengthView.mMeterValue != null)
                && (mLengthView.mKilometerValue != null)));

        assertEquals(0, mLengthView.mYardError);
        assertEquals("3408.38806", mLengthView.mInchValue);
        assertEquals("284.03234", mLengthView.mFootValue);
        assertEquals("0.05379", mLengthView.mMileValue);
        assertEquals("86573.05662", mLengthView.mMillimeterValue);
        assertEquals("8657.30566", mLengthView.mCentimeterValue);
        assertEquals("86.57306", mLengthView.mMeterValue);
        assertEquals("0.08657", mLengthView.mKilometerValue);

        mLengthView.resetValues();
    }

    @Test
    public void testMilesConversion() throws Exception {
        mLengthPresenter.getConversionFromMilesResults("8.569852369523695441288", 10);
        TestHelper.waitFor(() -> ((mLengthView.mInchValue != null)
                && (mLengthView.mFootValue != null)
                && (mLengthView.mYardValue != null)
                && (mLengthView.mMillimeterValue != null)
                && (mLengthView.mCentimeterValue != null)
                && (mLengthView.mMeterValue != null)
                && (mLengthView.mKilometerValue != null)));

        assertEquals(0, mLengthView.mMileError);
        assertEquals("542985.8461330213", mLengthView.mInchValue);
        assertEquals("45248.8205110851", mLengthView.mFootValue);
        assertEquals("15082.9401703617", mLengthView.mYardValue);
        assertEquals("13791840.4917787421", mLengthView.mMillimeterValue);
        assertEquals("1379184.0491778742", mLengthView.mCentimeterValue);
        assertEquals("13791.8404917787", mLengthView.mMeterValue);
        assertEquals("13.7918404918", mLengthView.mKilometerValue);

        mLengthPresenter.getConversionFromMilesResults("8.569852369523695441288", 5);
        TestHelper.waitFor(() -> ((mLengthView.mInchValue != null)
                && (mLengthView.mFootValue != null)
                && (mLengthView.mYardValue != null)
                && (mLengthView.mMillimeterValue != null)
                && (mLengthView.mCentimeterValue != null)
                && (mLengthView.mMeterValue != null)
                && (mLengthView.mKilometerValue != null)));

        assertEquals(0, mLengthView.mMileError);
        assertEquals("542985.84613", mLengthView.mInchValue);
        assertEquals("45248.82051", mLengthView.mFootValue);
        assertEquals("15082.94017", mLengthView.mYardValue);
        assertEquals("13791840.49178", mLengthView.mMillimeterValue);
        assertEquals("1379184.04918", mLengthView.mCentimeterValue);
        assertEquals("13791.84049", mLengthView.mMeterValue);
        assertEquals("13.79184", mLengthView.mKilometerValue);

        mLengthView.resetValues();
    }

    @Test
    public void testMillimetersConversion() throws Exception {
        mLengthPresenter.getConversionFromMillimetersResults("58.85236982258084", 10);
        TestHelper.waitFor(() -> ((mLengthView.mInchValue != null)
                && (mLengthView.mFootValue != null)
                && (mLengthView.mYardValue != null)
                && (mLengthView.mMileValue != null)
                && (mLengthView.mCentimeterValue != null)
                && (mLengthView.mMeterValue != null)
                && (mLengthView.mKilometerValue != null)));

        assertEquals(0, mLengthView.mMillimeterError);
        assertEquals("2.317022434", mLengthView.mInchValue);
        assertEquals("0.1930852028", mLengthView.mFootValue);
        assertEquals("0.0643617343", mLengthView.mYardValue);
        assertEquals("0.0000365692", mLengthView.mMileValue);
        assertEquals("5.8852369823", mLengthView.mCentimeterValue);
        assertEquals("0.0588523698", mLengthView.mMeterValue);
        assertEquals("0.0000588524", mLengthView.mKilometerValue);

        mLengthPresenter.getConversionFromMillimetersResults("58.85236982258084", 5);
        TestHelper.waitFor(() -> ((mLengthView.mInchValue != null)
                && (mLengthView.mFootValue != null)
                && (mLengthView.mYardValue != null)
                && (mLengthView.mMileValue != null)
                && (mLengthView.mCentimeterValue != null)
                && (mLengthView.mMeterValue != null)
                && (mLengthView.mKilometerValue != null)));

        assertEquals(0, mLengthView.mMillimeterError);
        assertEquals("2.31702", mLengthView.mInchValue);
        assertEquals("0.19309", mLengthView.mFootValue);
        assertEquals("0.06436", mLengthView.mYardValue);
        assertEquals("0.00004", mLengthView.mMileValue);
        assertEquals("5.88524", mLengthView.mCentimeterValue);
        assertEquals("0.05885", mLengthView.mMeterValue);
        assertEquals("0.00006", mLengthView.mKilometerValue);

        mLengthView.resetValues();
    }

    @Test
    public void testCentimetersConversion() throws Exception {
        mLengthPresenter.getConversionFromCentimetersResults("56943.98653164976", 10);
        TestHelper.waitFor(() -> ((mLengthView.mInchValue != null)
                && (mLengthView.mFootValue != null)
                && (mLengthView.mYardValue != null)
                && (mLengthView.mMileValue != null)
                && (mLengthView.mMillimeterValue != null)
                && (mLengthView.mMeterValue != null)
                && (mLengthView.mKilometerValue != null)));

        assertEquals(0, mLengthView.mCentimeterError);
        assertEquals("22418.8923352952", mLengthView.mInchValue);
        assertEquals("1868.2410279413", mLengthView.mFootValue);
        assertEquals("622.7470093138", mLengthView.mYardValue);
        assertEquals("0.353833528", mLengthView.mMileValue);
        assertEquals("569439.8653164976", mLengthView.mMillimeterValue);
        assertEquals("569.4398653165", mLengthView.mMeterValue);
        assertEquals("0.5694398653", mLengthView.mKilometerValue);

        mLengthPresenter.getConversionFromCentimetersResults("56943.98653164976", 5);
        TestHelper.waitFor(() -> ((mLengthView.mInchValue != null)
                && (mLengthView.mFootValue != null)
                && (mLengthView.mYardValue != null)
                && (mLengthView.mMileValue != null)
                && (mLengthView.mMillimeterValue != null)
                && (mLengthView.mMeterValue != null)
                && (mLengthView.mKilometerValue != null)));

        assertEquals(0, mLengthView.mCentimeterError);
        assertEquals("22418.89234", mLengthView.mInchValue);
        assertEquals("1868.24103", mLengthView.mFootValue);
        assertEquals("622.74701", mLengthView.mYardValue);
        assertEquals("0.35383", mLengthView.mMileValue);
        assertEquals("569439.86532", mLengthView.mMillimeterValue);
        assertEquals("569.43987", mLengthView.mMeterValue);
        assertEquals("0.56944", mLengthView.mKilometerValue);

        mLengthView.resetValues();
    }

    @Test
    public void testMetersConversion() throws Exception {
        mLengthPresenter.getConversionFromMetersResults("824.3642518597643169", 10);
        TestHelper.waitFor(() -> ((mLengthView.mInchValue != null)
                && (mLengthView.mFootValue != null)
                && (mLengthView.mYardValue != null)
                && (mLengthView.mMileValue != null)
                && (mLengthView.mMillimeterValue != null)
                && (mLengthView.mCentimeterValue != null)
                && (mLengthView.mKilometerValue != null)));

        assertEquals(0, mLengthView.mMeterError);
        assertEquals("32455.2855062899", mLengthView.mInchValue);
        assertEquals("2704.6071255242", mLengthView.mFootValue);
        assertEquals("901.5357085081", mLengthView.mYardValue);
        assertEquals("0.512236198", mLengthView.mMileValue);
        assertEquals("824364.2518597643", mLengthView.mMillimeterValue);
        assertEquals("82436.4251859764", mLengthView.mCentimeterValue);
        assertEquals("0.8243642519", mLengthView.mKilometerValue);

        mLengthPresenter.getConversionFromMetersResults("824.3642518597643169", 5);
        TestHelper.waitFor(() -> ((mLengthView.mInchValue != null)
                && (mLengthView.mFootValue != null)
                && (mLengthView.mYardValue != null)
                && (mLengthView.mMileValue != null)
                && (mLengthView.mMillimeterValue != null)
                && (mLengthView.mCentimeterValue != null)
                && (mLengthView.mKilometerValue != null)));

        assertEquals(0, mLengthView.mMeterError);
        assertEquals("32455.28551", mLengthView.mInchValue);
        assertEquals("2704.60713", mLengthView.mFootValue);
        assertEquals("901.53571", mLengthView.mYardValue);
        assertEquals("0.51224", mLengthView.mMileValue);
        assertEquals("824364.25186", mLengthView.mMillimeterValue);
        assertEquals("82436.42519", mLengthView.mCentimeterValue);
        assertEquals("0.82436", mLengthView.mKilometerValue);

        mLengthView.resetValues();
    }

    @Test
    public void testKilometersConversion() throws Exception {
        mLengthPresenter.getConversionFromKilometersResults("8.5649764312526591", 10);
        TestHelper.waitFor(() -> ((mLengthView.mInchValue != null)
                && (mLengthView.mFootValue != null)
                && (mLengthView.mYardValue != null)
                && (mLengthView.mMileValue != null)
                && (mLengthView.mMillimeterValue != null)
                && (mLengthView.mCentimeterValue != null)
                && (mLengthView.mMeterValue != null)));

        assertEquals(0, mLengthView.mKilometerError);
        assertEquals("337203.7965060102", mLengthView.mInchValue);
        assertEquals("28100.3163755009", mLengthView.mFootValue);
        assertEquals("9366.772125167", mLengthView.mYardValue);
        assertEquals("5.3220296166", mLengthView.mMileValue);
        assertEquals("8564976.4312526591", mLengthView.mMillimeterValue);
        assertEquals("856497.6431252659", mLengthView.mCentimeterValue);
        assertEquals("8564.9764312527", mLengthView.mMeterValue);

        mLengthPresenter.getConversionFromKilometersResults("8.5649764312526591", 5);
        TestHelper.waitFor(() -> ((mLengthView.mInchValue != null)
                && (mLengthView.mFootValue != null)
                && (mLengthView.mYardValue != null)
                && (mLengthView.mMileValue != null)
                && (mLengthView.mMillimeterValue != null)
                && (mLengthView.mCentimeterValue != null)
                && (mLengthView.mMeterValue != null)));

        assertEquals(0, mLengthView.mKilometerError);
        assertEquals("337203.79651", mLengthView.mInchValue);
        assertEquals("28100.31638", mLengthView.mFootValue);
        assertEquals("9366.77213", mLengthView.mYardValue);
        assertEquals("5.32203", mLengthView.mMileValue);
        assertEquals("8564976.43125", mLengthView.mMillimeterValue);
        assertEquals("856497.64313", mLengthView.mCentimeterValue);
        assertEquals("8564.97643", mLengthView.mMeterValue);

        mLengthView.resetValues();
    }
}
