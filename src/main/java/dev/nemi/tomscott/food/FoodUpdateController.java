package dev.nemi.tomscott.food;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/food/update"})
public class FoodUpdateController extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");
    try {
      String id = req.getParameter("id");
      String name = req.getParameter("name");
      String description = req.getParameter("description");
      new FoodDAO().update(
        FoodVO.builder()
          .id(Long.parseLong(id))
          .name(name)
          .description(description)
          .build());
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
