package me.hikingcarrot7.moodleripoff.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.hikingcarrot7.moodleripoff.model.Teacher;

import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class TeacherRepository {
  @Inject private EntityManager em;

  public Optional<Teacher> getTeacherById(Long id) {
    return Optional.ofNullable(em.find(Teacher.class, id));
  }

  public Optional<Teacher> getTeacherByEmail(String email) {
    return em.createQuery("SELECT t FROM Teacher t WHERE t.email = :email", Teacher.class)
        .setParameter("email", email)
        .getResultStream()
        .findFirst();
  }

  public Teacher saveTeacher(Teacher teacher) {
    em.persist(teacher);
    em.flush();
    return teacher;
  }

  public Teacher updateTeacher(Teacher teacher) {
    em.merge(teacher);
    em.flush();
    return teacher;
  }

  public boolean existsEmail(String email) {
    return em.createQuery("SELECT 1 FROM Teacher t WHERE t.email = :email", Long.class)
        .setParameter("email", email)
        .getResultList()
        .size() > 0;
  }

}
