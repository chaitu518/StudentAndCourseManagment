package com.aritribe.learntrack.repository;

import com.aritribe.learntrack.entity.Enrollment;
import com.aritribe.learntrack.entity.EnrollmentStatus;


import java.util.ArrayList;

public class EnrollmentRepository {
    static ArrayList<Enrollment> enrollments = new ArrayList<>();

     public Enrollment addEnrollment(Enrollment enrollment) {
         enrollments.add(enrollment);
         return enrollment;
    }

     public ArrayList<Enrollment> getAllEnrollments() {
        return enrollments;
    }

     public Enrollment findEnrollmentById(int enrollmentId) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == enrollmentId) {
                return enrollment;
            }
        }
        return null;
    }

     public Enrollment updateEnrollmentStatus(int enrollmentId, EnrollmentStatus enrollmentStatus) {
         Enrollment enrollment = findEnrollmentById(enrollmentId);
         if (enrollment != null) {
            enrollment.setStatus(enrollmentStatus);
         }
         return enrollment;
     }
}
