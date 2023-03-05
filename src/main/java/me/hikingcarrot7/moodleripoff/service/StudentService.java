package me.hikingcarrot7.moodleripoff.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import me.hikingcarrot7.moodleripoff.model.Student;
import me.hikingcarrot7.moodleripoff.repository.StudentRepository;
import me.hikingcarrot7.moodleripoff.service.exception.EmailAlreadyTakenException;
import me.hikingcarrot7.moodleripoff.service.exception.StudentNotFoundException;
import me.hikingcarrot7.moodleripoff.util.PasswordEncoder;

@ApplicationScoped
public class StudentService {
  @Inject private StudentRepository studentRepository;
  @Inject private PasswordEncoder passwordEncoder;

  public Student getStudentById(Long id) {
    return studentRepository.findStudentById(id)
        .orElseThrow(() -> new StudentNotFoundException(id));
  }

  public Student createStudent(Student newStudent) {
    ensureUniqueEmail(newStudent.getEmail());
    String password = newStudent.getPassword();
    newStudent.setPassword(passwordEncoder.encode(password));
    return studentRepository.saveStudent(newStudent);
  }

  private void ensureUniqueEmail(String email) {
    if (studentRepository.existsEmail(email)) {
      throw new EmailAlreadyTakenException(email);
    }
  }

}
