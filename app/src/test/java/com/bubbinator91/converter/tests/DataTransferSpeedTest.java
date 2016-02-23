package com.bubbinator91.converter.tests;

import com.bubbinator91.converter.mock.views.MockDataTransferSpeedView;
import com.bubbinator91.converter.interfaces.presenter.IDataTransferSpeedPresenter;
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
    MockDataTransferSpeedView dataTransferSpeedView;
    @Inject
    IDataTransferSpeedPresenter dataTransferSpeedPresenter;

    @Before
    public void setUp() throws Exception {
        TestHelper.getTestClassInjector()
                .inject(this);

        assertNotNull(dataTransferSpeedView);
        assertNotNull(dataTransferSpeedPresenter);
    }

    @Test
    public void testBitsPerSecondConversion() throws Exception {
        dataTransferSpeedPresenter
                .getConversionFromBitsPerSecond("8000000000.9764326598653", 10);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bypsValue != null)
                && (dataTransferSpeedView.kbpsValue != null)
                && (dataTransferSpeedView.kbypsValue != null)
                && (dataTransferSpeedView.mbpsValue != null)
                && (dataTransferSpeedView.mbypsValue != null)
                && (dataTransferSpeedView.gbpsValue != null)
                && (dataTransferSpeedView.gbypsValue != null)
                && (dataTransferSpeedView.tbpsValue != null)
                && (dataTransferSpeedView.tbypsValue != null)));

        assertEquals(false, dataTransferSpeedView.bpsError);
        assertEquals("1000000000.1220540825", dataTransferSpeedView.bypsValue);
        assertEquals("8000000.0009764327", dataTransferSpeedView.kbpsValue);
        assertEquals("1000000.0001220541", dataTransferSpeedView.kbypsValue);
        assertEquals("8000.0000009764", dataTransferSpeedView.mbpsValue);
        assertEquals("1000.0000001221", dataTransferSpeedView.mbypsValue);
        assertEquals("8.000000001", dataTransferSpeedView.gbpsValue);
        assertEquals("1.0000000001", dataTransferSpeedView.gbypsValue);
        assertEquals("0.008", dataTransferSpeedView.tbpsValue);
        assertEquals("0.001", dataTransferSpeedView.tbypsValue);

        dataTransferSpeedPresenter
                .getConversionFromBitsPerSecond("8000000000.9764326598653", 5);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bypsValue != null)
                && (dataTransferSpeedView.kbpsValue != null)
                && (dataTransferSpeedView.kbypsValue != null)
                && (dataTransferSpeedView.mbpsValue != null)
                && (dataTransferSpeedView.mbypsValue != null)
                && (dataTransferSpeedView.gbpsValue != null)
                && (dataTransferSpeedView.gbypsValue != null)
                && (dataTransferSpeedView.tbpsValue != null)
                && (dataTransferSpeedView.tbypsValue != null)));

        assertEquals(false, dataTransferSpeedView.bpsError);
        assertEquals("1000000000.12205", dataTransferSpeedView.bypsValue);
        assertEquals("8000000.00098", dataTransferSpeedView.kbpsValue);
        assertEquals("1000000.00012", dataTransferSpeedView.kbypsValue);
        assertEquals("8000", dataTransferSpeedView.mbpsValue);
        assertEquals("1000", dataTransferSpeedView.mbypsValue);
        assertEquals("8", dataTransferSpeedView.gbpsValue);
        assertEquals("1", dataTransferSpeedView.gbypsValue);
        assertEquals("0.008", dataTransferSpeedView.tbpsValue);
        assertEquals("0.001", dataTransferSpeedView.tbypsValue);

        dataTransferSpeedView.resetValues();
    }

    @Test
    public void testBytesPerSecondConversion() throws Exception {
        dataTransferSpeedPresenter
                .getConversionFromBytesPerSecond("1000000009.764953261346", 10);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bpsValue != null)
                && (dataTransferSpeedView.kbpsValue != null)
                && (dataTransferSpeedView.kbypsValue != null)
                && (dataTransferSpeedView.mbpsValue != null)
                && (dataTransferSpeedView.mbypsValue != null)
                && (dataTransferSpeedView.gbpsValue != null)
                && (dataTransferSpeedView.gbypsValue != null)
                && (dataTransferSpeedView.tbpsValue != null)
                && (dataTransferSpeedView.tbypsValue != null)));

        assertEquals(false, dataTransferSpeedView.bypsError);
        assertEquals("8000000078.1196260908", dataTransferSpeedView.bpsValue);
        assertEquals("8000000.0781196261", dataTransferSpeedView.kbpsValue);
        assertEquals("1000000.0097649533", dataTransferSpeedView.kbypsValue);
        assertEquals("8000.0000781196", dataTransferSpeedView.mbpsValue);
        assertEquals("1000.000009765", dataTransferSpeedView.mbypsValue);
        assertEquals("8.0000000781", dataTransferSpeedView.gbpsValue);
        assertEquals("1.0000000098", dataTransferSpeedView.gbypsValue);
        assertEquals("0.0080000001", dataTransferSpeedView.tbpsValue);
        assertEquals("0.001", dataTransferSpeedView.tbypsValue);

        dataTransferSpeedPresenter
                .getConversionFromBytesPerSecond("1000000009.764953261346", 5);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bpsValue != null)
                && (dataTransferSpeedView.kbpsValue != null)
                && (dataTransferSpeedView.kbypsValue != null)
                && (dataTransferSpeedView.mbpsValue != null)
                && (dataTransferSpeedView.mbypsValue != null)
                && (dataTransferSpeedView.gbpsValue != null)
                && (dataTransferSpeedView.gbypsValue != null)
                && (dataTransferSpeedView.tbpsValue != null)
                && (dataTransferSpeedView.tbypsValue != null)));

        assertEquals(false, dataTransferSpeedView.bypsError);
        assertEquals("8000000078.11963", dataTransferSpeedView.bpsValue);
        assertEquals("8000000.07812", dataTransferSpeedView.kbpsValue);
        assertEquals("1000000.00976", dataTransferSpeedView.kbypsValue);
        assertEquals("8000.00008", dataTransferSpeedView.mbpsValue);
        assertEquals("1000.00001", dataTransferSpeedView.mbypsValue);
        assertEquals("8", dataTransferSpeedView.gbpsValue);
        assertEquals("1", dataTransferSpeedView.gbypsValue);
        assertEquals("0.008", dataTransferSpeedView.tbpsValue);
        assertEquals("0.001", dataTransferSpeedView.tbypsValue);

        dataTransferSpeedView.resetValues();
    }

    @Test
    public void testKilobitsPerSecondConversion() throws Exception {
        dataTransferSpeedPresenter
                .getConversionFromKilobitsPerSecond("8000008.9764953164976", 10);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bpsValue != null)
                && (dataTransferSpeedView.bypsValue != null)
                && (dataTransferSpeedView.kbypsValue != null)
                && (dataTransferSpeedView.mbpsValue != null)
                && (dataTransferSpeedView.mbypsValue != null)
                && (dataTransferSpeedView.gbpsValue != null)
                && (dataTransferSpeedView.gbypsValue != null)
                && (dataTransferSpeedView.tbpsValue != null)
                && (dataTransferSpeedView.tbypsValue != null)));

        assertEquals(false, dataTransferSpeedView.kbpsError);
        assertEquals("8000008976.4953164976", dataTransferSpeedView.bpsValue);
        assertEquals("1000001122.0619145622", dataTransferSpeedView.bypsValue);
        assertEquals("1000001.1220619146", dataTransferSpeedView.kbypsValue);
        assertEquals("8000.0089764953", dataTransferSpeedView.mbpsValue);
        assertEquals("1000.0011220619", dataTransferSpeedView.mbypsValue);
        assertEquals("8.0000089765", dataTransferSpeedView.gbpsValue);
        assertEquals("1.0000011221", dataTransferSpeedView.gbypsValue);
        assertEquals("0.008000009", dataTransferSpeedView.tbpsValue);
        assertEquals("0.0010000011", dataTransferSpeedView.tbypsValue);

        dataTransferSpeedPresenter
                .getConversionFromKilobitsPerSecond("8000008.9764953164976", 5);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bpsValue != null)
                && (dataTransferSpeedView.bypsValue != null)
                && (dataTransferSpeedView.kbypsValue != null)
                && (dataTransferSpeedView.mbpsValue != null)
                && (dataTransferSpeedView.mbypsValue != null)
                && (dataTransferSpeedView.gbpsValue != null)
                && (dataTransferSpeedView.gbypsValue != null)
                && (dataTransferSpeedView.tbpsValue != null)
                && (dataTransferSpeedView.tbypsValue != null)));

        assertEquals(false, dataTransferSpeedView.kbpsError);
        assertEquals("8000008976.49532", dataTransferSpeedView.bpsValue);
        assertEquals("1000001122.06191", dataTransferSpeedView.bypsValue);
        assertEquals("1000001.12206", dataTransferSpeedView.kbypsValue);
        assertEquals("8000.00898", dataTransferSpeedView.mbpsValue);
        assertEquals("1000.00112", dataTransferSpeedView.mbypsValue);
        assertEquals("8.00001", dataTransferSpeedView.gbpsValue);
        assertEquals("1", dataTransferSpeedView.gbypsValue);
        assertEquals("0.008", dataTransferSpeedView.tbpsValue);
        assertEquals("0.001", dataTransferSpeedView.tbypsValue);

        dataTransferSpeedView.resetValues();
    }

    @Test
    public void testKilobytesPerSecondConversion() throws Exception {
        dataTransferSpeedPresenter
                .getConversionFromKilobytesPerSecond("1000001.122061915316", 10);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bpsValue != null)
                && (dataTransferSpeedView.bypsValue != null)
                && (dataTransferSpeedView.kbpsValue != null)
                && (dataTransferSpeedView.mbpsValue != null)
                && (dataTransferSpeedView.mbypsValue != null)
                && (dataTransferSpeedView.gbpsValue != null)
                && (dataTransferSpeedView.gbypsValue != null)
                && (dataTransferSpeedView.tbpsValue != null)
                && (dataTransferSpeedView.tbypsValue != null)));

        assertEquals(false, dataTransferSpeedView.kbypsError);
        assertEquals("8000008976.495322528", dataTransferSpeedView.bpsValue);
        assertEquals("1000001122.061915316", dataTransferSpeedView.bypsValue);
        assertEquals("8000008.9764953225", dataTransferSpeedView.kbpsValue);
        assertEquals("8000.0089764953", dataTransferSpeedView.mbpsValue);
        assertEquals("1000.0011220619", dataTransferSpeedView.mbypsValue);
        assertEquals("8.0000089765", dataTransferSpeedView.gbpsValue);
        assertEquals("1.0000011221", dataTransferSpeedView.gbypsValue);
        assertEquals("0.008000009", dataTransferSpeedView.tbpsValue);
        assertEquals("0.0010000011", dataTransferSpeedView.tbypsValue);

        dataTransferSpeedPresenter
                .getConversionFromKilobytesPerSecond("1000001.122061915316", 5);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bpsValue != null)
                && (dataTransferSpeedView.bypsValue != null)
                && (dataTransferSpeedView.kbpsValue != null)
                && (dataTransferSpeedView.mbpsValue != null)
                && (dataTransferSpeedView.mbypsValue != null)
                && (dataTransferSpeedView.gbpsValue != null)
                && (dataTransferSpeedView.gbypsValue != null)
                && (dataTransferSpeedView.tbpsValue != null)
                && (dataTransferSpeedView.tbypsValue != null)));

        assertEquals(false, dataTransferSpeedView.kbypsError);
        assertEquals("8000008976.49532", dataTransferSpeedView.bpsValue);
        assertEquals("1000001122.06192", dataTransferSpeedView.bypsValue);
        assertEquals("8000008.9765", dataTransferSpeedView.kbpsValue);
        assertEquals("8000.00898", dataTransferSpeedView.mbpsValue);
        assertEquals("1000.00112", dataTransferSpeedView.mbypsValue);
        assertEquals("8.00001", dataTransferSpeedView.gbpsValue);
        assertEquals("1", dataTransferSpeedView.gbypsValue);
        assertEquals("0.008", dataTransferSpeedView.tbpsValue);
        assertEquals("0.001", dataTransferSpeedView.tbypsValue);

        dataTransferSpeedView.resetValues();
    }

    @Test
    public void testMegabitsPerSecondConversion() throws Exception {
        dataTransferSpeedPresenter
                .getConversionFromMegabitsPerSecond("8009.976435623164", 10);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bpsValue != null)
                && (dataTransferSpeedView.bypsValue != null)
                && (dataTransferSpeedView.kbpsValue != null)
                && (dataTransferSpeedView.kbypsValue != null)
                && (dataTransferSpeedView.mbypsValue != null)
                && (dataTransferSpeedView.gbpsValue != null)
                && (dataTransferSpeedView.gbypsValue != null)
                && (dataTransferSpeedView.tbpsValue != null)
                && (dataTransferSpeedView.tbypsValue != null)));

        assertEquals(false, dataTransferSpeedView.mbpsError);
        assertEquals("8009976435.623164", dataTransferSpeedView.bpsValue);
        assertEquals("1001247054.4528955", dataTransferSpeedView.bypsValue);
        assertEquals("8009976.435623164", dataTransferSpeedView.kbpsValue);
        assertEquals("1001247.0544528955", dataTransferSpeedView.kbypsValue);
        assertEquals("1001.2470544529", dataTransferSpeedView.mbypsValue);
        assertEquals("8.0099764356", dataTransferSpeedView.gbpsValue);
        assertEquals("1.0012470545", dataTransferSpeedView.gbypsValue);
        assertEquals("0.0080099764", dataTransferSpeedView.tbpsValue);
        assertEquals("0.0010012471", dataTransferSpeedView.tbypsValue);

        dataTransferSpeedPresenter
                .getConversionFromMegabitsPerSecond("8009.976435623164", 5);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bpsValue != null)
                && (dataTransferSpeedView.bypsValue != null)
                && (dataTransferSpeedView.kbpsValue != null)
                && (dataTransferSpeedView.kbypsValue != null)
                && (dataTransferSpeedView.mbypsValue != null)
                && (dataTransferSpeedView.gbpsValue != null)
                && (dataTransferSpeedView.gbypsValue != null)
                && (dataTransferSpeedView.tbpsValue != null)
                && (dataTransferSpeedView.tbypsValue != null)));

        assertEquals(false, dataTransferSpeedView.mbpsError);
        assertEquals("8009976435.62316", dataTransferSpeedView.bpsValue);
        assertEquals("1001247054.4529", dataTransferSpeedView.bypsValue);
        assertEquals("8009976.43562", dataTransferSpeedView.kbpsValue);
        assertEquals("1001247.05445", dataTransferSpeedView.kbypsValue);
        assertEquals("1001.24705", dataTransferSpeedView.mbypsValue);
        assertEquals("8.00998", dataTransferSpeedView.gbpsValue);
        assertEquals("1.00125", dataTransferSpeedView.gbypsValue);
        assertEquals("0.00801", dataTransferSpeedView.tbpsValue);
        assertEquals("0.001", dataTransferSpeedView.tbypsValue);

        dataTransferSpeedView.resetValues();
    }

    @Test
    public void testMegabytesPerSecondConversion() throws Exception {
        dataTransferSpeedPresenter
                .getConversionFromMegabytesPerSecond("1005.9764326594613", 10);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bpsValue != null)
                && (dataTransferSpeedView.bypsValue != null)
                && (dataTransferSpeedView.kbpsValue != null)
                && (dataTransferSpeedView.kbypsValue != null)
                && (dataTransferSpeedView.mbpsValue != null)
                && (dataTransferSpeedView.gbpsValue != null)
                && (dataTransferSpeedView.gbypsValue != null)
                && (dataTransferSpeedView.tbpsValue != null)
                && (dataTransferSpeedView.tbypsValue != null)));

        assertEquals(false, dataTransferSpeedView.mbypsError);
        assertEquals("8047811461.2756904", dataTransferSpeedView.bpsValue);
        assertEquals("1005976432.6594613", dataTransferSpeedView.bypsValue);
        assertEquals("8047811.4612756904", dataTransferSpeedView.kbpsValue);
        assertEquals("1005976.4326594613", dataTransferSpeedView.kbypsValue);
        assertEquals("8047.8114612757", dataTransferSpeedView.mbpsValue);
        assertEquals("8.0478114613", dataTransferSpeedView.gbpsValue);
        assertEquals("1.0059764327", dataTransferSpeedView.gbypsValue);
        assertEquals("0.0080478115", dataTransferSpeedView.tbpsValue);
        assertEquals("0.0010059764", dataTransferSpeedView.tbypsValue);

        dataTransferSpeedPresenter
                .getConversionFromMegabytesPerSecond("1005.9764326594613", 5);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bpsValue != null)
                && (dataTransferSpeedView.bypsValue != null)
                && (dataTransferSpeedView.kbpsValue != null)
                && (dataTransferSpeedView.kbypsValue != null)
                && (dataTransferSpeedView.mbpsValue != null)
                && (dataTransferSpeedView.gbpsValue != null)
                && (dataTransferSpeedView.gbypsValue != null)
                && (dataTransferSpeedView.tbpsValue != null)
                && (dataTransferSpeedView.tbypsValue != null)));

        assertEquals(false, dataTransferSpeedView.mbypsError);
        assertEquals("8047811461.27569", dataTransferSpeedView.bpsValue);
        assertEquals("1005976432.65946", dataTransferSpeedView.bypsValue);
        assertEquals("8047811.46128", dataTransferSpeedView.kbpsValue);
        assertEquals("1005976.43266", dataTransferSpeedView.kbypsValue);
        assertEquals("8047.81146", dataTransferSpeedView.mbpsValue);
        assertEquals("8.04781", dataTransferSpeedView.gbpsValue);
        assertEquals("1.00598", dataTransferSpeedView.gbypsValue);
        assertEquals("0.00805", dataTransferSpeedView.tbpsValue);
        assertEquals("0.00101", dataTransferSpeedView.tbypsValue);

        dataTransferSpeedView.resetValues();
    }

    @Test
    public void testGigabitsPerSecondConversion() throws Exception {
        dataTransferSpeedPresenter
                .getConversionFromGigabitsPerSecond("8.946731926437619", 10);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bpsValue != null)
                && (dataTransferSpeedView.bypsValue != null)
                && (dataTransferSpeedView.kbpsValue != null)
                && (dataTransferSpeedView.kbypsValue != null)
                && (dataTransferSpeedView.mbpsValue != null)
                && (dataTransferSpeedView.mbypsValue != null)
                && (dataTransferSpeedView.gbypsValue != null)
                && (dataTransferSpeedView.tbpsValue != null)
                && (dataTransferSpeedView.tbypsValue != null)));

        assertEquals(false, dataTransferSpeedView.gbpsError);
        assertEquals("8946731926.437619", dataTransferSpeedView.bpsValue);
        assertEquals("1118341490.804702375", dataTransferSpeedView.bypsValue);
        assertEquals("8946731.926437619", dataTransferSpeedView.kbpsValue);
        assertEquals("1118341.4908047024", dataTransferSpeedView.kbypsValue);
        assertEquals("8946.7319264376", dataTransferSpeedView.mbpsValue);
        assertEquals("1118.3414908047", dataTransferSpeedView.mbypsValue);
        assertEquals("1.1183414908", dataTransferSpeedView.gbypsValue);
        assertEquals("0.0089467319", dataTransferSpeedView.tbpsValue);
        assertEquals("0.0011183415", dataTransferSpeedView.tbypsValue);

        dataTransferSpeedPresenter
                .getConversionFromGigabitsPerSecond("8.946731926437619", 5);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bpsValue != null)
                && (dataTransferSpeedView.bypsValue != null)
                && (dataTransferSpeedView.kbpsValue != null)
                && (dataTransferSpeedView.kbypsValue != null)
                && (dataTransferSpeedView.mbpsValue != null)
                && (dataTransferSpeedView.mbypsValue != null)
                && (dataTransferSpeedView.gbypsValue != null)
                && (dataTransferSpeedView.tbpsValue != null)
                && (dataTransferSpeedView.tbypsValue != null)));

        assertEquals(false, dataTransferSpeedView.gbpsError);
        assertEquals("8946731926.43762", dataTransferSpeedView.bpsValue);
        assertEquals("1118341490.8047", dataTransferSpeedView.bypsValue);
        assertEquals("8946731.92644", dataTransferSpeedView.kbpsValue);
        assertEquals("1118341.4908", dataTransferSpeedView.kbypsValue);
        assertEquals("8946.73193", dataTransferSpeedView.mbpsValue);
        assertEquals("1118.34149", dataTransferSpeedView.mbypsValue);
        assertEquals("1.11834", dataTransferSpeedView.gbypsValue);
        assertEquals("0.00895", dataTransferSpeedView.tbpsValue);
        assertEquals("0.00112", dataTransferSpeedView.tbypsValue);

        dataTransferSpeedView.resetValues();
    }

    @Test
    public void testGigabytesPerSecondConversion() throws Exception {
        dataTransferSpeedPresenter
                .getConversionFromGigabytesPerSecond("2.8946231649761316296", 10);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bpsValue != null)
                && (dataTransferSpeedView.bypsValue != null)
                && (dataTransferSpeedView.kbpsValue != null)
                && (dataTransferSpeedView.kbypsValue != null)
                && (dataTransferSpeedView.mbpsValue != null)
                && (dataTransferSpeedView.mbypsValue != null)
                && (dataTransferSpeedView.gbpsValue != null)
                && (dataTransferSpeedView.tbpsValue != null)
                && (dataTransferSpeedView.tbypsValue != null)));

        assertEquals(false, dataTransferSpeedView.gbypsError);
        assertEquals("23156985319.8090530368", dataTransferSpeedView.bpsValue);
        assertEquals("2894623164.9761316296", dataTransferSpeedView.bypsValue);
        assertEquals("23156985.319809053", dataTransferSpeedView.kbpsValue);
        assertEquals("2894623.1649761316", dataTransferSpeedView.kbypsValue);
        assertEquals("23156.9853198091", dataTransferSpeedView.mbpsValue);
        assertEquals("2894.6231649761", dataTransferSpeedView.mbypsValue);
        assertEquals("23.1569853198", dataTransferSpeedView.gbpsValue);
        assertEquals("0.0231569853", dataTransferSpeedView.tbpsValue);
        assertEquals("0.0028946232", dataTransferSpeedView.tbypsValue);

        dataTransferSpeedPresenter
                .getConversionFromGigabytesPerSecond("2.8946231649761316296", 5);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bpsValue != null)
                && (dataTransferSpeedView.bypsValue != null)
                && (dataTransferSpeedView.kbpsValue != null)
                && (dataTransferSpeedView.kbypsValue != null)
                && (dataTransferSpeedView.mbpsValue != null)
                && (dataTransferSpeedView.mbypsValue != null)
                && (dataTransferSpeedView.gbpsValue != null)
                && (dataTransferSpeedView.tbpsValue != null)
                && (dataTransferSpeedView.tbypsValue != null)));

        assertEquals(false, dataTransferSpeedView.gbypsError);
        assertEquals("23156985319.80905", dataTransferSpeedView.bpsValue);
        assertEquals("2894623164.97613", dataTransferSpeedView.bypsValue);
        assertEquals("23156985.31981", dataTransferSpeedView.kbpsValue);
        assertEquals("2894623.16498", dataTransferSpeedView.kbypsValue);
        assertEquals("23156.98532", dataTransferSpeedView.mbpsValue);
        assertEquals("2894.62316", dataTransferSpeedView.mbypsValue);
        assertEquals("23.15699", dataTransferSpeedView.gbpsValue);
        assertEquals("0.02316", dataTransferSpeedView.tbpsValue);
        assertEquals("0.00289", dataTransferSpeedView.tbypsValue);

        dataTransferSpeedView.resetValues();
    }

    @Test
    public void testTerabitsPerSecondConversion() throws Exception {
        dataTransferSpeedPresenter
                .getConversionFromTerabitsPerSecond("0.59461326596492331649564319", 10);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bpsValue != null)
                && (dataTransferSpeedView.bypsValue != null)
                && (dataTransferSpeedView.kbpsValue != null)
                && (dataTransferSpeedView.kbypsValue != null)
                && (dataTransferSpeedView.mbpsValue != null)
                && (dataTransferSpeedView.mbypsValue != null)
                && (dataTransferSpeedView.gbpsValue != null)
                && (dataTransferSpeedView.gbypsValue != null)
                && (dataTransferSpeedView.tbypsValue != null)));

        assertEquals(false, dataTransferSpeedView.tbpsError);
        assertEquals("594613265964.9233164956", dataTransferSpeedView.bpsValue);
        assertEquals("74326658245.615414562", dataTransferSpeedView.bypsValue);
        assertEquals("594613265.9649233165", dataTransferSpeedView.kbpsValue);
        assertEquals("74326658.2456154146", dataTransferSpeedView.kbypsValue);
        assertEquals("594613.2659649233", dataTransferSpeedView.mbpsValue);
        assertEquals("74326.6582456154", dataTransferSpeedView.mbypsValue);
        assertEquals("594.6132659649", dataTransferSpeedView.gbpsValue);
        assertEquals("74.3266582456", dataTransferSpeedView.gbypsValue);
        assertEquals("0.0743266582", dataTransferSpeedView.tbypsValue);

        dataTransferSpeedPresenter
                .getConversionFromTerabitsPerSecond("0.59461326596492331649564319", 5);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bpsValue != null)
                && (dataTransferSpeedView.bypsValue != null)
                && (dataTransferSpeedView.kbpsValue != null)
                && (dataTransferSpeedView.kbypsValue != null)
                && (dataTransferSpeedView.mbpsValue != null)
                && (dataTransferSpeedView.mbypsValue != null)
                && (dataTransferSpeedView.gbpsValue != null)
                && (dataTransferSpeedView.gbypsValue != null)
                && (dataTransferSpeedView.tbypsValue != null)));

        assertEquals(false, dataTransferSpeedView.tbpsError);
        assertEquals("594613265964.92332", dataTransferSpeedView.bpsValue);
        assertEquals("74326658245.61541", dataTransferSpeedView.bypsValue);
        assertEquals("594613265.96492", dataTransferSpeedView.kbpsValue);
        assertEquals("74326658.24562", dataTransferSpeedView.kbypsValue);
        assertEquals("594613.26596", dataTransferSpeedView.mbpsValue);
        assertEquals("74326.65825", dataTransferSpeedView.mbypsValue);
        assertEquals("594.61327", dataTransferSpeedView.gbpsValue);
        assertEquals("74.32666", dataTransferSpeedView.gbypsValue);
        assertEquals("0.07433", dataTransferSpeedView.tbypsValue);

        dataTransferSpeedView.resetValues();
    }

    @Test
    public void testTerabytesPerSecondConversion() throws Exception {
        dataTransferSpeedPresenter
                .getConversionFromTerabytesPerSecond("0.0264976532649164376495623", 10);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bpsValue != null)
                && (dataTransferSpeedView.bypsValue != null)
                && (dataTransferSpeedView.kbpsValue != null)
                && (dataTransferSpeedView.kbypsValue != null)
                && (dataTransferSpeedView.mbpsValue != null)
                && (dataTransferSpeedView.mbypsValue != null)
                && (dataTransferSpeedView.gbpsValue != null)
                && (dataTransferSpeedView.gbypsValue != null)
                && (dataTransferSpeedView.tbpsValue != null)));

        assertEquals(false, dataTransferSpeedView.tbypsError);
        assertEquals("211981226119.3315011965", dataTransferSpeedView.bpsValue);
        assertEquals("26497653264.9164376496", dataTransferSpeedView.bypsValue);
        assertEquals("211981226.1193315012", dataTransferSpeedView.kbpsValue);
        assertEquals("26497653.2649164376", dataTransferSpeedView.kbypsValue);
        assertEquals("211981.2261193315", dataTransferSpeedView.mbpsValue);
        assertEquals("26497.6532649164", dataTransferSpeedView.mbypsValue);
        assertEquals("211.9812261193", dataTransferSpeedView.gbpsValue);
        assertEquals("26.4976532649", dataTransferSpeedView.gbypsValue);
        assertEquals("0.2119812261", dataTransferSpeedView.tbpsValue);

        dataTransferSpeedPresenter
                .getConversionFromTerabytesPerSecond("0.0264976532649164376495623", 5);
        TestHelper.waitFor(() -> ((dataTransferSpeedView.bpsValue != null)
                && (dataTransferSpeedView.bypsValue != null)
                && (dataTransferSpeedView.kbpsValue != null)
                && (dataTransferSpeedView.kbypsValue != null)
                && (dataTransferSpeedView.mbpsValue != null)
                && (dataTransferSpeedView.mbypsValue != null)
                && (dataTransferSpeedView.gbpsValue != null)
                && (dataTransferSpeedView.gbypsValue != null)
                && (dataTransferSpeedView.tbpsValue != null)));

        assertEquals(false, dataTransferSpeedView.tbypsError);
        assertEquals("211981226119.3315", dataTransferSpeedView.bpsValue);
        assertEquals("26497653264.91644", dataTransferSpeedView.bypsValue);
        assertEquals("211981226.11933", dataTransferSpeedView.kbpsValue);
        assertEquals("26497653.26492", dataTransferSpeedView.kbypsValue);
        assertEquals("211981.22612", dataTransferSpeedView.mbpsValue);
        assertEquals("26497.65326", dataTransferSpeedView.mbypsValue);
        assertEquals("211.98123", dataTransferSpeedView.gbpsValue);
        assertEquals("26.49765", dataTransferSpeedView.gbypsValue);
        assertEquals("0.21198", dataTransferSpeedView.tbpsValue);

        dataTransferSpeedView.resetValues();
    }
}
