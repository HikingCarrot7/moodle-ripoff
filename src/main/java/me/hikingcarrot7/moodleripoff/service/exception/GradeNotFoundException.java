package me.hikingcarrot7.moodleripoff.service.exception;

public class GradeNotFoundException extends RuntimeException {

  public GradeNotFoundException(Long gradeId) {
    super(String.format("Grade with id %d not found", gradeId));
  }

}
