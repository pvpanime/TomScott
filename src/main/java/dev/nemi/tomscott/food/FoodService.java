package dev.nemi.tomscott.food;

import dev.nemi.tomscott.FightingSpirit;
import dev.nemi.tomscott.food.dto.*;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class FoodService {
  private final FoodDAO foodDAO = new FoodDAO();
  private final ModelMapper mapper = FightingSpirit.mapper;

  public FoodService() { }

  public FoodViewDTO toView(FoodVO vo) {
    return mapper.map(vo, FoodViewDTO.class);
  }

  public FoodVO use(FoodAddDTO dto) {
    return mapper.map(dto, FoodVO.class);
  }

  public FoodVO use(FoodWholeUpdateDTO dto) {
    return mapper.map(dto, FoodVO.class);
  }

  public FoodViewDTO getOne(long id) throws SQLException {
    FoodVO vo = foodDAO.getById(id);
    return toView(vo);
  }

  public List<FoodViewDTO> listAll() throws SQLException {
    List<FoodVO> ls = foodDAO.getAll();
    return ls.stream().map(this::toView).collect(Collectors.toList());
  }

  public void add(FoodAddDTO dto) throws SQLException {
    foodDAO.add(use(dto));
  }

  public void update(FoodWholeUpdateDTO vo) throws SQLException {
    foodDAO.update(use(vo));
  }


}
