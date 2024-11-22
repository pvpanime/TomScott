package dev.nemi.tomscott.board;

import lombok.Cleanup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

public class BoardService {
  public static final Path boardDirectory;

  static {
    String homeDir = System.getProperty("user.home");

    boardDirectory = Paths.get(homeDir, ".board");
    try {
      if (!Files.exists(boardDirectory)) Files.createDirectories(boardDirectory);
      else if (!Files.isDirectory(boardDirectory)) throw new RuntimeException(boardDirectory + " is not a directory");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void createNew(String title, String content) throws SQLException {
    new BoardDAO().addNew(title, content);
  }

  public static void update(int id, String title, String content) throws SQLException {
    new BoardDAO().update(id, title, content);
  }

  public static BoardDTO getByPathinfo(String pathinfo) throws NumberFormatException, SQLException {
    int id = Integer.parseInt(pathinfo.substring(1));
    return getById(id);
  }

  public static BoardDTO getById(int id) throws SQLException {
    return new BoardDAO().getBoardById(id);
  }

  public static void removeByPathinfo(String pathinfo) throws NumberFormatException, SQLException {
    int id = Integer.parseInt(pathinfo.substring(1));
    removeById(id);
  }

  public static void removeById(int id) throws SQLException {
    new BoardDAO().remove(id);
  }

  public static List<BoardDTO> list() throws SQLException {
    return new BoardDAO().getListAt();
  }

  public static List<BoardDTO> list(int offset, int count) throws SQLException {
    return new BoardDAO().getListAt(offset, count);
  }

}
