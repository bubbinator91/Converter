package com.bubbinator91.converter.views.fragments;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.views.activities.TestActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;

/**
 * Runs some {@link Espresso} tests on the {@link LengthFragment}
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LengthFragmentTest {
    @Rule
    public ActivityTestRule<TestActivity> activityTestRule = new ActivityTestRule<>(TestActivity.class);

    private TestActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = activityTestRule.getActivity();
    }

    @Test
    public void convertToAllFromInches() throws Exception {
        activity.setDecimalPlaces(10);
        activity.loadFragment("Length");

        onView(withId(R.id.editText_length_inch)).perform(scrollTo(), clearText(), typeText("1.258633885023369824"));
        onView(withId(R.id.editText_length_foot)).check(matches(withText("0.1048861571")));
        onView(withId(R.id.editText_length_yard)).check(matches(withText("0.0349620524")));
        onView(withId(R.id.editText_length_mile)).check(matches(withText("0.0000198648")));
        onView(withId(R.id.editText_length_millimeter)).check(matches(withText("31.9693006796")));
        onView(withId(R.id.editText_length_centimeter)).check(matches(withText("3.196930068")));
        onView(withId(R.id.editText_length_meter)).check(matches(withText("0.0319693007")));
        onView(withId(R.id.editText_length_kilometer)).check(matches(withText("0.0000319693")));

        activity.setDecimalPlaces(5);
        activity.loadFragment("Length");

        onView(withId(R.id.editText_length_inch)).perform(scrollTo(), clearText(), typeText("1.258633885023369824"));
        onView(withId(R.id.editText_length_foot)).check(matches(withText("0.10489")));
        onView(withId(R.id.editText_length_yard)).check(matches(withText("0.03496")));
        onView(withId(R.id.editText_length_mile)).check(matches(withText("0.00002")));
        onView(withId(R.id.editText_length_millimeter)).check(matches(withText("31.9693")));
        onView(withId(R.id.editText_length_centimeter)).check(matches(withText("3.19693")));
        onView(withId(R.id.editText_length_meter)).check(matches(withText("0.03197")));
        onView(withId(R.id.editText_length_kilometer)).check(matches(withText("0.00003")));
    }

    @Test
    public void convertToAllFromFeet() throws Exception {
        activity.setDecimalPlaces(10);
        activity.loadFragment("Length");

        onView(withId(R.id.editText_length_foot)).perform(scrollTo(), clearText(), typeText("10.694673465926534"));
        onView(withId(R.id.editText_length_inch)).check(matches(withText("128.3360815911")));
        onView(withId(R.id.editText_length_yard)).check(matches(withText("3.5648911553")));
        onView(withId(R.id.editText_length_mile)).check(matches(withText("0.0020255063")));
        onView(withId(R.id.editText_length_millimeter)).check(matches(withText("3259.7364724144")));
        onView(withId(R.id.editText_length_centimeter)).check(matches(withText("325.9736472414")));
        onView(withId(R.id.editText_length_meter)).check(matches(withText("3.2597364724")));
        onView(withId(R.id.editText_length_kilometer)).check(matches(withText("0.0032597365")));

        activity.setDecimalPlaces(5);
        activity.loadFragment("Length");

        onView(withId(R.id.editText_length_foot)).perform(scrollTo(), clearText(), typeText("10.694673465926534"));
        onView(withId(R.id.editText_length_inch)).check(matches(withText("128.33608")));
        onView(withId(R.id.editText_length_yard)).check(matches(withText("3.56489")));
        onView(withId(R.id.editText_length_mile)).check(matches(withText("0.00203")));
        onView(withId(R.id.editText_length_millimeter)).check(matches(withText("3259.73647")));
        onView(withId(R.id.editText_length_centimeter)).check(matches(withText("325.97365")));
        onView(withId(R.id.editText_length_meter)).check(matches(withText("3.25974")));
        onView(withId(R.id.editText_length_kilometer)).check(matches(withText("0.00326")));
    }

    @Test
    public void convertToAllFromYards() throws Exception {
        activity.setDecimalPlaces(10);
        activity.loadFragment("Length");

        onView(withId(R.id.editText_length_yard)).perform(scrollTo(), clearText(), typeText("94.677446"));
        onView(withId(R.id.editText_length_inch)).check(matches(withText("3408.388056")));
        onView(withId(R.id.editText_length_foot)).check(matches(withText("284.032338")));
        onView(withId(R.id.editText_length_mile)).check(matches(withText("0.0537940034")));
        onView(withId(R.id.editText_length_millimeter)).check(matches(withText("86573.0566224")));
        onView(withId(R.id.editText_length_centimeter)).check(matches(withText("8657.30566224")));
        onView(withId(R.id.editText_length_meter)).check(matches(withText("86.5730566224")));
        onView(withId(R.id.editText_length_kilometer)).check(matches(withText("0.0865730566")));

        activity.setDecimalPlaces(5);
        activity.loadFragment("Length");

        onView(withId(R.id.editText_length_yard)).perform(scrollTo(), clearText(), typeText("94.677446"));
        onView(withId(R.id.editText_length_inch)).check(matches(withText("3408.38806")));
        onView(withId(R.id.editText_length_foot)).check(matches(withText("284.03234")));
        onView(withId(R.id.editText_length_mile)).check(matches(withText("0.05379")));
        onView(withId(R.id.editText_length_millimeter)).check(matches(withText("86573.05662")));
        onView(withId(R.id.editText_length_centimeter)).check(matches(withText("8657.30566")));
        onView(withId(R.id.editText_length_meter)).check(matches(withText("86.57306")));
        onView(withId(R.id.editText_length_kilometer)).check(matches(withText("0.08657")));
    }

    @Test
    public void convertToAllFromMiles() throws Exception {
        activity.setDecimalPlaces(10);
        activity.loadFragment("Length");

        onView(withId(R.id.editText_length_mile)).perform(scrollTo(), clearText(), typeText("8.569852369523695441288"));
        onView(withId(R.id.editText_length_inch)).check(matches(withText("542985.8461330213")));
        onView(withId(R.id.editText_length_foot)).check(matches(withText("45248.8205110851")));
        onView(withId(R.id.editText_length_yard)).check(matches(withText("15082.9401703617")));
        onView(withId(R.id.editText_length_millimeter)).check(matches(withText("13791840.4917787421")));
        onView(withId(R.id.editText_length_centimeter)).check(matches(withText("1379184.0491778742")));
        onView(withId(R.id.editText_length_meter)).check(matches(withText("13791.8404917787")));
        onView(withId(R.id.editText_length_kilometer)).check(matches(withText("13.7918404918")));

        activity.setDecimalPlaces(5);
        activity.loadFragment("Length");

        onView(withId(R.id.editText_length_mile)).perform(scrollTo(), clearText(), typeText("8.569852369523695441288"));
        onView(withId(R.id.editText_length_inch)).check(matches(withText("542985.84613")));
        onView(withId(R.id.editText_length_foot)).check(matches(withText("45248.82051")));
        onView(withId(R.id.editText_length_yard)).check(matches(withText("15082.94017")));
        onView(withId(R.id.editText_length_millimeter)).check(matches(withText("13791840.49178")));
        onView(withId(R.id.editText_length_centimeter)).check(matches(withText("1379184.04918")));
        onView(withId(R.id.editText_length_meter)).check(matches(withText("13791.84049")));
        onView(withId(R.id.editText_length_kilometer)).check(matches(withText("13.79184")));
    }

    @Test
    public void convertToAllFromMillimeters() throws Exception {
        activity.setDecimalPlaces(10);
        activity.loadFragment("Length");

        onView(withId(R.id.editText_length_millimeter)).perform(scrollTo(), clearText(), typeText("58.85236982258084"));
        onView(withId(R.id.editText_length_inch)).check(matches(withText("2.317022434")));
        onView(withId(R.id.editText_length_foot)).check(matches(withText("0.1930852028")));
        onView(withId(R.id.editText_length_yard)).check(matches(withText("0.0643617343")));
        onView(withId(R.id.editText_length_mile)).check(matches(withText("0.0000365692")));
        onView(withId(R.id.editText_length_centimeter)).check(matches(withText("5.8852369823")));
        onView(withId(R.id.editText_length_meter)).check(matches(withText("0.0588523698")));
        onView(withId(R.id.editText_length_kilometer)).check(matches(withText("0.0000588524")));

        activity.setDecimalPlaces(5);
        activity.loadFragment("Length");

        onView(withId(R.id.editText_length_millimeter)).perform(scrollTo(), clearText(), typeText("58.85236982258084"));
        onView(withId(R.id.editText_length_inch)).check(matches(withText("2.31702")));
        onView(withId(R.id.editText_length_foot)).check(matches(withText("0.19309")));
        onView(withId(R.id.editText_length_yard)).check(matches(withText("0.06436")));
        onView(withId(R.id.editText_length_mile)).check(matches(withText("0.00004")));
        onView(withId(R.id.editText_length_centimeter)).check(matches(withText("5.88524")));
        onView(withId(R.id.editText_length_meter)).check(matches(withText("0.05885")));
        onView(withId(R.id.editText_length_kilometer)).check(matches(withText("0.00006")));
    }

    @Test
    public void convertToAllFromCentimeters() throws Exception {
        activity.setDecimalPlaces(10);
        activity.loadFragment("Length");

        onView(withId(R.id.editText_length_centimeter)).perform(scrollTo(), clearText(), typeText("56943.98653164976"));
        onView(withId(R.id.editText_length_inch)).check(matches(withText("22418.8923352952")));
        onView(withId(R.id.editText_length_foot)).check(matches(withText("1868.2410279413")));
        onView(withId(R.id.editText_length_yard)).check(matches(withText("622.7470093138")));
        onView(withId(R.id.editText_length_mile)).check(matches(withText("0.353833528")));
        onView(withId(R.id.editText_length_millimeter)).check(matches(withText("569439.8653164976")));
        onView(withId(R.id.editText_length_meter)).check(matches(withText("569.4398653165")));
        onView(withId(R.id.editText_length_kilometer)).check(matches(withText("0.5694398653")));

        activity.setDecimalPlaces(5);
        activity.loadFragment("Length");

        onView(withId(R.id.editText_length_centimeter)).perform(scrollTo(), clearText(), typeText("56943.98653164976"));
        onView(withId(R.id.editText_length_inch)).check(matches(withText("22418.89234")));
        onView(withId(R.id.editText_length_foot)).check(matches(withText("1868.24103")));
        onView(withId(R.id.editText_length_yard)).check(matches(withText("622.74701")));
        onView(withId(R.id.editText_length_mile)).check(matches(withText("0.35383")));
        onView(withId(R.id.editText_length_millimeter)).check(matches(withText("569439.86532")));
        onView(withId(R.id.editText_length_meter)).check(matches(withText("569.43987")));
        onView(withId(R.id.editText_length_kilometer)).check(matches(withText("0.56944")));
    }

    @Test
    public void convertToAllFromMeters() throws Exception {
        activity.setDecimalPlaces(10);
        activity.loadFragment("Length");

        onView(withId(R.id.editText_length_meter)).perform(scrollTo(), clearText(), typeText("824.3642518597643169"));
        onView(withId(R.id.editText_length_inch)).check(matches(withText("32455.2855062899")));
        onView(withId(R.id.editText_length_foot)).check(matches(withText("2704.6071255242")));
        onView(withId(R.id.editText_length_yard)).check(matches(withText("901.5357085081")));
        onView(withId(R.id.editText_length_mile)).check(matches(withText("0.512236198")));
        onView(withId(R.id.editText_length_millimeter)).check(matches(withText("824364.2518597643")));
        onView(withId(R.id.editText_length_centimeter)).check(matches(withText("82436.4251859764")));
        onView(withId(R.id.editText_length_kilometer)).check(matches(withText("0.8243642519")));

        activity.setDecimalPlaces(5);
        activity.loadFragment("Length");

        onView(withId(R.id.editText_length_meter)).perform(scrollTo(), clearText(), typeText("824.3642518597643169"));
        onView(withId(R.id.editText_length_inch)).check(matches(withText("32455.28551")));
        onView(withId(R.id.editText_length_foot)).check(matches(withText("2704.60713")));
        onView(withId(R.id.editText_length_yard)).check(matches(withText("901.53571")));
        onView(withId(R.id.editText_length_mile)).check(matches(withText("0.51224")));
        onView(withId(R.id.editText_length_millimeter)).check(matches(withText("824364.25186")));
        onView(withId(R.id.editText_length_centimeter)).check(matches(withText("82436.42519")));
        onView(withId(R.id.editText_length_kilometer)).check(matches(withText("0.82436")));
    }

    @Test
    public void convertToAllFromKilometers() throws Exception {
        activity.setDecimalPlaces(10);
        activity.loadFragment("Length");

        onView(withId(R.id.editText_length_kilometer)).perform(scrollTo(), clearText(), typeText("8.5649764312526591"));
        onView(withId(R.id.editText_length_inch)).check(matches(withText("337203.7965060102")));
        onView(withId(R.id.editText_length_foot)).check(matches(withText("28100.3163755009")));
        onView(withId(R.id.editText_length_yard)).check(matches(withText("9366.772125167")));
        onView(withId(R.id.editText_length_mile)).check(matches(withText("5.3220296166")));
        onView(withId(R.id.editText_length_millimeter)).check(matches(withText("8564976.4312526591")));
        onView(withId(R.id.editText_length_centimeter)).check(matches(withText("856497.6431252659")));
        onView(withId(R.id.editText_length_meter)).check(matches(withText("8564.9764312527")));

        activity.setDecimalPlaces(5);
        activity.loadFragment("Length");

        onView(withId(R.id.editText_length_kilometer)).perform(scrollTo(), clearText(), typeText("8.5649764312526591"));
        onView(withId(R.id.editText_length_inch)).check(matches(withText("337203.79651")));
        onView(withId(R.id.editText_length_foot)).check(matches(withText("28100.31638")));
        onView(withId(R.id.editText_length_yard)).check(matches(withText("9366.77213")));
        onView(withId(R.id.editText_length_mile)).check(matches(withText("5.32203")));
        onView(withId(R.id.editText_length_millimeter)).check(matches(withText("8564976.43125")));
        onView(withId(R.id.editText_length_centimeter)).check(matches(withText("856497.64313")));
        onView(withId(R.id.editText_length_meter)).check(matches(withText("8564.97643")));
    }
}