package com.aritribe.learntrack.service;

import com.aritribe.learntrack.entity.Course;
import com.aritribe.learntrack.exception.EntityNotFoudException;
import com.aritribe.learntrack.repository.CourseRepository;
import com.aritribe.learntrack.util.IGenerator;

import java.util.List;

public class CourseService {

    CourseRepository courseRepository = new CourseRepository();

    public void addCourse(String courseName, String courseDescription, int durationWeeks) {
        Course newCourse = new Course(IGenerator.getNextCourseId(),courseName, courseDescription, durationWeeks,true);
        courseRepository.addCourse(newCourse);
    }

    public List<Course> viewAllCourses() {
        return courseRepository.getAllCourses();
    }

    public void toggleCourseStatus(int courseIdToToggle) throws EntityNotFoudException {
        Course course = courseRepository.findCourseById(courseIdToToggle);
        if(course == null)
            throw new EntityNotFoudException("Course with ID " + courseIdToToggle + " not found.");
        courseRepository.toggleCourseStatus(courseIdToToggle);
    }
    public Course findCourseById(int courseId) throws EntityNotFoudException {

        Course course = courseRepository.findCourseById(courseId);
        if(course == null)
            throw new EntityNotFoudException("Course with ID " + courseId + " not found.");
        return course;
    }
}
