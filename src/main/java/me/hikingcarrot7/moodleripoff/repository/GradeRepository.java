package me.hikingcarrot7.moodleripoff.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.hikingcarrot7.moodleripoff.model.Grade;

import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class GradeRepository {
  @Inject private EntityManager em;

  public Optional<Grade> findById(Long id) {
    return Optional.ofNullable(em.find(Grade.class, id));
  }

  public Grade saveGrade(Grade grade) {
    em.persist(grade);
    em.flush();
    return grade;
  }

  public Grade updateGrade(Grade grade) {
    em.merge(grade);
    em.flush();
    return grade;
  }

  public void deleteGrade(Grade grade) {
    em.remove(grade);
    em.flush();
  }

}
