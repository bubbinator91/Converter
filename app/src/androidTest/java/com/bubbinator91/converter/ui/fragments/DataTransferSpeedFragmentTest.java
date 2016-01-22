package com.bubbinator91.converter.ui.fragments;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.ui.activities.TestActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;

/**
 * Runs some {@link Espresso} tests on the {@link DataTransferSpeedFragment}
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class DataTransferSpeedFragmentTest {
    @Rule
    public ActivityTestRule<TestActivity> mActivityTestRule = new ActivityTestRule<>(TestActivity.class);

    private TestActivity mActivity;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void convertToAllFromBitsPerSecond() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_bps)).perform(scrollTo(), clearText(), typeText("8000000000.9764326598653"));
        onView(withId(R.id.editText_data_transfer_speed_byps)).check(matches(withText("1000000000.1220540825")));
        onView(withId(R.id.editText_data_transfer_speed_kbps)).check(matches(withText("8000000.0009764327")));
        onView(withId(R.id.editText_data_transfer_speed_kbyps)).check(matches(withText("1000000.0001220541")));
        onView(withId(R.id.editText_data_transfer_speed_mbps)).check(matches(withText("8000.0000009764")));
        onView(withId(R.id.editText_data_transfer_speed_mbyps)).check(matches(withText("1000.0000001221")));
        onView(withId(R.id.editText_data_transfer_speed_gbps)).check(matches(withText("8.000000001")));
        onView(withId(R.id.editText_data_transfer_speed_gbyps)).check(matches(withText("1.0000000001")));
        onView(withId(R.id.editText_data_transfer_speed_tbps)).check(matches(withText("0.008")));
        onView(withId(R.id.editText_data_transfer_speed_tbyps)).check(matches(withText("0.001")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_bps)).perform(scrollTo(), clearText(), typeText("8000000000.9764326598653"));
        onView(withId(R.id.editText_data_transfer_speed_byps)).check(matches(withText("1000000000.12205")));
        onView(withId(R.id.editText_data_transfer_speed_kbps)).check(matches(withText("8000000.00098")));
        onView(withId(R.id.editText_data_transfer_speed_kbyps)).check(matches(withText("1000000.00012")));
        onView(withId(R.id.editText_data_transfer_speed_mbps)).check(matches(withText("8000")));
        onView(withId(R.id.editText_data_transfer_speed_mbyps)).check(matches(withText("1000")));
        onView(withId(R.id.editText_data_transfer_speed_gbps)).check(matches(withText("8")));
        onView(withId(R.id.editText_data_transfer_speed_gbyps)).check(matches(withText("1")));
        onView(withId(R.id.editText_data_transfer_speed_tbps)).check(matches(withText("0.008")));
        onView(withId(R.id.editText_data_transfer_speed_tbyps)).check(matches(withText("0.001")));
    }

    @Test
    public void convertToAllFromBytesPerSecond() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_byps)).perform(scrollTo(), clearText(), typeText("1000000009.764953261346"));
        onView(withId(R.id.editText_data_transfer_speed_bps)).check(matches(withText("8000000078.1196260908")));
        onView(withId(R.id.editText_data_transfer_speed_kbps)).check(matches(withText("8000000.0781196261")));
        onView(withId(R.id.editText_data_transfer_speed_kbyps)).check(matches(withText("1000000.0097649533")));
        onView(withId(R.id.editText_data_transfer_speed_mbps)).check(matches(withText("8000.0000781196")));
        onView(withId(R.id.editText_data_transfer_speed_mbyps)).check(matches(withText("1000.000009765")));
        onView(withId(R.id.editText_data_transfer_speed_gbps)).check(matches(withText("8.0000000781")));
        onView(withId(R.id.editText_data_transfer_speed_gbyps)).check(matches(withText("1.0000000098")));
        onView(withId(R.id.editText_data_transfer_speed_tbps)).check(matches(withText("0.0080000001")));
        onView(withId(R.id.editText_data_transfer_speed_tbyps)).check(matches(withText("0.001")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_byps)).perform(scrollTo(), clearText(), typeText("1000000009.764953261346"));
        onView(withId(R.id.editText_data_transfer_speed_bps)).check(matches(withText("8000000078.11963")));
        onView(withId(R.id.editText_data_transfer_speed_kbps)).check(matches(withText("8000000.07812")));
        onView(withId(R.id.editText_data_transfer_speed_kbyps)).check(matches(withText("1000000.00976")));
        onView(withId(R.id.editText_data_transfer_speed_mbps)).check(matches(withText("8000.00008")));
        onView(withId(R.id.editText_data_transfer_speed_mbyps)).check(matches(withText("1000.00001")));
        onView(withId(R.id.editText_data_transfer_speed_gbps)).check(matches(withText("8")));
        onView(withId(R.id.editText_data_transfer_speed_gbyps)).check(matches(withText("1")));
        onView(withId(R.id.editText_data_transfer_speed_tbps)).check(matches(withText("0.008")));
        onView(withId(R.id.editText_data_transfer_speed_tbyps)).check(matches(withText("0.001")));
    }

    @Test
    public void convertToAllFromKilobitsPerSecond() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_kbps)).perform(scrollTo(), clearText(), typeText("8000008.9764953164976"));
        onView(withId(R.id.editText_data_transfer_speed_bps)).check(matches(withText("8000008976.4953164976")));
        onView(withId(R.id.editText_data_transfer_speed_byps)).check(matches(withText("1000001122.0619145622")));
        onView(withId(R.id.editText_data_transfer_speed_kbyps)).check(matches(withText("1000001.1220619146")));
        onView(withId(R.id.editText_data_transfer_speed_mbps)).check(matches(withText("8000.0089764953")));
        onView(withId(R.id.editText_data_transfer_speed_mbyps)).check(matches(withText("1000.0011220619")));
        onView(withId(R.id.editText_data_transfer_speed_gbps)).check(matches(withText("8.0000089765")));
        onView(withId(R.id.editText_data_transfer_speed_gbyps)).check(matches(withText("1.0000011221")));
        onView(withId(R.id.editText_data_transfer_speed_tbps)).check(matches(withText("0.008000009")));
        onView(withId(R.id.editText_data_transfer_speed_tbyps)).check(matches(withText("0.0010000011")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_kbps)).perform(scrollTo(), clearText(), typeText("8000008.9764953164976"));
        onView(withId(R.id.editText_data_transfer_speed_bps)).check(matches(withText("8000008976.49532")));
        onView(withId(R.id.editText_data_transfer_speed_byps)).check(matches(withText("1000001122.06191")));
        onView(withId(R.id.editText_data_transfer_speed_kbyps)).check(matches(withText("1000001.12206")));
        onView(withId(R.id.editText_data_transfer_speed_mbps)).check(matches(withText("8000.00898")));
        onView(withId(R.id.editText_data_transfer_speed_mbyps)).check(matches(withText("1000.00112")));
        onView(withId(R.id.editText_data_transfer_speed_gbps)).check(matches(withText("8.00001")));
        onView(withId(R.id.editText_data_transfer_speed_gbyps)).check(matches(withText("1")));
        onView(withId(R.id.editText_data_transfer_speed_tbps)).check(matches(withText("0.008")));
        onView(withId(R.id.editText_data_transfer_speed_tbyps)).check(matches(withText("0.001")));
    }

    @Test
    public void convertToAllFromKilobytesPerSecond() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_kbyps)).perform(scrollTo(), clearText(), typeText("1000001.122061915316"));
        onView(withId(R.id.editText_data_transfer_speed_bps)).check(matches(withText("8000008976.495322528")));
        onView(withId(R.id.editText_data_transfer_speed_byps)).check(matches(withText("1000001122.061915316")));
        onView(withId(R.id.editText_data_transfer_speed_kbps)).check(matches(withText("8000008.9764953225")));
        onView(withId(R.id.editText_data_transfer_speed_mbps)).check(matches(withText("8000.0089764953")));
        onView(withId(R.id.editText_data_transfer_speed_mbyps)).check(matches(withText("1000.0011220619")));
        onView(withId(R.id.editText_data_transfer_speed_gbps)).check(matches(withText("8.0000089765")));
        onView(withId(R.id.editText_data_transfer_speed_gbyps)).check(matches(withText("1.0000011221")));
        onView(withId(R.id.editText_data_transfer_speed_tbps)).check(matches(withText("0.008000009")));
        onView(withId(R.id.editText_data_transfer_speed_tbyps)).check(matches(withText("0.0010000011")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_kbyps)).perform(scrollTo(), clearText(), typeText("1000001.122061915316"));
        onView(withId(R.id.editText_data_transfer_speed_bps)).check(matches(withText("8000008976.49532")));
        onView(withId(R.id.editText_data_transfer_speed_byps)).check(matches(withText("1000001122.06192")));
        onView(withId(R.id.editText_data_transfer_speed_kbps)).check(matches(withText("8000008.9765")));
        onView(withId(R.id.editText_data_transfer_speed_mbps)).check(matches(withText("8000.00898")));
        onView(withId(R.id.editText_data_transfer_speed_mbyps)).check(matches(withText("1000.00112")));
        onView(withId(R.id.editText_data_transfer_speed_gbps)).check(matches(withText("8.00001")));
        onView(withId(R.id.editText_data_transfer_speed_gbyps)).check(matches(withText("1")));
        onView(withId(R.id.editText_data_transfer_speed_tbps)).check(matches(withText("0.008")));
        onView(withId(R.id.editText_data_transfer_speed_tbyps)).check(matches(withText("0.001")));
    }

    @Test
    public void convertToAllFromMegabitsPerSecond() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_mbps)).perform(scrollTo(), clearText(), typeText("8009.976435623164"));
        onView(withId(R.id.editText_data_transfer_speed_bps)).check(matches(withText("8009976435.623164")));
        onView(withId(R.id.editText_data_transfer_speed_byps)).check(matches(withText("1001247054.4528955")));
        onView(withId(R.id.editText_data_transfer_speed_kbps)).check(matches(withText("8009976.435623164")));
        onView(withId(R.id.editText_data_transfer_speed_kbyps)).check(matches(withText("1001247.0544528955")));
        onView(withId(R.id.editText_data_transfer_speed_mbyps)).check(matches(withText("1001.2470544529")));
        onView(withId(R.id.editText_data_transfer_speed_gbps)).check(matches(withText("8.0099764356")));
        onView(withId(R.id.editText_data_transfer_speed_gbyps)).check(matches(withText("1.0012470545")));
        onView(withId(R.id.editText_data_transfer_speed_tbps)).check(matches(withText("0.0080099764")));
        onView(withId(R.id.editText_data_transfer_speed_tbyps)).check(matches(withText("0.0010012471")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_mbps)).perform(scrollTo(), clearText(), typeText("8009.976435623164"));
        onView(withId(R.id.editText_data_transfer_speed_bps)).check(matches(withText("8009976435.62316")));
        onView(withId(R.id.editText_data_transfer_speed_byps)).check(matches(withText("1001247054.4529")));
        onView(withId(R.id.editText_data_transfer_speed_kbps)).check(matches(withText("8009976.43562")));
        onView(withId(R.id.editText_data_transfer_speed_kbyps)).check(matches(withText("1001247.05445")));
        onView(withId(R.id.editText_data_transfer_speed_mbyps)).check(matches(withText("1001.24705")));
        onView(withId(R.id.editText_data_transfer_speed_gbps)).check(matches(withText("8.00998")));
        onView(withId(R.id.editText_data_transfer_speed_gbyps)).check(matches(withText("1.00125")));
        onView(withId(R.id.editText_data_transfer_speed_tbps)).check(matches(withText("0.00801")));
        onView(withId(R.id.editText_data_transfer_speed_tbyps)).check(matches(withText("0.001")));
    }

    @Test
    public void convertToAllFromMegabytesPerSecond() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_mbyps)).perform(scrollTo(), clearText(), typeText("1005.9764326594613"));
        onView(withId(R.id.editText_data_transfer_speed_bps)).check(matches(withText("8047811461.2756904")));
        onView(withId(R.id.editText_data_transfer_speed_byps)).check(matches(withText("1005976432.6594613")));
        onView(withId(R.id.editText_data_transfer_speed_kbps)).check(matches(withText("8047811.4612756904")));
        onView(withId(R.id.editText_data_transfer_speed_kbyps)).check(matches(withText("1005976.4326594613")));
        onView(withId(R.id.editText_data_transfer_speed_mbps)).check(matches(withText("8047.8114612757")));
        onView(withId(R.id.editText_data_transfer_speed_gbps)).check(matches(withText("8.0478114613")));
        onView(withId(R.id.editText_data_transfer_speed_gbyps)).check(matches(withText("1.0059764327")));
        onView(withId(R.id.editText_data_transfer_speed_tbps)).check(matches(withText("0.0080478115")));
        onView(withId(R.id.editText_data_transfer_speed_tbyps)).check(matches(withText("0.0010059764")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_mbyps)).perform(scrollTo(), clearText(), typeText("1005.9764326594613"));
        onView(withId(R.id.editText_data_transfer_speed_bps)).check(matches(withText("8047811461.27569")));
        onView(withId(R.id.editText_data_transfer_speed_byps)).check(matches(withText("1005976432.65946")));
        onView(withId(R.id.editText_data_transfer_speed_kbps)).check(matches(withText("8047811.46128")));
        onView(withId(R.id.editText_data_transfer_speed_kbyps)).check(matches(withText("1005976.43266")));
        onView(withId(R.id.editText_data_transfer_speed_mbps)).check(matches(withText("8047.81146")));
        onView(withId(R.id.editText_data_transfer_speed_gbps)).check(matches(withText("8.04781")));
        onView(withId(R.id.editText_data_transfer_speed_gbyps)).check(matches(withText("1.00598")));
        onView(withId(R.id.editText_data_transfer_speed_tbps)).check(matches(withText("0.00805")));
        onView(withId(R.id.editText_data_transfer_speed_tbyps)).check(matches(withText("0.00101")));
    }

    @Test
    public void convertToAllFromGigabitsPerSecond() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_gbps)).perform(scrollTo(), clearText(), typeText("8.946731926437619"));
        onView(withId(R.id.editText_data_transfer_speed_bps)).check(matches(withText("8946731926.437619")));
        onView(withId(R.id.editText_data_transfer_speed_byps)).check(matches(withText("1118341490.804702375")));
        onView(withId(R.id.editText_data_transfer_speed_kbps)).check(matches(withText("8946731.926437619")));
        onView(withId(R.id.editText_data_transfer_speed_kbyps)).check(matches(withText("1118341.4908047024")));
        onView(withId(R.id.editText_data_transfer_speed_mbps)).check(matches(withText("8946.7319264376")));
        onView(withId(R.id.editText_data_transfer_speed_mbyps)).check(matches(withText("1118.3414908047")));
        onView(withId(R.id.editText_data_transfer_speed_gbyps)).check(matches(withText("1.1183414908")));
        onView(withId(R.id.editText_data_transfer_speed_tbps)).check(matches(withText("0.0089467319")));
        onView(withId(R.id.editText_data_transfer_speed_tbyps)).check(matches(withText("0.0011183415")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_gbps)).perform(scrollTo(), clearText(), typeText("8.946731926437619"));
        onView(withId(R.id.editText_data_transfer_speed_bps)).check(matches(withText("8946731926.43762")));
        onView(withId(R.id.editText_data_transfer_speed_byps)).check(matches(withText("1118341490.8047")));
        onView(withId(R.id.editText_data_transfer_speed_kbps)).check(matches(withText("8946731.92644")));
        onView(withId(R.id.editText_data_transfer_speed_kbyps)).check(matches(withText("1118341.4908")));
        onView(withId(R.id.editText_data_transfer_speed_mbps)).check(matches(withText("8946.73193")));
        onView(withId(R.id.editText_data_transfer_speed_mbyps)).check(matches(withText("1118.34149")));
        onView(withId(R.id.editText_data_transfer_speed_gbyps)).check(matches(withText("1.11834")));
        onView(withId(R.id.editText_data_transfer_speed_tbps)).check(matches(withText("0.00895")));
        onView(withId(R.id.editText_data_transfer_speed_tbyps)).check(matches(withText("0.00112")));
    }

    @Test
    public void convertToAllFromGigabytesPerSecond() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_gbyps)).perform(scrollTo(), clearText(), typeText("2.8946231649761316296"));
        onView(withId(R.id.editText_data_transfer_speed_bps)).check(matches(withText("23156985319.8090530368")));
        onView(withId(R.id.editText_data_transfer_speed_byps)).check(matches(withText("2894623164.9761316296")));
        onView(withId(R.id.editText_data_transfer_speed_kbps)).check(matches(withText("23156985.319809053")));
        onView(withId(R.id.editText_data_transfer_speed_kbyps)).check(matches(withText("2894623.1649761316")));
        onView(withId(R.id.editText_data_transfer_speed_mbps)).check(matches(withText("23156.9853198091")));
        onView(withId(R.id.editText_data_transfer_speed_mbyps)).check(matches(withText("2894.6231649761")));
        onView(withId(R.id.editText_data_transfer_speed_gbps)).check(matches(withText("23.1569853198")));
        onView(withId(R.id.editText_data_transfer_speed_tbps)).check(matches(withText("0.0231569853")));
        onView(withId(R.id.editText_data_transfer_speed_tbyps)).check(matches(withText("0.0028946232")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_gbyps)).perform(scrollTo(), clearText(), typeText("2.8946231649761316296"));
        onView(withId(R.id.editText_data_transfer_speed_bps)).check(matches(withText("23156985319.80905")));
        onView(withId(R.id.editText_data_transfer_speed_byps)).check(matches(withText("2894623164.97613")));
        onView(withId(R.id.editText_data_transfer_speed_kbps)).check(matches(withText("23156985.31981")));
        onView(withId(R.id.editText_data_transfer_speed_kbyps)).check(matches(withText("2894623.16498")));
        onView(withId(R.id.editText_data_transfer_speed_mbps)).check(matches(withText("23156.98532")));
        onView(withId(R.id.editText_data_transfer_speed_mbyps)).check(matches(withText("2894.62316")));
        onView(withId(R.id.editText_data_transfer_speed_gbps)).check(matches(withText("23.15699")));
        onView(withId(R.id.editText_data_transfer_speed_tbps)).check(matches(withText("0.02316")));
        onView(withId(R.id.editText_data_transfer_speed_tbyps)).check(matches(withText("0.00289")));
    }

    @Test
    public void convertToAllFromTerabitsPerSecond() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_tbps)).perform(scrollTo(), clearText(), typeText("0.59461326596492331649564319"));
        onView(withId(R.id.editText_data_transfer_speed_bps)).check(matches(withText("594613265964.9233164956")));
        onView(withId(R.id.editText_data_transfer_speed_byps)).check(matches(withText("74326658245.615414562")));
        onView(withId(R.id.editText_data_transfer_speed_kbps)).check(matches(withText("594613265.9649233165")));
        onView(withId(R.id.editText_data_transfer_speed_kbyps)).check(matches(withText("74326658.2456154146")));
        onView(withId(R.id.editText_data_transfer_speed_mbps)).check(matches(withText("594613.2659649233")));
        onView(withId(R.id.editText_data_transfer_speed_mbyps)).check(matches(withText("74326.6582456154")));
        onView(withId(R.id.editText_data_transfer_speed_gbps)).check(matches(withText("594.6132659649")));
        onView(withId(R.id.editText_data_transfer_speed_gbyps)).check(matches(withText("74.3266582456")));
        onView(withId(R.id.editText_data_transfer_speed_tbyps)).check(matches(withText("0.0743266582")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_tbps)).perform(scrollTo(), clearText(), typeText("0.59461326596492331649564319"));
        onView(withId(R.id.editText_data_transfer_speed_bps)).check(matches(withText("594613265964.92332")));
        onView(withId(R.id.editText_data_transfer_speed_byps)).check(matches(withText("74326658245.61541")));
        onView(withId(R.id.editText_data_transfer_speed_kbps)).check(matches(withText("594613265.96492")));
        onView(withId(R.id.editText_data_transfer_speed_kbyps)).check(matches(withText("74326658.24562")));
        onView(withId(R.id.editText_data_transfer_speed_mbps)).check(matches(withText("594613.26596")));
        onView(withId(R.id.editText_data_transfer_speed_mbyps)).check(matches(withText("74326.65825")));
        onView(withId(R.id.editText_data_transfer_speed_gbps)).check(matches(withText("594.61327")));
        onView(withId(R.id.editText_data_transfer_speed_gbyps)).check(matches(withText("74.32666")));
        onView(withId(R.id.editText_data_transfer_speed_tbyps)).check(matches(withText("0.07433")));
    }

    @Test
    public void convertToAllFromTerabytesPerSecond() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_tbyps)).perform(scrollTo(), clearText(), typeText("0.0264976532649164376495623"));
        onView(withId(R.id.editText_data_transfer_speed_bps)).check(matches(withText("211981226119.3315011965")));
        onView(withId(R.id.editText_data_transfer_speed_byps)).check(matches(withText("26497653264.9164376496")));
        onView(withId(R.id.editText_data_transfer_speed_kbps)).check(matches(withText("211981226.1193315012")));
        onView(withId(R.id.editText_data_transfer_speed_kbyps)).check(matches(withText("26497653.2649164376")));
        onView(withId(R.id.editText_data_transfer_speed_mbps)).check(matches(withText("211981.2261193315")));
        onView(withId(R.id.editText_data_transfer_speed_mbyps)).check(matches(withText("26497.6532649164")));
        onView(withId(R.id.editText_data_transfer_speed_gbps)).check(matches(withText("211.9812261193")));
        onView(withId(R.id.editText_data_transfer_speed_gbyps)).check(matches(withText("26.4976532649")));
        onView(withId(R.id.editText_data_transfer_speed_tbps)).check(matches(withText("0.2119812261")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("DTS");

        onView(withId(R.id.editText_data_transfer_speed_tbyps)).perform(scrollTo(), clearText(), typeText("0.0264976532649164376495623"));
        onView(withId(R.id.editText_data_transfer_speed_bps)).check(matches(withText("211981226119.3315")));
        onView(withId(R.id.editText_data_transfer_speed_byps)).check(matches(withText("26497653264.91644")));
        onView(withId(R.id.editText_data_transfer_speed_kbps)).check(matches(withText("211981226.11933")));
        onView(withId(R.id.editText_data_transfer_speed_kbyps)).check(matches(withText("26497653.26492")));
        onView(withId(R.id.editText_data_transfer_speed_mbps)).check(matches(withText("211981.22612")));
        onView(withId(R.id.editText_data_transfer_speed_mbyps)).check(matches(withText("26497.65326")));
        onView(withId(R.id.editText_data_transfer_speed_gbps)).check(matches(withText("211.98123")));
        onView(withId(R.id.editText_data_transfer_speed_gbyps)).check(matches(withText("26.49765")));
        onView(withId(R.id.editText_data_transfer_speed_tbps)).check(matches(withText("0.21198")));
    }

}