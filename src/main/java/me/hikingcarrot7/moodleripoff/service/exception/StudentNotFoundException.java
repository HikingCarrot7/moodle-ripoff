package me.hikingcarrot7.moodleripoff.service.exception;

public class StudentNotFoundException extends RuntimeException {

  public StudentNotFoundException(Long id) {
    super(String.format("Student with id %d not found", id));
  }

  public StudentNotFoundException(String email) {
    super(String.format("Student with email %s not found", email));
  }

}
