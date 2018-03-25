package ru.otus.HW06;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by mix on 18.03.2018.
 */
public class ATMTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private ATM atm = new ATM();

    @Test
    public void testAddCash() {
        atm.addCash(Nominal.TEN, 25);
        atm.addCash(Nominal.FIVE, 10);
        assertEquals(300, atm.getBalance());
    }

    @Test
    public void testSlots() throws Exception {
        atm.addCash(Nominal.TEN, 25);
        atm.addCash(Nominal.ONE_THOUSAND, 25);
        atm.addCash(Nominal.FIVE, 10);

        Map<Nominal, Integer> w = atm.withdraw(120);

        Map<Nominal, Integer> ew = new HashMap<>();
        ew.put(Nominal.TEN, 12);

        assertEquals(ew, w);

    }

    @Test
    public void testSlotsOptimalIfAllNominalsPresent() throws Exception {
        atm.addCash(Nominal.TEN, 60);
        atm.addCash(Nominal.ONE_THOUSAND, 2);
        atm.addCash(Nominal.FIVE, 10);

        Map<Nominal, Integer> w = atm.withdraw(1555);

        Map<Nominal, Integer> ew = new HashMap<>();
        ew.put(Nominal.ONE_THOUSAND, 1);
        ew.put(Nominal.TEN, 55);
        ew.put(Nominal.FIVE, 1);

        assertEquals(ew, w);
        assertEquals(1095, atm.getBalance());
    }

    @Test
    public void testSlotsOptimalIfAllNominalNotPresent() throws Exception {
        atm.addCash(Nominal.TEN, 40);
        atm.addCash(Nominal.ONE_THOUSAND, 2);
        atm.addCash(Nominal.FIVE, 40);

        Map<Nominal, Integer> w = atm.withdraw(1555);

        Map<Nominal, Integer> ew = new HashMap<>();
        ew.put(Nominal.ONE_THOUSAND, 1);
        ew.put(Nominal.TEN, 40);
        ew.put(Nominal.FIVE, 31);

        assertEquals(ew, w);
    }

    @Test
    public void testSlotsIfNotPossibleWithdraw() throws Exception {
        exception.expect(WithdrawException.class);
        atm.addCash(Nominal.TEN, 40);
        atm.addCash(Nominal.ONE_THOUSAND, 2);
        atm.addCash(Nominal.FIVE, 40);

        Map<Nominal, Integer> w = atm.withdraw(1556);

    }

    @Test
    public void testWithdrawNoMoney() throws Exception {
        exception.expect(WithdrawException.class);
        atm.addCash(Nominal.TEN, 40);
        atm.addCash(Nominal.ONE_THOUSAND, 2);
        atm.addCash(Nominal.FIVE, 40);

        Map<Nominal, Integer> w = atm.withdraw(1000000);

    }

    @Test
    public void testATMStateNotChangedIfWithdrawTryFails() throws Exception {
        atm.addCash(Nominal.TEN, 60);
        atm.addCash(Nominal.ONE_THOUSAND, 2);
        atm.addCash(Nominal.FIVE, 10);

        try {
            atm.withdraw(1556);
            fail();
        } catch (WithdrawException e) {
            assertTrue(e instanceof WithdrawException);
        }


        Map<Nominal, Integer> w = atm.withdraw(1555);
        Map<Nominal, Integer> ew = new HashMap<>();
        ew.put(Nominal.ONE_THOUSAND, 1);
        ew.put(Nominal.TEN, 55);
        ew.put(Nominal.FIVE, 1);

        assertEquals(ew, w);
        assertEquals(1095, atm.getBalance());

    }
}