package me.hikingcarrot7.moodleripoff.service.exception;

public class InvalidCourseCodeException extends RuntimeException {

  public InvalidCourseCodeException() {
    super("Invalid course code");
  }
  
}
