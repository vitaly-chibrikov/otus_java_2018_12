package ru.otus.l21;

public class ClassLoaderMain {
    public static void main(String[] args) {

        System.out.println("Classloader of this class:"
                + ClassLoaderMain.class.getClassLoader());

        System.out.println("Classloader of String:"
                + String.class.getClassLoader()); //bootstrap class loader is written in native code

    }
}
