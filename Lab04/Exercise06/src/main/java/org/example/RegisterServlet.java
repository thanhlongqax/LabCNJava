package org.example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/register.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String birthDay = req.getParameter("birthDay");
        String birthTime = req.getParameter("birthTime");
        String gender = req.getParameter("gender");
        String country = req.getParameter("country");
        String toeic = req.getParameter("toeic");
        String message = req.getParameter("message");
        String[] favouriteIde = req.getParameterValues("favorite_ide[]");

        if(email.isEmpty() || birthDay.isEmpty() || birthTime.isEmpty() ||
                gender == null || country.equals("Select a country") || favouriteIde == null
                || message.isEmpty()){
            req.setAttribute("status", false);
        }else
            req.setAttribute("status", true);

        req.setAttribute("name", name);
        req.setAttribute("email", email);
        req.setAttribute("birthDay", birthDay);
        req.setAttribute("birthTime", birthTime);
        req.setAttribute("gender", gender);
        req.setAttribute("country", country);
        req.setAttribute("toeic", toeic);
        req.setAttribute("message", message);
        req.setAttribute("favouriteIde", favouriteIde);
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF8");
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/result.jsp");
        rd.forward(req, resp);
    }
}
