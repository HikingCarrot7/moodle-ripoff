package me.hikingcarrot7.moodleripoff.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static java.util.Objects.nonNull;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CloudFile {
  @Column
  private String publicId;

  @Column
  private String url;

  public boolean isValidFile() {
    return nonNull(publicId) && nonNull(url);
  }

}
