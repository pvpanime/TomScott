package dev.nemi.tomscott.tasker;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.Instant;

@Data
@Value
@Builder
public class TaskerVO {
  int id;
  String title;
  String content;
  Instant start;
  Instant end;
  int finished;
}
