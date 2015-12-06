package com.bubbinator91.converter.conversion.util;

/**
 * A POJO that allows the user to store two items in one object. Provided as an alternative to
 * the built in Pair class, so that the library can be unit tested without a device.
 */
public class Tuple<A, B> {
    private A first;
    private B second;

    public Tuple(A a, B b) {
        first = a;
        second = b;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public void setFirst(A a) {
        first = a;
    }

    public void setSecond(B b) {
        second = b;
    }
}
