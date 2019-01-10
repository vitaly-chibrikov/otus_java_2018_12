package ru.otus.l21;

import java.sql.Blob;

public class ClassLoaderMain {
    public static void main(String[] args) {
        System.out.println("Classloader of java.lang.String: "
                + String.class.getClassLoader()); //bootstrap class loader is written in native code

        System.out.println("Classloader of java.sql.Blob: "
                + Blob.class.getClassLoader());

        System.out.println("Classloader of ru.otus.l21.ClassLoaderMain: "
                + ClassLoaderMain.class.getClassLoader());
    }
}
