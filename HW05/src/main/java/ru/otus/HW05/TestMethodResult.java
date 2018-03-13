package ru.otus.HW05;

/**
 * Created by mix on 04.03.2018.
 */
public class TestMethodResult {
    private final boolean isPassed;
    private String errorMessage;
    private String name;

    public TestMethodResult(String name, boolean isPassed, String errorMessage) {
        this.isPassed = isPassed;
        this.errorMessage = errorMessage;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
