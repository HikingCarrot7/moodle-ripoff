package me.hikingcarrot7.moodleripoff.service.exception;

public class TeacherNotFoundException extends RuntimeException {

  public TeacherNotFoundException(Long id) {
    super(String.format("Teacher with id %d not found", id));
  }

  public TeacherNotFoundException(String email) {
    super(String.format("Teacher with email %s not found", email));
  }

}
