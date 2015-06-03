package com.bubbinator91.conversion.util;

/**
 * Helper class that allows the storage of two completely different objects. Useful if you have a
 * method that should return two things (such as a null value and an error code).
 *
 * Ex. Tuple<String, int> tuple = new Tuple<>(null, ERROR_CODE);
 */
public class Tuple<X, Y> {
    private final X x;
    private final Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getValue0() {
        return x;
    }

    public Y getValue1() {
        return y;
    }
}
