package ru.otus.types;

import ru.otus.base.Service;
import ru.otus.base.TraversedField;

import java.lang.reflect.Field;

public class TraversedArray extends TraversedField {
    private final Object array;

    public TraversedArray(Field field, Object array) {
        super(field);
        this.array = array;
    }

    public void accept(Service service){
        service.visit(this);
    }

    public Object getArray() {
        return array;
    }
}
