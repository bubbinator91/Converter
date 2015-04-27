package com.bubbinator91.converter.activities;

import android.app.ActivityOptions;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
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
import android.widget.ArrayAdapter;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (Utils.isDebugEnabled(getApplicationContext())) {
			Log.d(TAG + ".onCreate", "Entered");
		}
		super.onCreate(savedInstanceState);
		setToolbarIcon(-1, true);

		mDrawerTitles = getResources().getStringArray(R.array.drawer_list_labels);
		mDrawerIcons = getResources().obtainTypedArray(R.array.drawer_list_icons);

		mDrawerItems = new ArrayList<>();
		for (int i = 0; i < mDrawerTitles.length; i++) {
			mDrawerItems.add(new NavigationDrawerItem(mDrawerTitles[i], mDrawerIcons.getResourceId((i + 1), 0)));
		}
		mDrawerIcons.recycle();

		mDrawerLayout = ((DrawerLayout) findViewById(R.id.activity_main_layout));
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name) {
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
	protected void onResume() {
		if (Utils.isDebugEnabled(getApplicationContext())) {
			Log.d(TAG + ".onResume", "Entered");
		}
		super.onResume();

		if (mPrefs != null) {
			if (Utils.isDebugEnabled(getApplicationContext())) {
				Log.d(TAG + ".onResume", "mPrefs != null");
			}
			lastSelectedPosition = mPrefs.getInt(STATE_SELECTED_POSITION, -1);
			if (Utils.isDebugEnabled(getApplicationContext())) {
				Log.d(TAG + ".onResume", "lastSelectedPosition = " + lastSelectedPosition);
				Log.d(TAG + ".onResume", "fieldLength = " + mPrefs.getString(Utils.PREFERENCE_DECIMAL_PLACES, "null"));
			}
			if (mPrefs.getInt(Utils.PREFERENCE_TRANS_FROM_SETTINGS, -1) != 1) {
				if (lastSelectedPosition >= 0) {
					selectDrawerItem(lastSelectedPosition);
				}
			} else {
				SharedPreferences.Editor editor = mPrefs.edit();
				editor.putInt(Utils.PREFERENCE_TRANS_FROM_SETTINGS, 0);
				editor.apply();
			}
		} else {
			if (Utils.isDebugEnabled(getApplicationContext())) {
				Log.d(TAG + ".onResume", "mPrefs == null");
			}
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
	private void selectDrawerItem(int position) {
		if (Utils.isDebugEnabled(getApplicationContext())) {
			Log.d(TAG + ".selectDrawerItem", "Entered");
		}

		FragmentManager fragmentManager = getFragmentManager();
		boolean flag = true;
		switch (position) {
			case 0:
				fragmentManager.beginTransaction()
						.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left)
						.replace(R.id.container, new DataTransferSpeedFragment()).commit();
				break;
			case 1:
				fragmentManager.beginTransaction()
						.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left)
						.replace(R.id.container, new LengthFragment()).commit();
				break;
			case 2:
				fragmentManager.beginTransaction()
						.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left)
						.replace(R.id.container, new SpeedFragment()).commit();
				break;
			case 3:
				fragmentManager.beginTransaction()
						.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left)
						.replace(R.id.container, new TemperatureFragment()).commit();
				break;
			default:
				flag = false;
				break;
		}

		if (flag) {
			lastSelectedPosition = position;
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			mToolbar.setTitle(mDrawerTitles[position]);
			mDrawerLayout.closeDrawers();

			SharedPreferences.Editor editor = mPrefs.edit();
			editor.putInt(STATE_SELECTED_POSITION, lastSelectedPosition);
			editor.apply();
		}
	}

	/**
	 * A listener class that handles the event of the user selecting an item
	 * in the navigation drawer. If the selected position is a new position,
	 * the overridden {@link #onItemClick(AdapterView, View, int, long)} method
	 * passes that position into {@link #selectDrawerItem(int)}, which switches
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
				selectDrawerItem(position);
			}
		}
	}
}
