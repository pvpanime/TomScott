package dev.nemi.tomscott.board.controller;

import dev.nemi.tomscott.board.BoardDTO;
import dev.nemi.tomscott.board.BoardService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BoardDeleteController", urlPatterns = {"/board/delete/*"})
public class BoardDeleteController extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");

    BoardDTO dto = BoardService.findByURLName(req.getPathInfo());
    if (dto == null) {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    } else {
      BoardService.removeByTitle(dto.title);
      RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/board/delete.jsp");
      dispatcher.forward(req, resp);
    }
  }
}
