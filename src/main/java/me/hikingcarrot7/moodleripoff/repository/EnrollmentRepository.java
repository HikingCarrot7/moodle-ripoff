package me.hikingcarrot7.moodleripoff.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import me.hikingcarrot7.moodleripoff.model.Enrollment;

import java.util.List;

@ApplicationScoped
public class EnrollmentRepository {
  @Inject private EntityManager em;

  public List<Enrollment> findEnrolledStudentsByCourseId(Long courseId) {
    return em.createQuery("SELECT e FROM Enrollment e WHERE e.course.id = :courseId", Enrollment.class)
        .setParameter("courseId", courseId)
        .getResultList();
  }

  public Enrollment findEnrollmentByStudentIdAndCourseId(Long studentId, Long courseId) {
    return em.createQuery("SELECT e FROM Enrollment e WHERE e.student.id = :studentId AND e.course.id = :courseId", Enrollment.class)
        .setParameter("studentId", studentId)
        .setParameter("courseId", courseId)
        .getSingleResult();
  }

}
