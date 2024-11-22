package dev.nemi.tomscott.board.controller;

import dev.nemi.tomscott.board.BoardService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BoardIndexController", urlPatterns = {"/board"})
public class BoardIndexController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");

    req.setAttribute("list", BoardService.list());

    RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/board/index.jsp");
    rd.forward(req, resp);
  }
}
