package org.example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private Map<String, String> accounts = new HashMap<>();
    private void initAccount(){
        accounts.put("admin", "123456");
        accounts.put("admin123", "123456");
        accounts.put("thanhlong", "888888");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        rd.forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy giá trị từ form đăng nhập
        initAccount();
        String user = req.getParameter("userName");
        String pass = req.getParameter("userPassword");
        resp.setContentType("text/html");
        if(accounts.containsKey(user)) {
            if (pass.equals(accounts.get(user))){
                RequestDispatcher rd = req.getRequestDispatcher("welcome.jsp");
                rd.forward(req,resp);
            }else {
                RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
                rd.forward(req,resp);
            }
        }else {
            RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
            rd.forward(req,resp);
        }
    }


}
