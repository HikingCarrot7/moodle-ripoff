package me.hikingcarrot7.moodleripoff.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import me.hikingcarrot7.moodleripoff.model.Assignment;
import me.hikingcarrot7.moodleripoff.model.AssignmentCompletedStatusResult;
import me.hikingcarrot7.moodleripoff.model.Course;
import me.hikingcarrot7.moodleripoff.model.Student;
import me.hikingcarrot7.moodleripoff.repository.AssignmentRepository;
import me.hikingcarrot7.moodleripoff.repository.CourseRepository;
import me.hikingcarrot7.moodleripoff.repository.StudentRepository;
import me.hikingcarrot7.moodleripoff.service.exception.AssignmentNotFoundException;

import java.util.List;

@ApplicationScoped
public class AssignmentService {
  @Inject private AssignmentRepository assignmentRepository;
  @Inject private StudentService studentService;
  @Inject private StudentRepository studentRepository;
  @Inject private CourseService courseService;
  @Inject private CourseRepository courseRepository;

  public List<Assignment> getAssignmentsByCourseId(Long courseId) {
    return assignmentRepository.findAssignmentsByCourseId(courseId);
  }

  public List<Assignment> getAssignmentsByStudentId(Long studentId) {
    return assignmentRepository.findAssignmentsByStudentId(studentId);
  }

  public Assignment getAssignmentById(Long assignmentId) {
    return assignmentRepository
        .findAssignmentById(assignmentId)
        .orElseThrow(() -> new AssignmentNotFoundException(assignmentId));
  }

  public Assignment addAssignmentToCourse(Long courseId, Assignment assignment) {
    Course course = courseService.getCourseById(courseId);
    course.addAssignment(assignment);
    courseRepository.saveCourse(course);
    return assignment;
  }

  public AssignmentCompletedStatusResult toggleAssignmentAsCompleted(Long assignmentId, Long studentId) {
    Assignment assignment = getAssignmentById(assignmentId);
    Student student = studentService.getStudentById(studentId);
    boolean assignmentIsCompleted = student.hasCompletedAssignment(assignment);
    if (assignmentIsCompleted) {
      student.removeCompletedAssignment(assignment);
    } else {
      student.addCompletedAssignment(assignment);
    }
    studentRepository.saveStudent(student);
    assignmentRepository.saveAssignment(assignment);
    return new AssignmentCompletedStatusResult(!assignmentIsCompleted, assignmentIsCompleted);
  }

  public Assignment updateAssignment(Long assignmentId, Assignment assignment) {
    Assignment assignmentToUpdate = getAssignmentById(assignmentId);
    assignmentToUpdate.setTitle(assignment.getTitle());
    assignmentToUpdate.setDescription(assignment.getDescription());
    assignmentToUpdate.setDueDate(assignment.getDueDate());
    return assignmentRepository.saveAssignment(assignmentToUpdate);
  }

  public Assignment removeAssignmentFromCourse(Long assignmentId) {
    Assignment assignment = getAssignmentById(assignmentId);
    assignmentRepository.deleteAssignment(assignment);
    return assignment;
  }

}
