package com.bubbinator91.converter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bubbinator91.converter.R;

/**
 * Created by Christopher on 4/25/2015.
 */
public abstract class BaseActivity extends AppCompatActivity {
	protected Toolbar mToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResourceId());
		mToolbar = ((Toolbar) findViewById(R.id.toolbar));
		if (mToolbar != null) {
			setSupportActionBar(mToolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setHomeButtonEnabled(true);
		}
	}

	protected abstract int getLayoutResourceId();

	protected void setToolbarIcon(int iconResId) {
		if (mToolbar != null) {
			mToolbar.setNavigationIcon(iconResId);
		}
	}
}
