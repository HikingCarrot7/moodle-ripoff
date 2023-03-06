package me.hikingcarrot7.moodleripoff.web.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import me.hikingcarrot7.moodleripoff.web.dto.validation.Password;

@Data
public class LoginRequestDTO {
  @Email
  @NotBlank(message = "An email is required.")
  private String email;

  @Password
  @NotBlank(message = "A password is required.")
  private String password;
}
