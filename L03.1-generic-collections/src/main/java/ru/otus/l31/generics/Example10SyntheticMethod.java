package ru.otus.l31.generics;

import java.lang.reflect.Method;

public class Example10SyntheticMethod {
    /**
     * see bytecode
     */
    private interface GenericInterface<T> {
        void print(T value);
    }

    /**
     * see bytecode
     */
    private static class MyClass implements GenericInterface<String> {
        @Override
        public void print(String value) {
            System.out.println(value);
        }
    }

    private static class Main {
        public static void main(String[] args) throws NoSuchMethodException {
            for (Method method : Example10SyntheticMethod.class.getDeclaredMethods()) {
                System.out.println(method);
            }

            final Method method1 = Example10SyntheticMethod.class.getMethod("print", String.class);
            final Method method2 = Example10SyntheticMethod.class.getMethod("print", Object.class);

            System.out.println(method1.isSynthetic());
            System.out.println(method2.isSynthetic());
        }
    }
}