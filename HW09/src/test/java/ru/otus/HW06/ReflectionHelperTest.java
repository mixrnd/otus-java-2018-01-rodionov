package ru.otus.HW06;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by mix on 08.04.2018.
 */
public class ReflectionHelperTest {

    @Test
    public void getName() throws Exception {
        UserDataSet userDataSet = new UserDataSet();
        assertEquals("UserDataSet", ReflectionHelper.getName(userDataSet));
    }

    @Test
    public void getAttributes() throws Exception {
        UserDataSet userDataSet = new UserDataSet();

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("id", 0);
        attributes.put("name", null);
        attributes.put("age", 0);

        assertEquals(attributes.toString(), ReflectionHelper.getAttributesWithParents(userDataSet).toString());
    }
}