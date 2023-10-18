package org.example.Servlet;

import com.google.gson.Gson;
import org.example.Dao.ProductDAO;
import org.example.Models.Product;
import org.example.ResDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    private static final ProductDAO productDao;
    static {
        productDao = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("HomeServlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));

        Product product = new Product(name , price);
        productDao.add(product);
        resp.sendRedirect("HomeServlet");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("application/json");
        try {
            Product product = gson.fromJson(req.getReader(), Product.class);
            Product foundProduct = productDao.getById(product.getId());
            if(foundProduct != null){
                productDao.delete(foundProduct);
                ResDTO resDTO = new ResDTO(true, "Product is deleted");
                String json = gson.toJson(resDTO);
                printWriter.print(json);
                printWriter.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
            ResDTO resDTO = new ResDTO(false ,"Error");
            String json = gson.toJson(resDTO);
            printWriter.print(json);
            printWriter.flush();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        resp.setContentType("application/json");
        try {
            Product parameterProduct = gson.fromJson(req.getReader() , Product.class);
            Product foundProudct = productDao.getById(parameterProduct.getId());

            if(foundProudct != null){
                foundProudct.setName(parameterProduct.getName());
                foundProudct.setPrice(parameterProduct.getPrice());
                productDao.update(foundProudct);
                ResDTO resDTO = new ResDTO(true , "Ok");
                resp.getWriter().print(gson.toJson(resDTO));
            }
        }catch (Exception e){
            e.printStackTrace();
            ResDTO resDTO = new ResDTO(false , "not ok");
            resp.getWriter().print(gson.toJson(resDTO));
        }
    }
}
