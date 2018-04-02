package ru.otus.HW06;

/**
 * Created by mix on 21.03.2018.
 */
public enum Nominal {
    ONE(1), TWO(2), FIVE(5), TEN(10), ONE_HUNDRED(100), ONE_THOUSAND(1000);

    private int value;

    Nominal(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
