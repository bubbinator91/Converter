package com.bubbinator91.converter.util;

/**
 * This class is meant to keep track of any static globals.
 * This is more reliable than using the shared preferences
 * due to issues with context. Anything that would be deemed
 * personal information will not be used here as it would
 * be insecure and unsafe.
 */
public class Globals {
	public static String PREFERENCE_DEBUG = "is_debug_enabled";
	public static String PREFERENCE_DECIMAL_PLACES = "decimal_places";

	public static boolean isTransitioningBackToMainActivity = false;
	public static boolean isDebugEnabled = false;
	public static int decimalPlaceLength = 8;
}
