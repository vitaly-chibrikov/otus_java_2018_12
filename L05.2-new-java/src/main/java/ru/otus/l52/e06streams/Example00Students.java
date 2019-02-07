package ru.otus.l52.e06streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Example00Students {
    public static void main(String[] args) {
        // imperative - how i want to do it (step by step)
//        final List<Student> students = getStudents();
//
//        final List<Student> result = new ArrayList<>();
//        for (Student student : students) {
//            if (student.getAvgMark() > 4 && student.getCourse() == 5) {
//                result.add(student);
//            }
//        }
//        result.sort((o1, o2) -> Double.compare(o2.getAvgMark(), o1.getAvgMark()));
//        for (Student student : result) {
//            System.out.println(student.getName());
//        }

        // declarative - what i want to do
        // refactor more!
        getStudents().stream()
                .filter(student -> student.getCourse() == 5)
                .filter(student -> student.getAvgMark() > 4)
                .sorted(Comparator.comparingDouble(Student::getAvgMark).reversed())
                .map(Student::getName)
                .forEach(System.out::println);
    }

    static List<Student> getStudents() {
        final List<Student> students = new ArrayList<>();
        students.add(new Student("Leia", 4, 2.5));
        students.add(new Student("Darth Vader", 5, 5));
        students.add(new Student("Luke", 4, 4.5));
        students.add(new Student("Obi-Wan", 5, 4.9));
        students.add(new Student("Han Solo", 5, 0.5));
        return students;
    }
}
