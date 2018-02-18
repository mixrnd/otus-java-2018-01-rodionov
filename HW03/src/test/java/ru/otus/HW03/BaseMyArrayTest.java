package ru.otus.HW03;

import org.junit.Before;

/**
 * Created by mix on 18.02.2018.
 */
public class BaseMyArrayTest {
    protected MyArrayList<Integer> list;

    @Before
    public void newList() {
        list = new MyArrayList<>();
    }

    protected void fillListRange(int startInclusive, int endNotInclusive) {
        for (int i = startInclusive; i < endNotInclusive; i++) {
            list.add(i);
        }
    }
}
