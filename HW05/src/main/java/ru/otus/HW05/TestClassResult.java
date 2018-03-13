package ru.otus.HW05;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mix on 04.03.2018.
 */
public class TestClassResult {
    private String className;
    public String packageName;

    private List<TestMethodResult> testMethodResults;

    public TestClassResult(String className, String packageName) {
        testMethodResults = new ArrayList<>();
        this.className = className;
        this.packageName = packageName;
    }

    public TestMethodResult[] getResults() {
        return testMethodResults.toArray(new TestMethodResult[0]);
    }

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }

    void add(TestMethodResult testMethodResult) {
        testMethodResults.add(testMethodResult);
    }
}
