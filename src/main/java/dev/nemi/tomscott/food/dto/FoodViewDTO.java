package dev.nemi.tomscott.food.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodViewDTO {
  private long id;
  private String name;
  private String description;
}
