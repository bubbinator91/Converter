package com.bubbinator91.converter.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.Utils;

/**
 * The activity that handles the settings for the application.
 * It inherits from the BaseActivity, and the layout has only
 * a toolbar and a simple FrameLayout that gets replaced by
 * a {@link SettingsFragment}, which inherits from
 * {@link PreferenceFragment}.
 */
public class SettingsActivity extends BaseActivity {
	private final String TAG = "SettingsActivity";

	private SharedPreferences mPrefs;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		if (Utils.isDebugEnabled(getApplicationContext())) {
			Log.d(TAG + ".onCreate", "Entered");
		}
		super.onCreate(savedInstanceState);

		mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		getFragmentManager().beginTransaction()
				.replace(R.id.prefs_container, new SettingsFragment())
				.commit();
	}

	@Override
	protected int getLayoutResourceId() { return R.layout.activity_settings; }

	@Override
	protected String getChildTag() { return TAG; }

	@Override
	public void finish() {
		if (Utils.isDebugEnabled(getApplicationContext())) {
			Log.d(TAG + ".finish", "Entered");
		}
		super.finish();

		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putInt(Utils.PREFERENCE_TRANS_FROM_SETTINGS, 1);
		editor.apply();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (Utils.isDebugEnabled(getApplicationContext())) {
			Log.d(TAG + ".onOptionsItemSelected", "Entered");
		}

		switch(item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	public static class SettingsFragment extends PreferenceFragment {
		private final String TAG = "SettingsFragment";

		private Activity mActivity = null;

		@Override
		public void onAttach(Activity activity) {
			if (Utils.isDebugEnabled(activity.getApplicationContext())) {
				Log.d(TAG + ".onAttach", "Entered");
			}
			super.onAttach(activity);
			mActivity = activity;
		}

		@Override
		public void onCreate(final Bundle savedInstanceState) {
			if (Utils.isDebugEnabled(mActivity.getApplicationContext())) {
				Log.d(TAG + ".onCreate", "Entered");
			}

			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.settings);
		}
	}
}
