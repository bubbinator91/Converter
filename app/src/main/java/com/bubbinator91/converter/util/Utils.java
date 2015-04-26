package com.bubbinator91.converter.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Editable;

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
	public static String PREFERENCE_DEBUG = "is_debug_enabled";
	public static String PREFERENCE_DECIMAL_PLACES = "decimal_places";
	public static String PREFERENCE_TRANS_FROM_SETTINGS = "transitioned_from_settings";

	public static SharedPreferences PREFS = null;

    public Utils() {}

	public static boolean isDebugEnabled(Context context) {
		if (context != null) {
			PREFS = PreferenceManager.getDefaultSharedPreferences(context);
			return PREFS.getBoolean(PREFERENCE_DEBUG, false);
		} else {
			throw new NullPointerException("Context parameter cannot be null");
		}
	}

    /**
     * Method that sanitizes the incoming Editable to filter
     * out anything that should not be there. This includes
     * things like more than one negative sign, a negative
     * sign not at the beginning, double decimal points
     * (".."), multiple decimal points ("99.99.99"), and
     * leading zeroes that are not proceeded by a decimal
     * point ("00009" or "-00009").
     *
     * @param s Editable to sanitize
     * @return An object of type Editable that has been sanitized
     */
    public static Editable sanitizeEditable(Editable s) {
        if (s == null)
            return null;

        Editable ret = s;
        if (ret.length() >= 2) {
            //check for (a) leading zero(s) without a decimal point after it/them
            if ((ret.charAt(0) == '0')) {
                int j = 0;
                boolean containsAllZeroes = false;
                for (int i = 0; i < ret.length(); i++) {
                    if (ret.charAt(i) == '0') {
                        j++;
                    } else {
                        break;
                    }
                    if (i == (ret.length() - 1))
                        containsAllZeroes = true;
                }
                if (!containsAllZeroes)
                    ret.delete(0, j);
            } else if ((ret.charAt(0) == '-') && (ret.charAt(1) == '0')) {
                int j = 0;
                boolean containsAllZeroes = false;
                for (int i = 1; i < ret.length(); i++) {
                    if (ret.charAt(i) == '0') {
                        j++;
                    } else {
                        break;
                    }
                    if (i == (ret.length() - 1))
                        containsAllZeroes = true;
                }
                if (!containsAllZeroes)
                    ret.delete(1, j+1);
            }
        }
        // check for weird decimals and weird negative signs
        boolean containsDecimal = false;
        for (int i = 0; i < ret.length(); i++) {
            if (i == 0) {
                if (ret.charAt(0) == '.')
                    containsDecimal = true;
            } else if (i > 0) {
                if ((ret.charAt(i) == '-') || ((ret.charAt(i) == '.') && containsDecimal)) {
                    ret.delete(i, i + 1);
                } else if (s.charAt(i) == '.') {
                    containsDecimal = true;
                }
            }
        }

        return ret;
    }

    /**
     * Method to check if a string is purely numeric
     *
     * @param str The string to check
     * @return A boolean value indicating whether or not
     * the string is numeric
     */
    public static boolean isNumeric(final String str) {
        if (str.isEmpty()) {
            return false;
        }
        final char[] chars = str.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;

        // deal with any possible sign up front
        final int start = (chars[0] == '-') ? 1 : 0;
        if (sz > start + 1 && chars[start] == '0') { // leading 0
            if ((chars[start + 1] == 'x') || (chars[start + 1] == 'X')) { // leading 0x/0X
                int i = start + 2;
                if (i == sz)
                    return false; // str == "0x"

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
        sz--; // don't want to loop to the last char, check it afterwords for type qualifiers
        int i = start;
        // loop to the next to last char or to the last char if we need another digit to
        // make a valid number (e.g. chars[0..5] = "1234E")
        while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                foundDigit = true;
                allowSigns = false;
            } else if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent
                    return false;
                }
                hasDecPoint = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                // we've already taken care of hex.
                if (hasExp) {
                    // two E's
                    return false;
                }
                if (!foundDigit) {
                    return false;
                }
                hasExp = true;
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
                if (hasDecPoint || hasExp) {
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
                return foundDigit && !hasExp && !hasDecPoint;
            }
            // last character is illegal
            return false;
        }
        // allowSigns is true iff the val ends in 'E'
        // found digit it to make sure weird stuff like '.' and '1E-' doesn't pass
        return !allowSigns && foundDigit;
    }
}

