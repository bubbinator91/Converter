package com.bubbinator91.converter.activities;

import android.app.FragmentManager;
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
import com.bubbinator91.converter.fragments.FragmentDataTransferSpeed;
import com.bubbinator91.converter.fragments.FragmentLength;
import com.bubbinator91.converter.fragments.FragmentSpeed;
import com.bubbinator91.converter.fragments.FragmentTemperature;

import java.lang.reflect.Field;

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
        Log.d(TAG + ".onCreate", "Entered onCreate");
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
        TextView toolbarTitle = getToolbarTextView();
        if (toolbarTitle != null) {
            Typeface font = Typeface.create("sans-serif", Typeface.NORMAL);
            toolbarTitle.setTypeface(font);
        }
    }

    @Override
    protected void onResume() {
        Log.d(TAG + ".onResume", "Entered onResume");
        super.onResume();
        if (mPrefs != null) {
            Log.d(TAG + ".onResume", "mPrefs is not null");
            lastSelectedPosition = mPrefs.getInt(STATE_SELECTED_POSITION, -1);
            Log.d(TAG + ".onResume", "lastSelectedPosition = " + lastSelectedPosition);
            if (lastSelectedPosition >= 0)
                selectDrawerItem(lastSelectedPosition);
        } else {
            Log.d(TAG + ".onResume", "mPrefs is null");
        }
    }

    @Override
    protected void onPause() {
        Log.d(TAG + ".onPause", "Entered onPause");
        super.onPause();
        if (mPrefs != null) {
            Log.d(TAG + ".onPause", "mPrefs is not null");
            Log.d(TAG + ".onPause", "lastSelectedPosition = " + lastSelectedPosition);
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putInt(STATE_SELECTED_POSITION, lastSelectedPosition);
            editor.apply();
        } else {
            Log.d(TAG + ".onPause", "mPrefs is null");
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG + ".onCreateOptionsMenu", "Entered onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG + ".onOptionsItemSelected", "Entered onOptionsItemSelected");
        switch(item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void selectDrawerItem(int position) {
        Log.d(TAG + ".selectDrawerItem", "Entered selectDrawerItem");
        FragmentManager fragmentManager = getFragmentManager();
        boolean flag = true;
        switch (position) {
            case 0:
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.container, new FragmentDataTransferSpeed()).commit();
                break;
            case 1:
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.container, new FragmentLength()).commit();
                break;
            case 2:
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.container, new FragmentSpeed()).commit();
                break;
            case 3:
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
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
            Log.d(TAG + ".DrawerItemClickListener.onItemClick", "Entered onItemClick");
            selectDrawerItem(position);
        }
    }
}