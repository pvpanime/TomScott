package dev.nemi.tomscott.board;

import dev.nemi.tomscott.FightingSpirit;
import dev.nemi.tomscott.board.dto.BoardAddDTO;
import dev.nemi.tomscott.board.dto.BoardViewDTO;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;

public class BoardService {

  private final BoardDAO dao;
  private final ModelMapper mapper;

  public BoardService() {
    this.dao = new BoardDAO();
    this.mapper = FightingSpirit.mapper;
  }

  public void add(BoardAddDTO dto) throws SQLException {
    dao.insert(mapper.map(dto, BoardVO.class));
  }

  public List<BoardViewDTO> list() throws SQLException {
    return dao.getListAt();
  }

  public static void createNew(String title, String content) throws SQLException {
    new BoardDAO().insert(BoardVO.builder().title(title).content(content).build());
  }

  public static void update(int id, String title, String content) throws SQLException {
    new BoardDAO().update(id, title, content);
  }

  public static BoardViewDTO getByPathinfo(String pathinfo) throws NumberFormatException, SQLException {
    int id = Integer.parseInt(pathinfo.substring(1));
    return getById(id);
  }

  public static BoardViewDTO getById(int id) throws SQLException {
    return new BoardDAO().getBoardById(id);
  }

  public static void removeByPathinfo(String pathinfo) throws NumberFormatException, SQLException {
    int id = Integer.parseInt(pathinfo.substring(1));
    removeById(id);
  }

  public static void removeById(long id) throws SQLException {
    new BoardDAO().remove(id);
  }

}
