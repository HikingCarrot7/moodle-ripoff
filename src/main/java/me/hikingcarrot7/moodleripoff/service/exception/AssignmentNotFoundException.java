package me.hikingcarrot7.moodleripoff.service.exception;

public class AssignmentNotFoundException extends RuntimeException {

  public AssignmentNotFoundException(Long id) {
    super(String.format("Assignment with id %d not found", id));
  }

}
