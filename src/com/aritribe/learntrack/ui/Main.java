package com.aritribe.learntrack.ui;
import com.aritribe.learntrack.exception.EntityNotFoudException;
import com.aritribe.learntrack.service.CourseService;
import com.aritribe.learntrack.service.EnrollmentService;
import com.aritribe.learntrack.service.StudentService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Student & Course Management System");
        System.out.println("=======================");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Manage Enrollments");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice=-1;
            try {
                choice = scanner.nextInt();
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
                                StudentService.addStudent(firstName, lastName, email, batch);
                                break;
                            case 2:
                                StudentService.viewAllStudents();
                                break;
                            case 3:
                                System.out.println("Enter student ID:");
                                int studentId = scanner.nextInt();
                                StudentService.searchStudentById(studentId);
                                break;
                            case 4:
                                System.out.println("Enter student ID to deactivate:");
                                int studentIdToDeactivate = scanner.nextInt();
                                StudentService.deactivateStudent(studentIdToDeactivate);
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
                                CourseService.addCourse(courseName, courseDescription, durationWeeks);
                                break;
                            case 2:
                                CourseService.viewAllCourses();
                                break;
                            case 3:
                                System.out.println("Enter course ID to toggle status:");
                                int courseIdToToggle = scanner.nextInt();
                                CourseService.toggleCourseStatus(courseIdToToggle);
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
                                EnrollmentService.enrollStudent(studentId, courseId);
                                break;
                            case 2:
                                System.out.println("Enter student ID:");
                                int studentIdForEnrollmentsView = scanner.nextInt();
                                EnrollmentService.viewAllEnrollmentsForStudent(studentIdForEnrollmentsView);
                                break;
                            case 3:
                                System.out.println("Enter enrollment ID:");
                                int enrollmentId = scanner.nextInt();
                                System.out.println("Enter enrollment Status: as ACTIVE, COMPLETED, DROPPED");
                                String enrollmentStatus = scanner.next();
                                EnrollmentService.updateEnrollmentStatus(enrollmentId,enrollmentStatus);
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