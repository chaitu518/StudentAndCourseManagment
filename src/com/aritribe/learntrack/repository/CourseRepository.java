package com.aritribe.learntrack.repository;

import com.aritribe.learntrack.entity.Course;
import com.aritribe.learntrack.util.IGenerator;

import java.util.ArrayList;

public class CourseRepository {
    static ArrayList<Course> courses = new ArrayList<>();

     public void addCourse(Course course) {
        courses.add(course);
    }

     public ArrayList<Course> getAllCourses() {
        return courses;
    }

     public Course findCourseById(int courseId) {
        for (Course course : courses) {
            if (course.getId() == courseId) {
                return course;
            }
        }
        return null;
    }

     public void toggleCourseStatus(int courseId) {
         Course course = findCourseById(courseId);
            if (course != null) {
                course.setActive(!course.isActive());
            }
     }
}
