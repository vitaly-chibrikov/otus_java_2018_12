package ru.otus.l52.e06streams;

class Student {
    private final String name;
    private final int course;
    private final double avgMark;

    Student(String name, int course, double avgMark) {
        this.name = name;
        this.course = course;
        this.avgMark = avgMark;
    }

    String getName() {
        return name;
    }

    int getCourse() {
        return course;
    }

    double getAvgMark() {
        return avgMark;
    }
}
