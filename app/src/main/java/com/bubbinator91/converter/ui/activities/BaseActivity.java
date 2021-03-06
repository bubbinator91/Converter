package com.bubbinator91.converter.ui.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bubbinator91.converter.BuildConfig;
import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.Globals;
import com.bubbinator91.converter.util.GlobalsManager;

import timber.log.Timber;

/**
 * The base activity that all activities should inherit from.
 * This activity is specially created to handle the initialization
 * of the toolbar. It expects that you are using a theme that
 * disables the Action Bar (ex. Theme.AppCompat.NoActionBar).
 */
public abstract class BaseActivity extends AppCompatActivity {
	private final String TAG = BaseActivity.class.getSimpleName();

	private static SharedPreferences sharedPreferences = null;

	private Toolbar toolbar;

	// region Lifecycle methods

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Timber.tag(TAG + "." + getChildTag() + ".onCreate").i("Entered");
        View rootView = View.inflate(this, getLayoutResourceId(), null);
        setContentView(rootView);

        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        }

		PreferenceManager.setDefaultValues(this, R.xml.settings, false);

		SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		if (mPrefs != null) {
			GlobalsManager.INSTANCE.setIsFirstRun(mPrefs.getBoolean(Globals.PREFERENCE_FIRSTRUN, false));
			GlobalsManager.INSTANCE.setIsLogcatEnabled(mPrefs.getBoolean(Globals.PREFERENCE_LOGCAT, false));
			GlobalsManager.INSTANCE.setDecimalPlaceLength(Integer.parseInt(
                    mPrefs.getString(
                            Globals.PREFERENCE_DECIMAL_PLACES,
                            "-1"))
            );
			if (GlobalsManager.INSTANCE.decimalPlaceLength() == -1) {
				SharedPreferences.Editor editor = mPrefs.edit();
				editor.putString(Globals.PREFERENCE_DECIMAL_PLACES, "8");
				editor.apply();
				GlobalsManager.INSTANCE.setDecimalPlaceLength(8);
			}
		} else {
			Timber.tag(TAG + "." + getChildTag() + ".onCreate").e("Could not get shared prefs");
			GlobalsManager.INSTANCE.setIsLogcatEnabled(false);
			GlobalsManager.INSTANCE.setDecimalPlaceLength(8);
            Snackbar.make(rootView,
                    getString(R.string.activity_main_error_default_prefs),
                    Snackbar.LENGTH_LONG).show();
		}

		toolbar = ((Toolbar) findViewById(R.id.toolbar));
		setSupportActionBar(toolbar);
		if ((toolbar != null) && (getSupportActionBar() != null)) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setHomeButtonEnabled(true);
		}
	}

	// endregion

	// region Helper methods

    /**
     * Gets the {@link SharedPreferences} object for use in any child class.
     *
     * @return  A {@link SharedPreferences} object.
     */
    protected SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(this.getApplicationContext());
        }

        return sharedPreferences;
    }

	protected Toolbar getToolbar() {
		Timber.tag(TAG + "." + getChildTag() + ".getToolbar").i("Entered");
		return toolbar;
	}

	/**
	 * Sets the icon on the left side of the toolbar to a user
	 * defined image, or the default "hamburger" icon, depending
	 * on the parameters passed to the method. If enableHomeIcon
	 * is false, the value of iconResId will attempt to be set
	 * as the icon.
	 *
	 * @param iconResId			the integer value of the resource id of the icon
	 *                          to place in the toolbar
	 * @param enableHomeIcon	a boolean value indicating if the default "hamburger" icon
	 *                          should be shown
	 */
	protected void setToolbarIcon(int iconResId, boolean enableHomeIcon) {
		Timber.tag(TAG + "." + getChildTag() + ".setToolbarIcon").i("Entered");
		if ((toolbar != null) && (getSupportActionBar() != null)) {
			if (enableHomeIcon) {
				getSupportActionBar().setDisplayShowHomeEnabled(true);
			} else {
				toolbar.setNavigationIcon(iconResId);
			}
		}
	}

	// endregion

	// region Abstract methods

	/**
	 * Returns the resource ID of the layout for the activity.
	 * Must be overridden and implemented in any activity that
	 * inherits from this class.
	 *
	 * @return		the integer value of the resource id of the activity's layout
	 */
	protected abstract int getLayoutResourceId();

	protected abstract String getChildTag();

	// endregion
}
