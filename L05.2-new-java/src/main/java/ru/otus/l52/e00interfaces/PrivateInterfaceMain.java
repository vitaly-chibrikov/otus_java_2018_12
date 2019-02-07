package ru.otus.l52.e00interfaces;

public class PrivateInterfaceMain {
    public static void main(String[] args) {
        final PrivateInterface privateInterface = new PrivateInterface() {
        };
        privateInterface.printState();
        privateInterface.printClass();

        final MyClass myClass = new MyClass();
        myClass.printState();
        myClass.printClass();
    }

    private static final class MyClass implements PrivateInterface {
//        @Override printState() and printClass() only
    }
}
