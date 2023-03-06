package me.hikingcarrot7.moodleripoff.web.dto;

import jakarta.json.bind.annotation.JsonbDateFormat;
import lombok.Data;
import me.hikingcarrot7.moodleripoff.web.dto.student.StudentDTO;

import java.time.LocalDateTime;

@Data
public class EnrollmentDTO {
  private StudentDTO student;

  @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdAt;
}
