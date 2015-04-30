package com.bubbinator91.converter.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.Globals;

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

		SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		if (mPrefs != null) {
			Globals.isDebugEnabled = mPrefs.getBoolean(Globals.PREFERENCE_DEBUG, false);
			Globals.decimalPlaceLength = Integer.parseInt(mPrefs.getString(
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
			Log.e(TAG + "." + getChildTag() + ".onCreate", "Could not get shared prefs.");
			Toast.makeText(
							this,
							"Could not retrieve preferences. Running with defaults.",
							Toast.LENGTH_LONG)
				 .show();
			Globals.isDebugEnabled = false;
			Globals.decimalPlaceLength  = 8;
		}

		if (Globals.isDebugEnabled) {
			Log.d(TAG + "." + getChildTag() + ".onCreate", "Entered");
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
		if (Globals.isDebugEnabled) {
			Log.d(TAG + "." + getChildTag() + ".getToolbar", "Entered");
		}
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
		if (Globals.isDebugEnabled) {
			Log.d(TAG + "." + getChildTag() + ".setToolbarIcon", "Entered");
		}
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
