package dev.nemi.tomscott.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginResultController", urlPatterns = "/doLogin")
public class LoginResultController extends HttpServlet {
  private static final String usernameP = "idea@example.com";
  private static final String passwordP = "YOUNEVERKNOWTHISISEVENAPASSWORD";

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("email");
    String password = req.getParameter("password");
    String message = username.equals(usernameP) && password.equals(passwordP) ? "Welcome, user!!" : "HOW DARE YOU COME TO INVADE TO MY STRONGHOLD?! GO F!@#$%^&*()-=K YOURSELF!!!!!";
    req.setAttribute("message", message);
    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/login-result.jsp");
    dispatcher.forward(req, resp);
  }
}
