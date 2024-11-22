package dev.nemi.tomscott.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Run DB connection test
 */
public class MariaTest {

  private static final String driverName = "org.mariadb.jdbc.Driver";
  private static final String url = "jdbc:mariadb://localhost:3306/weeb";
  private static final String user = "hina";
  private static final String password = "perfect";


  @Test
  public void connectionTest() {
    try {
      Class.forName(driverName);

      try (Connection conn = DriverManager.getConnection(url, user, password)) {
        Assertions.assertNotNull(conn);
      }

    } catch (ClassNotFoundException e) {
      Assertions.fail("Driver not found");
    } catch (SQLException e) {
      Assertions.fail("Connection failed");
    }
  }

  @Test
  public void scottTest() {

  }
}
