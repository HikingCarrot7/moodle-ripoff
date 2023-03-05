package me.hikingcarrot7.moodleripoff.web.dto;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonbPropertyOrder({"id", "name", "email"})
public class StudentDTO {
  private Long id;

  @NotBlank(message = "Name is required")
  private String name;

  @Email
  @NotBlank(message = "Email is required")
  private String email;

  @NotBlank(message = "Password is required")
  private String password;
}
