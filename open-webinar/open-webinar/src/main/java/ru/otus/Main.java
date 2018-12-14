package ru.otus;

import ru.otus.base.Service;
import ru.otus.examples.Example01;
import ru.otus.examples.Example02;
import ru.otus.types.TraversedArray;
import ru.otus.types.TraversedObject;
import ru.otus.types.TraversedPrimitive;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Object example = new Example01();

        new Main().traverse(null, example, new TraversalLogService());

        ObjectSizeService service = new ObjectSizeService();
        new Main().traverse(null, example, service);
        System.out.println("Mem used: " + service.getSize());
    }

    private void traverse(Field mainField, Object object, Service service) throws IllegalAccessException {
        if (object.getClass().isArray()) {
            new TraversedArray(mainField, object).accept(service);
        } else {
            new TraversedObject(mainField).accept(service);
        }
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            if (field.getType().isPrimitive()) {
                new TraversedPrimitive(field).accept(service);
            } else if (field.getType().isArray()) {
                new TraversedArray(field, field.get(object)).accept(service);
            } else {
                traverse(field, field.get(object), service);
            }
        }
    }
}
