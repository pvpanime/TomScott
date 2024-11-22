package dev.nemi.tomscott;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "searchHandler", value = "/s")
public class UseSearch extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
//        String search = request.getParameter("search");
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<table><tbody>");
        for (String parameterName : request.getParameterMap().keySet()) {
            String value = request.getParameter(parameterName);
            out.print("<tr>");
            out.print("<th>" + parameterName + "</th>");
            out.print("<td>" + value + "</td>");
            out.print("</tr>");
            out.println();
        }
        out.println("</tbody></table>");
        out.println("</body>");
    }
}
