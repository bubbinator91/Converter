package com.bubbinator91.converter.activities;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.fragments.*;
import com.bubbinator91.converter.util.GlobalsManager;

import timber.log.Timber;

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
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private String lastSelectedFragment = null;
    private final String STATE_SELECTED_FRAGMENT = "selected_fragment";

    private Handler mHandler;
    private boolean wasActivityRestarted = false;

    // region Lifecycle methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.tag(TAG + ".onCreate").i("Entered");
        wasActivityRestarted = false;
        setToolbarIcon(-1, true);

        mHandler = new Handler();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main_layout);
        mNavigationView = (NavigationView) findViewById(R.id.drawer_view);

        mNavigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    if (!lastSelectedFragment.equals(item.getTitle().toString())) {
                        if (preChangeToFragment(item.getTitle().toString())) {
                            selectFragment(item.getTitle().toString());
                        }
                    }
                    return true;
                }
            }
        );

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                getToolbar(),
                R.string.app_name,
                R.string.app_name) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 0);
                InputMethodManager imm = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        };

        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Timber.tag(TAG + ".onRestart").i("Entered");

        wasActivityRestarted = true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.tag(TAG + ".onStart").i("Entered");

        if (!wasActivityRestarted) {
            Timber.tag(TAG + ".onStart").i("Activity was not restarted");
            lastSelectedFragment = PreferenceManager
                    .getDefaultSharedPreferences(this)
                    .getString(STATE_SELECTED_FRAGMENT, "null");
            Timber.tag(TAG + ".onStart").i("lastSelectedFragment = " + lastSelectedFragment);
            if (!GlobalsManager.INSTANCE.isTransitioningToMainActivity()) {
                if (!lastSelectedFragment.equals("null")) {
                    if (preChangeToFragment(lastSelectedFragment)) {
                        selectFragment(lastSelectedFragment);
                    }
                }
            } else {
                GlobalsManager.INSTANCE.setIsTransitioningToMainActivity(false);
                if (!lastSelectedFragment.equals("null")) {
                    preChangeToFragment(lastSelectedFragment);
                }
            }
        }

        wasActivityRestarted = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.tag(TAG + ".onResume").i("Entered");
        Timber.tag(TAG + ".onResume").i("isLogcatEnabled = "
                + GlobalsManager.INSTANCE.isLogcatEnabled());
        Timber.tag(TAG + ".onResume").i("decimalPlaceLength = "
                + GlobalsManager.INSTANCE.decimalPlaceLength());

        if (!lastSelectedFragment.equals("null")) {
            preChangeToFragment(lastSelectedFragment);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.tag(TAG + ".onPause").i("Entered");

        InputMethodManager imm = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.tag(TAG + ".onStop").i("Entered");
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
        Timber.tag(TAG + ".onCreateOptionsMenu").i("Entered");

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Timber.tag(TAG + ".onOptionsItemSelected").i("Entered");

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                InputMethodManager imm = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
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
     * @param fragmentName	the name of the selected fragment as a {@link String}, which is used to
     *                      determine which fragment to switch to
     */
    private void selectFragment(String fragmentName) {
        Timber.tag(TAG + ".selectFragment").i("Entered");

        if (fragmentName.equals(getString(R.string.title_acceleration))){
            changeToFragment(new AccelerationFragment());
        } else if (fragmentName.equals(getString(R.string.title_data_transfer_speed))) {
            changeToFragment(new DataTransferSpeedFragment());
        } else if (fragmentName.equals(getString(R.string.title_length))) {
            changeToFragment(new LengthFragment());
        } else if (fragmentName.equals(getString(R.string.title_speed))) {
            changeToFragment(new SpeedFragment());
        } else if (fragmentName.equals(getString(R.string.title_temperature))) {
            changeToFragment(new TemperatureFragment());
        }
    }

    private boolean preChangeToFragment(String fragmentName) {
        Timber.tag(TAG + ".preChangeToFragment").i("Entered");
        Timber.tag(TAG + ".preChangeToFragment").i("fragmentName = " + fragmentName);

        if (fragmentName.equals(getString(R.string.title_acceleration))) {
            mNavigationView.getMenu().findItem(R.id.nav_acceleration).setChecked(true);
        } else if (fragmentName.equals(getString(R.string.title_data_transfer_speed))) {
            mNavigationView.getMenu().findItem(R.id.nav_dts).setChecked(true);
        } else if (fragmentName.equals(getString(R.string.title_length))) {
            mNavigationView.getMenu().findItem(R.id.nav_length).setChecked(true);
        } else if (fragmentName.equals(getString(R.string.title_speed))) {
            mNavigationView.getMenu().findItem(R.id.nav_speed).setChecked(true);
        } else if (fragmentName.equals(getString(R.string.title_temperature))) {
            mNavigationView.getMenu().findItem(R.id.nav_temperature).setChecked(true);
        } else {
            return false;
        }

        lastSelectedFragment = fragmentName;
        getToolbar().setTitle(fragmentName);
        mDrawerLayout.closeDrawer(GravityCompat.START);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(STATE_SELECTED_FRAGMENT, lastSelectedFragment);
            editor.apply();
        }

        return true;
    }

    private void changeToFragment(final Fragment fragment) {
        Timber.tag(TAG + ".changeToFragment").i("Entered");

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.fragment_slide_in_right,
                                    R.anim.fragment_slide_out_left)
                            .replace(R.id.container, fragment)
                            .addToBackStack(null)
                            .commit();
                } catch (IllegalStateException e) {
                    Timber.tag(TAG + ".changeToFragment").e(e.getMessage());
                }
            }
        }, 225);
    }

    // endregion
}
