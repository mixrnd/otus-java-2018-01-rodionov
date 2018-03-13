package ru.otus.HW05;

import ru.otus.HW05.annotations.After;
import ru.otus.HW05.annotations.Before;
import ru.otus.HW05.annotations.Test;

import java.lang.reflect.Method;

/**
 * Created by mix on 04.03.2018.
 */
public class TestClassRunner {

    private Class testClassClass;

    private boolean hasBefore;
    private Method before;

    private boolean hasAfter;
    private Method after;

    public TestClassRunner(Class testClassClass) {
        this.testClassClass = testClassClass;
    }

    public TestClassRunner(String testClassName) throws ClassNotFoundException {
        this.testClassClass = Class.forName(testClassName);
    }

    public TestClassResult run() throws Throwable {
        Object classUnderTest = ReflectionHelper.instantiate(testClassClass);
        Method[] methods = ReflectionHelper.getMethodsWithAnnotation(classUnderTest, Test.class);
        if (methods.length == 0) {
            throw new NoTestMethodsFoundException();
        }
        initBefore(classUnderTest);
        initAfter(classUnderTest);
        TestClassResult testClassResult = new TestClassResult(testClassClass.getSimpleName(), testClassClass.getPackage().getName());

        for (Method m : methods) {
            TestMethodResult testMethodResult = getTestMethodResult(testClassClass, m);
            testClassResult.add(testMethodResult);
        }

        return testClassResult;
    }

    private void initAfter(Object object) {
        Method[] methods = ReflectionHelper.getMethodsWithAnnotation(object, After.class);
        if (methods.length >= 1) {
            hasAfter = true;
            after = methods[0];
        }
    }

    private void initBefore(Object object) {
        Method[] methods = ReflectionHelper.getMethodsWithAnnotation(object, Before.class);
        if (methods.length >= 1) {
            hasBefore = true;
            before = methods[0];
        }
    }

    private TestMethodResult getTestMethodResult(Class testClassClass, Method m) throws Throwable {
        Object cut = ReflectionHelper.instantiate(testClassClass);
        boolean isPassed = false;
        String errorMessage = "";
        if (hasBefore) {
            ReflectionHelper.callMethod(cut, before.getName(), new Object[0]);
        }
        try {
            ReflectionHelper.callMethod(cut, m.getName(), new Object[0]);
            isPassed = true;
        } catch (Throwable e){
            errorMessage = e.getMessage();
        }
        if (hasAfter) {
            ReflectionHelper.callMethod(cut, after.getName(), new Object[0]);
        }
        return new TestMethodResult(m.getName(), isPassed, errorMessage);
    }
}
