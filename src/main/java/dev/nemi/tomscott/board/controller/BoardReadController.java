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
import java.sql.SQLException;

@WebServlet(name = "BoardReadController", urlPatterns = {"/board/read/*"})
public class BoardReadController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");

    BoardDTO dto;
    try {
      dto = BoardService.getByPathinfo(req.getPathInfo());
      if (dto == null) {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
      } else {
        req.setAttribute("board", dto);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/board/read.jsp");
        requestDispatcher.forward(req, resp);
      }
    } catch (NumberFormatException e) {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    } catch (SQLException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

  }
}
