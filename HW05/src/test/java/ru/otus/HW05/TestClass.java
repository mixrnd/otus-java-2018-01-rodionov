package ru.otus.HW05;

import ru.otus.HW05.annotations.Test;

/**
 * Created by mix on 04.03.2018.
 */
public class TestClass {
    @Test
    public void testMethod() {
        throw new AssertionError("exceptionTestMethod");
    }

    @Test
    public void anotherTestMethod() {

    }
}
