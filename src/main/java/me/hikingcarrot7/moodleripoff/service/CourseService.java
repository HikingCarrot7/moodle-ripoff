package me.hikingcarrot7.moodleripoff.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import me.hikingcarrot7.moodleripoff.model.Course;
import me.hikingcarrot7.moodleripoff.model.Enrollment;
import me.hikingcarrot7.moodleripoff.model.Teacher;
import me.hikingcarrot7.moodleripoff.repository.CourseRepository;
import me.hikingcarrot7.moodleripoff.service.exception.CourseNotFoundException;

import java.util.List;

@ApplicationScoped
public class CourseService {
  @Inject private TeacherService teacherService;
  @Inject private CourseRepository courseRepository;

  public List<Course> getAllCourses() {
    return courseRepository.findAllCourses();
  }

  public Course getCourseById(Long id) {
    return courseRepository
        .findCourseById(id)
        .orElseThrow(() -> new CourseNotFoundException(id));
  }

  public List<Course> getCoursesByStudentId(Long studentId) {
    return courseRepository.findCoursesByStudentId(studentId);
  }

  public List<Course> getCoursesByTeacherId(Long teacherId) {
    return courseRepository.findCoursesByTeacherId(teacherId);
  }

  public List<Enrollment> getEnrollments(Long courseId) {
    return courseRepository.findEnrollments(courseId);
  }

  public Course addCourseToTeacher(Long teacherId, Course course) {
    Teacher teacher = teacherService.getTeacherById(teacherId);
    teacher.addCourse(course);
    return courseRepository.saveCourse(course);
  }

  public Course updateCourse(Long teacherId, Long courseId, Course course) {
    Teacher teacher = teacherService.getTeacherById(teacherId);
    Course courseToUpdate = getCourseById(courseId);

    ensureCourseIsOwnedByTeacher(teacher, courseToUpdate);
    courseToUpdate.setName(course.getName());
    courseToUpdate.setDescription(course.getDescription());

    return courseRepository.saveCourse(courseToUpdate);
  }

  public Course removeCourseFromTeacher(Long teacherId, Long courseId) {
    Teacher teacher = teacherService.getTeacherById(teacherId);
    Course courseToDelete = getCourseById(courseId);

    ensureCourseIsOwnedByTeacher(teacher, courseToDelete);
    teacher.removeCourse(courseToDelete);
    courseRepository.deleteCourse(courseToDelete);

    return courseToDelete;
  }

  private void ensureCourseIsOwnedByTeacher(Teacher teacher, Course course) {
    if (!course.getTeacher().getId().equals(teacher.getId())) {
      throw new CourseNotFoundException(course.getId());
    }
  }

}
