package dev.nemi.tomscott.food;

import dev.nemi.tomscott.FightingSpirit;
import dev.nemi.tomscott.food.dto.*;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class FoodService {
  private final FoodDAO foodDAO = new FoodDAO();
  private final ModelMapper mapper = FightingSpirit.mapper;

  public FoodService() { }

  public FoodViewDTO toView(FoodVO vo) {
    FoodViewDTO dto = mapper.map(vo, FoodViewDTO.class);
    log.info(dto);
    return dto;
  }

  public FoodVO use(FoodAddDTO dto) {
    FoodVO vo = mapper.map(dto, FoodVO.class);
    log.info(vo);
    return vo;
  }

  public FoodVO use(FoodWholeUpdateDTO dto) {
    FoodVO vo = mapper.map(dto, FoodVO.class);
    log.info(vo);
    return vo;
  }

  public List<FoodViewDTO> listAll() throws SQLException {
    List<FoodVO> ls = foodDAO.getAll();
    return ls.stream().map(this::toView).collect(Collectors.toList());
  }

  public void insert(FoodAddDTO dto) throws SQLException {
    foodDAO.add(use(dto));
  }

  public void update(FoodWholeUpdateDTO vo) throws SQLException {
    foodDAO.update(use(vo));
  }

  public void update(FoodDescriptionUpdateDTO dto) throws SQLException {
    foodDAO.update(dto);
  }

  public void update(FoodNameUpdateDTO dto) throws SQLException {
    foodDAO.update(dto);
  }

}
