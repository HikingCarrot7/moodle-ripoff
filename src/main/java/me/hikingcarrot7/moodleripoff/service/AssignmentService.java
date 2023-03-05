package me.hikingcarrot7.moodleripoff.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import me.hikingcarrot7.moodleripoff.model.Assignment;
import me.hikingcarrot7.moodleripoff.model.Course;
import me.hikingcarrot7.moodleripoff.repository.AssignmentRepository;
import me.hikingcarrot7.moodleripoff.repository.CourseRepository;

import java.util.List;

@ApplicationScoped
public class AssignmentService {
  @Inject private AssignmentRepository assignmentRepository;
  @Inject private CourseService courseService;
  @Inject private CourseRepository courseRepository;

  public List<Assignment> getAssignmentsByCourseId(Long courseId) {
    return assignmentRepository.findAssignmentsByCourseId(courseId);
  }

  public Assignment getAssignmentById(Long assignmentId) {
    return assignmentRepository
        .findAssignmentById(assignmentId)
        .orElseThrow(() -> new RuntimeException("Assignment not found"));
  }

  public Assignment addAssignmentToCourse(Long courseId, Assignment assignment) {
    Course course = courseService.getCourseById(courseId);
    course.addAssignment(assignment);
    courseRepository.saveCourse(course);
    return assignment;
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
