package dev.nemi.tomscott.board;

import org.apache.commons.text.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class BoardDTO {
  public final String title;
  public final String content;

  public BoardDTO(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public String getPath() {
    try {
      return URLEncoder.encode(title, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      // this is supported encoding, so there's no way to get here.
      throw new RuntimeException(e);
    }
  }

  public String getContentHTML() {
    return StringEscapeUtils.escapeHtml4(content);
  }

  @Override
  public String toString() {
    return String.format("Board<title=%s, content=%s>", title, content);
  }

}
