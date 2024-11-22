package dev.nemi.tomscott;

import dev.nemi.tomscott.board.BoardDAO;
import dev.nemi.tomscott.board.BoardDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class BoardDAOTest {
  private BoardDAO dao;

  @BeforeEach
  public void setUp() {
    dao = new BoardDAO();
  }

  @Test
  public void getTime() throws SQLException {
    List<BoardDTO> list = dao.getAllBoards();
    System.out.println(list);
  }

}
