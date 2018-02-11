package ru.otus.HW02;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String... args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int size = 2_000_000;
        System.out.println("Starting the loop");
        Runtime runtime = Runtime.getRuntime();
        Types currentType = Types.getNext();
        while (true) {
            System.gc();
            Thread.sleep(100);

            switch (currentType) {
                case OBJECT:
                    Main.<Object>measureMemSize(size, (i) -> new Object(), runtime, currentType);
                    break;
                case LONG:
                    Main.<Long>measureMemSize(size, (i) -> 130L * i, runtime, currentType);
                    break;
                case INTEGER:
                    Main.<Integer>measureMemSize(size, (i) -> 130 * i, runtime, currentType);
                    break;
                case STRING_WITH_POOL:
                    Main.<String>measureMemSize(size, (i) -> new String(""), runtime, currentType);
                    break;
                case STRING_WITHOUT_POOL:
                    Main.<String>measureMemSize(size, (i) -> new String(new char[0]), runtime, currentType);
                    break;
                case CLASS_WITH_INT_AND_LONG:
                    Main.<MyClass>measureMemSize(size, (i) -> new MyClass(), runtime, currentType);
                    break;
                case ARRAY_LIST_INTEGER:
                    Main.<ArrayList<Integer>>measureMemSize(size, (i) -> new ArrayList<Integer>(), runtime, currentType);
                    break;
                case ARRAY_LIST_INTEGER_ONE_ELEMENT:
                    Main.<ArrayList<Integer>>measureMemSize(size, (i) -> {
                        ArrayList<Integer> l = new ArrayList<>();
                        l.add(130);
                        return l;
                    }, runtime, currentType);
                    break;
                case ARRAY_LIST_LONG:
                    Main.<ArrayList<Long>>measureMemSize(size, (i) -> new ArrayList<Long>(), runtime, currentType);
                    break;
                case ARRAY_LIST_LONG_ONE_ELEMENT:
                    Main.<ArrayList<Long>>measureMemSize(size, (i) -> {
                        ArrayList<Long> l = new ArrayList<>();
                        l.add(130L);
                        return l;
                    }, runtime, currentType);
                    break;
                case HASH_MAP_INTEGER_STRING:
                    Main.<HashMap<Integer, String>>measureMemSize(size, (i) -> new HashMap<Integer, String>(), runtime, currentType);
                    break;
                case HASH_MAP_INTEGER_STRING_ONE_ELEMENT:
                    Main.<HashMap<Integer, String>>measureMemSize(size, (i) -> {
                        HashMap<Integer, String> m = new HashMap<>();
                        m.put(130, new String(new char[0]));
                        return m;
                    }, runtime, currentType);
                    break;
                case HASH_MAP_STRING_INTEGER:
                    Main.<HashMap<String, Integer>>measureMemSize(size, (i) -> new HashMap<String, Integer>(), runtime, currentType);
                    break;
                case HASH_MAP_STRING_INTEGER_ONE_ELEMENT:
                    Main.<HashMap<String, Integer>>measureMemSize(size, (i) -> {
                        HashMap<String, Integer> m = new HashMap<>();
                        m.put(new String(new char[0]), 130);
                        return m;
                    }, runtime, currentType);
                    break;
            }

            currentType = Types.getNext();
            Thread.sleep(1000); //wait for 1 sec
        }
    }

    private static <T> void measureMemSize(int size, Initializer<T> initializer, Runtime runtime, Types type) {
        Object[] array = new Object[size];
        long refMem = runtime.totalMemory() - runtime.freeMemory();
        for (int i = 0; i < size; i++) {
            array[i] = initializer.init(i);
        }
        long objectMem = runtime.totalMemory() - runtime.freeMemory();
        System.out.println(type + " size:" + (objectMem - refMem) / size);
    }

    private interface Initializer<T> {
        T init(int seed);
    }

    private enum Types {
        OBJECT, LONG, INTEGER, STRING_WITH_POOL, STRING_WITHOUT_POOL, ARRAY_LIST_INTEGER, ARRAY_LIST_INTEGER_ONE_ELEMENT,
        ARRAY_LIST_LONG, ARRAY_LIST_LONG_ONE_ELEMENT, HASH_MAP_INTEGER_STRING, HASH_MAP_INTEGER_STRING_ONE_ELEMENT,
        HASH_MAP_STRING_INTEGER, HASH_MAP_STRING_INTEGER_ONE_ELEMENT, CLASS_WITH_INT_AND_LONG;
        private static int current = 0;

        public static Types getNext() {
            return Types.values()[current++ % Types.values().length];
        }
    }

    private static class MyClass {
        private int i = 0;
        private long l = 1;
    }
}
