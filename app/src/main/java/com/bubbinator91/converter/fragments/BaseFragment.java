package com.bubbinator91.converter.fragments;

import android.app.Fragment;
import android.content.Context;
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
public abstract class BaseFragment extends Fragment implements ViewTreeObserver.OnScrollChangedListener {
	protected SharedPreferences mPrefs;
	protected int fieldLength = -1, lastY = 0;
	protected ScrollView mScrollView;

	private int mToolbarHeight;
	private int mToolbarOffset = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(getLayoutResource(), container, false);

		mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

		TypedArray actionBarAttrs = getActivity().obtainStyledAttributes(new int[] { android.R.attr.actionBarSize });
		mToolbarHeight = ((int) actionBarAttrs.getDimension(0, 0) + 10);
		actionBarAttrs.recycle();

		mScrollView = ((ScrollView) rootView.findViewById(getScrollViewResource()));
		mScrollView.getViewTreeObserver().addOnScrollChangedListener(this);


		return rootView;
	}

	@Override
	public void onResume() {
		if (mPrefs == null) {
			mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		}
		if (mPrefs != null) {
			fieldLength = mPrefs.getInt(Utils.PREFERENCE_FIELD_LENGTH, -1);
		}
		if (fieldLength == -1)
			fieldLength = 8;
		super.onResume();
	}

	@Override
	public void onScrollChanged() {
		if (isAdded()) {
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
