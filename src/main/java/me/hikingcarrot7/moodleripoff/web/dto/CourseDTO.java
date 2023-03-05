package me.hikingcarrot7.moodleripoff.web.dto;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonbPropertyOrder({"id", "name", "description", "code"})
public class CourseDTO {
  private Long id;

  @NotBlank(message = "Name cannot be blank")
  private String name;

  @NotBlank(message = "Description cannot be blank")
  private String description;

  @NotBlank(message = "Code cannot be blank")
  private String code;
}
