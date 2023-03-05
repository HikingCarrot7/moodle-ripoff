package me.hikingcarrot7.moodleripoff.service.exception;

public class StudentNotFoundException extends RuntimeException {

  public StudentNotFoundException(Long id) {
    super(String.format("Student with id %d not found", id));
  }

}
