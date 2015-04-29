package com.bubbinator91.converter.util;

import junit.framework.TestCase;

/**
 * Created by Christopher on 4/28/2015.
 */
public class UtilsTest extends TestCase {

	public void testIsNumeric() {
		assertEquals(true, Utils.isNumeric("0"));
		assertEquals(true, Utils.isNumeric("1"));
		assertEquals(true, Utils.isNumeric("1000000"));
		assertEquals(true, Utils.isNumeric("-1"));
		assertEquals(true, Utils.isNumeric("-1000000"));
		assertEquals(true, Utils.isNumeric("1.1"));
		assertEquals(true, Utils.isNumeric("-1.1"));

		assertEquals(false, Utils.isNumeric("1.1.1"));
		assertEquals(false, Utils.isNumeric("-1.1.1"));
		assertEquals(false, Utils.isNumeric("a"));
		assertEquals(false, Utils.isNumeric("#"));
	}

}
