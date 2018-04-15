package ru.otus.HW08;

/**
 * Created by mix on 01.04.2018.
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Bag b = new Bag();
        JsonObjectWriter jsonObjectWriter = new JsonObjectWriter();
        System.out.println(jsonObjectWriter.toJson(b));
    }
}
