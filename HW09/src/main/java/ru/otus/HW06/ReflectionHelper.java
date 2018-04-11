package ru.otus.HW06;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by mix on 08.04.2018.
 */
public class ReflectionHelper {
    private ReflectionHelper(){};

    static String getName(Object object) {
        return object.getClass().getSimpleName();
    }

    static Map<String, Object> getAttributesWithParents(Object object) throws IllegalAccessException {
        Field[] parentFields = object.getClass().getSuperclass().getDeclaredFields();
        HashMap<String, Object> result = new HashMap<>();
        for (Field field : parentFields) {
            field.setAccessible(true);
            result.put(field.getName(), field.get(object));
        }
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            result.put(field.getName(), field.get(object));
        }
        return result;
    }

    static List<String> getAttributesNamesWithParents(Object object) {
        Field[] parentFields = object.getClass().getSuperclass().getDeclaredFields();
        List<String> names = new ArrayList<>();
        for (Field field : parentFields) {
            field.setAccessible(true);
            names.add(field.getName());
        }
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            names.add(field.getName());
        }
        return names;
    }

    static void setFieldValue(Object object, String name, Object value) {
        Field field = null;
        boolean isAccessible = true;
        try {
            field = object.getClass().getDeclaredField(name);
            isAccessible = field.isAccessible();
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (field != null && !isAccessible) {
                field.setAccessible(false);
            }
        }
    }

    static <T> T instantiate(Class<T> type, Object... args) throws InvocationTargetException {
        try {
            if (args.length == 0) {
                return type.getDeclaredConstructor().newInstance();
            } else {
                Class<?>[] classes = toClasses(args);
                return type.getDeclaredConstructor(classes).newInstance(args);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    static private Class<?>[] toClasses(Object[] args) {
        return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
    }
}
