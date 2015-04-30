package com.bubbinator91.converter.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.Globals;
import com.bubbinator91.converter.util.Utils;

/**
 * The base fragment that all fragments should inherit from.
 * The purpose of this fragment to help reduce the amount of
 * duplicated code.
 */

// TODO Update to use global variables
// TODO Improve performance by using a second thread
public abstract class BaseFragment
		extends Fragment
		implements ViewTreeObserver.OnScrollChangedListener {
	private final String TAG = "BaseFragment";

	private Activity mActivity = null;
	private Handler mHandler = null;
	private SharedPreferences mPrefs = null;
	private int fieldLength = -1, lastY = 0;

	private View rootView = null;
	private ScrollView mScrollView = null;

	private boolean shouldHideToolbarOnScroll = true;
	private int mToolbarHeight = 0;
	private int mToolbarOffset = 0;

	// region Lifecycle methods

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (Utils.isDebugEnabled(activity.getApplicationContext())) {
			Log.d(TAG + "." + getChildTag() + ".onAttach", "Entered");
		}
		mActivity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (Utils.isDebugEnabled(mActivity.getApplicationContext())) {
			Log.d(TAG + "." + getChildTag() + ".onCreateView", "Entered");
		}
		rootView = inflater.inflate(getLayoutResource(), container, false);

		mHandler = mActivity.getWindow().getDecorView().getHandler();

		mPrefs = PreferenceManager.getDefaultSharedPreferences(mActivity.getApplicationContext());

		TypedArray actionBarAttrs = getActivity()
									.obtainStyledAttributes(new int[] {
																android.R.attr.actionBarSize
									});
		mToolbarHeight = ((int) actionBarAttrs.getDimension(0, 0) + 10);
		actionBarAttrs.recycle();

		mScrollView = ((ScrollView) rootView.findViewById(getScrollViewResource()));
		mScrollView.getViewTreeObserver().addOnScrollChangedListener(this);

		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();
		if (Utils.isDebugEnabled(mActivity.getApplicationContext())) {
			Log.d(TAG + "." + getChildTag() + ".onResume", "Entered");
		}

		if (mPrefs == null) {
			mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
		}
		if (mPrefs != null) {
			fieldLength = Integer.parseInt(mPrefs.getString(Globals.PREFERENCE_DECIMAL_PLACES, "-1"));
		}
		if (fieldLength == -1) {
			fieldLength = 8;
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (Utils.isDebugEnabled(mActivity.getApplicationContext())) {
			Log.d(TAG + "." + getChildTag() + ".onPause", "Entered");
		}

		Toolbar toolbar = ((Toolbar) mActivity.findViewById(R.id.toolbar));
		if (toolbar != null) {
			// Animate toolbar down if hidden
			while (mToolbarOffset > 0) {
				toolbar.animate().y(-mToolbarOffset);
				--mToolbarOffset;
			}
		}
	}

	// endregion

	// region Helper methods

	@Override
	public void onScrollChanged() {
		if (isAdded() && shouldHideToolbarOnScroll) {
			int dy = (mScrollView.getScrollY() - lastY);

			if (mToolbarOffset > mToolbarHeight) {
				mToolbarOffset = mToolbarHeight;
			} else if (mToolbarOffset < 0) {
				mToolbarOffset = 0;
			}

			try {
				Toolbar toolbar = ((Toolbar) mActivity.findViewById(R.id.toolbar));
				if (toolbar != null) {
					if (dy > 0) {
						while (mToolbarOffset < mToolbarHeight) {
							toolbar.animate().y(-mToolbarOffset);
							++mToolbarOffset;
						}
					} else if (dy < 0) {
						while (mToolbarOffset > 0) {
							toolbar.animate().y(-mToolbarOffset);
							--mToolbarOffset;
						}
					}
				}
			} catch (NullPointerException e) {
				if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
					Log.d(TAG + "." + getChildTag() +".onScrollChanged", "Toolbar is null\n" + e.toString());
				}
			}

			lastY = mScrollView.getScrollY();
		}
	}

	protected Activity getCurrentActivity() { return mActivity; }

	protected int getFieldLength() { return fieldLength; }

	protected Handler getHandler() { return mHandler; }

	protected View getRootView() { return rootView; }

	protected View getScrollView() { return mScrollView; }

	protected void setHandler(Handler handler) { mHandler = handler; }

	protected void setShouldHideToolbarOnScroll(boolean value) { shouldHideToolbarOnScroll = value; }

	// endregion

	// region Abstract methods

	protected abstract String getChildTag();
	protected abstract int getLayoutResource();
	protected abstract int getScrollViewResource();

	// endregion
}
