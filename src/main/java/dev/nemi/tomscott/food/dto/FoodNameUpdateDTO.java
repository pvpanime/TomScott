package dev.nemi.tomscott.food.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FoodNameUpdateDTO {
  private long id;
  private String name;
}
