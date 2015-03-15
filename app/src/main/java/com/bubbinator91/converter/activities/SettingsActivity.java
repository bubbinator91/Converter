package com.bubbinator91.converter.activities;

import android.content.SharedPreferences;
import android.graphics.Typeface;
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
import com.bubbinator91.converter.Util;

import java.lang.reflect.Field;

public class SettingsActivity extends BaseActivity {
	private boolean DEBUG = false;
	private final String TAG = "SettingsActivity";

	private EditText editTextFieldLength;

	private TextWatcher textWatcherFieldLength = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
			editTextFieldLength.removeTextChangedListener(textWatcherFieldLength);

			if (DEBUG)
				Log.d(TAG + ".textWatcherFieldLength.s.before", s.toString());

			if (s.length() != 0) {
				s = Util.sanitizeEditable(s);
				if (s != null) {
					if (Util.isNumeric(s.toString())) {
						int fieldLength = Integer.parseInt(s.toString());

						SharedPreferences.Editor editor = mPrefs.edit();
						editor.putInt(Util.PREFERENCE_FIELD_LENGTH, fieldLength);
						editor.apply();
					}
				}
			} else {
				editTextFieldLength.setText("", TextView.BufferType.EDITABLE);
				SharedPreferences.Editor editor = mPrefs.edit();
				editor.putInt(Util.PREFERENCE_FIELD_LENGTH, 8);
				editor.apply();
			}

			if (DEBUG && (s != null))
				Log.d(TAG + ".textWatcherFieldLength.s.after", s.toString());
			else if (DEBUG && (s == null))
				Log.d(TAG + ".textWatcherFieldLength.s.after", "null");

			editTextFieldLength.addTextChangedListener(textWatcherFieldLength);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private SharedPreferences mPrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (DEBUG)
			Log.d(TAG + ".onCreate", "Entered");

		super.onCreate(savedInstanceState);

		editTextFieldLength = (EditText) findViewById(R.id.editText_activity_settings_length);
		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

		if (mPrefs != null) {
			int isDebugOn = mPrefs.getInt(Util.PREFERENCE_DEBUG, -1);
			Switch debugSwitch = (Switch) findViewById(R.id.activity_settings_switch_debug);
			if (isDebugOn == -1) {
				SharedPreferences.Editor editor = mPrefs.edit();
				editor.putInt(Util.PREFERENCE_DEBUG, 0);
				editor.apply();
				DEBUG = false;
				debugSwitch.setChecked(false);
			} else if (isDebugOn == 1) {
				debugSwitch.setChecked(true);
			} else {
				debugSwitch.setChecked(false);
			}

			int fieldLength = mPrefs.getInt(Util.PREFERENCE_FIELD_LENGTH, -1);
			if (fieldLength == -1) {
				fieldLength = 8;
				SharedPreferences.Editor editor = mPrefs.edit();
				editor.putInt(Util.PREFERENCE_FIELD_LENGTH, fieldLength);
				editor.apply();
			}

			editTextFieldLength.setText(String.valueOf(fieldLength), TextView.BufferType.EDITABLE);
		}

		editTextFieldLength.addTextChangedListener(textWatcherFieldLength);

		TextView toolbarTitle = getToolbarTextView();
		if (toolbarTitle != null) {
			Typeface font = Typeface.create("sans-serif", Typeface.NORMAL);
			toolbarTitle.setTypeface(font);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (DEBUG)
			Log.d(TAG + ".onOptionsItemSelected", "Entered");

		switch(item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	public void onDebugSwitchToggled(View view) {
		if (DEBUG)
			Log.d(TAG + ".onDebugSwitchToggled", "Entered");

		boolean isOn = ((Switch)view).isChecked();
		SharedPreferences.Editor editor = mPrefs.edit();

		if (isOn) {
			if (DEBUG)
				Log.d(TAG + ".onDebugSwitchToggled", "Debug is on");
			editor.putInt(Util.PREFERENCE_DEBUG, 1);
			editor.apply();
			DEBUG = true;
		} else {
			if (DEBUG)
				Log.d(TAG + ".onDebugSwitchToggled", "Debug is off");
			editor.putInt(Util.PREFERENCE_DEBUG, 0);
			editor.apply();
			DEBUG = false;
		}
	}

	private TextView getToolbarTextView() {
		TextView tv;

		try {
			Field f = mToolbar.getClass().getDeclaredField("mTitleTextView");
			f.setAccessible(true);
			tv = (TextView)f.get(mToolbar);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			tv = null;
		}

		return tv;
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_settings;
	}
}
