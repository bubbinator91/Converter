package com.bubbinator91.converter.tests;

import com.bubbinator91.converter.tests.conversion.AllConversionTestsSuite;
import com.bubbinator91.converter.tests.main.MainSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all JUnit tests
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllConversionTestsSuite.class,
        MainSuite.class
})
public class AllTestsSuite {}
