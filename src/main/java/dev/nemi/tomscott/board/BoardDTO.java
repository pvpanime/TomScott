package dev.nemi.tomscott.board;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;

import java.net.URLEncoder;
import java.time.Instant;

@ToString @Getter
public class BoardDTO {
  public final int id;
  public final String title;
  public final String content;
  public final Instant createdAt;
  public final Instant lastMod;
  public final Integer userId;

  public BoardDTO(int id, String title, String content, Instant createdAt, Instant lastMod, Integer userId) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.createdAt = createdAt;
    this.lastMod = lastMod;
    this.userId = userId;
  }

  @SneakyThrows
  public String getPath() {
    return URLEncoder.encode(title, "UTF-8");
  }

}
