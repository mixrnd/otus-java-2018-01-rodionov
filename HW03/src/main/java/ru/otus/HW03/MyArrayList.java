package ru.otus.HW03;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * Created by mix on 16.02.2018.
 */
public class MyArrayList<E> implements List<E> {

    private int size = 0;
    private final int initialCapacity = 10;
    private int currentCapacity  = 10;
    private E[] data;

    public MyArrayList() {
        data = (E[])new Object[currentCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentPosition = 0;
            @Override
            public boolean hasNext() {
                return currentPosition < size;
            }

            @Override
            public E next() {
                return data[currentPosition++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length >= size) {
            System.arraycopy(data, 0, a, 0, size);
            return a;
        }

        return (T[])Arrays.copyOf(data, size, a.getClass());
    }

    @Override
    public boolean add(E e) {
        if (size >= currentCapacity) {
            currentCapacity *= 2;
            E[] newData = (E[]) new Object[currentCapacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
        data[size] = e;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, data[i])) {
                System.arraycopy(data, i + 1,data, i, size - i - 1);
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for(Object o : c) {
            add((E)o);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for(Object o : c) {
            remove(o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        List objectsToRemove = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            if (!c.contains(data[i])){
                objectsToRemove.add(data[i]);
            }
        }
        for (Object o : objectsToRemove) {
            remove(o);
        }
        return false;
    }

    @Override
    public void clear() {
        currentCapacity = initialCapacity;
        size = 0;
        data = (E[])new Object[currentCapacity];
    }

    public int hashCode() {
        int hashCode = 1;
        for (int i = 0; i < size; i++) {
            hashCode = 31 * hashCode + Objects.hashCode(data[i]);
        }
        return hashCode;
    }

    @Override
    public E get(int index) {
        ensureIndexInBounds(index);
        return data[index];
    }

    @Override
    public E set(int index, E element) {
        ensureIndexInBounds(index);
        E old = data[index];
        data[index] = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        ensureIndexInBounds(index);
        System.arraycopy(data, index, data, index + 1, size - index - 1);
        size++;
        data[index] = element;
    }

    @Override
    public E remove(int index) {
        ensureIndexInBounds(index);
        E e = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;
        return e;
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < size; i++) {
            if (Objects.equals(o, data[i])) {
                return  i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for(int i = size - 1; i >=0 ; i--) {
            if (Objects.equals(o, data[i])) {
                return  i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return createIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return createIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new NotImplementedException();
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof List))
            return false;

        ListIterator<E> e1 = listIterator();
        ListIterator<?> e2 = ((List<?>) o).listIterator();
        while (e1.hasNext() && e2.hasNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1==null ? o2==null : o1.equals(o2)))
                return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }

    private void ensureIndexInBounds(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private ListIterator<E> createIterator(int startIndex) {
        ensureIndexInBounds(startIndex);
        return new ListIterator<E>() {
            private int currentPosition = startIndex;
            private int lastReturned = -1;
            @Override
            public boolean hasNext() {
                return currentPosition < size;
            }

            @Override
            public E next() {
                lastReturned = currentPosition;
                currentPosition++;
                return data[lastReturned];
            }

            @Override
            public boolean hasPrevious() {
                return currentPosition > 0;
            }

            @Override
            public E previous() {
                return data[currentPosition - 1];
            }

            @Override
            public int nextIndex() {
                return currentPosition + 1;
            }

            @Override
            public int previousIndex() {
                return currentPosition - 1;
            }

            @Override
            public void remove() {
                MyArrayList.this.remove(lastReturned);
            }

            @Override
            public void set(E e) {
                MyArrayList.this.set(lastReturned, e);
            }

            @Override
            public void add(E e) {
                MyArrayList.this.add(lastReturned, e);
            }
        };
    }
}
