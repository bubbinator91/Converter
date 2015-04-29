package com.bubbinator91.converter.activities;

import android.os.Bundle;

import com.bubbinator91.converter.R;

/**
 * An activity created to integrate with the testing framework
 * to facilitate the testing of fragments.
 */
public class TestFragmentsActivity extends BaseActivity {
	private final String TAG = "TestFragmentsActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected int getLayoutResourceId() { return R.layout.activity_test_fragments; }

	@Override
	protected String getChildTag() { return TAG; }
}
