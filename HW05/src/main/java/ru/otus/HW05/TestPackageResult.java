package ru.otus.HW05;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mix on 07.03.2018.
 */
public class TestPackageResult {

    private String name;
    private List<TestClassResult> testClassResults;

    public TestPackageResult(String name) {
        testClassResults = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public TestClassResult[] getTestResults() {
        return testClassResults.toArray(new TestClassResult[0]);
    }

    void add(TestClassResult testClassResult) {
        testClassResults.add(testClassResult);
    }

}
