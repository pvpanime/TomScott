package dev.nemi.tomscott.food.controller;

import dev.nemi.tomscott.food.FoodService;
import dev.nemi.tomscott.food.dto.FoodViewDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/food/get/*"})
public class FoodGetOneController extends HttpServlet {

  private FoodService foodService;

  @Override
  public void init() throws ServletException {
    foodService = new FoodService();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      String pathInfo = req.getPathInfo();
      if (pathInfo == null) {
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return;
      }

      String idString = pathInfo.substring(1);
      long id = Long.parseLong(idString);
      FoodViewDTO dto = foodService.getOne(id);
      if (dto == null) {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        return;
      }
      resp.setContentType("text/plain");
      PrintWriter out = resp.getWriter();
      out.write(dto.getId() + "\n");
      out.write(dto.getName() + "\n");
      out.write(dto.getPrice() + "\n");
      out.write(dto.getDescription() + "\n");
    } catch (NumberFormatException nex) {
      nex.printStackTrace();
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    } catch (SQLException sex) {
      sex.printStackTrace();
      if (sex.getErrorCode() == 1264) {
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
      } else {
        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      }
    }
  }
}
