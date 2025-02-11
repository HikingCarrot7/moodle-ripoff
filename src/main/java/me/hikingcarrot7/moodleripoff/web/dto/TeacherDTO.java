package me.hikingcarrot7.moodleripoff.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import me.hikingcarrot7.moodleripoff.web.dto.validation.Password;

@Data
public class TeacherDTO {
  private Long id;

  @NotBlank
  private String name;

  @Email
  @NotBlank
  private String email;

  @Password
  @NotBlank(message = "Password is required")
  private String password;
}
