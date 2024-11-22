package dev.nemi.tomscott.board;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class BoardVO {
  private final int id;
  private final String title;
  private final String content;
  public final Instant createdAt;
  public final Instant lastMod;
  public final Integer userId;
}
