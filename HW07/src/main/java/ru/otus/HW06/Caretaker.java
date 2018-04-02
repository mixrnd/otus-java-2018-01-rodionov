package ru.otus.HW06;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mix on 27.03.2018.
 */
public class Caretaker {
    private final List<ATMMemento> savedStates = new ArrayList<ATMMemento>();

    public void add(ATMMemento state){
        savedStates.add(state);
    }

    public ATMMemento getLast(int index){
        return savedStates.get(index);
    }
}
