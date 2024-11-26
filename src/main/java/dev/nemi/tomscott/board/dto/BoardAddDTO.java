package dev.nemi.tomscott.board.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardAddDTO {
  private String title;
  private String content;
  private Integer userNum;
}
