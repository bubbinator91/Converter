package com.bubbinator91.converter.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.Utils;


public class SettingsActivity extends BaseActivity {
	private final String TAG = "SettingsActivity";

	private SharedPreferences mPrefs;

	private EditText editTextFieldLength;

	private TextWatcher textWatcherFieldLength = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
			editTextFieldLength.removeTextChangedListener(textWatcherFieldLength);

			if (Utils.isDebugEnabled(getApplicationContext())) {
				Log.d(TAG + ".textWatcherFieldLength.s.before", s.toString());
			}

			if (s.length() != 0) {
				s = Utils.sanitizeEditable(s);
				if (s != null) {
					if (Utils.isNumeric(s.toString())) {
						int fieldLength = Integer.parseInt(s.toString());

						SharedPreferences.Editor editor = mPrefs.edit();
						editor.putInt(Utils.PREFERENCE_FIELD_LENGTH, fieldLength);
						editor.apply();
					}
				}
			} else {
				editTextFieldLength.setText("", TextView.BufferType.EDITABLE);
				SharedPreferences.Editor editor = mPrefs.edit();
				editor.putInt(Utils.PREFERENCE_FIELD_LENGTH, 8);
				editor.apply();
			}

			if (Utils.isDebugEnabled(getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherFieldLength.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherFieldLength.s.after", "null");
			}

			editTextFieldLength.addTextChangedListener(textWatcherFieldLength);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (Utils.isDebugEnabled(this)) {
			Log.d(TAG + ".onCreate", "Entered");
		}
		super.onCreate(savedInstanceState);

		editTextFieldLength = (EditText) findViewById(R.id.editText_activity_settings_length);
		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

		if (mPrefs != null) {
			int isDebugOn = mPrefs.getInt(Utils.PREFERENCE_DEBUG, -1);
			Switch debugSwitch = (Switch) findViewById(R.id.activity_settings_switch_debug);
			if (isDebugOn == -1) {
				SharedPreferences.Editor editor = mPrefs.edit();
				editor.putInt(Utils.PREFERENCE_DEBUG, 0);
				editor.apply();
				debugSwitch.setChecked(false);
			} else if (isDebugOn == 1) {
				debugSwitch.setChecked(true);
			} else {
				debugSwitch.setChecked(false);
			}

			int fieldLength = mPrefs.getInt(Utils.PREFERENCE_FIELD_LENGTH, -1);
			if (fieldLength == -1) {
				fieldLength = 8;
				SharedPreferences.Editor editor = mPrefs.edit();
				editor.putInt(Utils.PREFERENCE_FIELD_LENGTH, fieldLength);
				editor.apply();
			}

			editTextFieldLength.setText(String.valueOf(fieldLength), TextView.BufferType.EDITABLE);
		}

		editTextFieldLength.addTextChangedListener(textWatcherFieldLength);
	}

	@Override
	protected void onDestroy() {
		if (Utils.isDebugEnabled(this)) {
			Log.d(TAG + ".onDestroy", "Entered");
		}
		super.onDestroy();

		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putInt(Utils.PREFERENCE_TRANS_TO_SETTINGS, 0);
		editor.apply();
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (Utils.isDebugEnabled(this)) {
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

	public void onDebugSwitchToggled(View view) {
		if (Utils.isDebugEnabled(this)) {
			Log.d(TAG + ".onDebugSwitchToggled", "Entered");
		}

		boolean isOn = ((Switch)view).isChecked();
		SharedPreferences.Editor editor = mPrefs.edit();

		if (isOn) {
			if (Utils.isDebugEnabled(this)) {
				Log.d(TAG + ".onDebugSwitchToggled", "Debug is on");
			}
			editor.putInt(Utils.PREFERENCE_DEBUG, 1);
			editor.apply();
		} else {
			if (Utils.isDebugEnabled(this)) {
				Log.d(TAG + ".onDebugSwitchToggled", "Debug is off");
			}
			editor.putInt(Utils.PREFERENCE_DEBUG, 0);
			editor.apply();
		}
	}

	@Override
	protected int getLayoutResourceId() { return R.layout.activity_settings; }
}
