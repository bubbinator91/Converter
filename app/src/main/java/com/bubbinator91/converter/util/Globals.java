package com.bubbinator91.converter.util;

/**
 * This class is meant to keep track of any static globals.
 * This is more reliable than using the shared preferences
 * due to issues with context. Anything that would be deemed
 * personal information will not be used here as it would
 * be insecure and unsafe.
 */
public class Globals {
	public static final String PREFERENCE_DECIMAL_PLACES = "decimal_places";
	public static final String PREFERENCE_LOGCAT = "is_logcat_enabled";
	public static final String PREFERENCE_FIRSTRUN = "is_first_run";
}
