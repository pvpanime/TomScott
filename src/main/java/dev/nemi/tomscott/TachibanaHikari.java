package dev.nemi.tomscott;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**

 ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▓▓▓▓▓█▓▓▓▓▓█▓▓▓▓▓▒░░░░░░░░░░░░░░███████████████████████████▓▓▓
 ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓▓▓▓▓█▓▓▓▓▓▓▓▓▓▓▒░░░░░░░░░░░░░░▓█████████████████████████▓▓▓▓
 ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒░░░░░░░░░░░░░░░███████████████████████▓▓▓▓▓▓
 ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒░░░░░░░░░░░░░░░░░█████████████████████▓▓▓▓▓▓▓
 ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▓▓▓▓▓▓▓▓▓▓▒░▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░▓██████████████████▓▓▓▓▓▓▓▓█
 ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░█████████████████▓▓▓▓▓▓▓▓██
 ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓█████████▓▒▒▒▒▓███████████▓▓▓█▓▓▓▓▓▓▓▓▓██
 ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓███████▓▓▒▒▒░░░░░░░░░░░░░▒█████████▓▓▓▓▓▓▓▓▓▓▓▓▓███
 ░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▓▒▒▒▒▒▒▓▓▓██████▓▓▒▒░░░░░░░░░░░░░░░░░░░░░░░▓███████▓▓▓▓▓▓▓▓▓▓▓▓▓████
 ░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▓▓▓▓▓▓▒▓▓▓█████▓▓▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▓███▓▓▓▓▓▓▓▓▓▓▓▓▓█████
 ░░░░░░░░░░░░░░░░░░░▒▒▒▓▓▓▓▓▓▓▓█████▓▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓█████
 ░░░░░░░░░░░░░░░░▒▒▓▓▓▓▓▓▓████▓▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▓▓▓▓▓▓▓▓▓▓██████
 ░░░░░░░░░░░░▒▒▒▒▒▒▒▓▓███▓▒░░░░░░░░░░░░░░░░░░▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓▓▓▓▓▓▓▓███████
 ░░░░░░░░░▒▒▒▓▓▒▓▓███▓▒░░░░░░░░░░░░░░░░░░░░▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓▓▓▓▓▓▓▓████████
 ░░░░░░▒▒▒▒▓▒▓▓██▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▓▓▓▓▓▓▓██████████
 ░░░▒▒▒▒▓▒▓▓██▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▓▓▓▓▓███████████
 █▓░░▒▒▓█████░░▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓███████▓███
 ███▓▒▓███▓▓▒▒░░░▒▓▓▓▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒░▒▒▒▒▒▒▒▓▒▒▓▓▓▓▓▓▓▒▒▒▒▒▓▓▓███████▓▓██
 ██████▓▒▒▒▒▒▒▓████████▓███▓▓▓▓▓▓▒▒▓▓▓▓▒▒▒▒▒▒▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▓▓▓▓▓█▓▓█▓█████▒▒▒▒▒▒▓▓███████████
 ███████▓▒▒▓███████████▓█████▓███▓▓█████▓████▓▓▓▓██████████████▓▒▓█████▓██▓███████▓▒▒▒▓▓▒▓███████████
 █████████▓████████████▓█████▓███▒▓█████▒▓▓▓▓▒▓▓▓███████████████▒█████▓▓███▓██████▓▒▒▓▓██▒▓▓▓███████▓
 ██████████▓███████████▓█████▓███▒█████▓▓███▓▓▓█▓██▓███████████▓▓████▓▓████▓██████▓▒▒▒███▓▒▓▓██████▓▓
 ██████████▓███████████▓█████▓███▒████▓▒███▓▓███▓██▓██████████▓▒▓█▓▓▒░▒░▓████▓████▓▓▒▓███▓░▒▓▓▓▓▓▓▓▓█
 ██████████▓███████████▓█████████▒████▓▓██▓▓████▓██▓█████████▓▓▒▓▒▒░░░░░░░░▓▓████▓█▒▓███▓░░░░░░░░▒▓██
 █████▓████▓███████████▓█████████▓███▓▓██▓▓▓████▓█▓▓████████▓▓▓▒▓▓▓▓▓▓░▓▓▒░░░▓▓▓▓█▓████▓░░░░░░░░░░░▓█
 ███████▓██▓███████████▓█████████▓██▒▒▒▒░░░░▒▒███▓▓██████▓▓▓█▓▓▒▓▒░▒▓▓▓▒███░▒▒▒▒▓██████░░░░░░░░░░░░░░
 ████████▓▓████████████▓█████████▓▒░░░▒▒▒░░▓▓▓▒▓▓▓▓█████▓▓█████▓▓▒░▒▓▓▓▒███▒▒▒▒▒▓█████░░░░░░░░░░░░░░░
 █████████▒████████████▓████████▓░░░▒▓▓▓▓▓▒▒███▓▓▓█████████████████████▓█████▒▒▒▒▓▓██▒░░░░░░░░░░░░░░░
 ████████▓▓████████████▓███████▓▒░░▓▓▓░░▒▓▓▒▓█████████████████████████▓██████▓▒▒▒▓▓▓▓▓░░░░░░░░░░░░░░░
 ████████▒█████████████▓██████▓▒▒▒▓▒▓▓▒▒▒██▓▓████████████████████████████████▓▒▒▒▒▓████▓░░░░░░░░░░░░░
 ███████▓▓█████████████▓█████▓▒▒▒▓█▓███████▓██████████████████████████████████▒▒▒▓███████▓░░░░░░░░░░░
 ███████▓▓█████████████▓▓███████▓▒▓██▓████▓▓██████████████████████████████████▒▒▒▒█████████▓░░░░░░░░░
 ▓▓█████▓███████▓███▓███▓████████▓████████████████████████████████████████████▒▒▒▒▒██████████▓░░░░░░░
 ▓▓░░▓██████████▓▓▓██▓▓▓▓████████▓████████████████████████████████████████████▒▒▒▒▒▒███████████▓▓▓▓▓▓
 ▒░░░░▒█████████████████▓████████▓███████████████████████████████████████████▓▒▒▒▒▒▒▓████████████████
 ░░░░░░░████▓████████████████████▓██████████████████████████████████████████▓▒▒▒▒▒▓▒▒▓█████████████▓▒
 ░░░░░░░░█████▓▓███████▓▓▒████████████████████████████████████████████████▓▒▒▒▒▒▒▒▓▓▒▒▒███████▓▒░░░░░
 ░░░░░░░░░████▓████████▒░░████████▓█████████████████████████████████████▓▒▒▒▒▒▒▒▓▓▓█▒▒▒▒▓█▓▒░░░░░░░░░
 ░░░░░░░░░░██▓████████▓░░░▓███████▒▒▓▓████████████████████████████████▓▒▒▒░░░░▒▒▓▓██▓░░░░░░░░░░░░░░░░
 ░░░░░░░░░░▒▓▓████████▒▒▒▒▒███████▓░░░░▒███████████████████████████▓░░░░░░░░░░░▒▓████▒░░░░░░░░░░░░░░░
 ░░░░░░░░░▒▓▓██████▓█▒▒▒▓▒▒████████░░░░░▓▓▓▓▓▓▓█████████████████▓▒░░░░░░░░░░░░░░▓█████░░░░░░░░░░░░░░░
 ░░░░░░░░▓█▓██████▓█▒▒▒▒▒░░▓███████░░░░▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒░░░░░░░░░░░░░░░░░░▒█████▒░░░░░░░░░░░░░░
 ░░░░░░▓█▓▓██████▓█▒▒▒▒▒▒▒▒▒███████▒░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▒▒░░░░░░░░▒░░░░░░░░░░░░░░██████░░░░░░░░░░░░░░
 ░░░░▓██▓▓▓█████▓▓▒▒▒▒▒▒▒▒▒▒▓██████▓░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░▒▒▒░░░░░░░░░░░░░░░░░▓█████▓░░░░░░░░░░░░░
 ░░▒▓▓▓▓▒▒▓▓▒▒▓▒▒░░░░░░▒▒░░░░███████░░░░░░░░░░░░░░░░░░░░░░░▒▓██▓░░░░░░░░░░░░░░░░░░██████▓░░░░░░░░░░░░
 ░▒▒▒▒░▒▓▒▒▒░░░░░░░░░░░░░░░░░▒██████▒░░░░░░░░░░░░░░░░░░░░░░▒▒▒░░░░░░░░░░░░░░░░░░░░▓██████▒░░░░░░░░░░░
 ░▒▒▒▒▒░▒▓░▒▒░░░░░░░░░░░░░░░░░██████▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░███████▒░░░░░░░░░░
 ░░░░▓░░░▒░▒▒░░░░░░░░░░░▒░░░░░▒██████▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒███████░░░░░░░░░░
 ▒░░░▒▒░░▒▓▓▒▒▒▒▒▒▒░░░░░▓░░░░░░▓████▓█░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒░░░░░░░░░░░░░░░░▓██████▓░░░░░░░░░
 ▓▒▒▒▓█▒▒▒█▒▒▒▒▒▒▒░░░░░░▓░░░░░░░████▓█▓░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒░░░░░░░░░░░░░░░░░░░░███████▓░░░░░░░░



 */

public final class TachibanaHikari {
  public static final String driverName = "org.mariadb.jdbc.Driver";
  public static final String url = "jdbc:mariadb://localhost:3306/weeb";
  public static final String user = "hina";
  public static final String password = "perfect";

  private static final HikariDataSource dataSource;

  static {
    HikariConfig config = new HikariConfig();
    config.setDriverClassName(TachibanaHikari.driverName);
    config.setJdbcUrl(TachibanaHikari.url);
    config.setUsername(TachibanaHikari.user);
    config.setPassword(TachibanaHikari.password);
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    dataSource = new HikariDataSource(config);
  }

  public static Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }


}




















