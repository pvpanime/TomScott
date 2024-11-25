package dev.nemi.tomscott.food;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/food"})
public class FoodController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");

    try {
      req.setAttribute("foods", new FoodDAO().getAll());
      RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/food/food-list.jsp");
      dispatcher.forward(req, resp);
    } catch (SQLException e) {
      e.printStackTrace();
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");

    try {
      String name = req.getParameter("name");
      String description = req.getParameter("description");
      new FoodDAO().add(FoodVO.builder().name(name).description(description).build());
      resp.setStatus(HttpServletResponse.SC_OK);
    } catch (SQLException e) {
      e.printStackTrace();
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");

    resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
  }
}
