package dev.nemi.tomscott.food.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class FoodAddDTO {
  private String name;
  private String description;
  private long price;
}
