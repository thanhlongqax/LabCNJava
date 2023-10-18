package org.example.Servlet;

import org.example.Dao.*;
import org.example.Models.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final UserDAO dao;
    static {
        dao = new UserDAO();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // Kiểm tra mật khẩu phải có ít nhất 16 ký tự và email phải kết thúc bằng "@gmail.com"
        if (password.length() < 8) {
            req.setAttribute("message", "Password must have at least 8 characters.");
            req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
        } else if (!email.endsWith("@gmail.com")) {
            req.setAttribute("message", "Email must end with '@gmail.com'.");
            req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
        } else {
            User user = new User();
            user.setUserfullname(name);
            user.setEmail(email);
            user.setPassword(password);

            // Kiểm tra xem email đã được sử dụng chưa
            if (dao.findByEmail(email) == null) {
                dao.add(user);
                resp.sendRedirect("/LoginServlet");
            } else {
                req.setAttribute("message", "Email already in use");
                req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
            }
        }
    }
}
