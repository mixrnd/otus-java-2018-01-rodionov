package ru.otus.HW05;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by mix on 04.03.2018.
 */
public class ReflectionHelperTest {

    private TestClass testClass = ReflectionHelper.instantiate(TestClass.class);

    @Test
    public void getMethods() throws Exception {
        Method[] methods = ReflectionHelper.getMethods(testClass);
        boolean testMethodFound = false;
        for (Method m : methods) {
            if (m.getName().equals("testMethod")) {
                testMethodFound = true;
                break;
            }
        }
        assertTrue(testMethodFound);
    }

    @Test
    public void methodWithAnnotation() throws Exception {
        Method[] methods = ReflectionHelper.getMethodsWithAnnotation(testClass, ru.otus.HW05.annotations.Test.class);

        assertEquals(2, methods.length);
        assertEquals("testMethod", methods[0].getName());
        assertEquals("anotherTestMethod", methods[1].getName());

    }
}