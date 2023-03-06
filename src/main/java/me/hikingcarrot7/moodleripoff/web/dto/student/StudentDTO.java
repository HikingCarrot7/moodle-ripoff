package me.hikingcarrot7.moodleripoff.web.dto.student;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import me.hikingcarrot7.moodleripoff.web.dto.validation.Password;

@Data
@JsonbPropertyOrder({"id", "name", "email"})
public class StudentDTO {
  private Long id;

  @NotBlank(message = "Name is required")
  private String name;

  @Email
  @NotBlank(message = "Email is required")
  private String email;

  @Password
  @NotBlank(message = "Password is required")
  private String password;
}
