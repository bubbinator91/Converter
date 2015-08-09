package com.bubbinator91.converter.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.fragments.AccelerationFragment;
import com.bubbinator91.converter.fragments.DataTransferSpeedFragment;
import com.bubbinator91.converter.fragments.LengthFragment;
import com.bubbinator91.converter.fragments.SpeedFragment;
import com.bubbinator91.converter.fragments.TemperatureFragment;
import com.bubbinator91.converter.util.Globals;
import com.bubbinator91.converter.util.GlobalsManager;

/**
 * An activity that aids in testing the UI of the fragments.
 */
public class TestActivity extends BaseActivity {
    private final String TAG = TestActivity.class.getSimpleName();

    // region Lifecycle methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // endregion

    // region Helper methods

    public void setDecimalPlaces(int decimalPlaces) {
        GlobalsManager.INSTANCE.setDecimalPlaceLength(decimalPlaces);
        PreferenceManager
                .getDefaultSharedPreferences(this)
                .edit()
                .putString(Globals.PREFERENCE_DECIMAL_PLACES, Integer.toString(decimalPlaces))
                .apply();
    }

    public void loadFragment(int id) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (id) {
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.test_frag_container, new AccelerationFragment())
                        .commit();
                break;
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.test_frag_container, new DataTransferSpeedFragment())
                        .commit();
                break;
            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.test_frag_container, new LengthFragment())
                        .commit();
                break;
            case 3:
                fragmentManager.beginTransaction()
                        .replace(R.id.test_frag_container, new SpeedFragment())
                        .commit();
                break;
            case 4:
                fragmentManager.beginTransaction()
                        .replace(R.id.test_frag_container, new TemperatureFragment())
                        .commit();
                break;
            default:
                break;
        }
    }

    // endregion

    // region Overridden BaseActivity methods

    @Override
    protected int getLayoutResourceId() { return R.layout.activity_test; }

    @Override
    protected String getChildTag() { return TAG; }

    // endregion
}
