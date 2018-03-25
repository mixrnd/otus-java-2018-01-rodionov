package ru.otus.HW06;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by mix on 18.03.2018.
 */
public class ATM {
    private int balance;

    private Map<Nominal, Integer> slots;

    public ATM() {
        slots = createEmptyMap();
    }

    public void addCash(Nominal nominal, int i) {
        if (slots.containsKey(nominal)) {
            slots.put(nominal, slots.get(nominal) + 1);
        } else {
            slots.put(nominal, i);
        }
        balance += nominal.getValue() * i;
    }

    public int getBalance() {
        return balance;
    }

    public Map<Nominal, Integer> withdraw(int sum) {
        if (sum > balance) {
            throw new WithdrawException();
        }

        Map<Nominal, Integer> slotsCopy = createEmptyMap();
        slotsCopy.putAll(slots);

        Map<Nominal, Integer> withdrawResult = getWithdrawResult(sum);

        if (withdrawResult == null) {
            slots = slotsCopy;
            slotsCopy = null;
            throw new WithdrawException();
        }
        balance -= sum;
        return withdrawResult;
    }

    private Map<Nominal, Integer> createEmptyMap() {
        return new TreeMap<>((a, b)-> b.getValue() - a.getValue());
    }

    private Map<Nominal, Integer> getWithdrawResult(int sum) {
        Map<Nominal, Integer> result = new HashMap<>();
        int sumReminder = sum;
        for (Map.Entry<Nominal, Integer> slot: slots.entrySet()) {
            int nominalValue = slot.getKey().getValue();
            if (nominalValue > sumReminder) {
                continue;
            }
            int maxNominalPerSum = sumReminder / nominalValue;

            if (maxNominalPerSum > slot.getValue()) {
                result.put(slot.getKey(), slot.getValue());
                sumReminder = sumReminder - slot.getValue() * nominalValue;
                slots.put(slot.getKey(), 0);
            } else {
                int nominalWithdrawValue = maxNominalPerSum * nominalValue;
                result.put(slot.getKey(), maxNominalPerSum);
                sumReminder = sumReminder - nominalWithdrawValue;
                slots.put(slot.getKey(), slot.getValue() - maxNominalPerSum);

                if (sumReminder == 0) {
                    break;
                }
            }
        }

        if (sumReminder != 0) {
            return null;
        }

        return result;
    }
}
