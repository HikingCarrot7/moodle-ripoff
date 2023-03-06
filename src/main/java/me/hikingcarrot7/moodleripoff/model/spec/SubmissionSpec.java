package me.hikingcarrot7.moodleripoff.model.spec;

import jakarta.ws.rs.core.EntityPart;
import lombok.Builder;
import lombok.Data;
import me.hikingcarrot7.moodleripoff.model.Submission;

@Data
@Builder
public final class SubmissionSpec {
  private final Submission submission;
  private final Long studentId;
  private final Long assignmentId;
  private final EntityPart file;
}
