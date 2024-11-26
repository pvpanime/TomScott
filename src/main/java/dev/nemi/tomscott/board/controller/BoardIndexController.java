package dev.nemi.tomscott.board.controller;

import dev.nemi.tomscott.board.BoardService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "BoardIndexController", urlPatterns = {"/board"})
public class BoardIndexController extends HttpServlet {

  private BoardService boardService;

  @Override
  public void init() throws ServletException {
    super.init();
    boardService = new BoardService();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");

    try {
      req.setAttribute("list", boardService.list());
      RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/board/index.jsp");
      rd.forward(req, resp);
    } catch (SQLException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }


  }
}
