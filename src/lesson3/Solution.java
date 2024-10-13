package lesson3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {

    public static class Grade {
        public String subject;
        public double score;
        public Grade(String subject, double score) {
            this.subject = subject;
            this.score = score;
        }
    }

    public static class Address {
        String city;
        String street;
        public Address(String city, String street) {
            this.city = city;
            this.street = street;
        }
    }

    public static class Student {
        public String name;
        public int age;
        List<Grade> grades;
        String school;
        Address address;
        public Student(String name, int age, List<Grade> grades, String school, Address address) {
            this.name = name;
            this.age = age;
            this.grades = grades;
            this.school = school;
            this.address = address;
        }
    }

    public record StudentGradeRecord(String studentName, String school, String subject, double score) {
        public String toString() {
            return "Name: " + studentName + ", school: " + school + ", subject: " + subject + ", score: " + score;
        }
    }

    public static void main(String[] args) {
        List<Grade> aliceGrades = new ArrayList<>(List.of());
        aliceGrades.add(new Grade("Math", 95.0));
        aliceGrades.add(new Grade("Physics", 92.0));
        aliceGrades.add(new Grade("Chemistry", 89.0));

        List<Grade> bobGrades = new ArrayList<>(List.of());
        bobGrades.add(new Grade("Math", 84.0));
        bobGrades.add(new Grade("Physics", 99.0));
        bobGrades.add(new Grade("Chemistry", 81.0));

        List<Grade> charlieGrades = new ArrayList<>(List.of());
        charlieGrades.add(new Grade("Math", 100.0));
        charlieGrades.add(new Grade("Physics", 82.0));
        charlieGrades.add(new Grade("Chemistry", 81.0));

        List<Grade> isaacGrades = new ArrayList<>(List.of());
        isaacGrades.add(new Grade("Math", 94.0));
        isaacGrades.add(new Grade("Physics", 96.0));
        isaacGrades.add(new Grade("Chemistry", 91.0));

        List<Grade> jesseGrades = new ArrayList<>(List.of());
        jesseGrades.add(new Grade("Math", 57.0));
        jesseGrades.add(new Grade("Physics", 62.0));
        jesseGrades.add(new Grade("Chemistry", 99.0));

        List<Student> students = new ArrayList<>(List.of());
        students.add(new Student("Alice", 18, aliceGrades, "Oxford", new Address("New York", "5th Avenue")));
        students.add(new Student("Bob", 21, bobGrades, "Harvard", new Address("Boston", "Main Street")));
        students.add(new Student("Charlie", 15, charlieGrades, "MY Middle School", new Address("New York", "Main Street")));
        students.add(new Student("Isaac", 29, isaacGrades, "Cambridge", new Address("New York", "Mill Rd")));
        students.add(new Student("Jesse", 26, jesseGrades, "Albuquerque High School", new Address("New York", "Comanche Road")));

        List<StudentGradeRecord> grades = students.stream()
                .filter(student -> student.address.city.equals("New York") && student.age > 15)
                .flatMap(student -> student.grades.stream()
                        .map(grade -> new StudentGradeRecord(student.name, student.school, grade.subject, grade.score)))
                .sorted((g1, g2) -> Double.compare(g2.score, g1.score))
                .toList();

        int i = 0;
        for (StudentGradeRecord grade: grades) {
            System.out.println(grade);
            i++;
            if (i >= 3) {
                break;
            }
        }
    }
}
