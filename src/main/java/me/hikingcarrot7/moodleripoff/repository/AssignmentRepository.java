package me.hikingcarrot7.moodleripoff.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.hikingcarrot7.moodleripoff.model.Assignment;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class AssignmentRepository {
  @Inject private EntityManager em;

  public List<Assignment> findAssignmentsByCourseId(Long courseId) {
    return em.createQuery("SELECT a FROM Assignment a WHERE a.course.id = :courseId", Assignment.class)
        .setParameter("courseId", courseId)
        .getResultList();
  }

  public List<Assignment> findAssignmentsByStudentId(Long studentId) {
    return em.createQuery("""
            SELECT a FROM Assignment a
            WHERE a.course.id IN (
              SELECT e.course.id FROM Enrollment e
              WHERE e.student.id = :studentId
            )
            """, Assignment.class)
        .setParameter("studentId", studentId)
        .getResultList();
  }

  public Optional<Assignment> findAssignmentById(Long assignmentId) {
    return Optional.ofNullable(em.find(Assignment.class, assignmentId));
  }

  public Assignment saveAssignment(Assignment assignment) {
    em.persist(assignment);
    em.flush();
    return assignment;
  }

  public void deleteAssignment(Assignment assignment) {
    em.remove(assignment);
    em.flush();
  }

}
