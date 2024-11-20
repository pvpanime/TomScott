package dev.nemi.tomscott;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SecondServlet", value = "/my2")
public class MySecondServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet MySecondServlet</title>");
        out.println("</head>");
        out.println("<body>");
        for (int i = 0 ; i < 100 ; i += 1) {
            out.println("<p>" + i + "</p>");
        }
        out.println("</body>");
        out.println("</html>");

    }
}
