package ru.otus.HW05;


import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by mix on 04.03.2018.
 */
public class TestRunnerTestClass {


    @Test
    public void runTestMethods() throws Throwable {
        TestClassRunner testClassRunner = new TestClassRunner(TestClass.class.getName());
        TestClassResult testClassResult = testClassRunner.run();
        TestMethodResult[] tmr = testClassResult.getResults();

        assertEquals("TestClass", testClassResult.getClassName());
        assertEquals("ru.otus.HW05", testClassResult.getPackageName());
        Arrays.sort(tmr, ( a, b) ->  a.getName().compareTo(b.getName()));

        assertEquals("testMethod", tmr[1].getName());
        assertFalse(tmr[1].isPassed());

        assertEquals("anotherTestMethod", tmr[0].getName());
        assertTrue(tmr[0].isPassed());
    }

    @Test
    public void beforeAndAfterCalls() throws Throwable {
        TestClassRunner testClassRunner = new TestClassRunner(TestClassWithAfterAndBefore.class.getName());
        testClassRunner.run();
        assertEquals(1, TestClassWithAfterAndBefore.setUpCallCounter);
        assertEquals(1, TestClassWithAfterAndBefore.tearDownCallCounter);
    }
}