package com.bubbinator91.converter.views.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.views.fragments.*;
import com.bubbinator91.converter.util.Globals;
import com.bubbinator91.converter.util.GlobalsManager;
import com.bubbinator91.converter.views.base.BaseActivity;

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

    public void loadFragment(String name) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (name) {
            case "Acceleration":
                fragmentManager.beginTransaction()
                        .replace(R.id.test_frag_container, new AccelerationFragment())
                        .commit();
                break;
            case "DTS":
                fragmentManager.beginTransaction()
                        .replace(R.id.test_frag_container, new DataTransferSpeedFragment())
                        .commit();
                break;
            case "Fuel":
                fragmentManager.beginTransaction()
                        .replace(R.id.test_frag_container, new FuelConsumptionFragment())
                        .commit();
                break;
            case "Length":
                fragmentManager.beginTransaction()
                        .replace(R.id.test_frag_container, new LengthFragment())
                        .commit();
                break;
            case "Speed":
                fragmentManager.beginTransaction()
                        .replace(R.id.test_frag_container, new SpeedFragment())
                        .commit();
                break;
            case "Temperature":
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
