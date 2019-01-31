package ru.otus.l51.reflection;

import java.lang.reflect.Field;

public class FinalFieldMain {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        final FinalField finalField = new FinalField(1);
        System.out.println(finalField.getValue());

        final Class<? extends FinalField> clazz = finalField.getClass();
        final Field field = clazz.getDeclaredField("value");
        field.setAccessible(true);
        field.set(finalField, 2);
        field.setAccessible(false);
        System.out.println(finalField.getValue());
    }
}
