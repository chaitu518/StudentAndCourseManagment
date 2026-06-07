package com.aritribe.learntrack.service;

import com.aritribe.learntrack.entity.Student;
import com.aritribe.learntrack.exception.EntityNotFoudException;

import java.util.ArrayList;

import static com.aritribe.learntrack.util.IGenerator.getNextStudentId;

public class StudentService {

    static ArrayList<Student> students = new ArrayList<>();

    public static void addStudent(String firstName, String lastName, String email, String batch) {
        Student newStudent = new Student(getNextStudentId(), firstName, lastName, email, batch, true);
        students.add(newStudent);
        System.out.println("Student added successfully: " + newStudent.getDisplayName());
    }

    public static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student.getDisplayName() + " - " + (student.isActive() ? "Active" : "Inactive"));
            }
        }
    }

    public static void deactivateStudent(int studentIdToDeactivate) throws EntityNotFoudException {
            for (Student student : students) {
                if (student.getId() == studentIdToDeactivate) {
                    student.setActive(false);
                    System.out.println("Student with ID " + studentIdToDeactivate + " has been deactivated.");
                    return;
                } else {
                    throw new EntityNotFoudException("Student with ID " + studentIdToDeactivate + " not found.");
                }
            }
    }

    public static void searchStudentById(int studentId) throws EntityNotFoudException {

        for (Student student : students) {
            if (student.getId() == studentId) {
                System.out.println(student.getDisplayName() + " - " + (student.isActive() ? "Active" : "Inactive"));
                return;
            }
        }
        throw new EntityNotFoudException("Student with ID " + studentId + " not found.");

    }
}
