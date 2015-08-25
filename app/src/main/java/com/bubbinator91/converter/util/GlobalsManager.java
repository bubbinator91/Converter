package com.bubbinator91.converter.util;

/**
 * Manages access to global variables. Follows a singleton pattern so that data is synchronized
 * across classes.
 *
 * To utilize one of the methods of the singleton, one must obtain an instance of the singleton
 * first. Ex. GlobalsManager.INSTANCE.isLogcatEnabled();
 */
public enum GlobalsManager {
    INSTANCE;

    private boolean isGoingBackToMainActivityFromSettings, isLogcatEnabled, isFirstRun;
    private int decimalPlaceLength;

    GlobalsManager() {
        isGoingBackToMainActivityFromSettings = false;
        isLogcatEnabled = true;
        isFirstRun = true;
        decimalPlaceLength = 8;
    }

    /**
     * Gets the status of whether or not the app is transitioning to the main activity from the
     * settings activity.
     *
     * @return  false if the app is not transitioning to the main activity; true otherwise.
     */
    public boolean isGoingToMainActivityFromSettings() { return isGoingBackToMainActivityFromSettings; }

    /**
     * Gets the status of whether or not information messages should be written to the logcat.
     *
     * @return  false if information messages should not be written; true otherwise.
     */
    public boolean isLogcatEnabled() { return isLogcatEnabled; }

    /**
     * Gets the status of whether or not the user has already seen the first run information.
     *
     * @return  false if the user has already seen the first run information; true otherwise.
     */
    public boolean isFirstRun() { return isFirstRun; }

    /**
     * Gets the amount of decimal places the text fields should display after a conversion.
     *
     * @return  An integer value containing the amount of decimal places to display.
     */
    public int decimalPlaceLength() { return decimalPlaceLength; }

    /**
     * Sets the status of whether or not the app is transitioning to the main activity from the
     * settings activity.
     *
     * @param state     false if the app is not transitioning to the main activity; true otherwise.
     */
    public void setIsGoingToMainActivityFromSettings(boolean state) {
        isGoingBackToMainActivityFromSettings = state;
    }

    /**
     * Sets the status of whether or not information messages should be written to the logcat.
     *
     * @param state     false if information messages should not be written; true otherwise.
     */
    public void setIsLogcatEnabled(boolean state) { isLogcatEnabled = state; }

    /**
     * Sets the status of whether or not the user has already seen the first run information.
     *
     * @param state     false if the user has already seen the first run information; true
     *                  otherwise.
     */
    public void setIsFirstRun(boolean state) { isFirstRun = state; }

    /**
     * Sets the amount of decimal places the text fields should display after a conversion.
     *
     * @param length    An integer value containing the amount of decimal places to display.
     */
    public void setDecimalPlaceLength(int length) { decimalPlaceLength = length; }
}
