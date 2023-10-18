package org.example.Servlet;
import org.example.Dao.*;
import org.example.Models.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
@WebServlet(value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final UserDAO dao;
    static{
        dao = new UserDAO();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // Kiểm tra mật khẩu phải có ít nhất 16 ký tự và email phải kết thúc bằng "@gmail.com"
        if (password.length() < 8) {
            req.setAttribute("message", "Password must have at least 8 characters.");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        } else {
            // Thực hiện xác thực người dùng
            User foundUser = dao.findByEmail(username); //tra ve nguoi dung hoac null
            if (foundUser != null) {
                if (username.equals(foundUser.getEmail()) && password.equals(foundUser.getPassword())) {
                    Base64.Encoder encoder = Base64.getEncoder();
                    String encodedUsername = encoder.encodeToString(foundUser.getUserfullname().getBytes());
                    Cookie cookie = new Cookie("username", encodedUsername);
                    cookie.setMaxAge(2592000);
                    resp.addCookie(cookie);
                    String value = "";
                    value = Arrays.stream(req.getCookies())
                            .filter(c -> c.getName().equals("username"))
                            .map(Cookie::getValue).findFirst().orElse(null);
                    req.setAttribute("userFullname", foundUser.getUserfullname());
                    resp.sendRedirect("/HomeServlet");
                } else {
                    req.setAttribute("message", "Incorrect username or password.");
                    req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("message", "User not found. Hay dang ki tai khoan moi");
                req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
            }
        }
    }
}

