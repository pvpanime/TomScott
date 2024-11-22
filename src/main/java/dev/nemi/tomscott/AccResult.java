package dev.nemi.tomscott;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "accResult", value = "/acc/result0")
public class AccResult extends HttpServlet {
    protected void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        try {
            String op1 = req.getParameter("op1");
            String op2 = req.getParameter("op2");
            String op = req.getParameter("operator");
            int v1 = Integer.parseInt(op1);
            int v2 = Integer.parseInt(op2);
            int result;
            switch (op) {
                case "add": result = v1 + v2; break;
                case "sub": result = v1 - v2; break;
                case "mul": result = v1 * v2; break;
                case "div": result = v1 / v2; break;
                default: throw new RuntimeException("Unknown operator: " + op);
            }
            PrintWriter out = resp.getWriter();
            out.println(result);
        } catch (RuntimeException e) {
            resp.setStatus(400);
            PrintWriter out = resp.getWriter();
            out.println(e.getClass().getName());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }
}
