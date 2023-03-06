package me.hikingcarrot7.moodleripoff.web.dto.teacher;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateTeacherRequestDTO {
  @NotBlank(message = "Name is required")
  private String name;

  private String password;

  private String repeatPassword;
}
