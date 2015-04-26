package com.bubbinator91.converter.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.Utils;

/**
 * Created by Christopher on 4/25/2015.
 */
public class SettingsActivity extends BaseActivity {
	private final String TAG = "SettingsActivity";

	private SharedPreferences mPrefs;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		getFragmentManager().beginTransaction()
				.replace(R.id.prefs_container, new SettingsFragment())
				.commit();
	}

	@Override
	protected int getLayoutResourceId() { return R.layout.activity_settings; }

	@Override
	public void finish() {
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
		@Override
		public void onCreate(final Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.settings);
		}

		@Override
		public void onStart() {
			super.onStart();
		}
	}
}
