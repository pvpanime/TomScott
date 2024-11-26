package dev.nemi.tomscott.board;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class BoardVO {
  private int id;
  private String title;
  private String content;
  public Instant createdAt;
  public Instant lastMod;
  public Integer userId;
}
