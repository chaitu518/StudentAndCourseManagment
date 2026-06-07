package com.aritribe.learntrack.service;

import com.aritribe.learntrack.entity.Course;
import com.aritribe.learntrack.exception.EntityNotFoudException;
import com.aritribe.learntrack.util.IGenerator;

import java.util.ArrayList;

public class CourseService {
    static ArrayList<Course> courses = new ArrayList<>();
    public static void addCourse(String courseName, String courseDescription, int durationWeeks) {
        Course newCourse = new Course(IGenerator.getNextCourseId(),courseName, courseDescription, durationWeeks,true);
        courses.add(newCourse);
        System.out.println("Course added successfully: " + newCourse.getCourseName());
    }

    public static void viewAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            for (Course course : courses) {
                System.out.println(course.getCourseName() + " - " + (course.isActive() ? "Active" : "Inactive"));
            }
        }
    }

    public static void toggleCourseStatus(int courseIdToToggle) throws EntityNotFoudException {
            for (Course course : courses) {
                if (course.getId() == courseIdToToggle) {
                    course.setActive(!course.isActive());
                    System.out.println("Course with ID " + courseIdToToggle + " is now " + (course.isActive() ? "Active" : "Inactive") + ".");
                    return;
                }
            }
            throw new EntityNotFoudException("Course with ID " + courseIdToToggle + " not found.");
    }
    public static void findCourseById(int courseId) throws EntityNotFoudException {

            for (Course course : courses) {
                if (course.getId() == courseId) {
                    System.out.println(course.getCourseName() + " - " + (course.isActive() ? "Active" : "Inactive"));
                    return;
                }
            }
            throw new EntityNotFoudException("Course with ID " + courseId + " not found.");
    }
}
