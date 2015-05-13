package com.bubbinator91.converter.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.Globals;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import timber.log.Timber;

/**
 * The base activity that all activities should inherit from.
 * This activity is specially created to handle the initialization
 * of the toolbar. It expects that you are using a theme that
 * disables the Action Bar (ex. Theme.AppCompat.NoActionBar).
 */
public abstract class BaseActivity extends AppCompatActivity {
	private final String TAG = "BaseActivity";

	private Toolbar mToolbar;

	// region Lifecycle methods

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Timber.tag(TAG + "." + getChildTag() + ".onCreate").i("Entered");

		PreferenceManager.setDefaultValues(this, R.xml.settings, false);

		SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		if (mPrefs != null) {
			Globals.isFirstRun = mPrefs.getBoolean(Globals.PREFERENCE_FIRSTRUN, false);
			Globals.isLogcatEnabled = mPrefs.getBoolean(Globals.PREFERENCE_LOGCAT, false);
			Globals.decimalPlaceLength = Integer.parseInt(
					mPrefs.getString(
							Globals.PREFERENCE_DECIMAL_PLACES,
							"-1")
			);
			if (Globals.decimalPlaceLength == -1) {
				SharedPreferences.Editor editor = mPrefs.edit();
				editor.putString(Globals.PREFERENCE_DECIMAL_PLACES, "8");
				editor.apply();
				Globals.decimalPlaceLength = 8;
			}
		} else {
			Timber.tag(TAG + "." + getChildTag() + ".onCreate").e("Could not get shared prefs");
			SnackbarManager.show(
					Snackbar.with(this)
					.text("Could not get preferences. Running with defaults.")
					.duration(5000),
					this
			);
			Globals.isLogcatEnabled = false;
			Globals.decimalPlaceLength  = 8;
		}

		setContentView(getLayoutResourceId());

		mToolbar = ((Toolbar) findViewById(R.id.toolbar));
		setSupportActionBar(mToolbar);
		if ((mToolbar != null) && (getSupportActionBar() != null)) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setHomeButtonEnabled(true);
		}
	}

	// endregion

	// region Helper methods

	protected Toolbar getToolbar() {
		Timber.tag(TAG + "." + getChildTag() + ".getToolbar").i("Entered");
		return mToolbar;
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
		if ((mToolbar != null) && (getSupportActionBar() != null)) {
			if (enableHomeIcon) {
				getSupportActionBar().setDisplayShowHomeEnabled(true);
			} else {
				mToolbar.setNavigationIcon(iconResId);
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
