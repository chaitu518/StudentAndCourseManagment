package com.aritribe.learntrack.service;

import com.aritribe.learntrack.entity.Course;
import com.aritribe.learntrack.entity.Enrollment;
import com.aritribe.learntrack.entity.EnrollmentStatus;
import com.aritribe.learntrack.entity.Student;
import com.aritribe.learntrack.exception.EntityNotFoudException;
import com.aritribe.learntrack.repository.EnrollmentRepository;
import com.aritribe.learntrack.util.IGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EnrollmentService {

    CourseService courseService = new CourseService();
    StudentService studentService = new StudentService();
    EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

    public Enrollment enrollStudent(int studentId, int courseId) throws EntityNotFoudException {
        Student student = studentService.searchStudentById(studentId);
        Course course = courseService.findCourseById(courseId);
        Date currentDate = new Date();
        String enrollmentDate = currentDate.toString();
        Enrollment enrollment = new Enrollment(IGenerator.getNextEnrollmentId(), studentId, courseId, enrollmentDate, EnrollmentStatus.ACTIVE); // ID will be set by the database
        return enrollmentRepository.addEnrollment(enrollment);
    }

    public Enrollment updateEnrollmentStatus(int enrollmentId, String enrollmentStatus) throws EntityNotFoudException, IllegalArgumentException {
        Enrollment enrollment = enrollmentRepository.updateEnrollmentStatus(enrollmentId, EnrollmentStatus.valueOf(enrollmentStatus.toUpperCase()));
        if(enrollment == null) {
            throw new EntityNotFoudException("Enrollment with ID " + enrollmentId + " not found.");
        }
        return enrollment;
    }

    public List<Enrollment> viewAllEnrollmentsForStudent(int studentIdForEnrollmentsView) throws EntityNotFoudException {
        Student student = new StudentService().searchStudentById(studentIdForEnrollmentsView);
        List<Enrollment> Enrollments = enrollmentRepository.getAllEnrollments();
        List<Enrollment> studentEnrollments = new ArrayList<>();
        for (Enrollment enrollment : Enrollments) {
            if (enrollment.getStudentId() == studentIdForEnrollmentsView) {
                studentEnrollments.add(enrollment);
            }
        }
        return studentEnrollments;
    }
}
