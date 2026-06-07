package com.aritribe.learntrack.service;

import com.aritribe.learntrack.entity.Enrollment;
import com.aritribe.learntrack.entity.EnrollmentStatus;
import com.aritribe.learntrack.exception.EntityNotFoudException;
import com.aritribe.learntrack.util.IGenerator;

import java.util.ArrayList;
import java.util.Date;

public class EnrollmentService {
    static ArrayList<Enrollment> enrollments = new ArrayList<>();
    public static void enrollStudent(int studentId, int courseId) throws EntityNotFoudException {
        StudentService.searchStudentById(studentId);
        CourseService.findCourseById(courseId);
        Date currentDate = new Date();
        String enrollmentDate = currentDate.toString();
         Enrollment enrollment = new Enrollment(IGenerator.getNextEnrollmentId(), studentId, courseId, enrollmentDate, EnrollmentStatus.ACTIVE); // ID will be set by the database
        enrollments.add(enrollment);
        System.out.println("Student with ID " + studentId + " enrolled on "+enrollmentDate+", course with ID " + courseId + " successfully.");
    }

    public static void updateEnrollmentStatus(int enrollmentId, String enrollmentStatus) throws EntityNotFoudException, IllegalArgumentException {
        int foundIndex = -1;
            for (Enrollment enrollment : enrollments) {
                if (enrollment.getId() == enrollmentId) {

                        EnrollmentStatus status = EnrollmentStatus.valueOf(enrollmentStatus.toUpperCase());
                        enrollment.setStatus(status);
                        System.out.println("Enrollment with ID " + enrollmentId + " status updated to " + status + ".");
                        foundIndex=enrollments.indexOf(enrollment);
                }
            }
        if(foundIndex==-1)
            throw new EntityNotFoudException("Enrollment with ID " + enrollmentId + " not found.");

    }

    public static void viewAllEnrollmentsForStudent(int studentIdForEnrollmentsView) throws EntityNotFoudException {
        StudentService.searchStudentById(studentIdForEnrollmentsView);
        for(Enrollment enrollment : enrollments) {
            if(enrollment.getStudentId() == studentIdForEnrollmentsView) {
                System.out.println("StudentID : "+studentIdForEnrollmentsView+" Enrollment ID: " + enrollment.getId() + ", Course ID: " + enrollment.getCourseId() + ", Enrollment Date: " + enrollment.getEnrollmentDate() + ", Status: " + enrollment.getStatus());
            }
        }


    }
}
