package com.bubbinator91.converter.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.Utils;

/**
 * Created by Christopher on 4/25/2015.
 */
// TODO replace all calls to getActivity() with mActivity
public abstract class BaseFragment extends Fragment implements ViewTreeObserver.OnScrollChangedListener {
	private final String TAG = "BaseFragment";

	protected Activity mActivity;
	protected SharedPreferences mPrefs;
	protected int fieldLength = -1, lastY = 0;
	protected View rootView;
	protected ScrollView mScrollView;

	protected boolean shouldHideToolbarOnScroll = true;
	private int mToolbarHeight;
	private int mToolbarOffset = 0;

	@Override
	public void onAttach(Activity activity) {
		if (Utils.isDebugEnabled(activity.getApplicationContext())) {
			Log.d(TAG + ".onAttach", "Entered");
		}
		super.onAttach(activity);
		mActivity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (Utils.isDebugEnabled(mActivity.getApplicationContext())) {
			Log.d(TAG + ".onCreateView", "Entered");
		}
		rootView = inflater.inflate(getLayoutResource(), container, false);

		mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

		TypedArray actionBarAttrs = getActivity().obtainStyledAttributes(new int[] { android.R.attr.actionBarSize });
		mToolbarHeight = ((int) actionBarAttrs.getDimension(0, 0) + 10);
		actionBarAttrs.recycle();

		mScrollView = ((ScrollView) rootView.findViewById(getScrollViewResource()));
		mScrollView.getViewTreeObserver().addOnScrollChangedListener(this);

		return rootView;
	}

	@Override
	public void onResume() {
		if (Utils.isDebugEnabled(mActivity.getApplicationContext())) {
			Log.d(TAG + ".onResume", "Entered");
		}

		if (mPrefs == null) {
			mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
		}
		if (mPrefs != null) {
			fieldLength = Integer.parseInt(mPrefs.getString(Utils.PREFERENCE_DECIMAL_PLACES, "-1"));
		}
		if (fieldLength == -1) {
			fieldLength = 8;
		}
		super.onResume();
	}

	@Override
	public void onPause() {
		if (Utils.isDebugEnabled(mActivity.getApplicationContext())) {
			Log.d(TAG + ".onPause", "Entered");
		}
		super.onPause();

		Toolbar toolbar = ((Toolbar) mActivity.findViewById(R.id.toolbar));
		if (toolbar != null) {
			// Animate toolbar down if hidden
			while (mToolbarOffset > 0) {
				toolbar.animate().y(-mToolbarOffset);
				--mToolbarOffset;
			}
		}
	}

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
				Toolbar toolbar = ((Toolbar) getActivity().findViewById(R.id.toolbar));
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
					Log.d("FragmentBase.onScrollChanged", "Toolbar is null\n" + e.toString());
				}
			}

			lastY = mScrollView.getScrollY();
		}
	}

	protected abstract int getLayoutResource();
	protected abstract int getScrollViewResource();
}
