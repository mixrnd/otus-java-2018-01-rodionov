package ru.otus.HW06;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mix on 18.03.2018.
 */
public class ATM {
    private int balance;

    public void addCash(Nominal nominal, int i) {
        balance += nominal.getValue() * i;
    }

    public int getBalance() {
        return balance;
    }

    public Map<Nominal, Integer> withdraw(int sum) {
        Map<Nominal, Integer> result = new HashMap<>();

        return result;
    }
}
