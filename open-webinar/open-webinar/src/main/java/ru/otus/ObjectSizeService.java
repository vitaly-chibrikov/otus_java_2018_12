package ru.otus;

import ru.otus.base.Service;
import ru.otus.types.TraversedArray;
import ru.otus.types.TraversedObject;
import ru.otus.types.TraversedPrimitive;

import java.lang.reflect.Array;

public class ObjectSizeService implements Service {
    private final static int REF_SIZE = 4;
    private final static int HEADER_SIZE = 8;
    private final static int ARRAY_HEADER_SIZE = 12;
    private final static int GRANULARITY = 8;
    private int size = 0;

    @Override
    public void visit(TraversedArray tArray) {
        if (size != 0)
            size += REF_SIZE;
        size += ARRAY_HEADER_SIZE;

        Object array = tArray.getArray();

        int arraySize = Array.getLength(array);
        Class<?> ofArray = array.getClass().getComponentType();
        int delta = getDelta(ofArray);

        size += delta * arraySize;
    }

    @Override
    public void visit(TraversedPrimitive primitive) {
        Class<?> clazz = primitive.getField().getType();
        int delta = getDelta(clazz);
        size += delta;
    }

    @Override
    public void visit(TraversedObject value) {
        if (size != 0)
            size += REF_SIZE;
        size += HEADER_SIZE;
    }

    int getSize() {
        if (size % GRANULARITY != 0) {
            size += GRANULARITY - size % GRANULARITY;
        }
        return size;
    }

    private int getDelta(Class<?> clazz) {
        int delta;
        if (clazz == byte.class || clazz == boolean.class) {
            delta = 1;
        } else if (clazz == short.class || clazz == char.class) {
            delta = 2;
        } else if (clazz == int.class || clazz == float.class) {
            delta = 4;
        } else {
            delta = 8;
        }
        return delta;
    }

}
