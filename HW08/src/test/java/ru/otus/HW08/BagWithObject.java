package ru.otus.HW08;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by mix on 04.04.2018.
 */
public class BagWithObject {
    private int value1 = 1;
    private BagOfPrimitives value2 = new BagOfPrimitives();
    private BagWithArray value3;
    private Float value4 = null;
    private List<Integer> value5 = new LinkedList<>();
    private List<BigDecimal> value6 = new LinkedList<>();
    private Map<String, Integer> value7 = new HashMap<>();
    private Map<String, Integer> value8 = new HashMap<>();
    private Map<Float, Integer> value9 = new HashMap<>();

    public BagWithObject() {
        value6.add(new BigDecimal(11));
        value6.add(new BigDecimal(22));

        value8.put("One", 1);
        value8.put("Two", 2);

        value9.put(10f, 10);
        value9.put(11f, 11);
    }
}
