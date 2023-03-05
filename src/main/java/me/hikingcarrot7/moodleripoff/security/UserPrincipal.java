package me.hikingcarrot7.moodleripoff.security;

import lombok.Data;

import java.security.Principal;
import java.util.List;

@Data
public class UserPrincipal implements Principal {

  @Override
  public String getName() {
    return "test";
  }

  public List<String> getRoles() {
    return List.of("student");
  }

}
