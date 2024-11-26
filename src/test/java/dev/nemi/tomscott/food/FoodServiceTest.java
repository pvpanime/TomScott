package dev.nemi.tomscott.food;

import dev.nemi.tomscott.food.dto.FoodAddDTO;
import dev.nemi.tomscott.food.dto.FoodViewDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

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



  @Test
  public void getOneTest() throws SQLException {
    Random random = new Random();
    FoodViewDTO dto = service.getOne(random.nextInt(20));
    log.info(dto);
  }

  @Test
  public void addTest() throws SQLException {
    service.add(FoodAddDTO.builder().name("건빵").price(3000).description("배만 채우는데는 이만한 게 없다").build());
  }

}
