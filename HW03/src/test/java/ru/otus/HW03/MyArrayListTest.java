package ru.otus.HW03;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by mix on 16.02.2018.
 */
public class MyArrayListTest extends BaseMyArrayTest {

    @Test
    public void addIncreaseSize() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
    }

    @Test
    public void addExtendDefaultArraySize() {
        fillListRange(0, 100);
        assertEquals(100, list.size());
    }

    @Test
    public void toArrayReturnAllElements() {
        Object[] a = new Object[100];
        for (int i = 0; i < 100; i++) {
            a[i] = i;
            list.add(i);
        }

        assertArrayEquals(a, list.toArray());
    }

    @Test
    public void toArrayWithTypeReturnAllElements() {
        fillListRange(0, 103);
        Integer[] a = IntStream.range(0, 103).boxed().toArray(Integer[]::new);
        assertArrayEquals(a, list.toArray(new Integer[2]));
    }

    @Test
    public void removeOneElement() {
        fillListRange(1, 11);
        IntStream.range(3, 11).forEach(i -> list.remove(2));
        assertArrayEquals(new Object[]{1,2}, list.toArray());
    }

    @Test
    public void contains() {
        fillListRange(1, 4);
        assertTrue(list.contains(3));
        assertTrue(list.contains(2));
        assertFalse(list.contains(4));
    }

    @Test
    public void containsAll() {
        fillListRange(0, 103);
        assertTrue(list.containsAll(Arrays.asList(5,6,7,8)));
        assertFalse(list.containsAll(Arrays.asList(5,6,7,8,104)));
    }

    @Test
    public void iteratorTest() {
        fillListRange(0, 103);
        int counter = 0;
        Iterator<Integer> listIterator = list.iterator();
        while (listIterator.hasNext()) {
            Integer c = listIterator.next();
            assertEquals((Integer) counter, c);
            counter++;
        }

        assertEquals(103, counter);
    }

    @Test
    public void retainAll() {
        fillListRange(1, 11);
        list.retainAll(Arrays.asList(1,2,3,4,5));
        assertArrayEquals(new Object[]{1,2,3,4,5}, list.toArray());
    }

    @Test
    public void addAll() {
        fillListRange(0, 3);
        assertArrayEquals(new Object[]{0,1,2}, list.toArray());
        list.addAll(Arrays.asList(3, 4, 5));
        assertArrayEquals(new Object[]{0,1,2,3,4,5}, list.toArray());
    }

    @Test
    public void removeAll() {
        fillListRange(0, 6);
        assertArrayEquals(new Object[]{0,1,2,3,4,5}, list.toArray());
        list.removeAll(Arrays.asList(3, 4, 5));
        assertArrayEquals(new Object[]{0,1,2}, list.toArray());
    }

    @Test
    public void get() {
        fillListRange(0, 10);
        assertEquals(2, (int)list.get(2));
        assertEquals(9, (int)list.get(9));
    }

    @Test
    public void set() {
        fillListRange(0, 10);
        Integer old = list.set(1, 222);
        assertEquals((Integer) 1, old);
        assertEquals(222, (int)list.get(1));
    }

    @Test
    public void add() {
        fillListRange(0, 15);
        list.add(2, 145);
        assertEquals(145, (int)list.get(2));
        assertEquals(16, list.size());

    }

    @Test
    public void remove() {
        fillListRange(0, 10);
        list.remove(5);
        assertEquals(6, (int)list.get(5));
        assertEquals(9, list.size());
    }
}