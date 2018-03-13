package ru.otus.HW05;

/**
 * Created by mix on 04.03.2018.
 */
public class Assert {

    public static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError("Error : expected - " + expected + ", actual - " + actual);
        }
    }
}
