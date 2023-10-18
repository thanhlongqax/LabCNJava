package org.example.Servlet;

import org.example.Dao.*;
import org.example.Models.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@WebServlet(value = "/HomeServlet")
public class HomeServlet extends HttpServlet {
    private static final ProductDAO productDAO;
    static {
        productDAO = new ProductDAO();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productDAO.getAllProduct();
        req.setAttribute("products", products);
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
        rd.forward(req,resp);
        Arrays.stream(IntStream.range(0, 10).toArray()).forEach(i -> {
        });
    }
}
