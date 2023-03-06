package me.hikingcarrot7.moodleripoff.web.dto.student;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import me.hikingcarrot7.moodleripoff.web.dto.validation.Password;

@Data
public class UpdateStudentRequestDTO {
  @NotBlank(message = "Name is required")
  private String name;

  @Password
  private String password;

  @Password
  private String repeatPassword;
}
