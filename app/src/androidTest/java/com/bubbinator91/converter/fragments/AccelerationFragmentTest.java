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
 * Runs some {@link Espresso} tests on the {@link AccelerationFragment}
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class AccelerationFragmentTest {
    @Rule
    public ActivityTestRule<TestActivity> mActivityTestRule = new ActivityTestRule<>(TestActivity.class);

    private TestActivity mActivity;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void convertToAllFromCentimetersPerSecondSquared() {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment(0);

        onView(withId(R.id.editText_acceleration_cmpss)).perform(typeText("8.0235641526359876"));
        onView(withId(R.id.editText_acceleration_fpss)).check(matches(withText("0.2632402937")));
        onView(withId(R.id.editText_acceleration_mpss)).check(matches(withText("0.0802356415")));
        onView(withId(R.id.editText_acceleration_sg)).check(matches(withText("0.0081817585")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment(0);

        onView(withId(R.id.editText_acceleration_cmpss)).perform(typeText("8.0235641526359876"));
        onView(withId(R.id.editText_acceleration_fpss)).check(matches(withText("0.26324")));
        onView(withId(R.id.editText_acceleration_mpss)).check(matches(withText("0.08024")));
        onView(withId(R.id.editText_acceleration_sg)).check(matches(withText("0.00818")));
    }

    @Test
    public void convertToAllFromFeetPerSecondSquared() {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment(0);

        onView(withId(R.id.editText_acceleration_fpss)).perform(typeText("2.5064318986478525"));
        onView(withId(R.id.editText_acceleration_cmpss)).check(matches(withText("76.3960442711")));
        onView(withId(R.id.editText_acceleration_mpss)).check(matches(withText("0.7639604427")));
        onView(withId(R.id.editText_acceleration_sg)).check(matches(withText("0.077902285")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment(0);

        onView(withId(R.id.editText_acceleration_fpss)).perform(typeText("2.5064318986478525"));
        onView(withId(R.id.editText_acceleration_cmpss)).check(matches(withText("76.39604")));
        onView(withId(R.id.editText_acceleration_mpss)).check(matches(withText("0.76396")));
        onView(withId(R.id.editText_acceleration_sg)).check(matches(withText("0.0779")));
    }

    @Test
    public void convertToAllFromMetersPerSecondSquared() {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment(0);

        onView(withId(R.id.editText_acceleration_mpss)).perform(typeText("1.568946531647201"));
        onView(withId(R.id.editText_acceleration_cmpss)).check(matches(withText("156.8946531647")));
        onView(withId(R.id.editText_acceleration_fpss)).check(matches(withText("5.1474623742")));
        onView(withId(R.id.editText_acceleration_sg)).check(matches(withText("0.1599880216")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment(0);

        onView(withId(R.id.editText_acceleration_mpss)).perform(typeText("1.568946531647201"));
        onView(withId(R.id.editText_acceleration_cmpss)).check(matches(withText("156.89465")));
        onView(withId(R.id.editText_acceleration_fpss)).check(matches(withText("5.14746")));
        onView(withId(R.id.editText_acceleration_sg)).check(matches(withText("0.15999")));
    }

    @Test
    public void convertToAllFromStandardGravity() {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment(0);

        onView(withId(R.id.editText_acceleration_sg)).perform(typeText("0.564859764825136"));
        onView(withId(R.id.editText_acceleration_cmpss)).check(matches(withText("553.9382012603")));
        onView(withId(R.id.editText_acceleration_fpss)).check(matches(withText("18.1738255006")));
        onView(withId(R.id.editText_acceleration_mpss)).check(matches(withText("5.5393820126")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment(0);

        onView(withId(R.id.editText_acceleration_sg)).perform(typeText("0.564859764825136"));
        onView(withId(R.id.editText_acceleration_cmpss)).check(matches(withText("553.9382")));
        onView(withId(R.id.editText_acceleration_fpss)).check(matches(withText("18.17383")));
        onView(withId(R.id.editText_acceleration_mpss)).check(matches(withText("5.53938")));
    }
}