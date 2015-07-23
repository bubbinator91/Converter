package com.bubbinator91.conversion.util;

import java.util.ArrayList;
import java.util.List;

/**
 * The base class that all unit types should extend from. It provides each class with useful methods
 * that would be repeated in each class if this class didn't exist.
 */
public abstract class Unit {

    /**
     * Adds empty items (the {@link String} object with the value "") to the given list.
     *
     * @param list      The {@link List} to add the empty items to.
     * @param numItems  The number of empty items to add.
     */
    protected static void addEmptyItems(List<String> list, int numItems) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.clear();
        for (int i = 0; i < numItems; i++) {
            list.add("");
        }
    }

    /**
     * Method to check if a string is purely numeric.
     *
     * @param string    The string to check.
     *
     * @return  A boolean value indicating whether or not the string is numeric.
     */
    protected static boolean isNumeric(String string) {
        if (string.isEmpty()) {
            return false;
        }

        final char[] chars = string.toCharArray();
        int size = chars.length;
        boolean hasDecimalPoint = false;

        /**
         * Check if the leading character is a decimal point, and keep track of it if it is.
         * If not, check to see if it's not a negative sign and if it's not a valid integer, and return false if
         * either of those two things are true.
         */
        if ((size == 1) && ((chars[0] < '0') || (chars[0] > '9'))) {
            return false;
        }
        if (chars[0] == '.') {
            hasDecimalPoint = true;
        } else if ((chars[0] != '-') && ((chars[0] < '0') || (chars[0] > '9'))) {
            return false;
        }

        /**
         * Scan the rest of the character array for weird things.
         * If the character is a decimal point, and if a decimal point has already been encountered (meaning that there
         *   are two decimals in the number), then it returns false;
         * If the character is a decimal point, and if a decimal point hasn't already been encountered (meaning that only
         *   one decimal point is present at this stage of scanning), then it sets a flag so that it can remember that a
         *   decimal point has been encountered.
         * Otherwise, if the current character is not a valid integer, then it returns false.
         */
        for (int i = 1; i < size; i++) {
            if ((chars[i] == '.') && (hasDecimalPoint)) {
                return false;
            } else if ((chars[i] == '.') && (!hasDecimalPoint)) {
                hasDecimalPoint = true;
            } else if ((chars[i] < '0') || (chars[i] > '9')) {
                return false;
            }
        }

        return true;
    }
}
