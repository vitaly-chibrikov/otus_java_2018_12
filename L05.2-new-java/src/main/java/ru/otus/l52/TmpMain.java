package ru.otus.l52;

public class TmpMain {
    public static void main(String[] args) {
        final Implementer implementer = new Implementer();
        implementer.A();
    }

    interface Interface {
        default void A() {
            System.out.println("interface");
        }
    }

    static class MyClass {
        void A() {
            System.out.println("class");
        }
    }

    static class Implementer extends MyClass implements Interface {
        @Override
        public void A() {
            super.A();
        }
    }
}
