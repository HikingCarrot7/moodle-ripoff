package me.hikingcarrot7.moodleripoff.web.dto;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssignmentDTO {
  private Long id;

  @NotBlank(message = "Title cannot be blank")
  private String title;

  @NotBlank(message = "Description cannot be blank")
  private String description;

  @NotNull(message = "Due date cannot be null")
  @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
  private LocalDateTime dueDate;

  private boolean lockAfterDueDate;

  private boolean isLocked;

  private boolean completed;

  @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdAt;

  private CourseDTO course;
}
