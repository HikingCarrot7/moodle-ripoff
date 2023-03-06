package me.hikingcarrot7.moodleripoff.model;

import lombok.Data;

@Data
public class AssignmentCompletedStatusResult {
  private final boolean completed;
  private final boolean wasCompleted;
}
