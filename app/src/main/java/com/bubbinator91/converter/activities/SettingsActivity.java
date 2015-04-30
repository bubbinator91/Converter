package com.bubbinator91.converter.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.util.Log;
import android.view.MenuItem;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.Globals;
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

	// region Lifecycle methods

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Globals.isDebugEnabled) {
			Log.d(TAG + ".onCreate", "Entered");
		}

		getFragmentManager().beginTransaction()
				.replace(R.id.prefs_container, new SettingsFragment())
				.commit();
	}

	// endregion

	// region Overridden BaseActivity methods

	@Override
	protected int getLayoutResourceId() { return R.layout.activity_settings; }

	@Override
	protected String getChildTag() { return TAG; }

	// endregion

	// region Other overridden methods

	@Override
	public void finish() {
		super.finish();
		if (Globals.isDebugEnabled) {
			Log.d(TAG + ".finish", "Entered");
		}

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		if (preferences != null) {
			Globals.decimalPlaceLength = Integer.parseInt(preferences
														  .getString(
																Globals.PREFERENCE_DECIMAL_PLACES,
																"8")
														 );
		}
		Globals.isTransitioningBackToMainActivity = true;

		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (Globals.isDebugEnabled) {
			Log.d(TAG + ".onOptionsItemSelected", "Entered");
		}

		switch(item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	// endregion

	public static class SettingsFragment extends PreferenceFragment {
		private final String TAG = "SettingsFragment";

		private Activity mActivity = null;

		// region Lifecycle methods

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
			if (Utils.isDebugEnabled(mActivity)) {
				Log.d(TAG + ".onCreate", "Entered");
			}

			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.settings);

			SwitchPreference debugSwitch =
					((SwitchPreference) findPreference(
											getResources().getString(R.string.util_key_debug)
										)
					);
			debugSwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					if (((boolean) newValue) != Globals.isDebugEnabled) {
						Globals.isDebugEnabled = ((boolean) newValue);
						return true;
					} else if (((boolean) newValue) == Globals.isDebugEnabled) {
						Globals.isDebugEnabled = !Globals.isDebugEnabled;
						return false;
					} else {
						return false;
					}
				}
			});
		}

		// endregion
	}
}
