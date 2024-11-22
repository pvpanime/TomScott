package dev.nemi.tomscott.tasker;

import dev.nemi.tomscott.TachibanaHikari;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskerDAO {
  public void add(TaskerVO task) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement("INSERT INTO Task(title, content) VALUES(?, ?)");
    stmt.setString(1, task.getTitle());
    stmt.setString(2, task.getContent());
    stmt.executeUpdate();
  }

  public TaskerDTO get(int id) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Task WHERE id = ?");
    stmt.setInt(1, id);
    @Cleanup ResultSet rs = stmt.executeQuery();
    if (rs.next()) {
      return new TaskerDTO(
        rs.getInt("id"),
        rs.getString("title"),
        rs.getString("content"),
        rs.getTimestamp("start").toInstant(),
        rs.getTimestamp("end").toInstant(),
        rs.getInt("finished")
      );
    }
    return null;
  }

  public List<TaskerDTO> list() throws SQLException {
    List<TaskerDTO> list = new ArrayList<>();
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Task ORDER BY end DESC");
    @Cleanup ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      list.add(new TaskerDTO(
        rs.getInt("id"),
        rs.getString("title"),
        rs.getString("content"),
        rs.getTimestamp("start").toInstant(),
        rs.getTimestamp("end").toInstant(),
        rs.getInt("finished")
      ));
    }
    return list;
  }

  public void setFinished(TaskerVO task) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement("UPDATE Task SET finished = ? WHERE id = ?");
    stmt.setInt(1, task.getFinished());
    stmt.setInt(2, task.getId());
    stmt.executeUpdate();
  }

  public void later(int id) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement("UPDATE Task SET end = DATE_ADD(end, interval 1 day) WHERE id = ?");
    stmt.setInt(1, id);
    stmt.executeUpdate();
  }


}
