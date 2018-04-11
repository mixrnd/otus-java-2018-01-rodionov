package ru.otus.HW06;

import java.util.Map;

/**
 * Created by mix on 08.04.2018.
 */
public class QueryBuilder {
    public String createInsert(String tableName, Map<String, Object> attributes) {
        //"insert into user (name, age) values ('Vasia', 50)";
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ").append(tableName).append(" (");
        int size = attributes.size();
        String[] names = new String[size];
        names = attributes.keySet().toArray(names);
        sb.append(String.join(", ", names));
        sb.append(") values (");
        int iterationsNumber = 0;
        for(Map.Entry<String, Object> e : attributes.entrySet()) {
            Object value = e.getValue();
            if (value instanceof String) {
                sb.append("'").append(value.toString()).append("'");
            } else {
                sb.append(value.toString());
            }
            if (iterationsNumber < size - 1) {
                sb.append(", ");
            }
            iterationsNumber++;
        }
        sb.append(")");
        return sb.toString();
    }

    public String createUpdateById(String tableName, long id, Map<String, Object> attributes) {
        //UPDATE persondata SET age=age*2, age=age+1 WHERE items.id=month.id
        StringBuilder sb = new StringBuilder();
        sb.append("update ").append(tableName).append(" set ");
        int size = attributes.size();
        int iterationsNumber = 0;
        for(Map.Entry<String, Object> e : attributes.entrySet()) {
            Object value = e.getValue();
            sb.append(e.getKey()).append(" = ");
            if (value instanceof String) {
                sb.append("'").append(value.toString()).append("'");
            } else {
                sb.append(value.toString());
            }
            if (iterationsNumber < size - 1) {
                sb.append(", ");
            }
            iterationsNumber++;
        }
        sb.append(" where id = ").append(id);
        return sb.toString();
    }

    public String selectById(String tableName, long id) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from ").append(tableName);
        sb.append(" where id = ").append(id);
        return sb.toString();
    }
}
