package ru.otus.types;

import ru.otus.base.Service;
import ru.otus.base.TraversedField;

import java.lang.reflect.Field;

public class TraversedPrimitive extends TraversedField {

    public TraversedPrimitive(Field field) {
        super(field);
    }

    public void accept(Service service) {
        service.visit(this);
    }
}
