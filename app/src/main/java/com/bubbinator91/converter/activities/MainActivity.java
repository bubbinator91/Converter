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
import com.bubbinator91.converter.util.Globals;
import com.bubbinator91.converter.util.NavigationDrawerItem;
import com.bubbinator91.converter.util.NavigationDrawerListAdapter;

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
	private int lastSelectedPosition;
	private final String STATE_SELECTED_POSITION = "selected_fragment";

	private Handler mHandler;
	private boolean wasActivityRestarted = false;

	// region Lifecycle methods

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Globals.isDebugEnabled) {
			Log.d(TAG + ".onCreate", "Entered");
		}
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
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		if (Globals.isDebugEnabled) {
			Log.d(TAG + ".onRestart", "Entered");
		}

		wasActivityRestarted = true;
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (Globals.isDebugEnabled) {
			Log.d(TAG + ".onStart", "Entered");
		}

		if (!wasActivityRestarted) {
			if (Globals.isDebugEnabled) {
				Log.d(TAG + ".onStart", "Activity was not restarted");
			}
			lastSelectedPosition = PreferenceManager
										   .getDefaultSharedPreferences(this)
										   .getInt(STATE_SELECTED_POSITION, 0);
			if (Globals.isDebugEnabled) {
				Log.d(TAG + ".onStart", "lastSelectedPosition = " + lastSelectedPosition);
			}
			if (!Globals.isTransitioningBackToMainActivity) {
				if (lastSelectedPosition >= 0) {
					preChangeToFragment(lastSelectedPosition);
					selectFragment(lastSelectedPosition);
				}
			} else {
				Globals.isTransitioningBackToMainActivity = false;
				if (lastSelectedPosition >= 0) {
					preChangeToFragment(lastSelectedPosition);
				}
			}
		}

		wasActivityRestarted = false;
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (Globals.isDebugEnabled) {
			Log.d(TAG + ".onResume", "Entered");
			Log.d(TAG + ".onResume", "isDebugEnabled = " + Globals.isDebugEnabled);
			Log.d(TAG + ".onResume", "decimalPlaceLength = " + Globals.decimalPlaceLength);
		}

		if (lastSelectedPosition >= 0) {
			preChangeToFragment(lastSelectedPosition);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (Globals.isDebugEnabled) {
			Log.d(TAG + ".onPause", "Entered");
		}

		InputMethodManager imm = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
		imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (Globals.isDebugEnabled) {
			Log.d(TAG + ".onStop", "Entered");
		}
	}

	// endregion

	// region Overridden BaseActivity methods

	@Override
	protected int getLayoutResourceId() { return R.layout.activity_main; }

	@Override
	protected String getChildTag() { return TAG; }

	// endregion

	// region Options Menu methods

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (Globals.isDebugEnabled) {
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
		if (Globals.isDebugEnabled) {
			Log.d(TAG + ".onOptionsItemSelected", "Entered");
		}

		switch (item.getItemId()) {
			case android.R.id.home:
				mDrawerLayout.openDrawer(Gravity.START);
				return true;
			case R.id.action_settings:
				InputMethodManager imm = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
				imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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

	// endregion

	// region Fragment switching methods

	/**
	 * Switches to the fragment that is selected by the user in
	 * the navigation drawer.
	 *
	 * @param position		the integer position of the selected fragment,
	 *                      which is used to determine which fragment to
	 *                      switch to
	 */
	private void selectFragment(int position) {
		if (Globals.isDebugEnabled) {
			Log.d(TAG + ".selectFragment", "Entered");
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
        if (Globals.isDebugEnabled) {
            Log.d(TAG + ".preChangeToFragment", "Entered");
            Log.d(TAG + ".preChangeToFragment", "position = " + position);
        }

        lastSelectedPosition = position;
        mDrawerList.setItemChecked(position, true);
        mDrawerList.setSelection(position);
        getToolbar().setTitle(mDrawerTitles[position]);
		mDrawerLayout.closeDrawer(Gravity.START);

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(STATE_SELECTED_POSITION, lastSelectedPosition);
            editor.apply();
        }
    }

    private void changeToFragment(final Fragment fragment) {
        if (Globals.isDebugEnabled) {
            Log.d(TAG + ".changeToFragment", "Entered");
        }

		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left)
						.replace(R.id.container, fragment)
						.addToBackStack(null)
						.commit();
			}
		}, 225);
    }

	// endregion

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
			if (Globals.isDebugEnabled) {
				Log.d(TAG + ".DrawerItemClickListener.onItemClick", "Entered");
			}
			if ((position != lastSelectedPosition) && (position >= 0)) {
				if (Globals.isDebugEnabled) {
					Log.d(TAG + ".DrawerItemClickListener.onItemClick", "position = " + position);
				}
				preChangeToFragment(position);
				selectFragment(position);
			}
		}
	}
}
