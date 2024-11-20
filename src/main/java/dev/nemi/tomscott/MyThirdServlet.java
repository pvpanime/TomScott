package dev.nemi.tomscott;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet(name = "yay", value = "/my3")
public class MyThirdServlet extends HttpServlet {

    private String[] menus;

    @Override
    public void init() throws ServletException {
        this.menus = new String[]{ "참치마요", "핫크리스피세트", "돼지국밥", "로제", "아파트", "순살후라이드" };
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Random random = new Random();

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>MyThirdServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.printf("This shouldn't be happen...: %d", random.nextInt());
        out.println("<ul>");
        for (String menu : menus) {
            out.println("<li>" + menu + "</li>");
        }
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }
}
