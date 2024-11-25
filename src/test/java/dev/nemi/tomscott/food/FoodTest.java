package dev.nemi.tomscott.food;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class FoodTest {

  FoodDAO foodDAO;

  @BeforeEach
  public void init() {
    foodDAO = new FoodDAO();
  }

  @Test
  public void listTest() throws SQLException {
    List<FoodVO> foodList = foodDAO.getAll();
    Assertions.assertNotNull(foodList);
    Assertions.assertFalse(foodList.isEmpty());
    for (FoodVO food : foodList) {
      System.out.println(food);
    }
  }

  @Test
  public void pickTest() throws SQLException {
    FoodVO food8 = foodDAO.getById(10);
    Assertions.assertNotNull(food8);
    System.out.println(food8);

    FoodVO food1 = foodDAO.getById(1);
    Assertions.assertNull(food1);
  }

  @Test
  public void deleteTest() throws SQLException {
    FoodVO food15 = foodDAO.getById(15);
    Assertions.assertNotNull(food15);
    System.out.println(food15);
    foodDAO.delete(15);

    food15 = foodDAO.getById(15);
    Assertions.assertNull(food15);
    System.out.println(food15);
  }

  @Test
  public void updateTest() throws SQLException {
    foodDAO.update(FoodVO.builder()
      .id(3)
      .name("치즈돈까스")
      .description("겉은 촉촉 속은 바삭")
      .build()
    );
  }

}
