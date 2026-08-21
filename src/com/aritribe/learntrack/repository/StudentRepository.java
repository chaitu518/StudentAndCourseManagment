package com.aritribe.learntrack.repository;

import com.aritribe.learntrack.entity.Student;

import java.util.ArrayList;

public class StudentRepository {
    static ArrayList<Student> students = new ArrayList<>();

     public void addStudent(Student student) {
        students.add(student);
    }

     public ArrayList<Student> getAllStudents() {
        return students;
    }

     public Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

     public void deactivateStudent(int studentId) {
         Student student = findStudentById(studentId);
            if (student != null) {
                student.setActive(false);
            }
     }
}
