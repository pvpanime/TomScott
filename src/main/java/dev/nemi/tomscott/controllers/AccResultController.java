package dev.nemi.tomscott.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;


@WebServlet(name = "accResultController", value = "/acc/result")
public class AccResultController extends HttpServlet {

  private Map<String, BiFunction<BigInteger, BigInteger, BigInteger>> ops = new HashMap<>();

  @Override
  public void init() throws ServletException {
    ops.put("add", BigInteger::add);
    ops.put("sub", BigInteger::subtract);
    ops.put("mul", BigInteger::multiply);
    ops.put("div", BigInteger::divide);
    ops.put("mod", BigInteger::mod);
    ops.put("pow", (a, b) -> a.pow(b.intValue()));
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    BigInteger op1 = new BigInteger(req.getParameter("op1"));
    BigInteger op2 = new BigInteger(req.getParameter("op2"));
    BiFunction<BigInteger, BigInteger, BigInteger> op = ops.get(req.getParameter("op"));
    BigInteger result = op != null ? op.apply(op1, op2) : null;

    req.setAttribute("result", result);

    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/acc-result.jsp");
    requestDispatcher.forward(req, resp);
  }
}
