package dev.nemi.tomscott.food.controller;

import dev.nemi.tomscott.food.FoodService;
import dev.nemi.tomscott.food.dto.FoodAddDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/food/insert"})
public class FoodInsertController extends HttpServlet {

  private FoodService service;

  @Override
  public void init() throws ServletException {
    service = new FoodService();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//    req.setCharacterEncoding("UTF-8");
//    resp.setCharacterEncoding("UTF-8");
    try {
      String name = req.getParameter("name");
      String description = req.getParameter("description");
      String priceString = req.getParameter("price");
      long price = Long.parseLong(priceString);
      service.add(FoodAddDTO.builder().name(name).description(description).price(price).build());
      PrintWriter out = resp.getWriter();
      resp.setStatus(HttpServletResponse.SC_OK);
      out.println("200 OK");
    } catch (NumberFormatException ne) {
      ne.printStackTrace();
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    } catch (SQLException e) {
      e.printStackTrace();
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}
