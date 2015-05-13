package com.bubbinator91.converter.util;

/**
 * This class is meant to keep track of any static globals.
 * This is more reliable than using the shared preferences
 * due to issues with context. Anything that would be deemed
 * personal information will not be used here as it would
 * be insecure and unsafe.
 */
public class Globals {
	public static String PREFERENCE_DECIMAL_PLACES = "decimal_places";
	public static String PREFERENCE_LOGCAT = "is_logcat_enabled";
	public static String PREFERENCE_FIRSTRUN = "is_first_run";

	public static boolean isTransitioningBackToMainActivity = false;
	public static boolean isLogcatEnabled = true;
	public static boolean isFirstRun = true;
	public static int decimalPlaceLength = 8;
}
