package dev.nemi.tomscott.food;

import dev.nemi.tomscott.TachibanaHikari;
import lombok.Cleanup;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {
  public List<FoodVO> getAll() throws SQLException {
    List<FoodVO> list = new ArrayList<>();
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("SELECT * FROM Food WHERE status > 0;");
    @Cleanup ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      list.add(FoodVO.builder()
        .id(rs.getLong("id"))
        .name(rs.getString("name").trim())
        .description(rs.getString("description").trim())
        .price(rs.getLong("wonPrice"))
        .build());
    }
    return list;
  }

  public FoodVO getById(long id) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("SELECT * FROM Food WHERE id = ? AND status > 0;");
    ps.setLong(1, id);
    @Cleanup ResultSet rs = ps.executeQuery();
    if (rs.next()) {
      return FoodVO.builder()
        .id(rs.getLong("id"))
        .name(rs.getString("name").trim())
        .description(rs.getString("description").trim())
        .price(rs.getLong("wonPrice"))
        .build();
    }
    return null;
  }

  public void add(@NotNull FoodVO food) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("INSERT INTO Food (name, description, wonPrice) VALUES (?, ?, ?)");
    ps.setString(1, food.getName());
    ps.setString(2, food.getDescription());
    ps.setLong(3, food.getPrice());
    ps.executeUpdate();
  }

  public void update(@NotNull FoodVO food) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("UPDATE Food SET name = ?, description = ?, wonPrice = ? WHERE id = ?");
    ps.setString(1, food.getName());
    ps.setString(2, food.getDescription());
    ps.setLong(3, food.getPrice());
    ps.setLong(4, food.getId());
    ps.executeUpdate();
  }

  public int delete(long id) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("DELETE FROM Food WHERE id = ?");
    ps.setLong(1, id);
    int rows = ps.executeUpdate();
    return rows;
  }
}
