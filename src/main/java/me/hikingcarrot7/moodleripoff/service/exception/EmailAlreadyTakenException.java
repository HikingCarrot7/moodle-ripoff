package me.hikingcarrot7.moodleripoff.service.exception;

public class EmailAlreadyTakenException extends RuntimeException {

  public EmailAlreadyTakenException(String email) {
    super(String.format("Email %s already exists", email));
  }

}
