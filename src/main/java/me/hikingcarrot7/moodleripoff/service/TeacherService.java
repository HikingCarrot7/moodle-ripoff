package me.hikingcarrot7.moodleripoff.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import me.hikingcarrot7.moodleripoff.model.Teacher;
import me.hikingcarrot7.moodleripoff.repository.TeacherRepository;
import me.hikingcarrot7.moodleripoff.service.exception.EmailAlreadyTakenException;
import me.hikingcarrot7.moodleripoff.service.exception.TeacherNotFoundException;
import me.hikingcarrot7.moodleripoff.util.PasswordEncoder;

import static java.util.Objects.nonNull;

@ApplicationScoped
public class TeacherService {
  @Inject private TeacherRepository teacherRepository;
  @Inject private PasswordEncoder passwordEncoder;

  public Teacher getTeacherById(Long id) {
    return teacherRepository.getTeacherById(id)
        .orElseThrow(() -> new TeacherNotFoundException(id));
  }

  public Teacher getTeacherByEmail(String email) {
    return teacherRepository.getTeacherByEmail(email)
        .orElseThrow(() -> new TeacherNotFoundException(email));
  }

  public Teacher createTeacher(Teacher teacher) {
    ensureUniqueEmail(teacher.getEmail());
    String password = teacher.getPassword();
    teacher.setPassword(passwordEncoder.encode(password));
    return teacherRepository.saveTeacher(teacher);
  }

  public Teacher updateTeacher(Long teacherId, Teacher teacher) {
    Teacher teacherToUpdate = getTeacherById(teacherId);
    if (nonNull(teacher.getPassword())) {
      String password = teacher.getPassword();
      teacherToUpdate.setPassword(passwordEncoder.encode(password));
    }
    teacherToUpdate.setName(teacher.getName());
    return teacherRepository.updateTeacher(teacherToUpdate);
  }

  public void ensureUniqueEmail(String email) {
    if (teacherRepository.existsEmail(email)) {
      throw new EmailAlreadyTakenException(email);
    }
  }

}
