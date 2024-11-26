package dev.nemi.tomscott.food;

import dev.nemi.tomscott.food.dto.FoodViewDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

@Log4j2
public class FoodServiceTest {

  private FoodService service;

  @BeforeEach
  public void setUp() {
    service = new FoodService();
  }

  @Test
  public void getAllTest() throws SQLException {
    List<FoodViewDTO> list = service.listAll();
    log.info(list);
  }

}
