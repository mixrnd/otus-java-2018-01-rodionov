package ru.otus.HW06;

import ru.otus.HW06.exceptions.RestoreStateException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mix on 26.03.2018.
 */
public class ATMDepartment {

    private List<ATM> atmList = new ArrayList<>();

    private Caretaker caretaker = new Caretaker();

    private int saveIndex = 0;

    public void addAtm(ATM atm)
    {
        atmList.add(atm);
    }

    public int getBalance() {
        int balance = 0;
        for(ATM a : atmList) {
            balance += a.getBalance();
        }
        return balance;
    }

    public void saveSate() throws CloneNotSupportedException {
        caretaker.add(new ATMMemento(this.atmList));
        saveIndex++;
    }

    public void restoreState() {
        saveIndex--;
        try {
            atmList = caretaker.getLast(saveIndex).getSavedState();
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new RestoreStateException("Save count calls does not match restore count calls");
        } finally {
            saveIndex++;
        }
    }
}
