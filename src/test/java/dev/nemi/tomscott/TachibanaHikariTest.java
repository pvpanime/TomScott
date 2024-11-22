package dev.nemi.tomscott;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class TachibanaHikariTest {

  @Test
  public void go() throws SQLException {
    Connection conn = TachibanaHikari.getConnection();
    Assertions.assertNotNull(conn);
    conn.close();
  }

}
