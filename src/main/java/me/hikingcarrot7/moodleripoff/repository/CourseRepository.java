package me.hikingcarrot7.moodleripoff.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.hikingcarrot7.moodleripoff.model.Course;
import me.hikingcarrot7.moodleripoff.model.Student;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class CourseRepository {
  @Inject private EntityManager em;

  public List<Student> getEnrolledStudents(Long courseId) {
    return em.createQuery("SELECT s FROM Student s JOIN s.enrolledCourses c WHERE c.id = :courseId", Student.class)
        .setParameter("courseId", courseId)
        .getResultList();
  }

  public Optional<Course> getCourseById(Long id) {
    return Optional.ofNullable(em.find(Course.class, id));
  }

  public Course saveCourse(Course course) {
    em.persist(course);
    em.flush();
    return course;
  }

  public void deleteCourse(Course course) {
    em.remove(course);
    em.flush();
  }

}
