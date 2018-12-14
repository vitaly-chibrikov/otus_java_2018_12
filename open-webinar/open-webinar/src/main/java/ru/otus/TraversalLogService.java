package ru.otus;

import ru.otus.base.Service;
import ru.otus.types.TraversedArray;
import ru.otus.types.TraversedObject;
import ru.otus.types.TraversedPrimitive;

public class TraversalLogService implements Service {
    @Override
    public void visit(TraversedArray value) {
        System.out.println("Array " + value.getName());
    }

    @Override
    public void visit(TraversedPrimitive value) {
        System.out.println("Primitive " + value.getName());
    }

    @Override
    public void visit(TraversedObject value) {
        System.out.println("Object " + value.getName());
    }
}
