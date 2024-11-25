package dev.nemi.tomscott.food;


import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class FoodVO {
  long id;
  String name;
  String description;
}
