package ru.otus.l31.collections;

import java.util.HashMap;
import java.util.Map;

public class Example1HashMapTreeify {
    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        //noinspection MismatchedQueryAndUpdateOfCollection
        final Map<KeyClass, String> map = new HashMap<>();
        map.put(new KeyClass(0), "0");
        map.put(new KeyClass(1), "1");
        map.put(new KeyClass(2), "2");
        map.put(new KeyClass(3), "3");
        map.put(new KeyClass(4), "4");
        map.put(new KeyClass(5), "5");
        map.put(new KeyClass(6), "6");
        map.put(new KeyClass(7), "7");
        map.put(new KeyClass(8), "8");
        map.put(new KeyClass(9), "9");
        map.put(new KeyClass(10), "10");
        map.put(new KeyClass(11), "11"); // see java.util.HashMap.java:639
        map.put(new KeyClass(12), "12"); // see java/util/HashMap.java:633
    }

    private static final class KeyClass implements Comparable<KeyClass> {
        private final int value;

        KeyClass(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final KeyClass keyClass = (KeyClass) o;
            return value == keyClass.value;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public int compareTo(KeyClass o) {
            return Integer.compare(value, o.value);
        }
    }
}
