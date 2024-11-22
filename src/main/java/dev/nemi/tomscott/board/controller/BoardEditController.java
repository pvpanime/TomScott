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

@WebServlet(name = "BoardEditController", urlPatterns = {"/board/edit/*"})
public class BoardEditController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");

    BoardDTO dto = BoardService.findByURLName(req.getPathInfo());
    if (dto == null) {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
      return;
    }
    req.setAttribute("board", dto);

    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/board/edit.jsp");
    dispatcher.forward(req, resp);

  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");
    BoardDTO boardDTO = new BoardDTO(req.getParameter("title"), req.getParameter("content"));
    resp.sendRedirect("/board/read/" + boardDTO.getPath());
  }
}

