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
    @Cleanup PreparedStatement ps = conn.prepareStatement("SELECT * FROM Food");
    @Cleanup ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      list.add(FoodVO.builder()
        .id(rs.getLong("id"))
        .name(rs.getString("name").trim())
        .description(rs.getString("description").trim())
        .build());
    }
    return list;
  }

  public FoodVO getById(long id) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("SELECT * FROM Food WHERE id = ?");
    ps.setLong(1, id);
    @Cleanup ResultSet rs = ps.executeQuery();
    if (rs.next()) {
      return FoodVO.builder()
        .id(rs.getLong("id"))
        .name(rs.getString("name").trim())
        .description(rs.getString("description").trim())
        .build();
    }
    return null;
  }

  public void add(@NotNull FoodVO food) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("INSERT INTO Food (name, description) VALUES (?, ?)");
    ps.setString(1, food.getName());
    ps.setString(2, food.getDescription());
    ps.executeUpdate();
  }

  public void update(@NotNull FoodVO food) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("UPDATE Food SET name = ?, description = ? WHERE id = ?");
    ps.setString(1, food.getName());
    ps.setString(2, food.getDescription());
    ps.setLong(3, food.getId());
    ps.executeUpdate();
  }

  public void update(@NotNull FoodNameUpdateDTO foodNameUpdateDTO) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("UPDATE Food SET name = ? WHERE id = ?");
    ps.setString(1, foodNameUpdateDTO.getName());
    ps.setLong(2, foodNameUpdateDTO.getId());
    ps.executeUpdate();
  }

  public void update(@NotNull FoodDescriptionDTO foodDescriptionDTO) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("UPDATE Food SET description = ? WHERE id = ?");
    ps.setString(1, foodDescriptionDTO.getDescription());
    ps.setLong(2, foodDescriptionDTO.getId());
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
