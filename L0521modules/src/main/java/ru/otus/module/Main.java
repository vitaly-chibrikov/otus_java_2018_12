package ru.otus.module;

import ru.otus.submodule.shared.Shared;

public class Main {
    public static void main(String[] args) {
        // remove requires?
        final Shared shared = new Shared("some value");
        System.out.println(shared.getValue());
    }
}
