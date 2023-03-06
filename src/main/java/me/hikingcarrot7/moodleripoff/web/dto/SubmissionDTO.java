package me.hikingcarrot7.moodleripoff.web.dto;

import lombok.Data;
import me.hikingcarrot7.moodleripoff.model.CloudFile;

@Data
public class SubmissionDTO {
  private String description;
  private CloudFile file;
}
