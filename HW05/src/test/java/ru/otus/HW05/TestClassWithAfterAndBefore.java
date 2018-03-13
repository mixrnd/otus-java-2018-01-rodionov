package ru.otus.HW05;

import ru.otus.HW05.annotations.Test;
import ru.otus.HW05.annotations.After;
import ru.otus.HW05.annotations.Before;

/**
 * Created by mix on 05.03.2018.
 */
public class TestClassWithAfterAndBefore {

    public static int setUpCallCounter = 0;

    public static int tearDownCallCounter = 0;

    @Before
    public void setUp() {
        setUpCallCounter++;
    }

    @Test
    public void testMethod() {

    }

    @After
    public void tearDown() {
        tearDownCallCounter++;
    }
}
