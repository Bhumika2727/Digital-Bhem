
Sign up
Student Gradebook

Student Data Management: Create a Java project for managing student information, including names and IDs.

Subject Grading: Implement a system to input and calculate grades for various subjects.

Gradebook Display: Show individual student grades and calculate their average scores.

User Interaction: Allow users to add, edit, or remove student records.



Step-by-Step Implementation
Student Class: Represents a student with attributes like name, ID, and a list of grades.
java
Copy code
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private int studentID;
    private List<Integer> grades;

    public Student(String name, int studentID) {
        this.name = name;
        this.studentID = studentID;
        this.grades = new ArrayList<>();
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    public double calculateAverageGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public String getName() {
        return name;
    }

    public int getStudentID() {
        return studentID;
    }

    public List<Integer> getGrades() {
        return grades;
    }
}
Gradebook Class: Manages a collection of students and provides methods to interact with them.
java
Copy code
import java.util.ArrayList;
import java.util.List;

public class Gradebook {
    private List<Student> students;

    public Gradebook() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addGrade(int studentID, int grade) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                student.addGrade(grade);
                return;
            }
        }
        System.out.println("Student with ID " + studentID + " not found.");
    }

    public double getStudentAverageGrade(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student.calculateAverageGrade();
            }
        }
        System.out.println("Student with ID " + studentID + " not found.");
        return 0.0;
    }

    public void generateGradeReport(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                System.out.println("Grade report for student " + student.getName() + " (ID: " + student.getStudentID() + ")");
                System.out.println("Grades: " + student.getGrades());
                System.out.println("Average Grade: " + student.calculateAverageGrade());
                return;
            }
        }
        System.out.println("Student with ID " + studentID + " not found.");
    }

    public void removeStudent(int studentID) {
        students.removeIf(student -> student.getStudentID() == studentID);
    }
}
Main Application: Provides a user interface to interact with the gradebook system.
java
Copy code
import java.util.Scanner;

public class Main {
    private static Gradebook gradebook = new Gradebook();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\nGradebook Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Calculate Average Grade for Student");
            System.out.println("4. Generate Grade Report");
            System.out.println("5. Remove Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addGrade();
                    break;
                case 3:
                    calculateAverageGrade();
                    break;
                case 4:
                    generateGradeReport();
                    break;
                case 5:
                    removeStudent();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();
        Student student = new Student(name, studentID);
        gradebook.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private static void addGrade() {
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();
        System.out.print("Enter grade: ");
        int grade = scanner.nextInt();
        gradebook.addGrade(studentID, grade);
        System.out.println("Grade added successfully.");
    }

    private static void calculateAverageGrade() {
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();
        double averageGrade = gradebook.getStudentAverageGrade(studentID);
        System.out.println("Average grade for student ID " + studentID + ": " + averageGrade);
    }

    private static void generateGradeReport() {
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();
        gradebook.generateGradeReport(studentID);
    }

    private static void removeStudent() {
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();
        gradebook.removeStudent(studentID);
        System.out.println("Student removed successfully.");
    }
}