package com.bubbinator91.converter.fragments;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.activities.TestActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;

/**
 * Runs some {@link Espresso} tests on the {@link SpeedFragment}
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SpeedFragmentTest {
    @Rule
    public ActivityTestRule<TestActivity> mActivityTestRule = new ActivityTestRule<>(TestActivity.class);

    private TestActivity mActivity;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void convertToAllFromFps() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("Speed");

        onView(withId(R.id.editText_speed_fps)).perform(scrollTo(), typeText("4.3465986431"));
        onView(withId(R.id.editText_speed_knot)).check(matches(withText("2.5752892868")));
        onView(withId(R.id.editText_speed_kph)).check(matches(withText("4.7694357591")));
        onView(withId(R.id.editText_speed_mps)).check(matches(withText("1.3248432664")));
        onView(withId(R.id.editText_speed_mph)).check(matches(withText("2.9635899839")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("Speed");

        onView(withId(R.id.editText_speed_fps)).perform(scrollTo(), typeText("4.3465986431"));
        onView(withId(R.id.editText_speed_knot)).check(matches(withText("2.57529")));
        onView(withId(R.id.editText_speed_kph)).check(matches(withText("4.76944")));
        onView(withId(R.id.editText_speed_mps)).check(matches(withText("1.32484")));
        onView(withId(R.id.editText_speed_mph)).check(matches(withText("2.96359")));
    }

    @Test
    public void convertToAllFromKnot() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("Speed");

        onView(withId(R.id.editText_speed_knot)).perform(scrollTo(), typeText("3.465923564312"));
        onView(withId(R.id.editText_speed_fps)).check(matches(withText("5.8498199558")));
        onView(withId(R.id.editText_speed_kph)).check(matches(withText("6.4188904411")));
        onView(withId(R.id.editText_speed_mps)).check(matches(withText("1.7830251225")));
        onView(withId(R.id.editText_speed_mph)).check(matches(withText("3.9885136062")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("Speed");

        onView(withId(R.id.editText_speed_knot)).perform(scrollTo(), typeText("3.465923564312"));
        onView(withId(R.id.editText_speed_fps)).check(matches(withText("5.84982")));
        onView(withId(R.id.editText_speed_kph)).check(matches(withText("6.41889")));
        onView(withId(R.id.editText_speed_mps)).check(matches(withText("1.78303")));
        onView(withId(R.id.editText_speed_mph)).check(matches(withText("3.98851")));
    }

    @Test
    public void convertToAllFromKph() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("Speed");

        onView(withId(R.id.editText_speed_kph)).perform(scrollTo(), typeText("7.5806235947"));
        onView(withId(R.id.editText_speed_fps)).check(matches(withText("6.9085589774")));
        onView(withId(R.id.editText_speed_knot)).check(matches(withText("4.0932092844")));
        onView(withId(R.id.editText_speed_mps)).check(matches(withText("2.1057287763")));
        onView(withId(R.id.editText_speed_mph)).check(matches(withText("4.7103811209")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("Speed");

        onView(withId(R.id.editText_speed_kph)).perform(scrollTo(), typeText("7.5806235947"));
        onView(withId(R.id.editText_speed_fps)).check(matches(withText("6.90856")));
        onView(withId(R.id.editText_speed_knot)).check(matches(withText("4.09321")));
        onView(withId(R.id.editText_speed_mps)).check(matches(withText("2.10573")));
        onView(withId(R.id.editText_speed_mph)).check(matches(withText("4.71038")));
    }

    @Test
    public void convertToAllFromMps() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("Speed");

        onView(withId(R.id.editText_speed_mps)).perform(scrollTo(), typeText("2.894653261712"));
        onView(withId(R.id.editText_speed_fps)).check(matches(withText("9.4968939033")));
        onView(withId(R.id.editText_speed_knot)).check(matches(withText("5.6267558003")));
        onView(withId(R.id.editText_speed_kph)).check(matches(withText("10.4207517422")));
        onView(withId(R.id.editText_speed_mph)).check(matches(withText("6.475154934")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("Speed");

        onView(withId(R.id.editText_speed_mps)).perform(scrollTo(), typeText("2.894653261712"));
        onView(withId(R.id.editText_speed_fps)).check(matches(withText("9.49689")));
        onView(withId(R.id.editText_speed_knot)).check(matches(withText("5.62676")));
        onView(withId(R.id.editText_speed_kph)).check(matches(withText("10.42075")));
        onView(withId(R.id.editText_speed_mph)).check(matches(withText("6.47515")));
    }

    @Test
    public void convertToAllFromMph() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("Speed");

        onView(withId(R.id.editText_speed_mph)).perform(scrollTo(), typeText("5.346592653461"));
        onView(withId(R.id.editText_speed_fps)).check(matches(withText("7.8416692251")));
        onView(withId(R.id.editText_speed_knot)).check(matches(withText("4.646061991")));
        onView(withId(R.id.editText_speed_kph)).check(matches(withText("8.6045068073")));
        onView(withId(R.id.editText_speed_mps)).check(matches(withText("2.3901407798")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("Speed");

        onView(withId(R.id.editText_speed_mph)).perform(scrollTo(), typeText("5.346592653461"));
        onView(withId(R.id.editText_speed_fps)).check(matches(withText("7.84167")));
        onView(withId(R.id.editText_speed_knot)).check(matches(withText("4.64606")));
        onView(withId(R.id.editText_speed_kph)).check(matches(withText("8.60451")));
        onView(withId(R.id.editText_speed_mps)).check(matches(withText("2.39014")));
    }
}