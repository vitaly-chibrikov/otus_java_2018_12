package ru.otus.examples;

public class Example01 {                           // 8 header
    private int a = 0;                      // 4 for int
    private int[] array = {1, 2};           // 20 + 4 for reference
    private Object object = new Object();   // 8 + 4
    private String string = "abc";        // 32 + 4
                                            //84 -> 88

    //private Example03 e03 = new Example03();
    //private Object[] objects = new Object[]{"1", 2};
}
