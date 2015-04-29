package com.bubbinator91.converter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.Utils;

/**
 * The base activity that all activities should inherit from.
 * This activity is specially created to handle the initialization
 * of the toolbar. It expects that you are using a theme that
 * disables the Action Bar (ex. Theme.AppCompat.NoActionBar).
 */
public abstract class BaseActivity extends AppCompatActivity {
	private final String TAG = "BaseActivity";

	private Toolbar mToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (Utils.isDebugEnabled(getApplicationContext())) {
			Log.d(TAG + "." + getChildTag() + ".onCreate", "Entered");
		}
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResourceId());
		mToolbar = ((Toolbar) findViewById(R.id.toolbar));
		setSupportActionBar(mToolbar);
		if ((mToolbar != null) && (getSupportActionBar() != null)) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setHomeButtonEnabled(true);
		}
	}

	/**
	 * Returns the resource ID of the layout for the activity.
	 * Must be overridden and implemented in any activity that
	 * inherits from this class.
	 *
	 * @return		the integer value of the resource id of the activity's layout
	 */
	protected abstract int getLayoutResourceId();

	protected abstract String getChildTag();

	protected Toolbar getToolbar() {
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
		if ((mToolbar != null) && (getSupportActionBar() != null)) {
			if (enableHomeIcon) {
				getSupportActionBar().setDisplayShowHomeEnabled(true);
			} else {
				mToolbar.setNavigationIcon(iconResId);
			}
		}
	}
}
