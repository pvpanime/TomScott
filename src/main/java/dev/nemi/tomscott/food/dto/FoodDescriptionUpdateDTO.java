package dev.nemi.tomscott.food.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FoodDescriptionUpdateDTO {
  private long id;
  private String description;
}
