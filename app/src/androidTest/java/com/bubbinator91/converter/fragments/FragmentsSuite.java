package com.bubbinator91.converter.fragments;

import android.support.test.espresso.Espresso;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs some {@link Espresso} tests on all of the fragments
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccelerationFragmentTest.class,
        DataTransferSpeedFragmentTest.class,
        TemperatureFragmentTest.class
})
public class FragmentsSuite {}
