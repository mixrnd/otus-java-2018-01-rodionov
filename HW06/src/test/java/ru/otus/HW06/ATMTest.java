package ru.otus.HW06;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by mix on 18.03.2018.
 */
public class ATMTest {

    @Test
    public void testAddCash() {
        ATM atm = new ATM();
        atm.addCash(Nominal.TEN, 25);
        atm.addCash(Nominal.FIVE, 10);
        assertEquals(300, atm.getBalance());
    }

    @Test
    public void testSlots() throws Exception {
        ATM atm = new ATM();
        atm.addCash(Nominal.TEN, 25);
        atm.addCash(Nominal.FIVE, 10);

        Map<Nominal, Integer> w = atm.withdraw(120);

        Map<Nominal, Integer> ew = new HashMap<>();
        ew.put(Nominal.TEN, 12);

        assertEquals(ew, w);

    }
}