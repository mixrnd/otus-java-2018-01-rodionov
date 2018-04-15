package ru.otus.HW08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mix on 15.04.2018.
 */
public class BagWithNestedObject {
    private Integer value1 = 1;
    private BagWithObject value2 = new BagWithObject();
    private List<BagWithObject> value3 = new ArrayList<>();
    private BagWithObject[] value4 = new BagWithObject[]{new BagWithObject()};
    private Map<String, BagWithObject> value5 = new HashMap<>();

    public BagWithNestedObject() {
        value3.add(new BagWithObject());
        value3.add(new BagWithObject());

        value5.put("value5", new BagWithObject());
    }
}
