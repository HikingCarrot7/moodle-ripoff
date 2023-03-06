package me.hikingcarrot7.moodleripoff.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.hikingcarrot7.moodleripoff.model.Course;
import me.hikingcarrot7.moodleripoff.model.Enrollment;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class CourseRepository {
  @Inject private EntityManager em;

  public List<Course> findAllCourses() {
    return em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
  }

  public List<Course> findCoursesByStudentId(Long studentId) {
    return em.createQuery("SELECT c FROM Course c JOIN c.enrollments e WHERE e.student.id = :studentId", Course.class)
        .setParameter("studentId", studentId)
        .getResultList();
  }

  public List<Course> findCoursesByTeacherId(Long teacherId) {
    return em.createQuery("SELECT c FROM Course c WHERE c.teacher.id = :teacherId", Course.class)
        .setParameter("teacherId", teacherId)
        .getResultList();
  }

  public List<Enrollment> findEnrollments(Long courseId) {
    return em.createQuery("SELECT e FROM Enrollment e WHERE e.course.id = :courseId", Enrollment.class)
        .setParameter("courseId", courseId)
        .getResultList();
  }

  public Optional<Course> findCourseById(Long id) {
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
