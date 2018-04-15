package ru.otus.HW08;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by mix on 01.04.2018.
 */
public class JsonObjectWriter {

    public String toJson(Object object) throws IllegalAccessException {
        JsonObjectBuilder jsonObjectBuilder = objectToJson(object);
        return jsonObjectBuilder.build().toString();
    }

    private JsonObjectBuilder objectToJson(Object object) throws IllegalAccessException {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        for (Field field : declaredFields) {
            field.setAccessible(true);
            Object fieldValue = field.get(object);
            String fieldName = field.getName();
            if (fieldValue == null) {
                continue;
            }
            if (field.getType().isArray()) {
                JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

                Object[] array = new Object[Array.getLength(fieldValue)];
                for (int i = 0; i < array.length; i++) {
                    Object o = Array.get(fieldValue, i);
                    if (!addToBuilder(null, o, null, arrayBuilder)) {
                        arrayBuilder.add(objectToJson(o));
                    }

                }
                objectBuilder.add(fieldName, arrayBuilder);

            } else if (fieldValue instanceof Iterable) {
                JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                for (Object o : (Iterable) fieldValue) {
                    if (!addToBuilder(null, o, null, arrayBuilder)) {
                        arrayBuilder.add(objectToJson(o));
                    }
                }
                objectBuilder.add(fieldName, arrayBuilder);
            } else if(fieldValue instanceof Map) {
                JsonObjectBuilder ob = Json.createObjectBuilder();
                for (Map.Entry e : ((Map<Object, Object>)fieldValue).entrySet()) {
                    if (!addToBuilder(e.getKey().toString(), e.getValue(), ob, null)) {
                        ob.add(e.getKey().toString(), objectToJson(e.getValue()));
                    }
                }
                objectBuilder.add(fieldName, ob);
            } else {
                if (!addToBuilder(fieldName, fieldValue, objectBuilder, null)) {
                    objectBuilder.add(fieldName, objectToJson(fieldValue));
                }

            }
        }
        return objectBuilder;
    }

    private boolean addToBuilder(String name, Object value, JsonObjectBuilder objectBuilder, JsonArrayBuilder arrayBuilder) {
        if (value instanceof String) {
            if (objectBuilder != null) {
                objectBuilder.add(name, (String) value);
            } else {
                arrayBuilder.add((String)value);
            }
        } else if (value instanceof Integer) {
            if (objectBuilder != null) {
                objectBuilder.add(name, (Integer) value);
            } else {
                arrayBuilder.add((Integer) value);
            }
        } else if (value instanceof Float) {
            if (objectBuilder != null) {
                objectBuilder.add(name, (Float) value);
            } else {
                arrayBuilder.add((Float) value);
            }
        } else if (value instanceof Double) {
            if (objectBuilder != null) {
                objectBuilder.add(name, (Double) value);
            } else {
                arrayBuilder.add((Double) value);
            }
        } else if (value instanceof Long) {
            if (objectBuilder != null) {
                objectBuilder.add(name, (Long) value);
            } else {
                arrayBuilder.add((Long) value);
            }
        } else if (value instanceof BigDecimal) {
            if (objectBuilder != null) {
                objectBuilder.add(name, (BigDecimal) value);
            } else {
                arrayBuilder.add((BigDecimal) value);
            }
        } else if (value instanceof Character) {
            char c = (char) value;
            String v = "\\" + String.format("u%04x", (int) c);
            if (objectBuilder != null) {
                objectBuilder.add(name, v);
            } else {
                arrayBuilder.add(v);
            }
        } else {
            return false;
        }
        return true;
    }
}
