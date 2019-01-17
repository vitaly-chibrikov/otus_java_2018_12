package ru.otus.l31.generics;

import java.lang.reflect.Method;

public class Example11TypeErasure {
    /**
     * see bytecode
     */
    private interface GenericInterface<T extends Number> {
        void print(T value);
    }

    /**
     * see bytecode
     */
    private static class MyClass implements GenericInterface<Long> {
        @Override
        public void print(Long value) {
            System.out.println(value);
        }
    }

    static class Main {
        public static void main(String[] args) throws NoSuchMethodException {
            for (Method method : MyClass.class.getDeclaredMethods()) {
                System.out.println(method);
            }

            final Method method1 = MyClass.class.getMethod("print", Long.class);
            final Method method2 = MyClass.class.getMethod("print", Number.class);
//            final Method method3 = MyGenericImplementation.class.getMethod("print", Object.class);

            System.out.println(method1.isSynthetic());
            System.out.println(method2.isSynthetic());
//            System.out.println(method3.isSynthetic());
        }
    }
}
