package dev.nemi.tomscott.board.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@ToString
public class BoardUpdateDTO {
  private final long id;
  private final String title;
  private final String content;
}
