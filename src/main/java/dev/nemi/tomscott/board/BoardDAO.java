package dev.nemi.tomscott.board;


import dev.nemi.tomscott.TachibanaHikari;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BoardDAO {

  private static BoardDTO complete(ResultSet rs) {
    try {
      return new BoardDTO(
        rs.getInt("id"),
        rs.getString("title"),
        rs.getString("content"),
        rs.getTimestamp("addTime").toInstant(),
        rs.getTimestamp("lastMod").toInstant(),
        rs.getObject("userId", Integer.class)
      );
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void addNew(String title, String content) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("INSERT INTO Board(title, content) VALUES (?, ?)");
    ps.setString(1, title);
    ps.setString(2, content);
    ps.executeUpdate();
  }

  public void update(int id, String title, String content) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("UPDATE Board SET title = ?, content = ? WHERE id = ?");
    ps.setString(1, title);
    ps.setString(2, content);
    ps.setInt(3, id);
    ps.executeUpdate();
  }

  public List<BoardDTO> getAllBoards() throws SQLException {
    return TachibanaHikari.getAll("SELECT * FROM Board", BoardDAO::complete);
  }

  public BoardDTO getBoardById(int id) throws SQLException {
    return TachibanaHikari.getOne("SELECT * FROM Board WHERE id = ?", BoardDAO::complete, id);
  }

  public void remove(int id) throws SQLException {
    try (Connection conn = TachibanaHikari.getConnection()) {
      try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Board WHERE id = ?")) {
        ps.setInt(1, id);
        ps.executeUpdate();
      }
    }
  }
}
