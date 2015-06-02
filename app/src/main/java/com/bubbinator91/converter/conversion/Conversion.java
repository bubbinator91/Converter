package com.bubbinator91.converter.conversion;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper enum that provides some error codes, along with some static helper methods.
 */
public enum Conversion {
    ERROR_NONE,
    ERROR_BELOW_ABSOLUTE_ZERO,
    ERROR_INPUT_NOT_NUMERIC,
    ERROR_UNKNOWN;

    public static void addWhitespaceItems(List<String> list, int numItems) {
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
     * @param string		The string to check
     *
     * @return  A boolean value indicating whether or not the string is numeric
     */
    @SuppressWarnings("all")
    public static boolean isNumeric(String string) {
        if (string.isEmpty()) {
            return false;
        }
        final char[] chars = string.toCharArray();
        int size = chars.length;
        boolean hasExponent = false;
        boolean hasDecimalPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;

        // deal with any possible sign up front
        final int start = (chars[0] == '-') ? 1 : 0;
        if (size > start + 1 && chars[start] == '0') { // leading 0
            if ((chars[start + 1] == 'x') || (chars[start + 1] == 'X')) { // leading 0x/0X
                int i = start + 2;
                if (i == size) {
                    return false; // str == "0x"
                }

                // checking hex (it can't be anything else)
                for (; i < chars.length; i++) {
                    if ((chars[i] < '0' || chars[i] > '9')
                            && (chars[i] < 'a' || chars[i] > 'f')
                            && (chars[i] < 'A' || chars[i] > 'F')) {
                        return false;
                    }
                }
                return true;
            } else if (Character.isDigit(chars[start + 1])) {
                // leading 0, but not hex, must be octal
                int i = start + 1;
                for (; i < chars.length; i++) {
                    if (chars[i] < '0' || chars[i] > '7') {
                        return false;
                    }
                }
                return true;
            }
        }
        size--; // don't want to loop to the last char, check it afterwords for type qualifiers
        int i = start;
        // loop to the next to last char or to the last char if we need another digit to
        // make a valid number (e.g. chars[0..5] = "1234E")
        while (i < size || (i < size + 1 && allowSigns && !foundDigit)) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                foundDigit = true;
                allowSigns = false;
            } else if (chars[i] == '.') {
                if (hasDecimalPoint || hasExponent) {
                    // two decimal points or dec in exponent
                    return false;
                }
                hasDecimalPoint = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                // we've already taken care of hex.
                if (hasExponent) {
                    // two E's
                    return false;
                }
                if (!foundDigit) {
                    return false;
                }
                hasExponent = true;
                allowSigns = true;
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (!allowSigns) {
                    return false;
                }
                allowSigns = false;
                foundDigit = false; // we need a digit after the E
            } else {
                return false;
            }
            i++;
        }
        if (i < chars.length) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                // no type qualifier, OK
                return true;
            }
            if (chars[i] == 'e' || chars[i] == 'E') {
                // can't have an E at the last byte
                return false;
            }
            if (chars[i] == '.') {
                if (hasDecimalPoint || hasExponent) {
                    // two decimal points or dec in exponent
                    return false;
                }
                // single trailing decimal point after non-exponent is ok
                return foundDigit;
            }
            if (!allowSigns && (chars[i] == 'd' || chars[i] == 'D' || chars[i] == 'f' || chars[i] == 'F')) {
                return foundDigit;
            }
            if ((chars[i] == 'l') || (chars[i] == 'L')) {
                // not allowing L with an exponent or decimal point
                return foundDigit && !hasExponent && !hasDecimalPoint;
            }
            // last character is illegal
            return false;
        }
        // allowSigns is true iff the val ends in 'E'
        // found digit is to make sure weird stuff like '.' and '1E-' doesn't pass
        return !allowSigns && foundDigit;
    }
}
