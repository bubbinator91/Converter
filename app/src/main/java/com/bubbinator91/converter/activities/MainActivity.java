package com.bubbinator91.converter.activities;

import android.app.ActivityOptions;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
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
import android.widget.TextView;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.Util;
import com.bubbinator91.converter.fragments.FragmentDataTransferSpeed;
import com.bubbinator91.converter.fragments.FragmentLength;
import com.bubbinator91.converter.fragments.FragmentSpeed;
import com.bubbinator91.converter.fragments.FragmentTemperature;

import java.lang.reflect.Field;

public class MainActivity extends BaseActivity {
	private  boolean DEBUG = false;
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
		if (DEBUG)
        	Log.d(TAG + ".onCreate", "Entered");
        super.onCreate(savedInstanceState);
        setActionBarIcon(R.drawable.ic_drawer);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout_main);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);
        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        mDrawerItems = getResources().getStringArray(R.array.drawer_list_labels);
        mDrawerList = (ListView)findViewById(R.id.drawer_list_main);
        mDrawerList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, mDrawerItems));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		if (mPrefs != null) {
			if (mPrefs.getInt(Util.PREFERENCE_DEBUG, -1) == -1) {
				SharedPreferences.Editor editor = mPrefs.edit();
				editor.putInt(Util.PREFERENCE_DEBUG, 0);
				editor.apply();
				DEBUG = false;
			}

			int fieldLength = mPrefs.getInt(Util.PREFERENCE_FIELD_LENGTH, -1);
			if (fieldLength == -1) {
				SharedPreferences.Editor editor = mPrefs.edit();
				editor.putInt(Util.PREFERENCE_FIELD_LENGTH, 8);
				editor.apply();
			}
		}

        TextView toolbarTitle = getToolbarTextView();
        if (toolbarTitle != null) {
            Typeface font = Typeface.create("sans-serif", Typeface.NORMAL);
            toolbarTitle.setTypeface(font);
        }
    }

    @Override
    protected void onResume() {
		if (DEBUG)
			Log.d(TAG + ".onResume", "Entered");
        super.onResume();
        if (mPrefs != null) {
			if (DEBUG)
            	Log.d(TAG + ".onResume", "mPrefs != null");
            lastSelectedPosition = mPrefs.getInt(STATE_SELECTED_POSITION, -1);
			if (DEBUG) {
				Log.d(TAG + ".onResume", "lastSelectedPosition = " + lastSelectedPosition);
				Log.d(TAG + ".onResume", "fieldLength = " + mPrefs.getInt(Util.PREFERENCE_FIELD_LENGTH, -1));
			}
            if (lastSelectedPosition >= 0)
                selectDrawerItem(lastSelectedPosition);
			if (mPrefs.getInt(Util.PREFERENCE_DEBUG, -1) == 1)
				DEBUG = true;
        } else {
			if (DEBUG)
            	Log.d(TAG + ".onResume", "mPrefs == null");
        }
    }

    @Override
    protected void onPause() {
		if (DEBUG)
        	Log.d(TAG + ".onPause", "Entered");
        super.onPause();
        if (mPrefs != null) {
			if (DEBUG) {
				Log.d(TAG + ".onPause", "mPrefs != null");
				Log.d(TAG + ".onPause", "lastSelectedPosition = " + lastSelectedPosition);
			}
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putInt(STATE_SELECTED_POSITION, lastSelectedPosition);
            editor.apply();
        } else {
			if (DEBUG)
            	Log.d(TAG + ".onPause", "mPrefs == null");
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
		if (DEBUG)
        	Log.d(TAG + ".onCreateOptionsMenu", "Entered");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		if (DEBUG)
        	Log.d(TAG + ".onOptionsItemSelected", "Entered");
        switch(item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                return true;
			case R.id.action_settings:
				Intent intent = new Intent(this, SettingsActivity.class);
				startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
				return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void selectDrawerItem(int position) {
		if (DEBUG)
        	Log.d(TAG + ".selectDrawerItem", "Entered");
        FragmentManager fragmentManager = getFragmentManager();
        boolean flag = true;
        switch (position) {
            case 0:
                fragmentManager.beginTransaction()
						//.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.container, new FragmentDataTransferSpeed()).commit();
                break;
            case 1:
                fragmentManager.beginTransaction()
						//.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.container, new FragmentLength()).commit();
                break;
            case 2:
                fragmentManager.beginTransaction()
						//.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.container, new FragmentSpeed()).commit();
                break;
            case 3:
                fragmentManager.beginTransaction()
						//.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.container, new FragmentTemperature()).commit();
                break;
            default:
                flag = false;
                break;
        }

        if (flag) {
            lastSelectedPosition = position;
            mDrawerList.setItemChecked(position, true);
            mToolbar.setTitle(mDrawerItems[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

			SharedPreferences.Editor editor = mPrefs.edit();
			editor.putInt(STATE_SELECTED_POSITION, lastSelectedPosition);
			editor.apply();
        }
    }

    private TextView getToolbarTextView() {
        TextView tv;

        try {
            Field f = mToolbar.getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            tv = (TextView)f.get(mToolbar);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            tv = null;
        }

        return tv;
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
			if (DEBUG)
            	Log.d(TAG + ".DrawerItemClickListener.onItemClick", "Entered");
            selectDrawerItem(position);
        }
    }
}
