package ru.otus.HW06;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by mix on 08.04.2018.
 */
public class QueryBuilderTest {

    private QueryBuilder queryBuilder = new QueryBuilder();

    @Test
    public void createInsert() throws Exception {
        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("name", "Vasia");
        attributes.put("age", 50);
        String sql = queryBuilder.createInsert("user", attributes);
        assertEquals("insert into user (name, age) values ('Vasia', 50)", sql);
    }



    @Test
    public void createUpdateById() throws Exception {
        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("name", "Vasia");
        attributes.put("age", 50);
        String sql = queryBuilder.createUpdateById("user", 1, attributes);
        assertEquals("update user set name = 'Vasia', age = 50 where id = 1", sql);
    }

    @Test
    public void selectById() throws Exception {
        String sql = queryBuilder.selectById("user", 1);
        assertEquals("select * from user where id = 1", sql);
    }
}