package ru.otus.HW05;

import org.junit.Test;
import ru.otus.HW05.test_package.RootPackageTestClass;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by mix on 07.03.2018.
 */
public class PackageTestRunnerTest {

    @Test
    public void name() throws Throwable {
        TestPackageRunner packageTestRunner = new TestPackageRunner(RootPackageTestClass.class.getPackage().getName());

        TestPackageResult testPackageResult = packageTestRunner.run();
        assertEquals("ru.otus.HW05.test_package", testPackageResult.getName());


        TestClassResult[] testClassResults = testPackageResult.getTestResults();
        assertEquals(4, testClassResults.length);
        Arrays.sort(testClassResults, (a, b) ->  a.getClassName().compareTo(b.getClassName()));
    }
}
