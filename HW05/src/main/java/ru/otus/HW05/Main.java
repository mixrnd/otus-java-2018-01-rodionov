package ru.otus.HW05;

import ru.otus.HW05.test_package.TestClass;

/**
 * Created by mix on 05.03.2018.
 */
public class Main {
    public static void main(String[] args) throws Throwable {
        TestPackageRunner testPackageRunner = new TestPackageRunner(TestClass.class.getPackage().getName());
        TestPackageResult testPackageResult = testPackageRunner.run();
        for (TestClassResult testClassResult: testPackageResult.getTestResults()) {
            System.out.println("TestClass:" + testClassResult.getPackageName() + ":" + testClassResult.getClassName());
            for(TestMethodResult testMethodResult : testClassResult.getResults()) {
                System.out.println("  Method:"+ testMethodResult.getName() + " " + (testMethodResult.isPassed()? "OK" : "FAIL"));
            }
        }
    }
}
