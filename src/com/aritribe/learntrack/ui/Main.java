package com.aritribe.learntrack.ui;
import com.aritribe.learntrack.entity.Course;
import com.aritribe.learntrack.entity.Enrollment;
import com.aritribe.learntrack.entity.Student;
import com.aritribe.learntrack.exception.EntityNotFoudException;
import com.aritribe.learntrack.service.CourseService;
import com.aritribe.learntrack.service.EnrollmentService;
import com.aritribe.learntrack.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        CourseService courseService = new CourseService();
        StudentService studentService = new StudentService();
        EnrollmentService enrollmentService = new EnrollmentService();

        System.out.println("Student & Course Management System");
        System.out.println("=======================");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Manage Enrollments");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("1. add new student");
                        System.out.println("2. view all students");
                        System.out.println("3. Search student by Id");
                        System.out.println("4. Deactivate student");
                        int studentChoice = scanner.nextInt();
                        switch (studentChoice) {
                            case 1:
                                System.out.println("Enter first name:");
                                String firstName = scanner.next();
                                System.out.println("Enter last name:");
                                String lastName = scanner.next();
                                System.out.println("Enter email:");
                                String email = scanner.next();
                                System.out.println("Enter batch:");
                                String batch = scanner.next();
                                Student student1 = studentService.addStudent(firstName, lastName, email, batch);
                                System.out.println("Student added successfully: " + student1.getFirstName() + " " + student1.getLastName()+ " with ID: " + student1.getId());
                                break;
                            case 2:
                                List<Student> students = studentService.viewAllStudents();
                                for(Student student : students) {
                                    System.out.println("ID: " + student.getId() + ", Name: " + student.getFirstName() + " " + student.getLastName() + ", Email: " + student.getEmail() + ", Batch: " + student.getBatch() + ", Status: " + (student.isActive() ? "Active" : "Inactive"));
                                }
                                break;
                            case 3:
                                System.out.println("Enter student ID:");
                                int studentId = scanner.nextInt();
                                Student student = studentService.searchStudentById(studentId);
                                System.out.println("ID: " + student.getId() + ", Name: " + student.getFirstName() + " " + student.getLastName() + ", Email: " + student.getEmail() + ", Batch: " + student.getBatch() + ", Status: " + (student.isActive() ? "Active" : "Inactive"));
                                break;
                            case 4:
                                System.out.println("Enter student ID to deactivate:");
                                int studentIdToDeactivate = scanner.nextInt();
                                studentService.deactivateStudent(studentIdToDeactivate);
                                System.out.println("Student with ID " + studentIdToDeactivate + " deactivated successfully.");
                                break;
                            default:
                                System.out.println("Invalid option. Please try again.");
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("1. add new course");
                        System.out.println("2. view all courses");
                        System.out.println("3. Activate/Deactivate course");
                        int courseChoice = scanner.nextInt();
                        switch (courseChoice) {
                            case 1:
                                System.out.println("Enter course name:");
                                String courseName = scanner.next();
                                System.out.println("Enter course description:");
                                String courseDescription = scanner.nextLine();
                                scanner.nextLine(); // Consume the newline left by next()
                                System.out.println("Enter duration in weeks:");
                                int durationWeeks = scanner.nextInt();
                                courseService.addCourse(courseName, courseDescription, durationWeeks);
                                System.out.println("Course added successfully: " + courseName);
                                break;
                            case 2:
                                List<Course> courses = courseService.viewAllCourses();
                                if (courses.isEmpty()) {
                                    System.out.println("No courses found.");
                                } else {
                                    for (Course course : courses) {
                                        System.out.println("Course ID: " + course.getId() + ", Name: " + course.getCourseName() + ", Description: " + course.getDescription() + ", Duration: " + course.getDurationInWeeks() + " weeks, Status: " + (course.isActive() ? "Active" : "Inactive"));
                                    }
                                }
                                break;
                            case 3:
                                System.out.println("Enter course ID to toggle status:");
                                int courseIdToToggle = scanner.nextInt();
                                courseService.toggleCourseStatus(courseIdToToggle);
                                System.out.println("Course with ID " + courseIdToToggle + " status toggled successfully.");
                                break;
                            default:
                                System.out.println("Invalid option. Please try again.");
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("1. Enroll student in course");
                        System.out.println("2. View enrollments for a student");
                        System.out.println("3. Mark enrollment as completed/Cancelled");
                        int enrollmentChoice = scanner.nextInt();
                        switch (enrollmentChoice) {
                            case 1:
                                System.out.println("Enter student ID:");
                                int studentId = scanner.nextInt();
                                System.out.println("Enter course ID:");
                                int courseId = scanner.nextInt();
                                Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
                                System.out.println("Student with ID " + studentId + " enrolled in course with ID " + courseId + " successfully. Enrollment ID: " + enrollment.getId()+", Enrollment Date: " + enrollment.getEnrollmentDate() + ", Status: " + enrollment.getStatus());
                                break;
                            case 2:
                                System.out.println("Enter student ID:");
                                int studentIdForEnrollmentsView = scanner.nextInt();
                                List<Enrollment> enrollments = enrollmentService.viewAllEnrollmentsForStudent(studentIdForEnrollmentsView);
                                if (enrollments.isEmpty()) {
                                    System.out.println("No enrollments found for student with ID " + studentIdForEnrollmentsView);
                                } else {
                                    for (Enrollment e : enrollments) {
                                        System.out.println("Enrollment ID: " + e.getId() + ", Course ID: " + e.getCourseId() + ", Enrollment Date: " + e.getEnrollmentDate() + ", Status: " + e.getStatus());
                                    }
                                }
                                break;
                            case 3:
                                System.out.println("Enter enrollment ID:");
                                int enrollmentId = scanner.nextInt();
                                System.out.println("Enter enrollment Status: as ACTIVE, COMPLETED, DROPPED");
                                String enrollmentStatus = scanner.next();
                                Enrollment enrollment1 = enrollmentService.updateEnrollmentStatus(enrollmentId,enrollmentStatus);
                                System.out.println("Enrollment with ID " + enrollmentId + " status updated successfully to " + enrollment1.getStatus());
                                break;
                            default:
                                System.out.println("Invalid option. Please try again.");
                                break;
                        }
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
            catch (EntityNotFoudException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
            catch (IllegalArgumentException e) {
                System.out.println("Invalid enrollment status. Please enter ACTIVE, COMPLETED, or DROPPED.");
            }
            catch (Exception e) {
                System.out.println("Invalid input. Please enter valid input"+e.getMessage());
                scanner.nextLine();
            }
        }

    }
}