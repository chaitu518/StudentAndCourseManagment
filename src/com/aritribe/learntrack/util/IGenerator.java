package com.aritribe.learntrack.util;

public class IGenerator {
    private static int studentIdCounter = 1;
    private static int courseIdCounter = 1;
    private static int EnrollmentIdCounter = 1;

    public static int getNextStudentId() {
        return studentIdCounter++;
    }
    public static int getNextCourseId() {
        return courseIdCounter++;
    }
    public static int getNextEnrollmentId() {
        return EnrollmentIdCounter++;
    }

}
