package com.bubbinator91.converter.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.test.ActivityInstrumentationTestCase2;
import android.text.Editable;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.fragments.DataTransferSpeedFragment;

/**
 * Created by Christopher on 4/28/2015.
 */
public class TestFragmentsActivityTest extends ActivityInstrumentationTestCase2<TestFragmentsActivity> {
	private TestFragmentsActivity mActivity;

	public TestFragmentsActivityTest() {
		super(TestFragmentsActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mActivity = getActivity();
	}

	// TODO Write test cases for the DataTransferSpeedFragment
	public void testDataTransferSpeedFragment() {
		DataTransferSpeedFragment dataTransferSpeedFragment = new DataTransferSpeedFragment() {
			@Override
			protected void afterTextChangedBps(Editable editableBps) {
				super.afterTextChangedBps(editableBps);
			}
		};

		Fragment fragment = startFragment(dataTransferSpeedFragment);
	}

	private Fragment startFragment(Fragment fragment) {
		FragmentTransaction fragmentTransaction = mActivity.getFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.test_fragment_container, fragment, "tag");
		fragmentTransaction.commit();
		getInstrumentation().waitForIdleSync();
		return mActivity.getFragmentManager().findFragmentByTag("tag");
	}
}
