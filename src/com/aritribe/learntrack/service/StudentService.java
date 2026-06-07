package com.aritribe.learntrack.service;

import com.aritribe.learntrack.entity.Student;
import com.aritribe.learntrack.exception.EntityNotFoudException;
import com.aritribe.learntrack.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

import static com.aritribe.learntrack.util.IGenerator.getNextStudentId;

public class StudentService {

    StudentRepository studentRepository = new StudentRepository();

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        Student newStudent = new Student(getNextStudentId(), firstName, lastName, email, batch, true);
        studentRepository.addStudent(newStudent);
        return newStudent;
    }

    public List<Student> viewAllStudents() {
        return studentRepository.getAllStudents();
    }

    public void deactivateStudent(int studentIdToDeactivate) throws EntityNotFoudException {
        Student student = studentRepository.findStudentById(studentIdToDeactivate);
        if (student != null) {
            studentRepository.deactivateStudent(studentIdToDeactivate);
        }
        else{
            throw new EntityNotFoudException("Student with ID " + studentIdToDeactivate + " not found.");
        }

    }

    public Student searchStudentById(int studentId) throws EntityNotFoudException {
        Student student = studentRepository.findStudentById(studentId);
        if(student != null) {
            return student;
        }
        else {
            throw new EntityNotFoudException("Student with ID " + studentId + " not found.");
        }
    }
}
