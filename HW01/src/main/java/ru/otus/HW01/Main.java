package ru.otus.HW01;

import org.apache.commons.lang3.ArrayUtils;

public class Main {
    public static void main(String[] args) {
        char[] str = args.length == 0? "Hello Otus".toCharArray() : args[0].toCharArray();
        ArrayUtils.shuffle(str);
        System.out.println(str);
    }
}
