package com.bubbinator91.converter.ui.activities;

import android.app.ActivityOptions;
import android.app.Fragment;
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
import android.widget.Toast;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.ui.fragments.AccelerationFragment;
import com.bubbinator91.converter.ui.fragments.DataTransferSpeedFragment;
import com.bubbinator91.converter.ui.fragments.FuelConsumptionFragment;
import com.bubbinator91.converter.ui.fragments.LengthFragment;
import com.bubbinator91.converter.ui.fragments.SpeedFragment;
import com.bubbinator91.converter.ui.fragments.TemperatureFragment;
import com.bubbinator91.converter.util.GlobalsManager;

import butterknife.Bind;
import butterknife.ButterKnife;
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
    private final String TAG = MainActivity.class.getSimpleName();
    private final String STATE_SELECTED_FRAGMENT = "selected_fragment";

    @Bind(R.id.activity_main_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.drawer_view) NavigationView mNavigationView;

    private ActionBarDrawerToggle toolbarDrawerToggle;
    private String lastSelectedFragment = null;
    private Handler mainThreadHandler = new Handler();
    private boolean wasActivityRestarted = false;

    // region Lifecycle methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.tag(TAG + ".onCreate").i("Entered");
        ButterKnife.bind(this);
        wasActivityRestarted = false;
        setToolbarIcon(-1, true);

        mNavigationView.setNavigationItemSelectedListener(menuItem -> {
                    if (!lastSelectedFragment.equals(menuItem.getTitle().toString())) {
                        switchToFragment(menuItem.getTitle().toString());
                    }
                    return true;
                }
        );

        toolbarDrawerToggle = new ActionBarDrawerToggle(this,
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

        mDrawerLayout.setDrawerListener(toolbarDrawerToggle);
        toolbarDrawerToggle.syncState();
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

            lastSelectedFragment = getSharedPreferences().getString(STATE_SELECTED_FRAGMENT, "null");
            Timber.tag(TAG + ".onStart").i("lastSelectedFragment = " + lastSelectedFragment);
            if (!GlobalsManager.INSTANCE.isGoingToMainActivityFromSettings()) {
                if (!lastSelectedFragment.equals("null")) {
                    switchToFragment(lastSelectedFragment);
                }
            } else {
                GlobalsManager.INSTANCE.setIsGoingToMainActivityFromSettings(false);
                if (!lastSelectedFragment.equals("null")) {
                    setFragmentRelatedProperties(lastSelectedFragment);
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
            setFragmentRelatedProperties(lastSelectedFragment);
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
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected String getChildTag() {
        return TAG;
    }

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

    // region Fragment related methods

    private void saveLastSelectedFragment() {
        Timber.tag(TAG + ".saveLastSelectedFragment").i("Entered");
        if (getSharedPreferences() != null) {
            SharedPreferences.Editor editor = getSharedPreferences().edit();
            editor.putString(STATE_SELECTED_FRAGMENT, lastSelectedFragment);
            editor.apply();
        }
    }

    private void setFragmentRelatedProperties(String fragmentName) {
        Timber.tag(TAG + ".setFragmentRelatedProperties").i("Entered");
        Timber.tag(TAG + ".setFragmentRelatedProperties").i("fragmentName = " + fragmentName);

        setNavigationDrawerItemChecked(fragmentName);
        lastSelectedFragment = fragmentName;
        getToolbar().setTitle(fragmentName);
        mDrawerLayout.closeDrawer(GravityCompat.START);

        saveLastSelectedFragment();
    }

    private void setNavigationDrawerItemChecked(String itemName) {
        Timber.tag(TAG + ".setNavigationDrawerItemChecked").i("Entered");
        Timber.tag(TAG + ".setNavigationDrawerItemChecked").i("fragmentName = " + itemName);

        if (itemName.equals(getString(R.string.title_acceleration))) {
            mNavigationView.getMenu().findItem(R.id.nav_acceleration).setChecked(true);
        } else if (itemName.equals(getString(R.string.title_data_transfer_speed))) {
            mNavigationView.getMenu().findItem(R.id.nav_dts).setChecked(true);
        } else if (itemName.equals(getString(R.string.title_fuel_consumption))) {
            mNavigationView.getMenu().findItem(R.id.nav_fuel).setChecked(true);
        } else if (itemName.equals(getString(R.string.title_length))) {
            mNavigationView.getMenu().findItem(R.id.nav_length).setChecked(true);
        } else if (itemName.equals(getString(R.string.title_speed))) {
            mNavigationView.getMenu().findItem(R.id.nav_speed).setChecked(true);
        } else if (itemName.equals(getString(R.string.title_temperature))) {
            mNavigationView.getMenu().findItem(R.id.nav_temperature).setChecked(true);
        } else {
            Timber.tag(TAG + ".setFragmentRelatedProperties")
                    .e("itemName parameter is invalid");
        }
    }

    /**
     * Switches to the fragment that is selected by the user in the navigation drawer.
     *
     * @param fragmentName the name of the selected fragment as a {@link String}, which is used to
     *                     determine which fragment to switch to
     */
    private void switchToFragment(final String fragmentName) {
        Timber.tag(TAG + ".selectFragment").i("Entered");

        final Fragment fragmentToSwitchTo;

        if (fragmentName.equals(getString(R.string.title_acceleration))) {
            fragmentToSwitchTo = new AccelerationFragment();
        } else if (fragmentName.equals(getString(R.string.title_data_transfer_speed))) {
            fragmentToSwitchTo = new DataTransferSpeedFragment();
        } else if (fragmentName.equals(getString(R.string.title_fuel_consumption))) {
            fragmentToSwitchTo = new FuelConsumptionFragment();
        } else if (fragmentName.equals(getString(R.string.title_length))) {
            fragmentToSwitchTo = new LengthFragment();
        } else if (fragmentName.equals(getString(R.string.title_speed))) {
            fragmentToSwitchTo = new SpeedFragment();
        } else if (fragmentName.equals(getString(R.string.title_temperature))) {
            fragmentToSwitchTo = new TemperatureFragment();
        } else {
            fragmentToSwitchTo = null;
        }

        mainThreadHandler.postDelayed(() -> {
                    if (fragmentToSwitchTo != null) {
                        try {
                            getFragmentManager().beginTransaction()
                                    .setCustomAnimations(R.anim.fragment_slide_in_right,
                                            R.anim.fragment_slide_out_left)
                                    .replace(R.id.container, fragmentToSwitchTo)
                                    .commit();

                            lastSelectedFragment = fragmentName;
                            getToolbar().setTitle(fragmentName);
                            mDrawerLayout.closeDrawer(GravityCompat.START);
                            saveLastSelectedFragment();
                        } catch (IllegalStateException e) {
                            Timber.tag(TAG + ".changeToFragment").e(e.getMessage());
                            Toast.makeText(getParent(), "", Toast.LENGTH_LONG).show();
                            setNavigationDrawerItemChecked(lastSelectedFragment);
                        }
                    }
                }
                , 225
        );
    }

    // endregion
}