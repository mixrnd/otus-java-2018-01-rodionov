package ru.otus.HW06;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.otus.HW06.exceptions.RestoreStateException;

import static org.junit.Assert.assertEquals;

/**
 * Created by mix on 26.03.2018.
 */
public class ATMDepartmentTest {

    private ATMDepartment atmDepartment = new ATMDepartment();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetBalance() throws Exception {
        ATM atm1 = new ATM();
        atm1.addCash(Nominal.ONE, 5);

        ATM atm2 = new ATM();
        atm2.addCash(Nominal.FIVE, 1);

        atmDepartment.addAtm(atm1);
        atmDepartment.addAtm(atm2);

        assertEquals(10, atmDepartment.getBalance());
    }

    @Test
    public void testRestoreInitialAtmsState() throws Exception {
        ATM atm1 = new ATM();
        atm1.addCash(Nominal.ONE, 5);

        ATM atm2 = new ATM();
        atm2.addCash(Nominal.FIVE, 1);

        atmDepartment.addAtm(atm1);
        atmDepartment.addAtm(atm2);

        atmDepartment.saveSate();

        atm1.addCash(Nominal.TEN, 30);
        atm2.addCash(Nominal.FIVE, 5);
        assertEquals(335, atmDepartment.getBalance());

        atmDepartment.restoreState();

        assertEquals(10, atmDepartment.getBalance());
    }

    @Test
    public void testRestoreStateException() throws Exception {
        exception.expect(RestoreStateException.class);
        ATM atm1 = new ATM();
        atm1.addCash(Nominal.ONE, 5);

        ATM atm2 = new ATM();
        atm2.addCash(Nominal.FIVE, 1);

        atmDepartment.addAtm(atm1);
        atmDepartment.addAtm(atm2);

        atmDepartment.restoreState();
    }
}