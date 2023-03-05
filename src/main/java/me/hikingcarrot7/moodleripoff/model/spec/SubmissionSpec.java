package me.hikingcarrot7.moodleripoff.model.spec;

import lombok.Builder;
import lombok.Data;
import me.hikingcarrot7.moodleripoff.model.Submission;

import java.io.IOException;
import java.io.InputStream;

import static java.util.Objects.isNull;

@Data
@Builder
public final class SubmissionSpec {
  private final Submission submission;
  private final Long studentId;
  private final Long assignmentId;
  private final InputStream file;
  private final String fileName;
  private byte[] fileBytes;

  public boolean hasFile() {
    if (isNull(fileBytes)) {
      fileBytes = getFileBytes(file);
    }
    return fileBytes.length > 0;
  }

  private byte[] getFileBytes(InputStream file) {
    try {
      return file.readAllBytes();
    } catch (IOException e) {
      return new byte[0];
    }
  }

}
