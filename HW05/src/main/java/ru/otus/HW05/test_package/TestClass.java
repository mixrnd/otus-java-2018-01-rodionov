package ru.otus.HW05.test_package;

import ru.otus.HW05.Assert;
import ru.otus.HW05.annotations.Test;

/**
 * Created by mix on 13.03.2018.
 */
public class TestClass {
    @Test
    public void testMethod1() {
        Assert.assertEquals(1,2);
    }
}
