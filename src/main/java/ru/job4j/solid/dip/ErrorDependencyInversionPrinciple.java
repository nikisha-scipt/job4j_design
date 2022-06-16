package ru.job4j.solid.dip;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ErrorDependencyInversionPrinciple {

    /**
     * В одном примере 3 нарушения принципа DIP:1) зависим по полю studentMap, а должна зависеть от абстракции;
     *                                          2) Напрямую зависим от консольного вывода, необходимо реализовать логгер, чтобы не нарушать DIP;
     *                                          3) напрямую зависит от входного параметра Student
     */
    private static class Student {
        private String name;
        private int course;

        public Student(String name, int course) {
            this.name = name;
            this.course = course;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Student student = (Student) o;
            return course == student.course && Objects.equals(name, student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, course);
        }

        @Override
        public String toString() {
            return "Student{" + "name='" + name + '\'' + ", course=" + course + '}';
        }

        public String getName() {
            return name;
        }

        public int getCourse() {
            return course;
        }
    }

    private static class Clazz {

        private final Map<Integer, Student> studentMap = new HashMap<>();
        private final Student student;

        public Clazz(Student student) {
            this.student = student;
        }

        boolean add() {
            return studentMap.put((int) (Math.random() * 100), student) != null;
        }

        @Override
        public String toString() {
            return "Clazz{" + "studentMap=" + studentMap + '}';
        }
    }

}
