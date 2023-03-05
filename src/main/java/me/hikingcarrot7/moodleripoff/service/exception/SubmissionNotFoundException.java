package me.hikingcarrot7.moodleripoff.service.exception;

public class SubmissionNotFoundException extends RuntimeException {

  public SubmissionNotFoundException(Long id) {
    super(String.format("Submission with id %d not found", id));
  }
  
}
