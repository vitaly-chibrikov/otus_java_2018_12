package ru.otus.types;

import ru.otus.base.Service;
import ru.otus.base.TraversedField;

import java.lang.reflect.Field;

public class TraversedObject extends TraversedField {
    public TraversedObject(Field field) {
        super(field);
    }

    @Override
    public void accept(Service service) {
        service.visit(this);
    }
}
