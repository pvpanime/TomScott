package dev.nemi.tomscott;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "I make servlet", value = "/9")
public class MyServlet extends HttpServlet {
    @Override
    public void init()  {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet MyServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet MyServlet</h1>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet MyServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>HOW DARE YOU POST TO ME?!?!?!?!?! YOU'RE GONNA DIE!!!!!!</h1>");
        out.println("</body>");
        out.println("</html>");
        
    }

    @Override
    public void destroy() {}
}
