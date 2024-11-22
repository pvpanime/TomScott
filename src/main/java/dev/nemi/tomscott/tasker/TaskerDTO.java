package dev.nemi.tomscott.tasker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.Instant;


@Getter
@Data
@AllArgsConstructor
public class TaskerDTO {
  private int id;
  private String title;
  private String content;
  private Instant start;
  private Instant end;
  private int finished;
}
