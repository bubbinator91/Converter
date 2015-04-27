package com.bubbinator91.converter.util;

/**
 * Created by Christopher on 4/26/2015.
 */
public class NavigationDrawerItem {
	private String mTitle;
	private int mIcon;

	public NavigationDrawerItem(String title, int icon) {
		mTitle = title;
		mIcon = icon;
	}

	public String getTitle() {
		return mTitle;
	}

	public int getIcon() {
		return mIcon;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public void setIcon(int icon) {
		mIcon = icon;
	}
}
