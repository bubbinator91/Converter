package com.bubbinator91.converter.tests;

import com.bubbinator91.converter.mock.views.MockDataTransferSpeedView;
import com.bubbinator91.converter.ui.interfaces.datatransferspeed.IDataTransferSpeedPresenter;
import com.bubbinator91.converter.util.TestHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * A collection of JUnit tests that test the DTS presenter's interaction with a mock of the DTS view.
 */
@RunWith(JUnit4.class)
public class DataTransferSpeedTest {

    @Inject
    MockDataTransferSpeedView mDataTransferSpeedView;
    @Inject
    IDataTransferSpeedPresenter mDataTransferSpeedPresenter;

    @Before
    public void setUp() throws Exception {
        TestHelper.getTestClassInjector()
                .inject(this);

        assertNotNull(mDataTransferSpeedView);
        assertNotNull(mDataTransferSpeedPresenter);
    }

    @Test
    public void testBitsPerSecondConversion() throws Exception {
        mDataTransferSpeedPresenter
                .getConversionFromBitsPerSecondResults("8000000000.9764326598653", 10);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBypsValue != null)
                && (mDataTransferSpeedView.mKbpsValue != null)
                && (mDataTransferSpeedView.mKbypsValue != null)
                && (mDataTransferSpeedView.mMbpsValue != null)
                && (mDataTransferSpeedView.mMbypsValue != null)
                && (mDataTransferSpeedView.mGbpsValue != null)
                && (mDataTransferSpeedView.mGbypsValue != null)
                && (mDataTransferSpeedView.mTbpsValue != null)
                && (mDataTransferSpeedView.mTbypsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mBpsError);
        assertEquals("1000000000.1220540825", mDataTransferSpeedView.mBypsValue);
        assertEquals("8000000.0009764327", mDataTransferSpeedView.mKbpsValue);
        assertEquals("1000000.0001220541", mDataTransferSpeedView.mKbypsValue);
        assertEquals("8000.0000009764", mDataTransferSpeedView.mMbpsValue);
        assertEquals("1000.0000001221", mDataTransferSpeedView.mMbypsValue);
        assertEquals("8.000000001", mDataTransferSpeedView.mGbpsValue);
        assertEquals("1.0000000001", mDataTransferSpeedView.mGbypsValue);
        assertEquals("0.008", mDataTransferSpeedView.mTbpsValue);
        assertEquals("0.001", mDataTransferSpeedView.mTbypsValue);

        mDataTransferSpeedPresenter
                .getConversionFromBitsPerSecondResults("8000000000.9764326598653", 5);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBypsValue != null)
                && (mDataTransferSpeedView.mKbpsValue != null)
                && (mDataTransferSpeedView.mKbypsValue != null)
                && (mDataTransferSpeedView.mMbpsValue != null)
                && (mDataTransferSpeedView.mMbypsValue != null)
                && (mDataTransferSpeedView.mGbpsValue != null)
                && (mDataTransferSpeedView.mGbypsValue != null)
                && (mDataTransferSpeedView.mTbpsValue != null)
                && (mDataTransferSpeedView.mTbypsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mBpsError);
        assertEquals("1000000000.12205", mDataTransferSpeedView.mBypsValue);
        assertEquals("8000000.00098", mDataTransferSpeedView.mKbpsValue);
        assertEquals("1000000.00012", mDataTransferSpeedView.mKbypsValue);
        assertEquals("8000", mDataTransferSpeedView.mMbpsValue);
        assertEquals("1000", mDataTransferSpeedView.mMbypsValue);
        assertEquals("8", mDataTransferSpeedView.mGbpsValue);
        assertEquals("1", mDataTransferSpeedView.mGbypsValue);
        assertEquals("0.008", mDataTransferSpeedView.mTbpsValue);
        assertEquals("0.001", mDataTransferSpeedView.mTbypsValue);

        mDataTransferSpeedView.resetValues();
    }

    @Test
    public void testBytesPerSecondConversion() throws Exception {
        mDataTransferSpeedPresenter
                .getConversionFromBytesPerSecondResults("1000000009.764953261346", 10);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBpsValue != null)
                && (mDataTransferSpeedView.mKbpsValue != null)
                && (mDataTransferSpeedView.mKbypsValue != null)
                && (mDataTransferSpeedView.mMbpsValue != null)
                && (mDataTransferSpeedView.mMbypsValue != null)
                && (mDataTransferSpeedView.mGbpsValue != null)
                && (mDataTransferSpeedView.mGbypsValue != null)
                && (mDataTransferSpeedView.mTbpsValue != null)
                && (mDataTransferSpeedView.mTbypsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mBypsError);
        assertEquals("8000000078.1196260908", mDataTransferSpeedView.mBpsValue);
        assertEquals("8000000.0781196261", mDataTransferSpeedView.mKbpsValue);
        assertEquals("1000000.0097649533", mDataTransferSpeedView.mKbypsValue);
        assertEquals("8000.0000781196", mDataTransferSpeedView.mMbpsValue);
        assertEquals("1000.000009765", mDataTransferSpeedView.mMbypsValue);
        assertEquals("8.0000000781", mDataTransferSpeedView.mGbpsValue);
        assertEquals("1.0000000098", mDataTransferSpeedView.mGbypsValue);
        assertEquals("0.0080000001", mDataTransferSpeedView.mTbpsValue);
        assertEquals("0.001", mDataTransferSpeedView.mTbypsValue);

        mDataTransferSpeedPresenter
                .getConversionFromBytesPerSecondResults("1000000009.764953261346", 5);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBpsValue != null)
                && (mDataTransferSpeedView.mKbpsValue != null)
                && (mDataTransferSpeedView.mKbypsValue != null)
                && (mDataTransferSpeedView.mMbpsValue != null)
                && (mDataTransferSpeedView.mMbypsValue != null)
                && (mDataTransferSpeedView.mGbpsValue != null)
                && (mDataTransferSpeedView.mGbypsValue != null)
                && (mDataTransferSpeedView.mTbpsValue != null)
                && (mDataTransferSpeedView.mTbypsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mBypsError);
        assertEquals("8000000078.11963", mDataTransferSpeedView.mBpsValue);
        assertEquals("8000000.07812", mDataTransferSpeedView.mKbpsValue);
        assertEquals("1000000.00976", mDataTransferSpeedView.mKbypsValue);
        assertEquals("8000.00008", mDataTransferSpeedView.mMbpsValue);
        assertEquals("1000.00001", mDataTransferSpeedView.mMbypsValue);
        assertEquals("8", mDataTransferSpeedView.mGbpsValue);
        assertEquals("1", mDataTransferSpeedView.mGbypsValue);
        assertEquals("0.008", mDataTransferSpeedView.mTbpsValue);
        assertEquals("0.001", mDataTransferSpeedView.mTbypsValue);

        mDataTransferSpeedView.resetValues();
    }

    @Test
    public void testKilobitsPerSecondConversion() throws Exception {
        mDataTransferSpeedPresenter
                .getConversionFromKilobitsPerSecondResults("8000008.9764953164976", 10);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBpsValue != null)
                && (mDataTransferSpeedView.mBypsValue != null)
                && (mDataTransferSpeedView.mKbypsValue != null)
                && (mDataTransferSpeedView.mMbpsValue != null)
                && (mDataTransferSpeedView.mMbypsValue != null)
                && (mDataTransferSpeedView.mGbpsValue != null)
                && (mDataTransferSpeedView.mGbypsValue != null)
                && (mDataTransferSpeedView.mTbpsValue != null)
                && (mDataTransferSpeedView.mTbypsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mKbpsError);
        assertEquals("8000008976.4953164976", mDataTransferSpeedView.mBpsValue);
        assertEquals("1000001122.0619145622", mDataTransferSpeedView.mBypsValue);
        assertEquals("1000001.1220619146", mDataTransferSpeedView.mKbypsValue);
        assertEquals("8000.0089764953", mDataTransferSpeedView.mMbpsValue);
        assertEquals("1000.0011220619", mDataTransferSpeedView.mMbypsValue);
        assertEquals("8.0000089765", mDataTransferSpeedView.mGbpsValue);
        assertEquals("1.0000011221", mDataTransferSpeedView.mGbypsValue);
        assertEquals("0.008000009", mDataTransferSpeedView.mTbpsValue);
        assertEquals("0.0010000011", mDataTransferSpeedView.mTbypsValue);

        mDataTransferSpeedPresenter
                .getConversionFromKilobitsPerSecondResults("8000008.9764953164976", 5);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBpsValue != null)
                && (mDataTransferSpeedView.mBypsValue != null)
                && (mDataTransferSpeedView.mKbypsValue != null)
                && (mDataTransferSpeedView.mMbpsValue != null)
                && (mDataTransferSpeedView.mMbypsValue != null)
                && (mDataTransferSpeedView.mGbpsValue != null)
                && (mDataTransferSpeedView.mGbypsValue != null)
                && (mDataTransferSpeedView.mTbpsValue != null)
                && (mDataTransferSpeedView.mTbypsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mKbpsError);
        assertEquals("8000008976.49532", mDataTransferSpeedView.mBpsValue);
        assertEquals("1000001122.06191", mDataTransferSpeedView.mBypsValue);
        assertEquals("1000001.12206", mDataTransferSpeedView.mKbypsValue);
        assertEquals("8000.00898", mDataTransferSpeedView.mMbpsValue);
        assertEquals("1000.00112", mDataTransferSpeedView.mMbypsValue);
        assertEquals("8.00001", mDataTransferSpeedView.mGbpsValue);
        assertEquals("1", mDataTransferSpeedView.mGbypsValue);
        assertEquals("0.008", mDataTransferSpeedView.mTbpsValue);
        assertEquals("0.001", mDataTransferSpeedView.mTbypsValue);

        mDataTransferSpeedView.resetValues();
    }

    @Test
    public void testKilobytesPerSecondConversion() throws Exception {
        mDataTransferSpeedPresenter
                .getConversionFromKilobytesPerSecondResults("1000001.122061915316", 10);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBpsValue != null)
                && (mDataTransferSpeedView.mBypsValue != null)
                && (mDataTransferSpeedView.mKbpsValue != null)
                && (mDataTransferSpeedView.mMbpsValue != null)
                && (mDataTransferSpeedView.mMbypsValue != null)
                && (mDataTransferSpeedView.mGbpsValue != null)
                && (mDataTransferSpeedView.mGbypsValue != null)
                && (mDataTransferSpeedView.mTbpsValue != null)
                && (mDataTransferSpeedView.mTbypsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mKbypsError);
        assertEquals("8000008976.495322528", mDataTransferSpeedView.mBpsValue);
        assertEquals("1000001122.061915316", mDataTransferSpeedView.mBypsValue);
        assertEquals("8000008.9764953225", mDataTransferSpeedView.mKbpsValue);
        assertEquals("8000.0089764953", mDataTransferSpeedView.mMbpsValue);
        assertEquals("1000.0011220619", mDataTransferSpeedView.mMbypsValue);
        assertEquals("8.0000089765", mDataTransferSpeedView.mGbpsValue);
        assertEquals("1.0000011221", mDataTransferSpeedView.mGbypsValue);
        assertEquals("0.008000009", mDataTransferSpeedView.mTbpsValue);
        assertEquals("0.0010000011", mDataTransferSpeedView.mTbypsValue);

        mDataTransferSpeedPresenter
                .getConversionFromKilobytesPerSecondResults("1000001.122061915316", 5);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBpsValue != null)
                && (mDataTransferSpeedView.mBypsValue != null)
                && (mDataTransferSpeedView.mKbpsValue != null)
                && (mDataTransferSpeedView.mMbpsValue != null)
                && (mDataTransferSpeedView.mMbypsValue != null)
                && (mDataTransferSpeedView.mGbpsValue != null)
                && (mDataTransferSpeedView.mGbypsValue != null)
                && (mDataTransferSpeedView.mTbpsValue != null)
                && (mDataTransferSpeedView.mTbypsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mKbypsError);
        assertEquals("8000008976.49532", mDataTransferSpeedView.mBpsValue);
        assertEquals("1000001122.06192", mDataTransferSpeedView.mBypsValue);
        assertEquals("8000008.9765", mDataTransferSpeedView.mKbpsValue);
        assertEquals("8000.00898", mDataTransferSpeedView.mMbpsValue);
        assertEquals("1000.00112", mDataTransferSpeedView.mMbypsValue);
        assertEquals("8.00001", mDataTransferSpeedView.mGbpsValue);
        assertEquals("1", mDataTransferSpeedView.mGbypsValue);
        assertEquals("0.008", mDataTransferSpeedView.mTbpsValue);
        assertEquals("0.001", mDataTransferSpeedView.mTbypsValue);

        mDataTransferSpeedView.resetValues();
    }

    @Test
    public void testMegabitsPerSecondConversion() throws Exception {
        mDataTransferSpeedPresenter
                .getConversionFromMegabitsPerSecondResults("8009.976435623164", 10);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBpsValue != null)
                && (mDataTransferSpeedView.mBypsValue != null)
                && (mDataTransferSpeedView.mKbpsValue != null)
                && (mDataTransferSpeedView.mKbypsValue != null)
                && (mDataTransferSpeedView.mMbypsValue != null)
                && (mDataTransferSpeedView.mGbpsValue != null)
                && (mDataTransferSpeedView.mGbypsValue != null)
                && (mDataTransferSpeedView.mTbpsValue != null)
                && (mDataTransferSpeedView.mTbypsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mMbpsError);
        assertEquals("8009976435.623164", mDataTransferSpeedView.mBpsValue);
        assertEquals("1001247054.4528955", mDataTransferSpeedView.mBypsValue);
        assertEquals("8009976.435623164", mDataTransferSpeedView.mKbpsValue);
        assertEquals("1001247.0544528955", mDataTransferSpeedView.mKbypsValue);
        assertEquals("1001.2470544529", mDataTransferSpeedView.mMbypsValue);
        assertEquals("8.0099764356", mDataTransferSpeedView.mGbpsValue);
        assertEquals("1.0012470545", mDataTransferSpeedView.mGbypsValue);
        assertEquals("0.0080099764", mDataTransferSpeedView.mTbpsValue);
        assertEquals("0.0010012471", mDataTransferSpeedView.mTbypsValue);

        mDataTransferSpeedPresenter
                .getConversionFromMegabitsPerSecondResults("8009.976435623164", 5);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBpsValue != null)
                && (mDataTransferSpeedView.mBypsValue != null)
                && (mDataTransferSpeedView.mKbpsValue != null)
                && (mDataTransferSpeedView.mKbypsValue != null)
                && (mDataTransferSpeedView.mMbypsValue != null)
                && (mDataTransferSpeedView.mGbpsValue != null)
                && (mDataTransferSpeedView.mGbypsValue != null)
                && (mDataTransferSpeedView.mTbpsValue != null)
                && (mDataTransferSpeedView.mTbypsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mMbpsError);
        assertEquals("8009976435.62316", mDataTransferSpeedView.mBpsValue);
        assertEquals("1001247054.4529", mDataTransferSpeedView.mBypsValue);
        assertEquals("8009976.43562", mDataTransferSpeedView.mKbpsValue);
        assertEquals("1001247.05445", mDataTransferSpeedView.mKbypsValue);
        assertEquals("1001.24705", mDataTransferSpeedView.mMbypsValue);
        assertEquals("8.00998", mDataTransferSpeedView.mGbpsValue);
        assertEquals("1.00125", mDataTransferSpeedView.mGbypsValue);
        assertEquals("0.00801", mDataTransferSpeedView.mTbpsValue);
        assertEquals("0.001", mDataTransferSpeedView.mTbypsValue);

        mDataTransferSpeedView.resetValues();
    }

    @Test
    public void testMegabytesPerSecondConversion() throws Exception {
        mDataTransferSpeedPresenter
                .getConversionFromMegabytesPerSecondResults("1005.9764326594613", 10);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBpsValue != null)
                && (mDataTransferSpeedView.mBypsValue != null)
                && (mDataTransferSpeedView.mKbpsValue != null)
                && (mDataTransferSpeedView.mKbypsValue != null)
                && (mDataTransferSpeedView.mMbpsValue != null)
                && (mDataTransferSpeedView.mGbpsValue != null)
                && (mDataTransferSpeedView.mGbypsValue != null)
                && (mDataTransferSpeedView.mTbpsValue != null)
                && (mDataTransferSpeedView.mTbypsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mMbypsError);
        assertEquals("8047811461.2756904", mDataTransferSpeedView.mBpsValue);
        assertEquals("1005976432.6594613", mDataTransferSpeedView.mBypsValue);
        assertEquals("8047811.4612756904", mDataTransferSpeedView.mKbpsValue);
        assertEquals("1005976.4326594613", mDataTransferSpeedView.mKbypsValue);
        assertEquals("8047.8114612757", mDataTransferSpeedView.mMbpsValue);
        assertEquals("8.0478114613", mDataTransferSpeedView.mGbpsValue);
        assertEquals("1.0059764327", mDataTransferSpeedView.mGbypsValue);
        assertEquals("0.0080478115", mDataTransferSpeedView.mTbpsValue);
        assertEquals("0.0010059764", mDataTransferSpeedView.mTbypsValue);

        mDataTransferSpeedPresenter
                .getConversionFromMegabytesPerSecondResults("1005.9764326594613", 5);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBpsValue != null)
                && (mDataTransferSpeedView.mBypsValue != null)
                && (mDataTransferSpeedView.mKbpsValue != null)
                && (mDataTransferSpeedView.mKbypsValue != null)
                && (mDataTransferSpeedView.mMbpsValue != null)
                && (mDataTransferSpeedView.mGbpsValue != null)
                && (mDataTransferSpeedView.mGbypsValue != null)
                && (mDataTransferSpeedView.mTbpsValue != null)
                && (mDataTransferSpeedView.mTbypsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mMbypsError);
        assertEquals("8047811461.27569", mDataTransferSpeedView.mBpsValue);
        assertEquals("1005976432.65946", mDataTransferSpeedView.mBypsValue);
        assertEquals("8047811.46128", mDataTransferSpeedView.mKbpsValue);
        assertEquals("1005976.43266", mDataTransferSpeedView.mKbypsValue);
        assertEquals("8047.81146", mDataTransferSpeedView.mMbpsValue);
        assertEquals("8.04781", mDataTransferSpeedView.mGbpsValue);
        assertEquals("1.00598", mDataTransferSpeedView.mGbypsValue);
        assertEquals("0.00805", mDataTransferSpeedView.mTbpsValue);
        assertEquals("0.00101", mDataTransferSpeedView.mTbypsValue);

        mDataTransferSpeedView.resetValues();
    }

    @Test
    public void testGigabitsPerSecondConversion() throws Exception {
        mDataTransferSpeedPresenter
                .getConversionFromGigabitsPerSecondResults("8.946731926437619", 10);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBpsValue != null)
                && (mDataTransferSpeedView.mBypsValue != null)
                && (mDataTransferSpeedView.mKbpsValue != null)
                && (mDataTransferSpeedView.mKbypsValue != null)
                && (mDataTransferSpeedView.mMbpsValue != null)
                && (mDataTransferSpeedView.mMbypsValue != null)
                && (mDataTransferSpeedView.mGbypsValue != null)
                && (mDataTransferSpeedView.mTbpsValue != null)
                && (mDataTransferSpeedView.mTbypsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mGbpsError);
        assertEquals("8946731926.437619", mDataTransferSpeedView.mBpsValue);
        assertEquals("1118341490.804702375", mDataTransferSpeedView.mBypsValue);
        assertEquals("8946731.926437619", mDataTransferSpeedView.mKbpsValue);
        assertEquals("1118341.4908047024", mDataTransferSpeedView.mKbypsValue);
        assertEquals("8946.7319264376", mDataTransferSpeedView.mMbpsValue);
        assertEquals("1118.3414908047", mDataTransferSpeedView.mMbypsValue);
        assertEquals("1.1183414908", mDataTransferSpeedView.mGbypsValue);
        assertEquals("0.0089467319", mDataTransferSpeedView.mTbpsValue);
        assertEquals("0.0011183415", mDataTransferSpeedView.mTbypsValue);

        mDataTransferSpeedPresenter
                .getConversionFromGigabitsPerSecondResults("8.946731926437619", 5);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBpsValue != null)
                && (mDataTransferSpeedView.mBypsValue != null)
                && (mDataTransferSpeedView.mKbpsValue != null)
                && (mDataTransferSpeedView.mKbypsValue != null)
                && (mDataTransferSpeedView.mMbpsValue != null)
                && (mDataTransferSpeedView.mMbypsValue != null)
                && (mDataTransferSpeedView.mGbypsValue != null)
                && (mDataTransferSpeedView.mTbpsValue != null)
                && (mDataTransferSpeedView.mTbypsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mGbpsError);
        assertEquals("8946731926.43762", mDataTransferSpeedView.mBpsValue);
        assertEquals("1118341490.8047", mDataTransferSpeedView.mBypsValue);
        assertEquals("8946731.92644", mDataTransferSpeedView.mKbpsValue);
        assertEquals("1118341.4908", mDataTransferSpeedView.mKbypsValue);
        assertEquals("8946.73193", mDataTransferSpeedView.mMbpsValue);
        assertEquals("1118.34149", mDataTransferSpeedView.mMbypsValue);
        assertEquals("1.11834", mDataTransferSpeedView.mGbypsValue);
        assertEquals("0.00895", mDataTransferSpeedView.mTbpsValue);
        assertEquals("0.00112", mDataTransferSpeedView.mTbypsValue);

        mDataTransferSpeedView.resetValues();
    }

    @Test
    public void testGigabytesPerSecondConversion() throws Exception {
        mDataTransferSpeedPresenter
                .getConversionFromGigabytesPerSecondResults("2.8946231649761316296", 10);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBpsValue != null)
                && (mDataTransferSpeedView.mBypsValue != null)
                && (mDataTransferSpeedView.mKbpsValue != null)
                && (mDataTransferSpeedView.mKbypsValue != null)
                && (mDataTransferSpeedView.mMbpsValue != null)
                && (mDataTransferSpeedView.mMbypsValue != null)
                && (mDataTransferSpeedView.mGbpsValue != null)
                && (mDataTransferSpeedView.mTbpsValue != null)
                && (mDataTransferSpeedView.mTbypsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mGbypsError);
        assertEquals("23156985319.8090530368", mDataTransferSpeedView.mBpsValue);
        assertEquals("2894623164.9761316296", mDataTransferSpeedView.mBypsValue);
        assertEquals("23156985.319809053", mDataTransferSpeedView.mKbpsValue);
        assertEquals("2894623.1649761316", mDataTransferSpeedView.mKbypsValue);
        assertEquals("23156.9853198091", mDataTransferSpeedView.mMbpsValue);
        assertEquals("2894.6231649761", mDataTransferSpeedView.mMbypsValue);
        assertEquals("23.1569853198", mDataTransferSpeedView.mGbpsValue);
        assertEquals("0.0231569853", mDataTransferSpeedView.mTbpsValue);
        assertEquals("0.0028946232", mDataTransferSpeedView.mTbypsValue);

        mDataTransferSpeedPresenter
                .getConversionFromGigabytesPerSecondResults("2.8946231649761316296", 5);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBpsValue != null)
                && (mDataTransferSpeedView.mBypsValue != null)
                && (mDataTransferSpeedView.mKbpsValue != null)
                && (mDataTransferSpeedView.mKbypsValue != null)
                && (mDataTransferSpeedView.mMbpsValue != null)
                && (mDataTransferSpeedView.mMbypsValue != null)
                && (mDataTransferSpeedView.mGbpsValue != null)
                && (mDataTransferSpeedView.mTbpsValue != null)
                && (mDataTransferSpeedView.mTbypsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mGbypsError);
        assertEquals("23156985319.80905", mDataTransferSpeedView.mBpsValue);
        assertEquals("2894623164.97613", mDataTransferSpeedView.mBypsValue);
        assertEquals("23156985.31981", mDataTransferSpeedView.mKbpsValue);
        assertEquals("2894623.16498", mDataTransferSpeedView.mKbypsValue);
        assertEquals("23156.98532", mDataTransferSpeedView.mMbpsValue);
        assertEquals("2894.62316", mDataTransferSpeedView.mMbypsValue);
        assertEquals("23.15699", mDataTransferSpeedView.mGbpsValue);
        assertEquals("0.02316", mDataTransferSpeedView.mTbpsValue);
        assertEquals("0.00289", mDataTransferSpeedView.mTbypsValue);

        mDataTransferSpeedView.resetValues();
    }

    @Test
    public void testTerabitsPerSecondConversion() throws Exception {
        mDataTransferSpeedPresenter
                .getConversionFromTerabitsPerSecondResults("0.59461326596492331649564319", 10);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBpsValue != null)
                && (mDataTransferSpeedView.mBypsValue != null)
                && (mDataTransferSpeedView.mKbpsValue != null)
                && (mDataTransferSpeedView.mKbypsValue != null)
                && (mDataTransferSpeedView.mMbpsValue != null)
                && (mDataTransferSpeedView.mMbypsValue != null)
                && (mDataTransferSpeedView.mGbpsValue != null)
                && (mDataTransferSpeedView.mGbypsValue != null)
                && (mDataTransferSpeedView.mTbypsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mTbpsError);
        assertEquals("594613265964.9233164956", mDataTransferSpeedView.mBpsValue);
        assertEquals("74326658245.615414562", mDataTransferSpeedView.mBypsValue);
        assertEquals("594613265.9649233165", mDataTransferSpeedView.mKbpsValue);
        assertEquals("74326658.2456154146", mDataTransferSpeedView.mKbypsValue);
        assertEquals("594613.2659649233", mDataTransferSpeedView.mMbpsValue);
        assertEquals("74326.6582456154", mDataTransferSpeedView.mMbypsValue);
        assertEquals("594.6132659649", mDataTransferSpeedView.mGbpsValue);
        assertEquals("74.3266582456", mDataTransferSpeedView.mGbypsValue);
        assertEquals("0.0743266582", mDataTransferSpeedView.mTbypsValue);

        mDataTransferSpeedPresenter
                .getConversionFromTerabitsPerSecondResults("0.59461326596492331649564319", 5);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBpsValue != null)
                && (mDataTransferSpeedView.mBypsValue != null)
                && (mDataTransferSpeedView.mKbpsValue != null)
                && (mDataTransferSpeedView.mKbypsValue != null)
                && (mDataTransferSpeedView.mMbpsValue != null)
                && (mDataTransferSpeedView.mMbypsValue != null)
                && (mDataTransferSpeedView.mGbpsValue != null)
                && (mDataTransferSpeedView.mGbypsValue != null)
                && (mDataTransferSpeedView.mTbypsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mTbpsError);
        assertEquals("594613265964.92332", mDataTransferSpeedView.mBpsValue);
        assertEquals("74326658245.61541", mDataTransferSpeedView.mBypsValue);
        assertEquals("594613265.96492", mDataTransferSpeedView.mKbpsValue);
        assertEquals("74326658.24562", mDataTransferSpeedView.mKbypsValue);
        assertEquals("594613.26596", mDataTransferSpeedView.mMbpsValue);
        assertEquals("74326.65825", mDataTransferSpeedView.mMbypsValue);
        assertEquals("594.61327", mDataTransferSpeedView.mGbpsValue);
        assertEquals("74.32666", mDataTransferSpeedView.mGbypsValue);
        assertEquals("0.07433", mDataTransferSpeedView.mTbypsValue);

        mDataTransferSpeedView.resetValues();
    }

    @Test
    public void testTerabytesPerSecondConversion() throws Exception {
        mDataTransferSpeedPresenter
                .getConversionFromTerabytesPerSecondResults("0.0264976532649164376495623", 10);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBpsValue != null)
                && (mDataTransferSpeedView.mBypsValue != null)
                && (mDataTransferSpeedView.mKbpsValue != null)
                && (mDataTransferSpeedView.mKbypsValue != null)
                && (mDataTransferSpeedView.mMbpsValue != null)
                && (mDataTransferSpeedView.mMbypsValue != null)
                && (mDataTransferSpeedView.mGbpsValue != null)
                && (mDataTransferSpeedView.mGbypsValue != null)
                && (mDataTransferSpeedView.mTbpsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mTbypsError);
        assertEquals("211981226119.3315011965", mDataTransferSpeedView.mBpsValue);
        assertEquals("26497653264.9164376496", mDataTransferSpeedView.mBypsValue);
        assertEquals("211981226.1193315012", mDataTransferSpeedView.mKbpsValue);
        assertEquals("26497653.2649164376", mDataTransferSpeedView.mKbypsValue);
        assertEquals("211981.2261193315", mDataTransferSpeedView.mMbpsValue);
        assertEquals("26497.6532649164", mDataTransferSpeedView.mMbypsValue);
        assertEquals("211.9812261193", mDataTransferSpeedView.mGbpsValue);
        assertEquals("26.4976532649", mDataTransferSpeedView.mGbypsValue);
        assertEquals("0.2119812261", mDataTransferSpeedView.mTbpsValue);

        mDataTransferSpeedPresenter
                .getConversionFromTerabytesPerSecondResults("0.0264976532649164376495623", 5);
        TestHelper.waitFor(() -> ((mDataTransferSpeedView.mBpsValue != null)
                && (mDataTransferSpeedView.mBypsValue != null)
                && (mDataTransferSpeedView.mKbpsValue != null)
                && (mDataTransferSpeedView.mKbypsValue != null)
                && (mDataTransferSpeedView.mMbpsValue != null)
                && (mDataTransferSpeedView.mMbypsValue != null)
                && (mDataTransferSpeedView.mGbpsValue != null)
                && (mDataTransferSpeedView.mGbypsValue != null)
                && (mDataTransferSpeedView.mTbpsValue != null)));

        assertEquals(0, mDataTransferSpeedView.mTbypsError);
        assertEquals("211981226119.3315", mDataTransferSpeedView.mBpsValue);
        assertEquals("26497653264.91644", mDataTransferSpeedView.mBypsValue);
        assertEquals("211981226.11933", mDataTransferSpeedView.mKbpsValue);
        assertEquals("26497653.26492", mDataTransferSpeedView.mKbypsValue);
        assertEquals("211981.22612", mDataTransferSpeedView.mMbpsValue);
        assertEquals("26497.65326", mDataTransferSpeedView.mMbypsValue);
        assertEquals("211.98123", mDataTransferSpeedView.mGbpsValue);
        assertEquals("26.49765", mDataTransferSpeedView.mGbypsValue);
        assertEquals("0.21198", mDataTransferSpeedView.mTbpsValue);

        mDataTransferSpeedView.resetValues();
    }
}
