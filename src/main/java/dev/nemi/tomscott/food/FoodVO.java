package dev.nemi.tomscott.food;


import lombok.*;
import org.checkerframework.checker.units.qual.A;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodVO {
  private long id;
  private String name;
  private String description;
  private long price;
}
