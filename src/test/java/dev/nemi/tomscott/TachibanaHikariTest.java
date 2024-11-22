package dev.nemi.tomscott;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class TachibanaHikariTest {
  HikariConfig config = new HikariConfig();
  HikariDataSource ds;

  @Test
  public void go() throws SQLException {
    config.setDriverClassName(TachibanaHikari.driverName);
    config.setJdbcUrl(TachibanaHikari.url);
    config.setUsername(TachibanaHikari.user);
    config.setPassword(TachibanaHikari.password);
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

    ds = new HikariDataSource(config);
    Connection conn = ds.getConnection();
    Assertions.assertNotNull(conn);
    conn.close();
  }

}
