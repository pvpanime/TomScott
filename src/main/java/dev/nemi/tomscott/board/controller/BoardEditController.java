package dev.nemi.tomscott.board.controller;

import dev.nemi.tomscott.board.dto.BoardViewDTO;
import dev.nemi.tomscott.board.BoardService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "BoardEditController", urlPatterns = {"/board/edit/*"})
public class BoardEditController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");

    BoardViewDTO dto = null;
    try {
      dto = BoardService.getByPathinfo(req.getPathInfo());
      if (dto == null) {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        return;
      }
      req.setAttribute("board", dto);

      RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/board/edit.jsp");
      dispatcher.forward(req, resp);
    } catch (NumberFormatException n) {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    } catch (SQLException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");
    try {
      int id = Integer.parseInt(req.getParameter("id"));
      BoardViewDTO board = BoardService.getById(id);
      BoardService.update(id, req.getParameter("title"), req.getParameter("content"));
      resp.sendRedirect("/board/read/" + board.getId());
    } catch (NumberFormatException n) {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    } catch (SQLException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }


}

