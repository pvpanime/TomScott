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

@WebServlet(name = "BoardWriteController", urlPatterns = {"/board/write"})
public class BoardWriteController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");
    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/board/write.jsp");
    dispatcher.forward(req, resp);
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");
    try {
      BoardDTO boardDTO = new BoardDTO(req.getParameter("title"), req.getParameter("content"));
      BoardService.set(boardDTO);
      resp.sendRedirect("/board/read/" + boardDTO.getPath());
    } catch (IOException ioe) {
      resp.sendError(500);
    }
  }
}

