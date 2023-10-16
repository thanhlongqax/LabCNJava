package org.example;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(value = "/DowloadServlet")
public class DownloadServlet extends HttpServlet {
    private final int ARBITARY_SIZE = 1048;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameterMap().containsKey("file") && !req.getParameter("file").isEmpty()) {
            String fileName = req.getParameter("file");
            ServletContext context = getServletContext();
            File file = new File(context.getRealPath("/WEB-INF/uploads/" + fileName));
            if (file.exists()) {
                resp.setContentType("text/plain");
                resp.setHeader("Content-disposition", "attachment; filename=" + fileName);

                try (
                        InputStream in = req.getServletContext().getResourceAsStream("/WEB-INF/uploads/" + fileName);
                        OutputStream out = resp.getOutputStream()
                ) {
                    byte[] buffer = new byte[ARBITARY_SIZE];

                    int numBytesRead;
                    while ((numBytesRead = in.read(buffer)) > 0) {
                        out.write(buffer, 0, numBytesRead);
                    }
                }
            } else {
                resp.setContentType("text/html");
                PrintWriter pw = resp.getWriter();
                pw.println("File not found");
            }
        }else{
            resp.setContentType("text/html");
            PrintWriter pw = resp.getWriter();
            pw.println("File not found");
        }
    }
}
