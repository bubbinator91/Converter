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
 * Runs some {@link Espresso} tests on the {@link TemperatureFragment}
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TemperatureFragmentTest {
    @Rule
    public ActivityTestRule<TestActivity> mActivityTestRule = new ActivityTestRule<>(TestActivity.class);

    private TestActivity mActivity;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void convertToAllFromCelsius() {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("Temperature");

        onView(withId(R.id.editText_temperature_celsius)).perform(scrollTo(), clearText(), typeText("20.9764956236"));
        onView(withId(R.id.editText_temperature_fahrenheit)).check(matches(withText("69.7576921225")));
        onView(withId(R.id.editText_temperature_kelvin)).check(matches(withText("294.1264956236")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("Temperature");

        onView(withId(R.id.editText_temperature_celsius)).perform(scrollTo(), clearText(), typeText("20.9764956236"));
        onView(withId(R.id.editText_temperature_fahrenheit)).check(matches(withText("69.75769")));
        onView(withId(R.id.editText_temperature_kelvin)).check(matches(withText("294.1265")));
    }

    @Test
    public void convertToAllFromFahrenheit() {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("Temperature");

        onView(withId(R.id.editText_temperature_fahrenheit)).perform(scrollTo(), clearText(), typeText("80.9467316594316"));
        onView(withId(R.id.editText_temperature_celsius)).check(matches(withText("27.1926286997")));
        onView(withId(R.id.editText_temperature_kelvin)).check(matches(withText("300.3426286997")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("Temperature");

        onView(withId(R.id.editText_temperature_fahrenheit)).perform(scrollTo(), clearText(), typeText("80.9467316594316"));
        onView(withId(R.id.editText_temperature_celsius)).check(matches(withText("27.19263")));
        onView(withId(R.id.editText_temperature_kelvin)).check(matches(withText("300.34263")));
    }

    @Test
    public void convertToAllFromKelvin() {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("Temperature");

        onView(withId(R.id.editText_temperature_kelvin)).perform(scrollTo(), clearText(), typeText("310.9764326946"));
        onView(withId(R.id.editText_temperature_celsius)).check(matches(withText("37.8264326946")));
        onView(withId(R.id.editText_temperature_fahrenheit)).check(matches(withText("100.0875788503")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("Temperature");

        onView(withId(R.id.editText_temperature_kelvin)).perform(scrollTo(), clearText(), typeText("310.9764326946"));
        onView(withId(R.id.editText_temperature_celsius)).check(matches(withText("37.82643")));
        onView(withId(R.id.editText_temperature_fahrenheit)).check(matches(withText("100.08758")));
    }
}