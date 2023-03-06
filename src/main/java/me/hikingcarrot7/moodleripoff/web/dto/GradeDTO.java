package me.hikingcarrot7.moodleripoff.web.dto;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

@Data
@JsonbPropertyOrder({"id", "score", "feedback"})
public class GradeDTO {
  private Long id;

  @DecimalMax(value = "100.0", message = "Score must be less than or equal to 100.0")
  @DecimalMin(value = "0.0", message = "Score must be greater than or equal to 0.0")
  private Double score;

  private String feedback;
}
