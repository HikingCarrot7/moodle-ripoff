package me.hikingcarrot7.moodleripoff.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.hikingcarrot7.moodleripoff.model.Submission;

import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class SubmissionRepository {
  @Inject private EntityManager em;

  public Optional<Submission> getSubmissionById(Long id) {
    return Optional.ofNullable(em.find(Submission.class, id));
  }

  public Optional<Submission> findSubmissionOfAssignmentByStudentId(Long assignmentId, Long studentId) {
    return em.createQuery("SELECT s FROM Submission s WHERE s.assignment.id = :assignmentId AND s.student.id = :studentId", Submission.class)
        .setParameter("assignmentId", assignmentId)
        .setParameter("studentId", studentId)
        .getResultStream()
        .findFirst();
  }

  public Submission saveSubmission(Submission submission) {
    em.persist(submission);
    em.flush();
    return submission;
  }

  public void deleteSubmission(Submission submission) {
    em.remove(submission);
    em.flush();
  }

}
