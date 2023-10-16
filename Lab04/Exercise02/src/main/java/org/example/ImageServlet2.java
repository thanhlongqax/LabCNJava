package org.example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.*;

@WebServlet("/image2")
public class ImageServlet2 extends HttpServlet {
    private final int jpg_SIZE = 1048;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setHeader("Content-disposition" , "attachment; filename = Long.png");
        try {
            InputStream in = req.getServletContext().getResourceAsStream("/files/Long.jpg");
            OutputStream out = resp.getOutputStream();

            byte[] writer = new byte[jpg_SIZE];
            int numBytesRead;
            while ((numBytesRead = in.read(writer)) >0){
                out.write(writer , 0 ,numBytesRead);
            }
        }catch (Exception e){
            e.getMessage();
        }

    }
}
