package org.example.Servlet;
import com.google.gson.Gson;
import org.example.DTO.MessDTO;
import org.example.Model.Product;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.net.URLEncoder;
import java.net.URLDecoder;

@WebServlet(value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    private Gson gson = new Gson();
    List <Product> products = new ArrayList<Product>();

    @Override
    public void init() throws ServletException {
        products.add(new Product(0, "Iphone 11 ", 549));
        products.add(new Product(1, "Iphone 11 pro", 512));
        products.add(new Product(3, "Iphone 13 Pro", 999));
        products.add(new Product(2, "Samsung galaxy", 1234));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final MessDTO messDTO = new MessDTO();
        messDTO.setCode(0);
        messDTO.setMessage("Đọc Sản Phẩm Thành Công ");
        final String idProduct = req.getParameter("id");
        if(idProduct == null){
            messDTO.setData(products);
        }else {
            Product product = products.stream().filter(pro ->{
                return pro.getId() == Integer.parseInt(idProduct);
            }).findFirst().orElseGet(() ->{
                messDTO.setMessage("Không tìm thấy sản phẩm nào với mã số " + idProduct);
                return null;
            });
            messDTO.setData(product);
        }
        String fileJson = gson.toJson(messDTO);
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        printWriter.print(fileJson);
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = gson.fromJson(req.getReader() , Product.class);
        products.add(product);
        MessDTO messDTO = new MessDTO(0,"Add products sucessfully",products);
        String fileJson = gson.toJson(messDTO);
        PrintWriter printWriter = resp.getWriter();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        printWriter.print(fileJson);
        printWriter.flush();

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = gson.fromJson(req.getReader() , Product.class);
        MessDTO messDTO = new MessDTO(0 , "Update success products" , null);
        Product foundProduct = products.stream().filter(
                pro ->{
                    return pro.getId() == product.getId() ;
                }
        ).findFirst().orElseGet(() ->{
            return null;
        });
        if (foundProduct == null){
            messDTO.setMessage("Update fail products because id is not found" + product.getId());
            messDTO.setCode(1);
            messDTO.setData("");
        }else {
            int index = IntStream.range(0, products.size()).filter( pro -> {
                return products.get(pro).equals(foundProduct);
            }).findFirst().orElse(-1);
            products.set(index , product);
            messDTO.setData(products);
        }
        resp.setContentType("application/json");
        PrintWriter pw = resp.getWriter();
        pw.print(gson.toJson(messDTO));
        pw.flush();

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = gson.fromJson(req.getReader(), Product.class);
        MessDTO messDTO = new MessDTO(0, "Delete product successfully", null);
        Product foundProduct = products.stream().filter( pro -> {
            return product.getId() == pro.getId();
        }).findFirst().orElseGet(() -> {
            return null;
        });
        if(foundProduct == null){
            messDTO.setCode(1);
            messDTO.setMessage("Delete products failed");
            messDTO.setData("");
        }else{
            products.remove(foundProduct);
            messDTO.setData(products);
        }
        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(gson.toJson(messDTO));
        printWriter.flush();
    }
}
