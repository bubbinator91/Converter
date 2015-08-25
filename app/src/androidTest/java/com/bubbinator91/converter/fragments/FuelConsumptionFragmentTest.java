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
 * Runs some {@link Espresso} tests on the {@link FuelConsumptionFragment}
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class FuelConsumptionFragmentTest {
    @Rule
    public ActivityTestRule<TestActivity> mActivityTestRule = new ActivityTestRule<>(TestActivity.class);

    private TestActivity mActivity;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void convertToAllFromUSMilesPerGallon() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("Fuel");

        onView(withId(R.id.editText_fuel_consumption_usmpg)).perform(scrollTo(), clearText(), typeText("2.15235645123659"));
        onView(withId(R.id.editText_fuel_consumption_ukmpg)).check(matches(withText("2.5848723198")));
        onView(withId(R.id.editText_fuel_consumption_kpl)).check(matches(withText("0.9150608015")));
        onView(withId(R.id.editText_fuel_consumption_l100k)).check(matches(withText("109.2823557168")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("Fuel");

        onView(withId(R.id.editText_fuel_consumption_usmpg)).perform(scrollTo(), clearText(), typeText("2.15235645123659"));
        onView(withId(R.id.editText_fuel_consumption_ukmpg)).check(matches(withText("2.58487")));
        onView(withId(R.id.editText_fuel_consumption_kpl)).check(matches(withText("0.91506")));
        onView(withId(R.id.editText_fuel_consumption_l100k)).check(matches(withText("109.28236")));
    }

    @Test
    public void convertToAllFromUKMilesPerGallon() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("Fuel");

        onView(withId(R.id.editText_fuel_consumption_ukmpg)).perform(scrollTo(), clearText(), typeText("3.6589456897564"));
        onView(withId(R.id.editText_fuel_consumption_usmpg)).check(matches(withText("3.0467096187")));
        onView(withId(R.id.editText_fuel_consumption_kpl)).check(matches(withText("1.295289423")));
        onView(withId(R.id.editText_fuel_consumption_l100k)).check(matches(withText("77.2028229669")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("Fuel");

        onView(withId(R.id.editText_fuel_consumption_ukmpg)).perform(scrollTo(), clearText(), typeText("3.6589456897564"));
        onView(withId(R.id.editText_fuel_consumption_usmpg)).check(matches(withText("3.04671")));
        onView(withId(R.id.editText_fuel_consumption_kpl)).check(matches(withText("1.29529")));
        onView(withId(R.id.editText_fuel_consumption_l100k)).check(matches(withText("77.20282")));
    }

    @Test
    public void convertToAllFromKilometersPerLiter() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("Fuel");

        onView(withId(R.id.editText_fuel_consumption_kpl)).perform(scrollTo(), clearText(), typeText("4.5613258749654"));
        onView(withId(R.id.editText_fuel_consumption_usmpg)).check(matches(withText("10.7289036498")));
        onView(withId(R.id.editText_fuel_consumption_ukmpg)).check(matches(withText("12.8848760393")));
        onView(withId(R.id.editText_fuel_consumption_l100k)).check(matches(withText("21.923450054")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("Fuel");

        onView(withId(R.id.editText_fuel_consumption_kpl)).perform(scrollTo(), clearText(), typeText("4.5613258749654"));
        onView(withId(R.id.editText_fuel_consumption_usmpg)).check(matches(withText("10.7289")));
        onView(withId(R.id.editText_fuel_consumption_ukmpg)).check(matches(withText("12.88488")));
        onView(withId(R.id.editText_fuel_consumption_l100k)).check(matches(withText("21.92345")));
    }

    @Test
    public void convertToAllFromLitersPer100Kilometers() throws Exception {
        mActivity.setDecimalPlaces(10);
        mActivity.loadFragment("Fuel");

        onView(withId(R.id.editText_fuel_consumption_l100k)).perform(scrollTo(), clearText(), typeText("8.56498658974125"));
        onView(withId(R.id.editText_fuel_consumption_usmpg)).check(matches(withText("27.4623411104")));
        onView(withId(R.id.editText_fuel_consumption_ukmpg)).check(matches(withText("32.9808965107")));
        onView(withId(R.id.editText_fuel_consumption_kpl)).check(matches(withText("11.6754415144")));

        mActivity.setDecimalPlaces(5);
        mActivity.loadFragment("Fuel");

        onView(withId(R.id.editText_fuel_consumption_l100k)).perform(scrollTo(), clearText(), typeText("8.56498658974125"));
        onView(withId(R.id.editText_fuel_consumption_usmpg)).check(matches(withText("27.46234")));
        onView(withId(R.id.editText_fuel_consumption_ukmpg)).check(matches(withText("32.9809")));
        onView(withId(R.id.editText_fuel_consumption_kpl)).check(matches(withText("11.67544")));
    }
}