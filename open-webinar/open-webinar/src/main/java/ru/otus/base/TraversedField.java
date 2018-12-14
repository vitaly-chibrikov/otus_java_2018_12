package ru.otus.base;

import java.lang.reflect.Field;

public abstract class TraversedField implements TraversedType {
    private final Field field;

    protected TraversedField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    public String getName() {
        return field == null ? "null" : field.getName();
    }
}
