package com.bubbinator91.converter.activities;

import android.app.ActivityOptions;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.fragments.*;
import com.bubbinator91.converter.util.Utils;

public class MainActivity extends BaseActivity {
	private final String TAG = "MainActivity";

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private ListView mDrawerList;
	private String[] mDrawerItems;

	private SharedPreferences mPrefs;
	private int lastSelectedPosition;
	private final String STATE_SELECTED_POSITION = "selected_fragment";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (Utils.isDebugEnabled(this)) {
			Log.d(TAG + ".onCreate", "Entered");
		}
		super.onCreate(savedInstanceState);
		setToolbarIcon(R.drawable.ic_drawer);

		mDrawerLayout = ((DrawerLayout) findViewById(R.id.activity_main_layout));
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);

		mDrawerItems = getResources().getStringArray(R.array.drawer_list_labels);
		mDrawerList = ((ListView) findViewById(R.id.drawer_list));
		mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.util_drawer_list_item, mDrawerItems));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		if (mPrefs != null) {
			if (mPrefs.getInt(Utils.PREFERENCE_DEBUG, -1) == -1) {
				SharedPreferences.Editor editor = mPrefs.edit();
				// TODO Switch this to false (0) for release
				editor.putInt(Utils.PREFERENCE_DEBUG, 1);
				editor.apply();
			}
			int fieldLength = mPrefs.getInt(Utils.PREFERENCE_FIELD_LENGTH, -1);
			if (fieldLength == -1) {
				SharedPreferences.Editor editor = mPrefs.edit();
				editor.putInt(Utils.PREFERENCE_FIELD_LENGTH, 8);
				editor.apply();
			}
		}
	}

	@Override
	protected int getLayoutResourceId() { return R.layout.activity_main; }

	@Override
	protected void onResume() {
		if (Utils.isDebugEnabled(this)) {
			Log.d(TAG + ".onResume", "Entered");
		}
		super.onResume();

		if (mPrefs != null) {
			if (Utils.isDebugEnabled(this)) {
				Log.d(TAG + ".onResume", "mPrefs != null");
			}
			lastSelectedPosition = mPrefs.getInt(STATE_SELECTED_POSITION, -1);
			if (Utils.isDebugEnabled(this)) {
				Log.d(TAG + ".onResume", "lastSelectedPosition = " + lastSelectedPosition);
				Log.d(TAG + ".onResume", "fieldLength = " + mPrefs.getInt(Utils.PREFERENCE_FIELD_LENGTH, -1));
			}
			if (mPrefs.getInt(Utils.PREFERENCE_TRANS_TO_SETTINGS, -1) != 1) {
				if (lastSelectedPosition >= 0) {
					selectDrawerItem(lastSelectedPosition);
				}
			}
		} else {
			if (Utils.isDebugEnabled(this)) {
				Log.d(TAG + ".onResume", "mPrefs == null");
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (Utils.isDebugEnabled(this)) {
			Log.d(TAG + ".onCreateOptionsMenu", "Entered");
		}

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (Utils.isDebugEnabled(this)) {
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

	private void selectDrawerItem(int position) {
		if (Utils.isDebugEnabled(this)) {
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
			mToolbar.setTitle(mDrawerItems[position]);
			//mDrawerLayout.closeDrawer(mDrawerList);
			mDrawerLayout.closeDrawers();

			SharedPreferences.Editor editor = mPrefs.edit();
			editor.putInt(STATE_SELECTED_POSITION, lastSelectedPosition);
			editor.apply();
		}
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			if (Utils.isDebugEnabled(getApplicationContext()))
				Log.d(TAG + ".DrawerItemClickListener.onItemClick", "Entered");
			selectDrawerItem(position);
		}
	}
}
