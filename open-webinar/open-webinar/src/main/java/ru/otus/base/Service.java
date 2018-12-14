package ru.otus.base;

import ru.otus.types.TraversedArray;
import ru.otus.types.TraversedObject;
import ru.otus.types.TraversedPrimitive;

public interface Service {
    void visit(TraversedArray value);

    void visit(TraversedPrimitive value);

    void visit(TraversedObject value);
}
