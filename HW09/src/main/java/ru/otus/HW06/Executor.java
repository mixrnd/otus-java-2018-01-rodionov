package ru.otus.HW06;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mix on 07.04.2018.
 */
public class Executor {

    private static final String SUFFIX = "DataSet";

    private QueryBuilder queryBuilder = new QueryBuilder();
    private QueryHelper queryHelper;

    public Executor(Connection connection) {
        queryHelper = new QueryHelper(connection);
    }

    public <T extends DataSet> void save(T user) throws IllegalAccessException, SQLException {
        //insert into user (name, age) values ('Vasia', 50)"
        String tableName = extractTableName(ReflectionHelper.getName(user));
        Map<String, Object> attributes = ReflectionHelper.getAttributesWithParents(user);
        Map<String, Object> attributesForQuery = new HashMap<>();
        boolean isInsert = true;
        long idValue = 0;
        for(Map.Entry<String, Object> e: attributes.entrySet()) {
            if (e.getKey().equals("id")) {
                if ((long)e.getValue() == 0) {
                    isInsert = true;
                } else {
                    idValue = (long)e.getValue();
                }
            } else {
                attributesForQuery.put(e.getKey(), e.getValue());
            }
        }

        if (isInsert) {
            long id = queryHelper.insert(queryBuilder.createInsert(tableName, attributesForQuery));
            user.setId(id);
        } else {
            queryHelper.insert(queryBuilder.createUpdateById(tableName, idValue, attributesForQuery));
        }

    }

    public <T extends DataSet> T load(long id, Class<T> clazz) throws InvocationTargetException, IllegalAccessException, SQLException {
        Object loaded = ReflectionHelper.instantiate(clazz);
        List<String> attributesNames = ReflectionHelper.getAttributesNamesWithParents(loaded);
        String tableName = extractTableName(ReflectionHelper.getName(loaded));
        ResultSet resultSet = queryHelper.select(queryBuilder.selectById(tableName, id));

        return (T)(new Object());
    }

    private String extractTableName(String className) {
        if (className.contains(SUFFIX)) {
            return className.substring(0, className.indexOf(SUFFIX)).toLowerCase();
        } else {
            return className.toLowerCase();
        }
    }
}
