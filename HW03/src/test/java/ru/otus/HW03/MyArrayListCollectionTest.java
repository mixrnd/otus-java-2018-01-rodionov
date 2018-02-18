package ru.otus.HW03;

import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by mix on 17.02.2018.
 */
public class MyArrayListCollectionTest extends  BaseMyArrayTest {

    @Test
    public void sort() {
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(1);

        Collections.sort(list);
        assertArrayEquals(new Integer[]{1, 2, 3, 4}, list.toArray());
    }

    @Test
    public void sortWithComparator() {
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(1);

        Collections.sort(list, (o1, o2) -> o2 - o1);
        assertArrayEquals(new Integer[]{4, 3, 2, 1}, list.toArray());
    }

    @Test
    public void range() {
        fillListRange(1, 5);
        Collections.reverse(list);
        assertArrayEquals(new Integer[]{4, 3, 2, 1}, list.toArray());

    }

    @Test
    public void addAll() {
        list.add(1);
        Collections.addAll(list, 2,3);
        assertArrayEquals(new Integer[]{1,2,3}, list.toArray());
    }

    @Test
    public void copy() {
        fillListRange(1, 32);
        List<Integer> linkedList = new LinkedList<>();
        IntStream.range(1, 32).forEach(i -> linkedList.add(0));
        Collections.copy(linkedList, list);
        assertArrayEquals(list.toArray(), linkedList.toArray());
    }

    @Test
    public void frequency() {
        list.add(3);
        list.add(127);
        list.add(3);
        list.add(6);
        list.add(39);
        assertEquals(2,Collections.frequency(list, 3));

    }

    @Test
    public void fill() {
        fillListRange(1, 5);
        Collections.fill(list, 11);
        assertArrayEquals(new Integer[]{11,11,11,11}, list.toArray());
    }

    @Test
    public void maxAndMax() {
        list.add(3);
        list.add(127);
        list.add(3);
        list.add(6);
        list.add(39);
        int max = Collections.max(list);
        int min = Collections.min(list);
        assertEquals(127, max);
        assertEquals(3, min);
    }
}
