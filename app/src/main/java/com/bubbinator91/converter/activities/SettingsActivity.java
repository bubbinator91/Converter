package com.bubbinator91.converter.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.view.MenuItem;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.Globals;
import com.bubbinator91.converter.util.GlobalsManager;

import timber.log.Timber;

/**
 * The activity that handles the settings for the application.
 * It inherits from the BaseActivity, and the layout has only
 * a toolbar and a simple FrameLayout that gets replaced by
 * a {@link SettingsFragment}, which inherits from
 * {@link PreferenceFragment}.
 */
public class SettingsActivity extends BaseActivity {
    private final String TAG = SettingsActivity.class.getSimpleName();

    // region Lifecycle methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.tag(TAG + ".onCreate").i("Entered");

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
        Timber.tag(TAG + ".finish").i("Entered");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences != null) {
            GlobalsManager.INSTANCE.setDecimalPlaceLength(Integer.parseInt(
                    preferences.getString(
                            Globals.PREFERENCE_DECIMAL_PLACES,
                            "8"
                    ))
            );
        }
        GlobalsManager.INSTANCE.setIsGoingToMainActivityFromSettings(true);

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Timber.tag(TAG + ".onOptionsItemSelected").i("Entered");

        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // endregion

    // region Private classes

    public static class SettingsFragment extends PreferenceFragment {
        private final String TAG = "SettingsFragment";

        // region Lifecycle methods

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            Timber.tag(TAG + ".onCreate").i("Entered");

            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);

            SwitchPreference logcatSwitch =
                    ((SwitchPreference) findPreference(
                            getResources().getString(R.string.util_key_debug)
                    ));
            logcatSwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (((boolean) newValue) != GlobalsManager.INSTANCE.isLogcatEnabled()) {
                        GlobalsManager.INSTANCE.setIsLogcatEnabled((boolean) newValue);
                        return true;
                    } else if (((boolean) newValue) == GlobalsManager.INSTANCE.isLogcatEnabled()) {
                        GlobalsManager.INSTANCE
                                .setIsLogcatEnabled(!GlobalsManager.INSTANCE.isLogcatEnabled());
                        return false;
                    } else {
                        return false;
                    }
                }
            });
        }

        // endregion
    }

    // endregion
}
