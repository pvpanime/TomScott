package dev.nemi.tomscott.board;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

  public static void set(BoardDTO boardDTO) throws IOException {
    String name = boardDTO.title;
    byte[] content = boardDTO.content.getBytes(StandardCharsets.UTF_8);
    Path filePath = boardDirectory.resolve(name + ".md");
    Files.write(filePath, content);
  }

  public static BoardDTO findByName(String name) {
    Path filePath = boardDirectory.resolve(name + ".md");
    try {
      String content = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
      return new BoardDTO(name, content);
    } catch (IOException e) {
      return null;
    }
  }

  public static BoardDTO findByURLName (String url) {
    try {
      if (url.startsWith("/")) url = url.substring(1);
      String name = URLDecoder.decode(url, "UTF-8");
      return findByName(name);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  public static boolean removeByTitle(String name) {
    Path filePath = boardDirectory.resolve(name + ".md");
    File file = filePath.toFile();
    return file.delete();
  }

  public static List<BoardDTO> list() throws IOException {
    try (Stream<Path> stream = Files.list(boardDirectory)) {
      return stream.map(p -> boardDirectory.relativize(p).toString().replaceAll("\\.md$", "")).map(BoardService::findByName).collect(Collectors.toList());
    }
  }

}
