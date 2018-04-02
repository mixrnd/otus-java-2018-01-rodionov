package ru.otus.HW06;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mix on 27.03.2018.
 */
public class ATMMemento {
    private final List<ATM> state;

    public ATMMemento(List<ATM> stateToSave) throws CloneNotSupportedException {
        state = new ArrayList<>();
        for (ATM a: stateToSave) {
            state.add(a.clone());
        }

    }

    public List<ATM> getSavedState() {
        return state;
    }
}
