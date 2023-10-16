package org.example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@WebServlet(value = "/download")
public class DownloadServlet extends HttpServlet {
    private final int ARBITARY_SIZE = 1048;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameterMap().containsKey("file") && !req.getParameter("file").isEmpty()){
            String fileName = req.getParameter("file");
            String limit = req.getParameter("speed");
            ServletContext context = getServletContext();
            File file = new File(context.getRealPath("/files/"  + fileName));

            if(file.exists()){
                resp.setContentType("text/plain");
                resp.setHeader("Content-disposition" ,"attatchment; filename = " + fileName );
                int speedLimit = parseSpeedLimit(limit);
                try {
                    InputStream in = req.getServletContext().getResourceAsStream("/files/" + fileName);
                    OutputStream out = resp.getOutputStream();

                    byte[] bytes = new byte[ARBITARY_SIZE];
                    long startTime = System.currentTimeMillis();
                    int numBytesRead;
                    while((numBytesRead = in.read(bytes)) > 0){
                        out.write(bytes , 0 , numBytesRead);
                        long elapsedTime = System.currentTimeMillis() - startTime;
                        long expectedTime = (numBytesRead / speedLimit) * 1000;

                        if (elapsedTime < expectedTime) {
                            Thread.sleep(expectedTime - elapsedTime);
                        }
                    }

                }catch (Exception e){
                    e.getMessage();
                }
            }else {
                resp.setContentType("text/html");

                PrintWriter printWriter = resp.getWriter();
                printWriter.println("File not found");
            }
        }else {
            resp.setContentType("text/html");

            PrintWriter printWriter = resp.getWriter();
            printWriter.println("File not found");
        }
    }
    private int parseSpeedLimit(String speedParam) {
        int speedLimit = 0;
        if (speedParam != null && !speedParam.isEmpty()) {
            try {
                speedLimit = Integer.parseInt(speedParam);
            } catch (NumberFormatException e) {
                // Xử lý ngoại lệ, ví dụ: ghi log hoặc cung cấp tốc độ mặc định.
                e.printStackTrace();
            }
        }
        return speedLimit;
    }
}
