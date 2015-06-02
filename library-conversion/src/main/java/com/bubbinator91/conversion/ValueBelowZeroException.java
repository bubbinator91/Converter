package com.bubbinator91.conversion;

/**
 * Custom exception that is thrown if a value is below absolute zero.
 */
public class ValueBelowZeroException extends Exception {
    public ValueBelowZeroException() { super(); }

    public ValueBelowZeroException(String message) { super(message); }

    public ValueBelowZeroException(Throwable throwable) { super(throwable); }

    public ValueBelowZeroException(String message, Throwable throwable) { super(message, throwable); }
}
