package me.hikingcarrot7.moodleripoff.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SecurityUser {
  private Long id;
  private String email;
  private String role;
}
