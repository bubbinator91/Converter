package com.bubbinator91.converter.tests.main;

import com.bubbinator91.converter.mock.views.MockLengthView;
import com.bubbinator91.converter.interfaces.presenter.ILengthPresenter;
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
    MockLengthView lengthView;
    @Inject
    ILengthPresenter lengthPresenter;

    @Before
    public void setUp() throws Exception {
        TestHelper.getTestClassInjector()
                .inject(this);

        assertNotNull(lengthView);
        assertNotNull(lengthPresenter);
    }

    @Test
    public void testInchesConversion() throws Exception {
        lengthPresenter.getConversionFromInches("1.258633885023369824", 10);
        TestHelper.waitFor(() -> ((lengthView.footValue != null)
                && (lengthView.yardValue != null)
                && (lengthView.mileValue != null)
                && (lengthView.millimeterValue != null)
                && (lengthView.centimeterValue != null)
                && (lengthView.meterValue != null)
                && (lengthView.kilometerValue != null)));

        assertEquals(false, lengthView.inchError);
        assertEquals("0.1048861571", lengthView.footValue);
        assertEquals("0.0349620524", lengthView.yardValue);
        assertEquals("0.0000198648", lengthView.mileValue);
        assertEquals("31.9693006796", lengthView.millimeterValue);
        assertEquals("3.196930068", lengthView.centimeterValue);
        assertEquals("0.0319693007", lengthView.meterValue);
        assertEquals("0.0000319693", lengthView.kilometerValue);

        lengthPresenter.getConversionFromInches("1.258633885023369824", 5);
        TestHelper.waitFor(() -> ((lengthView.footValue != null)
                && (lengthView.yardValue != null)
                && (lengthView.mileValue != null)
                && (lengthView.millimeterValue != null)
                && (lengthView.centimeterValue != null)
                && (lengthView.meterValue != null)
                && (lengthView.kilometerValue != null)));

        assertEquals(false, lengthView.inchError);
        assertEquals("0.10489", lengthView.footValue);
        assertEquals("0.03496", lengthView.yardValue);
        assertEquals("0.00002", lengthView.mileValue);
        assertEquals("31.9693", lengthView.millimeterValue);
        assertEquals("3.19693", lengthView.centimeterValue);
        assertEquals("0.03197", lengthView.meterValue);
        assertEquals("0.00003", lengthView.kilometerValue);

        lengthView.resetValues();
    }

    @Test
    public void testFeetConversion() throws Exception {
        lengthPresenter.getConversionFromFeet("10.694673465926534", 10);
        TestHelper.waitFor(() -> ((lengthView.inchValue != null)
                && (lengthView.yardValue != null)
                && (lengthView.mileValue != null)
                && (lengthView.millimeterValue != null)
                && (lengthView.centimeterValue != null)
                && (lengthView.meterValue != null)
                && (lengthView.kilometerValue != null)));

        assertEquals(false, lengthView.footError);
        assertEquals("128.3360815911", lengthView.inchValue);
        assertEquals("3.5648911553", lengthView.yardValue);
        assertEquals("0.0020255063", lengthView.mileValue);
        assertEquals("3259.7364724144", lengthView.millimeterValue);
        assertEquals("325.9736472414", lengthView.centimeterValue);
        assertEquals("3.2597364724", lengthView.meterValue);
        assertEquals("0.0032597365", lengthView.kilometerValue);

        lengthPresenter.getConversionFromFeet("10.694673465926534", 5);
        TestHelper.waitFor(() -> ((lengthView.inchValue != null)
                && (lengthView.yardValue != null)
                && (lengthView.mileValue != null)
                && (lengthView.millimeterValue != null)
                && (lengthView.centimeterValue != null)
                && (lengthView.meterValue != null)
                && (lengthView.kilometerValue != null)));

        assertEquals(false, lengthView.footError);
        assertEquals("128.33608", lengthView.inchValue);
        assertEquals("3.56489", lengthView.yardValue);
        assertEquals("0.00203", lengthView.mileValue);
        assertEquals("3259.73647", lengthView.millimeterValue);
        assertEquals("325.97365", lengthView.centimeterValue);
        assertEquals("3.25974", lengthView.meterValue);
        assertEquals("0.00326", lengthView.kilometerValue);

        lengthView.resetValues();
    }

    @Test
    public void testYardsConversion() throws Exception {
        lengthPresenter.getConversionFromYards("94.677446", 10);
        TestHelper.waitFor(() -> ((lengthView.inchValue != null)
                && (lengthView.footValue != null)
                && (lengthView.mileValue != null)
                && (lengthView.millimeterValue != null)
                && (lengthView.centimeterValue != null)
                && (lengthView.meterValue != null)
                && (lengthView.kilometerValue != null)));

        assertEquals(false, lengthView.yardError);
        assertEquals("3408.388056", lengthView.inchValue);
        assertEquals("284.032338", lengthView.footValue);
        assertEquals("0.0537940034", lengthView.mileValue);
        assertEquals("86573.0566224", lengthView.millimeterValue);
        assertEquals("8657.30566224", lengthView.centimeterValue);
        assertEquals("86.5730566224", lengthView.meterValue);
        assertEquals("0.0865730566", lengthView.kilometerValue);

        lengthPresenter.getConversionFromYards("94.677446", 5);
        TestHelper.waitFor(() -> ((lengthView.inchValue != null)
                && (lengthView.footValue != null)
                && (lengthView.mileValue != null)
                && (lengthView.millimeterValue != null)
                && (lengthView.centimeterValue != null)
                && (lengthView.meterValue != null)
                && (lengthView.kilometerValue != null)));

        assertEquals(false, lengthView.yardError);
        assertEquals("3408.38806", lengthView.inchValue);
        assertEquals("284.03234", lengthView.footValue);
        assertEquals("0.05379", lengthView.mileValue);
        assertEquals("86573.05662", lengthView.millimeterValue);
        assertEquals("8657.30566", lengthView.centimeterValue);
        assertEquals("86.57306", lengthView.meterValue);
        assertEquals("0.08657", lengthView.kilometerValue);

        lengthView.resetValues();
    }

    @Test
    public void testMilesConversion() throws Exception {
        lengthPresenter.getConversionFromMiles("8.569852369523695441288", 10);
        TestHelper.waitFor(() -> ((lengthView.inchValue != null)
                && (lengthView.footValue != null)
                && (lengthView.yardValue != null)
                && (lengthView.millimeterValue != null)
                && (lengthView.centimeterValue != null)
                && (lengthView.meterValue != null)
                && (lengthView.kilometerValue != null)));

        assertEquals(false, lengthView.mileError);
        assertEquals("542985.8461330213", lengthView.inchValue);
        assertEquals("45248.8205110851", lengthView.footValue);
        assertEquals("15082.9401703617", lengthView.yardValue);
        assertEquals("13791840.4917787421", lengthView.millimeterValue);
        assertEquals("1379184.0491778742", lengthView.centimeterValue);
        assertEquals("13791.8404917787", lengthView.meterValue);
        assertEquals("13.7918404918", lengthView.kilometerValue);

        lengthPresenter.getConversionFromMiles("8.569852369523695441288", 5);
        TestHelper.waitFor(() -> ((lengthView.inchValue != null)
                && (lengthView.footValue != null)
                && (lengthView.yardValue != null)
                && (lengthView.millimeterValue != null)
                && (lengthView.centimeterValue != null)
                && (lengthView.meterValue != null)
                && (lengthView.kilometerValue != null)));

        assertEquals(false, lengthView.mileError);
        assertEquals("542985.84613", lengthView.inchValue);
        assertEquals("45248.82051", lengthView.footValue);
        assertEquals("15082.94017", lengthView.yardValue);
        assertEquals("13791840.49178", lengthView.millimeterValue);
        assertEquals("1379184.04918", lengthView.centimeterValue);
        assertEquals("13791.84049", lengthView.meterValue);
        assertEquals("13.79184", lengthView.kilometerValue);

        lengthView.resetValues();
    }

    @Test
    public void testMillimetersConversion() throws Exception {
        lengthPresenter.getConversionFromMillimeters("58.85236982258084", 10);
        TestHelper.waitFor(() -> ((lengthView.inchValue != null)
                && (lengthView.footValue != null)
                && (lengthView.yardValue != null)
                && (lengthView.mileValue != null)
                && (lengthView.centimeterValue != null)
                && (lengthView.meterValue != null)
                && (lengthView.kilometerValue != null)));

        assertEquals(false, lengthView.millimeterError);
        assertEquals("2.317022434", lengthView.inchValue);
        assertEquals("0.1930852028", lengthView.footValue);
        assertEquals("0.0643617343", lengthView.yardValue);
        assertEquals("0.0000365692", lengthView.mileValue);
        assertEquals("5.8852369823", lengthView.centimeterValue);
        assertEquals("0.0588523698", lengthView.meterValue);
        assertEquals("0.0000588524", lengthView.kilometerValue);

        lengthPresenter.getConversionFromMillimeters("58.85236982258084", 5);
        TestHelper.waitFor(() -> ((lengthView.inchValue != null)
                && (lengthView.footValue != null)
                && (lengthView.yardValue != null)
                && (lengthView.mileValue != null)
                && (lengthView.centimeterValue != null)
                && (lengthView.meterValue != null)
                && (lengthView.kilometerValue != null)));

        assertEquals(false, lengthView.millimeterError);
        assertEquals("2.31702", lengthView.inchValue);
        assertEquals("0.19309", lengthView.footValue);
        assertEquals("0.06436", lengthView.yardValue);
        assertEquals("0.00004", lengthView.mileValue);
        assertEquals("5.88524", lengthView.centimeterValue);
        assertEquals("0.05885", lengthView.meterValue);
        assertEquals("0.00006", lengthView.kilometerValue);

        lengthView.resetValues();
    }

    @Test
    public void testCentimetersConversion() throws Exception {
        lengthPresenter.getConversionFromCentimeters("56943.98653164976", 10);
        TestHelper.waitFor(() -> ((lengthView.inchValue != null)
                && (lengthView.footValue != null)
                && (lengthView.yardValue != null)
                && (lengthView.mileValue != null)
                && (lengthView.millimeterValue != null)
                && (lengthView.meterValue != null)
                && (lengthView.kilometerValue != null)));

        assertEquals(false, lengthView.centimeterError);
        assertEquals("22418.8923352952", lengthView.inchValue);
        assertEquals("1868.2410279413", lengthView.footValue);
        assertEquals("622.7470093138", lengthView.yardValue);
        assertEquals("0.353833528", lengthView.mileValue);
        assertEquals("569439.8653164976", lengthView.millimeterValue);
        assertEquals("569.4398653165", lengthView.meterValue);
        assertEquals("0.5694398653", lengthView.kilometerValue);

        lengthPresenter.getConversionFromCentimeters("56943.98653164976", 5);
        TestHelper.waitFor(() -> ((lengthView.inchValue != null)
                && (lengthView.footValue != null)
                && (lengthView.yardValue != null)
                && (lengthView.mileValue != null)
                && (lengthView.millimeterValue != null)
                && (lengthView.meterValue != null)
                && (lengthView.kilometerValue != null)));

        assertEquals(false, lengthView.centimeterError);
        assertEquals("22418.89234", lengthView.inchValue);
        assertEquals("1868.24103", lengthView.footValue);
        assertEquals("622.74701", lengthView.yardValue);
        assertEquals("0.35383", lengthView.mileValue);
        assertEquals("569439.86532", lengthView.millimeterValue);
        assertEquals("569.43987", lengthView.meterValue);
        assertEquals("0.56944", lengthView.kilometerValue);

        lengthView.resetValues();
    }

    @Test
    public void testMetersConversion() throws Exception {
        lengthPresenter.getConversionFromMeters("824.3642518597643169", 10);
        TestHelper.waitFor(() -> ((lengthView.inchValue != null)
                && (lengthView.footValue != null)
                && (lengthView.yardValue != null)
                && (lengthView.mileValue != null)
                && (lengthView.millimeterValue != null)
                && (lengthView.centimeterValue != null)
                && (lengthView.kilometerValue != null)));

        assertEquals(false, lengthView.meterError);
        assertEquals("32455.2855062899", lengthView.inchValue);
        assertEquals("2704.6071255242", lengthView.footValue);
        assertEquals("901.5357085081", lengthView.yardValue);
        assertEquals("0.512236198", lengthView.mileValue);
        assertEquals("824364.2518597643", lengthView.millimeterValue);
        assertEquals("82436.4251859764", lengthView.centimeterValue);
        assertEquals("0.8243642519", lengthView.kilometerValue);

        lengthPresenter.getConversionFromMeters("824.3642518597643169", 5);
        TestHelper.waitFor(() -> ((lengthView.inchValue != null)
                && (lengthView.footValue != null)
                && (lengthView.yardValue != null)
                && (lengthView.mileValue != null)
                && (lengthView.millimeterValue != null)
                && (lengthView.centimeterValue != null)
                && (lengthView.kilometerValue != null)));

        assertEquals(false, lengthView.meterError);
        assertEquals("32455.28551", lengthView.inchValue);
        assertEquals("2704.60713", lengthView.footValue);
        assertEquals("901.53571", lengthView.yardValue);
        assertEquals("0.51224", lengthView.mileValue);
        assertEquals("824364.25186", lengthView.millimeterValue);
        assertEquals("82436.42519", lengthView.centimeterValue);
        assertEquals("0.82436", lengthView.kilometerValue);

        lengthView.resetValues();
    }

    @Test
    public void testKilometersConversion() throws Exception {
        lengthPresenter.getConversionFromKilometers("8.5649764312526591", 10);
        TestHelper.waitFor(() -> ((lengthView.inchValue != null)
                && (lengthView.footValue != null)
                && (lengthView.yardValue != null)
                && (lengthView.mileValue != null)
                && (lengthView.millimeterValue != null)
                && (lengthView.centimeterValue != null)
                && (lengthView.meterValue != null)));

        assertEquals(false, lengthView.kilometerError);
        assertEquals("337203.7965060102", lengthView.inchValue);
        assertEquals("28100.3163755009", lengthView.footValue);
        assertEquals("9366.772125167", lengthView.yardValue);
        assertEquals("5.3220296166", lengthView.mileValue);
        assertEquals("8564976.4312526591", lengthView.millimeterValue);
        assertEquals("856497.6431252659", lengthView.centimeterValue);
        assertEquals("8564.9764312527", lengthView.meterValue);

        lengthPresenter.getConversionFromKilometers("8.5649764312526591", 5);
        TestHelper.waitFor(() -> ((lengthView.inchValue != null)
                && (lengthView.footValue != null)
                && (lengthView.yardValue != null)
                && (lengthView.mileValue != null)
                && (lengthView.millimeterValue != null)
                && (lengthView.centimeterValue != null)
                && (lengthView.meterValue != null)));

        assertEquals(false, lengthView.kilometerError);
        assertEquals("337203.79651", lengthView.inchValue);
        assertEquals("28100.31638", lengthView.footValue);
        assertEquals("9366.77213", lengthView.yardValue);
        assertEquals("5.32203", lengthView.mileValue);
        assertEquals("8564976.43125", lengthView.millimeterValue);
        assertEquals("856497.64313", lengthView.centimeterValue);
        assertEquals("8564.97643", lengthView.meterValue);

        lengthView.resetValues();
    }
}
