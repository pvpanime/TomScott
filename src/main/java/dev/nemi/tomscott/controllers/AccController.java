package dev.nemi.tomscott.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "accController", urlPatterns = "/acc/input")
public class AccController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("From AccController.doGet()");

    /* 화면으로 전달을 해줄 것이다. */
    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/acc-input.jsp");
    dispatcher.forward(req, resp);
  }
}
