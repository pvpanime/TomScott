package dev.nemi.tomscott;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "useJson", value = "/json")
public class UseJSON extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println("{");
        out.println("\"name\": \"Tom Scott\",");
        out.println("\"age\": 35,");
        out.println("\"gender\": \"male\"");
        out.println("}");
    }

    @Override
    public void destroy() {
        
    }
}
