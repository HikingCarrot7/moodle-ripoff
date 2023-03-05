package me.hikingcarrot7.moodleripoff.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import me.hikingcarrot7.moodleripoff.model.Course;
import me.hikingcarrot7.moodleripoff.model.Enrollment;
import me.hikingcarrot7.moodleripoff.model.EnrollmentRequest;
import me.hikingcarrot7.moodleripoff.model.Student;
import me.hikingcarrot7.moodleripoff.repository.CourseRepository;
import me.hikingcarrot7.moodleripoff.repository.EnrollmentRepository;
import me.hikingcarrot7.moodleripoff.service.exception.InvalidCourseCodeException;

import java.util.List;

@ApplicationScoped
public class EnrollmentService {
  @Inject private StudentService studentService;
  @Inject private CourseService courseService;
  @Inject private CourseRepository courseRepository;
  @Inject private EnrollmentRepository enrollmentRepository;

  public List<Enrollment> getEnrolledStudents(Long courseId) {
    return enrollmentRepository.findEnrolledStudentsByCourseId(courseId);
  }

  public Enrollment enrollStudent(Long studentId, Long courseId, EnrollmentRequest request) {
    Course course = courseService.getCourseById(courseId);
    Student student = studentService.getStudentById(studentId);

    if (!course.getCode().equals(request.getCode())) {
      throw new InvalidCourseCodeException();
    }
    if (course.isStudentEnrolled(student)) {
      return enrollmentRepository.findEnrollmentByStudentIdAndCourseId(studentId, courseId);
    }
    course.enrollStudent(student);

    courseRepository.saveCourse(course);
    return enrollmentRepository.findEnrollmentByStudentIdAndCourseId(studentId, courseId);
  }

  public void removeStudentEnrollment(Long studentId, Long courseId) {
    Course course = courseService.getCourseById(courseId);
    Student student = studentService.getStudentById(studentId);
    course.removeEnrollmentOf(student);
    courseRepository.saveCourse(course);
  }

}
