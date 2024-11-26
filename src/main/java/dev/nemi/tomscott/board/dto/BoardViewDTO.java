package dev.nemi.tomscott.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@ToString @Getter @AllArgsConstructor
public class BoardViewDTO {
  public final long id;
  public final String title;
  public final String content;
  public final Instant createdAt;
  public final Instant lastMod;
  public final Integer userId;

}
