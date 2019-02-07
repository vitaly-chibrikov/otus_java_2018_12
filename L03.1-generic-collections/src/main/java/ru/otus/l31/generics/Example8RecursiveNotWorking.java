package ru.otus.l31.generics;

public class Example8RecursiveNotWorking {
    private abstract static class Shape {
        String name;

        // note final here!
        final Shape withName(String name) {
            this.name = name;
            return this;
        }

        abstract void printArea();
    }

    private static class Rectangle extends Shape {
        private int length;
        private int width;

        Rectangle withLength(int length) {
            this.length = length;
            return this;
        }

        Rectangle withWidth(int width) {
            this.width = width;
            return this;
        }

        @Override
        void printArea() {
            final int area = width * length;
            System.out.println("Rectangle " + name + " area: " + area);
        }
    }

    private static class Square extends Shape {
        private int side;

        Square withSide(int side) {
            this.side = side;
            return this;
        }

        @Override
        void printArea() {
            final int area = side * side;
            System.out.println("Square " + name + " area: " + area);
        }
    }

    public static void main(String[] args) {
        final Rectangle rectangle = new Rectangle();
//        rectangle
//                .withName("r1")
//                .withLength(2)
//                .withWidth(8)
//                .printArea();

        final Square square = new Square();
//        square
//                .withName("s1")
//                .withSide(4)
//                .printArea();
    }
}
