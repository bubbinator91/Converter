package com.bubbinator91.converter.activities;

import android.support.v7.widget.Toolbar;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * Created by Christopher on 4/28/2015.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

	MainActivity activity;

	public MainActivityTest() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
	}

	@SmallTest
	public void testToolbarNotNull() {
		Toolbar toolbar = activity.getToolbar();
		assertNotNull(toolbar);
	}
}
