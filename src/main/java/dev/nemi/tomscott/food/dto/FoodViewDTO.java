package dev.nemi.tomscott.food.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodViewDTO {
  private long id;
  private String name;
  private String description;
  private long price;

  public String getPriceView() {
    return NumberFormat.getCurrencyInstance(new Locale("ko", "kr")).format(price);
  }

}
