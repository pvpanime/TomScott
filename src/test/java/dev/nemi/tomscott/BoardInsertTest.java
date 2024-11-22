package dev.nemi.tomscott;

import dev.nemi.tomscott.board.BoardDAO;
import dev.nemi.tomscott.board.BoardVO;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class BoardInsertTest {

  @Test
  public void test() throws SQLException {
    BoardDAO dao = new BoardDAO();
    dao.addNew(BoardVO.builder().title("Test title").content("Test Content").build());
  }

  @Test
  public void testRandom() throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement("SELECT weeb.create_id();");
    @Cleanup ResultSet rs = stmt.executeQuery();
    if (rs.next()) {
      System.out.println(rs.getLong(1));
    }
  }

}
