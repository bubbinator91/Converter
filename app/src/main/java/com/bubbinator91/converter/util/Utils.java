package com.bubbinator91.converter.util;

import android.text.Editable;

import java.text.DecimalFormatSymbols;

import timber.log.Timber;

public class Utils {
    private static final String TAG = "Utils";

    /**
     * Method that sanitizes the incoming Editable to filter
     * out anything that should not be there. This includes
     * things like more than one negative sign, a negative
     * sign not at the beginning, double decimal points
     * (".."), multiple decimal points ("99.99.99"), and
     * leading zeroes that are not proceeded by a decimal
     * point ("00009" or "-00009").
     *
     * @param editable		Editable to sanitize
     */
    public static void sanitizeEditable(Editable editable) {
        if ((editable == null) || (editable.length() == 0)) {
            return;
        }

        Timber.tag(TAG + ".sanitizeEditable").i("before = " + editable.toString());

        DecimalFormatSymbols currentLocaleSymbols = DecimalFormatSymbols.getInstance();
        char localeMinusSign = currentLocaleSymbols.getMinusSign();
        char localeDecimalSeparator = currentLocaleSymbols.getDecimalSeparator();
        boolean hasDecimalPoint = false;
        boolean hasLeadingSign = false;
        boolean hasLeadingZero = false;
        int i = 0;

        // If the number begins with a sign or a zero, move the start index forward, and mark the
        // appropriate flag.
        if (editable.charAt(0) == localeMinusSign) {
            hasLeadingSign = true;
            i = 1;
        } else if (editable.charAt(0) == '0') {
            hasLeadingZero = true;
            i = 1;
        }

        // If the number begins with a sign AND a zero (ex. -0), move the start index forward, and
        // mark the other appropriate flag.
        if (hasLeadingSign && (editable.length() >= 2)) {
            if (editable.charAt(1) == '0') {
                hasLeadingZero = true;
                i = 2;
            }
        }

        // Loop through the number and remove non-valid characters.
        // If the number has a leading zero, that has to be handled specially.
        while (i < editable.length()) {
            if (hasLeadingZero) {
                // If the current character is a decimal point, and a decimal point has not been
                // encountered yet, mark the appropriate flag.
                if ((editable.charAt(i) == localeDecimalSeparator) && !hasDecimalPoint) {
                    hasDecimalPoint = true;
                }
                // Otherwise, if a double decimal point has been encountered, or if the current
                // character is not a decimal point and not the numbers 1-9 and a decimal point has
                // not been encountered, remove the current character.
                else if (((editable.charAt(i) == localeDecimalSeparator) && hasDecimalPoint)
                        || ((editable.charAt(i) != localeDecimalSeparator)
                            && ((editable.charAt(i) < '1') || (editable.charAt(i) > '9'))
                            && !hasDecimalPoint)) {
                    editable.delete(i, i + 1);
                    i--;
                }
                // Otherwise, we've encountered the situation like "09" or "-04". In this case, we
                // need to start at the beginning (after a leading sign, if one is present), and
                // remove any leading zeroes before the non-zero number, and then mark the
                // flag for a leading zero as false, since we've removed it.
                else if ((editable.charAt(i) >= '1')
                        && (editable.charAt(i) <= '9')
                        && !hasDecimalPoint) {
                    // Determine the beginning point here. If there's a leading sign, skip it.
                    int j = 0;
                    if (hasLeadingSign) {
                        j = 1;
                    }

                    // Loop through the beginning of the number and remove any zeroes.
                    while (j < editable.length()) {
                        // If we encounter a non-zero digit, we're done, so break;
                        if ((editable.charAt(j) >= '1') && (editable.charAt(j) <= '9')) {
                            break;
                        }

                        editable.delete(j, j + 1);
                        i--;
                    }

                    // Since we've removed the leading zero, mark the flag as false.
                    hasLeadingZero = false;
                }
            } else {
                // If the current character is a decimal point, and we haven't encountered one yet,
                // mark the appropriate flag,
                if ((editable.charAt(i) == localeDecimalSeparator) && !hasDecimalPoint) {
                    hasDecimalPoint = true;
                }
                // Otherwise, if a double decimal point has been encountered, or if a minus sign has
                // been found to be not at the beginning of the number, or if the character is not
                // a valid digit, remove the current character.
                else if (((editable.charAt(i) == localeDecimalSeparator) && hasDecimalPoint)
                        || ((editable.charAt(i) == localeMinusSign) && (i != 0))
                        || (editable.charAt(i) != localeMinusSign
                            && (!Character.isDigit(editable.charAt(i))
                                || Character.isSpaceChar(editable.charAt(i))))) {
                    editable.delete(i, i + 1);
                    i--;
                }
            }

            i++;
        }

        Timber.tag(TAG + ".sanitizeEditable").i("after = " + editable.toString());
    }
}

