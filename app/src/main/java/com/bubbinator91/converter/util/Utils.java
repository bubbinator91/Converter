package com.bubbinator91.converter.util;

import android.text.Editable;

import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import timber.log.Timber;

/**
 * Conversions to implement
 * Acceleration
 *  -meter/square second (m/s^2)
 *  -foot/square second (ft/s^2)
 *  -standard gravity (g sub n)
 *  -gal (cm/s^2)
 * Area
 *  -square kilometer (km^2)
 *  -hectare (ha)
 *  -square meter (m^2)
 *  -square centimeter (cm^2)
 *  -square millimeter(mm^2)
 *  -square mile (mi^2)
 *  -square yard (yd^2)
 *  -square foot (ft^2)
 *  -square inch (in^2)
 *  -acre
 * Currency
 *  -maybe later
 * Data storage
 *  -bit
 *  -byte
 *  -kilobit
 *  -kilobyte
 *  -kibibit
 *  -megabit
 *  -megabyte
 *  -mebibit
 *  -mebibyte
 *  -gigabit
 *  -gigabyte
 *  -gibibit
 *  -gibibyte
 *  -terabit
 *  -terabyte
 *  -tebibit
 *  -tebibyte
 * Energy
 *  -megajoule (MJ)
 *  -kilojoule (kJ)
 *  -joule (J)
 *  -ergs
 *  -electronvolt (eV)
 *  -kilocalorie (kcal)
 *  -calorie (cal)
 *  -foot-pound (ft-lbf)
 *  -inch-pound (in-lbf)
 *  -kilowatt-hour (kWh)
 *  -watt-hour (Wh)
 *  -british thermal unit (btu)
 * Force
 *  -newton (N)
 *  -dyne (dyn)
 *  -kilogram-force (kgf)
 *  -gram-force (gf)
 *  -pound-force (lbf)
 *  -ounce-force (ozf)
 * Fuel consumption
 *  -US miles per gallon (mpg)
 *  -Imperial miles per gallon (mpg)
 *  -kilometer/liter (km/L)
 *  -liter/100 Kilometer (L/100km)
 * Mass
 *  -metric ton
 *  -kilogram (kg)
 *  -gram (g)
 *  -milligram (mg)
 *  -microgram (mcg)
 *  -long ton
 *  -short ton
 *  -stone (st)
 *  -pound (lb)
 *  -ounce (oz)
 *  -troy ounce (oz t)
 * Numerical systems
 *  -decimal
 *  -binary
 *  -octal
 *  -hexadecimal
 * Power
 *  -megawatt (MW)
 *  -kilowatt (kW)
 *  -watt (W)
 *  -milliwatt (Wh)
 *  -metric horsepower (hp)
 *  -uk horsepower (hp)
 * Pressure
 *  -megapascal (mPa)
 *  -kilopascal (kPa)
 *  -pascal (Pa)
 *  -bar
 *  -pounds per square inch (psi)
 *  -pounds per square foot (psf)
 *  -atmosphere (atm)
 *  -millimeters of mercury (mmHg)
 *  -inches of mercury (inHg)
 *  -millimeters of water (mmH2O)
 *  -torr
 * Shoe Size
 *  -us size
 *  -uk size
 *  -euro size
 *  -centimeter (cm)
 *  -inch (in)
 * Time
 *  -nanosecond
 *  -microsecond
 *  -millisecond
 *  -second
 *  -minute
 *  -hour
 *  -day
 *  -week
 *  -month
 *  -year
 *  -decade
 *  -century
 * Torque
 *  -newton-meter (N-m)
 *  -meter-kilogram (m-kg)
 *  -foot-pound force (ft-lbf)
 *  -foot-poundal (ft-pdl)
 *  -inch-pound force (in-lbf)
 * Volume
 *  -US gallon (gal)
 *  -US quart (qt)
 *  -US pint (pt)
 *  -US cup
 *  -US Ounce (oz)
 *  -US fluid ounce (fl oz)
 *  -US tablespoon (tbsp)
 *  -US teaspoon (tsp)
 *  -cubic meter (m^3)
 *  -liter (L)
 *  -deciliter (dL)
 *  -milliliter (mL)
 *  -imperial gallon (gal)
 *  -imperial quart (qt)
 *  -imperial pint (pt)
 *  -imperial ounce (oz)
 *  -imperial fluid ounce (fl oz)
 *  -imperial tablespoon (tbsp)
 *  -imperial teaspoon (tsp)
 *  -cubic foot (ft^3)
 *  -cubic inch (in^3)
 * Volumetric flow
 *  -cubic feet per minute (ft^3/min)
 *  -cubic meters per second (m^3/sec)
 *  -cubic meters per minute (m^3/min)
 *  -cubic meters per hour (m^3/hour)
 *  -liters per second (L/sec)
 *  -liters per minute (L/min)
 *  -liters per hour (L/h)
 *  -US gallons per day (gpd)
 *  -US gallons per minute (gpm)
 *  -imperial gallons per day (gpd)
 *  -imperial gallons per minute (gpm)
 */

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

    /**
     * Method to check if a string is purely numeric.
     *
     * @param string		The string to check
	 *
     * @return  A boolean value indicating whether or not the string is numeric
     */
    public static boolean isNumeric(String string) {
        if (string.isEmpty() || string.equals("-.")) {
            return false;
        }

        final char[] chars = string.toCharArray();
        int size = chars.length;
        DecimalFormatSymbols currentLocaleSymbols = DecimalFormatSymbols.getInstance();
        char localeMinusSign = currentLocaleSymbols.getMinusSign();
        char localeDecimalSeparator = currentLocaleSymbols.getDecimalSeparator();
        boolean hasDecimalPoint = false;

        /**
         * If the size is one, return false if the character is not a number.
         * Check if the leading character is a decimal point, and keep track of it if it is.
         * If not, check to see if it's not a negative sign and if it's not a valid integer, and
         *   return false if either of those two things are true.
         */
        if ((size == 1) && !Character.isDigit(chars[0])) {
            return false;
        } else if (chars[0] == localeDecimalSeparator) {
            hasDecimalPoint = true;
        } else if ((chars[0] != localeMinusSign) && !Character.isDigit(chars[0])) {
            return false;
        }

        /**
         * Scan the rest of the character array for weird things.
         * If the character is a decimal point, and if a decimal point has already been encountered
         *   (meaning that there are two decimals in the number), then it returns false.
         * If the character is a decimal point, and if a decimal point hasn't already been
         *   encountered (meaning that only one decimal point is present at this stage of scanning),
         *   then it sets a flag so that it can remember that a decimal point has been encountered.
         * Otherwise, if the current character is not a valid integer, then it returns false.
         */
        for (int i = 1; i < size; i++) {
            if ((chars[i] == localeDecimalSeparator) && (hasDecimalPoint)) {
                return false;
            } else if ((chars[i] == localeDecimalSeparator) && (!hasDecimalPoint)) {
                hasDecimalPoint = true;
            } else if ((chars[i] < '0') || (chars[i] > '9')) {
                return false;
            }
        }

        return true;
    }

    public static void addWhitespaceItems(ArrayList<String> list, int numItems) {
        Timber.tag(TAG + ".addWhitespaceItems").i("Entered");
        if (list == null) {
            list = new ArrayList<>();
        }
        list.clear();
        for (int i = 0; i < numItems; i++) {
            list.add("");
        }
    }
}

