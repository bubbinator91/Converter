package com.bubbinator91.converter.activities;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.fragments.*;
import com.bubbinator91.converter.util.NavigationDrawerItem;
import com.bubbinator91.converter.util.NavigationDrawerListAdapter;
import com.bubbinator91.converter.util.Utils;

import java.util.ArrayList;

/**
 * The main activity of the application. Inherits from the
 * BaseActivity, uses a toolbar, and implements a navigation
 * drawer. The navigation drawer is used to switch between
 * fragments, with each of the said fragments implementing
 * functionality for converting between units of a specific
 * category or class.
 */
public class MainActivity extends BaseActivity {
	private final String TAG = "MainActivity";
	private boolean wasActivityRestarted;

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private ListView mDrawerList;
	private ArrayList<NavigationDrawerItem> mDrawerItems;
	private NavigationDrawerListAdapter mDrawerListAdapter;
	private String[] mDrawerTitles;
	private TypedArray mDrawerIcons;

	private SharedPreferences mPrefs;
	private int lastSelectedPosition;
	private final String STATE_SELECTED_POSITION = "selected_fragment";

	private Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (Utils.isDebugEnabled(getApplicationContext())) {
			Log.d(TAG + ".onCreate", "Entered");
		}
		super.onCreate(savedInstanceState);
		wasActivityRestarted = false;
		setToolbarIcon(-1, true);

		mHandler = new Handler();

		mDrawerTitles = getResources().getStringArray(R.array.drawer_list_labels);
		mDrawerIcons = getResources().obtainTypedArray(R.array.drawer_list_icons);

		mDrawerItems = new ArrayList<>();
		for (int i = 0; i < mDrawerTitles.length; i++) {
			mDrawerItems.add(new NavigationDrawerItem(mDrawerTitles[i], mDrawerIcons.getResourceId((i + 1), 0)));
		}
		mDrawerIcons.recycle();

		mDrawerLayout = ((DrawerLayout) findViewById(R.id.activity_main_layout));
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, getToolbar(), R.string.app_name, R.string.app_name) {
			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				super.onDrawerSlide(drawerView, 0);
				InputMethodManager imm = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
				imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerToggle.syncState();

		mDrawerList = ((ListView) findViewById(R.id.drawer_list));
		mDrawerListAdapter = new NavigationDrawerListAdapter(getApplicationContext(), mDrawerItems);
		mDrawerList.setAdapter(mDrawerListAdapter);
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		if (mPrefs != null) {
			String fieldLength = mPrefs.getString(Utils.PREFERENCE_DECIMAL_PLACES, null);
			if (fieldLength == null) {
				SharedPreferences.Editor editor = mPrefs.edit();
				editor.putString(Utils.PREFERENCE_DECIMAL_PLACES, "8");
				editor.apply();
			}
		}
	}

	@Override
	protected int getLayoutResourceId() { return R.layout.activity_main; }

	@Override
	protected void onRestart() {
		if (Utils.isDebugEnabled(getApplicationContext())) {
			Log.d(TAG + ".onRestart", "Entered");
		}
		super.onRestart();
		wasActivityRestarted = true;
		preChangeToFragment(lastSelectedPosition);
	}

	@Override
	protected void onStart() {
		if (Utils.isDebugEnabled(getApplicationContext())) {
			Log.d(TAG + ".onStart", "Entered");
		}
		super.onStart();

		if ((mPrefs != null) && !wasActivityRestarted) {
			if (Utils.isDebugEnabled(getApplicationContext())) {
				Log.d(TAG + ".onStart", "mPrefs != null");
			}
			lastSelectedPosition = mPrefs.getInt(STATE_SELECTED_POSITION, -1);
			if (Utils.isDebugEnabled(getApplicationContext())) {
				Log.d(TAG + ".onStart", "lastSelectedPosition = " + lastSelectedPosition);
				Log.d(TAG + ".onStart", "fieldLength = " + mPrefs.getString(Utils.PREFERENCE_DECIMAL_PLACES, "null"));
			}
			if (mPrefs.getInt(Utils.PREFERENCE_TRANS_FROM_SETTINGS, -1) != 1) {
				if (lastSelectedPosition >= 0) {
					preChangeToFragment(lastSelectedPosition);
					selectFragment(lastSelectedPosition);
				}
			} else {
				SharedPreferences.Editor editor = mPrefs.edit();
				editor.putInt(Utils.PREFERENCE_TRANS_FROM_SETTINGS, 0);
				editor.apply();
			}
		} else {
			if (Utils.isDebugEnabled(getApplicationContext())) {
				Log.d(TAG + ".onStart", "mPrefs == null");
			}
		}
	}

	@Override
	protected void onResume() {
		if (Utils.isDebugEnabled(getApplicationContext())) {
			Log.d(TAG + ".onResume", "Entered");
		}
		super.onResume();

		if (lastSelectedPosition >= 0) {
			preChangeToFragment(lastSelectedPosition);
		}
	}

	@Override
	protected void onPause() {
		if (Utils.isDebugEnabled(getApplicationContext())) {
			Log.d(TAG + ".onPause", "Entered");
		}
		super.onPause();

		InputMethodManager imm = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
		imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}

	@Override
	protected void onStop() {
		if (Utils.isDebugEnabled(getApplicationContext())) {
			Log.d(TAG + ".onStop", "Entered");
		}
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (Utils.isDebugEnabled(getApplicationContext())) {
			Log.d(TAG + ".onCreateOptionsMenu", "Entered");
		}

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		InputMethodManager imm = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
		imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (Utils.isDebugEnabled(getApplicationContext())) {
			Log.d(TAG + ".onOptionsItemSelected", "Entered");
		}

		switch (item.getItemId()) {
			case android.R.id.home:
				mDrawerLayout.openDrawer(Gravity.START);
				return true;
			case R.id.action_settings:
				Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
				startActivity(
					settingsIntent,
					ActivityOptions.makeCustomAnimation(
						MainActivity.this,
						R.anim.slide_in_left,
						R.anim.slide_out_left).toBundle()
				);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Switches to the fragment that is selected by the user in
	 * the navigation drawer.
	 *
	 * @param position		the integer position of the selected fragment,
	 *                      which is used to determine which fragment to
	 *                      switch to
	 */
	private void selectFragment(int position) {
		if (Utils.isDebugEnabled(getApplicationContext())) {
			Log.d(TAG + ".selectDrawerItem", "Entered");
		}

		switch (position) {
			case 0:
				changeToFragment(new DataTransferSpeedFragment());
				break;
			case 1:
				changeToFragment(new LengthFragment());
				break;
			case 2:
				changeToFragment(new SpeedFragment());
				break;
			case 3:
				changeToFragment(new TemperatureFragment());
				break;
			default:
				break;
		}
	}

    private void preChangeToFragment(int position) {
        if (Utils.isDebugEnabled(getApplicationContext())) {
            Log.d(TAG + ".preChangeToFragment", "Entered");
            Log.d(TAG + ".preChangeToFragment", "position = " + position);
        }

        lastSelectedPosition = position;
        mDrawerList.setItemChecked(position, true);
        mDrawerList.setSelection(position);
        getToolbar().setTitle(mDrawerTitles[position]);
		mDrawerLayout.closeDrawer(Gravity.START);

        if (mPrefs != null) {
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putInt(STATE_SELECTED_POSITION, lastSelectedPosition);
            editor.apply();
        }
    }

    private void changeToFragment(final Fragment fragment) {
        if (Utils.isDebugEnabled(getApplicationContext())) {
            Log.d(TAG + ".changeToFragment", "Entered");
        }

		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left)
						.replace(R.id.container, fragment).commit();
			}
		}, 225);
    }

	/**
	 * A listener class that handles the event of the user selecting an item
	 * in the navigation drawer. If the selected position is a new position,
	 * the overridden {@link #onItemClick(AdapterView, View, int, long)} method
	 * passes that position into {@link #selectFragment(int)}, which switches
	 * to the selected fragment.
	 */
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			if (Utils.isDebugEnabled(getApplicationContext())) {
				Log.d(TAG + ".DrawerItemClickListener.onItemClick", "Entered");
			}
			if ((position != lastSelectedPosition) && (position >= 0)) {
				if (Utils.isDebugEnabled(getApplicationContext())) {
					Log.d(TAG + ".DrawerItemClickListener.onItemClick", "position = " + position);
				}
				preChangeToFragment(position);
				selectFragment(position);
			}
		}
	}
}
