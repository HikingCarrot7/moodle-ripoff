package me.hikingcarrot7.moodleripoff.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.hikingcarrot7.moodleripoff.model.Student;

import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class StudentRepository {
  @Inject private EntityManager em;

  public Optional<Student> findStudentById(Long id) {
    return Optional.ofNullable(em.find(Student.class, id));
  }

  public Student saveStudent(Student newStudent) {
    em.persist(newStudent);
    em.flush();
    return newStudent;
  }

  public boolean existsEmail(String email) {
    return em.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class)
        .setParameter("email", email)
        .getResultList()
        .size() > 0;
  }

}
