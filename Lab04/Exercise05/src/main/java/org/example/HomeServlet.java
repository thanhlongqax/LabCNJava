package org.example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        resp.setContentType("text/html");
        String fullPath = getServletContext().getRealPath("WEB-INF/jsp/" +page + ".jsp");
        File file = new File(fullPath);
        if(page == null || !file.exists()){
            PrintWriter printWriter = resp.getWriter();
            printWriter.print("<h1>Welcome to our website</h1>");
            printWriter.flush();
            return;
        }
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/"+page+".jsp");
        rd.forward(req,resp);

    }
}
