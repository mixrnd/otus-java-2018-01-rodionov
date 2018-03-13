package ru.otus.HW05.test_package;

import ru.otus.HW05.Assert;
import ru.otus.HW05.annotations.Test;

/**
 * Created by mix on 07.03.2018.
 */
public class RootPackageTestClass {

    @Test
    public void rootPackageMethod() {
        Assert.assertEquals(1,1);
    }
}
