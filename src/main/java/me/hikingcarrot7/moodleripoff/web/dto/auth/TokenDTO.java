package me.hikingcarrot7.moodleripoff.web.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDTO {
  private Long id;
  private String email;
  private String name;
  private String accessToken;
}
